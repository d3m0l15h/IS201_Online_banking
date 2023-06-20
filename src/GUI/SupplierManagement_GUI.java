package GUI;

import BUS.SupplierManagment_BUS;
import DTO.Employee_DTO;
import DTO.Supplier_DTO;
import GUI.AdminHome_GUI;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SupplierManagement_GUI extends javax.swing.JFrame 
{
    SupplierManagment_BUS busSupplier = new SupplierManagment_BUS();
    Employee_DTO dtoAdmin = null;
    
    public SupplierManagement_GUI(Employee_DTO admin) 
    {
        initComponents();
        dtoAdmin = admin;
        
        /*Set giao diện*/
        setSize(1064, 650); // Set kích thước giao diện
        setResizable(false); // Không cho phóng to
        setTitle("Supplier Management"); // Set tiêu đề
        setLocation(225,70); // Set vị trí trang
        setVisible(true); // Hiển thị giao diện
        
        
        loadCboServiceType();
        createTable();
    }
    
    public void loadCboServiceType()
    {
        // Get service types information
        ArrayList<String> serviceList = busSupplier.getServiceTypeList();
        
        // Load data into the combobox
        for(String service: serviceList)
            cboServiceType.addItem(service);
    }
    
    DefaultTableModel tblSupplierModel;
    public void createTable()
    {
        tblSupplierModel = new DefaultTableModel();
        // Set title
        String title[] = {"ID", "Supplier Name", "Service Type","Contract Singing Date", "Address", "Phone Number"};
        tblSupplierModel.setColumnIdentifiers(title);
        tblSupplierModel.setRowCount(0);
        
        // Get all supplier information
        ArrayList<Supplier_DTO> list = busSupplier.getSupplierList();

        // Load data into the table
        for(int i = 0; i < list.size(); i++)
        {
            Supplier_DTO dtoSupplier = list.get(i);
            String[] rows = {String.valueOf(dtoSupplier.getId()), dtoSupplier.getName(), dtoSupplier.getServiceName(), dtoSupplier.getContractSigningDate().toString(), dtoSupplier.getAddress(), dtoSupplier.getPhoneNumber() };
            tblSupplierModel.addRow(rows);
        }

        // set model to table
        tblSupplier.setModel(tblSupplierModel);
    }

    public void clearForm()
    {
        btnAdd.setEnabled(true);
        txtSupplierName.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
        datContractSingingDate.setDate(null);
        cboServiceType.setSelectedItem(null);
        createTable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblServiceType = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        lblImage_Supplier = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        lblSupplierManagement = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        lblSupplierName = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        lblTransaction_type = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        cboServiceType = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        lblAddress = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPhoneNumber = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblTransaction_type1 = new javax.swing.JLabel();
        btnExportReport = new javax.swing.JButton();
        datContractSingingDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Supplier Management");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblServiceType.setBackground(new java.awt.Color(239, 250, 252));
        lblServiceType.setForeground(new java.awt.Color(32, 172, 216));
        lblServiceType.setPreferredSize(new java.awt.Dimension(1064, 650));
        lblServiceType.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        lblServiceType.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 270, -1));

        btnAdd.setBackground(new java.awt.Color(59, 196, 116));
        btnAdd.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Supplier_Management_btnADD.png"))); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAdd.setIconTextGap(7);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        lblServiceType.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, 111, 30));

        lblImage_Supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Supplier_Management.png"))); // NOI18N
        lblServiceType.add(lblImage_Supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 100));

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
        lblServiceType.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, 50, 57));

        lblSupplierManagement.setBackground(new java.awt.Color(59, 196, 116));
        lblSupplierManagement.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        lblSupplierManagement.setForeground(new java.awt.Color(255, 255, 255));
        lblSupplierManagement.setText("         Supplier Management");
        lblSupplierManagement.setOpaque(true);
        lblServiceType.add(lblSupplierManagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 30, 931, 58));

        lblSearch.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(59, 196, 116));
        lblSearch.setText("Search:");
        lblServiceType.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, -1, -1));

        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneNumberKeyTyped(evt);
            }
        });
        lblServiceType.add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 310, -1));

        lblSupplierName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblSupplierName.setForeground(new java.awt.Color(59, 196, 116));
        lblSupplierName.setText("Supplier name:");
        lblServiceType.add(lblSupplierName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        txtSupplierName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblServiceType.add(txtSupplierName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 310, -1));

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        lblServiceType.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 890, 210));

        lblTransaction_type.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblTransaction_type.setForeground(new java.awt.Color(59, 196, 116));
        lblTransaction_type.setText("Service Type:  ");
        lblServiceType.add(lblTransaction_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        btnClear.setBackground(new java.awt.Color(59, 196, 116));
        btnClear.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Supplier_Management_btnClear.png"))); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClear.setIconTextGap(1);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        lblServiceType.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 570, 110, 30));

        btnUpdate.setBackground(new java.awt.Color(59, 196, 116));
        btnUpdate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Supplier_Management_btnUpdate.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUpdate.setIconTextGap(1);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        lblServiceType.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 570, 130, 30));

        cboServiceType.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblServiceType.add(cboServiceType, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 240, 30));

        btnDelete.setBackground(new java.awt.Color(59, 196, 116));
        btnDelete.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Supplier_Management_btnDelete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDelete.setIconTextGap(1);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        lblServiceType.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 570, 111, 30));

        lblAddress.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(59, 196, 116));
        lblAddress.setText("Address:");
        lblServiceType.add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, -1));
        lblServiceType.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 278, 890, 20));

        lblPhoneNumber.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblPhoneNumber.setForeground(new java.awt.Color(59, 196, 116));
        lblPhoneNumber.setText("Phone Number:");
        lblServiceType.add(lblPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblServiceType.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 440, -1));

        lblTransaction_type1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblTransaction_type1.setForeground(new java.awt.Color(59, 196, 116));
        lblTransaction_type1.setText("Contract Signing Date:");
        lblServiceType.add(lblTransaction_type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, -1, -1));

        btnExportReport.setBackground(new java.awt.Color(59, 196, 116));
        btnExportReport.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        btnExportReport.setForeground(new java.awt.Color(255, 255, 255));
        btnExportReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Export.png"))); // NOI18N
        btnExportReport.setText("EXPORT SUPPLIER REPORT");
        btnExportReport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExportReport.setIconTextGap(1);
        btnExportReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportReportActionPerformed(evt);
            }
        });
        lblServiceType.add(btnExportReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 570, 260, 30));
        lblServiceType.add(datContractSingingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 190, 30));

        getContentPane().add(lblServiceType, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1064, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Check if the user input is enough or not
        if(txtPhoneNumber.getText().equals("")|| txtSupplierName.getText().equals("") ||  txtAddress.getText().equals("") || txtPhoneNumber.getText().equals("") || cboServiceType.getSelectedItem() == null)
        {
               JOptionPane.showMessageDialog(this, "Required fields are empty", "Please fill all required fields...!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Supplier_DTO dtoSupplier = new Supplier_DTO(0, txtSupplierName.getText(), cboServiceType.getSelectedItem().toString(), datContractSingingDate.getDate(), txtAddress.getText(), txtPhoneNumber.getText());
            // Add new supplier
            if(busSupplier.insert(dtoSupplier))
            {
                JOptionPane.showMessageDialog(this, "Supplier added susccessfully...!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            }
            else
                JOptionPane.showMessageDialog(this, "Cannot add supplier!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int index = tblSupplier.getSelectedRow();
        // Check the information on the textfield
        if(index == -1 || txtPhoneNumber.getText().equals("")|| txtSupplierName.getText().equals("") ||  txtAddress.getText().equals("") || txtPhoneNumber.getText().equals("") || cboServiceType.getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a supplier before updating", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Show confirmation dialog
            int ret = JOptionPane.showConfirmDialog(null, "Are you sure to update this supplier information?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(ret == JOptionPane.YES_OPTION)
            {
                int id = Integer.parseInt(tblSupplier.getValueAt(index, 0).toString());
                Supplier_DTO dtoSupplier = new Supplier_DTO(id, txtSupplierName.getText(), cboServiceType.getSelectedItem().toString(), datContractSingingDate.getDate(), txtAddress.getText(), txtPhoneNumber.getText());
                // Update supplier information
                if(busSupplier.update(dtoSupplier))
                {
                    JOptionPane.showConfirmDialog(null, "Supplier updated susccessfully...!", "Success", JOptionPane.CLOSED_OPTION);
                    clearForm();
                }
                else
                    JOptionPane.showMessageDialog(this, "Cannot update supplier!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int index = tblSupplier.getSelectedRow();
        if(index == -1) // Kiểm tra người dùng đã chọn supplier chưa
        {
            JOptionPane.showMessageDialog(this, "Please select a supplier before deleting", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Show confirmation dialog
            int ret = JOptionPane.showConfirmDialog(null, "Are you sure to delete this supplier and all bills associated with this supplier?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(ret == JOptionPane.YES_OPTION) 
            {
                int id = Integer.parseInt(tblSupplier.getValueAt(index, 0).toString());
                Supplier_DTO dtoSupplier = new Supplier_DTO(id, txtSupplierName.getText(), cboServiceType.getSelectedItem().toString(), datContractSingingDate.getDate(), txtAddress.getText(), txtPhoneNumber.getText());
                // Delete supplier information
                if(busSupplier.delete(dtoSupplier))
                {
                    JOptionPane.showConfirmDialog(null, "Supplier deleted susccessfully...!", "Success", JOptionPane.CLOSED_OPTION);
                    clearForm();
                }
                else
                    JOptionPane.showMessageDialog(this, "Cannot delete supplier!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setVisible(false);
        new AdminHome_GUI(dtoAdmin);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // Tạo biến SearchTable có kdl là DefaultTableModel và gán model trên bảng tblSupplier cho biến đó
        DefaultTableModel SearchTable = (DefaultTableModel) tblSupplier.getModel();
        
        // Khởi tạo biến sorter có kdl TableRowSorter gán dữ liệu của SearchTable cho nó
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(SearchTable);
        
        // Set sorter đó cho bảng tblSupplier
        tblSupplier.setRowSorter(sorter);
        
        // sử dụng đối tượng RowFilter để lọc dựa trên giá trị trong textfield
        String search = txtSearch.getText();
        sorter.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtPhoneNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumberKeyTyped
        // Đoạn code này không cho người dùng nhập chữ vào ô txtPhoneNumber
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_txtPhoneNumberKeyTyped

    private void btnExportReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportReportActionPerformed
        busSupplier.showSupplierList(dtoAdmin);
    }//GEN-LAST:event_btnExportReportActionPerformed

    private void tblSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseClicked
        int row = tblSupplier.getSelectedRow();
        
        // Display the information on the textfield
        if(row  < tblSupplierModel.getRowCount() && row  >= 0)
        {
            btnAdd.setEnabled(false);
            txtSupplierName.setText(tblSupplier.getValueAt(row, 1).toString());
            cboServiceType.setSelectedItem(tblSupplier.getValueAt(row, 2).toString());
            String contractSingingDateString = tblSupplier.getValueAt(row, 3).toString();
            Date contractSingingDate = null;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
            try { 
                contractSingingDate = df.parse(contractSingingDateString);
            } catch (ParseException ex) {
                Logger.getLogger(SupplierManagement_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            datContractSingingDate.setDate(contractSingingDate);
            txtAddress.setText(tblSupplier.getValueAt(row, 4).toString());
            txtPhoneNumber.setText(tblSupplier.getValueAt(row, 5).toString());
        }
    }//GEN-LAST:event_tblSupplierMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExportReport;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboServiceType;
    private com.toedter.calendar.JDateChooser datContractSingingDate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblImage_Supplier;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel lblServiceType;
    private javax.swing.JLabel lblSupplierManagement;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblTransaction_type;
    private javax.swing.JLabel lblTransaction_type1;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSupplierName;
    // End of variables declaration//GEN-END:variables
}
