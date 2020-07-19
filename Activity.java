
public class Activity {

	private int duration;
	private String[] resources;
	private String[] precedence;
	private String name;
	private Activity next;
	private int earlyStart;
	private int earlyFinish;
	private int lateStart;
	private int lateFinish;

	public Activity(int dur, String[] res, String[] pre, String name) {
		duration = dur;
		this.name = name;
		resources = res;
		precedence = pre;
		next = null;
		earlyStart = earlyFinish = lateStart = lateFinish = 0;
	}

	public int getEarlyStart() {
		return earlyStart;
	}

	public void setEarlyStart(int earlyStart) {
		this.earlyStart = earlyStart;
	}

	public int getEarlyFinish() {
		return earlyFinish;
	}

	public void setEarlyFinish(int earlyFinish) {
		this.earlyFinish = earlyFinish;
	}

	public int getLateStart() {
		return lateStart;
	}

	public void setLateStart(int lateStart) {
		this.lateStart = lateStart;
	}

	public int getLateFinish() {
		return lateFinish;
	}

	public void setLateFinish(int lateFinish) {
		this.lateFinish = lateFinish;
	}

	public Activity getNext() {
		return next;
	}

	public void setNext(Activity next) {
		this.next = next;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String[] getResources() {
		return resources;
	}

	public void setResources(String[] resources) {
		this.resources = resources;
	}

	public String[] getPrecedence() {
		return precedence;
	}

	public void setPrecedence(String[] precedence) {
		this.precedence = precedence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
