package models;

import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import models.GSMovement;

public class GSSiteswap {
	private String _ssName;
	private ArrayList<GSMovement> _listMvmt;
	
	public GSSiteswap() {
		_listMvmt = new ArrayList<GSMovement>();
	}
	
	public String getSsName() {return _ssName;}
	public void setSsName(String aSs) {_ssName = aSs;}
	
	public ArrayList<GSMovement> getListMvmt() { return _listMvmt; }

	public GSMovement getNext(GSMovement aRefMvmt) {
		int currentPos;
		currentPos = _listMvmt.indexOf(aRefMvmt);
		if (currentPos == _listMvmt.size()-1) 
			return _listMvmt.get(0);
		else
			return _listMvmt.get(currentPos+1);
	}
	
	public GSMovement getMvmtInNextBeat(GSMovement aMvmt, int nextBeat) {
		GSMovement nextMvmt;
		nextMvmt = aMvmt;
		for(int i=0; i < nextBeat; i++) {
			nextMvmt = getNext(nextMvmt);
		}
		return nextMvmt;
	}
	
	public String asJlabHandNotation() {
		StringBuilder str = new StringBuilder();
		GSMovement nextMvmt;
		for (GSMovement aMvmt  : _listMvmt) {
			nextMvmt = getMvmtInNextBeat(aMvmt, 3);
			str.append(aMvmt.asThrHandNotation());
			str.append(nextMvmt.asCatHandNotation());
			str = str.append(".");
		} 
		return str.toString();
	}	
	
}
