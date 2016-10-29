var express = require('express');

var app = express();

var bodyParser  = require('body-parser');
var path = require('path');
var stormpath = require('express-stormpath');
var MongoClient = require('mongodb').MongoClient;
var routes = require('./routes/index');

app.set('view engine','ejs');
app.set('views', path.join(__dirname,'views'));
app.use(express.static('./public'));
app.use(stormpath.init(app, {
    apiKey: {
        id: process.env.STORMPATH_CLIENT_APIKEY_ID,
        secret: process.env.STORMPATH_CLIENT_APIKEY_SECRET
    }
}));
app.use(bodyParser.urlencoded({extended: false}));
app.use('/', routes);

// app.get('/', function(req, res) {
//     res.render('home');
// });








app.on('stormpath.ready', function () {
    console.log("Stormpath ready!");
    app.listen(8000, function () {
        console.log("Running on 8000...");
    });
});
