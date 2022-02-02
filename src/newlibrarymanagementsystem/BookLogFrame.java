/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibrarymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bhavesh
 */
public class BookLogFrame extends javax.swing.JFrame {

    AllBooksFram abfobj;
    /**
     * Creates new form BookLogFrame
     * @param abfobj
     */
    public BookLogFrame(AllBooksFram abfobj) {
        initComponents();
        this.abfobj = abfobj;
        this.setLocationRelativeTo(null);
    }
    
    public String ACBIndexToColName(int i){
        String colname=null;
    switch(i){
        case 0: colname = "addon";
        break;
        case 1: colname = "CONV(bid,10,26)";
        break;
        case 2: colname = "bname";
        break;
        case 3: colname = "pages";
        break;
        case 4: colname = "pubname";
        break;
        case 5: colname = "writname";
        break;
        case 6: colname = "edition";
        break;
        case 7: colname = "price";
        break;
        case 8: colname = "addby";
        break;
        case 9: colname = "source";
        break;
    }
    return colname;
    }
    
    public void Arefresh(){
    String sfield1 = ACBIndexToColName(ASearchFieldCB1.getSelectedIndex());
    String sfield2 = ACBIndexToColName(ASearchFieldCB2.getSelectedIndex());
    String sfield3 = ACBIndexToColName(ASearchFieldCB3.getSelectedIndex());
    String scrit1 = (String)ASearchCritCB1.getSelectedItem();
    String scrit2 = (String)ASearchCritCB2.getSelectedItem();
    String scrit3 = (String)ASearchCritCB3.getSelectedItem();
    String search1 = Newlibrarymanagementsystem.MysqlEscapeSeq(ASearchTF1.getText());
    String search2 = Newlibrarymanagementsystem.MysqlEscapeSeq(ASearchTF2.getText());
    String search3 = Newlibrarymanagementsystem.MysqlEscapeSeq(ASearchTF3.getText());
     
    boolean inv1 = ANOTCheck1.isSelected();
    boolean inv2 = ANOTCheck2.isSelected();
    boolean inv3 = ANOTCheck3.isSelected();
    String sorter = ACBIndexToColName(ASortCB.getSelectedIndex());
    if(sorter.equals("CONV(bid,10,26)"))
        sorter = "bid" ;
    boolean sortdesc = ASortDescCheck.isSelected();
    String query = "SELECT addon,CONV(bid,10,26),bname,pages,pubname,writname,edition,price,addby,source FROM bookaddlog WHERE "+(inv1 ? "NOT " : "")+sfield1+" "+(scrit1.length()==1 ? scrit1 : "LIKE")+" \'"+(scrit1=="ends with" || scrit1=="includes" ? "%" : "")+search1+(scrit1=="starts with" || scrit1=="includes" ? "%" : "")+"\' AND "+(inv2 ? "NOT " : "")+sfield2+" "+(scrit2.length()==1 ? scrit2 : "LIKE")+" \'"+(scrit2=="ends with" || scrit2=="includes" ? "%" : "")+search2+(scrit2=="starts with" || scrit2=="includes" ? "%" : "")+"\' AND "+(inv3 ? "NOT " : "")+sfield3+" "+(scrit3.length()==1 ? scrit3 : "LIKE")+" \'"+(scrit3=="ends with" || scrit3=="includes" ? "%" : "")+search3+(scrit3=="starts with" || scrit3=="includes" ? "%" : "")+"\' ORDER BY "+sorter+(sortdesc ? " DESC " : " ")+";" ;
    DefaultTableModel model = (DefaultTableModel)ATbl.getModel();
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
    
    public String RCBIndexToColName(int i){
        String colname=null;
    switch(i){
        case 0: colname = "remon";
        break;
        case 1: colname = "CONV(bid,10,26)";
        break;
        case 2: colname = "bname";
        break;
        case 3: colname = "pages";
        break;
        case 4: colname = "pubname";
        break;
        case 5: colname = "writname";
        break;
        case 6: colname = "edition";
        break;
        case 7: colname = "price";
        break;
        case 8: colname = "remby";
        break;
        case 9: colname = "reason";
        break;
    }
    return colname;
    }
    
    public void Rrefresh(){
        RestoreBookBtn.setEnabled(false);
    String sfield1 = RCBIndexToColName(RSearchFieldCB1.getSelectedIndex());
    String sfield2 = RCBIndexToColName(RSearchFieldCB2.getSelectedIndex());
    String sfield3 = RCBIndexToColName(RSearchFieldCB3.getSelectedIndex());
    String scrit1 = (String)RSearchCritCB1.getSelectedItem();
    String scrit2 = (String)RSearchCritCB2.getSelectedItem();
    String scrit3 = (String)RSearchCritCB3.getSelectedItem();
    String search1 = Newlibrarymanagementsystem.MysqlEscapeSeq(RSearchTF1.getText());
    String search2 = Newlibrarymanagementsystem.MysqlEscapeSeq(RSearchTF2.getText());
    String search3 = Newlibrarymanagementsystem.MysqlEscapeSeq(RSearchTF3.getText());
     
    boolean inv1 = RNOTCheck1.isSelected();
    boolean inv2 = RNOTCheck2.isSelected();
    boolean inv3 = RNOTCheck3.isSelected();
    String sorter = RCBIndexToColName(RSortCB.getSelectedIndex());
    if(sorter.equals("CONV(bid,10,26)"))
        sorter = "bid" ;
    boolean sortdesc = RSortDescCheck.isSelected();
    String query = "SELECT remon,CONV(bid,10,26),bname,pages,pubname,writname,edition,price,remby,reason FROM bookremlog WHERE "+(inv1 ? "NOT " : "")+sfield1+" "+(scrit1.length()==1 ? scrit1 : "LIKE")+" \'"+(scrit1=="ends with" || scrit1=="includes" ? "%" : "")+search1+(scrit1=="starts with" || scrit1=="includes" ? "%" : "")+"\' AND "+(inv2 ? "NOT " : "")+sfield2+" "+(scrit2.length()==1 ? scrit2 : "LIKE")+" \'"+(scrit2=="ends with" || scrit2=="includes" ? "%" : "")+search2+(scrit2=="starts with" || scrit2=="includes" ? "%" : "")+"\' AND "+(inv3 ? "NOT " : "")+sfield3+" "+(scrit3.length()==1 ? scrit3 : "LIKE")+" \'"+(scrit3=="ends with" || scrit3=="includes" ? "%" : "")+search3+(scrit3=="starts with" || scrit3=="includes" ? "%" : "")+"\' ORDER BY "+sorter+(sortdesc ? " DESC " : " ")+";" ;
    DefaultTableModel model = (DefaultTableModel)RTbl.getModel();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        ASortDescCheck = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        ASearchFieldCB1 = new javax.swing.JComboBox<>();
        ASearchCritCB1 = new javax.swing.JComboBox<>();
        ASearchTF1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        ASearchCritCB2 = new javax.swing.JComboBox<>();
        ASearchTF2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ASearchCritCB3 = new javax.swing.JComboBox<>();
        ASearchTF3 = new javax.swing.JTextField();
        ASearchFieldCB2 = new javax.swing.JComboBox<>();
        ASearchFieldCB3 = new javax.swing.JComboBox<>();
        ANOTCheck1 = new javax.swing.JCheckBox();
        ANOTCheck2 = new javax.swing.JCheckBox();
        ANOTCheck3 = new javax.swing.JCheckBox();
        ASortCB = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ATbl = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        RSortDescCheck = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        RSearchFieldCB1 = new javax.swing.JComboBox<>();
        RSearchCritCB1 = new javax.swing.JComboBox<>();
        RSearchTF1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        RSearchCritCB2 = new javax.swing.JComboBox<>();
        RSearchTF2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        RSearchCritCB3 = new javax.swing.JComboBox<>();
        RSearchTF3 = new javax.swing.JTextField();
        RSearchFieldCB2 = new javax.swing.JComboBox<>();
        RSearchFieldCB3 = new javax.swing.JComboBox<>();
        RNOTCheck1 = new javax.swing.JCheckBox();
        RNOTCheck2 = new javax.swing.JCheckBox();
        RNOTCheck3 = new javax.swing.JCheckBox();
        RSortCB = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        RestoreBookBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        RTbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Books Log");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ASortDescCheck.setSelected(true);
        ASortDescCheck.setText("in descending order");
        ASortDescCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASortDescCheckItemStateChanged(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Criteria", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel12.setText("1");

        ASearchFieldCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Added on", "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added by", "Source" }));
        ASearchFieldCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASearchFieldCB1ItemStateChanged(evt);
            }
        });

        ASearchCritCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        ASearchCritCB1.setSelectedItem("starts with");
        ASearchCritCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASearchCritCB1ItemStateChanged(evt);
            }
        });

        ASearchTF1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ASearchTF1CaretUpdate(evt);
            }
        });

        jLabel13.setText("2");

        ASearchCritCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        ASearchCritCB2.setSelectedItem("starts with");
        ASearchCritCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASearchCritCB2ItemStateChanged(evt);
            }
        });

        ASearchTF2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ASearchTF2CaretUpdate(evt);
            }
        });

        jLabel14.setText("3");

        ASearchCritCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        ASearchCritCB3.setSelectedItem("starts with");
        ASearchCritCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASearchCritCB3ItemStateChanged(evt);
            }
        });

        ASearchTF3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ASearchTF3CaretUpdate(evt);
            }
        });

        ASearchFieldCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Added on", "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added by", "Source" }));
        ASearchFieldCB2.setSelectedItem("Book Id.");
        ASearchFieldCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASearchFieldCB2ItemStateChanged(evt);
            }
        });

        ASearchFieldCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Added on", "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added by", "Source" }));
        ASearchFieldCB3.setSelectedItem("Book Name");
        ASearchFieldCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASearchFieldCB3ItemStateChanged(evt);
            }
        });

        ANOTCheck1.setText("NOT");
        ANOTCheck1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ANOTCheck1ItemStateChanged(evt);
            }
        });

        ANOTCheck2.setText("NOT");
        ANOTCheck2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ANOTCheck2ItemStateChanged(evt);
            }
        });

        ANOTCheck3.setText("NOT");
        ANOTCheck3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ANOTCheck3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(ASearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ANOTCheck3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(ASearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ANOTCheck2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(ASearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ANOTCheck1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ASearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ASearchTF2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addComponent(ASearchTF1)
                    .addComponent(ASearchTF3))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ASearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(ASearchTF1)
                    .addComponent(ANOTCheck1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ASearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASearchTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(ANOTCheck2))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ASearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ASearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(ANOTCheck3))
                    .addComponent(ASearchTF3))
                .addGap(336, 336, 336))
        );

        ASortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Added on", "Book Id.", "Book Name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added by", "Source" }));
        ASortCB.setSelectedItem("Book Id.");
        ASortCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ASortCBItemStateChanged(evt);
            }
        });

        jLabel15.setText("Sort by");

        ATbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Added on", "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Added by", "Source"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ATbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ATbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ATbl.getTableHeader().setReorderingAllowed(false);
        ATbl.setCellSelectionEnabled(true);
        jScrollPane3.setViewportView(ATbl);
        if (ATbl.getColumnModel().getColumnCount() > 0) {
            ATbl.getColumnModel().getColumn(0).setPreferredWidth(25);
            ATbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            ATbl.getColumnModel().getColumn(2).setPreferredWidth(230);
            ATbl.getColumnModel().getColumn(3).setPreferredWidth(3);
            ATbl.getColumnModel().getColumn(6).setPreferredWidth(30);
            ATbl.getColumnModel().getColumn(7).setPreferredWidth(8);
            ATbl.getColumnModel().getColumn(8).setPreferredWidth(30);
            ATbl.getColumnModel().getColumn(9).setPreferredWidth(120);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(41, 41, 41)
                        .addComponent(ASortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(ASortDescCheck)
                        .addGap(131, 131, 131))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ASortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(ASortDescCheck))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Added", jPanel8);

        RSortDescCheck.setSelected(true);
        RSortDescCheck.setText("in descending order");
        RSortDescCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSortDescCheckItemStateChanged(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Criteria", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel20.setText("1");

        RSearchFieldCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Removed on", "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Removed by", "Reason" }));
        RSearchFieldCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchFieldCB1ItemStateChanged(evt);
            }
        });

        RSearchCritCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        RSearchCritCB1.setSelectedItem("starts with");
        RSearchCritCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchCritCB1ItemStateChanged(evt);
            }
        });

        RSearchTF1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                RSearchTF1CaretUpdate(evt);
            }
        });

        jLabel21.setText("2");

        RSearchCritCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        RSearchCritCB2.setSelectedItem("starts with");
        RSearchCritCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchCritCB2ItemStateChanged(evt);
            }
        });

        RSearchTF2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                RSearchTF2CaretUpdate(evt);
            }
        });

        jLabel22.setText("3");

        RSearchCritCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        RSearchCritCB3.setSelectedItem("starts with");
        RSearchCritCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchCritCB3ItemStateChanged(evt);
            }
        });

        RSearchTF3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                RSearchTF3CaretUpdate(evt);
            }
        });

        RSearchFieldCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Removed on", "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Removed by", "Reason" }));
        RSearchFieldCB2.setSelectedItem("Book Id.");
        RSearchFieldCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchFieldCB2ItemStateChanged(evt);
            }
        });

        RSearchFieldCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Removed on", "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Removed by", "Reason" }));
        RSearchFieldCB3.setSelectedItem("Book name");
        RSearchFieldCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchFieldCB3ItemStateChanged(evt);
            }
        });

        RNOTCheck1.setText("NOT");
        RNOTCheck1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RNOTCheck1ItemStateChanged(evt);
            }
        });

        RNOTCheck2.setText("NOT");
        RNOTCheck2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RNOTCheck2ItemStateChanged(evt);
            }
        });

        RNOTCheck3.setText("NOT");
        RNOTCheck3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RNOTCheck3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(33, 33, 33)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RSearchFieldCB2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RSearchFieldCB1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RSearchFieldCB3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RNOTCheck1)
                    .addComponent(RNOTCheck2)
                    .addComponent(RNOTCheck3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RSearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RSearchTF2, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                    .addComponent(RSearchTF1)
                    .addComponent(RSearchTF3))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RSearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(RSearchTF1)
                    .addComponent(RNOTCheck1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RSearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(RNOTCheck2))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RSearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RSearchTF3)
                        .addComponent(RSearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RNOTCheck3)))
                .addGap(336, 336, 336))
        );

        RSortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Removed on", "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Removed by", "Reason" }));
        RSortCB.setSelectedItem("Book Id.");
        RSortCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSortCBItemStateChanged(evt);
            }
        });

        jLabel23.setText("Sort by");

        RestoreBookBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        RestoreBookBtn.setText("Restore Book");
        RestoreBookBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestoreBookBtnActionPerformed(evt);
            }
        });

        RTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Removed on", "Book Id.", "Book name", "Pages", "Publisher", "Writer", "Edition", "Price", "Removed by", "Reason"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RTbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RTbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        RTbl.getTableHeader().setReorderingAllowed(false);
        RTbl.setCellSelectionEnabled(true);
        RTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RTblMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(RTbl);
        if (RTbl.getColumnModel().getColumnCount() > 0) {
            RTbl.getColumnModel().getColumn(0).setPreferredWidth(25);
            RTbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            RTbl.getColumnModel().getColumn(2).setPreferredWidth(230);
            RTbl.getColumnModel().getColumn(3).setPreferredWidth(3);
            RTbl.getColumnModel().getColumn(6).setPreferredWidth(30);
            RTbl.getColumnModel().getColumn(7).setPreferredWidth(8);
            RTbl.getColumnModel().getColumn(8).setPreferredWidth(30);
            RTbl.getColumnModel().getColumn(9).setPreferredWidth(120);
        }

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(RSortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(RSortDescCheck))
                            .addComponent(RestoreBookBtn))
                        .addGap(131, 131, 131))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RSortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(RSortDescCheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RestoreBookBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Removed", jPanel12);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        abfobj.blogbtnen();
        abfobj.refresh();
    }//GEN-LAST:event_formWindowClosed

    private void ASortDescCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASortDescCheckItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASortDescCheckItemStateChanged

    private void ASortCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASortCBItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASortCBItemStateChanged

    private void ASearchTF1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ASearchTF1CaretUpdate
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchTF1CaretUpdate

    private void ASearchTF2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ASearchTF2CaretUpdate
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchTF2CaretUpdate

    private void ASearchTF3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ASearchTF3CaretUpdate
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchTF3CaretUpdate

    private void ASearchCritCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASearchCritCB1ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchCritCB1ItemStateChanged

    private void ASearchCritCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASearchCritCB2ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchCritCB2ItemStateChanged

    private void ASearchCritCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASearchCritCB3ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchCritCB3ItemStateChanged

    private void ANOTCheck1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ANOTCheck1ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ANOTCheck1ItemStateChanged

    private void ANOTCheck2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ANOTCheck2ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ANOTCheck2ItemStateChanged

    private void ANOTCheck3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ANOTCheck3ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ANOTCheck3ItemStateChanged

    private void ASearchFieldCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASearchFieldCB1ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchFieldCB1ItemStateChanged

    private void ASearchFieldCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASearchFieldCB2ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchFieldCB2ItemStateChanged

    private void ASearchFieldCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ASearchFieldCB3ItemStateChanged
        // TODO add your handling code here:
        Arefresh();
    }//GEN-LAST:event_ASearchFieldCB3ItemStateChanged

    private void RSortDescCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSortDescCheckItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSortDescCheckItemStateChanged

    private void RSortCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSortCBItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSortCBItemStateChanged

    private void RSearchTF1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_RSearchTF1CaretUpdate
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchTF1CaretUpdate

    private void RSearchTF2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_RSearchTF2CaretUpdate
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchTF2CaretUpdate

    private void RSearchTF3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_RSearchTF3CaretUpdate
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchTF3CaretUpdate

    private void RSearchCritCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSearchCritCB1ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchCritCB1ItemStateChanged

    private void RSearchCritCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSearchCritCB2ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchCritCB2ItemStateChanged

    private void RSearchCritCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSearchCritCB3ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchCritCB3ItemStateChanged

    private void RNOTCheck1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RNOTCheck1ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RNOTCheck1ItemStateChanged

    private void RNOTCheck2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RNOTCheck2ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RNOTCheck2ItemStateChanged

    private void RNOTCheck3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RNOTCheck3ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RNOTCheck3ItemStateChanged

    private void RSearchFieldCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSearchFieldCB1ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchFieldCB1ItemStateChanged

    private void RSearchFieldCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSearchFieldCB2ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchFieldCB2ItemStateChanged

    private void RSearchFieldCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RSearchFieldCB3ItemStateChanged
        // TODO add your handling code here:
        Rrefresh();
    }//GEN-LAST:event_RSearchFieldCB3ItemStateChanged

    private void RTblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RTblMouseReleased
        // TODO add your handling code here:
        if(RTbl.getSelectedRowCount()>0)
            RestoreBookBtn.setEnabled(true);
        else RestoreBookBtn.setEnabled(false);
    }//GEN-LAST:event_RTblMouseReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        ATbl.setCellSelectionEnabled(true);
        ATbl.setColumnSelectionAllowed(true);
        RTbl.setCellSelectionEnabled(true);
        RTbl.setColumnSelectionAllowed(true);
        Arefresh();
        Rrefresh();
    }//GEN-LAST:event_formWindowOpened

    private void RestoreBookBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreBookBtnActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null,"Restore selected book ?");
        if(choice==JOptionPane.YES_OPTION){
        String bid = RTbl.getValueAt(RTbl.getSelectedRow(),1).toString();
        String bname = Newlibrarymanagementsystem.MysqlEscapeSeq(RTbl.getValueAt(RTbl.getSelectedRow(),2).toString());
        String pages = RTbl.getValueAt(RTbl.getSelectedRow(),3).toString();
        String pubname = Newlibrarymanagementsystem.MysqlEscapeSeq(RTbl.getValueAt(RTbl.getSelectedRow(),4).toString());
        String writname = Newlibrarymanagementsystem.MysqlEscapeSeq(RTbl.getValueAt(RTbl.getSelectedRow(),5).toString());
        String edition = Newlibrarymanagementsystem.MysqlEscapeSeq(RTbl.getValueAt(RTbl.getSelectedRow(),6).toString());
        bid = (""+Integer.parseInt(bid,26));
        String price = "";
        Object priceO = RTbl.getValueAt(RTbl.getSelectedRow(),7);
        if(priceO!=null)
            price = priceO.toString();
        String source;
        String addon;
        
        String query = "SELECT source,addon FROM bookremlog WHERE bid = '"+bid+"' ;" ;
        try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            source = Newlibrarymanagementsystem.MysqlEscapeSeq(rs.getObject(1).toString());
            addon = Newlibrarymanagementsystem.MysqlEscapeSeq(rs.getObject(2).toString());
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "DELETE FROM bookremlog WHERE bid = '"+bid+"' ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO books (bname,bid,pages,pubname,writname,edition,price,addon,source,issued) VALUES('"+bname+"',"+bid+","+pages+",'"+pubname+"','"+writname+"','"+edition+"',"+(price.length()==0 ? null : price)+",'"+addon+"','"+source+"','N') ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "INSERT INTO evl VALUES(NOW(),'book "+Integer.toString(Integer.parseInt(bid),26).toUpperCase()+" restored. Name- "+bname+"','"+abfobj.LoggedInUserName+"') ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "UPDATE issuelog SET avail = 'Y' WHERE bid = '"+bid+"' ;" ;
            stmt.executeUpdate(query);
            stmt= (com.mysql.jdbc.Statement) c.createStatement();
            query = "UPDATE returnlog SET avail = 'Y' WHERE bid = '"+bid+"' ;" ;
            stmt.executeUpdate(query);
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        Rrefresh();
        abfobj.refresh();
        abfobj.evlrefresh();
        }
    }//GEN-LAST:event_RestoreBookBtnActionPerformed

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
            java.util.logging.Logger.getLogger(BookLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ANOTCheck1;
    private javax.swing.JCheckBox ANOTCheck2;
    private javax.swing.JCheckBox ANOTCheck3;
    private javax.swing.JComboBox<String> ASearchCritCB1;
    private javax.swing.JComboBox<String> ASearchCritCB2;
    private javax.swing.JComboBox<String> ASearchCritCB3;
    private javax.swing.JComboBox<String> ASearchFieldCB1;
    private javax.swing.JComboBox<String> ASearchFieldCB2;
    private javax.swing.JComboBox<String> ASearchFieldCB3;
    private javax.swing.JTextField ASearchTF1;
    private javax.swing.JTextField ASearchTF2;
    private javax.swing.JTextField ASearchTF3;
    private javax.swing.JComboBox<String> ASortCB;
    private javax.swing.JCheckBox ASortDescCheck;
    private javax.swing.JTable ATbl;
    private javax.swing.JCheckBox RNOTCheck1;
    private javax.swing.JCheckBox RNOTCheck2;
    private javax.swing.JCheckBox RNOTCheck3;
    private javax.swing.JComboBox<String> RSearchCritCB1;
    private javax.swing.JComboBox<String> RSearchCritCB2;
    private javax.swing.JComboBox<String> RSearchCritCB3;
    private javax.swing.JComboBox<String> RSearchFieldCB1;
    private javax.swing.JComboBox<String> RSearchFieldCB2;
    private javax.swing.JComboBox<String> RSearchFieldCB3;
    private javax.swing.JTextField RSearchTF1;
    private javax.swing.JTextField RSearchTF2;
    private javax.swing.JTextField RSearchTF3;
    private javax.swing.JComboBox<String> RSortCB;
    private javax.swing.JCheckBox RSortDescCheck;
    private javax.swing.JTable RTbl;
    private javax.swing.JButton RestoreBookBtn;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables
}
