package com.interfacegrafica.com;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;



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
            this.serialPort.setParams(9600, 8, 1, 0);

            String data = serialPort.readString();

            System.out.println(data);

            serialPort.writeInt(outputValue);
                
            Constants.setValues(data);

            outputValue = 0;


        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("PORTA NÃO ENCONTRADA");
        }

    }

    public void sendValues(int value){
        outputValue = value;

    }

    public String getPort(){
        return this.serialPort.getPortName();
    }
    
    /*private class PortReader implements SerialPortEventListener {

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



    }*/


}
