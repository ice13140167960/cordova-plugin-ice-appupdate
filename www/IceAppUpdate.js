var exec = require('cordova/exec');

exports.update = function (arg0, success, error) {
    exec(success, error, 'IceAppUpdate', 'update', [arg0]);
};
