package com.interfacegrafica.com;

import javafx.application.Application;
import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.Event;

public class MainApp extends Application {

    private Stage stage;
    private Parent root;
    private Scene scene;
    private SerialComunication serialComunication;

    @Override
    public void start(Stage PrimaryStage) throws Exception {

        serialComunication = new SerialComunication();
        serialComunication.ReadValues();

        stage = PrimaryStage;
        root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        scene = new Scene(root);

        stage.setTitle("Automação residencial");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

            @Override
            public void handle(WindowEvent event) {
                // TODO Auto-generated method stub
                event.consume();

                stage.close();
                System.exit(0);
            }

        });

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
