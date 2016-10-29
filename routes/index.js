var express = require('express');
var bodyParser = require('body-parser');
var session = require('client-sessions');
var router = express.Router();

var model = require('../controllers/models');

router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: false }));


//======== GET ROUTES ========= //
router.get('/', function(req, res) {
   // console.log(req.session);
    if(req.session != null) {
        user = req.session;
    } else {
        user = null;
    }
    res.render('home', {user:user});
});

router.get('/profile', function(req, res){
    console.log(req.session);
    var user = req.session;
    res.render('profile', { user: user});
});

//For Logout
router.post('/logout', function(req, res) {
    req.session = null;
    req.session.user = null;

    res.redirect('/');
});

//======== POST ROUTES ========= //


module.exports = router;
