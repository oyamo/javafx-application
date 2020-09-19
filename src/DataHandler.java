import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class DataHandler {

	// Hashmaps for Company, ProjectOwner, Project, Student
	HashMap<String, Company> compHashMap = new HashMap<String, Company>();
	HashMap<String, ProjectOwner> ownerHashMap = new HashMap<String, ProjectOwner>();
	HashMap<String, Project> projHashMap = new HashMap<String, Project>();
	HashMap<String, Student> studHashMap = new HashMap<String, Student>();
	HashMap<String, Student> studPreferences = new HashMap<String, Student>();
	HashMap<String, Student> studInfoMap = new HashMap<String, Student>();
	HashMap<String, Project> shortlistProjectMap = new HashMap<String, Project>();
	HashMap<String, Student> availableStudents = new HashMap<String, Student>();
//	HashMap<String, Teams> teams = new HashMap<String, Teams>();
//	ArrayList <Student> students = new ArrayList<Student>();
//	projTally = new HashMap<String, Integer>();

	public void loadFiles() {

//		readPreferences();
//		readStudentinfo();
//		readStudents();
//		readShortlistedProjects();
		readAvailStudent();
	}

	// Method for adding company
	public void addCompany() {

		System.out.println("Adding a company...");

		// Reading companies.txt and loading the data in compHashMap to avoid duplicate
		// keys.
		readCompanies();

		Scanner input = new Scanner(System.in);
		boolean loop = true;
		String cID;
		String companyName;
		int abn;
		String url;
		String address;
		// Taking inputs from user
		do {
			try {
				System.out.print("Company ID: C");
				cID = "C" + Integer.parseInt(input.nextLine().trim());
				System.out.print("Company name: ");
				companyName = input.nextLine().trim();
				System.out.print("ABN: ");
				abn = Integer.parseInt(input.nextLine().trim());
				System.out.print("Url: ");
				url = input.nextLine().trim();
				System.out.print("Address: ");
				address = input.nextLine().trim();

				// Loading the inputs in the Company hashmap
				compHashMap.put(cID, new Company(cID, companyName, abn, url, address));

				// Writing in companies.txt file
				try {
					String fileName = "companies.txt";
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

					for (String key : compHashMap.keySet()) {
						out.println(compHashMap.get(key));
					}
					out.close();

					System.out.println("Successfully written to the " + fileName + " file.");
					loop = false;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error occurred");
					e.printStackTrace();
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				loop = true;
			}

		} while (loop);

	}

	// Method for adding Project owner
	public void addProjectOwner() {
		System.out.println("Adding a Project Owner...");

		readOwners();

		Scanner input = new Scanner(System.in);
		boolean loop = true;
		String firstName;
		String surname;
		String ownerID;
		String role;
		String email;

		do {
			try {

				// Taking inputs from the user

				System.out.print("Owner ID: Own");
				ownerID = "Own" + Integer.parseInt(input.nextLine());
				System.out.print("First name: ");
				firstName = input.nextLine().trim();
				System.out.print("Surname: ");
				surname = input.nextLine().trim();
				System.out.print("Role: ");
				role = input.nextLine().trim();
				System.out.print("Email: ");
				email = input.nextLine().trim();

				// Loading the inputs in the hashmap
				ownerHashMap.put(ownerID, new ProjectOwner(firstName, surname, ownerID, role, email));

				// Writing to owner.txt file
				try {
					String fileName = "owners.txt";
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

					for (String key : ownerHashMap.keySet()) {
						out.println(ownerHashMap.get(key));
					}
					out.close();
					System.out.println("Successfully written to the " + fileName + " file.");
					loop = false;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error occurred");
					e.printStackTrace();
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				loop = true;
			}

		} while (loop);

	}

	// Method for adding a Project
	public void addProject() {
		System.out.println("Adding a Project...");

		// Reading the existing projects.txt file
		readProjects();

		Scanner input = new Scanner(System.in);
		boolean loop = true;
		String title;
		String projectID;
		String descr;
		String projectOwner;
		String rank;

		do {
			try {

				// Taking inputs from the user
				System.out.print("Project ID: Pr");
				projectID = "Pr" + Integer.parseInt(input.nextLine().trim());
				System.out.print("Title: ");
				title = input.nextLine().trim();
				System.out.print("Description: ");
				descr = input.nextLine().trim();
				System.out.print("ProjectOwner's name: Own");
				projectOwner = "Own" + Integer.parseInt(input.nextLine().trim());
				System.out.print("Ranking of the skills in descending order(e.g N 4 A 3 W 2 P 1): ");
				rank = input.nextLine().trim();

				// Loading inputs to the hashmap
				projHashMap.put(projectID, new Project(title, projectID, descr, projectOwner, rank));

				// Writing the hashmap in projects.txt file
				try {
					String fileName = "projects.txt";
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

					for (String key : projHashMap.keySet()) {
						out.println(projHashMap.get(key));
					}
					out.close();
					System.out.println("Successfully written to the " + fileName + " file.");
					loop = false;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error occurred");
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				loop = true;
			}
		} while (loop);

	}

	// Method for capturing student personalities
	public void captureStudentPersonalities() {

		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int count = 0;
		// Reading students.txt file in order to load all the students
//		readStudentinfo();
//		readStudents();

		// Reading studentinfo.txt file in order to check how many time A,B,C or D
		// occurs
		// so that user couldn't add it more than 5 times
		try {
			File file = new File("studentinfo.txt");
			Scanner in = null;
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String str = in.nextLine();
				String arr[] = str.split(";");
				char ptype = arr[2].charAt(0);
				if (ptype == 'A') {
					a++;
				} else if (ptype == 'B') {
					b++;
				} else if (ptype == 'C') {
					c++;
				} else if (ptype == 'D') {
					d++;
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Scanner input = new Scanner(System.in);

		boolean flag = true;
		Student aStudent = null;
		while (flag) {

			// Taking input from user
			System.out.println("Which student you would like to update? (x to cancel)");
			String id = input.nextLine().trim();
			if (id.toLowerCase().contains("x")) {
				break;
			} else {
				// Checking if the id entered by the user is present in studHashMap
				for (String key : studHashMap.keySet()) {
					if (studHashMap.get(key).getsId().equals(id)) {
						aStudent = studHashMap.get(key);
					}
				}
			}
			if (aStudent != null) {
				System.out.println("Matching student as follows:");
				System.out.println(aStudent.getsId() + " " + aStudent.getRanking());

				System.out.println("What is " + aStudent.getsId() + "'s personality type:");
				char type = input.nextLine().charAt(0);

				// Checking whether the user has entered one of the options from A, B, C or D
				// Checking whether the maximum number of personality types A, B, C or D is less
				// than 5.

				if (Character.toUpperCase(type) == 'A') {
					if (aStudent.setPersonalityType(type) && a < 5) {
						aStudent.setPersonalityType(type);
						System.out.println("Personality type updated");

					} else {
						System.out.println("Maximum limit for this personality type has reached");
						break;
					}
				} else if (Character.toUpperCase(type) == 'B') {
					if (aStudent.setPersonalityType(type) && b < 5) {
						aStudent.setPersonalityType(type);
						System.out.println("Personality type updated");

					} else {
						System.out.println("Maximum limit for this personality type has reached");
						break;
					}
				} else if (Character.toUpperCase(type) == 'C') {
					if (aStudent.setPersonalityType(type) && c < 5) {
						aStudent.setPersonalityType(type);
						System.out.println("Personality type updated");

					} else {
						System.out.println("Maximum limit for this personality type has reached");
						break;
					}
				} else if (Character.toUpperCase(type) == 'D') {
					if (aStudent.setPersonalityType(type) && d < 5) {
						aStudent.setPersonalityType(type);
						System.out.println("Personality type updated");

					} else {
						System.out.println("Maximum limit for this personality type has reached");
						break;
					}
				} else {
					System.out.println("Please enter A,B,C or D");
					break;
				}

				System.out
						.println(aStudent.getsId() + " " + aStudent.getRanking() + " " + aStudent.getPersonalityType());

				// Taking input from user
				System.out.println("Enter up to two student IDs that have potential conflict:");
				int i = 0;
				int idCount = 1;
				String idArray[] = new String[2];
				while (i < 2) {
					System.out.println("Enter " + idCount + "'th student ID that have potential conflict:");
					idArray[i] = input.nextLine().trim();
					idCount++;
					i++;
				}
				String IDs = idArray[0] + " " + idArray[1];

				// Splitting the ids entered by the user by space to check whether user has
				// entered
				// correct Ids i.e. whether the ids are present in studHashMap table.
				// Checking that the conflict ids are not same as the student id whose
				// personality is being updated.

				String[] splitStr = IDs.trim().split("\\s+");

				for (String key : studHashMap.keySet()) {
					if (key.equals(splitStr[0]) || key.equals(splitStr[1])) {
						count++;
					}
				}

				if (aStudent.setConflict(IDs) && count == 2) {

					System.out.println("Potential conflicts updated");
				} else {
					System.out.println("Incorrect student id");
					break;
				}
			} else {
				System.out.println("Student not found");
				break;
			}

//			studHashMap.put(aStudent.getsId(), new Student(aStudent.getsId(), aStudent.getRanking(),
//					aStudent.getPersonalityType(), aStudent.getConflict(), aStudent.getPreferences()));

			studInfoMap.put(aStudent.getsId(), new Student(aStudent.getsId(), aStudent.getRanking(),
					aStudent.getPersonalityType(), aStudent.getConflict()));

//			writeStudents();

			// Writing the details into studentinfo.txt file
			String fileName = "studentinfo.txt";
			try {

				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

				for (String key : studInfoMap.keySet()) {
					out.println(studInfoMap.get(key).getsId() + ";" + studInfoMap.get(key).getRanking() + ";"
							+ studInfoMap.get(key).getPersonalityType() + ";" + studInfoMap.get(key).getConflict());
				}

				System.out.println("Successfully written to the " + fileName + " file.");
				out.close();

				flag = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// Method for adding Student Preferences
	public void addStudentPreferences() {

		Scanner input = new Scanner(System.in);
		boolean flag = true;
		String pref = "";
		int count = 0;

		// Reading students.txt file to load all the available students
//		readStudents();
//		readStudentinfo();

		// Reading preferences.txt file and loading the info in Preferences hashmap.
//		readPreferences();

		do {
			System.out.println("Available students " + studHashMap.size());

			// Printing all the available Ids.
			for (String key : studHashMap.keySet()) {
				System.out.println(studHashMap.get(key).getsId());
			}
			System.out.println("Which student you want to update preferences for?\n(Enter x for previous menu)");
			String id = input.nextLine();
			if (id.toLowerCase().contains("x")) {
				break;
			}
			Student aStudent = null;

			// Checking if the id entered by the user is present in studHashMap.
			for (String key : studHashMap.keySet()) {
				if (studHashMap.get(key).getsId().equals(id)) {
					aStudent = studHashMap.get(key);
					break;
				}
			}
			if (aStudent != null) {
				System.out.println("Matching Student:");
				System.out.println(aStudent.getsId() + " " + aStudent.getRanking());

				System.out.println("Enter " + id + "'s preferences: ");

				int i = 0;
				int prCount = 1;
				String prefArray[] = new String[4];

				while (i < 4) {
					System.out.println("Enter " + id + "'s " + prCount + " preference: ");
					prefArray[i] = input.nextLine();
					prCount++;
					i++;
				}

				pref = prefArray[0] + " 4 " + prefArray[1] + " 3 " + prefArray[2] + " 2 " + prefArray[3] + " 1";
				aStudent.setPreferences(pref);

			} else {
				System.out.println("No student with such student ID found");
				break;
			}

			// Loading the data entered by the user into hashmap
			studPreferences.put(aStudent.getsId(),
					new Student(aStudent.getsId(), aStudent.getRanking(), aStudent.getPreferences()));

//			studHashMap.put(aStudent.getsId(), new Student(aStudent.getsId(), aStudent.getRanking(),
//					aStudent.getPersonalityType(), aStudent.getConflict(), aStudent.getPreferences()));
//			writeStudents();

			try {

				// Writing to student.txt file
				String fileName = "preferences.txt";
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

				for (String key : studPreferences.keySet()) {
					out.println(studPreferences.get(key).getsId() + ";" + studPreferences.get(key).getRanking() + ";"
							+ studPreferences.get(key).getPreferences());
				}
				out.close();

				System.out.println("Successfully written to the " + fileName + " file.");
				out.close();
				flag = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (flag);
	}

	// Method for shortlisting projects
	public void shortlistProjects() {

		ArrayList<String> shortlistedProjects = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		String read;
		String pref = "";
		boolean flag = false;

		readProjects();

		do {
			try {
				Scanner in = new Scanner(new File("preferences.txt"));

				// Reading preferences.txt file and taking all the preferences of the students
				// in a String array Preferences.

				while (in.hasNextLine()) {
					String str = in.nextLine();
					String arr[] = str.split(";");
					pref += arr[2] + " ";
				}
				String[] Preferences = new String[pref.split(" ").length];
				Preferences = pref.split(" ");

				// Taking a HashMap to store unique project Ids with their total points of
				// preferences.
				HashMap<String, Integer> projectList = new HashMap<String, Integer>();
				for (int i = 0; i < Preferences.length; i = i + 2) {
					int pts = projectList.containsKey(Preferences[i]) ? projectList.get(Preferences[i]) : 0;
					pts += Integer.parseInt(Preferences[i + 1]);
					projectList.put(Preferences[i], pts);
				}

//                 Sort sort = new Sort();

				// Sorting the HashMap in ascending order of Project Preferences.
				HashMap<String, Integer> sortedMap = Sort.sortByValue(projectList);

				for (String key : sortedMap.keySet()) {
					System.out.println("Project ID: " + key + "      Points: " + sortedMap.get(key));
				}
				System.out.println("************************************");
				Set<String> keys = sortedMap.keySet();
				String[] keysArray = keys.toArray(new String[keys.size()]);

				System.out.print("Projects to be removed: ");
				for (int i = 0; i < 5; i++) {
					System.out.print(keysArray[i] + "\t");

				}
				System.out.println("\n************************************");

				System.out.print("Shortlisted projects are: ");
				// Discarding the 5 least popular projects by running the loop from 5
				// Storing the remaining projects in temp array.

				for (int i = 5; i < keysArray.length; i++) {
					System.out.print(keysArray[i] + "\t");

					shortlistedProjects.add(keysArray[i]);
				}
				System.out.println("\n***********************************");

				for (String project : shortlistedProjects) {
					for (String key : projHashMap.keySet()) {
						key = key.strip();
						if (project.equals(key)) {
//							key = " " + key;
							shortlistProjectMap.put(key, projHashMap.get(key));
						}
					}
				}

				try {

					// Writing the temp array to shortlisted_projects.txt file
					FileWriter fw = new FileWriter("shortlisted_projects.txt");
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					for (String key : shortlistProjectMap.keySet()) {
						pw.println(shortlistProjectMap.get(key));
					}

					System.out.println("Successfully written to the shortlisted_projects.txt file.");
					pw.close();
					flag = true;

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} catch (Exception e) {

				System.out.println(e.getMessage());
			}
		} while (!flag);
	}

	public void formTeams() 
	{

		boolean addTeam = true;
//		readPreferences();
		Scanner in = new Scanner(System.in);

		System.out.println("Shortlisted projects:");
		for (String key : shortlistProjectMap.keySet()) {
			System.out.println(shortlistProjectMap.get(key) + "\n");
		}

		while (addTeam) {

			System.out.print(
					"Please select one of the projects from the above listed projects (x for previous menu) : Pr");

			String selectedProject = "Pr" + in.nextLine().trim();
			if (selectedProject.equals("Prx")) {
				addTeam = false;
				break;
			}
			System.out.print("Required skill: " + shortlistProjectMap.get(selectedProject).getRank() + "\n");

			for (String project : shortlistProjectMap.keySet()) {
				if (shortlistProjectMap.get(project).getProjectID().contains(selectedProject)) {
					for (String student : availableStudents.keySet()) {

						if (availableStudents.get(student).getPreferences()
								.contains(shortlistProjectMap.get(project).getProjectID())) {
							int totalgap = 0;
							System.out.println(availableStudents.get(student).getsId() + " Skills:"
									+ availableStudents.get(student).getRanking() + " Preference:"
									+ availableStudents.get(student).getPreferences());

							Map<String, Integer> studentSkillset = new HashMap<String, Integer>();

							String skillsets = availableStudents.get(student).getRanking();
							String[] skillset = skillsets.split("\\s");

							for (int i = 0; i < skillset.length - 1; i += 2) {
								studentSkillset.putIfAbsent(skillset[i], Integer.parseInt(skillset[i + 1]));

								String ranking = shortlistProjectMap.get(project).getRank();
								String[] words = ranking.split("\\s");

								for (int j = 0; j < words.length - 1; j += 2) {

									// System.out.println("criteria: " + words[j] + " " + words[j + 1]);
									if (studentSkillset.containsKey(words[j]) && skillset[i].equals(words[j])) {

										int value = studentSkillset.get(words[j]);
										int gap = Math.abs(value - Integer.parseInt(words[j + 1]));

										System.out.println(availableStudents.get(student).getsId() + "'s " + words[j]
												+ "=" + value + "(Gap of = " + gap + ")");
										totalgap += gap;
									}
								}
							}

							System.out.println("Total gap " + totalgap);
							double match = (10.0 - totalgap) / 10 * 100;
							System.out.println(
									"Student - Project Skill match prediction : " + String.valueOf(match) + "%");
							System.out.println();
						}
					}
				}
			}

			boolean loop = true;
			String studentids[] = new String[4];
			int count = 0;

			do {
				System.out.print("\nWhich student you want to add to "
						+ shortlistProjectMap.get(selectedProject).getProjectID() + "?: (Enter x to exit) S");
				String response = "S" + in.nextLine();
				if (response.contains("x")) {
					loop = false;
					break;
				}

				try {
					String id = response;
					System.out.println(studHashMap.get(id).getsId() + " has potential conflict with "
							+ studHashMap.get(id).getConflict());

					if (availableStudents.get(id) != null) {
						if (shortlistProjectMap.get(selectedProject).getNoOfMembers() < 4) {
							System.out.print("Adding " + availableStudents.get(id).getsId());
							System.out.println(" to " + shortlistProjectMap.get(selectedProject).getProjectID());
							shortlistProjectMap.get(selectedProject).assignStudent((Student) availableStudents.get(id));
							studentids[count] = availableStudents.get(id).getsId();
							availableStudents.remove(id);
							System.out.println("Project team size = "
									+ shortlistProjectMap.get(selectedProject).getNoOfMembers() + " of 4");
							for (String key : availableStudents.keySet()) {
								System.out.println(availableStudents.get(key).getsId() + " Ranking:"
										+ availableStudents.get(key).getRanking() + " Preference: "
										+ availableStudents.get(key).getPreferences());
							}

							count++;

						} else {
							System.out.println("Project is full");
							loop = false;
						}
					} else {
						System.out.println("No student found");
					}

				} catch (Exception e) {

					// TODO: handle exception
					System.out.println(e.getMessage());
				}

			}

			while (loop == true);

			
			
			
		}
	}

	public void displayFitnessMetric() throws NoLeaderException
	{

		try {

			for (String key : shortlistProjectMap.keySet()) {
				System.out.println(
						shortlistProjectMap.get(key).getProjectID() + " " + shortlistProjectMap.get(key).getStudents());
			}
			System.out.println("***********************************************");
			for (String project : shortlistProjectMap.keySet()) {
				ArrayList<Student> students = new ArrayList<Student>();

				double totalGap = 0;
				double overallSD[] = new double[4];
				int count = 0;

				System.out.println("Project: " + shortlistProjectMap.get(project).getProjectID());

				for (Student s : shortlistProjectMap.get(project).getStudents()) {
					students.add(s);
				}

				String ranking = shortlistProjectMap.get(project).getRank();
				String[] words = ranking.split("\\s");

				for (int j = 0; j < words.length - 1; j += 2) {
					int sum = 0;
					double skillCompetence[] = new double[4];

					int k = 0;

					for (Student s : students) {

						Map<String, Integer> studentSkillset = new HashMap<String, Integer>();
						String skillsets = s.getRanking();
						String[] skillset = skillsets.split("\\s");

						for (int i = 0; i < skillset.length - 1; i += 2) {
							studentSkillset.putIfAbsent(skillset[i], Integer.parseInt(skillset[i + 1]));
						}
						// System.out.println("criteria: " + words[j] + " " + words[j + 1]);
						if (studentSkillset.containsKey(words[j])) {

							int value = studentSkillset.get(words[j]);
							skillCompetence[k] = value;
							k++;
							sum += value;
						}
					}

					int projSkill = Integer.parseInt(words[j + 1]);
					double avg = (double) sum / 4.0;
					
					System.out.println("\nAverage skill competency for "+ words[j] + " = " + avg + " (Required skill is " + projSkill + ")");
					overallSD[count] = sum;
					count++;
//					System.out.println("Avg: " + avg);
					double gap = Math.abs(projSkill - avg);
//					System.out.println(gap);
					totalGap += gap;

					System.out.println(
							"SD for " + words[j] + " for project " + shortlistProjectMap.get(project).getProjectID()
									+ " = " + StandardDeviation.calculateSD(skillCompetence));

				}

				System.out
						.println("\nSD for overall skills for project " + shortlistProjectMap.get(project).getProjectID()
								+ " = " + StandardDeviation.calculateSD(overallSD));

				System.out.println("Project: " + shortlistProjectMap.get(project).getProjectID()
						+ " has total skill set gap of " + totalGap);
				System.out.println("***********************************************");

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		
	}

	public void serializeProject() {

		try {
			FileOutputStream fileOut = new FileOutputStream("projects.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(shortlistProjectMap);
			out.writeObject(studHashMap);
//			out.writeObject(availableStudents);
			out.close();
			fileOut.close();
			// System.out.println("Serialized data is saved in students.ser");
		} catch (IOException i) {
			// TODO: handle exception
			i.printStackTrace();
		}

	}

	public void deserializeProject() {

		try {
			FileInputStream fileIn = new FileInputStream("projects.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			shortlistProjectMap = (HashMap<String, Project>) in.readObject();
			studHashMap = (HashMap<String, Student>) in.readObject();
//			availableStudents = (HashMap<String, Student>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			// TODO: handle exception

		} catch (ClassNotFoundException e2) {
			// TODO: handle exception
		}
		System.out.println("Deserialized student.......");
//		System.out.println("ID: " + e.getsId());
//		System.out.println("Skill Ranking: " + e.getRanking());

	}

	public void readAvailStudent() {
		for (String student : studHashMap.keySet()) {
			// System.out.println(studHashMap.get(student).getsId());
			availableStudents.put(studHashMap.get(student).getsId(), studHashMap.get(student));
		}

	}

	public void readCompanies() {
		File file = new File("companies.txt");
		Scanner in = null;
		try {

			in = new Scanner(file);
			while (in.hasNextLine()) {

				String str = in.nextLine();
				String arr[] = str.split(";");
				String cid = arr[0];
				String name = arr[1];
				int ABN = Integer.parseInt(arr[2]);
				String url = arr[3];
				String address = arr[4];
				compHashMap.put(cid, new Company(cid, name, ABN, url, address));
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void readOwners() {
		File file = new File("owners.txt");
		Scanner in = null;
		try {

			in = new Scanner(file);
			while (in.hasNextLine()) {
				String str = in.nextLine();
				String arr[] = str.split(";");
				String firstName = arr[0];
				String surname = arr[1];
				String ownerID = arr[2];
				String role = arr[3];
				String email = arr[4];

				// Loading the data in the ownerHashMap
				ownerHashMap.put(ownerID, new ProjectOwner(firstName, surname, ownerID, role, email));

			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}



//	private void writeStudents() {
//	String fileName = "students.txt";
//	try {
//		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
//
//		for (String key : studHashMap.keySet()) {
//			out.println(studHashMap.get(key));
//		}
//		out.close();
//		System.out.println("Successfully written to the " + fileName + " file.");
//	} catch (Exception e) {
//		// TODO: handle exception
//		System.out.println("Error occurred");
//		e.printStackTrace();
//	}
//
//}

//	public void writeteams() {
//		String fileName = "teams.txt";
//		try {
//			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
//
//			for (String key : projectTeams.keySet()) {
//				out.println(key + ";" + projectTeams.get(key));
//			}
//			out.close();
//			System.out.println("Successfully written to the " + fileName + " file.");
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Error occurred");
//			e.printStackTrace();
//		}
//	}

//	public void readteams() {
//		File file = new File("teams.txt");
//		Scanner in = null;
//		try {
//
//			in = new Scanner(file);
//			while (in.hasNextLine()) {
//				String str = in.nextLine();
//				String arr[] = str.split(";");
//				String projectid = arr[0];
//				String student = arr[1];
//
//				// Loading the data in the ownerHashMap
//				projectTeams.put(projectid, student);
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Error occurred");
//			e.printStackTrace();
//		}
//	}

	public void readProjects() {
		File file = new File("projects.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String str = in.nextLine();
				String arr[] = str.split(";");
				String title = arr[0];
				String projectID = arr[1];
				String descr = arr[2];
				String projectOwner = arr[3];
				String rank = arr[4];

				// Loading data in the hashmap
				projHashMap.put(projectID, new Project(title, projectID, descr, projectOwner, rank));

			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean checkConflict(ArrayList<String> conflicts, String student) throws StudentConflictException{
		if(conflicts.contains(student)) {
			System.out.println(student + " is having conflict with his team member");
			throw new StudentConflictException();

		}
		else {
			return true;
		}
	
	}
	
	public boolean checkRepeatedMember(HashMap<String, Project> projHashMap, String stud ) throws RepeatedMemberException {
		if(projHashMap.get(stud).getProjectID() == null) {
			return true;
		}
		else {
			throw new RepeatedMemberException();
		}
	}
	
	
//	public void readStudents() {
//
//		File file = new File("students.txt");
//		Scanner in = null;
//		try {
//			in = new Scanner(file);
//			while (in.hasNextLine()) {
//				String str = in.nextLine();
//				String arr[] = str.split(";");
//				String id = arr[0];
//				String ranking = arr[1];
////				char personalityType = arr[2].charAt(0);
////				String conflict = arr[3];
////				String preferences = arr[4];
//				System.out.println(id + " " + ranking);
//				studHashMap.put(id, new Student(id, ranking));
//				System.out.println("Data loaded into object\n");
//			}
//		} catch (IOException exp) {
//			exp.printStackTrace();
//		}
//
//		in.close();
//	}
//
//	public void readPreferences() {
//		File file = new File("preferences.txt");
//		Scanner in = null;
//		try {
//
//			in = new Scanner(file);
//			while (in.hasNextLine()) {
//				String str = in.nextLine();
//				String arr[] = str.split(";");
//				String id = arr[0];
//				String ranking = arr[1];
//				String preferences = arr[2];
//
//				for (String key : studHashMap.keySet()) {
//					if (key.equals(id)) {
//						studHashMap.get(key).setPreferences(preferences);
//					}
//				}
//
//				studPreferences.put(id, new Student(id, ranking, preferences));
//			}
//			in.close();
//		}
//
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	public void readStudentinfo() {
//		File file = new File("studentinfo.txt");
//		Scanner in = null;
//		try {
//
//			in = new Scanner(file);
//			while (in.hasNextLine()) {
//				String str = in.nextLine();
//				String arr[] = str.split(";");
//				String id = arr[0];
//				String ranking = arr[1];
//				char personalityType = arr[2].charAt(0);
//				String conflict = arr[3];
//
//				for (String key : studHashMap.keySet()) {
//					if (key.equals(id)) {
//						studHashMap.get(key).setConflict(conflict);
//						studHashMap.get(key).setPersonalityType(personalityType);
//					}
//				}
//
//				studInfoMap.put(id, new Student(id, ranking, personalityType, conflict));
//			}
//
//			in.close();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	public void readShortlistedProjects() {
//		File file = new File("shortlisted_projects.txt");
//		Scanner in = null;
//
//		try {
//			in = new Scanner(file);
//			while (in.hasNextLine()) {
//				String str = in.nextLine();
//				String arr[] = str.split(";");
//				String title = arr[0];
//				String projectID = arr[1];
//				String descr = arr[2];
//				String projectOwner = arr[3];
//				String rank = arr[4];
//
//				// Loading data in the hashmap
//				shortlistProjectMap.put(projectID, new Project(title, projectID, descr, projectOwner, rank));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//		}
//	}
}
