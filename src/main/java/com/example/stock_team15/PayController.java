package com.example.stock_team15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Statement;

public class PayController {

    @FXML
    private Button payButton;

    @FXML
    private TextField companyidTextField;

    @FXML
    private Label buyLabel;

    public void payButtonOnAction(ActionEvent event) {
        if (!companyidTextField.getText().isEmpty()) {
            buyStock();
        } else {
            buyLabel.setText("Please enter a company ID");
        }
    }

    public void buyStock() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String companyId = companyidTextField.getText();

        String insertFields = "INSERT INTO MyStocks (CompanyId, CompanyName, BoughtPrice) ";
        String selectValues = "SELECT CompanyId, CompanyName, Price ";
        String fromTable = "FROM company ";
        String whereCondition = "WHERE CompanyId = " + companyId;
        String insertToRegister = insertFields + selectValues + fromTable + whereCondition;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);
            buyLabel.setText("Stock Bought Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
