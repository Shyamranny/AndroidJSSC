package com.shyam.androidjssc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final String PORT_NAME = "/dev/ttyS2";
    private static final int BAUD_RATE = 57600;

    /**
     * The port.
     */
    private jssc.SerialPort serialPort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openSerialPort();

        write();

    }

    @Override
    protected void onDestroy() {

        try {
            if (serialPort != null) {
                synchronized (this) {
                    serialPort.removeEventListener();
                    serialPort.closePort();
                    serialPort = null;
                    this.notify();
                }

                Log.i(TAG, "Serial port '" + PORT_NAME + "' closed.");
            }
        } catch (Exception e) {
            Log.w(TAG, "Error closing serial port: '" + PORT_NAME + "'", e);
        }

        super.onDestroy();
    }

    /**
     * Write a sample string to serial port.
     */
    private void write() {
        if (null != serialPort) {
            try {
                serialPort.writeBytes("Hello".getBytes());
            } catch (SerialPortException e) {
                Log.e(TAG, "Exception while writing", e);
            }
        }
    }

    /**
     * Open serial port.
     */
    private void openSerialPort() {

        serialPort = new jssc.SerialPort(PORT_NAME);

        try {
            serialPort.openPort();
            serialPort.setParams(BAUD_RATE, 8, 1, 0);
            serialPort.setFlowControlMode(jssc.SerialPort.FLOWCONTROL_XONXOFF_OUT);
            serialPort.addEventListener(mSerialPortEventListener);

        } catch (SerialPortException e) {
            Log.e(TAG, "Unable to open port", e);
        }
    }

    /**
     * Sample serial port listener.
     */
    private SerialPortEventListener mSerialPortEventListener = new SerialPortEventListener() {
        @Override
        public void serialEvent(final SerialPortEvent serialPortEvent) {

        }
    };
}
