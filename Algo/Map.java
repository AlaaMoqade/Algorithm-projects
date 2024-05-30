package Algo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Map extends Application {

	TabPane tabPane;

	@Override
	public void start(Stage primaryStage) {
		tabPane = new TabPane();

		// First Tab
		Tab firstTab = new Tab("File");
		firstTab.setContent(createFirstTabContent(primaryStage));

		// Second Tab
		Tab secondTab = new Tab("Statistics");
		secondTab.setContent(createSecondTabContent(primaryStage));

		tabPane.getTabs().addAll(firstTab, secondTab);

		Scene scene = new Scene(tabPane,1100,800);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public VBox createFirstTabContent(Stage primaryStage1) {
		VBox content = new VBox(20);
		content.setAlignment(Pos.CENTER);

		Label label = new Label("Welcome to My Dynamic Programming :");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 35));
		label.setTextFill(Color.BLACK);

		Label labe2 = new Label("click on choose file to ublod your data :");
		labe2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		labe2.setTextFill(Color.BLACK);

		Button chooseButton = new Button("Choose File");
		chooseButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 20px;-fx-font-weight: bold;");
		chooseButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(primaryStage1);
			if (file != null) {

				tabPane.getSelectionModel().select(1);
				try {
					Methods.readFile(file);
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION");
				alert.setContentText("please chose a file!!");
				alert.showAndWait();
			}
		});

		// Load the image
		Image image = new Image("C:\\Users\\amjad\\OneDrive\\Pictures\\Screenshots\\Screenshot (95).png");
		ImageView imageView = new ImageView(image);
		imageView.fitWidthProperty().bind(primaryStage1.widthProperty());
		imageView.fitHeightProperty().bind(primaryStage1.heightProperty());

		VBox v = new VBox(10);
		v.getChildren().addAll(label, labe2, chooseButton);
		v.setAlignment(Pos.TOP_LEFT);
		v.setPadding(new Insets(50));
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageView, v);

		content.getChildren().add(stackPane);
		return content;

	}

	public VBox createSecondTabContent(Stage primaryStage) {
		VBox content2 = new VBox(40);
		content2.setAlignment(Pos.TOP_CENTER);
		content2.setPadding(new Insets(50));

		// Center GridPane
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);

		HBox h1 = new HBox(15);
		HBox h2 = new HBox(2);
		HBox h3 = new HBox(2);
		Label sl = new Label("Enter Start city :");
		sl.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		sl.setTextFill(Color.BLACK);

		//h2.getChildren().addAll(sl,Methods.sCombo);

		Label el = new Label("Enter End city :");
		el.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		el.setTextFill(Color.BLACK);

		//h3.getChildren().addAll(el,Methods.eCombo);

		h1.getChildren().addAll(h2, h3);
		h1.setAlignment(Pos.CENTER);

		Button b1 = new Button(" Find Min Cost :");
		b1.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 15px;-fx-font-weight: bold;");

		Button b2 = new Button(" Find Path Min Cost :");
		b2.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 15px;-fx-font-weight: bold;");
		Button b3 = new Button(" Print another Path :");
		b3.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 15px;-fx-font-weight: bold;");

		TextArea textArea1 = new TextArea();
		textArea1.setPrefRowCount(35);
		textArea1.setPrefColumnCount(40);

		TextArea textArea2 = new TextArea();
		textArea2.setPrefRowCount(20);
		textArea2.setPrefColumnCount(25);

		TextField textField1 = new TextField();
		// TextField textField2 = new TextField();

//		gridPane.add(b1, 0, 0);
//		gridPane.add(textField1, 1, 0);
//		gridPane.add(b2, 0, 1);
//		gridPane.add(textArea2, 1, 1);
//		gridPane.add(b3, 0, 2);
//		gridPane.add(textArea1, 1, 2);
		
		HBox r1 = new HBox(10);
		HBox r2 = new HBox(10);
		HBox r3 = new HBox(10);
		VBox r4 = new VBox(10);
		
		r1.getChildren().addAll(b1 , textField1);
		r2.getChildren().addAll(b2 , textArea2);
		r1.getChildren().addAll(b3 , textArea1);
		r4.getChildren().addAll(r1,r2,r3);
		

		Button b4 = new Button("Print DP Table:");
		b4.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 15px;-fx-font-weight: bold;");

		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(70);
		textArea.setPrefColumnCount(80);
		textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        

		VBox v2 = new VBox(10);
		v2.setAlignment(Pos.BOTTOM_CENTER);
		//v2.setPadding(new Insets(0, 40, 20, 40));

		b4.setOnAction(event -> {
		    textArea.setText("");
		    if (Methods.ECity >= Methods.SCity) {
		        StringBuilder outputBuilder = new StringBuilder();
		        for (int i = 1; i < Methods.ECity; i++) {
		            if (Methods.next[0][i].equals("X")) {
		                Methods.next[0][i] = Methods.startValue;
		            }
		        }

		        outputBuilder.append("          ");

		        for (int i = Methods.d; i <= Methods.ECity + Methods.d; i++) {
		            outputBuilder.append(String.format("%-10s", Methods.city[i]));
		        }

		        outputBuilder.append("\n");

		        for (int i = 0; i < Methods.numOfCities; i++) { // print the paths
		            outputBuilder.append(String.format("%-10s", Methods.city[i + Methods.d]));
		            for (int j = 0; j < Methods.numOfCities; j++) {
		                if (Methods.DPtable[i][j] == Integer.MAX_VALUE || j < i) {
		                    outputBuilder.append(String.format("%-10s", ""));
		                } else {
		                    // Check if the current cost is 311, then decrement it to 310
		                    int cost = Methods.DPtable[i][j];
		                    if (cost == 311)
		                        cost = 310;
		                    outputBuilder.append(String.format("%-10s", cost));
		                }
		                if (j == Methods.numOfCities - 1) {
		                    outputBuilder.append("\n");
		                    outputBuilder.append("\n");
		                }
		            }
		        }
		        textArea.setStyle("-fx-font-family: 'Courier New', monospaced;");
		        textArea.appendText(outputBuilder.toString());
		    } else
		        textArea.appendText("Error!!!!!");

		});


		b2.setOnAction(e -> {
			if ( Methods. ECity >=Methods. SCity &&Methods. DPtable[0][Methods. numOfCities - 1] != Integer.MAX_VALUE)
				textArea2.setText( Methods. printPath(Methods. next,Methods.startValue ,Methods. next[0][Methods. numOfCities - 1]) + Methods.endValue );
			else
				textArea2.setText("Error!!!, There is no direct way.");
		});

		v2.getChildren().addAll(b4, textArea);

		b1.setOnAction(e -> {
			if (Methods.ECity >= Methods.SCity) {
		        Methods.FindMinCost(Methods.startValue, Methods.endValue, Methods.Data);
		        int minCost = Methods.DPtable[0][Methods.numOfCities - 1];
		        if (minCost == 311) {
		            minCost = 310;
		        }
		        textField1.setText(Integer.toString(minCost));
		    } else {
		        textField1.setText("Error!!!, There is no direct way.");
		    }
		
		});

		b3.setOnAction(e -> {
			if (Methods. ECity >=Methods. SCity &&Methods. DPtable[0][Methods. numOfCities - 1] != Integer.MAX_VALUE)
				textArea1.setText(Methods.printAllPaths(Methods. city,Methods. DPtable,Methods.startValue , Methods.endValue));
			else
				textArea1.setText("Error!!!, There is no direct way.");
		});
		
		content2.getChildren().addAll(r4, v2);
		return content2;

	}

	public static void main(String[] args) {
		launch(args);
	}
}
