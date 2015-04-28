'use strict';

angular.module('adminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('room', {
                parent: 'entity',
                url: '/room',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'adminApp.room.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/room/rooms.html',
                        controller: 'RoomController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('room');
                        return $translate.refresh();
                    }]
                }
            })
            .state('roomDetail', {
                parent: 'entity',
                url: '/room/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'adminApp.room.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/room/room-detail.html',
                        controller: 'RoomDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('room');
                        return $translate.refresh();
                    }]
                }
            });
    });
