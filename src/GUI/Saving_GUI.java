package GUI;

import BUS.Saving_BUS;
import DTO.Account_Type_DTO;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;
import GUI.CustomerHome_GUI;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

public class Saving_GUI extends javax.swing.JFrame 
{
    Saving_BUS busSaving = new Saving_BUS();
    
    Customer_DTO dtoCustomer = null;
    Account_Type_DTO dtoSavingsAccountType = null;
    Account_DTO dtoPaymentAccount = null;
    TreeMap<String, String> accountTypeList = null;
    ArrayList<Account_DTO> savingsAccountList = null;
    
    public Saving_GUI(Customer_DTO customer, Account_DTO account) 
    {
        initComponents();
        /*Nhận tham số từ CustomerHome_GUI*/
        dtoCustomer = customer;
        dtoPaymentAccount = account;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Saving"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        cboSavingsAccountType.setModel(new DefaultComboBoxModel<>(new String[] {"Term Savings Account", "Non-term Savings Account"} ) );
        cboTerm.setModel(new DefaultComboBoxModel<>(new String[] {"1 month", "3 months", "6 months"} ) );
        
        cboSavingsAccountType.setSelectedItem(null); // Set giá trị ban đầu của combobox là null
        cboTerm.setSelectedItem(null); 
        btnOpenAccount.setVisible(false); // Ẩn button open account
        
        // Calculate total savings amount
        long totalSavingAccount = busSaving.getTotalSavingsAmount(dtoCustomer);
        // Display total savings amount
        txtTotalSavingAccount.setText(String.format("%,d", totalSavingAccount) + " VND");
        createTable();
    }

    public String calculateRemainingDay(Account_DTO dtoAccount)
    {
        // Tính số ngày còn lại
        Date currentDate = new Date();
        long remainingDays =  TimeUnit.MILLISECONDS.toDays(dtoAccount.getMaturityDate().getTime() - currentDate.getTime()) + 1; 
        String strRemainingDays = "";
        if(remainingDays > 1)
            strRemainingDays = remainingDays + " days";
        else if(remainingDays == 1)
            strRemainingDays = "1 day";
        else
            strRemainingDays = "0 day";
        return strRemainingDays;
    }
    
    DefaultTableModel tblAccountModel;
    public void createTable()
    {
        tblAccountModel = new DefaultTableModel();
        // Set tiêu đề
        String title[] = {"Account No", "Name", "Amount", "Interest", "Open Date", "Maturity Date", "Remaining days"};
        tblAccountModel.setColumnIdentifiers(title);
        tblAccountModel.setRowCount(0); 
        tblSavingsAccount.setModel(tblAccountModel);
        
        // Set kích thước cho các cột
        tblSavingsAccount.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblSavingsAccount.getColumnModel().getColumn(1).setPreferredWidth(280);
        
        // Get savings accounts information
        savingsAccountList = busSaving.getSavingsAccountList(dtoCustomer);
        // Get savings types information
        accountTypeList = busSaving.getAccountTypeList();
        // Load data into savings accounts table
        for(int i = 0; i < savingsAccountList.size(); i++)
        {
            Account_DTO dtoAccount = savingsAccountList.get(i);
            String accountId = String.valueOf(dtoAccount.getId());
            String name = accountTypeList.get(dtoAccount.getAccountTypeID());
            String amount = String.format("%,d", dtoAccount.getCurrentBalance()) + " VND";
            String interest = String.format("%,d",dtoAccount.getAnticipatedInterest()) + " VND";
            String openDate = String.valueOf(dtoAccount.getOpenDay());
            String maturityDate = String.valueOf(dtoAccount.getMaturityDate());
            String remainingDays = calculateRemainingDay(dtoAccount);
            String[] rows = {accountId, name , amount , interest, openDate, maturityDate, remainingDays};
            tblAccountModel.addRow(rows);
        }
    }
    
    private boolean confirmPassword()
    {
        // Hiển thị form nhập mật khẩu
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please enter your password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Confirm", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Verify by password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

        if(option == 0) // Customer pressing OK button
        {
            String password = pass.getText();
            User_Login_DTO dtoUserLogIn = busSaving.getUserLogin(dtoCustomer); // Get customer password in database
            if(password.equals(dtoUserLogIn.getPassword()))
                return true;
            else
                JOptionPane.showMessageDialog(this, "Password is incorrect", "Incorrect details", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    int numberOfMonth = 0;
    public String calculateMaturityDate()
    {
        //-- Tính ngày đáo hạn
        long millis = System.currentTimeMillis();
        java.sql.Date startDate = new java.sql.Date(millis);
        Calendar temp = Calendar.getInstance();
        temp.setTime(startDate);
        String term = cboTerm.getSelectedItem().toString();
        
        numberOfMonth = Integer.parseInt(term.replace(" months", "").replace( " month", ""));
        temp.add(Calendar.MONTH, numberOfMonth);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String maturityDate = df.format(temp.getTime());
        return maturityDate;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        WithdrawOnlineSavings = new javax.swing.JPanel();
        btnHome_ViewAccount = new javax.swing.JButton();
        lblIcon = new javax.swing.JLabel();
        lblTitle_ViewAccount = new javax.swing.JLabel();
        btnSettlement = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSavingsAccount = new javax.swing.JTable();
        lblTotalSavingAccount = new javax.swing.JLabel();
        txtTotalSavingAccount = new javax.swing.JFormattedTextField();
        lblCurrentSavingAccountsList = new javax.swing.JLabel();
        OpenOnlineSavings = new javax.swing.JPanel();
        btnHome_OpenAccount = new javax.swing.JButton();
        lblIcon1 = new javax.swing.JLabel();
        lblTitle_OpenAccount = new javax.swing.JLabel();
        lblDebitAccount = new javax.swing.JLabel();
        lblTerm = new javax.swing.JLabel();
        lblVND = new javax.swing.JLabel();
        cboSavingsAccountType = new javax.swing.JComboBox<>();
        txtDeposit = new javax.swing.JTextField();
        cboTerm = new javax.swing.JComboBox<>();
        btnFindSuitableProduct = new javax.swing.JButton();
        btnOpenAccount = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlSuitableProductDetails = new javax.swing.JPanel();
        lblMaturityDate = new javax.swing.JLabel();
        lblDebitAccount5 = new javax.swing.JLabel();
        lblVND3 = new javax.swing.JLabel();
        lblTotalReceivedAmount = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        lblAnticipatedInterest = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtInterestRate = new javax.swing.JTextField();
        txtAnticipatedInterest = new javax.swing.JTextField();
        lblInterestRate = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JTextField();
        txtMaturityDate = new javax.swing.JTextField();
        txtProductName = new javax.swing.JTextField();
        lblVND2 = new javax.swing.JLabel();
        lblDebitAccount4 = new javax.swing.JLabel();
        lxlInputError_Deposits = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Saving");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        WithdrawOnlineSavings.setBackground(new java.awt.Color(239, 250, 252));
        WithdrawOnlineSavings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome_ViewAccount.setBackground(new java.awt.Color(59, 196, 116));
        btnHome_ViewAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnHome_ViewAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Home.png"))); // NOI18N
        btnHome_ViewAccount.setText("Home");
        btnHome_ViewAccount.setToolTipText("");
        btnHome_ViewAccount.setBorder(null);
        btnHome_ViewAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome_ViewAccount.setIconTextGap(1);
        btnHome_ViewAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome_ViewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome_ViewAccountActionPerformed(evt);
            }
        });
        WithdrawOnlineSavings.add(btnHome_ViewAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 76, 64));

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Saving_money_125px.png"))); // NOI18N
        WithdrawOnlineSavings.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 120, 110));

        lblTitle_ViewAccount.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle_ViewAccount.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitle_ViewAccount.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle_ViewAccount.setText("           View Online Savings Accounts");
        lblTitle_ViewAccount.setOpaque(true);
        lblTitle_ViewAccount.setPreferredSize(new java.awt.Dimension(34, 50));
        WithdrawOnlineSavings.add(lblTitle_ViewAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 940, 66));

        btnSettlement.setBackground(new java.awt.Color(59, 196, 116));
        btnSettlement.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnSettlement.setForeground(new java.awt.Color(255, 255, 255));
        btnSettlement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Saving_request_money_20px.png"))); // NOI18N
        btnSettlement.setText("Settlement");
        btnSettlement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettlementActionPerformed(evt);
            }
        });
        WithdrawOnlineSavings.add(btnSettlement, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, 150, 40));

        tblSavingsAccount.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSavingsAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSavingsAccountMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSavingsAccount);

        WithdrawOnlineSavings.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 230, 840, 240));

        lblTotalSavingAccount.setBackground(new java.awt.Color(32, 172, 216));
        lblTotalSavingAccount.setFont(new java.awt.Font("Segoe UI Semibold", 0, 17)); // NOI18N
        lblTotalSavingAccount.setForeground(new java.awt.Color(59, 196, 116));
        lblTotalSavingAccount.setText("Total Savings Amount:");
        WithdrawOnlineSavings.add(lblTotalSavingAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        txtTotalSavingAccount.setEditable(false);
        txtTotalSavingAccount.setBackground(new java.awt.Color(239, 250, 252));
        txtTotalSavingAccount.setBorder(null);
        txtTotalSavingAccount.setForeground(new java.awt.Color(59, 196, 116));
        txtTotalSavingAccount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtTotalSavingAccount.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        WithdrawOnlineSavings.add(txtTotalSavingAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 220, -1));

        lblCurrentSavingAccountsList.setBackground(new java.awt.Color(32, 172, 216));
        lblCurrentSavingAccountsList.setFont(new java.awt.Font("Segoe UI Semibold", 0, 17)); // NOI18N
        lblCurrentSavingAccountsList.setForeground(new java.awt.Color(59, 196, 116));
        lblCurrentSavingAccountsList.setText("Current Saving Accounts List:");
        WithdrawOnlineSavings.add(lblCurrentSavingAccountsList, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jTabbedPane1.addTab("View Online Savings Information", WithdrawOnlineSavings);

        OpenOnlineSavings.setBackground(new java.awt.Color(239, 250, 252));
        OpenOnlineSavings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome_OpenAccount.setBackground(new java.awt.Color(59, 196, 116));
        btnHome_OpenAccount.setForeground(new java.awt.Color(59, 196, 116));
        btnHome_OpenAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Home.png"))); // NOI18N
        btnHome_OpenAccount.setText("Home");
        btnHome_OpenAccount.setToolTipText("");
        btnHome_OpenAccount.setBorder(null);
        btnHome_OpenAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome_OpenAccount.setIconTextGap(1);
        btnHome_OpenAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome_OpenAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome_OpenAccountActionPerformed(evt);
            }
        });
        OpenOnlineSavings.add(btnHome_OpenAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 76, 64));

        lblIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_tietkiem.png"))); // NOI18N
        OpenOnlineSavings.add(lblIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 110));

        lblTitle_OpenAccount.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle_OpenAccount.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitle_OpenAccount.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle_OpenAccount.setText("       Open Online Savings Account");
        lblTitle_OpenAccount.setOpaque(true);
        lblTitle_OpenAccount.setPreferredSize(new java.awt.Dimension(34, 50));
        OpenOnlineSavings.add(lblTitle_OpenAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 940, 66));

        lblDebitAccount.setBackground(new java.awt.Color(32, 172, 216));
        lblDebitAccount.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblDebitAccount.setForeground(new java.awt.Color(59, 196, 116));
        lblDebitAccount.setText("Deposit:");
        OpenOnlineSavings.add(lblDebitAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        lblTerm.setBackground(new java.awt.Color(32, 172, 216));
        lblTerm.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblTerm.setForeground(new java.awt.Color(59, 196, 116));
        lblTerm.setText("Term:");
        OpenOnlineSavings.add(lblTerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        lblVND.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblVND.setForeground(new java.awt.Color(59, 196, 116));
        lblVND.setText("VND");
        OpenOnlineSavings.add(lblVND, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, -1, -1));

        cboSavingsAccountType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboSavingsAccountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSavingsAccountTypeActionPerformed(evt);
            }
        });
        OpenOnlineSavings.add(cboSavingsAccountType, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 320, -1));

        txtDeposit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDeposit.setForeground(new java.awt.Color(1, 1, 1));
        txtDeposit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDepositMouseClicked(evt);
            }
        });
        txtDeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepositKeyTyped(evt);
            }
        });
        OpenOnlineSavings.add(txtDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 320, -1));

        cboTerm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTermActionPerformed(evt);
            }
        });
        OpenOnlineSavings.add(cboTerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 320, -1));

        btnFindSuitableProduct.setBackground(new java.awt.Color(59, 196, 116));
        btnFindSuitableProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFindSuitableProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnFindSuitableProduct.setText("Find Suitable Product");
        btnFindSuitableProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindSuitableProductActionPerformed(evt);
            }
        });
        OpenOnlineSavings.add(btnFindSuitableProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, -1, 40));

        btnOpenAccount.setBackground(new java.awt.Color(59, 196, 116));
        btnOpenAccount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOpenAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/plus_25px.png"))); // NOI18N
        btnOpenAccount.setText("Open Account");
        btnOpenAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenAccountActionPerformed(evt);
            }
        });
        OpenOnlineSavings.add(btnOpenAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 170, 40));
        OpenOnlineSavings.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 710, 10));

        pnlSuitableProductDetails.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaturityDate.setBackground(new java.awt.Color(32, 172, 216));
        lblMaturityDate.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblMaturityDate.setForeground(new java.awt.Color(59, 196, 116));
        lblMaturityDate.setText("Maturity date:");
        pnlSuitableProductDetails.add(lblMaturityDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

        lblDebitAccount5.setBackground(new java.awt.Color(32, 172, 216));
        lblDebitAccount5.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblDebitAccount5.setForeground(new java.awt.Color(59, 196, 116));
        lblDebitAccount5.setText("Suitable Product Details:");
        pnlSuitableProductDetails.add(lblDebitAccount5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblVND3.setBackground(new java.awt.Color(32, 172, 216));
        lblVND3.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblVND3.setForeground(new java.awt.Color(59, 196, 116));
        lblVND3.setText("(VND)");
        pnlSuitableProductDetails.add(lblVND3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        lblTotalReceivedAmount.setBackground(new java.awt.Color(32, 172, 216));
        lblTotalReceivedAmount.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblTotalReceivedAmount.setForeground(new java.awt.Color(59, 196, 116));
        lblTotalReceivedAmount.setText("Total received amount: ");
        pnlSuitableProductDetails.add(lblTotalReceivedAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        lblStartDate.setBackground(new java.awt.Color(32, 172, 216));
        lblStartDate.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblStartDate.setForeground(new java.awt.Color(59, 196, 116));
        lblStartDate.setText("Start date:");
        pnlSuitableProductDetails.add(lblStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        lblAnticipatedInterest.setBackground(new java.awt.Color(32, 172, 216));
        lblAnticipatedInterest.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblAnticipatedInterest.setForeground(new java.awt.Color(59, 196, 116));
        lblAnticipatedInterest.setText("Anticipated Interest:");
        pnlSuitableProductDetails.add(lblAnticipatedInterest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(59, 196, 116));
        txtTotal.setBorder(null);
        pnlSuitableProductDetails.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 140, -1));

        txtInterestRate.setEditable(false);
        txtInterestRate.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtInterestRate.setForeground(new java.awt.Color(59, 196, 116));
        txtInterestRate.setBorder(null);
        pnlSuitableProductDetails.add(txtInterestRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 140, -1));

        txtAnticipatedInterest.setEditable(false);
        txtAnticipatedInterest.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtAnticipatedInterest.setForeground(new java.awt.Color(59, 196, 116));
        txtAnticipatedInterest.setBorder(null);
        pnlSuitableProductDetails.add(txtAnticipatedInterest, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 140, -1));

        lblInterestRate.setBackground(new java.awt.Color(32, 172, 216));
        lblInterestRate.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblInterestRate.setForeground(new java.awt.Color(59, 196, 116));
        lblInterestRate.setText("Interest rate:");
        pnlSuitableProductDetails.add(lblInterestRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtStartDate.setEditable(false);
        txtStartDate.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtStartDate.setForeground(new java.awt.Color(59, 196, 116));
        txtStartDate.setBorder(null);
        pnlSuitableProductDetails.add(txtStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 140, -1));

        txtMaturityDate.setEditable(false);
        txtMaturityDate.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtMaturityDate.setForeground(new java.awt.Color(59, 196, 116));
        txtMaturityDate.setBorder(null);
        pnlSuitableProductDetails.add(txtMaturityDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 150, -1));

        txtProductName.setEditable(false);
        txtProductName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtProductName.setForeground(new java.awt.Color(59, 196, 116));
        txtProductName.setBorder(null);
        pnlSuitableProductDetails.add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 470, -1));

        lblVND2.setBackground(new java.awt.Color(32, 172, 216));
        lblVND2.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblVND2.setForeground(new java.awt.Color(59, 196, 116));
        lblVND2.setText("(VND)");
        pnlSuitableProductDetails.add(lblVND2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        OpenOnlineSavings.add(pnlSuitableProductDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 710, 210));

        lblDebitAccount4.setBackground(new java.awt.Color(32, 172, 216));
        lblDebitAccount4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblDebitAccount4.setForeground(new java.awt.Color(59, 196, 116));
        lblDebitAccount4.setText("Saving Account Type:");
        OpenOnlineSavings.add(lblDebitAccount4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        lxlInputError_Deposits.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lxlInputError_Deposits.setForeground(new java.awt.Color(255, 102, 102));
        OpenOnlineSavings.add(lxlInputError_Deposits, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 170, -1));

        jTabbedPane1.addTab("Open Online Savings", OpenOnlineSavings);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHome_ViewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome_ViewAccountActionPerformed
        this.setVisible(false);
        new CustomerHome_GUI(dtoCustomer);
    }//GEN-LAST:event_btnHome_ViewAccountActionPerformed

    private void btnSettlementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettlementActionPerformed
        // Xử lý tất toán tài khoản tiết kiệm
        int row = tblSavingsAccount.getSelectedRow();
        if(savingAccountId == 0 || row == -1) // Check to see if the customer has selected an account or not
        {
            JOptionPane.showMessageDialog(this, "Please select a saving account to settle.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Check the number of remaining day
            if(tblSavingsAccount.getValueAt(row, 6).toString().equals("0 day") == false) // Nếu chưa đến ngày đáo hạn
            {
                String accountsSavingType= savingsAccountList.get(row).getAccountTypeID();
                // Check the savings type
                if(accountsSavingType.contains("TSA")) // Đối với tiết kiệm có kỳ hạn thì không được tất toán
                {
                    JOptionPane.showMessageDialog(this, "Term savings account cannot be settled because the maturity date hasn't come yet.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else // Đối với tiết kiệm không kỳ hạn thì có thể tất toán được.
                {
                    int reply = JOptionPane.showConfirmDialog(null, "The maturity date hasn't come yet, are you sure to settle this saving account?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) 
                    {
                        if(confirmPassword())
                        {
                            // Nếu password đúng thì thực hiện tất toán
                            Account_DTO dtoSavingAccount= new Account_DTO(savingAccountId);
                            if(busSaving.settle(dtoSavingAccount))
                            {
                                JOptionPane.showMessageDialog(this, "Settled successfully" , "Notification", JOptionPane.INFORMATION_MESSAGE); 
                                createTable();
                                txtTotalSavingAccount.setText(String.format("%,d",busSaving.getTotalSavingsAmount(dtoCustomer)) + " VND");
                            }
                        }
                    }
                }
            }
            else // Đã tới hoặc qua ngày đáo hạn thì cả 2 loại tiết kiệm đều có thể tất toán
            {
                if(confirmPassword())
                {
                    Account_DTO dtoSavingAccount= new Account_DTO(savingAccountId);
                    if(busSaving.settle(dtoSavingAccount))
                    {
                        JOptionPane.showMessageDialog(this, "Settled successfully" , "Notification", JOptionPane.INFORMATION_MESSAGE); 
                        createTable();
                        txtTotalSavingAccount.setText(String.format("%,d",busSaving.getTotalSavingsAmount(dtoCustomer)) + " VND");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSettlementActionPerformed

    private void btnHome_OpenAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome_OpenAccountActionPerformed
        new CustomerHome_GUI(dtoCustomer);
        setVisible(false);
    }//GEN-LAST:event_btnHome_OpenAccountActionPerformed

    private void cboTermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTermActionPerformed
        //Clear form
        txtInterestRate.setText("");
        txtAnticipatedInterest.setText("");
        txtTotal.setText("");
        txtStartDate.setText("");
        txtMaturityDate.setText("");
        txtProductName.setText("");
        btnOpenAccount.setVisible(false);
    }//GEN-LAST:event_cboTermActionPerformed
    long anticipatedInterest = 0;
    private void btnFindSuitableProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindSuitableProductActionPerformed
        if(cboSavingsAccountType.getSelectedItem() == null ||cboTerm.getSelectedItem() == null || txtDeposit.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Required field are empty", "Please fill required field...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Check balance
            if(Long.parseLong(txtDeposit.getText()) > dtoPaymentAccount.getCurrentBalance())
            {
                JOptionPane.showMessageDialog(this, "Current balance is not enough", "Incorrect details", JOptionPane.ERROR_MESSAGE);
            }
            else 
            {
                if(Long.parseLong(txtDeposit.getText()) < 1000000)
                {
                    JOptionPane.showMessageDialog(this, "The deposit amount must be more than 1,000,000 VND", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    // Bus lấy loại tài khoản tiết kiệm, lãi suất từ database
                    dtoSavingsAccountType = busSaving.getSavingsAccountType(cboSavingsAccountType.getSelectedItem().toString(), cboTerm.getSelectedItem().toString());
                    // Lấy lãi suất
                    double interestRate = dtoSavingsAccountType.getInterestRate();

                    // Tính ngày đáo hạn
                    String maturityDate = calculateMaturityDate();

                    // Tính tiền lãi dự kiến = số tiền gửi x  lãi suất (% năm)/ 12  x số tháng gửi
                    anticipatedInterest = (long)(Long.parseLong(txtDeposit.getText()) * interestRate * numberOfMonth) / 12;

                    // Tính tổng số tiền nhận được khi tất toán tài khoản tiết kiệm
                    long total = (long)(Long.parseLong(txtDeposit.getText()) + anticipatedInterest);

                    // Hiển thị sản phẩm phù hợp lên form
                    txtProductName.setText(dtoSavingsAccountType.getName());
                    txtInterestRate.setText(String.valueOf(Math.round(interestRate * 100.0 * 100.0) / 100.0) + "%/year");
                    txtAnticipatedInterest.setText(String.format("%,d",anticipatedInterest));
                    txtTotal.setText(String.format("%,d",total));
                    Date nowDate = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    txtStartDate.setText(df.format(nowDate));
                    txtMaturityDate.setText(maturityDate);
                    btnOpenAccount.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnFindSuitableProductActionPerformed

    private void btnOpenAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenAccountActionPerformed
        if(confirmPassword())
        {
            // Lấy ngày đáo hạn từ form xuống
            Date maturityDate = null;
            try {
                maturityDate = new SimpleDateFormat("yyyy-MM-dd").parse(txtMaturityDate.getText());
            } catch (ParseException ex) {
                Logger.getLogger(Saving_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Account_DTO dtoNewSavingAccount = new Account_DTO(dtoSavingsAccountType.getId(), Long.parseLong(txtDeposit.getText()), maturityDate, anticipatedInterest , dtoCustomer.getId());
            if(busSaving.openSavingsAccount(dtoNewSavingAccount))
            {
                JOptionPane.showConfirmDialog(null, "Open savings account is successful", "Successful", JOptionPane.CLOSED_OPTION);
                createTable();
                txtTotalSavingAccount.setText(String.format("%,d",busSaving.getTotalSavingsAmount(dtoCustomer)) + " VND");
                
                //Clear form
                cboSavingsAccountType.setSelectedItem(null);
                cboTerm.setSelectedItem(null);
                txtDeposit.setText("");
                txtInterestRate.setText("");
                txtAnticipatedInterest.setText("");
                txtTotal.setText("");
                txtStartDate.setText("");
                txtMaturityDate.setText("");
                txtProductName.setText("");
                btnOpenAccount.setVisible(false);
            }
            else
                JOptionPane.showConfirmDialog(null, "Cannot open savings account.", "Error", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnOpenAccountActionPerformed

    private void cboSavingsAccountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSavingsAccountTypeActionPerformed
        //Clear form
        txtInterestRate.setText("");
        txtAnticipatedInterest.setText("");
        txtTotal.setText("");
        txtStartDate.setText("");
        txtMaturityDate.setText("");
        txtProductName.setText("");
        btnOpenAccount.setVisible(false);
    }//GEN-LAST:event_cboSavingsAccountTypeActionPerformed

    private void txtDepositKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtDepositKeyTyped
    
    int savingAccountId = 0;
    private void tblSavingsAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSavingsAccountMouseClicked
        int row = tblSavingsAccount.getSelectedRow();
        if(row  < tblAccountModel.getRowCount() && row  >= 0)
            savingAccountId = Integer.parseInt(tblSavingsAccount.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tblSavingsAccountMouseClicked

    private void txtDepositMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDepositMouseClicked
        txtInterestRate.setText("");
        txtAnticipatedInterest.setText("");
        txtTotal.setText("");
        txtStartDate.setText("");
        txtMaturityDate.setText("");
        txtProductName.setText("");
        btnOpenAccount.setVisible(false);
    }//GEN-LAST:event_txtDepositMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OpenOnlineSavings;
    private javax.swing.JPanel WithdrawOnlineSavings;
    private javax.swing.JButton btnFindSuitableProduct;
    private javax.swing.JButton btnHome_OpenAccount;
    private javax.swing.JButton btnHome_ViewAccount;
    private javax.swing.JButton btnOpenAccount;
    private javax.swing.JButton btnSettlement;
    private javax.swing.JComboBox<String> cboSavingsAccountType;
    private javax.swing.JComboBox<String> cboTerm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAnticipatedInterest;
    private javax.swing.JLabel lblCurrentSavingAccountsList;
    private javax.swing.JLabel lblDebitAccount;
    private javax.swing.JLabel lblDebitAccount4;
    private javax.swing.JLabel lblDebitAccount5;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblIcon1;
    private javax.swing.JLabel lblInterestRate;
    private javax.swing.JLabel lblMaturityDate;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblTerm;
    private javax.swing.JLabel lblTitle_OpenAccount;
    private javax.swing.JLabel lblTitle_ViewAccount;
    private javax.swing.JLabel lblTotalReceivedAmount;
    private javax.swing.JLabel lblTotalSavingAccount;
    private javax.swing.JLabel lblVND;
    private javax.swing.JLabel lblVND2;
    private javax.swing.JLabel lblVND3;
    private javax.swing.JLabel lxlInputError_Deposits;
    private javax.swing.JPanel pnlSuitableProductDetails;
    private javax.swing.JTable tblSavingsAccount;
    private javax.swing.JTextField txtAnticipatedInterest;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtInterestRate;
    private javax.swing.JTextField txtMaturityDate;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JFormattedTextField txtTotalSavingAccount;
    // End of variables declaration//GEN-END:variables
}
