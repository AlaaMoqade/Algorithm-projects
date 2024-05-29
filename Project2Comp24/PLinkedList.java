//package Project2Comp24;
//
//import project1.singleNode;
//
//public class PLinkedList {
//	Node<TimeUnit> first;
//	Node<TimeUnit> last;
//	int size;
//
//	public PLinkedList() {
//		first = last = null;
//		size = 0;
//	}
//
//	public TimeUnit getFirst() {
//		if (size == 0) {
//			return null;
//		}
//		return first.value;
//	}
//
//	public void addLast(TimeUnit object) {
//		Node<TimeUnit> node = new Node<>(object);
//		if (size == 0) {
//			first = last = node;
//		} else {
//			last.next = node;
//			last = last.next;
//		}
//		size++;
//	}
//
//	public void addFirst(TimeUnit object) {
//		Node<TimeUnit> node = new Node<>(object);
//		if (size == 0) {
//			first = last = node;
//		} else {
//			node.next = first;
//			first = node;
//		}
//		size++;
//
//	}
//
//	private boolean removeFirst() {
//		if (first == null) {
//			return false;
//		}
//		// Check if we have one element only
//		if (first == last) {
//			first = last = null;
//			return true;
//		}
//		first = first.next;
//		size--;
//		return true;
//	}
//
//	public boolean removeLast() {
//		if (first == null) {
//			return false;
//		}
//		// Check if we have one element only
//		if (first == last) {
//			first = last = null;
//			return true;
//		}
//		Node<TimeUnit> current = first;
//		for (int i = 0; i < size - 2; i++) {
//			current = current.next;
//
//		}
//		current.next = null;
//		last = current;
//		size--;
//		return true;
//	}
//
//	public boolean remove(int index) {
//		if (index <= 0) {
//			return removeFirst();
//		} else if (index >= size) {
//			return removeLast();
//		} else {
//			Node<TimeUnit> current = first;
//			for (int i = 0; i < index - 1; i++) {
//				current = current.next;
//			}
//
//			Node<TimeUnit> temp = current.next;
//			current.next = temp.next;
//			size--;
//			return false;
//
//		}
//	}
//
//	public TimeUnit find(int val) {
//		Node<TimeUnit> current = first;
//		while (current != null && current.getValue().getValue() != val) {
//			current = current.next;
//		}
//		if (current == null) {
//			return null;
//		}
//		return current.getValue();
//	}
//	
//
//	public void addSorted(TimeUnit value) {
//		Node<TimeUnit> newNode = new Node<>(value);
//		if (first == null) {
//			first = newNode;
//			return;
//		}
//
//		Node<TimeUnit> curr = first;
//		Node<TimeUnit> prev = null;
//		while (curr != null && value.getValue() > curr.getValue().getValue()) {
//			prev = curr;
//			curr = curr.next;
//		}
//		if (prev == null) {
//			first = newNode;
//		} else {
//			prev.next = newNode;
//		}
//		newNode.next = curr;
//
//	}
//
//	public TimeUnit get(int index) {
//		Node<TimeUnit> current = first;
//		int currentIndex = 0;
//
//		while (current != null && currentIndex < index) {
//			current = current.next;
//			currentIndex++;
//		}
//		if (current == null) {
//
//			return null;
//		}
//
//		return current.getValue();
//	}
//
//	public String printAllData() {
//		String result = "";
//		Node<TimeUnit> current = first;
//		while (current != null) {
//			if (current.value instanceof Year) {
//				Year year = (Year) current.value;
//				result += year.getValue() + "\n\n";
//				System.out.println(result);
//				System.out.println();
//
//				Node<TimeUnit> monthNode = year.getMonthList().first;
//				while (monthNode != null) {
//					Month month = (Month) monthNode.value;
//					result += "  " + month.getValue() + "\n";
//					System.out.println(result);
//
//					Node<TimeUnit> dayNode = month.getDayList().first;
//					while (dayNode != null) {
//						Day day = (Day) dayNode.value;
//						result += "    " + day.getInformation() + "\n";
//						System.out.println(result);
//						dayNode = dayNode.next;
//					}
//
//					result += "\n";
//					System.out.println(result);
//					monthNode = monthNode.next;
//				}
//			}
//
//			result += "\n\n";
//			System.out.println(result);
//			current = current.next;
//		}
//
//		return result;
//	}
//
//	public int getSize() {
//		Node<TimeUnit> current = first;
//		int count = 0;
//		while (current != null) {
//			size++;
//			current = current.next;
//		}
//		return size;
//	}
//
//}