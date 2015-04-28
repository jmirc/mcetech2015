'use strict';

angular.module('adminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('hotel', {
                parent: 'entity',
                url: '/hotel',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'adminApp.hotel.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/hotel/hotels.html',
                        controller: 'HotelController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('hotel');
                        return $translate.refresh();
                    }]
                }
            })
            .state('hotelDetail', {
                parent: 'entity',
                url: '/hotel/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'adminApp.hotel.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/hotel/hotel-detail.html',
                        controller: 'HotelDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('hotel');
                        return $translate.refresh();
                    }]
                }
            });
    });
