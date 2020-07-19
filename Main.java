import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

//		Initiate a scanner
		Scanner s = new Scanner(System.in);

//		Some inputs
		System.out.println("Enter project name: ");
		String name = s.next();

		System.out.println("Enter project start date: ");
		int date = s.nextInt();

		System.out.println("Enter max number of activities: ");
		int act = s.nextInt();

//		Initiate a project
		Project project = new Project(name, date, act);

		String info = "Project name is: " + name + "\nAnd start day is: " + date;

//		Some inputs
		System.out.println("Enter number of phases: ");
		int cPhases = s.nextInt();
		info += "\nThe project have " + cPhases + " phase/s which is: ";
//		Variables to be used on for loop
		int cDeliverables, cAcvtivities, cRes, cPre, dur = 0;
		String[] pre, res;
		String acName, delName, phaName, resName, preName;

//		Looping to initiate all the component of the project
		for (int i = 0; i < cPhases; i++) {

			System.out.println("Enter the name of phase " + (i + 1));
			phaName = s.next();
			info += "\n" + phaName + " phase";

//			Create a new phase
			project.insert(phaName);

			System.out.println("Enter the number of deliverables of the " + phaName + " phase");
			cDeliverables = s.nextInt();
			info += "\n\twhich has " + cDeliverables + " deliverable/s which is: ";

			for (int k = 0; k < cDeliverables; k++) {

				System.out.println("Enter the name of deliverable " + (k + 1));
				delName = s.next();
				info += "\n\t" + delName + " deliverable";

//				Create a new deliverable
				project.retrievePhase().insert(delName);

				System.out.println("Enter the number of activities of the " + delName + " deliverable");
				cAcvtivities = s.nextInt();
				info += "\n\t\twhich has " + cAcvtivities + " activity/s which is: ";

				for (int j = 0; j < cAcvtivities; j++) {

					System.out.println("Enter the name of activity " + (j + 1));
					acName = s.next();
					info += "\n\t\t" + acName + " activty";

					System.out.println("Enter the duration of the " + acName + " activity");
					dur = s.nextInt();

					System.out.println("Enter number of resources of the " + acName + " activity");
					cRes = s.nextInt();

					res = new String[cRes];

					for (int t = 0; t < cRes; t++) {

						System.out.println("Enter the name of resource " + (t + 1) + " of the " + acName + " activity");
						resName = s.next();

						res[t] = resName;
					}

					System.out.println("Enter number of precedence of the " + acName + " activity");
					cPre = s.nextInt();

					pre = new String[cPre];

					for (int l = 0; l < cPre; l++) {

						System.out.println(
								"Enter the name of precedence " + (l + 1) + " of the " + acName + " activity ");
						preName = s.next();

						pre[l] = preName;
					}

//						Create a new activity
					project.retrievePhase().retrieveDeliverable().insert(dur, res, pre, acName);
					project.setAllActivities(new Activity(dur, res, pre, acName));
				}
			}
		}
		project.setEarlyDates();
		project.setLateDates();
		project.printInfo(info);
	}

}
