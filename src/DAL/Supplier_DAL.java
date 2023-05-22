package DAL;

import DTO.Supplier_DTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Supplier_DAL 
{
    
    public boolean insert(Supplier_DTO dtoSupplier)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "INSERT INTO SUPPLIER VALUES (SUPPLIER_ID_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?)";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, dtoSupplier.getName());
            prest.setString(2, dtoSupplier.getServiceName());
            Date sqlContractSigningDate = new java.sql.Date(dtoSupplier.getContractSigningDate().getTime());
            prest.setDate(3, sqlContractSigningDate); // TO_DATE(?, 'MON DD, YYYY');
            prest.setString(4, dtoSupplier.getAddress());
            prest.setString(5, dtoSupplier.getPhoneNumber());
            prest.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e); 
        }
        return false;
    }
    
    public boolean delete(Supplier_DTO dtoSupplier)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String sqlDeleteBill = "DELETE FROM BILL WHERE SUPPLIER_ID = ?";
            PreparedStatement prest = con.prepareStatement(sqlDeleteBill);
            prest.setLong(1, dtoSupplier.getId());
            prest.executeUpdate();
            
            String sqlDeleteSupplier = "DELETE FROM SUPPLIER WHERE SUPPLIER_ID = ?";
            PreparedStatement prest1 = con.prepareStatement(sqlDeleteSupplier);
            prest1.setLong(1, dtoSupplier.getId());
            prest1.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean update(Supplier_DTO dtoSupplier)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE SUPPLIER SET SUPPLIER_NAME = ?, SERVICE_TYPE = ?, CONTRACT_SIGNING_DATE = ?, ADDRESS = ?, PHONE_NUMBER = ? WHERE SUPPLIER_ID = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, dtoSupplier.getName());
            prest.setString(2, dtoSupplier.getServiceName());
            Date sqlDateOfBirth = new java.sql.Date(dtoSupplier.getContractSigningDate().getTime());
            prest.setDate(3, sqlDateOfBirth); // TO_DATE(?, 'MON DD, YYYY')
            prest.setString(4,  dtoSupplier.getAddress());
            prest.setString(5, dtoSupplier.getPhoneNumber());
            prest.setLong(6, dtoSupplier.getId());
            prest.executeUpdate();
            con.close();
            return true;
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public ArrayList<Supplier_DTO> getSupplierList()
    {
        ArrayList<Supplier_DTO> supplierList = new ArrayList<Supplier_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM SUPPLIER";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
            {
                Supplier_DTO dtoSupplier = new Supplier_DTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6));
                supplierList.add(dtoSupplier);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return supplierList; 
    }
    
    public ArrayList<String> getServiceTypeList()
    {
        ArrayList<String> serviceTypeList = new ArrayList<String>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT DISTINCT(SERVICE_TYPE) FROM SUPPLIER";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
                serviceTypeList.add(rs.getString(1));
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return serviceTypeList; 
    }
    
    public ArrayList<Supplier_DTO> getSupplierNameList(String serviceType)
    {
        ArrayList<Supplier_DTO> supplierNameList = new ArrayList<Supplier_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM SUPPLIER WHERE SERVICE_TYPE = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, serviceType);
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                Supplier_DTO dtoSupplier = new Supplier_DTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6));
                supplierNameList.add(dtoSupplier);
            }
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return supplierNameList; 
    }
}
