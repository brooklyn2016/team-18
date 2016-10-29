var express = require('express');
var bodyParser = require('body-parser');

var router = express.Router();

var model = require('../controllers/models');

router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: false }));


//======== GET ROUTES ========= //
router.get('/', function(req, res) {
    res.render('home');
});

router.get('/login', function(req, res){
	res.render('login');
});

//======== POST ROUTES ========= //


module.exports = router;
