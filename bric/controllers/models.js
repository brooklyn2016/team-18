var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var ObjectId = Schema.ObjectId;

var db = {};

db.general_users_schema = new Schema({
	id: ObjectId,
	first_name: String,
	last_name: String,
	email: {type: String, unique: true},
	password: Object,
	iv: Buffer,
	random_url: {type: ObjectId, default: mongoose.Types.ObjectId()},
	first_login: {type: Boolean, deafult: true}
});

db.regular_users_schema = new Schema({
	id: ObjectId,
	uploads: [{
		video_id: ObjectId,
		video_name: String,
		upload_date: {type: Date, default: Date.now()},
		video_length: Number,
		size_video: Number,
		categories: [String],
		scheduled: Boolean,
		warning: Boolean,
		hidden: Boolean
	}],
	facebook: Object,
	twitter: Object,
	google: Object
});

db.bric_users_schema = new Schema({
	id: ObjectId,
	log:[{
		edited_user: {type: ObjectId, rel: 'regular_users'},
		edited_video: ObjectId,
		time_edited: {type: Date, defualt: Date.now()},
		added_categories: [String],
		removed_categories: [String],
		video_hidden: Boolean
	}]
});

db.general_user = mongoose.model('general_user', db.general_users_schema);
db.regular_user = mongoose.model('regular_user', db.regular_users_schema);
db.bric_user = mongoose.model('bric_user', db.bric_users_schema);

module.exports = db;