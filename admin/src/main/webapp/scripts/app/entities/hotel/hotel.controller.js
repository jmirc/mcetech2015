'use strict';

angular.module('adminApp')
    .controller('HotelController', function ($scope, Hotel, Room) {
        $scope.hotels = [];
        $scope.rooms = Room.query();
        $scope.loadAll = function() {
            Hotel.query(function(result) {
               $scope.hotels = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Hotel.update($scope.hotel,
                function () {
                    $scope.loadAll();
                    $('#saveHotelModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Hotel.get({id: id}, function(result) {
                $scope.hotel = result;
                $('#saveHotelModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Hotel.get({id: id}, function(result) {
                $scope.hotel = result;
                $('#deleteHotelConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Hotel.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteHotelConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.hotel = {name: null, description: null, address: null, city: null, postalCode: null, province: null, country: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
