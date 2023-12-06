module com.example.stock_team15 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stock_team15 to javafx.fxml;
    exports com.example.stock_team15;
}