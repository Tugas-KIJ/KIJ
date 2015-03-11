/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import chatui.ChatClient;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JList;
/**
 *
 * @author SONY VAIO
 */
public class Listen extends Thread {
 
    private JTextArea ta_inbox;
    private JList li_user;
    Socket cli;
    public Listen(JTextArea ta, Socket cli, JList li_user){
        this.ta_inbox=ta;
        this.cli=cli;
        this.li_user=li_user;
    }
public void run() {
        InputStream istream = null; 
        try {
            istream = cli.getInputStream();
            BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
            String receiveMessage;
            while((receiveMessage = receiveRead.readLine()) != null)
            {
               if(receiveMessage.startsWith(";")==false)
               {
                    ta_inbox.append(receiveMessage);
                     ta_inbox.append("\n");
               }
               else
               {
                   
                  // String type = msg.split("~")[0];
                   String type = receiveMessage.split(";")[0];
                    DefaultListModel tes = new DefaultListModel();
                    tes.addElement(receiveMessage.split(";")[1]);
                    tes.addElement(receiveMessage.split(";")[2]);
                    tes.addElement(receiveMessage.split(";")[3]);
                    
                    li_user.setModel(tes);   //memasukkan items tes ke JList lstTes.

        
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
