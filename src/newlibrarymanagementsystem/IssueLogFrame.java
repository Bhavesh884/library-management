/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlibrarymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bhavesh
 */
public class IssueLogFrame extends javax.swing.JFrame {

    IssuesFrame isfobj;
    /**
     * Creates new form IssueLogFrame
     * @param isfobj
     */
    public IssueLogFrame(IssuesFrame isfobj) {
        initComponents();
        this.isfobj = isfobj;
        this.setLocationRelativeTo(null);
    }
    
    public String ICBIndexToColName(int i){
        String colname=null;
    switch(i){
        case 0: colname = "ion";
        break;
        case 1: colname = "CONV(iid,10,27)";
        break;
        case 2: colname = "CONV(bid,10,26)";
        break;
        case 3: colname = "rno";
        break;
        case 4: colname = "gender";
        break;
        case 5: colname = "price";
        break;
        case 6: colname = "isby";
        break;
        case 7: colname = "period";
        break;
        case 8: colname = "bname";
        break;
        case 9: colname = "pages";
        break;
        case 10: colname = "isto";
        break;
        case 11: colname = "CONV(sid,10,18)";
        break;
    }
    return colname;
    }
    
    public void Irefresh(){
        IDetailsBtn.setEnabled(false);
    String sfield1 = ICBIndexToColName(ISearchFieldCB1.getSelectedIndex());
    String sfield2 = ICBIndexToColName(ISearchFieldCB2.getSelectedIndex());
    String sfield3 = ICBIndexToColName(ISearchFieldCB3.getSelectedIndex());
    String scrit1 = (String)ISearchCritCB1.getSelectedItem();
    String scrit2 = (String)ISearchCritCB2.getSelectedItem();
    String scrit3 = (String)ISearchCritCB3.getSelectedItem();
    String search1 = Newlibrarymanagementsystem.MysqlEscapeSeq(ISearchTF1.getText());
    String search2 = Newlibrarymanagementsystem.MysqlEscapeSeq(ISearchTF2.getText());
    String search3 = Newlibrarymanagementsystem.MysqlEscapeSeq(ISearchTF3.getText());
    
    boolean inv1 = INOTCheck1.isSelected();
    boolean inv2 = INOTCheck2.isSelected();
    boolean inv3 = INOTCheck3.isSelected();
    String sorter = ICBIndexToColName(ISortCB.getSelectedIndex());
    if(sorter.equals("CONV(sid,10,18)"))
        sorter = "sid" ;
    if(sorter.equals("CONV(bid,10,26)"))
        sorter = "bid" ;
    if(sorter.equals("CONV(iid,10,27)"))
        sorter = "iid" ;
    boolean sortdesc = ISortDescCheck.isSelected();
    String query = "SELECT ion,CONV(iid,10,27),CONV(bid,10,26),rno,gender,price,isby,period,bname,pages,isto,CONV(sid,10,18) FROM issuelog WHERE "+(inv1 ? "NOT " : "")+sfield1+" "+(scrit1.length()==1 ? scrit1 : "LIKE")+" \'"+(scrit1=="ends with" || scrit1=="includes" ? "%" : "")+search1+(scrit1=="starts with" || scrit1=="includes" ? "%" : "")+"\' AND "+(inv2 ? "NOT " : "")+sfield2+" "+(scrit2.length()==1 ? scrit2 : "LIKE")+" \'"+(scrit2=="ends with" || scrit2=="includes" ? "%" : "")+search2+(scrit2=="starts with" || scrit2=="includes" ? "%" : "")+"\' AND "+(inv3 ? "NOT " : "")+sfield3+" "+(scrit3.length()==1 ? scrit3 : "LIKE")+" \'"+(scrit3=="ends with" || scrit3=="includes" ? "%" : "")+search3+(scrit3=="starts with" || scrit3=="includes" ? "%" : "")+"\' ORDER BY "+sorter+(sortdesc ? " DESC " : " ")+";" ;
    DefaultTableModel model = (DefaultTableModel)ITbl.getModel();
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
    Object[] tuple = new Object[12];
    while(rs.next()){
    for(int i=1 ; i<=12; i++)
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
        case 0: colname = "ron";
        break;
        case 1: colname = "CONV(iid,10,27)";
        break;
        case 2: colname = "CONV(bid,10,26)";
        break;
        case 3: colname = "rno";
        break;
        case 4: colname = "gender";
        break;
        case 5: colname = "price";
        break;
        case 6: colname = "reto";
        break;
        case 7: colname = "period";
        break;
        case 8: colname = "bname";
        break;
        case 9: colname = "pages";
        break;
        case 10: colname = "reby";
        break;
        case 11: colname = "CONV(sid,10,18)";
        break;
    }
    return colname;
    }
    
    public void Rrefresh(){
        RDetailsBtn.setEnabled(false);
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
    if(sorter.equals("CONV(sid,10,18)"))
        sorter = "sid" ;
    if(sorter.equals("CONV(bid,10,26)"))
        sorter = "bid" ;
    if(sorter.equals("CONV(iid,10,27)"))
        sorter = "iid" ;
    boolean sortdesc = RSortDescCheck.isSelected();
    String query = "SELECT ron,CONV(iid,10,27),CONV(bid,10,26),rno,gender,price,reto,period,bname,pages,reby,CONV(sid,10,18) FROM returnlog WHERE "+(inv1 ? "NOT " : "")+sfield1+" "+(scrit1.length()==1 ? scrit1 : "LIKE")+" \'"+(scrit1=="ends with" || scrit1=="includes" ? "%" : "")+search1+(scrit1=="starts with" || scrit1=="includes" ? "%" : "")+"\' AND "+(inv2 ? "NOT " : "")+sfield2+" "+(scrit2.length()==1 ? scrit2 : "LIKE")+" \'"+(scrit2=="ends with" || scrit2=="includes" ? "%" : "")+search2+(scrit2=="starts with" || scrit2=="includes" ? "%" : "")+"\' AND "+(inv3 ? "NOT " : "")+sfield3+" "+(scrit3.length()==1 ? scrit3 : "LIKE")+" \'"+(scrit3=="ends with" || scrit3=="includes" ? "%" : "")+search3+(scrit3=="starts with" || scrit3=="includes" ? "%" : "")+"\' ORDER BY "+sorter+(sortdesc ? " DESC " : " ")+";" ;
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
    Object[] tuple = new Object[12];
    while(rs.next()){
    for(int i=1 ; i<=12; i++)
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
    
    public String[] ViewDetailsDialogDat(Object iid,boolean iss){
    String query = "SELECT * FROM "+(iss ? "issue" : "return")+"log WHERE CONV(iid,10,27) = \'"+((String)iid)+"\' ;" ;
    String[] tuple = new String[21];
    try{
    Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/newlibman";
            Connection c=DriverManager.getConnection(url,"root","123");
            com.mysql.jdbc.Statement stmt= (com.mysql.jdbc.Statement) c.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    rs.next();
    for(int i=1 ; i<=21; i++)
        if(rs.getObject(i)!=null)
    tuple[i-1] = (rs.getObject(i)).toString();
    rs.close();
    stmt.close();
    c.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
    tuple[1] = Integer.toString(Integer.parseInt((String)tuple[1],10),27).toUpperCase();
    tuple[2] = Integer.toString(Integer.parseInt((String)tuple[2],10),26).toUpperCase();
    if(tuple[10]!=null)
        tuple[10] = Integer.toString(Integer.parseInt((String)tuple[10],10),18).toUpperCase();
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

        ViewDetailsDialog = new javax.swing.JDialog();
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
        jLabel61 = new javax.swing.JLabel();
        availOTF = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
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
        jLabel59 = new javax.swing.JLabel();
        rnoOTF = new javax.swing.JTextField();
        sidOTF = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        OtherRB = new javax.swing.JRadioButton();
        OtherNameOTF = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        StudentRB = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        retoOTF = new javax.swing.JTextField();
        retoLbl = new javax.swing.JLabel();
        ronOTF = new javax.swing.JTextField();
        ronLbl = new javax.swing.JLabel();
        iidOTF = new javax.swing.JTextField();
        ronLbl1 = new javax.swing.JLabel();
        periodOTF = new javax.swing.JTextField();
        ronLbl2 = new javax.swing.JLabel();
        BtnGroup = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        ISortDescCheck = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        ISearchFieldCB1 = new javax.swing.JComboBox<>();
        ISearchCritCB1 = new javax.swing.JComboBox<>();
        ISearchTF1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        ISearchCritCB2 = new javax.swing.JComboBox<>();
        ISearchTF2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ISearchCritCB3 = new javax.swing.JComboBox<>();
        ISearchTF3 = new javax.swing.JTextField();
        ISearchFieldCB2 = new javax.swing.JComboBox<>();
        ISearchFieldCB3 = new javax.swing.JComboBox<>();
        INOTCheck1 = new javax.swing.JCheckBox();
        INOTCheck2 = new javax.swing.JCheckBox();
        INOTCheck3 = new javax.swing.JCheckBox();
        ISortCB = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ITbl = new javax.swing.JTable();
        IDetailsBtn = new javax.swing.JButton();
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
        RSearchTF3 = new javax.swing.JTextField();
        RSearchFieldCB2 = new javax.swing.JComboBox<>();
        RSearchFieldCB3 = new javax.swing.JComboBox<>();
        RNOTCheck1 = new javax.swing.JCheckBox();
        RNOTCheck2 = new javax.swing.JCheckBox();
        RNOTCheck3 = new javax.swing.JCheckBox();
        RSearchCritCB3 = new javax.swing.JComboBox<>();
        RSortCB = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        RTbl = new javax.swing.JTable();
        RDetailsBtn = new javax.swing.JButton();

        ViewDetailsDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ViewDetailsDialog.setTitle("Log Details");
        ViewDetailsDialog.setAlwaysOnTop(true);
        ViewDetailsDialog.setResizable(false);
        ViewDetailsDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                ViewDetailsDialogWindowClosed(evt);
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

        jLabel61.setText("Available-");

        availOTF.setEditable(false);

        jLabel64.setText("Added on-");

        addonOTF.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel50)
                .addGap(18, 18, 18)
                .addComponent(pagesOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(priceOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
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
                        .addGap(53, 53, 53)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addonOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(availOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addonOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(availOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)))
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

        jLabel59.setText("Roll no-");

        rnoOTF.setEditable(false);

        sidOTF.setEditable(false);

        jLabel62.setText("Student Id-");

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
                                .addGap(77, 77, 77)
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rnoOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sidOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE))))
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
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(nameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sidOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel62))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(rnoOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(genderOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jLabel60.setText("Issued to-");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnGroup.add(OtherRB);
        OtherRB.setText("Other");
        OtherRB.setEnabled(false);

        OtherNameOTF.setEditable(false);

        jLabel63.setText("Name of person/organization-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63)
                        .addGap(18, 18, 18)
                        .addComponent(OtherNameOTF))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(OtherRB)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(OtherRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OtherNameOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BtnGroup.add(StudentRB);
        StudentRB.setSelected(true);
        StudentRB.setText("Student");
        StudentRB.setEnabled(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StudentRB)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(StudentRB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        retoOTF.setEditable(false);

        retoLbl.setText("Returned to-");

        ronOTF.setEditable(false);

        ronLbl.setText("Returned on-");

        iidOTF.setEditable(false);

        ronLbl1.setText("Issue Id-");

        periodOTF.setEditable(false);

        ronLbl2.setText("Period-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ronLbl1)
                    .addComponent(retoLbl)
                    .addComponent(ronLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(iidOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ronLbl2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(periodOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(retoOTF, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ronOTF)
                        .addGap(127, 127, 127))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ronOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ronLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(retoLbl)
                    .addComponent(retoOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(periodOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ronLbl2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(iidOTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ronLbl1)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ViewDetailsDialogLayout = new javax.swing.GroupLayout(ViewDetailsDialog.getContentPane());
        ViewDetailsDialog.getContentPane().setLayout(ViewDetailsDialogLayout);
        ViewDetailsDialogLayout.setHorizontalGroup(
            ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewDetailsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewDetailsDialogLayout.createSequentialGroup()
                        .addGap(553, 553, 553)
                        .addComponent(jLabel60))
                    .addGroup(ViewDetailsDialogLayout.createSequentialGroup()
                        .addGroup(ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ViewDetailsDialogLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        ViewDetailsDialogLayout.setVerticalGroup(
            ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewDetailsDialogLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ViewDetailsDialogLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(23, 23, 23)
                        .addGroup(ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(ViewDetailsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Issues Log");
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

        ISortDescCheck.setSelected(true);
        ISortDescCheck.setText("in descending order");
        ISortDescCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISortDescCheckItemStateChanged(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Criteria", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel12.setText("1");

        ISearchFieldCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Issued on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Issued by", "Period", "Book name", "Pages", "Issued to", "Student Id." }));
        ISearchFieldCB1.setSelectedItem("Issued on");
        ISearchFieldCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISearchFieldCB1ItemStateChanged(evt);
            }
        });

        ISearchCritCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        ISearchCritCB1.setSelectedItem("starts with");
        ISearchCritCB1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISearchCritCB1ItemStateChanged(evt);
            }
        });

        ISearchTF1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ISearchTF1CaretUpdate(evt);
            }
        });

        jLabel13.setText("2");

        ISearchCritCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        ISearchCritCB2.setSelectedItem("starts with");
        ISearchCritCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISearchCritCB2ItemStateChanged(evt);
            }
        });

        ISearchTF2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ISearchTF2CaretUpdate(evt);
            }
        });

        jLabel14.setText("3");

        ISearchCritCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        ISearchCritCB3.setSelectedItem("starts with");
        ISearchCritCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISearchCritCB3ItemStateChanged(evt);
            }
        });

        ISearchTF3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ISearchTF3CaretUpdate(evt);
            }
        });

        ISearchFieldCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Issued on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Issued by", "Period", "Book name", "Pages", "Issued to", "Student Id." }));
        ISearchFieldCB2.setSelectedItem("Book Id.");
        ISearchFieldCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISearchFieldCB2ItemStateChanged(evt);
            }
        });

        ISearchFieldCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Issued on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Issued by", "Period", "Book name", "Pages", "Issued to", "Student Id." }));
        ISearchFieldCB3.setSelectedItem("Book name");
        ISearchFieldCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISearchFieldCB3ItemStateChanged(evt);
            }
        });

        INOTCheck1.setText("NOT");
        INOTCheck1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                INOTCheck1ItemStateChanged(evt);
            }
        });

        INOTCheck2.setText("NOT");
        INOTCheck2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                INOTCheck2ItemStateChanged(evt);
            }
        });

        INOTCheck3.setText("NOT");
        INOTCheck3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                INOTCheck3ItemStateChanged(evt);
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
                        .addComponent(ISearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(INOTCheck3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(ISearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(INOTCheck2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(ISearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(INOTCheck1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ISearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ISearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ISearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ISearchTF2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(ISearchTF1)
                    .addComponent(ISearchTF3))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(ISearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ISearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ISearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ISearchFieldCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(ISearchTF1)
                            .addComponent(INOTCheck1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ISearchFieldCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ISearchTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(INOTCheck2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ISearchFieldCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(INOTCheck3))
                            .addComponent(ISearchTF3))))
                .addGap(336, 336, 336))
        );

        ISortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Issued on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Issued by", "Period", "Book name", "Pages", "Issued to", "Student Id." }));
        ISortCB.setSelectedItem("Issue Id.");
        ISortCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ISortCBItemStateChanged(evt);
            }
        });

        jLabel15.setText("Sort by");

        ITbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Issued on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Issued by", "Period", "Book name", "Pages", "Issued to", "Student Id."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ITbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ITbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ITbl.getTableHeader().setReorderingAllowed(false);
        ITbl.setCellSelectionEnabled(true);
        ITbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ITblMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(ITbl);
        if (ITbl.getColumnModel().getColumnCount() > 0) {
            ITbl.getColumnModel().getColumn(0).setPreferredWidth(170);
            ITbl.getColumnModel().getColumn(1).setPreferredWidth(140);
            ITbl.getColumnModel().getColumn(2).setPreferredWidth(130);
            ITbl.getColumnModel().getColumn(3).setPreferredWidth(120);
            ITbl.getColumnModel().getColumn(4).setPreferredWidth(110);
            ITbl.getColumnModel().getColumn(5).setPreferredWidth(150);
            ITbl.getColumnModel().getColumn(6).setPreferredWidth(150);
            ITbl.getColumnModel().getColumn(7).setPreferredWidth(100);
            ITbl.getColumnModel().getColumn(8).setPreferredWidth(670);
            ITbl.getColumnModel().getColumn(9).setPreferredWidth(110);
            ITbl.getColumnModel().getColumn(10).setPreferredWidth(260);
            ITbl.getColumnModel().getColumn(11).setPreferredWidth(180);
        }

        IDetailsBtn.setText("View more details");
        IDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDetailsBtnActionPerformed(evt);
            }
        });

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
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(ISortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(ISortDescCheck))
                            .addComponent(IDetailsBtn))
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
                            .addComponent(ISortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(ISortDescCheck))
                        .addGap(55, 55, 55)
                        .addComponent(IDetailsBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Issued", jPanel8);

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

        RSearchFieldCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Returned on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Returned to", "Period", "Book name", "Pages", "Returned by", "Student Id." }));
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

        RSearchTF3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                RSearchTF3CaretUpdate(evt);
            }
        });

        RSearchFieldCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Returned on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Returned to", "Period", "Book name", "Pages", "Returned by", "Student Id." }));
        RSearchFieldCB2.setSelectedItem("Book Id.");
        RSearchFieldCB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchFieldCB2ItemStateChanged(evt);
            }
        });

        RSearchFieldCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Returned on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Returned to", "Period", "Book name", "Pages", "Returned by", "Student Id." }));
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

        RSearchCritCB3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", "<", ">", "starts with", "ends with", "includes" }));
        RSearchCritCB3.setSelectedItem("starts with");
        RSearchCritCB3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSearchCritCB3ItemStateChanged(evt);
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
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RSearchCritCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchCritCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RSearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RSearchTF2, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
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
                        .addComponent(RNOTCheck3)
                        .addComponent(RSearchCritCB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(336, 336, 336))
        );

        RSortCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Returned on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Returned to", "Period", "Book name", "Pages", "Returned by", "Student Id." }));
        RSortCB.setSelectedItem("Returned on");
        RSortCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RSortCBItemStateChanged(evt);
            }
        });

        jLabel23.setText("Sort by");

        RTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Returned on", "Issue Id.", "Book Id.", "Roll no.", "Gender", "Price", "Returned to", "Period", "Book name", "Pages", "Returned by", "Student Id."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
            RTbl.getColumnModel().getColumn(0).setPreferredWidth(170);
            RTbl.getColumnModel().getColumn(1).setPreferredWidth(140);
            RTbl.getColumnModel().getColumn(2).setPreferredWidth(130);
            RTbl.getColumnModel().getColumn(3).setPreferredWidth(120);
            RTbl.getColumnModel().getColumn(4).setPreferredWidth(110);
            RTbl.getColumnModel().getColumn(5).setPreferredWidth(150);
            RTbl.getColumnModel().getColumn(6).setPreferredWidth(150);
            RTbl.getColumnModel().getColumn(7).setPreferredWidth(100);
            RTbl.getColumnModel().getColumn(8).setPreferredWidth(670);
            RTbl.getColumnModel().getColumn(9).setPreferredWidth(110);
            RTbl.getColumnModel().getColumn(10).setPreferredWidth(260);
            RTbl.getColumnModel().getColumn(11).setPreferredWidth(180);
        }

        RDetailsBtn.setText("View more details");
        RDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDetailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(RDetailsBtn))
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RSortCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(RSortDescCheck))
                        .addGap(55, 55, 55)
                        .addComponent(RDetailsBtn)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Returned", jPanel12);

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

    public void ViewDetailsDialogreset(){
    OtherRB.setSelected(true);
    ronOTF.setText("");
    retoOTF.setText("");
    OtherNameOTF.setText("");
    nameOTF.setText("");
    genderOTF.setText("");
    rnoOTF.setText("");
    sidOTF.setText("");
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
    iidOTF.setText("");
    availOTF.setText("");
    addonOTF.setText("");
    ronLbl.setText("Returned on");
    retoLbl.setText("Returned to");
    }
    
    public void ViewDetailsDialogset(String[] tuple,boolean iss){
        
    if(iss){
    ronLbl.setText("Issued on");
    retoLbl.setText("Issued by");
    }
    
    ronOTF.setText(tuple[0]);
    iidOTF.setText(tuple[1]);
    bidOTF.setText(tuple[2]);
    rnoOTF.setText(tuple[3]);
    priceOTF.setText(tuple[4]);
    retoOTF.setText(tuple[5]);
    periodOTF.setText(tuple[6]);
    bnameOTF.setText(tuple[7]);
    pagesOTF.setText(tuple[8]);
    pubnameOTF.setText(tuple[11]);
    writnameOTF.setText(tuple[12]);
    editionOTF.setText(tuple[13]);
    sourceOTF.setText(tuple[14]);
    availOTF.setText(tuple[15]);
    addonOTF.setText(tuple[20]);
    
    if(tuple[10]!=null){
    StudentRB.setSelected(true);
    nameOTF.setText(tuple[9]);
    sidOTF.setText(tuple[10]);
    fnameOTF.setText(tuple[16]);
    cnoOTF.setText(tuple[17]);
    addressOTF.setText(tuple[18]);
    genderOTF.setText(tuple[19]);
    }
    else
    {
        OtherRB.setSelected(true);
    OtherNameOTF.setText(tuple[9]);
    }
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        isfobj.islogbtnen();
    }//GEN-LAST:event_formWindowClosed

    private void ISortDescCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISortDescCheckItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISortDescCheckItemStateChanged

    private void ISortCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISortCBItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISortCBItemStateChanged

    private void ISearchTF1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ISearchTF1CaretUpdate
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchTF1CaretUpdate

    private void ISearchTF2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ISearchTF2CaretUpdate
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchTF2CaretUpdate

    private void ISearchTF3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ISearchTF3CaretUpdate
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchTF3CaretUpdate

    private void ISearchCritCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISearchCritCB1ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchCritCB1ItemStateChanged

    private void ISearchCritCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISearchCritCB2ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchCritCB2ItemStateChanged

    private void ISearchCritCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISearchCritCB3ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchCritCB3ItemStateChanged

    private void INOTCheck1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_INOTCheck1ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_INOTCheck1ItemStateChanged

    private void INOTCheck2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_INOTCheck2ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_INOTCheck2ItemStateChanged

    private void INOTCheck3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_INOTCheck3ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_INOTCheck3ItemStateChanged

    private void ISearchFieldCB1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISearchFieldCB1ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchFieldCB1ItemStateChanged

    private void ISearchFieldCB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISearchFieldCB2ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchFieldCB2ItemStateChanged

    private void ISearchFieldCB3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ISearchFieldCB3ItemStateChanged
        // TODO add your handling code here:
        Irefresh();
    }//GEN-LAST:event_ISearchFieldCB3ItemStateChanged

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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        ITbl.setColumnSelectionAllowed(true);
        ITbl.setCellSelectionEnabled(true);
        RTbl.setColumnSelectionAllowed(true);
        RTbl.setCellSelectionEnabled(true);
        Irefresh();
        Rrefresh();
    }//GEN-LAST:event_formWindowOpened

    private void RTblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RTblMouseReleased
        // TODO add your handling code here:
        if(RTbl.getSelectedRowCount()>0)
            RDetailsBtn.setEnabled(true);
        else RDetailsBtn.setEnabled(false);
    }//GEN-LAST:event_RTblMouseReleased

    private void ITblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ITblMouseReleased
        // TODO add your handling code here:
        if(ITbl.getSelectedRowCount()>0)
            IDetailsBtn.setEnabled(true);
        else IDetailsBtn.setEnabled(false);
    }//GEN-LAST:event_ITblMouseReleased

    private void ViewDetailsDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ViewDetailsDialogWindowClosed
        // TODO add your handling code here:
        ViewDetailsDialogreset();
        isfobj.framesfocbackref();
        this.setEnabled(true);
        this.requestFocus();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_ViewDetailsDialogWindowClosed

    private void IDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDetailsBtnActionPerformed
        // TODO add your handling code here:
        isfobj.framesfocloseref();
        ViewDetailsDialog.pack();
        ViewDetailsDialog.setVisible(true);
        this.setEnabled(false);
        ViewDetailsDialog.setLocationRelativeTo(null);
        
        Object iid = ITbl.getValueAt(ITbl.getSelectedRow(),1);
        ViewDetailsDialogset(ViewDetailsDialogDat(iid,true),true);
    }//GEN-LAST:event_IDetailsBtnActionPerformed

    private void RDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDetailsBtnActionPerformed
        // TODO add your handling code here:
        isfobj.framesfocloseref();
        ViewDetailsDialog.pack();
        ViewDetailsDialog.setVisible(true);
        this.setEnabled(false);
        ViewDetailsDialog.setLocationRelativeTo(null);
        
        Object iid = RTbl.getValueAt(RTbl.getSelectedRow(),1);
        ViewDetailsDialogset(ViewDetailsDialogDat(iid,false),false);
    }//GEN-LAST:event_RDetailsBtnActionPerformed

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
            java.util.logging.Logger.getLogger(IssueLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueLogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtnGroup;
    private javax.swing.JButton IDetailsBtn;
    private javax.swing.JCheckBox INOTCheck1;
    private javax.swing.JCheckBox INOTCheck2;
    private javax.swing.JCheckBox INOTCheck3;
    private javax.swing.JComboBox<String> ISearchCritCB1;
    private javax.swing.JComboBox<String> ISearchCritCB2;
    private javax.swing.JComboBox<String> ISearchCritCB3;
    private javax.swing.JComboBox<String> ISearchFieldCB1;
    private javax.swing.JComboBox<String> ISearchFieldCB2;
    private javax.swing.JComboBox<String> ISearchFieldCB3;
    private javax.swing.JTextField ISearchTF1;
    private javax.swing.JTextField ISearchTF2;
    private javax.swing.JTextField ISearchTF3;
    private javax.swing.JComboBox<String> ISortCB;
    private javax.swing.JCheckBox ISortDescCheck;
    private javax.swing.JTable ITbl;
    private javax.swing.JTextField OtherNameOTF;
    private javax.swing.JRadioButton OtherRB;
    private javax.swing.JButton RDetailsBtn;
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
    private javax.swing.JRadioButton StudentRB;
    private javax.swing.JDialog ViewDetailsDialog;
    private javax.swing.JTextField addonOTF;
    private javax.swing.JTextField addressOTF;
    private javax.swing.JTextField availOTF;
    private javax.swing.JTextField bidOTF;
    private javax.swing.JTextField bnameOTF;
    private javax.swing.JTextField cnoOTF;
    private javax.swing.JTextField editionOTF;
    private javax.swing.JTextField fnameOTF;
    private javax.swing.JTextField genderOTF;
    private javax.swing.JTextField iidOTF;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
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
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField nameOTF;
    private javax.swing.JTextField pagesOTF;
    private javax.swing.JTextField periodOTF;
    private javax.swing.JTextField priceOTF;
    private javax.swing.JTextField pubnameOTF;
    private javax.swing.JLabel retoLbl;
    private javax.swing.JTextField retoOTF;
    private javax.swing.JTextField rnoOTF;
    private javax.swing.JLabel ronLbl;
    private javax.swing.JLabel ronLbl1;
    private javax.swing.JLabel ronLbl2;
    private javax.swing.JTextField ronOTF;
    private javax.swing.JTextField sidOTF;
    private javax.swing.JTextField sourceOTF;
    private javax.swing.JTextField writnameOTF;
    // End of variables declaration//GEN-END:variables
}
