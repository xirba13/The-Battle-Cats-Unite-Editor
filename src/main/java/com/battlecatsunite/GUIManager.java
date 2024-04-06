package com.battlecatsunite;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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

        Label catFruitsSummaryLabel = new Label("Cat Fruits (Total Max. 256)");
        Button expandButton = new Button("Expand");
        Label redSeedsLabel = new Label("Red Seeds:");
        TextField redSeedsField = new TextField();
        Label redFruitsLabel = new Label("Red Fruits:");
        TextField redFruitsField = new TextField();
        Label blueSeedsLabel = new Label("Blue Seeds:");
        TextField blueSeedsField = new TextField();
        Label blueFruitsLabel = new Label("Blue Fruits:");
        TextField blueFruitsField = new TextField();
        Label yellowSeedsLabel = new Label("Yellow Seeds:");
        TextField yellowSeedsField = new TextField();
        Label yellowFruitsLabel = new Label("Yellow Fruits:");
        TextField yellowFruitsField = new TextField();
        Label purpleSeedsLabel = new Label("Purple Seeds:");
        TextField purpleSeedsField = new TextField();
        Label purpleFruitsLabel = new Label("Purple Fruits:");
        TextField purpleFruitsField = new TextField();
        Label greenSeedsLabel = new Label("Green Seeds:");
        TextField greenSeedsField = new TextField();
        Label greenFruitsLabel = new Label("Green Fruits:");
        TextField greenFruitsField = new TextField();

        Button submitButton = new Button("Save");

        Region spacingRegion = new Region();
        spacingRegion.setPrefHeight(20);


        GridPane seedsAndFruitsContainer = new GridPane();
        seedsAndFruitsContainer.setHgap(10);
        seedsAndFruitsContainer.setVgap(5);

        seedsAndFruitsContainer.addRow(0, redSeedsLabel, redSeedsField, redFruitsLabel,
                redFruitsField);
        seedsAndFruitsContainer.addRow(1, blueSeedsLabel, blueSeedsField, blueFruitsLabel,
                blueFruitsField);
        seedsAndFruitsContainer.addRow(2, yellowSeedsLabel, yellowSeedsField, yellowFruitsLabel,
                yellowFruitsField);
        seedsAndFruitsContainer.addRow(3, purpleSeedsLabel, purpleSeedsField, purpleFruitsLabel,
                purpleFruitsField);
        seedsAndFruitsContainer.addRow(4, greenSeedsLabel, greenSeedsField, greenFruitsLabel,
                greenFruitsField);

        seedsAndFruitsContainer.setVisible(false);

        FileManager fileManager = new FileManager();
        fileManager.readCurrentValues(filePath, catFoodField, rareTicketsField, catTicketsField,
                platinumTicketsField, xpField, redSeedsField, redFruitsField, blueSeedsField,
                blueFruitsField, yellowSeedsField, yellowFruitsField, purpleSeedsField,
                purpleFruitsField, greenSeedsField, greenFruitsField);

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
        gridPane.add(spacingRegion, 0, 5);
        gridPane.add(catFruitsSummaryLabel, 0, 6);
        gridPane.add(expandButton, 1, 6);
        gridPane.add(seedsAndFruitsContainer, 0, 7, 2, 1);

        final boolean[] expandedMenu = {false};
        expandButton.setOnAction(event -> {
            expandedMenu[0] = !expandedMenu[0];
            if (expandedMenu[0]) {
                expandButton.setText("Collapse");
                GridPane.setRowIndex(submitButton, 11);
            } else {
                expandButton.setText("Expand");
                GridPane.setRowIndex(submitButton, 7);
            }
            seedsAndFruitsContainer.setVisible(!seedsAndFruitsContainer.isVisible());
        });
        

        gridPane.add(submitButton, 3, 7);
        gridPane.setAlignment(Pos.CENTER);

        submitButton.setOnAction(event -> {
            int catFood = getIntValue(catFoodField.getText().trim(), catFoodField);
            int rareTickets = getIntValue(rareTicketsField.getText().trim(), rareTicketsField);
            int catTickets = getIntValue(catTicketsField.getText().trim(), catTicketsField);
            int platinumTickets =
                    getIntValue(platinumTicketsField.getText().trim(), platinumTicketsField);
            int xp = getIntValue(xpField.getText().trim(), xpField);
            int redSeeds = getIntValue(redSeedsField.getText().trim(), redSeedsField);
            int redFruits = getIntValue(redFruitsField.getText().trim(), redFruitsField);
            int blueSeeds = getIntValue(blueSeedsField.getText().trim(), blueSeedsField);
            int blueFruits = getIntValue(blueFruitsField.getText().trim(), blueFruitsField);
            int yellowSeeds = getIntValue(yellowSeedsField.getText().trim(), yellowSeedsField);
            int yellowFruits = getIntValue(yellowFruitsField.getText().trim(), yellowFruitsField);
            int purpleSeeds = getIntValue(purpleSeedsField.getText().trim(), purpleSeedsField);
            int purpleFruits = getIntValue(purpleFruitsField.getText().trim(), purpleFruitsField);
            int greenSeeds = getIntValue(greenSeedsField.getText().trim(), greenSeedsField);
            int greenFruits = getIntValue(greenFruitsField.getText().trim(), greenFruitsField);


            if (validateValues(catFood, rareTickets, catTickets, platinumTickets, xp, redSeeds,
                    redFruits, blueSeeds, blueFruits, yellowSeeds, yellowFruits, purpleSeeds,
                    purpleFruits, greenSeeds, greenFruits)) {
                try {
                    fileManager.modifyFile(filePath, catFood, rareTickets, catTickets,
                            platinumTickets, xp, redSeeds, redFruits, blueSeeds, blueFruits,
                            yellowSeeds, yellowFruits, purpleSeeds, purpleFruits, greenSeeds,
                            greenFruits);
                    showSuccessAlert("Success", "Values modified successfully.");
                } catch (IOException e) {
                    showAlert("Error",
                            "An error occurred while modifying the file: " + e.getMessage());
                }
            } else {
                showAlert("Error", "Please enter valid numeric values within the allowed limits.");
            }
        });

        Scene scene = new Scene(gridPane, 800, 600);
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
            int platinumTickets, int xp, int redSeeds, int redFruits, int blueSeeds, int blueFruits,
            int yellowSeeds, int yellowFruits, int purpleSeeds, int purpleFruits, int greenSeeds,
            int greenFruits) {

        int totalSeeds = redSeeds + blueSeeds + yellowSeeds + purpleSeeds + greenSeeds;
        int totalFruits = redFruits + blueFruits + yellowFruits + purpleFruits + greenFruits;

        boolean allFruitValuesNonNegative = redSeeds >= 0 && redFruits >= 0 && blueSeeds >= 0
                && blueFruits >= 0 && yellowSeeds >= 0 && yellowFruits >= 0 && purpleSeeds >= 0
                && purpleFruits >= 0 && greenSeeds >= 0 && greenFruits >= 0;

        boolean totalValid = totalSeeds + totalFruits <= 256;


        boolean individualValid = catFood >= 0 && catFood <= 65535 && rareTickets >= 0
                && rareTickets <= 999 && catTickets >= 0 && catTickets <= 999
                && platinumTickets >= 0 && platinumTickets <= 999 && xp >= 0 && xp <= 99999999;

        return totalValid && individualValid && allFruitValuesNonNegative;
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
