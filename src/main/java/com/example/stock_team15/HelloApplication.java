package com.example.stock_team15;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 665, 476);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 665, 476));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}