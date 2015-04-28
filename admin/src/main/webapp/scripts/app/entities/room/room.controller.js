'use strict';

angular.module('adminApp')
    .controller('RoomController', function ($scope, Room, Hotel) {
        $scope.rooms = [];
        $scope.hotels = Hotel.query();
        $scope.loadAll = function() {
            Room.query(function(result) {
               $scope.rooms = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Room.update($scope.room,
                function () {
                    $scope.loadAll();
                    $('#saveRoomModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Room.get({id: id}, function(result) {
                $scope.room = result;
                $('#saveRoomModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Room.get({id: id}, function(result) {
                $scope.room = result;
                $('#deleteRoomConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Room.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteRoomConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.room = {name: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
