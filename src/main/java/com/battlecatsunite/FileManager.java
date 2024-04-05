package com.battlecatsunite;

import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.scene.control.TextField;

public class FileManager {

    private static final int BYTE_MASK = 0xFF;

    public void readCurrentValues(String filePath, TextField catFoodField,
            TextField rareTicketsField, TextField catTicketsField, TextField platinumTicketsField,
            TextField xpField) {
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
        } catch (IOException e) {
            GUIManager.showAlert("Error", "Error reading the file: " + e.getMessage());
        }
    }

    public void modifyFile(String filePath, int catFood, int rareTickets, int catTickets,
            int platinumTickets, int xp) throws IOException {
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
        }
    }

    private int readByteAtPosition(RandomAccessFile file, int row, int column) throws IOException {
        file.seek(calculatePosition(row, column));
        return file.readByte() & BYTE_MASK;
    }

    private long calculatePosition(int row, int column) {
        return row * 16 + column;
    }
}
