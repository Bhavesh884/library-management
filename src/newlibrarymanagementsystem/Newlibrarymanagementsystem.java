/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibrarymanagementsystem;

/**
 *
 * @author bhavesh
 */
public class Newlibrarymanagementsystem {

    public static String MysqlEscapeSeq(String ins){
    String outs = "" ;
for(int i=0 ; i<ins.length() ; i++){
        char thechar = ins.charAt(i);
    if(thechar=='\\'||thechar=='\''||thechar=='\"'||thechar=='%'||thechar=='_')
         outs = outs+"\\"+thechar ;
    else outs = outs+thechar ;
}
return outs;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MenuFrame.main(args);
        
        
    }
    
}
