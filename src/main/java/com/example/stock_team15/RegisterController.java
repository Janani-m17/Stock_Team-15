package com.example.stock_team15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    private ImageView shieldImageView;

    @FXML
    private Label registerationMessageLabel;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField emailidTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private DatePicker dobTextField;

    /*public void initialize(URL url, ResourceBundle resourceBundle){
        File shieldFile = new File("..\\..\\..\\..\\..\\Downloads\\stockb.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shieldImageView.setImage(shieldImage);
    }*/

    public void registerButtonOnAction(ActionEvent event){
        //registerationMessageLabel.setText("Registered Successfully");

        if(setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("");


        }else{
            confirmPasswordLabel.setText("Password doesn't match");
        }

    }

    public void registerUser() {
        DatabaseConnection ConnectNow = new DatabaseConnection();
        Connection connection = ConnectNow.getConnection();

        String Username = usernameTextField.getText();
        String Email_ID = emailidTextField.getText();
        String Password = setPasswordField.getText();
        String Phone = phoneTextField.getText();
        String Dob = dobTextField.getValue().toString();

        String insertFields = "INSERT INTO User(UserName,MailId, Password, PhoneNo, D_O_B) VALUES ('";
        String insertValues = Username + "','" + Email_ID + "','" + Password + "','" +  Phone + "','" + Dob +"')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement =  connection.createStatement();
            statement.executeUpdate(insertToRegister);
            registerationMessageLabel.setText("Registered Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



    }




