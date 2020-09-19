import java.io.Serializable;

public class Student implements Serializable {
	private String sId;
	private String ranking;
	private char personalityType;
	private String conflict;
	private String preferences;
	
	public Student(String id, String ranking, String preferences) {
		this.sId = id;
		this.ranking = ranking;
//		this.personalityType= '\0';
//		this.conflict= "NC";
		this.preferences=preferences;
	}
	
	public Student(String id, String ranking, char personalityType, String conflict) {
		this.sId = id;
		this.ranking = ranking;
		this.personalityType= personalityType;
		this.conflict= conflict;
//		this.preferences = "NP";
	}
	
	public Student(String id, String ranking) {
		this.sId = id;
		this.ranking = ranking;
		this.personalityType= '\0';
		this.conflict= "NC";
		this.preferences= "NP";
	}
	

	public String getsId() {
		return sId;
	}

	public String getRanking() {
		return ranking;
	}
	
	public String getConflict() {
		return conflict;
	}
	
	public char getPersonalityType() {
		return personalityType;
	}

	public void setId(String id) {
		this.sId = id;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public boolean setConflict(String conflictID) {
		System.out.println(conflictID);
		this.conflict = conflictID;
		return true;
	}
	
	

	public String getPreferences() {
		return preferences;
	}

	public boolean setPreferences(String preferences) {
		this.preferences = preferences;
		return true;
	}

	public boolean setPersonalityType(char personalityType) {
		char type= Character.toUpperCase(personalityType);
		if(type=='A' || type=='B' ||type=='C' ||type=='D') {
			this.personalityType =  type;
			return true;
		}
		return false;
	}

	@Override
//	public String toString() {
//		return sId+ ";"+ ranking+";"+ personalityType+";"+ conflict + ";" + preferences;
//	}
	public String toString() {
		return sId;
	}

}
