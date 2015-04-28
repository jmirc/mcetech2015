/* globals $ */
'use strict';

angular.module('adminApp')
    .directive('adminAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
