var express = require('express');

var app = express();

var bodyParser  = require('body-parser');
var path = require('path');
var stormpath = require('express-stormpath');
// var MongoClient = require('mongodb').MongoClient;
var mongoose = require('mongoose');
var routes = require('./routes/index');

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

app.set('view engine','ejs');
app.set('views', path.join(__dirname,'views'));

app.use(express.static('public'));
app.use(bodyParser.urlencoded({extended: false}));

app.use('/', routes);

app.get('/', function(req, res) {
    res.render('home');
});

app.listen(8000, function(){
    console.log("Running on 8000...");
});