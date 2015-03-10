/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;
import chatui.ChatClient;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JTextArea;
/**
 *
 * @author evaria
 */
public class ChatListen extends Thread{
    private InputStream is;
    private OutputStream os;
}
