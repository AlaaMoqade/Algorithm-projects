package Project3Comp242;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import Project2Comp24.viewApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewApp extends Application {

	private javafx.scene.control.Label filePathLabel;

	private int currentSectionIndex;

	@Override
	public void start(Stage stage1) throws Exception {

		currentSectionIndex = 0;

		BorderPane bp = new BorderPane();
		Button loadButton = new Button("   Load File  ");
		loadButton.setStyle("-fx-background-color: #5BE6FF; -fx-font-size: 15px;-fx-font-weight: bold;");
		loadButton.setAlignment(Pos.BOTTOM_CENTER);

		filePathLabel = new javafx.scene.control.Label(" Selected File :  ");
		filePathLabel.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15; -fx-background-color: #35424a;");

		Button nextButton = new Button("    Next   ");
		nextButton.setStyle("-fx-background-color: #5BE6FF; -fx-font-size: 17px;-fx-font-weight: bold;");
		nextButton.setAlignment(Pos.BOTTOM_CENTER);

		Button prevButton = new Button("  previous ");
		prevButton.setStyle("-fx-background-color: #5BE6FF; -fx-font-size: 17px;-fx-font-weight: bold;");
		prevButton.setAlignment(Pos.BOTTOM_CENTER);

		javafx.scene.control.TextArea text = new javafx.scene.control.TextArea();
		
		text.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 15;");
		
		javafx.scene.control.Label l = new javafx.scene.control.Label(" Equation Section: ");
		l.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 25;");

		VBox v = new VBox(10);
		v.getChildren().addAll(l, text);
		v.setPadding(new Insets(50, 0, 0, 0));

		HBox h = new HBox(20);
		h.getChildren().addAll(prevButton,nextButton);
		h.setPadding(new Insets(0, 0, 130, 160));

		HBox h2 = new HBox(10);
		h2.getChildren().addAll(loadButton, filePathLabel);
		h2.setPadding(new Insets(20, 20, 0, 0));

		bp.setStyle("-fx-background-color:#021931;");
		bp.setTop(h2);
		bp.setCenter(v);
		bp.setBottom(h);
		bp.setPadding(new Insets(20, 20, 20, 20));

		Scene scene = new Scene(bp, 600, 600);

		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.setTitle("show stage");
		stage1.show();

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		loadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select File");
				File selectedFile = fileChooser.showOpenDialog(stage1);

				if (selectedFile != null) {
					filePathLabel.setText("Selected File: " + selectedFile.getAbsolutePath());
					if (driver.isEquationsFileBalanced(selectedFile) == true) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("INFORMATION");
						alert.setContentText("successfully!!! the tage in file is balance");
						alert.showAndWait();

						driver.processEquationsFile(selectedFile);
						displaySection(text);

					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("INFORMATION");
						alert.setContentText("!!! the tag not balanced !!!");
						alert.showAndWait();
					}

				}
			}

		});
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (currentSectionIndex <driver.sectionList.size() - 1) {
					currentSectionIndex++;
					displaySection(text);
				}
			}
		});

		prevButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (currentSectionIndex > 0) {
					currentSectionIndex--;
					displaySection(text);
				}
			}
		});
	}

	private void displaySection(javafx.scene.control.TextArea text) {
		if (!driver.sectionList.isEmpty()) {
			text.clear();

			text.setText(driver.sectionList.get(currentSectionIndex).printSection()); 
		} else {

			text.clear();
			text.setText("No sections available.");
		}
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}