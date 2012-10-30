package models;

public class GSMovement {
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
		return "("+catStr+")";
	}

	public String asThrHandNotation() {
		String thrStr;
		thrStr = valueForPos(_thrPos);
		return "("+thrStr+")";
	}
	
}
