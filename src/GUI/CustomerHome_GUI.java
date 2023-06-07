package GUI;

import BUS.CustomerHome_BUS;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CustomerHome_GUI extends javax.swing.JFrame 
{
    CustomerHome_BUS busCustomer = new CustomerHome_BUS();
    Customer_DTO dtoCustomer = null; // Người đang đăng nhập hệ thống
   
    public CustomerHome_GUI(Customer_DTO customer) 
    {
        initComponents();
        dtoCustomer = customer;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Customer Home"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        // Show customer's name
        lblHelloCustomer.setText(dtoCustomer.getFirstName() + " " + dtoCustomer.getLastName());
    }
    class JPanelGradient extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            
            Color color1 = new Color(15, 141, 125);
            Color color2 = new Color(141, 189, 80);
            GradientPaint gp = new GradientPaint(0,0,color2,180,height,color1);
            g2d.setPaint(gp);
            g2d.fillRect(0,0,width,height);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        btnMyWallet = new javax.swing.JButton();
        btnTransfer = new javax.swing.JButton();
        btnSaving = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        jPanel2 = new JPanelGradient();
        lblIcon = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblHelloCustomer = new javax.swing.JLabel();
        btnPersonalInfo = new javax.swing.JButton();
        lblHelloCustomer1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Customer Home");
        setPreferredSize(new java.awt.Dimension(1070, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMyWallet.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMyWallet.setForeground(new java.awt.Color(59, 196, 116));
        btnMyWallet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_vicuatoi.png"))); // NOI18N
        btnMyWallet.setText("My Wallet");
        btnMyWallet.setBorder(null);
        btnMyWallet.setBorderPainted(false);
        btnMyWallet.setDefaultCapable(false);
        btnMyWallet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMyWallet.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnMyWallet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMyWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyWalletActionPerformed(evt);
            }
        });
        pnlBackground.add(btnMyWallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 212, 200));

        btnTransfer.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnTransfer.setForeground(new java.awt.Color(59, 196, 116));
        btnTransfer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_chyentien.png"))); // NOI18N
        btnTransfer.setText("Transfer");
        btnTransfer.setBorder(null);
        btnTransfer.setBorderPainted(false);
        btnTransfer.setDefaultCapable(false);
        btnTransfer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTransfer.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnTransfer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });
        pnlBackground.add(btnTransfer, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 212, 200));

        btnSaving.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnSaving.setForeground(new java.awt.Color(59, 196, 116));
        btnSaving.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_tietkiem.png"))); // NOI18N
        btnSaving.setText("Saving");
        btnSaving.setBorder(null);
        btnSaving.setBorderPainted(false);
        btnSaving.setDefaultCapable(false);
        btnSaving.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaving.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnSaving.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSaving.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavingActionPerformed(evt);
            }
        });
        pnlBackground.add(btnSaving, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 212, 200));

        btnPayment.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnPayment.setForeground(new java.awt.Color(59, 196, 116));
        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_thanhtoan.png"))); // NOI18N
        btnPayment.setText("Payment");
        btnPayment.setBorder(null);
        btnPayment.setBorderPainted(false);
        btnPayment.setDefaultCapable(false);
        btnPayment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPayment.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnPayment.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        pnlBackground.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 180, 212, 200));

        jPanel2.setBackground(new java.awt.Color(59, 196, 116));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/user (Custom).png"))); // NOI18N
        jPanel2.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 211, 220, 102));

        btnLogOut.setBackground(new java.awt.Color(239, 250, 252));
        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(59, 196, 116));
        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 523, 150, 40));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 319, 220, 31));

        lblHelloCustomer.setBackground(new java.awt.Color(32, 172, 210));
        lblHelloCustomer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHelloCustomer.setForeground(new java.awt.Color(255, 255, 255));
        lblHelloCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHelloCustomer.setText("Customer");
        lblHelloCustomer.setToolTipText("");
        lblHelloCustomer.setMaximumSize(new java.awt.Dimension(341, 48));
        lblHelloCustomer.setMinimumSize(new java.awt.Dimension(341, 48));
        jPanel2.add(lblHelloCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 220, 28));

        pnlBackground.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 220, 650));

        btnPersonalInfo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnPersonalInfo.setForeground(new java.awt.Color(59, 196, 116));
        btnPersonalInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/personalInformation_icon.png"))); // NOI18N
        btnPersonalInfo.setText("Personal Info");
        btnPersonalInfo.setBorder(null);
        btnPersonalInfo.setBorderPainted(false);
        btnPersonalInfo.setDefaultCapable(false);
        btnPersonalInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonalInfo.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnPersonalInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalInfoActionPerformed(evt);
            }
        });
        pnlBackground.add(btnPersonalInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 212, 200));

        lblHelloCustomer1.setBackground(new java.awt.Color(59, 196, 116));
        lblHelloCustomer1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblHelloCustomer1.setForeground(new java.awt.Color(255, 255, 255));
        lblHelloCustomer1.setText("          Customer Portal");
        lblHelloCustomer1.setOpaque(true);
        pnlBackground.add(lblHelloCustomer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 850, 80));

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1070, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // Show Logout interface 
        setVisible(false);
        new LogIn_GUI();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnMyWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyWalletActionPerformed
        // Get account information
        Account_DTO dtoAccount = busCustomer.getPaymentAccount(dtoCustomer);
        
        // Show MyWallet interface 
        setVisible(false);
        new MyWallet_GUI(dtoCustomer, dtoAccount);
    }//GEN-LAST:event_btnMyWalletActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        // Get account information
        Account_DTO dtoAccount = busCustomer.getPaymentAccount(dtoCustomer);
        
        // Check the account's status
        if(dtoAccount.getStatus().equals("Active"))
        {
            // Show Transfer interface 
            setVisible(false);
            new Transfer_GUI(dtoCustomer, dtoAccount);
        }
        else
            JOptionPane.showMessageDialog(this, "Your payment account is locked. You cannot make any transactions", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnTransferActionPerformed

    private void btnSavingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavingActionPerformed
        // Get account information
        Account_DTO dtoAccount = busCustomer.getPaymentAccount(dtoCustomer);
        
        // Check the account's status
        if(dtoAccount.getStatus().equals("Active"))
        {
            // Show Saving interface
            setVisible(false);
            new Saving_GUI(dtoCustomer, dtoAccount);
        }
        else
            JOptionPane.showMessageDialog(this, "Your payment account is locked. You cannot make any transactions", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnSavingActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // Get account information
        Account_DTO dtoAccount = busCustomer.getPaymentAccount(dtoCustomer);
        
        // Check the account's status
        if(dtoAccount.getStatus().equals("Active"))
        {
            // Show Payment interface
            setVisible(false);
            new Payment_GUI(dtoCustomer, dtoAccount);
        }
        else
            JOptionPane.showMessageDialog(this, "Your payment account is locked. You cannot make any transactions", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnPersonalInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalInfoActionPerformed
        setVisible(false);
        new PersonalInformation_GUI(dtoCustomer);
    }//GEN-LAST:event_btnPersonalInfoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnMyWallet;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPersonalInfo;
    private javax.swing.JButton btnSaving;
    private javax.swing.JButton btnTransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHelloCustomer;
    private javax.swing.JLabel lblHelloCustomer1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JPanel pnlBackground;
    // End of variables declaration//GEN-END:variables
}
