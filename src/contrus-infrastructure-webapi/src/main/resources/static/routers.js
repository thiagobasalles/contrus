app.config(function($routeProvider) {
$routeProvider
    .when('/cadastrar-colaborador', {
        templateUrl: 'pages/colaborador/cadastrar-colaborador/cadastrar-colaborador-page.html',
        controller: 'CadastrarColaboradorController'
    })
    .when('/listar-colaboradores-hierarquia',{
        templateUrl: 'pages/colaborador/listar-colaboradores-hierarquia/listar-colaboradores-hierarquia-page.html',
        controller: 'ListarColaboradoresHierarquiaController'
    })
    .otherwise({
        redirectTo: '/'
    });
});