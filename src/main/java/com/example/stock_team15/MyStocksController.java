package com.example.stock_team15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MyStocksController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextArea stockTextArea;

    @FXML
    private Button removeButton;

    @FXML
    private Button backButton;

    public void backButtonOnAction(ActionEvent event){
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void removeButtonOnAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Remove.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void initialize() {
        loadStockInfo();
    }

    private void loadStockInfo() {
        // Use the DatabaseConnection class to establish the database connection
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        // If the connection is null, display an error message
        if (connection == null) {
            stockTextArea.setText("Error connecting to the database");
            return;
        }

        // Load stock information from the 'mystocks' table into the 'stockTextArea'
        String selectSql = "SELECT * FROM mystocks";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int stockid = resultSet.getInt("stockid");
                int companyid = resultSet.getInt("companyid");
                String companyname = resultSet.getString("companyname");
                float boughtprice = resultSet.getFloat("boughtprice");


                // Append the information to the 'stockTextArea'
                stockTextArea.appendText( "      ");
                stockTextArea.appendText( stockid + "                                  ");
                stockTextArea.appendText( companyid + "                                  ");
                stockTextArea.appendText(companyname + "                                  ");
                stockTextArea.appendText(boughtprice + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            stockTextArea.setText("Error loading stock information. Check the console for details.");
        }
    }
}
