package GUI;

import DTO.Employee_DTO;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class AdminHome_GUI extends javax.swing.JFrame 
{
    Employee_DTO dtoAdmin = null;
    
    public AdminHome_GUI(Employee_DTO admin) 
    {
        initComponents();
        dtoAdmin = admin;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Admin Home"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        // Show the admin's name
        lblHelloAdmin.setText(admin.getFirstName() + " " + admin.getLastName());
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

        pnlBackground_Admin = new javax.swing.JPanel();
        pnlWelcome = new JPanelGradient();
        lblIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        lblHelloAdmin = new javax.swing.JLabel();
        btnAccountManagement = new javax.swing.JButton();
        btnCustomerManagement = new javax.swing.JButton();
        btnSupplierManagement = new javax.swing.JButton();
        btnDeposit = new javax.swing.JButton();
        btnUserLoginManagement = new javax.swing.JButton();
        btnStatement = new javax.swing.JButton();
        lblHelloAdmin1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Home");
        setBackground(new java.awt.Color(239, 250, 252));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBackground_Admin.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground_Admin.setMinimumSize(new java.awt.Dimension(1060, 650));
        pnlBackground_Admin.setPreferredSize(new java.awt.Dimension(1060, 650));
        pnlBackground_Admin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlWelcome.setBackground(new java.awt.Color(59, 196, 116));
        pnlWelcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/user (Custom).png"))); // NOI18N
        pnlWelcome.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 211, 220, 102));
        lblIcon.getAccessibleContext().setAccessibleDescription("");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME");
        pnlWelcome.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 319, 220, 31));

        btnLogOut.setBackground(new java.awt.Color(239, 250, 252));
        btnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(59, 196, 116));
        btnLogOut.setText("Log out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        pnlWelcome.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 523, 150, 40));

        lblHelloAdmin.setBackground(new java.awt.Color(59, 196, 116));
        lblHelloAdmin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHelloAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblHelloAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHelloAdmin.setText("Admin");
        pnlWelcome.add(lblHelloAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 356, 208, 28));

        pnlBackground_Admin.add(pnlWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 650));

        btnAccountManagement.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAccountManagement.setForeground(new java.awt.Color(59, 196, 116));
        btnAccountManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/admingui_AccountManagement.png"))); // NOI18N
        btnAccountManagement.setText("Account");
        btnAccountManagement.setBorder(null);
        btnAccountManagement.setBorderPainted(false);
        btnAccountManagement.setDefaultCapable(false);
        btnAccountManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAccountManagement.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnAccountManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAccountManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountManagementActionPerformed(evt);
            }
        });
        pnlBackground_Admin.add(btnAccountManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 200, 190));

        btnCustomerManagement.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCustomerManagement.setForeground(new java.awt.Color(59, 196, 116));
        btnCustomerManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/admingui_cusmanagement.png"))); // NOI18N
        btnCustomerManagement.setText("Customer");
        btnCustomerManagement.setBorder(null);
        btnCustomerManagement.setBorderPainted(false);
        btnCustomerManagement.setDefaultCapable(false);
        btnCustomerManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCustomerManagement.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnCustomerManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCustomerManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerManagementActionPerformed(evt);
            }
        });
        pnlBackground_Admin.add(btnCustomerManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 210, 190));

        btnSupplierManagement.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSupplierManagement.setForeground(new java.awt.Color(59, 196, 116));
        btnSupplierManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/admingui_PartnerManagement.png"))); // NOI18N
        btnSupplierManagement.setText("Supplier");
        btnSupplierManagement.setBorder(null);
        btnSupplierManagement.setBorderPainted(false);
        btnSupplierManagement.setDefaultCapable(false);
        btnSupplierManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSupplierManagement.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnSupplierManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSupplierManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierManagementActionPerformed(evt);
            }
        });
        pnlBackground_Admin.add(btnSupplierManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 210, 190));

        btnDeposit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDeposit.setForeground(new java.awt.Color(59, 196, 116));
        btnDeposit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/admingui_Deposit2.0.png"))); // NOI18N
        btnDeposit.setText("Deposit");
        btnDeposit.setBorder(null);
        btnDeposit.setBorderPainted(false);
        btnDeposit.setDefaultCapable(false);
        btnDeposit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeposit.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnDeposit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });
        pnlBackground_Admin.add(btnDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 210, 190));

        btnUserLoginManagement.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUserLoginManagement.setForeground(new java.awt.Color(59, 196, 116));
        btnUserLoginManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/admingui_UserLoginManagement.png"))); // NOI18N
        btnUserLoginManagement.setText("Login Mangement");
        btnUserLoginManagement.setBorder(null);
        btnUserLoginManagement.setBorderPainted(false);
        btnUserLoginManagement.setDefaultCapable(false);
        btnUserLoginManagement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUserLoginManagement.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnUserLoginManagement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUserLoginManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserLoginManagementActionPerformed(evt);
            }
        });
        pnlBackground_Admin.add(btnUserLoginManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, 210, 190));

        btnStatement.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnStatement.setForeground(new java.awt.Color(59, 196, 116));
        btnStatement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/admingui_Statement1.png"))); // NOI18N
        btnStatement.setText("Statement");
        btnStatement.setBorder(null);
        btnStatement.setBorderPainted(false);
        btnStatement.setDefaultCapable(false);
        btnStatement.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStatement.setMargin(new java.awt.Insets(0, 14, 30, 14));
        btnStatement.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatementActionPerformed(evt);
            }
        });
        pnlBackground_Admin.add(btnStatement, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 200, 190));

        lblHelloAdmin1.setBackground(new java.awt.Color(59, 196, 116));
        lblHelloAdmin1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblHelloAdmin1.setForeground(new java.awt.Color(239, 250, 252));
        lblHelloAdmin1.setText("            Admin Portal");
        lblHelloAdmin1.setOpaque(true);
        pnlBackground_Admin.add(lblHelloAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 850, 80));

        getContentPane().add(pnlBackground_Admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        setVisible(false);
        new LogIn_GUI();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnSupplierManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierManagementActionPerformed
        setVisible(false);
        new SupplierManagement_GUI(dtoAdmin);
    }//GEN-LAST:event_btnSupplierManagementActionPerformed

    private void btnAccountManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountManagementActionPerformed
        setVisible(false);
        new AccountManagement_GUI(dtoAdmin);
    }//GEN-LAST:event_btnAccountManagementActionPerformed

    private void btnCustomerManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerManagementActionPerformed
        setVisible(false);
        new CustomerManagement_GUI(dtoAdmin);
    }//GEN-LAST:event_btnCustomerManagementActionPerformed

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        setVisible(false);
        new Deposit_GUI(dtoAdmin);
    }//GEN-LAST:event_btnDepositActionPerformed

    private void btnUserLoginManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserLoginManagementActionPerformed
        setVisible(false);
        new UserLoginManagement_GUI(dtoAdmin);
    }//GEN-LAST:event_btnUserLoginManagementActionPerformed

    private void btnStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatementActionPerformed
        setVisible(false);
        new Statement_GUI(dtoAdmin);
    }//GEN-LAST:event_btnStatementActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccountManagement;
    private javax.swing.JButton btnCustomerManagement;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnStatement;
    private javax.swing.JButton btnSupplierManagement;
    private javax.swing.JButton btnUserLoginManagement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblHelloAdmin;
    private javax.swing.JLabel lblHelloAdmin1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JPanel pnlBackground_Admin;
    private javax.swing.JPanel pnlWelcome;
    // End of variables declaration//GEN-END:variables
}