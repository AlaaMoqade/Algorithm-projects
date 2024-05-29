package Project2Comp24;

public class Day implements Comparable<Day>,TimeUnit {
 private int day;
 private ElectricityRecord information;
 
public Day(int day, ElectricityRecord information) {
	super();
	this.day = day;
	this.information = information;
}

public int getDay() {
	return day;
}

public void setDay(int day) {
	this.day = day;
}

public ElectricityRecord getInformation() {
	return information;
}

public void setInformation(ElectricityRecord information) {
	this.information = information;
}

@Override
public String toString() {
	return "" + day ;
}

@Override
public int compareTo(Day o) {
	if(this.day > o.day) return 1;
	if(this.day < o.day) return -1;
	return 0;
	}


@Override
public int getValue() {
	return day;
}

@Override
public int compareTo(TimeUnit o) {
	return compareTo((Month)o);
}
 
}
