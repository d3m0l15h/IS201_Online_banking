package GUI;

import BUS.MyWallet_BUS;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.Transaction_DTO;
import DTO.Transaction_Type_DTO;
import GUI.CustomerHome_GUI;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.table.*;

public class MyWallet_GUI extends javax.swing.JFrame 
{
    MyWallet_BUS busMyWallet = new MyWallet_BUS();
    
    Customer_DTO dtoCustomer = null; // Người đang đăng nhập
    Account_DTO dtoAccount = null;
            
    public MyWallet_GUI(Customer_DTO customer, Account_DTO account)
    {
        initComponents();
        // Nhận tham số mà CustomerHome_GUI truyền vào
        dtoAccount = account;
        dtoCustomer = customer; 
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("My Wallet"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        displayAccountInformation();
        createTable();
    }
    
    public void displayAccountInformation()
    {
        txtAccountId.setText(String.valueOf(dtoAccount.getId()));
        txtAccountOwner.setText(dtoCustomer.getFirstName() + " " + dtoCustomer.getLastName());
        txtOpenDay.setText(dtoAccount.getOpenDay().toString());
        String accountStatus = dtoAccount.getStatus().toString();
        txtStatus.setText(accountStatus);
        
        // Check the account's status
        if(accountStatus.equals("Locked"))
            txtStatus.setForeground(Color.RED);
        
        // Get the latest transaction date
        String lastestTransactionDate = busMyWallet.getLatestTransactionDate(dtoAccount);
        
        // Display the latest transaction date
        txtLatestTransactionDate.setText(lastestTransactionDate);
    }

    DefaultTableModel tblTransactionModel = new DefaultTableModel();
    public void createTable() 
    {
        // Set tiêu đề
        String title[] = {"Transaction ID","Transaction Type","Time", "Amount"};
        tblTransactionModel.setColumnIdentifiers(title);
        tblTransactionModel.setRowCount(0); 
        tblTransactionHistory.setModel(tblTransactionModel);
        
        // Set kích thước cho các cột
        tblTransactionHistory.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblTransactionHistory.getColumnModel().getColumn(1).setPreferredWidth(270);
        tblTransactionHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    
    public void loadTable() 
    {
        // Get the most latest 15 transactions
        ArrayList<Transaction_DTO> transactionlist = busMyWallet.getTransactionHistory(dtoAccount);
        
        // Get transaction types information
        TreeMap<String, Transaction_Type_DTO> transactionTypeList = busMyWallet.getTransactionTypeList();
        
        // Displays a list of the 15 most latest transactions on the table.
        for(int i = 0; i < transactionlist.size(); i++)
        {
            Transaction_DTO dtoTransaction = transactionlist.get(i);
            String transactionId = String.valueOf(dtoTransaction.getId());
            String transactionTypeID = dtoTransaction.getTransactionTypeID();
            String transactionTypeName = transactionTypeList.get(transactionTypeID).getName();
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String transactionDate =  df.format(dtoTransaction.getTrasactionDate());
            String totalTransactionAmount = String.format("%,d", dtoTransaction.getTotalTransactionAmount());
            
            if(transactionTypeID.equals("NT01") || transactionTypeID.equals("TK02")) // nếu là giao dịch nhận tiền
                totalTransactionAmount = "+" + totalTransactionAmount + " VND";
            else // nếu là giao dịch trừ tiền tài khoản
                totalTransactionAmount = "-" + totalTransactionAmount + " VND";
            
            String[] rows = {transactionId, transactionTypeName, transactionDate, totalTransactionAmount};
            tblTransactionModel.addRow(rows);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblImage_MyWalletCusGUI = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblOpenDay = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        tblTransactionHistory = new javax.swing.JTable();
        lblTransactionHistory = new javax.swing.JLabel();
        lblCurrentBalance = new javax.swing.JLabel();
        lbllAccountId = new javax.swing.JLabel();
        lblAccountOwner = new javax.swing.JLabel();
        lblPaymentAccountInformation = new javax.swing.JLabel();
        txtAccountOwner = new javax.swing.JTextField();
        txtOpenDay = new javax.swing.JTextField();
        txtLatestTransactionDate = new javax.swing.JTextField();
        txtAccountId = new javax.swing.JTextField();
        btnShowTransactionHistory = new javax.swing.JButton();
        txtCurrentBalance = new javax.swing.JTextField();
        lblLatestTransactionDate = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        lblTransactionHistoryNote = new javax.swing.JLabel();
        ckbShowCurrentBalance = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Wallet");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(239, 250, 252));
        jPanel1.setMinimumSize(new java.awt.Dimension(1060, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(1060, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage_MyWalletCusGUI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_vicuatoi.png"))); // NOI18N
        jPanel1.add(lblImage_MyWalletCusGUI, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 100));

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
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 50, 57));

        lblTitle.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("         My Wallet");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 30, 931, 58));

        lblOpenDay.setBackground(new java.awt.Color(32, 172, 216));
        lblOpenDay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblOpenDay.setForeground(new java.awt.Color(59, 196, 116));
        lblOpenDay.setText("Open day:");
        jPanel1.add(lblOpenDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, -1, -1));

        tblTransactionHistory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTransactionHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransactionHistory.setShowGrid(true);
        jScrollPane.setViewportView(tblTransactionHistory);
        if (tblTransactionHistory.getColumnModel().getColumnCount() > 0) {
            tblTransactionHistory.getColumnModel().getColumn(0).setResizable(false);
            tblTransactionHistory.getColumnModel().getColumn(1).setResizable(false);
            tblTransactionHistory.getColumnModel().getColumn(2).setResizable(false);
            tblTransactionHistory.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 800, 210));

        lblTransactionHistory.setBackground(new java.awt.Color(32, 172, 216));
        lblTransactionHistory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTransactionHistory.setForeground(new java.awt.Color(59, 196, 116));
        lblTransactionHistory.setText("Transaction History");
        jPanel1.add(lblTransactionHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, -1, -1));

        lblCurrentBalance.setBackground(new java.awt.Color(32, 172, 216));
        lblCurrentBalance.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblCurrentBalance.setForeground(new java.awt.Color(59, 196, 116));
        lblCurrentBalance.setText("Current Balance:");
        jPanel1.add(lblCurrentBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        lbllAccountId.setBackground(new java.awt.Color(32, 172, 216));
        lbllAccountId.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lbllAccountId.setForeground(new java.awt.Color(59, 196, 116));
        lbllAccountId.setText("Account ID:");
        jPanel1.add(lbllAccountId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, -1));

        lblAccountOwner.setBackground(new java.awt.Color(32, 172, 216));
        lblAccountOwner.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblAccountOwner.setForeground(new java.awt.Color(59, 196, 116));
        lblAccountOwner.setText("Account owner:");
        jPanel1.add(lblAccountOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        lblPaymentAccountInformation.setBackground(new java.awt.Color(32, 172, 216));
        lblPaymentAccountInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPaymentAccountInformation.setForeground(new java.awt.Color(59, 196, 116));
        lblPaymentAccountInformation.setText("Payment Account Information:");
        jPanel1.add(lblPaymentAccountInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        txtAccountOwner.setEditable(false);
        txtAccountOwner.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jPanel1.add(txtAccountOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 240, 30));

        txtOpenDay.setEditable(false);
        txtOpenDay.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jPanel1.add(txtOpenDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 210, 30));

        txtLatestTransactionDate.setEditable(false);
        txtLatestTransactionDate.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jPanel1.add(txtLatestTransactionDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 210, 30));

        txtAccountId.setEditable(false);
        txtAccountId.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jPanel1.add(txtAccountId, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 240, 30));

        btnShowTransactionHistory.setBackground(new java.awt.Color(59, 196, 116));
        btnShowTransactionHistory.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnShowTransactionHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnShowTransactionHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/plus_25px.png"))); // NOI18N
        btnShowTransactionHistory.setText("Show Transaction History");
        btnShowTransactionHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowTransactionHistoryActionPerformed(evt);
            }
        });
        jPanel1.add(btnShowTransactionHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 240, -1));

        txtCurrentBalance.setEditable(false);
        txtCurrentBalance.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        txtCurrentBalance.setText("*************");
        jPanel1.add(txtCurrentBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 240, 30));

        lblLatestTransactionDate.setBackground(new java.awt.Color(32, 172, 216));
        lblLatestTransactionDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblLatestTransactionDate.setForeground(new java.awt.Color(59, 196, 116));
        lblLatestTransactionDate.setText("Latest transaction date:");
        jPanel1.add(lblLatestTransactionDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        txtStatus.setEditable(false);
        txtStatus.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jPanel1.add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 210, 30));

        lblStatus.setBackground(new java.awt.Color(32, 172, 216));
        lblStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(59, 196, 116));
        lblStatus.setText("Account status:");
        jPanel1.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, -1, -1));

        lblTransactionHistoryNote.setBackground(new java.awt.Color(32, 172, 216));
        lblTransactionHistoryNote.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        lblTransactionHistoryNote.setForeground(new java.awt.Color(59, 196, 116));
        lblTransactionHistoryNote.setText("(15 most recent transactions)");
        jPanel1.add(lblTransactionHistoryNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, -1, -1));

        ckbShowCurrentBalance.setBackground(new java.awt.Color(239, 250, 252));
        ckbShowCurrentBalance.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        ckbShowCurrentBalance.setForeground(new java.awt.Color(59, 196, 116));
        ckbShowCurrentBalance.setText("Show current balance");
        ckbShowCurrentBalance.setBorder(null);
        ckbShowCurrentBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbShowCurrentBalanceActionPerformed(evt);
            }
        });
        jPanel1.add(ckbShowCurrentBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        new CustomerHome_GUI(dtoCustomer);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnShowTransactionHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowTransactionHistoryActionPerformed
        loadTable();
        
        // Hide the Show Transaction History button
        btnShowTransactionHistory.setVisible(false);
    }//GEN-LAST:event_btnShowTransactionHistoryActionPerformed
    
    private void ckbShowCurrentBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbShowCurrentBalanceActionPerformed
        // Check checkbox status
        if(ckbShowCurrentBalance.isSelected())
        {
            // Show balance
            txtCurrentBalance.setText(String.format("%,d", dtoAccount.getCurrentBalance()) + " VND");
        }
        else
        {
            // Hide balance
            txtCurrentBalance.setText("*************");
        } 
    }//GEN-LAST:event_ckbShowCurrentBalanceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnShowTransactionHistory;
    private javax.swing.JCheckBox ckbShowCurrentBalance;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblAccountOwner;
    private javax.swing.JLabel lblCurrentBalance;
    private javax.swing.JLabel lblImage_MyWalletCusGUI;
    private javax.swing.JLabel lblLatestTransactionDate;
    private javax.swing.JLabel lblOpenDay;
    private javax.swing.JLabel lblPaymentAccountInformation;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTransactionHistory;
    private javax.swing.JLabel lblTransactionHistoryNote;
    private javax.swing.JLabel lbllAccountId;
    private javax.swing.JTable tblTransactionHistory;
    private javax.swing.JTextField txtAccountId;
    private javax.swing.JTextField txtAccountOwner;
    private javax.swing.JTextField txtCurrentBalance;
    private javax.swing.JTextField txtLatestTransactionDate;
    private javax.swing.JTextField txtOpenDay;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
