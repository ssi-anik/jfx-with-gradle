package com.gradle.with.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Label label = new Label("Hello world");
        Scene scene = new Scene(label);
        primaryStage.setScene(scene);
        primaryStage.show();*/
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World for JDK + JFX 11");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
