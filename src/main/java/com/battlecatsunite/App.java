package com.battlecatsunite;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class App extends Application {

    private String filePath;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setPadding(new Insets(10));

        Label selectFileLabel = new Label("Select or Drop the file:");
        Button selectFileButton = new Button("Select File");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(selectFileLabel, selectFileButton);
        vbox.setAlignment(Pos.CENTER);

        root.getChildren().add(vbox);

        selectFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the file");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                filePath = selectedFile.getAbsolutePath();
                GUIManager.showMainWindow(primaryStage, filePath);
            }
        });

        root.setOnDragOver(event -> {
            if (event.getGestureSource() != root && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        root.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                filePath = db.getFiles().get(0).getAbsolutePath();
                GUIManager.showMainWindow(primaryStage, filePath);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        Image icon = new Image(getClass().getResourceAsStream("/icon.jpg"));
        primaryStage.getIcons().add(icon);


        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("The Battle Cats Unite! Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
