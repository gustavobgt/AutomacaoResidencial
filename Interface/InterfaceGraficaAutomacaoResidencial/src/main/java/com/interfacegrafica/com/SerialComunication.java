package com.interfacegrafica.com;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.OutputStream;
import java.io.IOException;

public class SerialComunication {
    private OutputStream serialOut;
    private int bitRate;
    private String port;
    private CommPortIdentifier portId;
    
    public SerialComunication(){
        
    }

}