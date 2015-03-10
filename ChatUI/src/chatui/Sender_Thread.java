/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Hafiz Djufri
 */
public class Sender_Thread extends Thread
{
    private PrintWriter msgOut;

    public Sender_Thread(PrintWriter aOut)
    {
            msgOut = aOut;
    }

        
    @Override
    public void run()
    {
    try {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    while (!isInterrupted()) {
    String message = in.readLine();
    msgOut.println(message);
    msgOut.flush();
    }
    } catch (IOException ioe) {
                
    }
    }     
}
