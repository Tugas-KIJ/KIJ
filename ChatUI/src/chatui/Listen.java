/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import chatui.ChatClient;
import javax.swing.JTextArea;
/**
 *
 * @author SONY VAIO
 */
public class Listen extends Thread {
 
    private JTextArea ta_inbox;
public void run() {
    while (true) {
        try {
            String msg = null;
            String res;
            String type = msg.split("~")[0];
            String pengirim = msg.split("~")[1];
            String text = msg.split("~")[2];
            String kepada = msg.split("~")[3];
            switch (type) {
                case "recieveText":
                    res = pengirim + ": " + text;
                    ta_inbox.setText(ta_inbox.getText() + res + "\n");
                    break;
                case "recievePrivateText":
                    res = pengirim + ": " + text;
                    if (kepada.equals(name)) {
                        ta_inbox.setText(ta_inbox.getText() + res + "\n");
                    }
                    break;
                case "login":
                    ta_inbox.setText(ta_inbox.getText() + pengirim + " sedah login..." + "\n");
                    clients.add(pengirim);
                    break;
                case "logout":
                    ta_inbox.setText(ta_inbox.getText() + pengirim + " telah logout..." + "\n");
                    clients.remove(pengirim);
                    break;
                case "list":
                    setTable(text);
                    break;
            }
        } 
        catch (IOException e) {
            System.out.println("Server has close the connection: " + e);
            break;
        } 
        catch (ClassNotFoundException e2) {
        }
    }
}

private void setTable(String text) {
    int rows = text.split(":").length - 1;
    Object[][] data = new Object[rows][1];
    for (int i = 0; i < rows; i++) {
        String t = text.split("split(":")[i];
        data[i][0] = t;
    }
    String[] header = {"Clients"};
    clientTable.setModel(new DefaultTableModel(data, header));
}
    
}