var express = require('express');

var app = express();

var bodyParser  = require('body-parser');
var path = require('path');
var stormpath = require('express-stormpath');
var MongoClient = require('mongodb').MongoClient;


app.set('view engine','ejs');
app.set('views', path.join(__dirname,'views'));
app.use(express.static('./public'));
app.use(bodyParser.urlencoded({extended: false}));



app.get('/', function(req, res) {
    res.render('home');
})



app.listen(8000, function(){
    console.log("Running on 8000...")
});