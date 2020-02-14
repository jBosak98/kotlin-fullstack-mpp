path = require("path")

config = config || {};
config.devServer = config.devServer || {};
config.devServer = {
    "hot": true,
    "open": false,
    "port": 3000,
    contentBase: [
        path.resolve(__dirname, "..\\..\\..\\processedResources\\frontend\\main\\public")
    ]
}
config.devServer.proxy = config.devServer.proxy || {};
config.devServer.proxy = {
    "/api": "https://localhost:8080"
}
config.devServer.watchOptions = config.devServer.watchOptions || {};
config.devServer.watchOptions = {
    "aggregateTimeout": 5000,
    "poll": 1000
};

/*module.exports = {
    entry: {
        main: path.resolve(__dirname, "kotlin\\kotlin-fullstack-mpp-frontend.js")
    }
}*/
