'use strict';

angular.module('adminApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


