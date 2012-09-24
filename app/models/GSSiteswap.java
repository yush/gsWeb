package models;

import java.util.ArrayList;
import models.GSMovement;

public class GSSiteswap {
	private String _ssBase;
	private ArrayList<GSMovement> _listMvmt;
	
	public GSSiteswap() {
		_listMvmt = new ArrayList<GSMovement>();
		GSMovement aMove = new GSMovement();
		aMove.setThrHand("r");
		aMove.setThrPos("c");
		_listMvmt.add(aMove);
		GSMovement aMove2 = new GSMovement();
		aMove2.setThrHand("l");
		aMove2.setThrPos("c");		
		_listMvmt.add(aMove2);
	}
	
	public String getSsBase() {return _ssBase;}
	public void setSsBase(String aSs) {_ssBase = aSs;}
	
	public ArrayList<GSMovement> getListMvmt() { return _listMvmt; }
	
	public String getGeneralizedSiteswap() {
		String str = "test";
		return str;
	}
	
	
}
