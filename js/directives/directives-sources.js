angular
    .module('streamPipesApp') 
    .directive('sepaStreamDetail', function() {
    	return {
    		restrict : 'E',
    		templateUrl : 'modules/sensors/directives/stream-detail.tmpl.html',
    		scope : {
    			stream : "=stream",
    			disabled : "=disabled"
    		},
    		
    		controller: function($scope, $element) {
    			
    			$scope.activeStreamTab = "basics";
    			 
    			
    			$scope.selectStreamTab = function(name) {
    				$scope.activeStreamTab = name;
    			}
    			
    			$scope.isStreamTabSelected = function(name) {
    				return $scope.activeStreamTab == name;
    			}
    			
    			$scope.getStreamActiveTabCss = function(name) {
    				if (name == $scope.activeStreamTab) return "md-fab md-accent md-mini";
    				else return "md-fab md-accent md-mini wizard-inactive";
    			}
    			
    			$scope.addProperty = function(properties) {
    				properties.push({"type" : "de.fzi.cep.sepa.model.impl.eventproperty.EventPropertyPrimitive", "properties" : {"runtimeName" : "", "runtimeType" : "", "domainProperties" : []}});
    			}
    			
    			$scope.removeProperty = function(index, properties) {
    				properties.splice(index, 1);
    			}
    		}
    	}
    }) 
    .directive('streamDeployment', function() {
    	return {
    		restrict : 'E',
    		templateUrl : 'modules/sensors/directives/stream-deployment.tmpl.html',
    		scope : {
    			selectedSource : "=stream",
    			disabled : "=disabled"
    		},
    		
    		controller: function($scope, $element) {
    			
    			
    		}
    	}
    }) ;