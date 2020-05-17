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

    public void VerifyPort(){
        try {
            this.serialPort.openPort();
            this.serialPort.setParams(
                SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
    
            byte[] buffer = serialPort.readBytes(8);
            System.out.println("Humidity: " + new String(buffer));
            buffer = serialPort.readBytes(4);
            System.out.println("Temperature " + new String(buffer));
            this.serialPort.closePort();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("PORTA NÃO ENCONTRADA");
        }
    }
}