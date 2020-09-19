import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean flag1 = true;
		char response;
		int opt;
		DataHandler dh = new DataHandler();
		dh.deserializeProject();
		dh.loadFiles();
		while (flag1) {
			try {
				System.out.print("**** Menu ****\n" + "1. Add Company\n" + "2. Add Project Owner\n" + "3. Add Project\n"
						+ "4. Capture Student Personalities\n" + "5. Add Student Preferences\n"
						+ "6. Shortlist Projects\n" + "7. Form Teams\n"+ "8. Display metric\n" + "9. Exit\n");
				Scanner input = new Scanner(System.in);
				//opt = Integer.parseInt(input.nextLine());
				opt = Integer.parseInt(input.nextLine());

				if (opt == 1) {
					System.out.println("Company");
					dh.addCompany();
				} else if (opt == 2) {
					System.out.println("Project Owner");
					dh.addProjectOwner();
				} else if (opt == 3) {
					System.out.println("Project");
					dh.addProject();
				} else if (opt == 4) {
					System.out.println("Capturing Student Personalities");
					dh.captureStudentPersonalities();
				} else if (opt == 5) {
					System.out.println("Adding Student Preferences");
					dh.addStudentPreferences();
				} else if (opt == 6) {
					System.out.println("Shortlisting Projects");
					dh.shortlistProjects();
				}
				else if (opt == 7) {
					System.out.println("Forming Teams");
					dh.formTeams();
				}
				
				else if (opt == 8) {
					System.out.println("Display Fitness metric");
					dh.displayFitnessMetric();
				}
				
				else if (opt == 9) {

					System.out.println("Do you want to exit? Y/N");
					response = input.nextLine().charAt(0);
					System.out.println("The response is " + response);
					if (Character.toUpperCase(response) == 'Y') {
						dh.serializeProject();
						System.out.println("Serialized objects");
						flag1 = true;
						System.out.println("See you again!");
						System.exit(0);
					}

				} else {
					System.out.println("Invalid option");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
