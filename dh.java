/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dh;

import java.math.BigInteger;
import sun.security.util.BigInt;

/**
 *
 * @author Administrator
 */
public class DH {

    /**
     * @param args the command line arguments
     */
    static int p= 353;
    static int g= 3;
    public static int pubKey(int key,int g,int p)
    {
        BigInteger pub= BigInteger.valueOf(g).pow(key).mod(BigInteger.valueOf(p));
        return pub.intValue();
    }
    public static  int getKey(int pubKey,int secKey,int p)
    {
        BigInteger key= BigInteger.valueOf(pubKey).pow(secKey).mod(BigInteger.valueOf(p));
        return key.intValue();
    }
    public static void main(String[] args) {
        int alicePrivate =98;
        int bobPrivate=99;
        int alicePublic=pubKey(alicePrivate,g,p);
        int bobPublic=pubKey(bobPrivate,g,p);
        int aliceKey=getKey(bobPublic, alicePrivate, p);
        int bobKey=getKey(alicePublic,bobPrivate,p);
        System.out.println("pubKey: " +alicePublic + " " + bobPublic);
        System.out.println("key: " +aliceKey + " " + bobKey);
    }
    
}
