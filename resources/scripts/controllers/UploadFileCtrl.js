angular.module('DynFiles')
   .controller('UploadFileCtrl', ['$scope', '$rootScope', 'FileUploader', 'UploadFileService',
      function($scope, $rootScope, FileUploader, UploadFileService) {
         var vm = this;
         /************/

         vm.fileUploader = new FileUploader();
         vm.fileUploader.url = $rootScope.ajaxHostPrefix + "upload";

         vm.fileItem = {};

         bSuccessMessage = true; // Show success message for ajax calls
         bFailMessage = true; // Show faul message for ajax calls
         /*----------  Init function  ----------*/
         function init() {}

         /*----------  Click methods  ----------*/
         vm.addFile = function(fileName, fileUrl) {
            UploadFileService.addFile(fileName, fileUrl).then(function(result) {
               console.log(result)
               if (bSuccessMessage) {
                  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@ addFile - SUCCESSFUL");
               }
            }, function() {
               if (bFailMessage) {
                  console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~ addFile - FAILED");
               }
            });
         }

         /*----------  Upload functions  ----------*/
         vm.fileUploader.onAfterAddingFile = function(fileItem) {
            vm.fileItem = fileItem;
         };

         vm.fileUploader.onSuccessItem = function(fileItem, re, status, headers) {
            vm.fileUrl = re.imageUrl;
         };

         vm.fileUploader.onErrorItem = function(fileItem, res, status, headers) {};
         /*----------  Construct  ----------*/
         init();
      }
   ]);