package Project2Comp24;

public class Month implements Comparable<Month>, TimeUnit {
	private String month;
	private AvlTree<Day> dayList;
	private Month next;

	public Month(String month) {
		super();
		this.month = month;
		this.dayList = new AvlTree<>();
		this.next = null;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public AvlTree<Day> getDayAvlTree() {
		return dayList;
	}

	public void setDayAvlTree(AvlTree<Day> dayAvlTree) {
		this.dayList = dayAvlTree;
	}

	public Month getNext() {
		return next;
	}

	public void setNext(Month next) {
		this.next = next;
	}

	public void addDay(Day day) {
		this.dayList.insert(day);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("" + month);
//		if (dayList != null) {
//			dayList.inorder();
//			result.append(dayList);
//		}
		return result.toString();
	}

	@Override
	public int compareTo(Month o) {
		return this.month.compareTo(o.month);
	}
	
	public int getMonthAsInt() {
	    switch (month.toLowerCase()) {
	        case "january":
	            return 1;
	        case "february":
	            return 2;
	        case "march":
	            return 3;
	        case "april":
	            return 4;
	        case "may":
	            return 5;
	        case "june":
	            return 6;
	        case "july":
	            return 7;
	        case "august":
	            return 8;
	        case "september":
	            return 9;
	        case "october":
	            return 10;
	        case "november":
	            return 11;
	        case "december":
	            return 12;
	        default:
	            throw new IllegalArgumentException("Invalid month: " + month);
	    }
	}


	@Override
	public int getValue() {
		return getMonthAsInt();
	}
	public String getValue2() {
		return month;
	}

	@Override
	public int compareTo(TimeUnit o) {
		return compareTo((Month) o);
	}
}
