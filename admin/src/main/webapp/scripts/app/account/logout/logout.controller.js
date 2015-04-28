'use strict';

angular.module('adminApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
