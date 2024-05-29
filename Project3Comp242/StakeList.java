package Project3Comp242;



public class StakeList<T extends Comparable<T>> {
	private CursorArray<T> list = new CursorArray<>();
	private int l ;
	
	public StakeList() {
		l = list.createList();
	}

	public boolean isEmpty() {
		return list.isEmpty(l);
	}

	public int size() {
		return list.getSize();
	}

	public void push(T element) {
		list.insertFirst(element, l);
	}

	public T pop() {
		return list.deleteFirst(l);
	}

	public T peek() {
		
		return list.getFirst(l);
	}
	
	public void clear() {
		list = new CursorArray<>(l);
	}
	
	public void print() {
		list.print(l);
	}
	
	public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return list.getFirst(index);
    }
	
}

