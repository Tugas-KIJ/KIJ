/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;


/**
 *
 * @author SONY VAIO
 */
public class Userr extends Thread  {
    private JList list;
    Socket cli;
    public Userr(Socket cli){
      //  this.list=list;
        this.cli=cli;
    }
    
    public void run() {
        InputStream istream = null; 
        try {
            BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in)); // sending to client (pwrite object) 
            OutputStream ostream = cli.getOutputStream();
            PrintWriter pwrite;   
            pwrite = new PrintWriter(ostream, true);
            while(true)
            {
                pwrite.println("$"); // sending to server
                pwrite.flush(); // flush the data
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Userr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                istream.close();
            } catch (IOException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
