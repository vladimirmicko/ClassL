angular
   .module('DynFiles', [
      'ngRoute',
      'routeStyles',
      'ngTable',
      'ui.ace',
      'angularFileUpload'
   ])
   .config(function($routeProvider, $locationProvider, $httpProvider) {
      $routeProvider
         .when('/', {
            templateUrl: 'views/home.html',
            controller: 'HomeCtrl',
            controllerAs: 'vm'
         })
         .when('/home', {
            templateUrl: 'views/home.html',
            controller: 'HomeCtrl',
            controllerAs: 'vm'
         })
         .when('/calculate', {
            templateUrl: 'views/calculate.html',
            controller: 'CalculateCtrl',
            controllerAs: 'vm'
         })
         .when('/uploadFile', {
            templateUrl: 'views/upload_file.html',
            controller: 'UploadFileCtrl',
            controllerAs: 'vm'
         });
   })
   .run(function($rootScope) {
      $rootScope.ajaxHostPrefix = 'http://localhost:8091/vladaDemo/rest/';
   });