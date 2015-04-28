/* globals $ */
'use strict';

angular.module('adminApp')
    .directive('adminAppPager', function() {
        return {
            templateUrl: 'scripts/components/form/pager.html'
        };
    });
