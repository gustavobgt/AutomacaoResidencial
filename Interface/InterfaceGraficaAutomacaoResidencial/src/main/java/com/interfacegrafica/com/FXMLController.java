package com.interfacegrafica.com;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


public class FXMLController implements Initializable {
    
    private boolean btnRelay1Pressed = false;
    private boolean btnRelay2Pressed = false;
    private boolean btnRelay3Pressed = false;

    Image imgOFF = new Image("/Images/Botão Desligado.png");
    Image imgON = new Image("/Images/Botão Ligado.png");
        
    @FXML
    private ImageView imgBtnRelay1;
    @FXML
    private ImageView imgBtnRelay2;
    @FXML
    private ImageView imgBtnRelay3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnRelay1(ActionEvent Event){
      System.out.println("BOTAO PRESSIONADO");

      if(btnRelay1Pressed){
        imgBtnRelay1.setImage(imgOFF);
        btnRelay1Pressed = false;
      }else{

        btnRelay1Pressed = true;
        imgBtnRelay1.setImage(imgON);

      }
      
    }

    @FXML
    private void btnRelay2(ActionEvent Event){
      System.out.println("BOTAO PRESSIONADO");

      if(btnRelay2Pressed){
         imgBtnRelay2.setImage(imgOFF);
         btnRelay2Pressed = false;
      }else{
        btnRelay2Pressed = true;
        imgBtnRelay2.setImage(imgON);

      }
      
    }

    @FXML
    private void btnRelay3(ActionEvent Event){
      System.out.println("BOTAO PRESSIONADO");

      if(btnRelay3Pressed){
        imgBtnRelay3.setImage(imgOFF);
        btnRelay3Pressed = false;
      }else{
        btnRelay3Pressed = true;
        imgBtnRelay3.setImage(imgON);

      }
      
    }



    
}
