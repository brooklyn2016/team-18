var express = require('express');

var app = express();
var session = require('client-sessions');
var bodyParser  = require('body-parser');
var path = require('path');
var stormpath = require('express-stormpath');
// var MongoClient = require('mongodb').MongoClient;
var mongoose = require('mongoose');
var routes = require('./routes/index');


app.set('view engine','ejs');
app.set('views', path.join(__dirname,'views'));
app.use(express.static('./public'));
app.use(session({
    cookieName: 'session',
    secret: 'random_string_goes_here',
    httpOnly: false,
    secure: false,
    ephemeral: true,
    resave: false
}));
app.use(stormpath.init(app, {
    web: {
        logout: {
            enabled: true
        }
    },
    expand: {
        customData: true,
    },
    apiKey: {
        id: process.env.STORMPATH_CLIENT_APIKEY_ID,
        secret: process.env.STORMPATH_CLIENT_APIKEY_SECRET
    },
    postLoginHandler: function (account, req, res) {
        req.session.user = account;
        console.log(account);
        res.redirect('/profile');
    },
    postLogoutHandler: function (account, req, res) {
        req.session.reset();
        res.redirect('/');
    }
}));

// MongoClient.connect("mongodb://localhost:27017/bric", function(err, db) {
//   if(!err) {
//     console.log("Bric is connected");
//   }
// });

var Schema = mongoose.Schema, ObjectId = Schema.ObjectId;
mongoose.connect('mongodb://localhost/bric', function(err, res){
	if(!err){
		console.log("Connected to database!");
	}
	else{
		throw err;
	}
});
app.use(bodyParser.urlencoded({extended: false}));
app.use('/', routes);
app.use('/profile', stormpath.loginRequired, routes);

// app.get('/', function(req, res) {
//     res.render('home');
// });








app.on('stormpath.ready', function () {
    console.log("Stormpath ready!");
    app.listen(8000, function () {
        console.log("Running on 8000...");
    });
});
