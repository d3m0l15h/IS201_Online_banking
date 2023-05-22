package GUI;
import BUS.PersonalInformation_BUS;
import DTO.User_Login_DTO;
import DTO.Customer_DTO;
import DTO.Account_DTO;
import GUI.CustomerHome_GUI;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PersonalInformation_GUI extends javax.swing.JFrame 
{
    PersonalInformation_BUS busPersonalInformation = new PersonalInformation_BUS();
    
    Customer_DTO dtoCustomer = null; // Người đang đăng nhập
    Account_DTO dtoAccount = null;
    User_Login_DTO dtoUserLogin = null;

    public PersonalInformation_GUI(Customer_DTO customer) 
    {
        initComponents();
        dtoCustomer = customer;
        
        setVisible(true);
        setSize(1048, 625);
        setResizable(false); // Không cho phóng to
        setTitle("Personal Information"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        displayPersonalInformation();                
        pnlPersonalnformation.setVisible(false);
    }
    
    public void displayPersonalInformation()
    {
        dtoUserLogin = busPersonalInformation.getUserLogin(dtoCustomer);
        txtCustomerID.setText(String.valueOf(dtoCustomer.getId()));
        txtName.setText(dtoCustomer.getFirstName()+" "+dtoCustomer.getLastName());
        txtGender.setText(String.valueOf(dtoCustomer.getGender()));  
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String birthDay = df.format(dtoCustomer.getDateOfBirth());       
        txtDateofBirth.setText(birthDay);
        txtPhoneNo.setText(String.valueOf(dtoCustomer.getPhoneNumber()));
        txtAddress.setText(String.valueOf(dtoCustomer.getAddress()));
        txtIDCard.setText(String.valueOf(dtoCustomer.getIdCard()));
        txtUserName.setText(String.valueOf(dtoUserLogin.getUsername()));
        //txtOldPassword.setText(dtoUserLogIn.getPassword());
        
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
            User_Login_DTO dtoUserLogIn = busPersonalInformation.getUserLogin(dtoCustomer); 
            // Check password
            String password = pass.getText();
            if(password.equals(dtoUserLogIn.getPassword()) == false){
                JOptionPane.showMessageDialog(this, "Password is incorrect", "Incorrect details", JOptionPane.ERROR_MESSAGE);                
            }else
                return true;               
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_SearchAccount = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        btnHome_PersonalInformation = new javax.swing.JButton();
        pnlPersonalnformation = new javax.swing.JPanel();
        txtNewPassword = new javax.swing.JPasswordField();
        lblName2 = new javax.swing.JLabel();
        txtEnteraNewPassword = new javax.swing.JPasswordField();
        lblName3 = new javax.swing.JLabel();
        ckbShowNewPassword = new javax.swing.JCheckBox();
        lblUserLoginInformation1 = new javax.swing.JLabel();
        btnUpdatePassword = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        lblCustomerID = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtPhoneNo = new javax.swing.JTextField();
        txtDateofBirth = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblUsername2 = new javax.swing.JLabel();
        txtIDCard = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        lblLastAccessTime = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnChangePassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Panel_SearchAccount.setBackground(new java.awt.Color(239, 250, 252));
        Panel_SearchAccount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/customergui_personalinformation.png"))); // NOI18N
        Panel_SearchAccount.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 100));

        btnHome_PersonalInformation.setBackground(new java.awt.Color(59, 196, 116));
        btnHome_PersonalInformation.setForeground(new java.awt.Color(255, 255, 255));
        btnHome_PersonalInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Home.png"))); // NOI18N
        btnHome_PersonalInformation.setText("Home");
        btnHome_PersonalInformation.setToolTipText("");
        btnHome_PersonalInformation.setBorder(null);
        btnHome_PersonalInformation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome_PersonalInformation.setIconTextGap(1);
        btnHome_PersonalInformation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome_PersonalInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome_PersonalInformationActionPerformed(evt);
            }
        });
        Panel_SearchAccount.add(btnHome_PersonalInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 76, 64));

        pnlPersonalnformation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlPersonalnformation.add(txtNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 160, 30));

        lblName2.setBackground(new java.awt.Color(32, 172, 216));
        lblName2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblName2.setForeground(new java.awt.Color(59, 196, 116));
        lblName2.setText("New Password:");
        pnlPersonalnformation.add(lblName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        txtEnteraNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pnlPersonalnformation.add(txtEnteraNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 160, 30));

        lblName3.setBackground(new java.awt.Color(32, 172, 216));
        lblName3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        lblName3.setForeground(new java.awt.Color(59, 196, 116));
        lblName3.setText("Confirm new password:");
        pnlPersonalnformation.add(lblName3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        ckbShowNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ckbShowNewPassword.setText("Show Password");
        ckbShowNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbShowNewPasswordActionPerformed(evt);
            }
        });
        pnlPersonalnformation.add(ckbShowNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        lblUserLoginInformation1.setBackground(new java.awt.Color(32, 172, 216));
        lblUserLoginInformation1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUserLoginInformation1.setForeground(new java.awt.Color(59, 196, 116));
        lblUserLoginInformation1.setText("Change Password");
        pnlPersonalnformation.add(lblUserLoginInformation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnUpdatePassword.setBackground(new java.awt.Color(59, 196, 116));
        btnUpdatePassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnUpdatePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdatePassword.setText("Update Password");
        btnUpdatePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePasswordActionPerformed(evt);
            }
        });
        pnlPersonalnformation.add(btnUpdatePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 160, -1));

        Panel_SearchAccount.add(pnlPersonalnformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 490, 200));

        lblTitle.setBackground(new java.awt.Color(59, 196, 116));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("         Personal Information");
        lblTitle.setOpaque(true);
        lblTitle.setPreferredSize(new java.awt.Dimension(34, 50));
        Panel_SearchAccount.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 930, 66));

        txtCustomerID.setEditable(false);
        txtCustomerID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 200, 30));

        lblCustomerID.setBackground(new java.awt.Color(32, 172, 216));
        lblCustomerID.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblCustomerID.setForeground(new java.awt.Color(59, 196, 116));
        lblCustomerID.setText("Customer ID:");
        Panel_SearchAccount.add(lblCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        lblName.setBackground(new java.awt.Color(32, 172, 216));
        lblName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(59, 196, 116));
        lblName.setText("Name:");
        Panel_SearchAccount.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 200, 30));

        txtPhoneNo.setEditable(false);
        txtPhoneNo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 320, 150, 30));

        txtDateofBirth.setEditable(false);
        txtDateofBirth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtDateofBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 200, 30));

        lblPassword.setBackground(new java.awt.Color(32, 172, 216));
        lblPassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(59, 196, 116));
        lblPassword.setText("Date of Birth:");
        Panel_SearchAccount.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, -1, -1));

        lblRole.setBackground(new java.awt.Color(32, 172, 216));
        lblRole.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblRole.setForeground(new java.awt.Color(59, 196, 116));
        lblRole.setText("Address:");
        Panel_SearchAccount.add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        lblUsername1.setBackground(new java.awt.Color(32, 172, 216));
        lblUsername1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(59, 196, 116));
        lblUsername1.setText("Username:");
        Panel_SearchAccount.add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 200, 30));

        lblUsername2.setBackground(new java.awt.Color(32, 172, 216));
        lblUsername2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblUsername2.setForeground(new java.awt.Color(59, 196, 116));
        lblUsername2.setText("ID Card:");
        Panel_SearchAccount.add(lblUsername2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, -1, -1));

        txtIDCard.setEditable(false);
        txtIDCard.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtIDCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 150, 30));

        lblUsername.setBackground(new java.awt.Color(32, 172, 216));
        lblUsername.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(59, 196, 116));
        lblUsername.setText("Gender:");
        Panel_SearchAccount.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        txtGender.setEditable(false);
        txtGender.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 150, 30));

        txtAddress.setEditable(false);
        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Panel_SearchAccount.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 460, 30));

        lblLastAccessTime.setBackground(new java.awt.Color(32, 172, 216));
        lblLastAccessTime.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblLastAccessTime.setForeground(new java.awt.Color(59, 196, 116));
        lblLastAccessTime.setText("Phone No:");
        Panel_SearchAccount.add(lblLastAccessTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/administrator_male_100px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel12)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel12)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Panel_SearchAccount.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, 180, 180));

        btnChangePassword.setBackground(new java.awt.Color(59, 196, 116));
        btnChangePassword.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnChangePassword.setText("Change Password");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });
        Panel_SearchAccount.add(btnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Panel_SearchAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_SearchAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHome_PersonalInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome_PersonalInformationActionPerformed
        this.setVisible(false);
        new CustomerHome_GUI(dtoCustomer);
    }//GEN-LAST:event_btnHome_PersonalInformationActionPerformed

    private void ckbShowNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbShowNewPasswordActionPerformed
        if(ckbShowNewPassword.isSelected())
        {
            // Show newpassword            
            txtNewPassword.setEchoChar((char) 0);
            txtEnteraNewPassword.setEchoChar((char) 0);
        }else
        {
            // Hide newpassword           
            txtNewPassword.setEchoChar('\u25cf');
            txtEnteraNewPassword.setEchoChar('\u25cf');
        }
    }//GEN-LAST:event_ckbShowNewPasswordActionPerformed

    private void btnUpdatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePasswordActionPerformed
        // TODO add your handling code here:
        String password = txtNewPassword.getText();
        String EnterPassword = txtEnteraNewPassword.getText();
        if(password. equals(EnterPassword)){
           if(txtNewPassword.getText().equals("") || txtEnteraNewPassword.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            User_Login_DTO dtoNewUserLogin = new User_Login_DTO(dtoUserLogin.getId(), dtoUserLogin.getUsername(), txtNewPassword.getText(), dtoUserLogin.getRole(), dtoUserLogin.getNumberOfFailedLogin(), dtoUserLogin.getLastAccessTime());
            
            // Update password
            if(busPersonalInformation.updateInfo(dtoNewUserLogin))
            {
                JOptionPane.showMessageDialog(this, "Change Password Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE); 
                
                txtNewPassword.setText("");
                txtEnteraNewPassword.setText("");
                pnlPersonalnformation.setVisible(false);
                btnChangePassword.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(this, "Cannot change password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
           
        }else{
            JOptionPane.showMessageDialog(this, "Password does not match!","Error",JOptionPane.ERROR_MESSAGE);            
        }
    }//GEN-LAST:event_btnUpdatePasswordActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        if(confirmPassword())
        {
            pnlPersonalnformation.setVisible(true);
            btnChangePassword.setVisible(false);
        }
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_SearchAccount;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnHome_PersonalInformation;
    private javax.swing.JButton btnUpdatePassword;
    private javax.swing.JCheckBox ckbShowNewPassword;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCustomerID;
    private javax.swing.JLabel lblLastAccessTime;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblName3;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserLoginInformation1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JLabel lblUsername2;
    private javax.swing.JPanel pnlPersonalnformation;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtDateofBirth;
    private javax.swing.JPasswordField txtEnteraNewPassword;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtIDCard;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtPhoneNo;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
