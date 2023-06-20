package GUI;

import BUS.Payment_BUS;
import DTO.Account_DTO;
import DTO.Bill_DTO;
import DTO.Customer_DTO;
import DTO.Supplier_DTO;
import DTO.User_Login_DTO;
import GUI.CustomerHome_GUI;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Payment_GUI extends javax.swing.JFrame 
{
    Payment_BUS busPayment = new Payment_BUS();
    Account_DTO dtoAccount = null; 
    Customer_DTO dtoCustomer = null;
    Bill_DTO dtoBill = null;
    
    public Payment_GUI(Customer_DTO customer, Account_DTO account) 
    {
        initComponents();
        dtoCustomer = customer;
        dtoAccount = account; 
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Payment"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        cboMonth.setSelectedItem(null); // Set giá trị ban đầu của combobox là null
        cboYear.setSelectedItem(null);
        btnPayment.setEnabled(true);

        loadCboServiceType();
    }
    
    public void loadCboServiceType()
    {
        // Get service types information
        ArrayList<String> serviceTypeList = busPayment.getServiceTypeList();
        // Load data into the combobox
        for(String serviceType : serviceTypeList)
        {
            cboServiceType.addItem(serviceType);
        }
        cboServiceType.setSelectedItem(null);
    }
    
    public void clearBillLookupInformation()
    {
        txtBillID_BillLookup.setText("");
        txtCustomerID_BillLookup.setText("");
        txtSupplier_BillLookup.setText("");
        txtBillDate_BillLookup.setText("");
        txtBillAmount_BillLookup.setText("");
        txtStatus_BillLookup.setText("");
        btnPayment.setVisible(false);
    }
    
    private boolean confirmPassword()
    {
        // Show password input dialog
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please enter your password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Confirm", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Verify by password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

        if(option == 0) // Customer pressing Confirm button
        {
            String password = pass.getText();
            
            // Get user login information
            User_Login_DTO dtoUserLogIn = busPayment.getUserLogin(dtoCustomer); 
            
            // Check password
            if(password.equals(dtoUserLogIn.getPassword()))
                return true;
            else
                JOptionPane.showMessageDialog(this, "Password is incorrect", "Incorrect details", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboServiceType = new javax.swing.JComboBox<>();
        cboSupplierName = new javax.swing.JComboBox<>();
        lblImage_Transfer = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblServiceType = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        btnPayment = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pnlBillLookupInformation = new javax.swing.JPanel();
        lblBillLookupInformation = new javax.swing.JLabel();
        lblBillID_BillLookup = new javax.swing.JLabel();
        lblSupplier_BillLookup = new javax.swing.JLabel();
        lblCustomerID_BillLookup = new javax.swing.JLabel();
        lblBillDate_BillLookup = new javax.swing.JLabel();
        lblBillAmount_BillLookup = new javax.swing.JLabel();
        lblStatus_BillLookup = new javax.swing.JLabel();
        txtSupplier_BillLookup = new javax.swing.JTextField();
        txtBillID_BillLookup = new javax.swing.JTextField();
        txtCustomerID_BillLookup = new javax.swing.JTextField();
        txtBillDate_BillLookup = new javax.swing.JTextField();
        txtBillAmount_BillLookup = new javax.swing.JTextField();
        txtStatus_BillLookup = new javax.swing.JTextField();
        lblCustomerID = new javax.swing.JLabel();
        btnBillLookup = new javax.swing.JButton();
        lblSupplierName = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox<>();
        lblMonth = new javax.swing.JLabel();
        cboMonth = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Payment");

        jPanel1.setBackground(new java.awt.Color(239, 250, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboServiceType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboServiceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServiceTypeActionPerformed(evt);
            }
        });
        jPanel1.add(cboServiceType, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 270, -1));

        cboSupplierName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSupplierNameActionPerformed(evt);
            }
        });
        jPanel1.add(cboSupplierName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 270, -1));

        lblImage_Transfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Payment_Water.png"))); // NOI18N
        jPanel1.add(lblImage_Transfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 100));

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
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, 50, 57));

        lblTitle.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("         Bill Payment");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 30, 931, 58));

        lblServiceType.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblServiceType.setForeground(new java.awt.Color(59, 196, 116));
        lblServiceType.setText("Service Type:");
        jPanel1.add(lblServiceType, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        lblYear.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblYear.setForeground(new java.awt.Color(59, 196, 116));
        lblYear.setText("Year:");
        jPanel1.add(lblYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, -1, -1));

        txtCustomerID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCustomerID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustomerIDMouseClicked(evt);
            }
        });
        txtCustomerID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCustomerIDKeyTyped(evt);
            }
        });
        jPanel1.add(txtCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 270, -1));

        btnPayment.setBackground(new java.awt.Color(59, 196, 116));
        btnPayment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Payment_cash_in_hand.png"))); // NOI18N
        btnPayment.setText("Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        jPanel1.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 140, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 600, 10));

        lblBillLookupInformation.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblBillLookupInformation.setForeground(new java.awt.Color(59, 196, 116));
        lblBillLookupInformation.setText("Bill Lookup Information");

        lblBillID_BillLookup.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblBillID_BillLookup.setForeground(new java.awt.Color(59, 196, 116));
        lblBillID_BillLookup.setText("Bill ID:");

        lblSupplier_BillLookup.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblSupplier_BillLookup.setForeground(new java.awt.Color(59, 196, 116));
        lblSupplier_BillLookup.setText("Supplier:");

        lblCustomerID_BillLookup.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblCustomerID_BillLookup.setForeground(new java.awt.Color(59, 196, 116));
        lblCustomerID_BillLookup.setText("Customer ID:");

        lblBillDate_BillLookup.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblBillDate_BillLookup.setForeground(new java.awt.Color(59, 196, 116));
        lblBillDate_BillLookup.setText("Bill Date:");

        lblBillAmount_BillLookup.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        lblBillAmount_BillLookup.setForeground(new java.awt.Color(59, 196, 116));
        lblBillAmount_BillLookup.setText("Bill Amount:");

        lblStatus_BillLookup.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblStatus_BillLookup.setForeground(new java.awt.Color(59, 196, 116));
        lblStatus_BillLookup.setText("Status:");

        txtSupplier_BillLookup.setEditable(false);
        txtSupplier_BillLookup.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtSupplier_BillLookup.setForeground(new java.awt.Color(32, 172, 216));

        txtBillID_BillLookup.setEditable(false);
        txtBillID_BillLookup.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtBillID_BillLookup.setForeground(new java.awt.Color(32, 172, 216));

        txtCustomerID_BillLookup.setEditable(false);
        txtCustomerID_BillLookup.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtCustomerID_BillLookup.setForeground(new java.awt.Color(32, 172, 216));

        txtBillDate_BillLookup.setEditable(false);
        txtBillDate_BillLookup.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtBillDate_BillLookup.setForeground(new java.awt.Color(32, 172, 216));

        txtBillAmount_BillLookup.setEditable(false);
        txtBillAmount_BillLookup.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtBillAmount_BillLookup.setForeground(new java.awt.Color(32, 172, 216));

        txtStatus_BillLookup.setEditable(false);
        txtStatus_BillLookup.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        txtStatus_BillLookup.setForeground(new java.awt.Color(32, 172, 216));

        javax.swing.GroupLayout pnlBillLookupInformationLayout = new javax.swing.GroupLayout(pnlBillLookupInformation);
        pnlBillLookupInformation.setLayout(pnlBillLookupInformationLayout);
        pnlBillLookupInformationLayout.setHorizontalGroup(
            pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillLookupInformationLayout.createSequentialGroup()
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBillLookupInformationLayout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(lblBillLookupInformation))
                    .addGroup(pnlBillLookupInformationLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBillAmount_BillLookup)
                            .addComponent(lblStatus_BillLookup)
                            .addComponent(lblBillDate_BillLookup)
                            .addComponent(lblSupplier_BillLookup)
                            .addComponent(lblCustomerID_BillLookup)
                            .addComponent(lblBillID_BillLookup))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStatus_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBillAmount_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBillID_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustomerID_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupplier_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBillDate_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        pnlBillLookupInformationLayout.setVerticalGroup(
            pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillLookupInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBillLookupInformation)
                .addGap(18, 18, 18)
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBillID_BillLookup)
                    .addComponent(txtBillID_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerID_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomerID_BillLookup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupplier_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSupplier_BillLookup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBillDate_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBillDate_BillLookup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBillAmount_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBillAmount_BillLookup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBillLookupInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStatus_BillLookup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus_BillLookup))
                .addContainerGap())
        );

        jPanel1.add(pnlBillLookupInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 600, 270));

        lblCustomerID.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblCustomerID.setForeground(new java.awt.Color(59, 196, 116));
        lblCustomerID.setText("Customer ID:");
        jPanel1.add(lblCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        btnBillLookup.setBackground(new java.awt.Color(59, 196, 116));
        btnBillLookup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBillLookup.setForeground(new java.awt.Color(255, 255, 255));
        btnBillLookup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Search.png"))); // NOI18N
        btnBillLookup.setText("Lookup Bill");
        btnBillLookup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillLookupActionPerformed(evt);
            }
        });
        jPanel1.add(btnBillLookup, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 140, -1));

        lblSupplierName.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblSupplierName.setForeground(new java.awt.Color(59, 196, 116));
        lblSupplierName.setText("Supplier Name:");
        jPanel1.add(lblSupplierName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        cboYear.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2020", "2021", "2022", "2023" }));
        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });
        jPanel1.add(cboYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 90, 30));

        lblMonth.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblMonth.setForeground(new java.awt.Color(59, 196, 116));
        lblMonth.setText("Month");
        jPanel1.add(lblMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        cboMonth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonthActionPerformed(evt);
            }
        });
        jPanel1.add(cboMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        new CustomerHome_GUI(dtoCustomer);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // Check balance
        if(dtoAccount.getCurrentBalance() > Long.parseLong(txtBillAmount_BillLookup.getText().replace(",", "").replace(" VND", "")))
        {
            if(confirmPassword())
            {
                // Make payment transaction
                if(busPayment.payment(dtoBill, dtoAccount))
                {
                    JOptionPane.showConfirmDialog(null, "Payment is successful", "Successful", JOptionPane.CLOSED_OPTION);

                    int reply = JOptionPane.showConfirmDialog(null, "Would you like to print out the payment receipt?", "Notification", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) 
                    {
                        busPayment.showPaymentReceipt(dtoBill.getId());
                    }
                    //Clear form
                    cboServiceType.setSelectedItem(null);
                    txtCustomerID.setText("");
                    cboMonth.setSelectedItem(null);
                    cboYear.setSelectedItem(null);
                    txtCustomerID.setText("");

                    clearBillLookupInformation();
                }
                else
                    JOptionPane.showMessageDialog(this, "Payment is unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Current balance is not enough", "Incorrect details", JOptionPane.ERROR_MESSAGE); 
            clearBillLookupInformation();
        }  
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void cboServiceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServiceTypeActionPerformed
        clearBillLookupInformation();
        cboSupplierName.removeAllItems();
        
        if(cboServiceType.getSelectedItem() == null)
            cboSupplierName.setSelectedItem(null);
        else
        {
            ArrayList<Supplier_DTO> supplierNameList = supplierNameList = busPayment.getSupplierNameList(cboServiceType.getSelectedItem().toString());
            for(Supplier_DTO supplier : supplierNameList)
            {
                cboSupplierName.addItem(supplier.getName());
            }
        }
    }//GEN-LAST:event_cboServiceTypeActionPerformed

    private void btnBillLookupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillLookupActionPerformed
        // Check if the user input is enough or not
        if(cboServiceType.getSelectedItem() == null || cboSupplierName.getSelectedItem() == null || txtCustomerID.getText().equals("") || cboMonth.getSelectedItem() == null || cboYear.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Required field are empty", "Please fill required field...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Lấy thông tin từ form xuống
            long customerId = Long.parseLong(txtCustomerID.getText().replace(" ", ""));
            String supplierName = cboSupplierName.getSelectedItem().toString();
            int month = Integer.parseInt(cboMonth.getSelectedItem().toString());
            int year = Integer.parseInt(cboYear.getSelectedItem().toString());
            // Get bill information
            dtoBill = busPayment.getBillInformation(new Customer_DTO(customerId), new Supplier_DTO(supplierName),month , year);
            if(dtoBill != null) // Hóa đơn tồn tại
            {
                if(dtoBill.getStatus().equals("Unpaid")) // Hóa đơn chưa được thanh toán
                {
                    // Display bill information
                    txtBillID_BillLookup.setText(String.valueOf(dtoBill.getId()));
                    txtCustomerID_BillLookup.setText(String.valueOf(dtoBill.getCustomerID()));
                    txtSupplier_BillLookup.setText(cboSupplierName.getSelectedItem().toString());
                    txtBillDate_BillLookup.setText(dtoBill.getInvoiceDate().toString());
                    txtStatus_BillLookup.setText(dtoBill.getStatus());
                    txtBillAmount_BillLookup.setText(String.format("%,d", dtoBill.getBillAmount()) + " VND");
                    
                    // Show Payment button
                    btnPayment.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "This bill has been paid", "Paid", JOptionPane.CLOSED_OPTION);
                    clearBillLookupInformation();
                }
            }
            else
                JOptionPane.showMessageDialog(this, "This bill is not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBillLookupActionPerformed

    private void cboSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSupplierNameActionPerformed
        clearBillLookupInformation();
    }//GEN-LAST:event_cboSupplierNameActionPerformed

    private void cboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonthActionPerformed
        clearBillLookupInformation();
    }//GEN-LAST:event_cboMonthActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
        clearBillLookupInformation();
    }//GEN-LAST:event_cboYearActionPerformed

    private void txtCustomerIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustomerIDMouseClicked
        clearBillLookupInformation();
    }//GEN-LAST:event_txtCustomerIDMouseClicked

    private void txtCustomerIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerIDKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtCustomerIDKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBillLookup;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnPayment;
    private javax.swing.JComboBox<String> cboMonth;
    private javax.swing.JComboBox<String> cboServiceType;
    private javax.swing.JComboBox<String> cboSupplierName;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBillAmount_BillLookup;
    private javax.swing.JLabel lblBillDate_BillLookup;
    private javax.swing.JLabel lblBillID_BillLookup;
    private javax.swing.JLabel lblBillLookupInformation;
    private javax.swing.JLabel lblCustomerID;
    private javax.swing.JLabel lblCustomerID_BillLookup;
    private javax.swing.JLabel lblImage_Transfer;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblServiceType;
    private javax.swing.JLabel lblStatus_BillLookup;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblSupplier_BillLookup;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblYear;
    private javax.swing.JPanel pnlBillLookupInformation;
    private javax.swing.JTextField txtBillAmount_BillLookup;
    private javax.swing.JTextField txtBillDate_BillLookup;
    private javax.swing.JTextField txtBillID_BillLookup;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtCustomerID_BillLookup;
    private javax.swing.JTextField txtStatus_BillLookup;
    private javax.swing.JTextField txtSupplier_BillLookup;
    // End of variables declaration//GEN-END:variables
}
