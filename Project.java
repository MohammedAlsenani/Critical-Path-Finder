import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Project {

	private String name;
	private int startDay;
	private Phases head;
	private Phases current;
	private Activity[] allActivities;
	private int counter;

	public void setAllActivities(Activity activity) {
		allActivities[counter++] = activity;
	}

	public Project(String name, int date, int act) {
		head = current = null;
		this.name = name;
		startDay = date;
		allActivities = new Activity[act];
		counter = 0;
	}

	public boolean empty() {
		return head == null;
	}

	public Phases retrievePhase() {
		return current;
	}

	public void findnext() {
		if (empty() || current.getNext() == null)
			return;
		current = current.getNext();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartDay() {
		return startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public void insert(String name) {
		if (empty()) {
			head = new Phases(name);
			current = head;
		} else {
			current.setNext(new Phases(name));
			findnext();
		}
	}

	public void setEarlyDates() {

		for (int i = 0; i < counter; i++) {
			if (allActivities[i].getPrecedence().equals(null)) {
				allActivities[i].setEarlyStart(startDay);
				allActivities[i].setEarlyFinish((startDay + allActivities[i].getDuration()));
			} else {
				int length = allActivities[i].getPrecedence().length;
				int[] tmp = new int[length];
				String[] tmp2 = allActivities[i].getPrecedence();
				for (int j = 0; j < length; j++) {
					tmp[j] = getEarlyFinishOfPre(tmp2[j]);
				}
				int tmp3 = 0;
				for (int k = 0; k < length; k++) {
					if (tmp[k] > tmp3) {
						tmp3 = tmp[k];
					}
				}
				allActivities[i].setEarlyStart(tmp3);
				allActivities[i].setEarlyFinish(tmp3 + allActivities[i].getDuration());
			}
		}
	}

	private int getEarlyFinishOfPre(String name) {
		int EF = 0;
		for (int i = 0; i < counter; i++) {
			if (allActivities[i].getName().equals(name)) {
				EF = allActivities[i].getEarlyFinish();
				break;
			}
		}
		return EF;
	}

	public void setLateDates() {
		allActivities[counter - 1].setLateFinish(allActivities[counter - 1].getEarlyFinish());
		allActivities[counter - 1].setLateStart(allActivities[counter - 1].getEarlyStart());

		for (int i = counter - 1; i >= 0; i--) {
			if (!allActivities[i].getPrecedence().equals(null)) {
				int length = allActivities[i].getPrecedence().length;
				String[] tmp = allActivities[i].getPrecedence();

				for (int j = 0; j < length; j++) {
					for (int k = counter - 1; k >= 0; k--) {
						if (allActivities[k].getName().equals(tmp[j])) {
							if (allActivities[k].getLateFinish() == 0
									|| allActivities[i].getLateStart() < allActivities[k].getLateFinish()) {
								allActivities[k].setLateFinish(allActivities[i].getLateStart());
								allActivities[k]
										.setLateStart(allActivities[i].getLateStart() - allActivities[k].getDuration());
							}
						}
					}
				}
			}
		}
	}

	public void printInfo(String info) {

		for (int i = 0; i < counter; i++) {
			info += "\n------------------------------------------\n";
			info += allActivities[i].getName() + " activity has ";
			info += allActivities[i].getDuration() + " day/s duration\nES is: ";
			info += allActivities[i].getEarlyStart() + "\nEF is: ";
			info += allActivities[i].getEarlyFinish() + "\nLS is: ";
			info += allActivities[i].getLateStart() + "\nLF is: ";
			info += allActivities[i].getLateFinish();

			int length1 = allActivities[i].getPrecedence().length;
			int length2 = allActivities[i].getResources().length;
			String[] tmp1 = allActivities[i].getPrecedence();
			String[] tmp2 = allActivities[i].getResources();
			if (length1 != 0) {
				info += "\nThe precedence of this activity is/are:";
				for (int j = 0; j < length1; j++) {
					info += "\n" + tmp1[j];
				}
			} else {
				info += "\nThis activity does not have precedence";
			}
			if (length2 != 0) {
				info += "\nThe resources of this activity is/are:";
				for (int k = 0; k < length2; k++) {
					info += "\n" + tmp2[k];
				}
			} else {
				info += "\nThis activity hasn't resources written";
			}
		}
		findCriticalPath(info);
	}

	public void findCriticalPath(String info) {
		StringBuilder input1 = new StringBuilder();
		String tmp = "";
		for (int i = counter - 1; i >= 0; i--) {
			if (isCritical(allActivities[i])) {
				tmp += (" " + allActivities[i].getName());
			}
		}
		info += "\n------------------------------------------";
		info += "\nThe critical path of this project is: ";
		input1.append(tmp);
		info += input1.reverse();
		writeToFile(info);
	}

	private boolean isCritical(Activity act) {
		return act.getEarlyFinish() == act.getLateFinish();
	}

	private void writeToFile(String info) {
		try {
			File myObj = new File("ProjectInfo.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter("ProjectInfo.txt");
			myWriter.write(info);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
