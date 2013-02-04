define("gsTests", ["gsModule", "./dojo/dom-construct",, "./dojo/dom", "./dojo/dom-construct", "./dojo/on", "./dojo/query", "./dojo/parser", "./dojo/_base/array", "./dojo/json"], function(gsModule, domCons, dom, domConstr, on, query, parser, array, json) {

	var testCascade = {
		cascade : {
			"ssName" : "cascade",
			"listMvmt" : [{
				"ssBase" : "3",
				"thrHand" : "r",
				"thrPos" : "r",
				"catHand" : "l",
				"catPos" : "c"
			}, {
				"ssBase" : "3",
				"thrHand" : "l",
				"thrPos" : "l",
				"catHand" : "r",
				"catPos" : "c"
			}]
		},

		cascadeInverse : {
			"ssName" : "cascade",
			"listMvmt" : [{
				"ssBase" : "3",
				"thrHand" : "r",
				"thrPos" : "r",
				"catHand" : "l",
				"catPos" : "c"
			}, {
				"ssBase" : "3",
				"thrHand" : "l",
				"thrPos" : "l",
				"catHand" : "r",
				"catPos" : "c"
			}]
		},
		
		test : function() {
			console.info(this.cascade);
			gsModule.toJLab(this.cascade);
		}
	}
	return testCascade;

});
