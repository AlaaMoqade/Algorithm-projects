package Project2Comp24;

public class Year implements Comparable<Year>, TimeUnit {
	private int Year;
	private AvlTree<Month> monthAvlTree;
	private Year next;

	public Year(int year) {
		super();
		this.Year = year;
		this.monthAvlTree = new AvlTree<>();
		this.next = null;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public AvlTree<Month> getMonthAvlTree() {
		return monthAvlTree;
	}

	public void setMonthAvlTree(AvlTree<Month> monthAvlTree) {
		this.monthAvlTree = monthAvlTree;
	}

	public Year getNext() {
		return next;
	}

	public void setNext(Year next) {
		this.next = next;
	}

	public void addMonth(Month month) {
		this.monthAvlTree.insert(month);
	}

	@Override
	public String toString() {
		return ""+ Year ;
	}

	@Override
	public int compareTo(Year o) {
		if (this.Year > o.Year)
			return 1;
		if (this.Year < o.Year)
			return -1;
		return 0;
	}

	@Override
	public int getValue() {
		return Year;
	}

	@Override
	public int compareTo(TimeUnit o) {
		return compareTo((Year) o);
	}
}
