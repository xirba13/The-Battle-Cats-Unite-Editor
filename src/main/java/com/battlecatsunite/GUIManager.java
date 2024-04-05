package com.battlecatsunite;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class GUIManager {

    public static void showMainWindow(Stage primaryStage, String filePath) {
        if (!isValidSavedataFile(filePath)) {
            showAlert("Error", "The selected file is not a valid savedata file.");
            return;
        }

        Label catFoodLabel = new Label("Cat Food (0-65535):");
        TextField catFoodField = new TextField();
        Label rareTicketsLabel = new Label("Rare Tickets (0-999):");
        TextField rareTicketsField = new TextField();
        Label catTicketsLabel = new Label("Cat Tickets (0-999):");
        TextField catTicketsField = new TextField();
        Label platinumTicketsLabel = new Label("Platinum Tickets (0-999):");
        TextField platinumTicketsField = new TextField();
        Label xpLabel = new Label("XP (0-99999999):");
        TextField xpField = new TextField();
        Button submitButton = new Button("Save");

        FileManager fileManager = new FileManager();
        fileManager.readCurrentValues(filePath, catFoodField, rareTicketsField, catTicketsField,
                platinumTicketsField, xpField);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(catFoodLabel, 0, 0);
        gridPane.add(catFoodField, 1, 0);
        gridPane.add(rareTicketsLabel, 0, 1);
        gridPane.add(rareTicketsField, 1, 1);
        gridPane.add(catTicketsLabel, 0, 2);
        gridPane.add(catTicketsField, 1, 2);
        gridPane.add(platinumTicketsLabel, 0, 3);
        gridPane.add(platinumTicketsField, 1, 3);
        gridPane.add(xpLabel, 0, 4);
        gridPane.add(xpField, 1, 4);
        gridPane.add(submitButton, 1, 5);
        gridPane.setAlignment(Pos.CENTER);

        submitButton.setOnAction(event -> {
            try {
                int catFood = getIntValue(catFoodField.getText().trim(), catFoodField);
                int rareTickets = getIntValue(rareTicketsField.getText().trim(), rareTicketsField);
                int catTickets = getIntValue(catTicketsField.getText().trim(), catTicketsField);
                int platinumTickets =
                        getIntValue(platinumTicketsField.getText().trim(), platinumTicketsField);
                int xp = getIntValue(xpField.getText().trim(), xpField);

                if (validateValues(catFood, rareTickets, catTickets, platinumTickets, xp)) {
                    fileManager.modifyFile(filePath, catFood, rareTickets, catTickets,
                            platinumTickets, xp);
                    showSuccessAlert("Success", "Values modified successfully.");
                } else {
                    showAlert("Error", "Please enter values within the allowed limits.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter valid numeric values.");
            } catch (IOException e) {
                showAlert("Error", "An error occurred while modifying the file: " + e.getMessage());
            }
        });

        Scene scene = new Scene(gridPane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        ImageView imageView = new ImageView(new Image("/success.png"));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("warning.png"));
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        alert.setGraphic(imageView);
        alert.showAndWait();
    }

    private static boolean isValidSavedataFile(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        return fileName.toLowerCase().startsWith("savedata");
    }

    private static int getIntValue(String text, TextField textField) {
        return text.isEmpty() ? Integer.parseInt(textField.getPromptText())
                : Integer.parseInt(text);
    }

    private static boolean validateValues(int catFood, int rareTickets, int catTickets,
            int platinumTickets, int xp) {
        return catFood >= 0 && catFood <= 65535 && rareTickets >= 0 && rareTickets <= 999
                && catTickets >= 0 && catTickets <= 999 && platinumTickets >= 0
                && platinumTickets <= 999 && xp >= 0 && xp <= 99999999;
    }

    static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        ImageView imageView = new ImageView(new Image("/error.png"));
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("warning.png"));
        alert.setGraphic(imageView);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
