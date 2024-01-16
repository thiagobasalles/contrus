app.controller('CadastrarColaboradorController', function($scope, $http) {
    
    init();
    function init(){
        buscarListaColaboradoresHierarquia();
    };

    $scope.colaboradores = [];
    $scope.colaborador = {
        nome: '',
        senha: '',
        telefone: '',
        email: '',
        dataNascimento: '',
        colaboradorId: ''
    };

    function buscarListaColaboradoresHierarquia() {
        $http.get('api/contrus/listar-colaboradores')
        .then(function(response) {
            $scope.colaboradores = response.data
        })
        .catch(function(error) {
            console.error("Erro:", error);
        });
    }

    $scope.enviarFormulario = function() {
        if($scope.colaborador.telefone.replace(/\D/g, '').length != 11){
            alert("Telefone inválido!")
        }
        if($scope.colaborador.senha.length < 3){
            alert("Senha inválido!")
        }
        if($scope.colaborador.nome.length < 3){
            alert("Nome inválido!")
        }

        $http.post('api/contrus/cadastrar-colaborador?nome='+$scope.colaborador.nome+'&senha='+$scope.colaborador.senha
        +"&telefone="+$scope.colaborador.telefone.replace(/\D/g, '')+"&email="+$scope.colaborador.email+"&dataNascimento="+$scope.colaborador.dataNascimento
        +"&colaboradorHierarquiaSuperiorId="+$scope.colaborador.colaboradorId
        )
        .then(function(response) {
            alert("Cadastro efetuado com sucesso!");
            $scope.colaborador = [];
            buscarListaColaboradoresHierarquia();
        })
        .catch(function(error) {
            alert(error);
        });
    }

    $scope.aplicarMascaraTelefoneCelular = function() {
        var telefoneCelular = $scope.colaborador.telefone.replace(/\D/g, '');
  
        if (telefoneCelular.length === 11) {
            telefoneCelular = telefoneCelular.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
        }
        $scope.colaborador.telefone = telefoneCelular;
      };

});