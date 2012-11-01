require(["dojo/ready", "dojo/dom-form", "dojo/dom", "dojo/on", "dojo/request/xhr", "dojo/domReady!"],
function(ready, domForm, dom, on, xhr){
	function gridToJson(){
		var gridJson;
		console.info("gridToJson");
		gridJson = domForm.toJson("gridForm");
		dom.byId("output").innerHTML = gridJson;
		xhr("gsSiteswap", {
			handleAs: "text/html",
			data: gridJson,
			method: "POST",
			headers : {
        		"Content-Type" : "application/json"   			
   			}
		}).then(function(response){
			console.info("reponse:"+response);
			dom.byId("jlabApplet").innerHTML = response;
		}, function(err) {
			console.info("Error:"+err);
		});
	};
	
	function testCascade() {
		xhr("gsSiteswap", {
			handleAs: "text/html",
			data: gridJson,
			method: "POST",
			headers : {
        		"Content-Type" : "application/json"   			
   			}
		}).then(function(response){
			console.info("reponse:"+response);
			dom.byId("jlabApplet").innerHTML = response;
		}, function(err) {
			console.info("Error:"+err);
		});		
	}
		
	ready(function(){
		parser.parse();
		console.info("ready");
		//convertForm = dom.byId("convertForm");
		//on(convertForm, "click", gridToJson);
	});
});
