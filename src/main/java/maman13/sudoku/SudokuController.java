package maman13.sudoku;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML
    private GridPane grid;

    // Create a 2D array of TextFields to represent the Sudoku board
    private TextField[][] board;

    private final int SIZE = 9;

    public void initialize() {
        board = new TextField[SIZE][SIZE];
        initializeBoard();
    }

    // Initialize the board
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Create a new TextField for each cell
                board[i][j] = new TextField();
                board[i][j].setPrefSize(grid.getPrefHeight() / SIZE, grid.getPrefWidth() / SIZE);
                grid.add(board[i][j], i, j);

                int finalI = i;
                int finalJ = j;
                board[i][j].setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.ENTER) {
                            String text = board[finalI][finalJ].getText();
                            if (text.trim().isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Input cannot be null!");
                                alert.setContentText("Please enter an input.");
                                alert.showAndWait();
                            } else {
                                try {
                                    int num = Integer.parseInt(text);
                                    if (num < 1 || num > 9) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Invalid input entered!");
                                        alert.setContentText("Please enter a valid number between 1 and 9.");
                                        alert.showAndWait();
                                        board[finalI][finalJ].clear();
                                    } else if (!isCorrectNumber(finalI, finalJ, num)) {
                                        // The entered number is incorrect.
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Incorrect number entered!");
                                        alert.setContentText("Please enter a correct number.");
                                        alert.showAndWait();
                                        board[finalI][finalJ].clear();
                                    }
                                } catch (NumberFormatException e) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("Input cannot be non - numeric!");
                                    alert.setContentText("Please enter a valid numeric value.");
                                    alert.showAndWait();
                                    board[finalI][finalJ].clear();
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @FXML
    void clearPressed (ActionEvent event){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].clear();+++33
            }
        }

    }

    @FXML
    void setPressed (ActionEvent event){

    }

    private boolean isCorrectNumber(int row, int col, int num) {
        // Check if the number already exists in the same row
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i].getText().equals(String.valueOf(num))) {
                return false;
            }
        }

        // Check if the number already exists in the same column
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col].getText().equals(String.valueOf(num))) {
                return false;
            }
        }

        // Check if the number already exists in the same 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (i != row && j != col && board[i][j].getText().equals(String.valueOf(num))) {
                    return false;
                }
            }
        }

        return true;
    }
}
