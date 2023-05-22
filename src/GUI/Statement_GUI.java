package GUI;

import BUS.Statement_BUS;
import DTO.Account_DTO;
import DTO.Employee_DTO;
import DTO.Customer_DTO;
import DTO.Transaction_DTO;
import DTO.Transaction_Type_DTO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Statement_GUI extends javax.swing.JFrame {
    Employee_DTO dtoAdmin = null;
    Statement_BUS busStatement = new Statement_BUS();
    Account_DTO dtoAccount = null;
    Customer_DTO dtoCustomer = null;
    
    public Statement_GUI(Employee_DTO admin) 
    {
        initComponents();
        dtoAdmin = admin;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Statement"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        btnExport.setEnabled(false); 
        createTable();
    }
    
    DefaultTableModel tblStatementModel = null;
    public void createTable() 
    {
        tblStatementModel = new DefaultTableModel();
        // Set tiêu đề
        String title[] = {"Transaction ID", "Time", "Total Transaction Amount", "Content"};
        tblStatementModel.setColumnIdentifiers(title);
        tblStatement.setModel(tblStatementModel);
        // Set kích thước cột
        tblStatement.getColumnModel().getColumn(0).setPreferredWidth(35);
        tblStatement.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblStatement.getColumnModel().getColumn(3).setPreferredWidth(320);
    }
    
    ArrayList<Transaction_DTO> transactionList = null;
    public void loadTable() 
    {
        // Get statement information
        transactionList = busStatement.getStatement(dtoAccount, dcFromDate.getDate(), dcToDate.getDate());
        
        // Check statement information
        if(transactionList.size() == 0) // No transactions found
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            JOptionPane.showMessageDialog(this, "Customers do not make transactions from " + df.format(dcFromDate.getDate())+ " to " + df.format(dcToDate.getDate()) , "Notification", JOptionPane.INFORMATION_MESSAGE);
            tblStatementModel.setRowCount(0); 
        }
        else // Transaction information is found
        {
            // Get transaction types information
            TreeMap<String, Transaction_Type_DTO> transactionTypeList = busStatement.getTransactionTypeList();
            tblStatementModel.setRowCount(0); 
            
            // Display transaction information on the table.
            for(int i = 0; i < transactionList.size(); i++) 
            {
                Transaction_DTO dtoTransaction = transactionList.get(i);
                String transactionId = String.valueOf(dtoTransaction.getId());
                
                String transactionTypeID = dtoTransaction.getTransactionTypeID();
                
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String transactionDate =  df.format(dtoTransaction.getTrasactionDate());
                
                String totalTransactionAmount = String.format("%,d", dtoTransaction.getTotalTransactionAmount());
                
                if(transactionTypeID.equals("NT01") || transactionTypeID.equals("TK02")) // nếu là giao dịch nhận tiền
                    totalTransactionAmount = "+" + totalTransactionAmount + " VND";
                else // nếu là giao dịch trừ tiền tài khoản
                    totalTransactionAmount = "-" + totalTransactionAmount + " VND";
                String transactionContent = transactionTypeList.get(transactionTypeID).getName().toUpperCase();
                
                
                String[] rows = {transactionId, transactionDate , totalTransactionAmount , transactionContent};
                tblStatementModel.addRow(rows);
            }
        }
    }
 
    public void loadCbbReportType()
    {
        cbbReportType.removeAllItems();
        if(dtoAccount != null)
            cbbReportType.addItem("Account balance confirmation");
        if(transactionList != null)
        {
            cbbReportType.addItem("Account statement");
            cbbReportType.addItem("Revenue report from customer");
        }
        cbbReportType.setSelectedItem(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblToDate = new javax.swing.JLabel();
        lblAccountOnwer = new javax.swing.JLabel();
        lblFromDate = new javax.swing.JLabel();
        txtAccountID = new javax.swing.JTextField();
        btnExport = new javax.swing.JButton();
        btnStatement = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStatement = new javax.swing.JTable();
        btnHome = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblAccountID1 = new javax.swing.JLabel();
        txtAccountOnwer = new javax.swing.JTextField();
        cbbReportType = new javax.swing.JComboBox<>();
        lblFromDate1 = new javax.swing.JLabel();
        dcToDate = new com.toedter.calendar.JDateChooser();
        dcFromDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Statement");

        jPanel1.setBackground(new java.awt.Color(239, 250, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblToDate.setBackground(new java.awt.Color(32, 172, 216));
        lblToDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblToDate.setForeground(new java.awt.Color(59, 196, 116));
        lblToDate.setText("To:");
        jPanel1.add(lblToDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, -1, -1));

        lblAccountOnwer.setBackground(new java.awt.Color(32, 172, 216));
        lblAccountOnwer.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblAccountOnwer.setForeground(new java.awt.Color(59, 196, 116));
        lblAccountOnwer.setText("Account onwer:");
        jPanel1.add(lblAccountOnwer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, -1));

        lblFromDate.setBackground(new java.awt.Color(32, 172, 216));
        lblFromDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblFromDate.setForeground(new java.awt.Color(59, 196, 116));
        lblFromDate.setText("Report type:");
        jPanel1.add(lblFromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        txtAccountID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtAccountID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAccountIDMouseClicked(evt);
            }
        });
        txtAccountID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountIDActionPerformed(evt);
            }
        });
        jPanel1.add(txtAccountID, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 300, 30));

        btnExport.setBackground(new java.awt.Color(59, 196, 116));
        btnExport.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Export.png"))); // NOI18N
        btnExport.setText("Export report PDF");
        btnExport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExport.setIconTextGap(2);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        jPanel1.add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 190, 40));

        btnStatement.setBackground(new java.awt.Color(59, 196, 116));
        btnStatement.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnStatement.setForeground(new java.awt.Color(255, 255, 255));
        btnStatement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Statement_Statement.png"))); // NOI18N
        btnStatement.setText(" Statement");
        btnStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatementActionPerformed(evt);
            }
        });
        jPanel1.add(btnStatement, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 150, 40));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Statement.png"))); // NOI18N
        jPanel1.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 100));

        tblStatement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStatement);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 780, 230));

        btnHome.setBackground(new java.awt.Color(59, 196, 116));
        btnHome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setToolTipText("");
        btnHome.setBorder(null);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome.setIconTextGap(0);
        btnHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 50, 57));

        lblTitle.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("        Statement");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 30, 935, 58));

        lblAccountID1.setBackground(new java.awt.Color(32, 172, 216));
        lblAccountID1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblAccountID1.setForeground(new java.awt.Color(59, 196, 116));
        lblAccountID1.setText("Account ID:");
        jPanel1.add(lblAccountID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        txtAccountOnwer.setEditable(false);
        txtAccountOnwer.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtAccountOnwer, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 300, 30));

        cbbReportType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(cbbReportType, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 300, -1));

        lblFromDate1.setBackground(new java.awt.Color(32, 172, 216));
        lblFromDate1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblFromDate1.setForeground(new java.awt.Color(59, 196, 116));
        lblFromDate1.setText("From:");
        jPanel1.add(lblFromDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));
        jPanel1.add(dcToDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, 200, 30));
        jPanel1.add(dcFromDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 200, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        new AdminHome_GUI(dtoAdmin);
    }//GEN-LAST:event_btnHomeActionPerformed
    private void btnStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatementActionPerformed
        // Check if the user input is enough or not
        if(txtAccountID.getText().equals("") || txtAccountOnwer.getText().equals("") || dcToDate.getDate() == null || dcToDate.getDate() == null)
        {
            JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Check statement date 
            if(dcFromDate.getDate().compareTo(dcToDate.getDate()) == 1 || dcToDate.getDate().compareTo(new Date()) == 1) // Kiểm tra ngày sao kê: fromDate có lớn hơn toDate không? OR toDate có lớn hơn ngày hôm nay ko
            {
                JOptionPane.showMessageDialog(this, "Invalid statement date", "Eroror", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                loadTable();
                loadCbbReportType();
            }
        }
    }//GEN-LAST:event_btnStatementActionPerformed

    private void txtAccountIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountIDActionPerformed
        // Get account owner information
        dtoAccount = new Account_DTO(Long.parseLong(txtAccountID.getText()));
        dtoCustomer = busStatement.getCustomerInfo(dtoAccount);
        
        // Check account owner information
        if(dtoCustomer == null)
        {
            JOptionPane.showMessageDialog(this, "Account ID is invalid", "Error", JOptionPane.ERROR_MESSAGE);
            btnExport.setEnabled(false);
        }
        else
        {
            // Display the account owner's name 
            txtAccountOnwer.setText(dtoCustomer.getFirstName() + " " + dtoCustomer.getLastName());
            btnExport.setEnabled(true);
        }
        loadCbbReportType();
    }//GEN-LAST:event_txtAccountIDActionPerformed

    private void txtAccountIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountIDMouseClicked
        txtAccountOnwer.setText("");
        tblStatementModel.setRowCount(0); // clear Table
        dtoAccount = null;
        transactionList = null;
        cbbReportType.removeAllItems();
        btnExport.setEnabled(false);
    }//GEN-LAST:event_txtAccountIDMouseClicked

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        Account_DTO dtoAccount = busStatement.getPaymentAccount(dtoCustomer);
        if(cbbReportType.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select the type of report to export", "Required fields are empty", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            if(cbbReportType.getSelectedItem().equals("Account balance confirmation"))
            {
                busStatement.printAccountBalanceConfirmationReport(dtoAccount, dtoCustomer);
            }
            else
            {
                if(dcFromDate.getDate().compareTo(dcToDate.getDate()) == 1) // Kiểm tra ngày sao kê: fromDate có lớn hơn toDate không?
                {
                    JOptionPane.showMessageDialog(this, "Invalid statement date", "Eroror", JOptionPane.ERROR_MESSAGE);
                }
                else
                {

                    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strFromDate = df1.format(dcFromDate.getDate()) + " 00:00:59";
                    String strToDate = df1.format(dcToDate.getDate()) + " 23:59:59";
                    Date fromDate = new Date();
                    Date toDate = new Date();
                    try 
                    {
                        fromDate = df2.parse(strFromDate);
                        toDate = df2.parse(strToDate);
                    } 
                    catch (ParseException e) 
                    {
                        e.printStackTrace();
                    }

                    if(cbbReportType.getSelectedItem().equals("Account statement"))
                    {
                        busStatement.printAccountStatementReport(dtoAccount, dtoCustomer, fromDate,toDate) ;
                    }
                    if(cbbReportType.getSelectedItem().equals("Revenue report from customer"))
                    {
                        busStatement.printRevenueReportFromCustomer(dtoAccount, dtoCustomer, fromDate, toDate);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnStatement;
    private javax.swing.JComboBox<String> cbbReportType;
    private com.toedter.calendar.JDateChooser dcFromDate;
    private com.toedter.calendar.JDateChooser dcToDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAccountID1;
    private javax.swing.JLabel lblAccountOnwer;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblFromDate1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblToDate;
    private javax.swing.JTable tblStatement;
    private javax.swing.JTextField txtAccountID;
    private javax.swing.JTextField txtAccountOnwer;
    // End of variables declaration//GEN-END:variables
}
