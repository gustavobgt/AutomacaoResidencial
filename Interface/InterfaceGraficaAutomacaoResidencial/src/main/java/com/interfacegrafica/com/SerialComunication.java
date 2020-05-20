package com.interfacegrafica.com;

/*import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;*/
import jssc.SerialPortList;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import static jssc.SerialPort.MASK_RXCHAR;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import jssc.SerialPortEventListener;

import java.io.BufferedWriter;
import java.io.IOException;


public class SerialComunication {
    private String port;
    private String[] serialPorts;
    private SerialPort serialPort;
    private static int outputValue = 0;
    
    public SerialComunication(){
        this.serialPorts = SerialPortList.getPortNames();
        this.port = this.serialPorts[0];
        System.out.println(this.serialPorts[0]);
        this.serialPort = new SerialPort(this.port);
    }

    public void ReadValues(){
        try {
            
            this.serialPort.openPort();
            this.serialPort.setParams(
                SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("PORTA NÃO ENCONTRADA");
        }

    }

    public void sendValues(int value){
        outputValue = value;

    }
    
    private class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            // TODO Auto-generated method stub
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try{
                    
                    //byte[] buffer = serialPort.readBytes(16);
                    String data = serialPort.readString();

                    System.out.println(data);

                    serialPort.writeInt(outputValue);
                
                    Constants.setValues(data);

                    outputValue = 0;


                }catch(SerialPortException e){
                    e.printStackTrace();
                }
            }


        }



    }


}
