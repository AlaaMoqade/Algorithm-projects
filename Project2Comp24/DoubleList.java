//package Project2Comp24;
//
//public class DoubleList {
//	private Node<TimeUnit> first;
//	private Node<TimeUnit> last;
//	int size;
//
//	// Inner Node class
//	private static class Node<TimeUnit> {
//		TimeUnit date;
//		Node<TimeUnit> next;
//		Node<TimeUnit> prev;
//		public Project2Comp24.TimeUnit data;
//
//		public Node(TimeUnit data) {
//			this.date = date;
//			this.next = null;
//			this.prev = null;
//		}
//	}
//
//	private boolean removeFirst() {
//		if (first == null) {
//			return false;
//		}
//
//		// Check if we have one element only
//		if (first == last) {
//			first = last = null;
//			return true;
//		}
//
//		first = first.next;
//		first.prev = null; // Update previous reference of the new first node
//
//		size--;
//		return true;
//	}
//
//	public boolean removeLast() {
//		if (first == null) {
//			return false;
//		}
//
//		// Check if we have one element only
//		if (first == last) {
//			first = last = null;
//			return true;
//		}
//
//		last = last.prev;
//		last.next = null; // Update next reference of the new last node
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
//			if (temp.next != null) {
//				temp.next.prev = current; // Update the previous reference of the next node
//			}
//			size--;
//			return false;
//		}
//	}
//
//	public void addSorted(TimeUnit value) {
//		Node<TimeUnit> newNode = new Node<>(value);
//		if (first == null) {
//			first = last = newNode;
//			return;
//		}
//
//		Node<TimeUnit> curr = first;
//		Node<TimeUnit> prev = null;
//		while (curr != null && value.getValue() > curr.data.getValue()) {
//			prev = curr;
//			curr = curr.next;
//		}
//		if (prev == null) {
//			newNode.next = first;
//			first.prev = newNode;
//			first = newNode;
//		} else {
//			prev.next = newNode;
//			newNode.prev = prev;
//		}
//		newNode.next = curr;
//		if (curr != null) {
//			curr.prev = newNode;
//		}
//	}
//
//}
