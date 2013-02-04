package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import play.db.ebean.*;

import models.GSMovement;

@Entity
public class GSSiteswap extends Model {
	@Id
	public Long id;	
	private String _ssName;
	@OneToMany(cascade=CascadeType.ALL)
	private List<GSMovement> _listMvmt;
	
	public GSSiteswap() {
		_listMvmt = new ArrayList<GSMovement>();
	}
	
	public String getSsName() {return _ssName;}
	public void setSsName(String aSs) {_ssName = aSs;}
	
	public List<GSMovement> getListMvmt() { return _listMvmt; }
	
	public GSMovement newMovement() {
		GSMovement aMove = new GSMovement();
		_listMvmt.add(aMove);
		return aMove;
	}

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
	
	// retourne l'enchainement de position des mains
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
	
	// retourne le siteswap version vanilla (juste les nombres)
	public String asVanillaSiteswap() {
		StringBuilder str = new StringBuilder();
		GSMovement nextMvmt;
		for (GSMovement aMvmt  : _listMvmt) {
			str = str.append(aMvmt.getSsBase());
		} 
		return str.toString();		
	}
	
}
