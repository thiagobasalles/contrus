app.directive('snackbar', function($timeout) {
    return {
        restrict: 'E',
        template: '<div class="snackbar" ng-show="showSnackbar">{{ message }}</div>',
        scope: {
            showSnackbar: '=',
            message: '='
        },
        link: function(scope, element, attrs) {
            scope.$watch('showSnackbar', function(value) {
                if (value === true) {
                    $timeout(function() {
                        scope.showSnackbar = false;
                    }, 3000); // Esconde depois de 3 segundos
                }
            });
        }
    };
});