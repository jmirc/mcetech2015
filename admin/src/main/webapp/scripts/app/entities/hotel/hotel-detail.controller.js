'use strict';

angular.module('adminApp')
    .controller('HotelDetailController', function ($scope, $stateParams, Hotel, Room) {
        $scope.hotel = {};
        $scope.load = function (id) {
            Hotel.get({id: id}, function(result) {
              $scope.hotel = result;
            });
        };
        $scope.load($stateParams.id);
    });
