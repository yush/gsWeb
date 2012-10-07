package models;

import java.util.ArrayList;
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
	
	public String asJlabHandNotation() {
		String str = "???";
		return str;
	}	
	
}
