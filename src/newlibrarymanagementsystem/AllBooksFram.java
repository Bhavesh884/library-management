/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibrarymanagementsystem;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bhavesh
 */
public class AllBooksFram extends javax.swing.JFrame {
    
MenuFrame mnfobj;
BookLogFrame blfobj;
String LoggedInUserName;

boolean ReasonValid = false;

public void foclose(){
this.setEnabled(false);
if(blfobj!=null)
    blfobj.setEnabled(false);
}
public void focback(){
this.setEnabled(true);
if(blfobj!=null)
    blfobj.setEnabled(true);
}
    /**
     * Creates new form AllBooksFrame
     */
    public AllBooksFram(MenuFrame mnfobj) {
        initComponents();
        this.mnfobj = mnfobj;
        LoggedInUserName = mnfobj.LoggedInUserName;
        this.setLocationRelativeTo(null);
    }
    
    public void evlrefresh(){
    mnfobj.evlrefresh();
    }

    public String CBIndexToColName(int i){
        String colname=null;
    switch(i){
        case 0: colname = "CONV(bid,10,26)";
        break;
        case 1: colname = "bname";
        break;
        case 2: colname = "pages";
        break;
        case 3: colname = "pubname";
        break;
        case 4: colname = "writname";
        break;
        case 5: colname = "edition";
        break;
        case 6: colname = "price";
        break;
        case 7: colname = "addon";
        break;
        case 8: colname = "source";
        break;
        case 9: colname = "issued";
        break;
    }
    return colname;
    }
        
    public void refresh(){
        RemoveBookBtn.setEnabled(false);
        IssueBookBtn.setEnabled(false);
    String sfield1 = CBIndexToColName(SearchFieldCB1.getSelectedIndex());
    String sfield2 = CBIndexToColName(SearchFieldCB2.getSelectedIndex());
    String sfield3 = CBIndexToColName(SearchFieldCB3.getSelectedIndex());
    String scrit1 = (String)SearchCritCB1.getSelectedItem();
    String scrit2 = (String)SearchCritCB2.getSelectedItem();
    String scrit3 = (String)SearchCritCB3.getSelectedItem();
    String search1 = Newlibrarymanagementsystem.MysqlEscapeSeq(SearchTF1.getText());
    String search2 = Newlibrarymanagementsystem.MysqlEscapeSeq(SearchTF2.getText());
    String search3 = Newlibrarymanagementsystem.MysqlEscapeSeq(SearchTF3.getText());
    
    boolean inv1 = NOTCheck1.isSelected();
    boolean inv2 = NOTCheck2.isSelected();
    boolean inv3 = NOTCheck3.isSelected();
    String sorter = CBIndexToColName(SortCB.getSelectedIndex());
    if(sorter.equals("CONV(bid,10,26)"))
        sorter = "bid" ;
    boolean sortdesc = SortDescCheck.isSelected();
    String query = "SELECT CONV(bid,10,26),bname,pages,pubname,writname,edition,price,addon,source,issued FROM books WHERE "+(inv1 ? "NOT " : "")+sfield1+" "+(scrit1.length()==1 ? scrit1 : "LIKE")+" \'"+(scrit1=="ends with" || scrit1=="includes" ? "%" : "")+search1+(scrit1=="starts with" || scrit1=="includes" ? "%" : "")+"\' AND "+(inv2 ? "NOT " : "")+sfield2+" "+(scrit2.length()==1 ? scrit2 : "LIKE")+" \'"+(scrit2=="ends with" || scrit2=="includes" ? "%" : "")+search2+(scrit2=="starts with" || scrit2=="includes" ? "%" : "")+"\' AND "+(inv3 ? "NOT " : "")+sfield3+" "+(scrit3.length()==1 ? scrit3 : "LIKE")+" \'"+(scrit3=="ends with" || scrit3=="includes" ? "%" : "")+search3+(scrit3=="starts with" || scrit3=="includes" ? "%" : "")+"\' ORDER BY "+sorter+(sortdesc ? " DESC " : " ")+";" ;
    DefaultTableModel model = (DefaultTableModel)Tbl.getModel();
    int rows = model.getRowCount();
    if(rows!=0)
        for(int i=0 ; i<rows ; i++)
            model.removeRow(0);
    try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    Object[] tuple = new Object[10];
    while(rs.next()){
    for(int i=1 ; i<=10; i++)
    tuple[i-1] = rs.getObject(i);
    model.addRow(tuple);
    }
    rs.close();
    stmt.close();
    c.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
    }
    
    public void IssueBookDialogrefresh(){
    
    }
    
    public String[] IssueBookDialogDat(Object bid){
    String query = "SELECT * FROM books WHERE CONV(bid,10,26) = \'"+((String)bid)+"\' ;" ;
    String[] tuple = new String[9];
    try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    rs.next();
    for(int i=1 ; i<=9; i++)
        if(rs.getObject(i)!=null)
    tuple[i-1] = (rs.getObject(i)).toString();
    rs.close();
    stmt.close();
    c.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
    tuple[0] = Integer.toString(Integer.parseInt((String)tuple[0],10),26).toUpperCase();
    return tuple;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddBookDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        pagesTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pubnameTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        sourceTF = new javax.swing.JTextField();
        editionTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        writnameTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        bnameTF = new javax.swing.JTextField();
        bnameLbl = new javax.swing.JLabel();
        pubnameLbl = new javax.swing.JLabel();
        writnameLbl = new javax.swing.JLabel();
        pagesLbl = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        editionLbl = new javax.swing.JLabel();
        sourceLbl = new javax.swing.JLabel();
        AddBookDoneBtn = new javax.swing.JButton();
        IssueBookDialog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        pagesOTF = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        pubnameOTF = new javax.swing.JTextField();
        priceOTF = new javax.swing.JTextField();
        editionOTF = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        writnameOTF = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        bnameOTF = new javax.swing.JTextField();
        sourceOTF = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        bidOTF = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        addonOTF = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        fnameOTF = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        addressOTF = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        cnoOTF = new javax.swing.JTextField();
        nameOTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        genderOTF = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        OtherRB = new javax.swing.JRadioButton();
        OtherNameTF = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        OtherNameLbl = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        StudentRB = new javax.swing.JRadioButton();
        jLabel59 = new javax.swing.JLabel();
        rnoTF = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        sidTF = new javax.swing.JTextField();
        rnoLbl = new javax.swing.JLabel();
        sidLbl = new javax.swing.JLabel();
        IssueBookDoneBtn = new javax.swing.JButton();
        IssueSelectBtnGroup = new javax.swing.ButtonGroup();
        ReasonDialog = new javax.swing.JDialog();
        reasonTF = new javax.swing.JTextField();
        reasonLbl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        SortDescCheck = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl = new javax.swing.JTable();
        SortCB = new javax.swing.JComboBox<>();
        IssueBookBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        AddBookBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SearchFieldCB1 = new javax.swing.JComboBox<>();
        SearchCritCB1 = new javax.swing.JComboBox<>();
        SearchTF1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        SearchFieldCB2 = new javax.swing.JComboBox<>();
        SearchCritCB2 = new javax.swing.JComboBox<>();
        SearchTF2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SearchFieldCB3 = new javax.swing.JComboBox<>();
        SearchCritCB3 = new javax.swing.JComboBox<>();
        SearchTF3 = new javax.swing.JTextField();
        NOTCheck1 = new javax.swing.JCheckBox();
        NOTCheck2 = new javax.swing.JCheckBox();
        NOTCheck3 = new javax.swing.JCheckBox();
        RemoveBookBtn = new javax.swing.JButton();
        ViewLogBtn = new javax.swing.JButton();

        AddBookDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        AddBookDialog.setTitle("Add New Book");
        AddBookDialog.setAlwaysOnTop(true);
        AddBookDialog.setResizable(false);
        AddBookDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                AddBookDialogWindowClosed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter Book Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel10.setText("Price-");

        pagesTF.setBackground(java.awt.Color.pink);
        pagesTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                pagesTFCaretUpdate(evt);
            }
        });
        pagesTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pagesTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pagesTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pagesTFKeyAll(evt);
            }
        });

        jLabel11.setText("Source-");

        pubnameTF.setBackground(java.awt.Color.pink);
        pubnameTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                pubnameTFCaretUpdate(evt);
            }
        });
        pubnameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pubnameTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pubnameTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pubnameTFKeyAll(evt);
            }
        });

        priceTF.setBackground(java.awt.Color.lightGray);
        priceTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                priceTFCaretUpdate(evt);
            }
        });
        priceTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                priceTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceTFKeyAll(evt);
            }
        });

        sourceTF.setBackground(java.awt.Color.pink);
        sourceTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                sourceTFCaretUpdate(evt);
            }
        });
        sourceTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editionTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sourceTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editionTFKeyAll(evt);
            }
        });

        editionTF.setBackground(java.awt.Color.pink);
        editionTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                editionTFCaretUpdate(evt);
            }
        });
        editionTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editionTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                editionTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editionTFKeyAll(evt);
            }
        });

        jLabel4.setText("Book Name-");

        jLabel6.setText("Pages-");

        jLabel7.setText("Publisher-");

        jLabel8.setText("Writer-");

        writnameTF.setBackground(java.awt.Color.pink);
        writnameTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                writnameTFCaretUpdate(evt);
            }
        });
        writnameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                writnameTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                writnameTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                writnameTFKeyAll(evt);
            }
        });

        jLabel9.setText("Edition-");

        bnameTF.setBackground(java.awt.Color.pink);
        bnameTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                bnameTFCaretUpdate(evt);
            }
        });
        bnameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bnameTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bnameTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bnameTFKeyAll(evt);
            }
        });

        bnameLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bnameLbl.setText("0/50");

        pubnameLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pubnameLbl.setText("0/45");

        writnameLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        writnameLbl.setText("0/45");

        pagesLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        pagesLbl.setText("0/5");

        priceLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        priceLbl.setText("0/9");

        editionLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        editionLbl.setText("0/30");

        sourceLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        sourceLbl.setText("0/100");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(jLabel9)))))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(writnameTF)
                            .addComponent(bnameTF)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pagesLbl)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(pagesTF, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(editionTF)
                            .addComponent(sourceTF)
                            .addComponent(pubnameTF)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(writnameLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editionLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sourceLbl, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(286, 295, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bnameLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pubnameLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(priceLbl)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(171, 171, 171)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pubnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pubnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(writnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(writnameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(pagesTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagesLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(editionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editionLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sourceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        AddBookDoneBtn.setText("Done");
        AddBookDoneBtn.setEnabled(false);
        AddBookDoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBookDoneBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddBookDialogLayout = new javax.swing.GroupLayout(AddBookDialog.getContentPane());
        AddBookDialog.getContentPane().setLayout(AddBookDialogLayout);
        AddBookDialogLayout.setHorizontalGroup(
            AddBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(AddBookDialogLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(AddBookDoneBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddBookDialogLayout.setVerticalGroup(
            AddBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddBookDialogLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddBookDoneBtn)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        IssueBookDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        IssueBookDialog.setTitle("Issue Book");
        IssueBookDialog.setAlwaysOnTop(true);
        IssueBookDialog.setResizable(false);
        IssueBookDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                IssueBookDialogWindowClosed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel47.setText("Price-");

        pagesOTF.setEditable(false);

        jLabel48.setText("Source-");

        pubnameOTF.setEditable(false);

        priceOTF.setEditable(false);

        editionOTF.setEditable(false);

        jLabel49.setText("Book Name-");

        jLabel50.setText("Pages-");

        jLabel51.setText("Publisher-");

        jLabel52.setText("Writer-");

        writnameOTF.setEditable(false);

        jLabel53.setText("Edition-");

        bnameOTF.setEditable(false);

        sourceOTF.setEditable(false);

        jLabel54.setText("Book Id -");

        bidOTF.setEditable(false);

        jLabel67.setText("Added on-");

        addonOTF.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel50)
                .addGap(18, 18, 18)
                .addComponent(pagesOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(priceOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel49)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel48)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bnameOTF)
                    .addComponent(editionOTF)
                    .addComponent(writnameOTF)
                    .addComponent(pubnameOTF)
                    .addComponent(sourceOTF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(bidOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addonOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pubnameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(writnameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(pagesOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47))
                    .addComponent(priceOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(editionOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addonOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bidOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel54)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel55.setText("Name-");

        fnameOTF.setEditable(false);

        jLabel56.setText("Father's name-");

        jLabel57.setText("Address-");

        addressOTF.setEditable(false);

        jLabel58.setText("Contact no-");

        cnoOTF.setEditable(false);

        nameOTF.setEditable(false);

        jLabel19.setText("Gender-");

        genderOTF.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameOTF)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(genderOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 410, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addressOTF))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fnameOTF))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cnoOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(nameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(genderOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnoOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel60.setText("Issue to-");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        IssueSelectBtnGroup.add(OtherRB);
        OtherRB.setText("Other");
        OtherRB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OtherRBItemStateChanged(evt);
            }
        });

        OtherNameTF.setBackground(java.awt.Color.pink);
        OtherNameTF.setEnabled(false);
        OtherNameTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                OtherNameTFCaretUpdate(evt);
            }
        });
        OtherNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OtherNameTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OtherNameTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OtherNameTFKeyAll(evt);
            }
        });

        jLabel63.setText("Name of person/organization-");

        OtherNameLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        OtherNameLbl.setText("0/45");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(OtherRB)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(OtherNameLbl))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63)
                        .addGap(18, 18, 18)
                        .addComponent(OtherNameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(OtherRB)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OtherNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OtherNameLbl)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        IssueSelectBtnGroup.add(StudentRB);
        StudentRB.setSelected(true);
        StudentRB.setText("Student");
        StudentRB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                StudentRBItemStateChanged(evt);
            }
        });

        jLabel59.setText("Roll no-");

        rnoTF.setBackground(java.awt.Color.pink);
        rnoTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                rnoTFCaretUpdate(evt);
            }
        });
        rnoTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rnoTFKeyAll(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rnoTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rnoTFKeyAll(evt);
            }
        });

        jLabel61.setText("-OR-");

        jLabel62.setText("Student Id-");

        sidTF.setBackground(java.awt.Color.pink);
        sidTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                sidTFCaretUpdate(evt);
            }
        });
        sidTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sidTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sidTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sidTFKeyAll(evt);
            }
        });

        rnoLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rnoLbl.setText("0/5");

        sidLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        sidLbl.setText("0/4");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(StudentRB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel61)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 16, Short.MAX_VALUE)
                                .addComponent(jLabel59)
                                .addGap(36, 36, 36))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rnoTF, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(sidTF))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rnoLbl)
                    .addComponent(sidLbl))
                .addGap(31, 31, 31))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(StudentRB)
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(rnoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rnoLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sidTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(sidLbl))
                .addContainerGap())
        );

        IssueBookDoneBtn.setText("Done");
        IssueBookDoneBtn.setEnabled(false);
        IssueBookDoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueBookDoneBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout IssueBookDialogLayout = new javax.swing.GroupLayout(IssueBookDialog.getContentPane());
        IssueBookDialog.getContentPane().setLayout(IssueBookDialogLayout);
        IssueBookDialogLayout.setHorizontalGroup(
            IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IssueBookDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IssueBookDialogLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(IssueBookDialogLayout.createSequentialGroup()
                        .addGroup(IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IssueBookDialogLayout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(IssueBookDialogLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(jLabel60)
                                .addGap(158, 158, 158)))
                        .addComponent(IssueBookDoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        IssueBookDialogLayout.setVerticalGroup(
            IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IssueBookDialogLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel60)
                .addGroup(IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IssueBookDialogLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(IssueBookDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(IssueBookDialogLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(IssueBookDoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        ReasonDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ReasonDialog.setTitle("Reason for removal");
        ReasonDialog.setModal(true);
        ReasonDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                ReasonDialogWindowClosed(evt);
            }
        });

        reasonTF.setBackground(java.awt.Color.pink);
        reasonTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                reasonTFCaretUpdate(evt);
            }
        });
        reasonTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasonTFActionPerformed(evt);
            }
        });
        reasonTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reasonTFKeyAll(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reasonTFKeyAll(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reasonTFKeyAll(evt);
            }
        });

        reasonLbl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        reasonLbl.setText("0/100");

        javax.swing.GroupLayout ReasonDialogLayout = new javax.swing.GroupLayout(ReasonDialog.getContentPane());
        ReasonDialog.getContentPane().setLayout(ReasonDialogLayout);
        ReasonDialogLayout.setHorizontalGroup(
            ReasonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReasonDialogLayout.createSequentialGroup()
                .addGroup(ReasonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReasonDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reasonTF, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ReasonDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reasonLbl)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReasonDialogLayout.setVerticalGroup(
            ReasonDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReasonDialogLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(reasonTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reasonLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("All Books");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        SortDescCheck.setSelected(true);
        SortDescCheck.setText("in descending order");
        SortDescCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SortDescCheckItemStateChanged(evt);
            }
        });

        Tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added on", "Source", "Issued"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl.setCellSelectionEnabled(true);
        Tbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Tbl.getTableHeader().setReorderingAllowed(false);
        Tbl.setCellSelectionEnabled(true);
        Tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TblMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Tbl);
        if (Tbl.getColumnModel().getColumnCount() > 0) {
            Tbl.getColumnModel().getColumn(0).setPreferredWidth(6);
            Tbl.getColumnModel().getColumn(1).setPreferredWidth(270);
            Tbl.getColumnModel().getColumn(2).setPreferredWidth(10);
            Tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
            Tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
            Tbl.getColumnModel().getColumn(6).setPreferredWidth(15);
            Tbl.getColumnModel().getColumn(7).setPreferredWidth(20);
            Tbl.getColumnModel().getColumn(9).setPreferredWidth(1);
        }

        SortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added on", "Source", "Issued" }));
        SortCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SortCBItemStateChanged(evt);
            }
        });

        IssueBookBtn.setText("Issue Book");
        IssueBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueBookBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Sort by");

        AddBookBtn.setText("Add Book");
        AddBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBookBtnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Criteria", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("1");

        SearchFieldCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added on", "Source", "Issued" }));
        SearchFieldCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchFieldCB1ItemStateChanged(evt);
            }
        });

        SearchCritCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        SearchCritCB1.setSelectedItem("starts with");
        SearchCritCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchCritCB1ItemStateChanged(evt);
            }
        });

        SearchTF1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SearchTF1CaretUpdate(evt);
            }
        });

        jLabel2.setText("2");

        SearchFieldCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added on", "Source", "Issued" }));
        SearchFieldCB2.setSelectedItem("Book Name");
        SearchFieldCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchFieldCB2ItemStateChanged(evt);
            }
        });

        SearchCritCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        SearchCritCB2.setSelectedItem("starts with");
        SearchCritCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchCritCB2ItemStateChanged(evt);
            }
        });

        SearchTF2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SearchTF2CaretUpdate(evt);
            }
        });

        jLabel3.setText("3");

        SearchFieldCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added on", "Source", "Issued" }));
        SearchFieldCB3.setSelectedItem("Added on");
        SearchFieldCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchFieldCB3ItemStateChanged(evt);
            }
        });

        SearchCritCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        SearchCritCB3.setSelectedItem("starts with");
        SearchCritCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchCritCB3ItemStateChanged(evt);
            }
        });

        SearchTF3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SearchTF3CaretUpdate(evt);
            }
        });

        NOTCheck1.setText("NOT");
        NOTCheck1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NOTCheck1ItemStateChanged(evt);
            }
        });

        NOTCheck2.setText("NOT");
        NOTCheck2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NOTCheck2ItemStateChanged(evt);
            }
        });

        NOTCheck3.setText("NOT");
        NOTCheck3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NOTCheck3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addComponent(SearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NOTCheck1)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39)
                                .addComponent(SearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NOTCheck2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(39, 39, 39)
                                .addComponent(SearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NOTCheck3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(SearchTF3, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(SearchTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(SearchTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(SearchTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NOTCheck1))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(SearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NOTCheck2))
                    .addComponent(SearchTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NOTCheck3))
                    .addComponent(SearchTF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(336, 336, 336))
        );

        RemoveBookBtn.setText("Remove Book");
        RemoveBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBookBtnActionPerformed(evt);
            }
        });

        ViewLogBtn.setText("View Log");
        ViewLogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewLogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(SortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(IssueBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AddBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SortDescCheck)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(RemoveBookBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addComponent(ViewLogBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SortDescCheck)
                            .addComponent(SortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IssueBookBtn)
                            .addComponent(ViewLogBtn))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RemoveBookBtn)
                            .addComponent(AddBookBtn)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void IssueBookDialogset(String[] tuple){
    bidOTF.setText(tuple[0]);
    bnameOTF.setText(tuple[1]);
    pagesOTF.setText(tuple[2]);
    pubnameOTF.setText(tuple[3]);
    writnameOTF.setText(tuple[4]);
    editionOTF.setText(tuple[5]);
    priceOTF.setText(tuple[6]);
    addonOTF.setText(tuple[7]);
    sourceOTF.setText(tuple[8]);
    }
    
    public void IssueBookDialogreset(){
    StudentRB.setSelected(true);
    OtherNameTF.setText("");
    nameOTF.setText("");
    genderOTF.setText("");
    rnoTF.setText("");
    sidTF.setText("");
    fnameOTF.setText("");
    cnoOTF.setText("");
    addressOTF.setText("");
    bnameOTF.setText("");
    pubnameOTF.setText("");
    writnameOTF.setText("");
    pagesOTF.setText("");
    priceOTF.setText("");
    editionOTF.setText("");
    sourceOTF.setText("");
    bidOTF.setText("");
    }
    
    public void StudentFieldsreset(){
    nameOTF.setText("");
    genderOTF.setText("");
    fnameOTF.setText("");
    cnoOTF.setText("");
    addressOTF.setText("");
    IssueBookDoneBtn.setEnabled(false);
    }
    
    public void AddBookDialogreset(){
    bnameTF.setText("");
    pubnameTF.setText("");
    writnameTF.setText("");
    pagesTF.setText("");
    priceTF.setText("");
    editionTF.setText("");
    sourceTF.setText("");
    bnameTF.setBackground(Color.pink);
    pubnameTF.setBackground(Color.pink);
    writnameTF.setBackground(Color.pink);
    pagesTF.setBackground(Color.pink);
    priceTF.setBackground(Color.LIGHT_GRAY);
    editionTF.setBackground(Color.pink);
    sourceTF.setBackground(Color.pink);
    AddBookDoneBtn.setEnabled(false);
    }
    
    public void AddBookDialogrefresh(){
    boolean key1 = false;
    boolean key2 = false;
    boolean key3 = false;
    boolean key4 = false;
    boolean key5 = false;
    boolean key6 = false;
    boolean key7 = false;
        {
        String inp = bnameTF.getText();
        if(inp.length()>50){
            inp = inp.substring(0,50);
            bnameTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        bnameTF.setBackground(Color.white);
        key1 = true;
    }
        else{
        bnameTF.setBackground(Color.pink);
        }
        }
        {
        String inp = pubnameTF.getText();
        if(inp.length()>45){
            inp = inp.substring(0,45);
            pubnameTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        pubnameTF.setBackground(Color.white);
        key2 = true;
    }
        else{
        pubnameTF.setBackground(Color.pink);
        }
        }
        {
        String inp = writnameTF.getText();
        if(inp.length()>45){
            inp = inp.substring(0,45);
            writnameTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        writnameTF.setBackground(Color.white);
        key3 = true;
    }
        else{
        writnameTF.setBackground(Color.pink);
        }
        }
        {
        String inp = pagesTF.getText();
        if(inp.length()>5){
            inp = inp.substring(0,5);
            pagesTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        pagesTF.setBackground(Color.white);
        try{
        int pagesnum = Integer.parseInt(inp);
        if(pagesnum>0)
            key4 = true;
        else pagesTF.setBackground(Color.pink);
        }
        catch(Exception e){
        pagesTF.setBackground(Color.pink);
        }
    }
        else{
        pagesTF.setBackground(Color.pink);
        }
        }
        {
        String inp = priceTF.getText();
        if(inp.length()>9){
            inp = inp.substring(0,9);
            priceTF.setText(inp);
        }
        boolean pass = false;
        if(inp.length()==0){
        priceTF.setBackground(Color.LIGHT_GRAY);
        key5 = true;
        }
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        priceTF.setBackground(Color.white);
        try{
        int pricenum = Integer.parseInt(inp);
        if(pricenum>0)
            key5 = true;
        else priceTF.setBackground(Color.pink);
        }
        catch(Exception e){
        priceTF.setBackground(Color.pink);
        }
    }
        else if(key5==false)
        priceTF.setBackground(Color.pink);
        }
        {
        String inp = editionTF.getText();
        if(inp.length()>30){
            inp = inp.substring(0,30);
            editionTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        editionTF.setBackground(Color.white);
        key6 = true;
    }
        else{
        editionTF.setBackground(Color.pink);
        }
        }
        {
        String inp = sourceTF.getText();
        if(inp.length()>100){
            inp = inp.substring(0,100);
            sourceTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        sourceTF.setBackground(Color.white);
        key7 = true;
    }
        else{
        sourceTF.setBackground(Color.pink);
        }
        }

        if(key1 && key2 && key3 && key4 && key5 && key6 && key7)
            AddBookDoneBtn.setEnabled(true);
        else AddBookDoneBtn.setEnabled(false);
    }
    
    public void blogbtnen(){
    ViewLogBtn.setEnabled(true);
    ViewLogBtn.setToolTipText(null);
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        mnfobj.bookbtnen();
    }//GEN-LAST:event_formWindowClosed

    private void AddBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBookBtnActionPerformed
        // TODO add your handling code here:
        mnfobj.framesfoclose();
        AddBookDialog.pack();
        AddBookDialog.setVisible(true);
        this.setEnabled(false);
        AddBookDialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_AddBookBtnActionPerformed

    private void IssueBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueBookBtnActionPerformed
        // TODO add your handling code here:
        mnfobj.framesfoclose();
        IssueBookDialog.pack();
        IssueBookDialog.setVisible(true);
        this.setEnabled(false);
        IssueBookDialog.setLocationRelativeTo(null);
        
        Object bid = Tbl.getValueAt(Tbl.getSelectedRow(),0);
        IssueBookDialogset(IssueBookDialogDat(bid));
    }//GEN-LAST:event_IssueBookBtnActionPerformed

    private void ViewLogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewLogBtnActionPerformed
        // TODO add your handling code here:
        blfobj = new BookLogFrame(AllBooksFram.this);
        blfobj.setVisible(true);
        ViewLogBtn.setToolTipText("Already open");
        ViewLogBtn.setEnabled(false);
    }//GEN-LAST:event_ViewLogBtnActionPerformed

    private void AddBookDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_AddBookDialogWindowClosed
        // TODO add your handling code here:
        AddBookDialogreset();
        mnfobj.framesfocback();
        this.setEnabled(true);
        this.requestFocus();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_AddBookDialogWindowClosed

    private void IssueBookDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_IssueBookDialogWindowClosed
        // TODO add your handling code here:
        IssueBookDialogreset();
        mnfobj.framesfocback();
        this.setEnabled(true);
        this.requestFocus();
        this.setLocationRelativeTo(null);mnfobj.setEnabled(true);
        this.setEnabled(true);
        this.requestFocus();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_IssueBookDialogWindowClosed

    private void StudentRBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_StudentRBItemStateChanged
        // TODO add your handling code here:
        if(StudentRB.isSelected()==true){
        rnoTF.setEnabled(true);
        sidTF.setEnabled(true);
        OtherNameTF.setEnabled(false);
        nameOTF.setEnabled(true);
        genderOTF.setEnabled(true);
        fnameOTF.setEnabled(true);
        cnoOTF.setEnabled(true);
        addressOTF.setEnabled(true);
        IssueBookDoneBtn.setEnabled(false);
        OtherNameTF.setText("");
        }
    }//GEN-LAST:event_StudentRBItemStateChanged

    private void OtherRBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OtherRBItemStateChanged
        // TODO add your handling code here:
        if(OtherRB.isSelected()==true){
        rnoTF.setEnabled(false);
        sidTF.setEnabled(false);
        OtherNameTF.setEnabled(true);
        nameOTF.setEnabled(false);
        genderOTF.setEnabled(false);
        fnameOTF.setEnabled(false);
        cnoOTF.setEnabled(false);
        addressOTF.setEnabled(false);
        IssueBookDoneBtn.setEnabled(false);
        StudentFieldsreset();
        rnoTF.setText("");
        sidTF.setText("");
        }
    }//GEN-LAST:event_OtherRBItemStateChanged

    private void TblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblMouseReleased
        // TODO add your handling code here:
        if(Tbl.getSelectedRowCount()>0){
            RemoveBookBtn.setEnabled(true);
            if("N".equals((String)Tbl.getValueAt(Tbl.getSelectedRow(),9)))
            IssueBookBtn.setEnabled(true);     
            else IssueBookBtn.setEnabled(false);
        }
        else{
        RemoveBookBtn.setEnabled(false);
        IssueBookBtn.setEnabled(false);
        }
    }//GEN-LAST:event_TblMouseReleased

    private void SearchCritCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchCritCB1ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchCritCB1ItemStateChanged

    private void SearchCritCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchCritCB2ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchCritCB2ItemStateChanged

    private void SearchCritCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchCritCB3ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchCritCB3ItemStateChanged

    private void NOTCheck1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NOTCheck1ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_NOTCheck1ItemStateChanged

    private void NOTCheck2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NOTCheck2ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_NOTCheck2ItemStateChanged

    private void NOTCheck3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NOTCheck3ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_NOTCheck3ItemStateChanged

    private void SearchFieldCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchFieldCB1ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchFieldCB1ItemStateChanged

    private void SearchFieldCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchFieldCB2ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchFieldCB2ItemStateChanged

    private void SearchFieldCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchFieldCB3ItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchFieldCB3ItemStateChanged

    private void SearchTF1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SearchTF1CaretUpdate
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchTF1CaretUpdate

    private void SearchTF2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SearchTF2CaretUpdate
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchTF2CaretUpdate

    private void SearchTF3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SearchTF3CaretUpdate
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SearchTF3CaretUpdate

    private void SortCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SortCBItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SortCBItemStateChanged

    private void SortDescCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SortDescCheckItemStateChanged
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_SortDescCheckItemStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(blfobj!=null)
        blfobj.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void rnoTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_rnoTFCaretUpdate
        // TODO add your handling code here:
        rnoLbl.setText(rnoTF.getText().length()+"/5");
    }//GEN-LAST:event_rnoTFCaretUpdate

    private void sidTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_sidTFCaretUpdate
        // TODO add your handling code here:
        sidLbl.setText(sidTF.getText().length()+"/4");
    }//GEN-LAST:event_sidTFCaretUpdate

    private void OtherNameTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_OtherNameTFCaretUpdate
        // TODO add your handling code here:
        OtherNameLbl.setText(OtherNameTF.getText().length()+"/45");
    }//GEN-LAST:event_OtherNameTFCaretUpdate

    private void rnoTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rnoTFKeyAll
        // TODO add your handling code here:
        String inp = rnoTF.getText();
        if(inp.length()<5){
            rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            sidTF.setText("");
        rnoTF.setText(inp.toUpperCase());
        }
        else{
        inp = inp.substring(0,5).toUpperCase();
        String tests = inp;
        rnoTF.setText(inp);
        int ch5c = (int)tests.charAt(4);
        if(ch5c<65 || ch5c>90){
            rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            sidTF.setText("");
        }
        else{
        if(tests.charAt(0)=='0')
            tests = tests.substring(1,4);
        else tests = tests.substring(0,4);
        try{
        int rnonum = Integer.parseInt(tests,10);
        if(rnonum>1299 || rnonum<100 || rnonum%100==0){
        rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            sidTF.setText("");
            return;
        }
        
        String query = "SELECT * FROM students WHERE rno = \'"+inp+"\' ;" ;
    String[] tuple = new String[7];
    try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    if(rs.next()){
    for(int i=1 ; i<=7; i++)
        if(rs.getObject(i)!=null)
    tuple[i-1] = (rs.getObject(i)).toString();
    tuple[0] = Integer.toString(Integer.parseInt(tuple[0]),18).toUpperCase();
    sidTF.setText(tuple[0]);
    nameOTF.setText(tuple[2]);
    fnameOTF.setText(tuple[3]);
    cnoOTF.setText(tuple[4]);
    addressOTF.setText(tuple[5]);
    genderOTF.setText(tuple[6]);
    rnoTF.setBackground(Color.white);
    sidTF.setBackground(Color.white);
    IssueBookDoneBtn.setEnabled(true);
    }
    else {
        rnoTF.setBackground(Color.yellow);
        sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            sidTF.setText("");
    }
    rs.close();
    stmt.close();
    c.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
    
        }
        catch(Exception e){
        rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            sidTF.setText("");
        }
        }
        }
    }//GEN-LAST:event_rnoTFKeyAll

    private void sidTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sidTFKeyAll
        // TODO add your handling code here:
        String inp = sidTF.getText();
        if(inp.length()<4){
            rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            rnoTF.setText("");
        sidTF.setText(inp.toUpperCase());
        }
        else{
        inp = inp.substring(0,4).toUpperCase();
        sidTF.setText(inp);
        
        try{
        int sidnum = Integer.parseInt(inp.toLowerCase(),36);
        if(sidnum<0){
        rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            rnoTF.setText("");
            return;
        }
        
        String query = "SELECT * FROM students WHERE CONV(sid,10,18) = \'"+inp+"\' ;" ;
    String[] tuple = new String[7];
    try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    if(rs.next()){
    for(int i=1 ; i<=7; i++)
        if(rs.getObject(i)!=null)
    tuple[i-1] = (rs.getObject(i)).toString();
    rnoTF.setText(tuple[1]);
    nameOTF.setText(tuple[2]);
    fnameOTF.setText(tuple[3]);
    cnoOTF.setText(tuple[4]);
    addressOTF.setText(tuple[5]);
    genderOTF.setText(tuple[6]);
    rnoTF.setBackground(Color.white);
    sidTF.setBackground(Color.white);
    IssueBookDoneBtn.setEnabled(true);
    }
    else {
        rnoTF.setBackground(Color.pink);
        sidTF.setBackground(Color.yellow);
            StudentFieldsreset();
            rnoTF.setText("");
    }
    rs.close();
    stmt.close();
    c.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
    
        }
        catch(Exception e){
        rnoTF.setBackground(Color.pink);
            sidTF.setBackground(Color.pink);
            StudentFieldsreset();
            rnoTF.setText("");
        }
        }
    }//GEN-LAST:event_sidTFKeyAll

    private void OtherNameTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OtherNameTFKeyAll
        // TODO add your handling code here:
        String inp = OtherNameTF.getText();
        if(inp.length()>45){
            inp = inp.substring(0,45);
            OtherNameTF.setText(inp);
        }
        boolean pass = false;
        for(int i=0 ; i<inp.length() ; i++)
            if((int)inp.charAt(i)!=32)
                pass = true;
        if(pass){
        OtherNameTF.setBackground(Color.white);
        IssueBookDoneBtn.setEnabled(true);
    }
        else{
        OtherNameTF.setBackground(Color.pink);
        IssueBookDoneBtn.setEnabled(false);
        }
    }//GEN-LAST:event_OtherNameTFKeyAll

    private void bnameTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_bnameTFCaretUpdate
        // TODO add your handling code here:
        bnameLbl.setText(bnameTF.getText().length()+"/50");
    }//GEN-LAST:event_bnameTFCaretUpdate

    private void pubnameTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_pubnameTFCaretUpdate
        // TODO add your handling code here:
        pubnameLbl.setText(pubnameTF.getText().length()+"/45");
    }//GEN-LAST:event_pubnameTFCaretUpdate

    private void writnameTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_writnameTFCaretUpdate
        // TODO add your handling code here:
        writnameLbl.setText(writnameTF.getText().length()+"/45");
    }//GEN-LAST:event_writnameTFCaretUpdate

    private void pagesTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_pagesTFCaretUpdate
        // TODO add your handling code here:
        pagesLbl.setText(pagesTF.getText().length()+"/5");
    }//GEN-LAST:event_pagesTFCaretUpdate

    private void priceTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_priceTFCaretUpdate
        // TODO add your handling code here:
        priceLbl.setText(priceTF.getText().length()+"/9");
    }//GEN-LAST:event_priceTFCaretUpdate

    private void editionTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_editionTFCaretUpdate
        // TODO add your handling code here:
        editionLbl.setText(editionTF.getText().length()+"/30");
    }//GEN-LAST:event_editionTFCaretUpdate

    private void sourceTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_sourceTFCaretUpdate
        // TODO add your handling code here:
        sourceLbl.setText(sourceTF.getText().length()+"/100");
    }//GEN-LAST:event_sourceTFCaretUpdate

    private void bnameTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bnameTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_bnameTFKeyAll

    private void pubnameTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pubnameTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_pubnameTFKeyAll

    private void writnameTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_writnameTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_writnameTFKeyAll

    private void pagesTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pagesTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_pagesTFKeyAll

    private void priceTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_priceTFKeyAll

    private void editionTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editionTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_editionTFKeyAll

    private void sourceTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sourceTFKeyAll
        // TODO add your handling code here:
        AddBookDialogrefresh();
    }//GEN-LAST:event_sourceTFKeyAll

    private void AddBookDoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBookDoneBtnActionPerformed
        // TODO add your handling code here:
        String bname = Newlibrarymanagementsystem.MysqlEscapeSeq(bnameTF.getText());
        String pages = pagesTF.getText();
        String pubname = Newlibrarymanagementsystem.MysqlEscapeSeq(pubnameTF.getText());
        String writname = Newlibrarymanagementsystem.MysqlEscapeSeq(writnameTF.getText());
        String edition = Newlibrarymanagementsystem.MysqlEscapeSeq(editionTF.getText());
        String price = priceTF.getText();
        String source = Newlibrarymanagementsystem.MysqlEscapeSeq(sourceTF.getText());
        String bid;
        String query = "INSERT INTO books (bname,pages,pubname,writname,edition,price,addon,source,issued) VALUES('"+bname+"',"+pages+",'"+pubname+"','"+writname+"','"+edition+"',"+(price.length()==0 ? null : price)+",CURDATE(),'"+source+"','N') ;" ;
        try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "SELECT bid FROM books ORDER BY bid DESC ;" ;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            bid = rs.getObject(1).toString();
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO evl VALUES(NOW(),'Book "+Integer.toString(Integer.parseInt(bid),26).toUpperCase()+" added. Name- "+bname+"','"+mnfobj.LoggedInUserName+"') ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO bookaddlog (bid,bname,pages,pubname,writname,edition,price,addon,source,addby) VALUES("+bid+",'"+bname+"',"+pages+",'"+pubname+"','"+writname+"','"+edition+"',"+(price.length()==0 ? null : price)+",CURDATE(),'"+source+"','"+mnfobj.LoggedInUserName+"') ;" ;
            stmt.executeUpdate(query);
            stmt.close();
    c.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        AddBookDialog.dispatchEvent(new WindowEvent(AddBookDialog, WindowEvent.WINDOW_CLOSING));
        refresh();
        if(blfobj!=null)
        blfobj.Arefresh();
        mnfobj.evlrefresh();
    }//GEN-LAST:event_AddBookDoneBtnActionPerformed

    private void RemoveBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBookBtnActionPerformed
        // TODO add your handling code here:
        boolean pass = true;
        String bid = Tbl.getValueAt(Tbl.getSelectedRow(),0).toString();
        bid = ""+Integer.parseInt(bid,26);
        String query = "SELECT * FROM issues WHERE bid = '"+bid+"' ;" ;
        try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
                pass = false;
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(pass){
        ReasonDialog.pack();
        ReasonDialog.setLocationRelativeTo(null);
        ReasonDialog.setVisible(true);
        }
        else JOptionPane.showMessageDialog(null,"This book has been issued by someone");
    }//GEN-LAST:event_RemoveBookBtnActionPerformed

    private void reasonTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_reasonTFCaretUpdate
        // TODO add your handling code here:
        reasonLbl.setText(reasonTF.getText().length()+"/100");
    }//GEN-LAST:event_reasonTFCaretUpdate

    private void reasonTFKeyAll(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reasonTFKeyAll
        // TODO add your handling code here:
        boolean key1 = false;
        {
    String inp = reasonTF.getText();
    if(inp.length()>100)
        inp = inp.substring(0,100);
    reasonTF.setText(inp);
    boolean pass = false;
    if(inp.length()==0)
        pass = false;
    for(int i=0 ; i<inp.length() ; i++){
    int charcod = (int)inp.charAt(i);
    if(charcod!=32)
        pass = true;
    }
    if(pass){
    reasonTF.setBackground(Color.white);
    key1 = true;
    }
    else reasonTF.setBackground(Color.pink);
    }
        if(key1)
            ReasonValid = true;
        else ReasonValid = false;
    }//GEN-LAST:event_reasonTFKeyAll

    private void ReasonDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ReasonDialogWindowClosed
        // TODO add your handling code here:
        reasonTF.setText("");
        reasonTF.setBackground(Color.pink);
        ReasonValid = false;
    }//GEN-LAST:event_ReasonDialogWindowClosed

    private void reasonTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasonTFActionPerformed
        // TODO add your handling code here:
        if(ReasonValid==true){
        String bid = Tbl.getValueAt(Tbl.getSelectedRow(),0).toString();
        String bname = Newlibrarymanagementsystem.MysqlEscapeSeq(Tbl.getValueAt(Tbl.getSelectedRow(),1).toString());
        String pages = Tbl.getValueAt(Tbl.getSelectedRow(),2).toString();
        String pubname = Newlibrarymanagementsystem.MysqlEscapeSeq(Tbl.getValueAt(Tbl.getSelectedRow(),3).toString());
        String writname = Newlibrarymanagementsystem.MysqlEscapeSeq(Tbl.getValueAt(Tbl.getSelectedRow(),4).toString());
        String edition = Newlibrarymanagementsystem.MysqlEscapeSeq(Tbl.getValueAt(Tbl.getSelectedRow(),5).toString());
        String source = Newlibrarymanagementsystem.MysqlEscapeSeq(Tbl.getValueAt(Tbl.getSelectedRow(),8).toString());
        String addon = Tbl.getValueAt(Tbl.getSelectedRow(),7).toString();
        String price = "";
        Object priceO = Tbl.getValueAt(Tbl.getSelectedRow(),6);
        if(priceO!=null)
            price = priceO.toString();
        String reason = reasonTF.getText();
        bid = (""+Integer.parseInt(bid,26));
        
        String query = "DELETE FROM books WHERE bid = '"+bid+"' ;" ;
        try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO evl VALUES(NOW(),'book "+Integer.toString(Integer.parseInt(bid),26).toUpperCase()+" removed. Name- "+bname+"','"+mnfobj.LoggedInUserName+"') ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO bookremlog (addon,source,bid,bname,pages,pubname,writname,edition,price,remon,reason,remby) VALUES('"+addon+"','"+source+"',"+bid+",'"+bname+"',"+pages+",'"+pubname+"','"+writname+"','"+edition+"',"+(price.length()==0 ? null : price)+",CURDATE(),'"+reason+"','"+mnfobj.LoggedInUserName+"') ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "UPDATE issuelog SET avail = 'N' WHERE bid = '"+bid+"' ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "UPDATE returnlog SET avail = 'N' WHERE bid = '"+bid+"' ;" ;
            stmt.executeUpdate(query);
            stmt.close();
    c.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        ReasonDialog.dispatchEvent(new WindowEvent(ReasonDialog, WindowEvent.WINDOW_CLOSING));
        refresh();
        if(blfobj!=null)
        blfobj.Rrefresh();
        mnfobj.evlrefresh();
        }
    }//GEN-LAST:event_reasonTFActionPerformed

    private void IssueBookDoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueBookDoneBtnActionPerformed
        // TODO add your handling code here:
        String bid = bidOTF.getText();
        if(bid.equals("")){} else
        bid = ""+Integer.parseInt(bid,26);
        String rno = rnoTF.getText();
        String price = priceOTF.getText();
        String isby = Newlibrarymanagementsystem.MysqlEscapeSeq(mnfobj.LoggedInUserName);
        String bname = Newlibrarymanagementsystem.MysqlEscapeSeq(bnameOTF.getText());
        String pages = pagesOTF.getText();
        String isto = (StudentRB.isSelected() ? nameOTF.getText() : OtherNameTF.getText());
        isto = Newlibrarymanagementsystem.MysqlEscapeSeq(isto);
        String sid = sidTF.getText();
        if(sid.equals("")){} else
        sid = ""+Integer.parseInt(sid,18);
        String pubname = Newlibrarymanagementsystem.MysqlEscapeSeq(pubnameOTF.getText());
        String writname = Newlibrarymanagementsystem.MysqlEscapeSeq(writnameOTF.getText());
        String edition = Newlibrarymanagementsystem.MysqlEscapeSeq(editionOTF.getText());
        String source = Newlibrarymanagementsystem.MysqlEscapeSeq(sourceOTF.getText());
        String fname = fnameOTF.getText();
        String cno = cnoOTF.getText();
        String address = Newlibrarymanagementsystem.MysqlEscapeSeq(addressOTF.getText());
        String gender = genderOTF.getText();
        String addon = addonOTF.getText();
        String iid;
        
        String query = "INSERT INTO issues (bid,rno,price,ion,bname,pages,isto,sid,gender) VALUES ("+bid+","+(rno.equals("") ? "null" : "'"+rno+"'")+","+(price.equals("") ? "null" : price)+",CURDATE(),'"+bname+"',"+pages+",'"+isto+"',"+(sid.equals("") ? "null" : sid)+",'"+gender+"') ;" ;
        try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "SELECT * FROM issues ORDER BY iid DESC ;" ;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            iid = rs.getObject(1).toString();
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO issuelog (bid,rno,price,ion,bname,pages,isto,sid,gender,iid,isby,period,pubname,writname,edition,source,avail,fname,cno,address,addon) VALUES ("+bid+","+(rno.equals("") ? "null" : "'"+rno+"'")+","+(price.equals("") ? "null" : price)+",CURDATE(),'"+bname+"',"+pages+",'"+isto+"',"+(sid.equals("") ? "null" : sid)+",'"+gender+"',"+iid+",'"+isby+"',null,'"+pubname+"','"+writname+"','"+edition+"','"+source+"','Y',"+(fname.equals("") ? "null" : "'"+fname+"'")+","+(cno.equals("") ? "null" : cno)+","+(address.equals("") ? "null" : "'"+address+"'")+",'"+addon+"') ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "UPDATE books SET issued = 'Y' WHERE bid = '"+bid+"' ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO evl VALUES(NOW(),'Book "+Integer.toString(Integer.parseInt(bid),26).toUpperCase()+" issued"+(rno.equals("") ? "" : " to "+rno+" ")+". Name- "+bname+"','"+isby+"') ;" ;
            stmt.executeUpdate(query);
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        IssueBookDialog.dispatchEvent(new WindowEvent(IssueBookDialog, WindowEvent.WINDOW_CLOSING));
        refresh();
        mnfobj.issuesrefresh();
        mnfobj.evlrefresh();
    }//GEN-LAST:event_IssueBookDoneBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AllBooksFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllBooksFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllBooksFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllBooksFram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBookBtn;
    private javax.swing.JDialog AddBookDialog;
    private javax.swing.JButton AddBookDoneBtn;
    private javax.swing.JButton IssueBookBtn;
    private javax.swing.JDialog IssueBookDialog;
    private javax.swing.JButton IssueBookDoneBtn;
    private javax.swing.ButtonGroup IssueSelectBtnGroup;
    private javax.swing.JCheckBox NOTCheck1;
    private javax.swing.JCheckBox NOTCheck2;
    private javax.swing.JCheckBox NOTCheck3;
    private javax.swing.JLabel OtherNameLbl;
    private javax.swing.JTextField OtherNameTF;
    private javax.swing.JRadioButton OtherRB;
    private javax.swing.JDialog ReasonDialog;
    private javax.swing.JButton RemoveBookBtn;
    private javax.swing.JComboBox<String> SearchCritCB1;
    private javax.swing.JComboBox<String> SearchCritCB2;
    private javax.swing.JComboBox<String> SearchCritCB3;
    private javax.swing.JComboBox<String> SearchFieldCB1;
    private javax.swing.JComboBox<String> SearchFieldCB2;
    private javax.swing.JComboBox<String> SearchFieldCB3;
    private javax.swing.JTextField SearchTF1;
    private javax.swing.JTextField SearchTF2;
    private javax.swing.JTextField SearchTF3;
    private javax.swing.JComboBox<String> SortCB;
    private javax.swing.JCheckBox SortDescCheck;
    private javax.swing.JRadioButton StudentRB;
    private javax.swing.JTable Tbl;
    private javax.swing.JButton ViewLogBtn;
    private javax.swing.JTextField addonOTF;
    private javax.swing.JTextField addressOTF;
    private javax.swing.JTextField bidOTF;
    private javax.swing.JLabel bnameLbl;
    private javax.swing.JTextField bnameOTF;
    private javax.swing.JTextField bnameTF;
    private javax.swing.JTextField cnoOTF;
    private javax.swing.JLabel editionLbl;
    private javax.swing.JTextField editionOTF;
    private javax.swing.JTextField editionTF;
    private javax.swing.JTextField fnameOTF;
    private javax.swing.JTextField genderOTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameOTF;
    private javax.swing.JLabel pagesLbl;
    private javax.swing.JTextField pagesOTF;
    private javax.swing.JTextField pagesTF;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JTextField priceOTF;
    private javax.swing.JTextField priceTF;
    private javax.swing.JLabel pubnameLbl;
    private javax.swing.JTextField pubnameOTF;
    private javax.swing.JTextField pubnameTF;
    private javax.swing.JLabel reasonLbl;
    private javax.swing.JTextField reasonTF;
    private javax.swing.JLabel rnoLbl;
    private javax.swing.JTextField rnoTF;
    private javax.swing.JLabel sidLbl;
    private javax.swing.JTextField sidTF;
    private javax.swing.JLabel sourceLbl;
    private javax.swing.JTextField sourceOTF;
    private javax.swing.JTextField sourceTF;
    private javax.swing.JLabel writnameLbl;
    private javax.swing.JTextField writnameOTF;
    private javax.swing.JTextField writnameTF;
    // End of variables declaration//GEN-END:variables
}
