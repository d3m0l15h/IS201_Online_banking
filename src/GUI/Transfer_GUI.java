package GUI;

import BUS.Transfer_BUS;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.Transfer_Detail_DTO;
import DTO.User_Login_DTO;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Transfer_GUI extends javax.swing.JFrame 
{
    Transfer_BUS busTransfer = new Transfer_BUS();
    
    Account_DTO dtoAccount = null; // Tài khoản của người chuyển tiền
    Customer_DTO dtoCustomer = null; //Người chuyển tiền
    Account_DTO dtoReceiverAccount = null;
    
    public Transfer_GUI(Customer_DTO customer, Account_DTO account) 
    {
        initComponents();
        /*Nhận tham số từ CustomerHome_GUI truyền vào*/
        dtoAccount = account;
        dtoCustomer = customer;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Transfer"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
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
            // Get user login information
            User_Login_DTO dtoUserLogIn = busTransfer.getUserLogin(dtoCustomer); 
            // Check password
            String password = pass.getText();
            if(password.equals(dtoUserLogIn.getPassword()) == false)
                JOptionPane.showMessageDialog(this, "Password is incorrect", "Incorrect details", JOptionPane.ERROR_MESSAGE);
            else
                return true; // password is correct
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblImage_Transfer = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblImage_Description = new javax.swing.JLabel();
        lblReceiverAccount = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        lblContent = new javax.swing.JLabel();
        cboReceiverBank = new javax.swing.JComboBox<>();
        txtReceiverAccount = new javax.swing.JTextField();
        lbltienVND = new javax.swing.JLabel();
        btnContinue = new javax.swing.JButton();
        lblImage_Bank = new javax.swing.JLabel();
        lblImage_BAccount = new javax.swing.JLabel();
        lblReceiverInformation = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextPane();
        lblReceiverBank = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        txtReceiverName = new javax.swing.JTextField();
        lblReceiverName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transfer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(239, 250, 252));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage_Transfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Transfer_transfer.png"))); // NOI18N
        jPanel1.add(lblImage_Transfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 100));

        btnHome.setBackground(new java.awt.Color(59, 196, 116));
        btnHome.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
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
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 50, 57));

        lblTitle.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 29)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("         Transfer");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 30, 931, 58));

        lblImage_Description.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblImage_Description.setForeground(new java.awt.Color(51, 51, 51));
        lblImage_Description.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Transfer_Description.png"))); // NOI18N
        jPanel1.add(lblImage_Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 450, 40, -1));

        lblReceiverAccount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReceiverAccount.setForeground(new java.awt.Color(59, 196, 116));
        lblReceiverAccount.setText("Receiver account ");
        jPanel1.add(lblReceiverAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        lblAmount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAmount.setForeground(new java.awt.Color(59, 196, 116));
        lblAmount.setText("Amount");
        jPanel1.add(lblAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        lblContent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblContent.setForeground(new java.awt.Color(59, 196, 116));
        lblContent.setText("Content");
        jPanel1.add(lblContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, -1, -1));

        cboReceiverBank.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        cboReceiverBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vietcombank" }));
        jPanel1.add(cboReceiverBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 422, -1));

        txtReceiverAccount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtReceiverAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtReceiverAccountMouseClicked(evt);
            }
        });
        txtReceiverAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReceiverAccountActionPerformed(evt);
            }
        });
        txtReceiverAccount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReceiverAccountKeyTyped(evt);
            }
        });
        jPanel1.add(txtReceiverAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 422, -1));

        lbltienVND.setBackground(new java.awt.Color(32, 172, 216));
        lbltienVND.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbltienVND.setForeground(new java.awt.Color(59, 196, 116));
        lbltienVND.setText("VND");
        jPanel1.add(lbltienVND, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, -1, 30));

        btnContinue.setBackground(new java.awt.Color(59, 196, 116));
        btnContinue.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnContinue.setForeground(new java.awt.Color(255, 255, 255));
        btnContinue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Transfer_btnContinue.png"))); // NOI18N
        btnContinue.setText("Continue");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        jPanel1.add(btnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 560, -1, -1));

        lblImage_Bank.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblImage_Bank.setForeground(new java.awt.Color(51, 51, 51));
        lblImage_Bank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Transfer_Bank.png"))); // NOI18N
        jPanel1.add(lblImage_Bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, -1, -1));

        lblImage_BAccount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblImage_BAccount.setForeground(new java.awt.Color(51, 51, 51));
        lblImage_BAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Transfer_BeneficiaryAccount.png"))); // NOI18N
        jPanel1.add(lblImage_BAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 40, -1));

        lblReceiverInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblReceiverInformation.setForeground(new java.awt.Color(59, 196, 116));
        lblReceiverInformation.setText("Receiver Information");
        jPanel1.add(lblReceiverInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, 20));

        txtContent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(txtContent);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 430, 60));

        lblReceiverBank.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReceiverBank.setForeground(new java.awt.Color(59, 196, 116));
        lblReceiverBank.setText("Bank");
        jPanel1.add(lblReceiverBank, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, 20));

        txtAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmountKeyTyped(evt);
            }
        });
        jPanel1.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 250, -1));

        txtReceiverName.setEditable(false);
        txtReceiverName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtReceiverName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 422, -1));

        lblReceiverName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblReceiverName.setForeground(new java.awt.Color(59, 196, 116));
        lblReceiverName.setText("Receiver name");
        jPanel1.add(lblReceiverName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        // Check if the customer input is enough or not
        if(cboReceiverBank.getSelectedItem().equals("") || txtReceiverAccount.getText().equals("") || txtReceiverName.getText().equals("") ||  txtAmount.getText().equals("")|| txtContent.getText().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Required fields are empty", "Please fill all required fields...!", JOptionPane.CLOSED_OPTION);
        }
        else
        {
            // Check transfer amount
            long amount = Long.parseLong(txtAmount.getText());
            if(amount < 10000)
            {
                JOptionPane.showMessageDialog(this, "The transfer amount must be more than or equal to 10,000 VND", "Incorrect details", JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                // Check balance
                if(dtoAccount.getCurrentBalance() < Long.parseLong(txtAmount.getText().replaceAll("\\s+",""))) 
                {
                    JOptionPane.showMessageDialog(this, "Current balance is not enough", "Incorrect details", JOptionPane.ERROR_MESSAGE);   
                }
                else
                {
                    // Show password input dialog and check password
                    if(confirmPassword())
                    {
                        Transfer_Detail_DTO dtoTransferDetail = new Transfer_Detail_DTO(dtoAccount.getId(), dtoReceiverAccount.getId(), cboReceiverBank.getSelectedItem().toString(), Long.parseLong(txtAmount.getText()), txtContent.getText());
                        // Make transfer transaction
                        int transactionId = busTransfer.transfer(dtoTransferDetail); 
                        if(transactionId != 0) // Thực hiện giao dịch thành công
                        {
                            JOptionPane.showConfirmDialog(null, "Money transfer is successful", "Successful", JOptionPane.CLOSED_OPTION);

                            int reply = JOptionPane.showConfirmDialog(null, "Would you like to print out the transfer receipt?", "Notification", JOptionPane.YES_NO_OPTION);
                            if (reply == JOptionPane.YES_OPTION) 
                            {
                                // Request transfer receipt
                                busTransfer.showTransferReceipt(transactionId);
                            }
                            //Clear Form
                            cboReceiverBank.setSelectedItem(null);
                            txtReceiverAccount.setText("");
                            txtAmount.setText("");
                            txtReceiverName.setText("");
                            txtContent.setText("");
                        }
                        else
                           JOptionPane.showMessageDialog(this, "Money transfer was unsuccessful.", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                } 
            }  
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new CustomerHome_GUI(dtoCustomer);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void txtReceiverAccountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReceiverAccountKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtReceiverAccountKeyTyped

    private void txtAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtAmountKeyTyped

    private void txtReceiverAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReceiverAccountActionPerformed
        // Check if the user input is enough or not
        if(txtReceiverAccount.getText().equals("") || cboReceiverBank.getSelectedItem() == null) 
        {
           JOptionPane.showConfirmDialog(null, "Required fields are empty", "Please fill all required fields...!", JOptionPane.CLOSED_OPTION);
        }
        else
        {
            dtoReceiverAccount = new Account_DTO(Long.parseLong(txtReceiverAccount.getText()));
           // Check receiver account information
           if(busTransfer.isValidPaymentAccount(dtoReceiverAccount) == false || dtoReceiverAccount.getId() == dtoAccount.getId()) // Kiểm tra tài khoản người nhận 
           {
               JOptionPane.showConfirmDialog(null, "Receiver account is invalid", "Error", JOptionPane.CLOSED_OPTION);
               txtReceiverName.setText("");
           }
           else
           {
               // Get receiver information
               Customer_DTO dtoReceiver = busTransfer.getCustomerInfo(dtoReceiverAccount);
               // Display receiver name
               txtReceiverName.setText(dtoReceiver.getFirstName() + " " + dtoReceiver.getLastName());
           }
        }
    }//GEN-LAST:event_txtReceiverAccountActionPerformed

    private void txtReceiverAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReceiverAccountMouseClicked
        txtReceiverName.setText("");
    }//GEN-LAST:event_txtReceiverAccountMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnHome;
    private javax.swing.JComboBox<String> cboReceiverBank;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblContent;
    private javax.swing.JLabel lblImage_BAccount;
    private javax.swing.JLabel lblImage_Bank;
    private javax.swing.JLabel lblImage_Description;
    private javax.swing.JLabel lblImage_Transfer;
    private javax.swing.JLabel lblReceiverAccount;
    private javax.swing.JLabel lblReceiverBank;
    private javax.swing.JLabel lblReceiverInformation;
    private javax.swing.JLabel lblReceiverName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lbltienVND;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextPane txtContent;
    private javax.swing.JTextField txtReceiverAccount;
    private javax.swing.JTextField txtReceiverName;
    // End of variables declaration//GEN-END:variables
}
