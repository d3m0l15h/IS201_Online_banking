package GUI;

import BUS.Deposit_BUS;
import DTO.Account_DTO;
import DTO.Employee_DTO;
import DTO.Customer_DTO;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class Deposit_GUI extends javax.swing.JFrame {

    Deposit_BUS busDeposit = new Deposit_BUS();
    Employee_DTO dtoAdmin = null;
    ButtonGroup radioGroup;
    public Deposit_GUI(Employee_DTO admin) 
    {
        initComponents();
        dtoAdmin  = admin;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Deposit"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        setupRadioButtonGroup();
    }

    public void setupRadioButtonGroup()
    {
        radioGroup = new ButtonGroup();
        radioGroup.add(rbAccountOpening);
        radioGroup.add(rbDepositToAccount);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Panel_OpenAccount = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        lblBeneficiaryName = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnDeposit = new javax.swing.JButton();
        lbIcon_OpenAccount = new javax.swing.JLabel();
        lbOpenAccount = new javax.swing.JLabel();
        txtBeneficiaryName = new javax.swing.JTextField();
        lblTransactionType = new javax.swing.JLabel();
        lblDepositInformation = new javax.swing.JLabel();
        lbCustomerID = new javax.swing.JLabel();
        rbDepositToAccount = new javax.swing.JRadioButton();
        rbAccountOpening = new javax.swing.JRadioButton();
        lblBeneficiaryInformation = new javax.swing.JLabel();
        txtAccountID = new javax.swing.JTextField();
        lblAccountID = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deposit");

        Panel_OpenAccount.setBackground(new java.awt.Color(239, 250, 252));
        Panel_OpenAccount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setBackground(new java.awt.Color(59, 196, 116));
        btnHome.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setToolTipText("");
        btnHome.setBorder(null);
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome.setIconTextGap(1);
        btnHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        Panel_OpenAccount.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 76, 64));

        lblBeneficiaryName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblBeneficiaryName.setForeground(new java.awt.Color(59, 196, 116));
        lblBeneficiaryName.setText("Customer's name ");
        Panel_OpenAccount.add(lblBeneficiaryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 160, -1));

        lblAmount.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblAmount.setForeground(new java.awt.Color(59, 196, 116));
        lblAmount.setText("Amount:  ");
        Panel_OpenAccount.add(lblAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, -1, -1));

        txtAmount.setEditable(false);
        txtAmount.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmountKeyTyped(evt);
            }
        });
        Panel_OpenAccount.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 460, 370, -1));

        btnDeposit.setBackground(new java.awt.Color(59, 196, 116));
        btnDeposit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnDeposit.setForeground(new java.awt.Color(255, 255, 255));
        btnDeposit.setText("Deposit");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });
        Panel_OpenAccount.add(btnDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, 140, 40));

        lbIcon_OpenAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Deposit.png"))); // NOI18N
        Panel_OpenAccount.add(lbIcon_OpenAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 120, 110));

        lbOpenAccount.setBackground(new java.awt.Color(59, 196, 116));
        lbOpenAccount.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lbOpenAccount.setForeground(new java.awt.Color(255, 255, 255));
        lbOpenAccount.setText("       Deposit");
        lbOpenAccount.setOpaque(true);
        lbOpenAccount.setPreferredSize(new java.awt.Dimension(34, 50));
        Panel_OpenAccount.add(lbOpenAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 930, 66));

        txtBeneficiaryName.setEditable(false);
        txtBeneficiaryName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_OpenAccount.add(txtBeneficiaryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 370, -1));

        lblTransactionType.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTransactionType.setForeground(new java.awt.Color(59, 196, 116));
        lblTransactionType.setText("Transaction Type:");
        Panel_OpenAccount.add(lblTransactionType, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        lblDepositInformation.setBackground(new java.awt.Color(32, 172, 216));
        lblDepositInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDepositInformation.setForeground(new java.awt.Color(59, 196, 116));
        lblDepositInformation.setText("Deposit Information");
        Panel_OpenAccount.add(lblDepositInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        lbCustomerID.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lbCustomerID.setForeground(new java.awt.Color(59, 196, 116));
        lbCustomerID.setText("Customer ID ");
        Panel_OpenAccount.add(lbCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 160, -1));

        rbDepositToAccount.setBackground(new java.awt.Color(239, 250, 252));
        rbDepositToAccount.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        rbDepositToAccount.setForeground(new java.awt.Color(59, 196, 116));
        rbDepositToAccount.setText("Deposit to Account");
        rbDepositToAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDepositToAccountActionPerformed(evt);
            }
        });
        Panel_OpenAccount.add(rbDepositToAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        rbAccountOpening.setBackground(new java.awt.Color(239, 250, 252));
        rbAccountOpening.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        rbAccountOpening.setForeground(new java.awt.Color(59, 196, 116));
        rbAccountOpening.setText("Account Opening");
        rbAccountOpening.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAccountOpeningActionPerformed(evt);
            }
        });
        Panel_OpenAccount.add(rbAccountOpening, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        lblBeneficiaryInformation.setBackground(new java.awt.Color(32, 172, 216));
        lblBeneficiaryInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBeneficiaryInformation.setForeground(new java.awt.Color(59, 196, 116));
        lblBeneficiaryInformation.setText("Beneficiary Information");
        Panel_OpenAccount.add(lblBeneficiaryInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        txtAccountID.setEditable(false);
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
        txtAccountID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAccountIDKeyTyped(evt);
            }
        });
        Panel_OpenAccount.add(txtAccountID, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 370, -1));

        lblAccountID.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblAccountID.setForeground(new java.awt.Color(59, 196, 116));
        lblAccountID.setText("Account ID ");
        Panel_OpenAccount.add(lblAccountID, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 160, -1));

        txtCustomerID.setEditable(false);
        txtCustomerID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCustomerID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustomerIDMouseClicked(evt);
            }
        });
        txtCustomerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerIDActionPerformed(evt);
            }
        });
        txtCustomerID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCustomerIDKeyTyped(evt);
            }
        });
        Panel_OpenAccount.add(txtCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 370, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_OpenAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Panel_OpenAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        this.setVisible(false);
        new AdminHome_GUI(dtoAdmin);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void txtAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtAmountKeyTyped

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        // Check the status of  radio button group
        if(rbAccountOpening.isSelected())
        {
            // Check if the user input is enough or not
            if(txtCustomerID.getText().equals("") || txtAmount.getText().equals("") || txtBeneficiaryName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                // Check amount
                if(Long.parseLong(txtAmount.getText()) >= 50000)
                {
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure to open this payment account for customer?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) 
                    {
                        if(busDeposit.openPaymentAccount(Long.parseLong(txtCustomerID.getText()), Long.parseLong(txtAmount.getText())))
                        {
                            JOptionPane.showMessageDialog(this, "Successful account opening" , "Notification", JOptionPane.INFORMATION_MESSAGE);  
                            clearForm();
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Cannot account opening", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                   JOptionPane.showMessageDialog(this, "The initial amount must be more than 50,000 VND", "Error", JOptionPane.ERROR_MESSAGE);  
            }
        }
        if(rbDepositToAccount.isSelected())
        {
            // Check if the user input is enough or not
            if(txtAccountID.getText().equals("") || txtBeneficiaryName.getText().equals("") || txtAmount.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                // Check amount
                if(Long.parseLong(txtAmount.getText()) >= 100000)
                {
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure to deposit for this account?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) 
                    {
                        long amount = Long.parseLong(txtAmount.getText());
                        Account_DTO dtoAccount = new Account_DTO(Long.parseLong(txtAccountID.getText()));
                        if(busDeposit.deposit(dtoAccount, amount))
                        {
                            JOptionPane.showMessageDialog(this, "Successful deposit to account" , "Notification", JOptionPane.INFORMATION_MESSAGE);  
                            clearForm();
                        }
                        else
                            JOptionPane.showMessageDialog(this, "Cannot deposit to account", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "The amount must be more than 100,000 VND", "Error", JOptionPane.ERROR_MESSAGE); 
                }
            }
        }
    }//GEN-LAST:event_btnDepositActionPerformed

    private void txtAccountIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountIDMouseClicked
        txtBeneficiaryName.setText("");
        txtCustomerID.setText("");
    }//GEN-LAST:event_txtAccountIDMouseClicked

    private void txtAccountIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountIDActionPerformed
        // Check if the account id text field is filled or not
        if(txtAccountID.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Required field are empty", "Please fill required field...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Account_DTO dtoAccount = new Account_DTO(Long.parseLong(txtAccountID.getText()));
            // Get customer information
            Customer_DTO dtoCustomer = busDeposit.getCustomerInfo(dtoAccount);
            if(dtoCustomer == null)
            {
                JOptionPane.showMessageDialog(this, "Account ID is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                clearForm();
            }
            else
            {
                // Display customer id and customer's name 
                txtCustomerID.setText(String.valueOf(dtoCustomer.getId()));
                txtBeneficiaryName.setText(dtoCustomer.getFirstName() + " " + dtoCustomer.getLastName());
            }
        }
        
    }//GEN-LAST:event_txtAccountIDActionPerformed

    private void txtAccountIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAccountIDKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtAccountIDKeyTyped

    private void txtCustomerIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustomerIDMouseClicked
        txtBeneficiaryName.setText("");
    }//GEN-LAST:event_txtCustomerIDMouseClicked

    private void txtCustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerIDActionPerformed
        // Check if the customer text field is filled or not
        if(txtCustomerID.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Required field are empty", "Please fill required field...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Get customer information
            Customer_DTO dtoCustomer = busDeposit.getInformation(Long.parseLong(txtCustomerID.getText()));
            if(dtoCustomer == null)
            {
                JOptionPane.showMessageDialog(this, "Customer ID is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                clearForm();
            }
            else
            {
                // Display the customer's name 
                txtBeneficiaryName.setText(dtoCustomer.getFirstName() + " " + dtoCustomer.getLastName());
            }
        }
    }//GEN-LAST:event_txtCustomerIDActionPerformed

    private void txtCustomerIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerIDKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtCustomerIDKeyTyped

    public void clearForm()
    {
        txtAccountID.setText("");
        txtCustomerID.setText("");
        txtBeneficiaryName.setText("");
        txtAmount.setText("");
    }
    
    private void rbAccountOpeningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAccountOpeningActionPerformed
        clearForm();
        // Enable fillable textfield 
        txtAccountID.setEditable(false);
        txtCustomerID.setEditable(true);
        txtBeneficiaryName.setEditable(false);
        txtAmount.setEditable(true);
    }//GEN-LAST:event_rbAccountOpeningActionPerformed

    private void rbDepositToAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDepositToAccountActionPerformed
        clearForm();
        //Enable fillable textfield 
        txtAccountID.setEditable(true);
        txtCustomerID.setEditable(false);
        txtBeneficiaryName.setEditable(false);
        txtAmount.setEditable(true);
        
    }//GEN-LAST:event_rbDepositToAccountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_OpenAccount;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnHome;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lbCustomerID;
    private javax.swing.JLabel lbIcon_OpenAccount;
    private javax.swing.JLabel lbOpenAccount;
    private javax.swing.JLabel lblAccountID;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblBeneficiaryInformation;
    private javax.swing.JLabel lblBeneficiaryName;
    private javax.swing.JLabel lblDepositInformation;
    private javax.swing.JLabel lblTransactionType;
    private javax.swing.JRadioButton rbAccountOpening;
    private javax.swing.JRadioButton rbDepositToAccount;
    private javax.swing.JTextField txtAccountID;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBeneficiaryName;
    private javax.swing.JTextField txtCustomerID;
    // End of variables declaration//GEN-END:variables

}
