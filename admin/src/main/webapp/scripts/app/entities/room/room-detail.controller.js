'use strict';

angular.module('adminApp')
    .controller('RoomDetailController', function ($scope, $stateParams, Room, Hotel) {
        $scope.room = {};
        $scope.load = function (id) {
            Room.get({id: id}, function(result) {
              $scope.room = result;
            });
        };
        $scope.load($stateParams.id);
    });
