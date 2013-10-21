package fr.laurentbillon.pigrow.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.util.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SerialService {

    GPIOService gPIOService;

    public abstract void close();
}
