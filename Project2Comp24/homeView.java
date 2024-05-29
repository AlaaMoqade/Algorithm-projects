package Project2Comp24;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class homeView extends Application{

	@Override
	public void start(Stage stage1) throws Exception {
		
		javafx.scene.image.Image image = new javafx.scene.image.Image(
				"C:\\Users\\amjad\\Downloads\\Vintage Black Orange Energy Charge Typography T-Shirt (1).png");
		ImageView imageView = new ImageView(image);
		
		double desiredWidth = 600;
        double desiredHeight = 500;

        // Resize the image
        imageView.setFitWidth(desiredWidth);
        imageView.setFitHeight(desiredHeight);

        // Preserve the image's aspect ratio
        imageView.setPreserveRatio(true);

		Button b1 = new Button("       File      ");
		b1.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 20px;-fx-font-weight: bold;");
		b1.setAlignment(Pos.BOTTOM_CENTER);
		
		
        Button b2 = new Button("       App      ");
        b2.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 20px;-fx-font-weight: bold;");
		b2.setAlignment(Pos.BOTTOM_CENTER);
		b2.setVisible(false);
        
		
		BorderPane bp = new BorderPane();
		bp.setBottom(b1);
		bp.setPadding(new Insets(80, 180, 20, 180));
		

		StackPane root = new StackPane();
		root.getChildren().addAll(imageView, bp);
		root.setStyle("-fx-background-color:Black;");

		// Create the scene and set it on the stage
		Scene scene = new Scene(root, 500, 500);

		stage1.setScene(scene);
		stage1.setResizable(false);
		stage1.setTitle("Electric Record In Gaza");
		stage1.show();

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		b1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select File");// Set the title of the file chooser dialog
				File selectedFile = fileChooser.showOpenDialog(stage1);// Show the open dialog and get the selected

				if (selectedFile != null) {
					
					
						try {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("INFORMATION");
							alert.setContentText("successfuly");
							alert.showAndWait();
							//filechooser.setVisible(false);
							Project2Comp24.driver.readAndProcessFile(selectedFile);
							String content = new String(Files.readAllBytes(selectedFile.toPath()));
							
							
							b1.setVisible(false);
							b2.setVisible(true);
							bp.setBottom(b2);
							b2.setOnAction(new viewApp());
							
							

						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("INFORMATION");
						alert.setContentText("please chose a file!!");
						alert.showAndWait();
					}
				}
			

		});

		
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);

	}
	
}
