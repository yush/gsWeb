package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.*;

@Entity
public class GSMovement extends Model {
	@Id
	public Long id;
	@ManyToOne
	public GSSiteswap parentTrick;
	private String _ssBase;
	private String _thrHand; 
	private String _thrPos;
	private String _catHand;
	private String _catPos;
	
	public void setSsBase(String b) { _ssBase = b; }
	public String getSsBase() { return _ssBase; }
	
	public void setThrHand(String h) { _thrHand = h;}
	public String getThrHand() {return _thrHand;}
	
	public void setThrPos(String p) { _thrPos = p;}
	public String getThrPos() { return _thrPos; }
	
	public void setCatHand(String h) { _catHand = h; }
	public String getCatHand() { return _catHand; }
	
	public void setCatPos(String p) { _catPos = p; }
	public String getCatPos() { return _catPos; }
	
	public String valueForPos(String pos) {
		if(pos.equals("r")) {
			return "32";
		} else if (pos.equals("l")) {
			return "32";
		} else if (pos.equals("c")) {
			return "0";
		} else
			return "";
	}

	public String asCatHandNotation() {
		String catStr;
		catStr = valueForPos(_catPos);
		if ((_catHand.equals("L") && _catPos.equals("r")) ||
			(_catHand.equals("R") && _catPos.equals("l"))) {
			catStr = new String("-"+catStr.toString());
		}
		return "("+catStr+")";
	}

	public String asThrHandNotation() {
		String thrStr;
		thrStr = valueForPos(_thrPos);
		if ((_thrHand.equals("R") && _thrPos.equals("l")) || 
			(_thrHand.equals("L") && _thrPos.equals("r"))) {
			thrStr = new String("-"+thrStr.toString());
		}			
		return "("+thrStr+")";
	}
	
}
