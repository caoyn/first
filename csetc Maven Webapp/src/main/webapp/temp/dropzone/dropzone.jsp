<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/dropzone/dropzone.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/dropzone/dropzone.css">
</head>
<body>
	<div class="row">

        <div class="col-md-12">

            <form dropzone2  class="dropzone" enctype="multipart/form-data" method="post"></form>

        </div>

    </div>
	<script>
	var manage = angular.module('hubBrowseManageDirectives', []);

    manage.directive('dropzone2', function () {
        return {
            restrict: 'EA',
            controller: ['$scope', '$element', '$attrs', '$timeout', function ($scope, $element, $attrs, $timeout) {
                $element.dropzone({
                    url : "rest/components/"+$scope.component.name+"/"+$scope.component.version+"/images",
                    autoDiscover : false,
                    autoProcessQueue: true,
                    addRemoveLinks: true,
                    addViewLinks: true,
                    acceptedFiles: ".jpg,.png",
                    dictDefaultMessage: "upload head picture",
                    maxFiles : "1",
                    dictMaxFilesExceeded: "Only can upload one picture, repeat upload will be deleted!",
                    init: function () {
                     var mockFile = { name: "Filename", 
                                      size: 10000
                                     };
                     this.emit("addedfile", mockFile);
                     mockFile._viewLink.href = "rest/components/"+$scope.component.name+"/"+$scope.component.version +"/"+$scope.component.image;
                     mockFile._viewLink.name = $scope.component.image;
                     this.emit("thumbnail", mockFile, "rest/components/"+$scope.component.name+"/"+$scope.component.version +"/"+$scope.component.image);
                     this.emit("complete", mockFile);


                        $(".dz-view").colorbox({
                               rel:'dz-view', 
                               width:"70%",
                               height:"80%"
                        });

                        this.on("error", function (file, message) {
                            alert(message);
                            this.removeFile(file);
                        });
                        this.on("success", function(file,imageInfo) {

                          file._viewLink.href = imageInfo.newfile;
                          file._viewLink.name = imageInfo.newfile;

                           $scope.$apply(function() {
                                $scope.component.image="rest/components/"+$scope.component.name+"/"+$scope.component.version+"/"+imageInfo.newfile;
                           });

                        });
                        this.on("removedfile", function(file) {
                           var removeFileUrl = file._viewLink.name;

                                if($scope.component.image == removeFileUrl){
                                    this.removeFile(file);
                                }

                          });

                    }
                });

            }]
        };
    });
	</script>
</body>
</html>