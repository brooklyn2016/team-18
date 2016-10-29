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

router.get('/profile', function(req, res){
  
    res.render('profile');
});

//======== POST ROUTES ========= //


module.exports = router;
