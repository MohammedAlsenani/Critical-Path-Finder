
public class Deliverables {

	private String name;
	private Activity head;
	private Activity current;
	private Deliverables next;

	public Deliverables(String name) {
		head = current = null;
		next = null;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Deliverables getNext() {
		return next;
	}

	public void setNext(Deliverables next) {
		this.next = next;
	}

	public boolean empty() {
		return head == null;
	}

	public Activity retrieveActivity() {
		return current;
	}

	public void findFirst() {
		if (empty())
			return;
		current = head;
	}

	public void findnext() {
		if (empty() || current.getNext() == null)
			return;
		current = current.getNext();
	}

	public void insert(int dur, String[] res, String[] pre, String name) {
		if (empty()) {
			head = new Activity(dur, res, pre, name);
			current = head;
		} else {
			current.setNext(new Activity(dur, res, pre, name));
			findnext();
		}
	}
}
