/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import chatui.ChatClient;
import static chatui.ChatClient.XOR;
import static chatui.ChatClient.byteArrayToHexString;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
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
    Socket servKey;
    private JTextField too;
    BufferedReader readerKey;
    ArrayList<String> pubKey;
    PrintWriter pwriter;
    String ownerSecret;
    MessageDigest md = null;
    int p=353;
    public Listen(JTextArea ta, Socket cli, JList li_user, JTextField to,Socket servKey,BufferedReader reader,PrintWriter pwriter,ArrayList<String> pubkey,String secKey) throws NoSuchAlgorithmException{
        this.ta_inbox=ta;
        this.cli=cli;
        this.li_user=li_user;
        this.too=to;
        this.servKey=servKey;
        this.readerKey=reader;
        this.pubKey=pubkey;
        this.pwriter=pwriter;
        this.ownerSecret=secKey;
        md = MessageDigest.getInstance("SHA-1");
    }
     public static  int getKey(int pubKey,int secKey,int p)
    {
        BigInteger key= BigInteger.valueOf(pubKey).pow(secKey).mod(BigInteger.valueOf(p));
        return key.intValue();
    }
     
     public static String keystringbinary(String a){
    byte[] infoBin;
    String g = "";
        infoBin = a.getBytes();
        for (byte b : infoBin) {
            int o = Integer.toBinaryString(b).length();
            if( o == 7)
                g += "0" + Integer.toBinaryString(b);
            else if( o == 8)
                g += Integer.toBinaryString(b);
            else if( o == 6)
                g += "00" + Integer.toBinaryString(b);
            else if( o == 5)
                g += "000" + Integer.toBinaryString(b);
        }
        //System.out.println(g);
        return g;
    }
    
    public static String XOR(String a, String b, int panjang){
        String g="";
        System.out.println(a);
        System.out.println(b);
       // System.out.println(a.length());
        for(int i = 0; i<a.length();i++){
            if(a.charAt(i)== b.charAt(i)){
                g += "0";
            }
            else{
                g += "1";
            }
        }
        return g;
    }
    
    private String binToString (String a )
    {
        String hasil = "";
        int z = a.length()/8;
        for (int x = 0; x < z ; x = x+1)
        {
             String temp = a.substring((x*8),((x+1)*8));
             int charCode = Integer.parseInt(temp, 2);
             String str = new Character((char)charCode).toString();
             //System.out.println("str = " + str);
             hasil += str;
             
             
        }
        System.out.println("hasil = " + hasil);
        return hasil;
    }
     
     private String count2(String pesan, String key)
    {
        System.out.println("---------------");
        String counter = "akuwahyu";
        //String key = "akuhafiz";
        //String kk = "";
        //kk += keystringbinary(pesan);
        
        
        String cipher_text = "";
        String lop ="";
        
        DES des = new DES();
        //panggil difhel
        //panggil encrypt 
        String encript;
        //String cipher_text;
        String cipher;
        int panjang = pesan.length()/8;
        int banyak_counter = panjang /8;
        //System.out.println(banyak_counter);
        for (int x = 0; x < banyak_counter; x = x+1)
        {
            
             counter = counter + x;
             
            String hasil_enkrip = des.Enkripsi(key, counter);
            //System.out.println(hasil_enkrip);
    //         Integer.toBinaryString(counter);
             //key di enkrip
             
             // plain text 
             String temp = pesan.substring((x*64),((x+1)*64));
           //  System.out.println(temp);
            // String temppesan = keystringbinary(temp);
             //System.out.println(temppesan);
             //System.out.println("");
             
             
             String xor = XOR(temp,hasil_enkrip,64);
             //String xor2 = XOR(temppesan,hasil_enkrip,64);
            /* //System.out.println(xor);
             byte[] infoBin;
            //String g = "";
             infoBin = xor.getBytes();
             for (byte b : infoBin) 
             {
                cipher_text += Integer.toBinaryString(b);
             }*/
            //System.out.println("tes");
            cipher_text += xor;
            //lop += kk;
             //cipher_text = cipher_text + ;
           // System.out.println(cipher_text);
       
        }
        String aaaa= "";
       // String bbbb="";
         //cipher_text = 
        aaaa += binToString(cipher_text);
       // bbbb += binToString(lop);
        // System.out.println(cipher_text);
        //============================================================= decrypt
        System.out.println(aaaa);
        return aaaa;
    }
     
     public static String byteArrayToHexString(byte[] b) {
      String result = "";
      for (int i=0; i < b.length; i++) {
        result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
      }
      return result;
    }
     
public void run() {
        String ddd = null;
        String pesann;
        
        InputStream istream = null; 
        try {
            istream = cli.getInputStream();
            BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
            String receiveMessage;
            while((receiveMessage = receiveRead.readLine()) != null)
            {
                
               if(receiveMessage.startsWith(";")==false)
               {
                   String[] sender=receiveMessage.split(": ");
                   pesann = new String(sender[0]);
                   if(sender[0].length()>3 && sender[0].length()<9)
                   {
                     pwriter.println("get:"+sender[0]);
                     String getKey=readerKey.readLine();
                     String key=getKey;
                    
                    int DHkey=getKey(Integer.parseInt( key), Integer.parseInt(ownerSecret), 353);
                    System.out.println(sender[1]);
                    ddd= new String( Integer.toString(DHkey));
                    int dddd = ddd.length();
                    if(dddd == 2)
                    {
                        ddd += ddd + ddd + ddd; 
                    }
                    else{
                        ddd += ddd + ddd.charAt(0) + ddd.charAt(1);
                    }
                    System.out.println("input: "+sender[1]);
                    pesann+=":";
                    pesann += count2(sender[1], ddd);
                    String[] pesan = pesann.split("~");
                    String[] msg = pesan[0].split(":");
                    String hash=byteArrayToHexString(md.digest(msg[1].getBytes()));
                    System.out.println("pesan: "+msg[1]);
                    if(hash.equals(msg[2]))
                    {
                        System.out.println("Verified");
                    }
                    pesann = new String(msg[0]+": "+msg[1]);
                   }
                   
                  
                   //String[] pesan2 = pesan[0].split("\r\n");
                   
                   
        //System.out.println(ddd);
        
                   
                   
                   
                   //String tmp=new String(count2(sender[1], ddd));
                  // String[] pesan22 = tmp.split("~");
                   //System.out.println("tmp"+tmp);
                   
                   
                   //System.out.println("ini" + tmp+ "ini");

                   //System.out.println("ini  == " + count2(tmp));
                   ta_inbox.append(pesann);
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
