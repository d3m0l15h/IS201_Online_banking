package GUI;

import BUS.UserLoginManagement_BUS;
import DTO.Employee_DTO;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class UserLoginManagement_GUI extends javax.swing.JFrame 
{
    UserLoginManagement_BUS busUserLoginManagement = new UserLoginManagement_BUS();
    Employee_DTO dtoAdmin = null;
    User_Login_DTO dtoUserLogin = null;
    public UserLoginManagement_GUI(Employee_DTO admin) 
    {
        initComponents();
        dtoAdmin = admin;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("User Login Management"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        btnUpdate.setVisible(false);
        btnEdit.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_SearchAccount = new javax.swing.JPanel();
        btnHome_SearchAccount = new javax.swing.JButton();
        lblIcon = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlUserLoginInformation = new javax.swing.JPanel();
        lblUserLoginInformation = new javax.swing.JLabel();
        lblLastAccessTime = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblNumberOfFailedLogin = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        lblUserLoginID = new javax.swing.JLabel();
        txtLastAccessTime = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        txtNumberFailedLogin = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblRole = new javax.swing.JLabel();
        txtUserLoginId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lbSearch2 = new javax.swing.JLabel();
        lbCustomerAdminID = new javax.swing.JLabel();
        cbbRole = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Login Management");

        Panel_SearchAccount.setBackground(new java.awt.Color(239, 250, 252));
        Panel_SearchAccount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome_SearchAccount.setBackground(new java.awt.Color(59, 196, 116));
        btnHome_SearchAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnHome_SearchAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Home.png"))); // NOI18N
        btnHome_SearchAccount.setText("Home");
        btnHome_SearchAccount.setToolTipText("");
        btnHome_SearchAccount.setBorder(null);
        btnHome_SearchAccount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome_SearchAccount.setIconTextGap(1);
        btnHome_SearchAccount.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome_SearchAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome_SearchAccountActionPerformed(evt);
            }
        });
        Panel_SearchAccount.add(btnHome_SearchAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 76, 64));

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/UserLoginManagment.png"))); // NOI18N
        Panel_SearchAccount.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 110, 120));

        lblTitle.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("        User Login Managment");
        lblTitle.setOpaque(true);
        lblTitle.setPreferredSize(new java.awt.Dimension(34, 50));
        Panel_SearchAccount.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 930, 66));
        Panel_SearchAccount.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 750, 30));

        pnlUserLoginInformation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUserLoginInformation.setBackground(new java.awt.Color(32, 172, 216));
        lblUserLoginInformation.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblUserLoginInformation.setForeground(new java.awt.Color(59, 196, 116));
        lblUserLoginInformation.setText("User Login Information");
        pnlUserLoginInformation.add(lblUserLoginInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        lblLastAccessTime.setBackground(new java.awt.Color(32, 172, 216));
        lblLastAccessTime.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblLastAccessTime.setForeground(new java.awt.Color(59, 196, 116));
        lblLastAccessTime.setText("Last access time:");
        pnlUserLoginInformation.add(lblLastAccessTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        lblPassword.setBackground(new java.awt.Color(32, 172, 216));
        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(59, 196, 116));
        lblPassword.setText("Password:");
        pnlUserLoginInformation.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        lblUsername.setBackground(new java.awt.Color(32, 172, 216));
        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(59, 196, 116));
        lblUsername.setText("Username:");
        pnlUserLoginInformation.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        btnEdit.setBackground(new java.awt.Color(59, 196, 116));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        pnlUserLoginInformation.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 70, 30));

        lblName.setBackground(new java.awt.Color(32, 172, 216));
        lblName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblName.setForeground(new java.awt.Color(59, 196, 116));
        lblName.setText("Name:");
        pnlUserLoginInformation.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        lblNumberOfFailedLogin.setBackground(new java.awt.Color(32, 172, 216));
        lblNumberOfFailedLogin.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblNumberOfFailedLogin.setForeground(new java.awt.Color(59, 196, 116));
        lblNumberOfFailedLogin.setText("Number of failed login:");
        pnlUserLoginInformation.add(lblNumberOfFailedLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(59, 196, 116));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/update_25px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnlUserLoginInformation.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 120, 40));

        lblUserLoginID.setBackground(new java.awt.Color(32, 172, 216));
        lblUserLoginID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblUserLoginID.setForeground(new java.awt.Color(59, 196, 116));
        lblUserLoginID.setText("User Login ID:");
        pnlUserLoginInformation.add(lblUserLoginID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        txtLastAccessTime.setEditable(false);
        txtLastAccessTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlUserLoginInformation.add(txtLastAccessTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 160, -1));

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlUserLoginInformation.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 560, -1));

        txtUsername.setEditable(false);
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlUserLoginInformation.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 180, -1));

        txtRole.setEditable(false);
        txtRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlUserLoginInformation.add(txtRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 160, -1));

        txtNumberFailedLogin.setEditable(false);
        txtNumberFailedLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumberFailedLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumberFailedLoginKeyTyped(evt);
            }
        });
        pnlUserLoginInformation.add(txtNumberFailedLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 160, -1));

        txtPassword.setEditable(false);
        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pnlUserLoginInformation.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 180, 30));

        lblRole.setBackground(new java.awt.Color(32, 172, 216));
        lblRole.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblRole.setForeground(new java.awt.Color(59, 196, 116));
        lblRole.setText("Role:");
        pnlUserLoginInformation.add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        txtUserLoginId.setEditable(false);
        txtUserLoginId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlUserLoginInformation.add(txtUserLoginId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 180, -1));

        Panel_SearchAccount.add(pnlUserLoginInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 750, 320));

        btnSearch.setBackground(new java.awt.Color(59, 196, 116));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        Panel_SearchAccount.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 120, 30));

        lbSearch2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbSearch2.setForeground(new java.awt.Color(59, 196, 116));
        lbSearch2.setText("Role:");
        Panel_SearchAccount.add(lbSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        lbCustomerAdminID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbCustomerAdminID.setForeground(new java.awt.Color(59, 196, 116));
        lbCustomerAdminID.setText("Customer/ Admin ID:");
        Panel_SearchAccount.add(lbCustomerAdminID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        cbbRole.setEditable(true);
        cbbRole.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Customer" }));
        cbbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRoleActionPerformed(evt);
            }
        });
        Panel_SearchAccount.add(cbbRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 330, -1));

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdMouseClicked(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });
        Panel_SearchAccount.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 330, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Panel_SearchAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Panel_SearchAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHome_SearchAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome_SearchAccountActionPerformed
        setVisible(false);
        new AdminHome_GUI(dtoAdmin);
    }//GEN-LAST:event_btnHome_SearchAccountActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // Check if the customer input is enough or not
        if(cbbRole.getSelectedItem() == null || txtId.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // check Role
            if(cbbRole.getSelectedItem().equals("Admin")) 
            {
                int adminId = Integer.parseInt(txtId.getText());
                // Get admin information
                Employee_DTO dtoAdmin = busUserLoginManagement.getAdminInfo(adminId);
                if(dtoAdmin == null) // Kiểm tra thông tin Admin
                {
                    JOptionPane.showMessageDialog(this, "Admin ID is invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    // Get user login information
                    dtoUserLogin = busUserLoginManagement.getUserLogin(dtoAdmin);
                    
                    // Display information on the form
                    txtName.setText(dtoAdmin.getFirstName() + " " + dtoAdmin.getLastName());
                    txtUserLoginId.setText(String.valueOf(dtoUserLogin.getId()));
                    txtRole.setText(dtoUserLogin.getRole());
                    txtUsername.setText(dtoUserLogin.getUsername());
                    txtPassword.setText(dtoUserLogin.getPassword());
                    if(dtoUserLogin.getLastAccessTime() == null) // Nếu chưa đăng nhập lần nào
                    {
                       txtLastAccessTime.setText("never logged in"); 
                    }
                    else
                    {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String lastAccessTime = df.format(dtoUserLogin.getLastAccessTime());
                        txtLastAccessTime.setText(lastAccessTime);;
                    }
                    txtNumberFailedLogin.setText(String.valueOf(dtoUserLogin.getNumberOfFailedLogin()));
                    
                    //Show Edit button
                    btnEdit.setVisible(true); 
                }
            }
            else // Role as customer
            {
                long customerId = Long.parseLong(txtId.getText());
                
                // Get customer information
                Customer_DTO dtoCustomer = busUserLoginManagement.getInformation(customerId);
                if(dtoCustomer == null) // Kiểm tra thông tin Customer
                {
                    JOptionPane.showMessageDialog(this, "Customer ID is invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    // Get user login information
                    dtoUserLogin = busUserLoginManagement.getUserLogin(dtoCustomer);
                    
                    // Display information on the form
                    txtName.setText(dtoCustomer.getFirstName() + " " + dtoCustomer.getLastName());
                    txtUserLoginId.setText(String.valueOf(dtoUserLogin.getId()));
                    txtRole.setText(dtoUserLogin.getRole());
                    txtUsername.setText(dtoUserLogin.getUsername());
                    txtPassword.setText(dtoUserLogin.getPassword());
                    if(dtoUserLogin.getLastAccessTime() == null)
                    {
                        txtLastAccessTime.setText("never logged in");
                    }
                    else
                    {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String lastAccessTime = df.format(dtoUserLogin.getLastAccessTime());
                        txtLastAccessTime.setText(lastAccessTime);
                    }
                    txtNumberFailedLogin.setText(String.valueOf(dtoUserLogin.getNumberOfFailedLogin()));
                    
                    //Show Edit button
                    btnEdit.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Display editable information
        txtPassword.setEditable(true);
        txtPassword.setEchoChar((char)0);
        txtNumberFailedLogin.setEditable(true);
        
        // Show Update button 
        btnUpdate.setVisible(true);
        
        // Hide Edit button
        btnEdit.setVisible(false);
    }//GEN-LAST:event_btnEditActionPerformed

    public void clearForm()
    {
        txtUserLoginId.setText("");
        txtName.setText("");
        txtRole.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtLastAccessTime.setText("");
        txtNumberFailedLogin.setText("");
        
        txtPassword.setEditable(false);
        txtPassword.setEchoChar('\u25cf');
        txtNumberFailedLogin.setEditable(false);
        btnEdit.setVisible(false);
        btnUpdate.setVisible(false);
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // Check if the information is enough or not
        if(txtNumberFailedLogin.getText().equals("") || txtPassword.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            User_Login_DTO dtoNewUserLogin = new User_Login_DTO(dtoUserLogin.getId(), dtoUserLogin.getUsername(), txtPassword.getText(), dtoUserLogin.getRole(), Integer.parseInt(txtNumberFailedLogin.getText()), dtoUserLogin.getLastAccessTime());
            
            // Update user login information
            if(busUserLoginManagement.updateInfo(dtoNewUserLogin))
            {
                JOptionPane.showMessageDialog(this, "User login information updated successfully...!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                
                // Hide update button
                btnUpdate.setVisible(false);
            }
            else
                JOptionPane.showMessageDialog(this, "Cannot update user login information!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdMouseClicked
        clearForm();
    }//GEN-LAST:event_txtIdMouseClicked

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtIdKeyTyped

    private void cbbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoleActionPerformed
        txtId.setText("");
        clearForm();
    }//GEN-LAST:event_cbbRoleActionPerformed

    private void txtNumberFailedLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumberFailedLoginKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtNumberFailedLoginKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_SearchAccount;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHome_SearchAccount;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbRole;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCustomerAdminID;
    private javax.swing.JLabel lbSearch2;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblLastAccessTime;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumberOfFailedLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserLoginID;
    private javax.swing.JLabel lblUserLoginInformation;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlUserLoginInformation;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastAccessTime;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumberFailedLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtUserLoginId;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
