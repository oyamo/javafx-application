import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {
	private String projectID;
	private String descr;
	private String title;
	private String ownerID;
	private String rank;
	

	private ArrayList<Student> students;

	public  Project() {
		// TODO Auto-generated method stub

	}
	public Project(String title, String projectID, String descr, String ownerID, String rank) 
	{
		this.title=title;
		this.projectID=projectID;
		this.descr= descr;
		this.ownerID=ownerID;
		this.rank=rank;
		this.students= new ArrayList<Student>();
		
	}
	
	public void assignStudent(Student s) {
		students.add(s);
//		System.out.println("in");
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public int getNoOfMembers() {
		return students.size();
	}
	
	public String getTitle() {
		return title;
	}

	public String getProjectID() {
		return projectID;
	}



	public String getDescr() {
		return descr;
	}


	public String getownerID() {
		return ownerID;
	}
	
	public String getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return title+";"+projectID+";"+descr+";"+ownerID+";"+rank;
	}
}
