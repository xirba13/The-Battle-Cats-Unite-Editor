package com.battlecatsunite;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.TextField;

public class FileManager {

    private static final int BYTE_MASK = 0xFF;

    public void readCurrentValues(String filePath, TextField catFoodField,
            TextField rareTicketsField, TextField catTicketsField, TextField platinumTicketsField,
            TextField xpField, TextField redSeedsField, TextField redFruitsField,
            TextField blueSeedsField, TextField blueFruitsField, TextField yellowSeedsField,
            TextField yellowFruitsField, TextField purpleSeedsField, TextField purpleFruitsField,
            TextField greenSeedsField, TextField greenFruitsField, TextField epicFruitsField) {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            catFoodField.setPromptText(String.valueOf(
                    readByteAtPosition(file, 3, 0) << 8 | readByteAtPosition(file, 2, 15)));

            rareTicketsField.setPromptText(String.valueOf(
                    readByteAtPosition(file, 757, 12) << 8 | readByteAtPosition(file, 757, 11)));

            catTicketsField.setPromptText(String.valueOf(
                    readByteAtPosition(file, 757, 8) << 8 | readByteAtPosition(file, 757, 7)));

            platinumTicketsField.setPromptText(String.valueOf(
                    readByteAtPosition(file, 14336, 0) << 8 | readByteAtPosition(file, 14335, 15)));

            xpField.setPromptText(String.valueOf(readByteAtPosition(file, 7, 6) << 24
                    | readByteAtPosition(file, 7, 5) << 16 | readByteAtPosition(file, 7, 4) << 8
                    | readByteAtPosition(file, 7, 3)));

            redSeedsField.setPromptText(String.valueOf(readByteAtPosition(file, 13997, 3)));
            redFruitsField.setPromptText(String.valueOf(readByteAtPosition(file, 13998, 7)));

            blueSeedsField.setPromptText(String.valueOf(readByteAtPosition(file, 13997, 7)));
            blueFruitsField.setPromptText(String.valueOf(readByteAtPosition(file, 13998, 11)));

            yellowSeedsField.setPromptText(String.valueOf(readByteAtPosition(file, 13997, 15)));
            yellowFruitsField.setPromptText(String.valueOf(readByteAtPosition(file, 13999, 3)));

            purpleSeedsField.setPromptText(String.valueOf(readByteAtPosition(file, 13996, 15)));
            purpleFruitsField.setPromptText(String.valueOf(readByteAtPosition(file, 13998, 3)));

            greenSeedsField.setPromptText(String.valueOf(readByteAtPosition(file, 13997, 11)));
            greenFruitsField.setPromptText(String.valueOf(readByteAtPosition(file, 13998, 15)));

            epicFruitsField.setPromptText(String.valueOf(readByteAtPosition(file, 13999, 7)));
        } catch (IOException e) {
            GUIManager.showAlert("Error", "Error reading the file: " + e.getMessage());
        }
    }

    public void modifyFile(String filePath, int catFood, int rareTickets, int catTickets,
            int platinumTickets, int xp, int redSeeds, int redFruits, int blueSeeds, int blueFruits,
            int yellowSeeds, int yellowFruits, int purpleSeeds, int purpleFruits, int greenSeeds,
            int greenFruits, int epicFruits) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            file.seek(calculatePosition(3, 0));
            file.writeByte(catFood >> 8);
            file.seek(calculatePosition(2, 15));
            file.writeByte(catFood & BYTE_MASK);
            file.seek(calculatePosition(757, 12));
            file.writeByte(rareTickets >> 8);
            file.seek(calculatePosition(757, 11));
            file.writeByte(rareTickets & BYTE_MASK);
            file.seek(calculatePosition(757, 8));
            file.writeByte(catTickets >> 8);
            file.seek(calculatePosition(757, 7));
            file.writeByte(catTickets & BYTE_MASK);
            file.seek(calculatePosition(14336, 0));
            file.writeByte(platinumTickets >> 8);
            file.seek(calculatePosition(14335, 15));
            file.writeByte(platinumTickets & BYTE_MASK);
            file.seek(calculatePosition(7, 6));
            file.writeByte((xp >> 24) & BYTE_MASK);
            file.seek(calculatePosition(7, 5));
            file.writeByte((xp >> 16) & BYTE_MASK);
            file.seek(calculatePosition(7, 4));
            file.writeByte((xp >> 8) & BYTE_MASK);
            file.seek(calculatePosition(7, 3));
            file.writeByte(xp & BYTE_MASK);

            writeByteAtPosition(file, 13997, 3, redSeeds);
            writeByteAtPosition(file, 13998, 7, redFruits);

            writeByteAtPosition(file, 13997, 7, blueSeeds);
            writeByteAtPosition(file, 13998, 11, blueFruits);

            writeByteAtPosition(file, 13997, 15, yellowSeeds);
            writeByteAtPosition(file, 13999, 3, yellowFruits);

            writeByteAtPosition(file, 13996, 15, purpleSeeds);
            writeByteAtPosition(file, 13998, 3, purpleFruits);

            writeByteAtPosition(file, 13997, 11, greenSeeds);
            writeByteAtPosition(file, 13998, 15, greenFruits);

            writeByteAtPosition(file, 13999, 7, epicFruits);
        }
    }

    public static void unlockUnitsEnemies(String filePath, int startRow, int startColumn,
            int endRow, int endColumn) {
        int increment = 4;
        Set<Integer> cat_ids = new HashSet<>(Arrays.asList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 18, 21, 20, 19, 14, 123,
            22, 12, 13, 10, 9, 17, 11, 23, 15, 16, 28, 29, 45, 
            62, 82, 108, 127, 140, 24, 25, 130, 172, 340, 390, 
            391, 392, 393, 398, 399, 354, 352, 353, 355, 356, 
            357, 371, 372, 373, 37, 38, 41, 46, 47, 48, 49, 50, 
            51, 52, 55, 56, 58, 145, 146, 147, 148, 149, 197, 
            198, 341, 343, 376, 60, 126, 154, 370, 32, 61, 129, 
            200, 344, 131, 144, 237, 238, 239, 35, 33, 39, 36, 
            40, 31, 30, 150, 151, 152, 153, 199, 377, 91, 92, 
            93, 94, 95, 96, 97, 98, 99, 367, 267, 368, 369, 
            273, 384, 386, 387, 388, 389, 42, 43, 44, 57, 59, 
            143, 71, 72, 73, 124, 125, 158, 338, 75, 76, 105, 
            106, 107, 159, 351, 84, 83, 85, 86, 87, 177, 396, 
            136, 138, 137, 134, 135, 203, 322, 194, 195, 196, 
            212, 226, 261, 257, 258, 259, 271, 272, 316, 345, 
            346, 347, 348, 349, 34, 168, 169, 170, 171, 240, 
            283, 286, 397, 269, 350, 363, 364, 365, 366, 53
        ));

        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            long currentPosition = calculatePosition(startRow, startColumn);
            Integer i = 0;
            while (currentPosition <= calculatePosition(endRow, endColumn)) {
                file.seek(currentPosition);
                byte currentValue = file.readByte();

                if (cat_ids.contains(i)) {
                    file.seek(currentPosition);
                    file.writeByte(0x01);
                } else if(currentValue == 0x01) {
                    file.seek(currentPosition);
                    file.writeByte(0x00);
                }

                currentPosition += increment;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeByteAtPosition(RandomAccessFile file, int row, int column, int value)
            throws IOException {
        file.seek(calculatePosition(row, column));
        file.writeByte(value & BYTE_MASK);
    }

    private int readByteAtPosition(RandomAccessFile file, int row, int column) throws IOException {
        file.seek(calculatePosition(row, column));
        return file.readByte() & BYTE_MASK;
    }

    private static long calculatePosition(int row, int column) {
        return row * 16 + column;
    }
}
