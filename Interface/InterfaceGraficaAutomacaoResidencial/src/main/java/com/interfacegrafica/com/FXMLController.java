package com.interfacegrafica.com;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import jssc.SerialPortException;

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
  @FXML
  private Label lblHumidity;
  @FXML
  private Label lblTemperature;
  @FXML
  private Label lblPort;

  SerialComunication serialComunication;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    try{
      serialComunication = new SerialComunication();


    }catch(Exception e){
      System.out.println("ERRO AQUI");
    }

    //lblHumidity.setText(Constants.getHumidity());
    //lblTemperature.setText(Constants.getTemperature());


            
    /*EventHandler handler = new EventHandler(){
    @Override
    public void handle(Event event) {
    // TODO Auto-generated method stub
        serialComunication.ReadValues();
        UpdateValues();
      }

      };

    KeyFrame frame = new KeyFrame(Duration.millis(1000), handler);
    Timeline timeline = new Timeline();
    timeline.getKeyFrames().add(frame);
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();*/


  }

  @FXML
  private void Init(ActionEvent Event) throws SerialPortException {
    try{
      serialComunication.ReadValues();
      UpdateValues();
    }catch(Exception e){
      System.out.println("ERRO AQUI");
    }

  }


  @FXML
  private void btnRelay1(ActionEvent Event) {
    System.out.println("BOTAO PRESSIONADO");

    if (btnRelay1Pressed) {
      imgBtnRelay1.setImage(imgOFF);
      btnRelay1Pressed = false;
      serialComunication.sendValues(4);
    } else {
      btnRelay1Pressed = true;
      imgBtnRelay1.setImage(imgON);
      serialComunication.sendValues(1);

    }


  }

  @FXML
  private void btnRelay2(ActionEvent Event) {
    System.out.println("BOTAO PRESSIONADO");

    if (btnRelay2Pressed) {
      imgBtnRelay2.setImage(imgOFF);
      btnRelay2Pressed = false;
      serialComunication.sendValues(5);
    } else {
      btnRelay2Pressed = true;
      imgBtnRelay2.setImage(imgON);
      serialComunication.sendValues(2);
    }


  }

  @FXML
  private void btnRelay3(ActionEvent Event) {
    System.out.println("BOTAO PRESSIONADO");

    if (btnRelay3Pressed) {
      imgBtnRelay3.setImage(imgOFF);
      btnRelay3Pressed = false;
      serialComunication.sendValues(6);
    } else {
      btnRelay3Pressed = true;
      imgBtnRelay3.setImage(imgON);
      serialComunication.sendValues(3);

    }

  }

  public void UpdateValues(){
    lblTemperature.setText(Constants.getTemperature());
    lblHumidity.setText(Constants.getHumidity());
    lblPort.setText(Constants.getPort());
  }

}
