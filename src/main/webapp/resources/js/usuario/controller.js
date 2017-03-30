/**
 * Created by alfredo on 28/03/17.
 */

usuarioApp.controller('usuarioController', ['$scope', '$http', function($scope, $http) {
    $scope.name = 'ALFREDO';
    $http({
        method : "GET",
        url : "/admin/usuario/listar"
    }).then(function mySucces(response) {
        console.log( response );
    }, function myError(response) {
        $scope.nome = response.statusText;
    });
}]);