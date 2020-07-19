
public class Phases {

	private String name;
	private Deliverables head;
	private Deliverables current;
	private Phases next;

	public Phases(String name) {
		head = current = null;
		next = null;
		this.name = name;
	}

	public boolean empty() {
		return head == null;
	}

	public Deliverables retrieveDeliverable() {
		return current;
	}

	public void findnext() {
		if (empty() || current.getNext() == null)
			return;
		current = current.getNext();
	}

	public void find(String name) {
		if (current.getName() == name) {
			return;
		} else if (empty()) {
			System.out.println("This phase hasn't deliveriables");
		} else {
			Deliverables tmp = head;
			while (tmp.getNext() != null) {
				if (tmp.getName() == name) {
					current = tmp;
					return;
				}
				tmp = tmp.getNext();
			}

			if (tmp.getName() == name) {
				current = tmp;
			} else {
				System.out.println("There is no deliverable with this name");
			}
		}
	}

	public void insert(String name) {
		if (empty()) {
			head = new Deliverables(name);
			current = head;
		} else {
			current.setNext(new Deliverables(name));
			findnext();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Phases getNext() {
		return next;
	}

	public void setNext(Phases next) {
		this.next = next;
	}

}
