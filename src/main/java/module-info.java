module maman13.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens maman13.sudoku to javafx.fxml;
    exports maman13.sudoku;
}