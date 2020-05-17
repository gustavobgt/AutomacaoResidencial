package com.interfacegrafica.com;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MainApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        stage = PrimaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        //Image img = new Image("/Images/Background.jpg");
        //ImageView background = new ImageView(img);
        
        Scene scene = new Scene(root);

        stage.setTitle("Automação residencial");
        stage.setScene(scene);
        stage.show();

        new Service<Integer>(){
            SerialComunication serialComunication = new SerialComunication();
			@Override
			protected Task<Integer> createTask() {
                // TODO Auto-generated method stub
                
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        while(true){
                            
                            serialComunication.VerifyPort();
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
