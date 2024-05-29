package Project2Comp24;

import java.time.LocalDate;

public class ElectricityRecord {
	private LocalDate date;
	private float israel_Lines;
	private float gazaPowerPlant;
	private float egyptian_Lines;
	private float totalDailySupply;
	private float overallDemand;
	private float powerCutsHoursDay;
	private float temp;

	public ElectricityRecord(LocalDate date,float israel_Lines, float gazaPowerPlant, float egyptian_Lines, float totalDailySupply,
			float overallDemand,float powerCutsHoursDay, float temp) {
		super();
        this.date = date;
		this.israel_Lines = israel_Lines;
		this.gazaPowerPlant = gazaPowerPlant;
		this.egyptian_Lines = egyptian_Lines;
		this.totalDailySupply = totalDailySupply;
		this.overallDemand = overallDemand;
		this.powerCutsHoursDay = powerCutsHoursDay;
		this.temp = temp;
	}

	public float getIsrael_Lines() {
		return israel_Lines;
	}

	public void setIsrael_Lines(float israel_Lines) {
		this.israel_Lines = israel_Lines;
	}

	public float getGazaPowerPlant() {
		return gazaPowerPlant;
	}

	public void setGazaPowerPlant(float gazaPowerPlant) {
		this.gazaPowerPlant = gazaPowerPlant;
	}

	public float getEgyptian_Lines() {
		return egyptian_Lines;
	}

	public void setEgyptian_Lines(float egyptian_Lines) {
		this.egyptian_Lines = egyptian_Lines;
	}

	public float getTotalDailySupply() {
		return totalDailySupply;
	}

	public void setTotalDailySupply(float totalDailySupply) {
		this.totalDailySupply = totalDailySupply;
	}

	public float getOverallDemand() {
		return overallDemand;
	}

	public void setOverallDemand(float overallDemand) {
		this.overallDemand = overallDemand;
	}

	public float getPowerCutsHoursDay() {
		return powerCutsHoursDay;
	}

	public void setPowerCutsHoursDay(float powerCutsHoursDay) {
		this.powerCutsHoursDay = powerCutsHoursDay;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return  date + ", "  + israel_Lines +" ," 
				+ gazaPowerPlant + ", " + egyptian_Lines + " ," + totalDailySupply
				+ " ," + overallDemand + " ," + powerCutsHoursDay + " ," + temp;
	}
	

	

}
