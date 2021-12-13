package com.gradle.with.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        /*Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));*/
        primaryStage.setTitle("Hello World for JDK + JFX 11");
        primaryStage.setScene(new Scene(new StackPane(new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".")), 300, 275));
        primaryStage.show();
    }
}
