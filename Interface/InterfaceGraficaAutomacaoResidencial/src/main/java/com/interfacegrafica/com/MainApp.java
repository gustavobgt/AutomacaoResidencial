package com.interfacegrafica.com;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.concurrent.*;

public class MainApp extends Application {

    private Stage stage;
    private Parent root;
    private Scene scene;
    
    @Override
    public void start(Stage PrimaryStage) throws Exception {

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

        new Service<Integer>() {
			@Override
			protected Task<Integer> createTask() {
                // TODO Auto-generated method stub
            	return new Task<Integer>(){

					@Override
					protected Integer call() throws Exception {
                        // TODO Auto-generated method stub
                        SerialComunication connection = new SerialComunication();
                        while(true){
                            connection.ReadValues();
                        }
					}
                };
            }
        }.start();

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
