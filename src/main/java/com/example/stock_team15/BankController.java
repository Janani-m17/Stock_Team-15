package com.example.stock_team15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Statement;
import java.time.temporal.Temporal;

public class BankController {

    @FXML
    private Button okButton;
    @FXML
    private TextField banknameTextField;

    @FXML
    private TextField accountnoTextField;

    @FXML
    private TextField accountnameTextField;

    @FXML
    private TextField ifsccodeTextField;

    @FXML
    private Label okLabel;

    public void okButtonOnAction(ActionEvent event) {
        if (!banknameTextField.getText().isEmpty() && !accountnoTextField.getText().isEmpty() && !accountnameTextField.getText().isEmpty() && !ifsccodeTextField.getText().isEmpty()) {
            buyStock();
        } else {
            okLabel.setText("Please enter a company ID");
        }
    }

    public void buyStock() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String BankName = banknameTextField.getText();
        String AccountNo = accountnoTextField.getText();
        String AccountName = accountnameTextField.getText();
        String IfscCode = ifsccodeTextField.getText();

        String insertFields = "INSERT INTO Bank(BankName,AccountNo, AccountName, IFSCcode) VALUES ('";
        String insertValues = BankName + "','" + AccountNo + "','" + AccountName + "','" +  IfscCode  +"')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);
            okLabel.setText("Bank Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
