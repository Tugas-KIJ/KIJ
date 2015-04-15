/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * 
 * @author evaria
 */
public class ChatClient extends javax.swing.JFrame {

    /**
     * Creates new form ChatClient
     */
    
    private BufferedReader keyRead, receiveRead;
    private OutputStream ostream;
    private PrintWriter pwrite;
    private InputStream istream; 
    private Socket socket;
    private String server, name;
    private int port;
    private List<String> clients;
    
    public ChatClient() {
        clients = new ArrayList();
        
        initComponents();
        
        
        
  /*      l_kontak.addListSelectionListener(new );
        l_kontak.addListSelectionListener(new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()){
            JList source = (JList)event.getSource();
            String selected = source.getSelectedValue().toString();
        }
    }
});*/
    }
    
    public static String keystringbinary(String a){
    byte[] infoBin;
    String g = "";
        infoBin = a.getBytes();
        for (byte b : infoBin) {
            g += "0" + Integer.toBinaryString(b);
        }
        //System.out.println(g);
        return g;
    }
    
    public static String XOR(String a, String b, int panjang){
        String g="";
       // System.out.println(a.length());
        for(int i = 0; i<panjang;i++){
            if(a.charAt(i)== b.charAt(i)){
                g += "0";
            }
            else{
                g += "1";
            }
        }
        return g;
    }
    
    private void count1(String pesan)
    {
        String counter = "akuwahyu";
        String key = "akuhafiz";
        
        
        DES des = new DES();
        String hasil_enkrip = des.Enkripsi(key, counter);
        System.out.println(hasil_enkrip);
        //panggil difhel
        //panggil encrypt 
        String encript;
        String convxor;
        String cipher;
        int panjang = pesan.length();
        int banyak_counter = panjang /8;
        for (int x = 0; x < banyak_counter; x = x+1)
        {
            
             counter = counter + x;
    //         Integer.toBinaryString(counter);
             //key di enkrip
             
             // plain text 
             String temp = pesan.substring((x*8),((x+1)*8)-1);
        //     convxor = XOR(temp,encript);
        }
    }
    
    public boolean start() {
        try {
            socket = new Socket(server, port);
        } catch (Exception ec) {
            System.out.println("Error connectiong to server:" + ec);
            return false;
        }
        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        System.out.println(msg);

        
        try {
            Listen l = new Listen(ta_inbox, socket, l_kontak, t_to);
            l.start();
            
            
            
            keyRead = new BufferedReader(new InputStreamReader(System.in)); // sending to client (pwrite object) 
            ostream = socket.getOutputStream();
            pwrite = new PrintWriter(ostream, true);   // receiving from server ( receiveRead object) 
            istream = socket.getInputStream(); 
            receiveRead = new BufferedReader(new InputStreamReader(istream));
            
            
         
        } catch (IOException eIO) {
            System.out.println("Exception creating new Input/outputStreams: " + eIO);
            return false;
        }
        
        //new ChatClient.Listen().start();
        
        return true;
    }
    
    public boolean disconnect() {
        try {
            if (istream != null) {
                istream.close();
            }
        } 
        catch (Exception e) {
        return false;
        } 
        try { 
            if (ostream != null) {
                ostream.close();
            }
        } 
        catch (Exception e) {
            return false;
        } 
        try { 
            if (socket != null) {
                socket.close();
            }
        } 
        catch (Exception e) {
            return false;
        }
        return true;
    }
    
     public String CheckLenPlain(String plain){
        int i = 0;
         if(plain.length()%8!=0) {
            int len=8-plain.length()%8;
            for(; i<len; i++)
                plain=plain.concat(";");
        } else {
            return plain;
        }
        return plain;
    }
     
    public static void warningBox(String warningMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, warningMessage, titleBar, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_masuk = new javax.swing.JButton();
        b_keluar = new javax.swing.JButton();
        t_server = new javax.swing.JTextField();
        t_port = new javax.swing.JTextField();
        t_nama = new javax.swing.JTextField();
        t_pesan = new javax.swing.JTextField();
        b_kirim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_inbox = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        l_kontak = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        t_to = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Server");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Port");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nama");

        b_masuk.setText("Masuk");
        b_masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_masukActionPerformed(evt);
            }
        });

        b_keluar.setText("Keluar");
        b_keluar.setEnabled(false);
        b_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_keluarActionPerformed(evt);
            }
        });

        t_server.setText("10.151.36.26");

        t_port.setText("9000");

        t_nama.setText("WAHYU");
        t_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_namaActionPerformed(evt);
            }
        });

        t_pesan.setEditable(false);

        b_kirim.setText("Kirim");
        b_kirim.setEnabled(false);
        b_kirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_kirimActionPerformed(evt);
            }
        });

        ta_inbox.setEditable(false);
        ta_inbox.setColumns(20);
        ta_inbox.setRows(5);
        jScrollPane1.setViewportView(ta_inbox);

        jScrollPane2.setViewportView(l_kontak);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TO");

        t_to.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("PRIVATE KEY");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("PUBLIC KEY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(t_server, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(t_port, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(t_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b_masuk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(b_keluar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(t_to, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(t_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(b_kirim))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(t_server, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(t_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_masuk)
                    .addComponent(b_keluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_kirim)
                    .addComponent(jLabel1)
                    .addComponent(t_to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void t_namaActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
    }                                      
    
    
    
    private void b_masukActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        this.server = t_server.getText();
        this.port = new Integer (t_port.getText());
        this.name = t_nama.getText();
        if (start())
        {
            t_server.setEditable(false);
            t_port.setEditable(false);
            t_nama.setEditable(false);
            t_pesan.setEditable(true);
            b_kirim.setEnabled(true);
            b_masuk.setEnabled(false);
            b_keluar.setEnabled(true);
        }
        
        
        pwrite.println(name); // sending to server
        System.out.println(name);
        pwrite.flush(); // flush the data 
       
        Userr li = new Userr(socket);
        li.start();
    }                                       


    
    private void b_kirimActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
        if(t_pesan.getText().isEmpty() || t_to.getText().isEmpty())
        {
            warningBox("Tujuan atau Isi Pesan Tidak Boleh Kosong!", "WARNING");
            return;
        }
        if(t_pesan.getText().endsWith(";"))
        {
            warningBox("Isi Pesan Tidak Boleh Mengandung Karakter ';' !", "WARNING");
            return;
        }
        
        String ui = CheckLenPlain(t_pesan.getText());
        
        
        
        String message = ":" + t_to.getText() + ":" + ui;
        
        
        //System.out.println(message);
        pwrite.println(message);
        //message="$";
        //pwrite.println(t_pesan.getText());
        pwrite.flush();
        
        try {
            ostream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        t_pesan.setText("");
    }                                       

    private void b_keluarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (disconnect())
        {
            t_server.setEditable(true);
            t_port.setEditable(true);
            t_nama.setEditable(true);
            t_pesan.setEditable(false);
            b_kirim.setEnabled(false);
            b_masuk.setEnabled(true);
            b_keluar.setEnabled(false);
        }
    }                                        

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton b_keluar;
    private javax.swing.JButton b_kirim;
    private javax.swing.JButton b_masuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JList l_kontak;
    private javax.swing.JTextField t_nama;
    private javax.swing.JTextField t_pesan;
    private javax.swing.JTextField t_port;
    private javax.swing.JTextField t_server;
    private javax.swing.JTextField t_to;
    private javax.swing.JTextArea ta_inbox;
    // End of variables declaration                   
}

