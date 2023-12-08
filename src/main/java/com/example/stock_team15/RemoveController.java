package com.example.stock_team15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RemoveController {

    @FXML
    private Button removeButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField companyidTextField;

    @FXML
    private Label removeLabel;

    public void removeButtonOnAction(ActionEvent event) {
        if (!companyidTextField.getText().isEmpty()) {
            removeStock();
        } else {
            removeLabel.setText("Please enter a company ID");
        }
    }

    public void backButtonOnAction(ActionEvent event){
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
}

    public void removeStock() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String companyId = companyidTextField.getText();

        // Use DELETE statement to remove the stock with the specified CompanyId
        String deleteSql = "DELETE FROM mystocks WHERE CompanyId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, companyId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                removeLabel.setText("Stock Removed Successfully");
            } else {
                removeLabel.setText("No stock found with the specified Company ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
            removeLabel.setText("Error removing stock. Check the console for details.");
        }
    }

}
