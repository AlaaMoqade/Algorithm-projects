package Project2Comp24;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import project1.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class viewApp implements EventHandler<ActionEvent> {

	public Project2Comp24.driver getList = new Project2Comp24.driver();
	private DatePicker datePicker;
	Stage primaryStage1 = new Stage();
	Stage primaryStage2 = new Stage();
	String selectedElectricRecord ;

	@Override
	public void handle(ActionEvent arg0) {
		primaryStage1.setTitle("Management Application");

		// Create the main layout
		BorderPane borderPane1 = new BorderPane();

		TabPane tabPane = new TabPane();

		Tab managementTab = createManagementTab();
		tabPane.getTabs().add(managementTab);

		Tab statistaceTab = createStatisticsTab();
		tabPane.getTabs().add(statistaceTab);

		Tab saveTab = createSaveTab();
		tabPane.getTabs().add(saveTab);

		// Add the tabPane to the center of the borderPane
		borderPane1.setCenter(tabPane);
		borderPane1.setStyle("-fx-background-color:Black;");

		// Create the scene
		Scene scene = new Scene(borderPane1, 600, 600);

		// Set the scene and show the stage
		primaryStage1.setScene(scene);
		primaryStage1.show();
	}

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Management Tap

	private Tab createManagementTab() {
		Tab tab = new Tab("Management");

		Stage s = new Stage();
		datePicker = new DatePicker();

		Button searchButton = new Button("Search");
		searchButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13;");

		// Add functionality to the read file button

		GridPane gp = new GridPane();

		Label l1 = new Label("Israeli Line");
		l1.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
		Label l2 = new Label("Gaza_Power");
		l2.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
		Label l3 = new Label("Egyptian_Lines");
		l3.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
		Label l4 = new Label("Total_daily_Supply_available");
		l4.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
		Label l5 = new Label("Overall_demand");
		l5.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
		Label l6 = new Label("Power_Cuts_hours_day");
		l6.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
		Label l7 = new Label("Temp");
		l7.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();
		TextField t6 = new TextField();
		TextField t7 = new TextField();

		VBox v1 = new VBox(2);
		v1.getChildren().addAll(l1, t1);

		VBox v2 = new VBox(2);
		v2.getChildren().addAll(l2, t2);

		VBox v3 = new VBox(2);
		v3.getChildren().addAll(l3, t3);

		VBox v4 = new VBox(2);
		v4.getChildren().addAll(l4, t4);

		VBox v5 = new VBox(2);
		v5.getChildren().addAll(l5, t5);

		VBox v6 = new VBox(2);
		v6.getChildren().addAll(l6, t6);

		VBox v7 = new VBox(2);
		v7.getChildren().addAll(l7, t7);

		gp.add(v1, 0, 0);
		gp.add(v2, 1, 0);
		gp.add(v3, 0, 1);
		gp.add(v4, 1, 1);
		gp.add(v5, 0, 2);
		gp.add(v6, 1, 2);
		gp.add(v7, 0, 3);
		gp.setVgap(12);
		gp.setHgap(12);

		Button addButton = new Button("Add");
		addButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13;");
		Button deleteButton = new Button("Delete");
		deleteButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13;");
		Button updateButton = new Button("Update");
		updateButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13;");

		Button levelButton = new Button(" Traverse");
		levelButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13;");

		Button heightButton = new Button("display the height");
		heightButton.setStyle("-fx-text-fill: blue; -fx-font-weight: bold; -fx-font-size: 13;");

		addButton.setOnAction(event -> {

			LocalDate selectedDate = datePicker.getValue();
			float f1 = Float.parseFloat(t1.getText());
			float f2 = Float.parseFloat(t2.getText());
			float f3 = Float.parseFloat(t3.getText());
			float f4 = Float.parseFloat(t4.getText());
			float f5 = Float.parseFloat(t5.getText());
			float f6 = Float.parseFloat(t6.getText());
			float f7 = Float.parseFloat(t7.getText());

			ElectricityRecord info = new ElectricityRecord(selectedDate, f1, f2, f3, f4, f5, f6, f7);
			if (getList.search(selectedDate) == null) {
				getList.addElectricRecord(info);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("added it successfully");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("the Electric Record  exists you cant added!!!");
				alert.showAndWait();
			}
			datePicker.setValue(null);
			t1.clear();
			t2.clear();
			t3.clear();
			t4.clear();
			t5.clear();
			t6.clear();
			t7.clear();

		});
		// delete button action
		deleteButton.setOnAction(event -> {
			LocalDate selectedDate = datePicker.getValue();
			ElectricityRecord e = getList.search(selectedDate);

			if (e != null) {

				getList.deleteElectricRecord(selectedDate);
				t1.clear();
				t2.clear();
				t3.clear();
				t4.clear();
				t5.clear();
				t6.clear();
				t7.clear();
				datePicker.setValue(null);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("delete it successfully");
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("the date not exists you cant deleted!!!");
				alert.showAndWait();
			}

			System.out.println("Deleting data...");
		});
		// update button action
		updateButton.setOnAction(event -> {

			LocalDate selectedDate = datePicker.getValue();

			float f1 = Float.parseFloat(t1.getText());
			float f2 = Float.parseFloat(t2.getText());
			float f3 = Float.parseFloat(t3.getText());
			float f4 = Float.parseFloat(t4.getText());
			float f5 = Float.parseFloat(t5.getText());
			float f6 = Float.parseFloat(t6.getText());
			float f7 = Float.parseFloat(t7.getText());

			ElectricityRecord info = new ElectricityRecord(selectedDate, f1, f2, f3, f4, f5, f6, f7);

			if (info != null) {
				getList.updateElectric(selectedDate, info);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("update it successfully");
				alert.showAndWait();
				t1.clear();
				t2.clear();
				t3.clear();
				t4.clear();
				t5.clear();
				t6.clear();
				t7.clear();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("the date not exists you cant update it !!!");
				alert.showAndWait();
			}

			System.out.println("Updating data...");
		});

		// search button action
		searchButton.setOnAction(event ->

		{
			t1.clear();
			t2.clear();
			t3.clear();
			t4.clear();
			t5.clear();
			t6.clear();
			t7.clear();

			LocalDate selectedDate = datePicker.getValue();
			if (selectedDate != null) {
				ElectricityRecord e = getList.search(selectedDate);

				if (e != null) {
					t1.setText(String.valueOf(e.getIsrael_Lines()));
					t2.setText(String.valueOf(e.getGazaPowerPlant()));
					t3.setText(String.valueOf(e.getEgyptian_Lines()));
					t4.setText(String.valueOf(e.getTotalDailySupply()));
					t5.setText(String.valueOf(e.getOverallDemand()));
					t6.setText(String.valueOf(e.getPowerCutsHoursDay()));
					t7.setText(String.valueOf(e.getTemp()));

				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION");
					alert.setContentText("the date not existes!!!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("please selecte date!!!");
				alert.showAndWait();
			}

		});

		// >>>>>>>>>>>>>>>>>>>>>>> Traverse action button

		levelButton.setOnAction(event -> {
			LocalDate selectedDate = datePicker.getValue();
			if (selectedDate != null) {
				Stage p = new Stage();
				TextArea textArea = new TextArea();
				textArea.setEditable(false);

				Font customFont = Font.font("Verdana", FontWeight.BOLD, 12);
				textArea.setFont(customFont);

				Scene scene = new Scene(textArea, 400, 400);
				p.setScene(scene);
				p.show();

				printTreesAfterSearch(selectedDate, textArea);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("please selecte date!!!");
				alert.showAndWait();
			}

		});
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> height action button
		heightButton.setOnAction(event -> {
			LocalDate selectedDate2 = datePicker.getValue();
			if (selectedDate2 != null) {
				Stage p1 = new Stage();
				TextArea textArea = new TextArea();
				textArea.setEditable(false);

				Font customFont = Font.font("Verdana", FontWeight.BOLD, 12);
				textArea.setFont(customFont);

				Scene scene = new Scene(textArea, 400, 400);
				p1.setScene(scene);
				p1.show();

				printHRSearch(selectedDate2, textArea);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("please selecte date!!!");
				alert.showAndWait();
			}

		});

		// Create layout for the Management tab
		VBox managementLayout = new VBox(30);
		managementLayout.setPadding(new Insets(10, 30, 10, 30));
		Label text = new Label("Choose Date:");
		text.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		managementLayout.getChildren().addAll(new HBox(10, text, datePicker, searchButton), gp,
				// new HBox(10, new VBox(10, labels), new VBox(10, textFields)),
				new HBox(10, addButton, updateButton, deleteButton, levelButton, heightButton));

		// Set the layout for the Management tab
		tab.setContent(managementLayout);
		return tab;
	}

	private void printHRSearch(LocalDate selectedDate, TextArea textArea) {
		int yearValue = selectedDate.getYear();
		Year year = (Year) getList.yearTree.find(new Year(yearValue));
		int d = selectedDate.getDayOfMonth();

		if (year != null) {
			textArea.appendText("\nPrinting hight and Root year for " + yearValue + ":\n");
			textArea.appendText("the Height : " + getList.yearTree.height() + "\n");
			textArea.appendText("Root value : " + getList.yearTree.getRootValue() + "\n\n");

			String monthValue = selectedDate.getMonth().name();
			Month month = (Month) year.getMonthAvlTree().find(new Month(monthValue));

			if (month != null) {

				textArea.appendText(
						"\nPrinting month( " + monthValue + " )hight and root for year " + yearValue + ":\n");
				textArea.appendText("the Height : " + year.getMonthAvlTree().height() + "\n");
				textArea.appendText("Root value : " + year.getMonthAvlTree().getRootValue() + "\n\n");

				textArea.appendText("\nPrinting day( " + d + " ) height and root to " + monthValue + ":\n");
				textArea.appendText("the Height : " + month.getDayAvlTree().height() + "\n");
				textArea.appendText("Root value : " + month.getDayAvlTree().getRootValue() + "\n");
			} else {
				textArea.appendText("Month not found for " + monthValue + "-" + yearValue + "\n");
			}
		} else {
			textArea.appendText("Year not found for " + yearValue + "\n");
		}

	}

	private void printTreesAfterSearch(LocalDate searchDate, TextArea textArea) {
		int yearValue = searchDate.getYear();
		Year year = (Year) getList.yearTree.find(new Year(yearValue));

		if (year != null) {
			appendText("\nPrinting year Tree Level by Level for Year " + yearValue + ":\n", textArea);
			appendText(getList.yearTree.printLevelOrder(), textArea);

			String monthValue = searchDate.getMonth().name();

			Month month = (Month) year.getMonthAvlTree().find(new Month(monthValue));

			if (month != null) {

				appendText("\nPrinting month Tree Level by Level for " + monthValue + ":\n\n", textArea);
				appendText(year.getMonthAvlTree().printLevelOrder(), textArea);

				appendText("\nPrinting day Tree Level by Level for month " + monthValue + ":\n\n", textArea);
				appendText(month.getDayAvlTree().printLevelOrder(), textArea);

			} else {
				appendText("\nMonth not found for " + monthValue + "-" + yearValue + "\n\n", textArea);
			}
		} else {
			appendText("\nYear not found for " + yearValue + "\n", textArea);
		}
	}

// Helper method to append text to the TextArea
	private static void appendText(String text, TextArea textArea) {
		if (textArea != null) {
			textArea.appendText(text);
		}
	}

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..statistics Tap

	private Tab createStatisticsTab() {
		Tab tab = new Tab("Statistics");

		// Create radio buttons
		RadioButton yearRadioButton = new RadioButton("Year");
		yearRadioButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		RadioButton monthRadioButton = new RadioButton("Month");
		monthRadioButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		RadioButton dayRadioButton = new RadioButton("Day");
		dayRadioButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		RadioButton allDateRadioButton = new RadioButton("All Date");
		allDateRadioButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		ToggleGroup toggleGroup = new ToggleGroup();
		yearRadioButton.setToggleGroup(toggleGroup);
		monthRadioButton.setToggleGroup(toggleGroup);
		dayRadioButton.setToggleGroup(toggleGroup);
		allDateRadioButton.setToggleGroup(toggleGroup);

		ComboBox<String> YearComboBox = new ComboBox<>();
		ComboBox<String> monthComboBox = new ComboBox<>();
		ComboBox<String> dayComboBox = new ComboBox<>();
		ComboBox<String> recordComboBox = new ComboBox<>();
		recordComboBox.setPrefSize(20, 20);

		Toggle selectedToggle = toggleGroup.getSelectedToggle();

		// Add items to the combo boxes
		YearComboBox.getItems().addAll("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
				"2020", "2021", "2022", "2023", "2024", "2025", "2026");
		monthComboBox.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December");

		dayComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");

		recordComboBox = new ComboBox<>(
				FXCollections.observableArrayList("Israeli_Lines_MWs", "Gaza_Power_Plant_MWs", "Egyptian_Lines_MWs"));

		recordComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue != null) {
		         selectedElectricRecord = newValue;
		        System.out.println("Selected Electric Record: " + selectedElectricRecord);
		    }
		    });
		
		String selectedyear = YearComboBox.getValue();
		String selectedmonth = monthComboBox.getValue();
		String selectedday = dayComboBox.getValue();

		Label yearLabel = new Label("Select Year:");
		yearLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		Label monthLabel = new Label("Select Month:");
		monthLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		Label dayLabel = new Label("Select Day:");
		dayLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		Label recordLabel = new Label("Select Column:");
		recordLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		VBox buttonBox = new VBox(5);

		Button compute = new Button("   compute   ");
		compute.setStyle("-fx-text-fill: Blue; -fx-font-weight: bold; -fx-font-size: 15;");

		GridPane gp2 = new GridPane();

		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();

		Label l1 = new Label("Avarege:");
		l1.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		Label l2 = new Label("SUM:");
		l2.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		Label l3 = new Label("Max:");
		l3.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		Label l4 = new Label("Min:");
		l4.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");

		VBox tb1 = new VBox(2);
		tb1.getChildren().addAll(l1, t1);
		VBox tb2 = new VBox(2);
		tb2.getChildren().addAll(l2, t2);
		VBox tb3 = new VBox(2);
		tb2.getChildren().addAll(l3, t3);
		VBox tb4 = new VBox(2);
		tb2.getChildren().addAll(l4, t4);

		gp2.add(tb1, 0, 0);
		gp2.add(tb2, 1, 0);
		gp2.add(tb3, 0, 1);
		gp2.add(tb4, 1, 1);
		gp2.setVgap(10);
		gp2.setHgap(10);

		VBox root = new VBox(50);
		root.setPadding(new Insets(30, 30, 30, 30));

		HBox yearBox = new HBox(10);
		yearBox.getChildren().addAll(yearLabel, YearComboBox);

		HBox monthBox = new HBox(10);
		monthBox.getChildren().addAll(monthLabel, monthComboBox);

		HBox dayBox = new HBox(10);
		dayBox.getChildren().addAll(dayLabel, dayComboBox);

		HBox recordBox = new HBox(10);
		recordBox.getChildren().addAll(recordLabel, recordComboBox);

		// Set up the layout

		HBox radioButtons = new HBox(10);
		radioButtons.getChildren().addAll(yearRadioButton, monthRadioButton, dayRadioButton, allDateRadioButton);

		HBox comboBoxes = new HBox(2);
		comboBoxes.getChildren().addAll(yearBox, monthBox, dayBox);

		root.getChildren().addAll(radioButtons, comboBoxes, recordBox, compute, gp2);

		// Set up event handling
		toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			t1.clear();
			t2.clear();
			t3.clear();
			t4.clear();
			if (newValue == yearRadioButton) {

				yearBox.setVisible(true);
				monthBox.setVisible(false);
				dayBox.setVisible(false);

				compute.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						t1.clear();
						t2.clear();
						t3.clear();
						t4.clear();

						int selectedYear = Integer.parseInt(YearComboBox.getSelectionModel().getSelectedItem());

						ElectricStats e = getList.statisticsY(selectedYear, selectedElectricRecord);

						t1.setText(String.valueOf(e.getAverage()));
						t2.setText(String.valueOf(e.getTotal()));
						t3.setText(String.valueOf(e.getMaximum()));
						t4.setText(String.valueOf(e.getMinimum()));

					}

				});

			} else if (newValue == monthRadioButton) {

				yearBox.setVisible(false);
				monthBox.setVisible(true);
				dayBox.setVisible(false);

				compute.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						t1.clear();
						t2.clear();
						t3.clear();
						t4.clear();
						String selectedmonth = monthComboBox.getSelectionModel().getSelectedItem();

						ElectricStats e = getList.statisticsM(selectedmonth, selectedElectricRecord);

						t1.setText(String.valueOf(e.getAverage()));
						t2.setText(String.valueOf(e.getTotal()));
						t3.setText(String.valueOf(e.getMaximum()));
						t4.setText(String.valueOf(e.getMinimum()));
					}
				});

			} else if (newValue == dayRadioButton) {

				yearBox.setVisible(false);
				monthBox.setVisible(false);
				dayBox.setVisible(true);
				compute.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						t1.clear();
						t2.clear();
						t3.clear();
						t4.clear();
						
						int selectedday = Integer.parseInt(dayComboBox.getSelectionModel().getSelectedItem());

						ElectricStats e = getList.statisticsD(selectedday, selectedElectricRecord);

						t1.setText(String.valueOf(e.getAverage()));
						t2.setText(String.valueOf(e.getTotal()));
						t3.setText(String.valueOf(e.getMaximum()));
						t4.setText(String.valueOf(e.getMinimum()));
					}

				});

			} else if (newValue == allDateRadioButton) {

				yearBox.setVisible(false);
				monthBox.setVisible(false);
				dayBox.setVisible(false);
				compute.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						t1.clear();
						t2.clear();
						t3.clear();
						t4.clear();
						ElectricStats e = getList.statisticsAll( selectedElectricRecord);

						t1.setText(String.valueOf(e.getAverage()));
						t2.setText(String.valueOf(e.getTotal()));
						t3.setText(String.valueOf(e.getMaximum()));
						t4.setText(String.valueOf(e.getMinimum()));
					}
				});

			}
		});

		tab.setContent(root);
		return tab;
	}

//<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>save Tap

	private Tab createSaveTab() {
		Tab tab = new Tab("Save");

		HBox b = new HBox();
		HBox b2 = new HBox(10);
		Label text = new Label("Enter Name OF the File to Save:");
		text.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 25;");

		// TextField t = new TextField();
		Button saveButton = new Button("SAVE DATE");
		saveButton.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 15;");
		b2.getChildren().addAll(saveButton);

		b.getChildren().add(text);

		VBox p = new VBox(30);
		p.setPadding(new Insets(20, 30, 30, 60));
		p.getChildren().addAll(b, b2);

		saveButton.setOnAction(event -> {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save List to File");

			File file = fileChooser.showSaveDialog(null);

			if (file != null) {

				try (FileWriter writer = new FileWriter(file)) {
					writer.write(getList.yearTree.printAllYearsMonthsDays());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		tab.setContent(p);
		return tab;

	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
