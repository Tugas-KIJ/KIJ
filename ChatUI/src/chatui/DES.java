/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatui;

/**
 *
 * @author Vancrew
 */
public class DES {
    
    public DES(){
        
    }
    
    private static int[] PC1 = {
        57, 49, 41, 33, 25, 17,  9,
         1, 58, 50, 42, 34, 26, 18,
        10,  2, 59, 51, 43, 35, 27,
        19, 11,  3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
         7, 62, 54, 46, 38, 30, 22,
        14,  6, 61, 53, 45, 37, 29,
        21, 13,  5, 28, 20, 12,  4
    };
    
    private static int[] Shift = {
        1, 1, 2, 2, 2, 2, 2, 2,
        1, 2, 2, 2, 2, 2, 2, 1
    };
    
    private static int[] PC2 = {
        14, 17, 11, 24,  1,  5,  3, 28,
        15,  6, 21, 10, 23, 19,	12,  4,	
        26,  8, 16,  7, 27, 20,	13,  2,
        41, 52,	31, 37, 47, 55, 30, 40,
        51, 45,	33, 48, 44, 49,	39, 56,	
        34, 53, 46, 42, 50, 36,	29, 32  
    };
    
    private static int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52,	44, 36,	28, 20,	12, 4,
        62, 54,	46, 38,	30, 22,	14, 6,
        64, 56,	48, 40,	32, 24,	16, 8,
        57, 49,	41, 33,	25, 17,	 9, 1,
        59, 51,	43, 35,	27, 19,	11, 3,
        61, 53,	45, 37,	29, 21,	13, 5,
        63, 55,	47, 39,	31, 23,	15, 7  
    };
    
    private static int[] Expansion = {
        32,  1,  2,  3,  4,  5,
         4,  5,	 6,  7,	 8,  9,
         8,  9,	10, 11,	12, 13,
        12, 13,	14, 15,	16, 17,
        16, 17,	18, 19,	20, 21,
        20, 21,	22, 23,	24, 25,
        24, 25,	26, 27,	28, 29,
        28, 29,	30, 31,	32,  1				
    };
    
    private static int[] P = {
        16,  7,	20, 21,
        29, 12,	28, 17,
         1, 15,	23, 26,
         5, 18,	31, 10,
         2,  8,	24, 14,
        32, 27,	 3,  9,
        19, 13,	30,  6,
        22, 11,	 4, 25,  
    };
    
    private static int[] IP_ = {
        40,  8,	48, 16,	56, 24,	64, 32,
        39,  7,	47, 15,	55, 23,	63, 31,
        38,  6,	46, 14,	54, 22,	62, 30,
        37,  5,	45, 13,	53, 21,	61, 29,
        36,  4,	44, 12,	52, 20,	60, 28,
        35,  3,	43, 11,	51, 19,	59, 27,
        34,  2,	42, 10,	50, 18,	58, 26,
        33,  1,	41,  9,	49, 17,	57, 25
    };
    
    private static int[][][] S = new int[][][]
    {   {   {14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7}, { 0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8}, { 4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0}, {15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13}  },
        {
        {15,	1,	8,	14,	6,	11,	3,	4,	9,	7,	2,	13,	12,	0,	5,	10},
        {3,	13,	4,	7,	15,	2,	8,	14,	12,	0	,1,	10,	6,	9,	11,	5},
        {0,	14,	7,	11,	10,	4,	13,	1,	5,	8,	12,	6,	9,	3,	2,	15},
        {13,	8,	10,	1	,3	,15,	4,	2	,11	,6	,7,	12,	0,	5,	14,	9}
    },
        {
        {10,	0	,9,	14,	6,	3,	15,	5,	1,	13,	12,	7,	11,	4,	2,	8},
        {13,	7,	0,	9,	3,	4,	6,	10,	2,	8,	5,	14,	12,	11,	15,	1},
        {13,	6,	4,	9,	8,	15,	3	,0,	11,	1,	2,	12,	5	,10,	14,	7},
        {1,	10,	13,	0,	6,	9,	8,	7,	4,	15,	14,	3,	11,	5,	2,	12}
    },
        {
        {7,	13,	14,	3	,0,	6,	9,	10,	1,	2,	8,	5,	11,	12,	4,	15},
        {13,	8,	11,	5,	6,	15,	0,	3,	4,	7,	2,	12,	1,	10,	14,	9},
        {10,	6,	9,	0,	12	,11,	7,	13,	15,	1,	3,	14,	5,	2,	8,	4},
        {3,	15,	0,	6,	10,	1,	13,	8,	9,	4,	5,	11,	12,	7,	2,	14}
    },
        {
        {2,	12,	4,	1,	7,	10,	11,	6,	8,	5,	3,	15,	13,	0,	14,	9},
        {14,	11,	2,	12,	4,	7,	13,	1,	5,	0,	15,	10,	3,	9,	8,	6},
        {4,	2,	1,	11,	10,	13,	7,	8,	15,	9,	12,	5,	6,	3,	0,	14},
        {11,	8,	12,	7,	1,	14,	2,	13,	6,	15,	0,	9,	10,	4,	5,	3}
    },
        {
        {12,	1,	10,	15,	9,	2,	6,	8,	0,	13,	3,	4,	14,	7,	5,	11},
        {10,	15,	4,	2,	7,	12,	9,	5,	6,	1,	13,	14,	0,	11,	3,	8},
        {9,	14,	15,	5,	2,	8,	12,	3,	7,	0,	4,	10,	1,	13,	11,	6},
        {4,	3,	2,	12,	9,	5,	15,	10,	11,	14,	1,	7,	6,	0,	8,	13}
    },
        {
        {4,	11,	2,	14,	15,	0,	8,	13,	3,	12,	9,	7,	5,	10,	6,	1},
        {13,	0,	11,	7,	4,	9,	1,	10,	14,	3,	5,	12,	2,	15,	8,	6},
        {1,	4,	11,	13,	12,	3,	7,	14,	10,	15,	6,	8,	0,	5,	9,	2},
        {6,	11,	13,	8,	1,	4,	10,	7,	9,	5,	0,	15,	14,	2,	3,	12}
    },
        {
        {13,	2,	8,	4,	6,	15,	11,	1,	10,	9	,3	,14,	5,	0,	12,	7},
        {1,	15,	13,	8,	10	,3,	7,	4,	12,	5,	6,	11,	0,	14,	9,	2},
        {7,	11,	4,	1,	9,	12,	14,	2,	0	,6	,10,	13,	15,	3,	5,	8},
        {2,	1,	14,	7,	4,	10,	8,	13,	15,	12,	9,	0,	3,	5,	6,	11}
    },
            
    };
    

    
    //Fungsi Subtitusi
    private static String subtitusi(String a){
        String g = "";
       
        for(int i=1;i<9;i++){
            String d = ""; 
            d += a.substring(6*(i-1), 6*i);
            String h = "";
            String j = "";
            h += d.charAt(0);
            h += d.charAt(5);
            j += d.substring(1, 5);
            int k = Integer.parseInt(h, 2);
            int l = Integer.parseInt(j, 2);
            if(S[i-1][k][l]<2)
            {
                g += "000" + Integer.toBinaryString(S[i-1][k][l]);
            }
            else if(S[i-1][k][l]<4)
            {
                g += "00" + Integer.toBinaryString(S[i-1][k][l]);
            }
            else if(S[i-1][k][l]<8)
            {
                g += "0" + Integer.toBinaryString(S[i-1][k][l]);
            }
            else
            {
                g += Integer.toBinaryString(S[i-1][k][l]);
            }
        }

        return g;
    }
    
    // Fungsi mengubah key (string) menjadi key (binary)
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
    
    //Fungsi Permutasi
    private static String permutation(String a, int[] b){
        String g="";
        for(int c: b){
            g += a.charAt(c-1);
        }
        return g;
    }
    
    private static String XOR(String a, String b){
        String g="";
        for(int i = 0; i<48;i++){
            if(a.charAt(i)== b.charAt(i)){
                g += "0";
            }
            else{
                g += "1";
            }
        }
        return g;
    }
    
    private static String XOR2(String a, String b){
        String g="";
        for(int i = 0; i<32;i++){
            if(a.charAt(i)== b.charAt(i)){
                g += "0";
            }
            else{
                g += "1";
            }
        }
        return g;
    }
    
    private static String LeftShift(String a, int b){
        String g="";
        //int i =0;
        for(int i = 0 ; i <28 ; i++ ){
            g += a.charAt((i+b)%28);
        }
        return g;
    }
    
    private static String hex(String a){ 
    int digitNumber = 1;
    int sum = 0;
    String g = "";
    for(int i = 0; i < a.length(); i++){
        if(digitNumber == 1)
            sum+=Integer.parseInt(a.charAt(i) + "")*8;
        else if(digitNumber == 2)
            sum+=Integer.parseInt(a.charAt(i) + "")*4;
        else if(digitNumber == 3)
            sum+=Integer.parseInt(a.charAt(i) + "")*2;
        else if(digitNumber == 4 || i < a.length()+1){
            sum+=Integer.parseInt(a.charAt(i) + "")*1;
            digitNumber = 0;
            if(sum < 10)
                g += sum;
         
            else if(sum == 10)
                g += "A";
         
            else if(sum == 11)
                g += "B";
         
            else if(sum == 12)
                g += "C";
           
            else if(sum == 13)
                g += "D";
           
            else if(sum == 14)
                g += "E";
           
            else if(sum == 15)
                g += "F";
            sum=0;
        }
        digitNumber++;  
    }
    return g;
    }
    
    public static String Enkripsi(String plain, String key){
        String cipher;
        
        String plainbinary;
        String plainip;
        String[] PL = new String[17];
        String[] PR = new String[17];
        String[] EPR = new String[17];
        String[] KEPR = new String[17];
        String[] SKEPR = new String[17];
        String[] PSKEPR = new String[17];
        String PLPR;
        String PPLPR;
 
        String bkey;
        String pcbkey;
        String[] L = new String[17];
        String[] R = new String[17];
        String[] LR = new String[17];
        String[] LRF = new String[17];
        
                
        bkey = keystringbinary(key);
        pcbkey = permutation(bkey, PC1);
        
        L[0] = pcbkey.substring(0,28);
        R[0] = pcbkey.substring(28,56);
        
        for(int i=1; i<17;i++){
            L[i] = LeftShift(L[i-1],Shift[i-1]);
            R[i] = LeftShift(R[i-1],Shift[i-1]);
        }
        
        for(int i=1; i<17;i++){
            LR[i] = "";
            LR[i] += L[i] + R[i];
        }
        
        for(int i=1; i<17;i++){
           LRF[i] = permutation(LR[i], PC2);
        }
        
        plainbinary = keystringbinary(plain);
        plainip = permutation(plainbinary, IP);
        
        PL[0] = plainip.substring(0,32);
        PR[0] = plainip.substring(32,64);
        for(int i=0;i<17;i++)
        {
            EPR[i] = permutation(PR[i], Expansion);
            KEPR[i] = XOR(EPR[i], LRF[i+1]);
            SKEPR[i] = subtitusi(KEPR[i]);
            PSKEPR[i] = permutation(SKEPR[i], P);
            PL[i+1] = PR[i]; 
            PR[i+1] = XOR2(PSKEPR[i], PL[i]);
            
        }
        PLPR = "";
        PLPR += PR[16] + PL[16];
        PPLPR = permutation(PLPR, IP_);
        return PPLPR;
    }
    
    public static String CTREnkripsi(String a)
    {
        
        
        return a;
    }
    
    public static String CounTeR(String a)
    {
        
        return a;
    }
    
    public static String Dekripsi(String plain, String key){
        String cipher;
        
        String plainbinary;
        String plainip;
        String[] PL = new String[17];
        String[] PR = new String[17];
        String[] EPR = new String[17];
        String[] KEPR = new String[17];
        String[] SKEPR = new String[17];
        String[] PSKEPR = new String[17];
        String PLPR;
        String PPLPR;
 
        String bkey;
        String pcbkey;
        String[] L = new String[17];
        String[] R = new String[17];
        String[] LR = new String[17];
        String[] LRF = new String[17];
        
                
        bkey = keystringbinary(key);
        pcbkey = permutation(bkey, PC1);
        
        L[0] = pcbkey.substring(0,28);
        R[0] = pcbkey.substring(28,56);
        
        for(int i=1; i<17;i++){
            L[i] = LeftShift(L[i-1],Shift[i-1]);
            R[i] = LeftShift(R[i-1],Shift[i-1]);
        }
        
        for(int i=1; i<17;i++){
            LR[i] = "";
            LR[i] += L[i] + R[i];
        }
        
        for(int i=1; i<17;i++){
           LRF[i] = permutation(LR[i], PC2);
        }
        
        plainbinary = keystringbinary(plain);
        plainip = permutation(plainbinary, IP);
        
        PL[0] = plainip.substring(0,32);
        PR[0] = plainip.substring(32,64);
        
        for(int z=0; z<16; z++){
            EPR[z] = permutation(PR[z], Expansion);
            KEPR[z] = XOR(LRF[16-z],EPR[z]);
            SKEPR[z] = subtitusi(KEPR[z]);
            PSKEPR[z] = permutation(SKEPR[z], P);
            PL[z+1]= PR[z];
            PR[z+1]= XOR2(PSKEPR[z],PL[z]);
        }
        
        PLPR = "";
        PLPR += PR[16] + PL[16];
        PPLPR = permutation(PLPR, IP_);
        
        return PPLPR;
    }
    
}
