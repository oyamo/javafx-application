
public class ProjectOwner {

	private String firstName;
	private String surname;
	private String ownerID;
	private String role;
	private String email;

	public ProjectOwner(String firstName, String surname, String ownerID, String role, String email) {
		this.firstName = firstName;
		this.surname = surname;
		this.ownerID = ownerID;
		this.role = role;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getownerID() {
		return ownerID;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return firstName + ";" + surname + ";" + ownerID + ";" + role + ";" + email;
	}
}
