package Project2Comp24;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

import lap2Data.linkedList;
import project1.Martyr;
import project1.SingleLinkedList;

public class driver {

	static final AvlTree<Year> yearTree = new AvlTree<>();
	static int c = 0;

	public static void readAndProcessFile(File selectedFile) {
		try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				String[] values = line.split(",");

				try {
					DateTimeFormatter processDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(values[0], processDate);
					float israeliLinesMWs = Float.parseFloat(values[1].trim());
					float gazaPowerPlantMWs = Float.parseFloat(values[2].trim());
					float egyptianLinesMWs = Float.parseFloat(values[3].trim());
					float totalDailySupplyAvailableInMWs = Float.parseFloat(values[4].trim());
					float overallDemandInMWs = Float.parseFloat(values[5].trim());
					float powerCutsHoursDay400mg = Float.parseFloat(values[6].trim());
					float temp = Float.parseFloat(values[7].trim());

					ElectricityRecord electricRecord = new ElectricityRecord(date, israeliLinesMWs, gazaPowerPlantMWs,
							egyptianLinesMWs, totalDailySupplyAvailableInMWs, overallDemandInMWs,
							powerCutsHoursDay400mg, temp);

					addElectricRecord(electricRecord);
					c = c + 1;

				} catch (Exception e) {
					continue;
				}

			}

			System.out.println(c);

		}

		catch (

		Exception e1) {
			e1.printStackTrace();
		}

	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<add electric

	public static void addElectricRecord(ElectricityRecord electricData) {
		int yearValue = electricData.getDate().getYear();
		String monthValue = electricData.getDate().getMonth().name();
		int dayValue = electricData.getDate().getDayOfMonth();

		Year yearNode = yearTree.find(new Year(yearValue));
		if (yearNode == null) {
			yearNode = new Year(yearValue);
			yearTree.insert(yearNode);
		}

		Month monthNode = yearNode.getMonthAvlTree().find(new Month(monthValue));

		if (monthNode == null) {
			monthNode = new Month(monthValue);
			yearNode.getMonthAvlTree().insert(monthNode);
		}

		monthNode.getDayAvlTree().insert(new Day(dayValue, electricData));
	}
	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< search electric

	public static ElectricityRecord search(LocalDate date) {
		if (yearTree == null) {
			throw new NullPointerException("Year tree is null!");
		}

		int yearValue = date.getYear();
		String monthValue = date.getMonth().name();
		int dayValue = date.getDayOfMonth();

		Year year = (Year) yearTree.find(yearValue);

		if (year != null) {
			Month month = (Month) year.getMonthAvlTree().find(new Month(monthValue));

			if (month != null) {
				Day day = (Day) month.getDayAvlTree().find(dayValue);

				if (day != null) {
					return day.getInformation();
				}
			}
		}

		return null;
	}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> delete electric 

	public static boolean deleteElectricRecord(LocalDate date) {
		int yearValue = date.getYear();
		String monthValue = date.getMonth().name();
		int dayValue = date.getDayOfMonth();

		Year year = (Year) yearTree.find(yearValue);

		if (year != null) {
			Month month = (Month) year.getMonthAvlTree().find(new Month(monthValue));

			if (month != null) {

				Day day = (Day) month.getDayAvlTree().find(dayValue);
				if (day != null) {

					day.setInformation(null);
					return true;
				} else {
					System.out.println("Day not found!");
				}
			} else {
				System.out.println("Month not found!");
			}
		} else {
			System.out.println("Year not found!");
		}

		return false;
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> update method

	public static ElectricityRecord updateElectric(LocalDate date, ElectricityRecord info) {

		int yearValue = date.getYear();
		String monthValue = date.getMonth().name();
		int dayValue = date.getDayOfMonth();

		Year year = (Year) yearTree.find(yearValue);

		if (year != null) {
			Month m = new Month(monthValue);
			Month month = (Month) year.getMonthAvlTree().find(m);
			if (month != null) {
				Day day = (Day) month.getDayAvlTree().find(dayValue);
				if (day != null) {
					day.setInformation(info);
				}
			}
		}

		return null;
	}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  statictic for year>>>>>>>>>>>>>>.
	public static ElectricStats statisticsY(int year, String electricRecord) {

		int count = 0;
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		float value = 0.0f;
		float average = 0.0f;

		ElectricStats e = new ElectricStats(value, average, max, min);
		Year currentYear = (Year) yearTree.find(new Year(year));

		if (currentYear != null && currentYear.getValue() == year) {
		//	System.out.println("Year: " + currentYear.getValue());

			for (int j = 0; j < currentYear.getMonthAvlTree().getSize(); j++) {
				Month month = (Month) currentYear.getMonthAvlTree().get(j);

				if (month != null) {
				//	System.out.println("Month: " + month.getValue());

					for (int k = 0; k < month.getDayAvlTree().getSize(); k++) {
						Day day = (Day) month.getDayAvlTree().get(k);

						if (day != null) {
							//System.out.println("Day: " + day.getValue());

							ElectricityRecord dayRecord = day.getInformation();

							if ("Israeli_Lines_MWs".equals(electricRecord)) {
								value = value + dayRecord.getIsrael_Lines();
								//System.out.println("Value: " + value);
								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);

							} else if ("Gaza_Power_Plant_MWs".equals(electricRecord)) {
								value = value + dayRecord.getGazaPowerPlant();
								//System.out.println("Value: " + value);
								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);
							} else if ("Egyptian_Lines_MWs".equals(electricRecord)) {
								value = value + dayRecord.getEgyptian_Lines();
								//System.out.println("Value: " + value);
								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);
							}

						}

					}
					average = (float) (count > 0 ? (float) value / count : 0.0);
					e.setAverage(average);
				}
			}
		}
		return e;
	}

	public static ElectricStats statisticsM(String selectedMonth, String electricRecord) {
		int count = 0;
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		float value = 0.0f;
		float avg = 0.0f;

		ElectricStats e = new ElectricStats(value, avg, max, min);

		for (int j = 2010; j < 2026; j++) {
			Year currentYear = (Year) yearTree.find(j);

			if (currentYear != null) {
				//System.out.println("Year: " + currentYear.getValue());

				for (int i = 0; i < currentYear.getMonthAvlTree().getSize(); i++) {
					Month month = (Month) currentYear.getMonthAvlTree().get(i);

					if (month != null) {
					//	System.out.println("Month: " + month.getValue());

						for (int k = 0; k < month.getDayAvlTree().getSize(); k++) {
							Day day = (Day) month.getDayAvlTree().get(k);

							if (day != null) {
							//	System.out.println("Day: " + day.getValue());

								ElectricityRecord dayRecord = day.getInformation();

								if ("Israeli_Lines_MWs".equals(electricRecord)) {
									value = value + dayRecord.getIsrael_Lines();
									//System.out.println("Value: " + value);
									count++;
									max = Math.max(max, value);
									min = Math.min(min, value);
									e.setTotal(value);
									e.setMaximum(max);
									e.setMinimum(min);

								} else if ("Gaza_Power_Plant_MWs".equals(electricRecord)) {
									value = value + dayRecord.getGazaPowerPlant();
									//System.out.println("Value: " + value);
									count++;
									max = Math.max(max, value);
									min = Math.min(min, value);
									e.setTotal(value);
									e.setMaximum(max);
									e.setMinimum(min);
								} else if ("Egyptian_Lines_MWs".equals(electricRecord)) {
									value = value + dayRecord.getEgyptian_Lines();
									//System.out.println("Value: " + value);
									count++;
									max = Math.max(max, value);
									min = Math.min(min, value);
									e.setTotal(value);
									e.setMaximum(max);
									e.setMinimum(min);
								}

							}

						}
						avg = (float) (count > 0 ? (float) value / count : 0.0);
						e.setAverage(avg);
					}
				}
			}
		}

		return e;

	}

	public static ElectricStats statisticsD(int selectedDay, String electricRecord) {
		int count = 0;
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		float value = 0.0f;
		float avg = 0.0f;

		ElectricStats e = new ElectricStats(value, avg, max, min);

		for (int i = 2010; i < 2026; i++) {
			Year currentYear = (Year) yearTree.find(i);

			if (currentYear != null) {
				//System.out.println("Year: " + currentYear.getValue());

				for (int j = 0; j < currentYear.getMonthAvlTree().getSize(); j++) {
					Month month = (Month) currentYear.getMonthAvlTree().get(j);

					if (month != null) {
					//	System.out.println("Month: " + month.getValue());

						// Find the Day based on selectedDay
						Day day = findDayInMonth(month, selectedDay);

						if (day != null) {
						//	System.out.println("Day: " + day.getValue());
							ElectricityRecord dayRecord = day.getInformation();

							if ("Israeli_Lines_MWs".equals(electricRecord)) {
								value = value + dayRecord.getIsrael_Lines();
								//System.out.println("Value: " + value);
								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);
							} else if ("Gaza_Power_Plant_MWs".equals(electricRecord)) {
								value += dayRecord.getGazaPowerPlant();
								//System.out.println("Value: " + value);
								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);
							} else if ("Egyptian_Lines_MWs".equals(electricRecord)) {
								value += dayRecord.getEgyptian_Lines();
								//System.out.println("Value: " + value);
								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);
							}
						}
					}
				}
			}
		}

		avg = (count > 0) ? value / count : 0.0f;
		e.setAverage(avg);

		return e;
	}

	private static Day findDayInMonth(Month month, int targetDay) {
		for (int k = 0; k < month.getDayAvlTree().getSize(); k++) {
			Day day = (Day) month.getDayAvlTree().get(k);
			if (day != null && day.getValue() == targetDay) {
				return day;
			}
		}
		return null;
	}

	public static ElectricStats statisticsAll(String electricRecord) {
		int count = 0;
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		float value = 0.0f;
		float average = 0.0f;

		ElectricStats e = new ElectricStats(value, average, max, min);

		for (int i = 2010; i < 2026; i++) {
			Year currentYear = (Year) yearTree.find(i);
			if (currentYear != null) {
				//System.out.println("Year: " + currentYear.getValue());

				for (int j = 0; j < currentYear.getMonthAvlTree().getSize(); j++) {
					Month month = (Month) currentYear.getMonthAvlTree().get(j);

					if (month != null) {
					//	System.out.println("Month: " + month.getValue());

						for (int k = 0; k < month.getDayAvlTree().getSize(); k++) {
							Day day = (Day) month.getDayAvlTree().get(k);

							if (day != null) {
								//System.out.println("Day: " + day.getValue());

								ElectricityRecord dayRecord = day.getInformation();

								if ("Israeli_Lines_MWs".equals(electricRecord)) {
									value += dayRecord.getIsrael_Lines();
								} else if ("Gaza_Power_Plant_MWs".equals(electricRecord)) {
									value += dayRecord.getGazaPowerPlant();
								} else if ("Egyptian_Lines_MWs".equals(electricRecord)) {
									value += dayRecord.getEgyptian_Lines();
								}

								count++;
								max = Math.max(max, value);
								min = Math.min(min, value);
								e.setTotal(value);
								e.setMaximum(max);
								e.setMinimum(min);
							}
						}
					}
				}
			}
		}
		average = (count > 0) ? value / count : 0.0f;
		e.setAverage(average);

		return e;
	}

}
