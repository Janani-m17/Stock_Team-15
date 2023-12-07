package com.example.stock_team15;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.net.URL;

public class HelloController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button loginButton;
    @FXML
    public Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("..\\..\\..\\..\\..\\Downloads\\stockb.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }
    public Button loginButtonOnAction(ActionEvent event){

        if(!username.getText().isBlank() && !password.getText().isBlank()){
            validateLogin();
        }
        else{
            loginMessageLabel.setText("Enter Valid Details");
        }
        return null;
    }

    public void signupOnAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((root));
        stage.setScene(scene);
        stage.show();
    }
    public void validateLogin(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM User WHERE UserName = '" + username.getText() + "' AND Password ='" + password.getText() +  "'";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageLabel.setText("You are Logged In");
                }else {
                    loginMessageLabel.setText("Invalid Login");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }



}