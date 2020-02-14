val kotlin_version: String by project

val ktor_version: String by project
val logback_version: String by project

val annotations_version: String by project

val kotlin_react_version: String by project
val kotlin_react_dom_version: String by project
val kotlin_extensions_version: String by project
val kotlin_css_version: String by project
val kotlin_css_js_version: String by project
val kotlin_styled_version: String by project

val kotlinx_serialization_version: String by project
val kotlinx_html_version: String by project


plugins {
    kotlin("multiplatform") version "1.3.61"
    kotlin("kapt") version "1.3.61"
    kotlin("plugin.serialization") version "1.3.61"
}

apply {
    plugin("kotlin-dce-js")
}

group = "com.jaro2gw"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://kotlin.bintray.com/kotlin-eap")
    maven(url = "https://kotlin.bintray.com/kotlinx")
    maven(url = "https://kotlin.bintray.com/js-externals")
    maven(url = "https://kotlin.bintray.com/kotlin-js-wrappers")
    jcenter()
}


kotlin {
    js("frontend") {
        useCommonJs()
        nodejs()
        browser {
            compilations.all {
                kotlinOptions {
                    metaInfo = true
                    sourceMap = true
                    sourceMapEmbedSources = "always"
                    moduleKind = "commonjs"
                    main = "call"
                }
            }
        }
    }
    jvm("backend")
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val frontendMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-js", kotlin_version))

                implementation(kotlinDependency("react", kotlin_react_version))
                implementation(kotlinDependency("react-dom", kotlin_react_dom_version))
                implementation(kotlinDependency("extensions", kotlin_extensions_version))
                implementation(kotlinDependency("css", kotlin_css_version))
                implementation(kotlinDependency("css-js", kotlin_css_js_version))
                implementation(kotlinDependency("styled", kotlin_styled_version))

                implementation(kotlinxDependency("html-js", kotlinx_html_version))
                implementation(kotlinxDependency("serialization-runtime-js", kotlinx_serialization_version))

                implementation("org.jetbrains:annotations:$annotations_version")

                implementation(npm("webpack"))
                implementation(npm("webpack-cli"))
                implementation(npm("webpack-dev-server"))

                implementation(npm("react"))
                implementation(npm("react-dom"))
                implementation(npm("react-draggable"))
                implementation(npm("react-list"))
                implementation(npm("react-is"))

                implementation(npm("inline-style-prefixer"))
                implementation(npm("core-js"))
                implementation(npm("styled-components"))
                implementation(npm("jquery"))
            }

        }

        val backendMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-jdk8", kotlin_version))
                implementation(ktorDependency("server-netty"))
                implementation(ktorDependency("server-core"))
                implementation(ktorDependency("locations"))
                implementation(ktorDependency("server-sessions"))
                implementation(ktorDependency("websockets"))
                implementation(ktorDependency("gson"))
                implementation("ch.qos.logback:logback-classic:$logback_version")
            }
        }
    }
}

//tasks[""]

fun kotlinDependency(name: String, version: String) = "org.jetbrains:kotlin-$name:$version"
fun ktorDependency(name: String, version: String = ktor_version) = "io.ktor:ktor-$name:$version"
fun kotlinxDependency(name: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$name:$version"