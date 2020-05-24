package com.interfacegrafica.com;

import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;
import java.util.concurrent.TimeUnit;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialComunication {
    private String port;
    private String[] serialPorts;
    private SerialPort serialPort;
    private static int outputValue = 0;
    private CommPortIdentifier portId;
    private OutputStream serialOut;
    private InputStream serialIn;

    public SerialComunication() {
        this.serialPorts = SerialPortList.getPortNames();
        this.port = this.serialPorts[0];

        try {
            this.portId = CommPortIdentifier.getPortIdentifier(this.port);
            this.serialPort = (SerialPort) this.portId.open("Comunicacao Serial", 9600);
            this.serialOut = this.serialPort.getOutputStream();
            this.serialPort.setSerialPortParams(9600, 8, 1, 0);
            this.serialIn = serialPort.getInputStream();

        } catch (NoSuchPortException | PortInUseException | IOException | UnsupportedCommOperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ReadValues() {
        try {
            /*String result = "";
            byte[] buffer = new byte[16];
            int bytes = serialIn.read(buffer);
            result = new String(buffer);*/
                    /*try {
                       int numBytes = 0;
                        while (serialIn.available() > 0) {
                            numBytes = serialIn.read(readBuffer);
                        }
                        result  = new String(readBuffer);
                                        result = result.substring(0, numBytes);
                                        int x = 0;
                        System.out.println(result+"\n");
        
                    } catch (IOException e) {}
                    */

            //System.out.println(result);
            this.serialOut.write(outputValue);

        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void sendValues(int value){
        outputValue = value;

    }

    public String getPort(){
        return this.port;
    }
    
    private class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            // TODO Auto-generated method stub
            switch (event.getEventType()) {
                case SerialPortEvent.BI:
                case SerialPortEvent.OE:
                case SerialPortEvent.FE:
                case SerialPortEvent.PE:
                case SerialPortEvent.CD:
                case SerialPortEvent.CTS:
                case SerialPortEvent.DSR:
                case SerialPortEvent.RI:
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    break;
                case SerialPortEvent.DATA_AVAILABLE:
                            try {
            TimeUnit.MILLISECONDS.sleep(500); // 
        } catch (InterruptedException ignored) {  
        }
                    byte[] readBuffer = new byte[1024];
                    try {
                       int numBytes = 0;
                        while (serialIn.available() > 0) {
                            numBytes = serialIn.read(readBuffer);
                        }
                        String result  = new String(readBuffer);
                        result = result.substring(0, numBytes);
                        int x = 0;
                        System.out.println(result+"\n");
        
                    } catch (IOException e) {}
        
                    break;
                }
            }
        
    }


}
