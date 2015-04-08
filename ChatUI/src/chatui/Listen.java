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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
/**
 *
 * @author SONY VAIO
 */
public class Listen extends Thread {
 
    private JTextArea ta_inbox;
    private JList li_user;
    Socket cli;
    private JTextField too;
    private JTable tabel;
    public Listen(JTextArea ta, Socket cli, JList li_user, JTextField to, JTable tabel){
        this.ta_inbox=ta;
        this.cli=cli;
        this.li_user=li_user;
        this.too=to;
        this.tabel=tabel;
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
                   String[] pesan = receiveMessage.split(";");
                    ta_inbox.append(pesan[0]);
                     ta_inbox.append("\n");
               }
               else
               {
                   DefaultListModel tes = new DefaultListModel();
                   String[] pecah1 = receiveMessage.split(";");
                    for(int counter = 1; counter < pecah1.length; counter++){
                       tes.addElement(pecah1[counter]);
                    }
                 
                    li_user.setModel(tes);   //memasukkan items tes ke JList lstTes.
                    
                    tabel.setModel((TableModel) tes);
                    
                    li_user.addListSelectionListener(new ListSelectionListener() 
                        { 
                            @Override 
                            public void valueChanged(ListSelectionEvent arg0) 
                            { 
                                if (!arg0.getValueIsAdjusting()) 
                                { 
                                    if(li_user.getSelectedValue()!=null){
                                        String selected=new String(li_user.getSelectedValue().toString());
                                        too.setText(selected);
                                    }
                                } 
                            } 
                        }
                    );
                    
                   
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
