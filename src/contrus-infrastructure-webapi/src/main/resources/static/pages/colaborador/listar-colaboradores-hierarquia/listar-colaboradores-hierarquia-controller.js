app.controller('ListarColaboradoresHierarquiaController', function($scope, $http) {
    
    init();
    
    $scope.colaboradoresHierarquia = [];

    function init() {
        listarColaboradoresHierarquia();
    }

    function listarColaboradoresHierarquia() {
        $scope.colaboradoresHierarquia = [];
        $http.get('api/contrus/listar-colaboradores-hierarquia')
        .then(function(response) {
            $scope.colaboradoresHierarquia = [];
            $scope.colaboradoresHierarquia = response.data;
        })
        .catch(function(error) {
            $scope.colaboradoresHierarquia = [];
            console.error("Erro:", error);
        })
    }

});