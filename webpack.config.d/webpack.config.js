config.devServer = config.devServer || {}; // create devServer in case it is undefined
config.devServer = {
    "hot": true,
    "open": false,
    "port": 3000,
    "contentBase": [
        "C:\\Users\\pjaronski\\IdeaProjects\\kotlin-fullstack-mpp\\build\\processedResources\\frontend\\main\\public"
    ]
}
config.devServer.proxy = {
    "/api": "https://localhost:8080"
}
config.devServer.watchOptions = {
    "aggregateTimeout": 5000,
    "poll": 1000
};