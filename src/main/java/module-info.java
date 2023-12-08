module com.example.stock_team15 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.stock_team15 to javafx.fxml;
    exports com.example.stock_team15;
    exports com.example.stock_team15.model;
    opens com.example.stock_team15.model to javafx.fxml;
}