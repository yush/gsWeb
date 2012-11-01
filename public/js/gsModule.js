define("gsModule", ["./dojo/dom-form", "./dojo/dom", "./dojo/dom-construct", "./dojo/on", "./dojo/query", "./dojo/parser", "./dojo/_base/array", "./dojo/json"], function(domForm, dom, domConstr, on, query, parser, array, json){

	// object siteSwap
	var Ss = function(idxCol) {				
		num = idxCol;
		ssBase = domForm.fieldToObject("ssBase-"+num);
		thrHand = domForm.fieldToObject("thrHand-"+num);
		thrPos = domForm.fieldToObject("thrPos-"+num);
		catHand = domForm.fieldToObject("catHand-"+num);
		catPos = domForm.fieldToObject("catPos-"+num);
		
		// retourne un Ss en Json
		this.toJson = function() {
			return '{"ssBase":"'+ssBase+'", '+
					 '"thrHand":"'+thrHand+'", '+
					 '"thrPos":"'+thrPos+'", '+
					 '"catHand":"'+catHand+'", '+
					 '"catPos":"'+catPos+'"'+
					 '}';
		}
	};
	
	var Trick = {				
		// convertit la grille de saisie en JSON string
		getSsToJson: function() {
			var strJson
			colNodeList = query("#ssBase input[id^='ssBase-']");
			colList = array.map(colNodeList, function(aNode){
				return aNode.id.substring(7);
			});
			strJson = '{"ssName":"'+domForm.fieldToObject("ssName")+'", '
			strJson = strJson+'"listMvmt":[';
			array.forEach(colList, function(aCol) {
			  aColObj = new Ss(aCol);
			  strJson = strJson + (aColObj.toJson());
			  if ( colList[colList.length-1] != aCol) {
				strJson = strJson + ',';
			  }
			});
			strJson = strJson + ']}';
			return strJson;
		},

		// on recupere l'index de la derniere colonne
		getIndexLast: function() {
			var listCol = query("#ssBase input[id^='ssBase-']");
			console.info(listCol);
			return listCol.length;
		},		
		
		// on construit une colonne de la grille de saisie
		addCol: function() {
			idx = this.getIndexLast()+1;
			array.forEach(['ssBase', 'thrHand', 'thrPos', 'catHand', 'catPos'], function(aItem) {
				idName = aItem+'-'+idx;
				switch(aItem) {
					case 'ssBase':  
						storeName = 'ssStore';
						break;
					case 'thrHand': case 'catHand':
						storeName = 'handStore';
						break;
					case 'thrPos': case 'catPos':
						storeName = 'posStore';
						break;						
				}				
				aLine = domConstr.toDom('<td><input id="'+idName+'" data-dojo-type="dijit/form/ComboBox" data-dojo-props="store:'+storeName+', searchAttr:\'name\'" name="'+idName+'" value="" /></td>');
				domConstr.place(aLine, dom.byId(aItem), 'last');
				parser.parse(aLine);				
			});
		},
	};
	return Trick;
});