/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfacegrafica;


import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.SourceDataLine;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialComunication implements SerialPortEventListener{
    private String port;
    private String[] serialPorts;
    private SerialPort serialPort;
    private static int outputValue = 0;
    private CommPortIdentifier portId;
    private OutputStream serialOut;
    private BufferedReader serialIn;
   private SerialPortEvent event;

    public SerialComunication() {

        try {
            this.serialPorts = SerialPortList.getPortNames();
            this.port = this.serialPorts[0];
            this.portId = CommPortIdentifier.getPortIdentifier(this.port);
            this.serialPort = (SerialPort) this.portId.open("Comunicacao Serial", 9600);
            this.serialOut = this.serialPort.getOutputStream();
            this.serialPort.setSerialPortParams(9600, 8, 1, 0);
            this.serialIn = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            Constants.setPort(this.port);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ReadValues() {
        try {
            this.serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*String responseHumidity;
        String responseTemperature;
        try {
            responseHumidity = this.serialIn.readLine();
            System.out.println(responseHumidity);
            responseTemperature = this.serialIn.readLine();
            System.out.println(responseTemperature);
            
            Constants.setHumudity(responseHumidity);
            Constants.setTemperature(responseTemperature);

            //Constants.setValues(response);
        } catch (Exception e) {
            this.serialPort.close();
            this.port = "ARDUINO NÃO ENCONTRADO";
        }*/
    


    }

    public void sendValues(int value) {
        try {

            this.serialOut.write(value);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ;

    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        // TODO Auto-generated method stub

       try{
            if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                String responseHumidity;
                String responseTemperature;
                try {
                    responseHumidity = this.serialIn.readLine();
                    System.out.println(responseHumidity);
                    responseTemperature = this.serialIn.readLine();
                    System.out.println(responseTemperature);
                    
                    Constants.setHumudity(responseHumidity);
                    Constants.setTemperature(responseTemperature);



                    //Constants.setValues(response);
                } catch (Exception e) {
                    this.serialPort.close();
                    this.port = "ARDUINO NÃO ENCONTRADO";
                    
                }
            }
        }catch(Exception e){
        }
    }


}
