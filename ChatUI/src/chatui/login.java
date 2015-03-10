/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SONY VAIO
 */
public class login {
    
    private Connection connection;
    private Statement stt;
    private ResultSet rstt;
    private String query;
    
    private String t_ip;
    private String t_port;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String passwd;

    public String getT_ip() {
        return t_ip;
    }

    public void setT_ip(String t_ip) {
        this.t_ip = t_ip;
    }

    public String getT_port() {
        return t_port;
    }

    public void setT_port(String t_port) {
        this.t_port = t_port;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
}


