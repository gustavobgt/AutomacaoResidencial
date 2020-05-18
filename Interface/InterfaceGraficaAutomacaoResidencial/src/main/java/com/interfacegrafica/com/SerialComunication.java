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
import java.io.BufferedWriter;
import java.io.IOException;


public class SerialComunication {
    private String port;
    private String[] serialPorts;
    private SerialPort serialPort;
    
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
    
            serialPort.writeByte((byte) 0x01);

            byte[] buffer = serialPort.readBytes(16);
            String data = new String(buffer);

            System.out.println(data);

            //FXMLController layoutController = new FXMLController(); 
            //layoutController.setValues(Temperature, Humidity);

            Constants.setValues(data);

            this.serialPort.closePort();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("PORTA NÃO ENCONTRADA");
        }

    }

    public void sendValues(){
        try {
            this.serialPort.openPort();
            this.serialPort.setParams(
                SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
    
            serialPort.writeByte("a".getBytes()[0]);

            this.serialPort.closePort();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("PORTA NÃO ENCONTRADA");
        }

    }

}