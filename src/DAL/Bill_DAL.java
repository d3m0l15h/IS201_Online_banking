package DAL;

import DTO.Customer_DTO;
import DTO.Bill_DTO;
import DTO.Supplier_DTO;

import javax.swing.JOptionPane;
import java.sql.*;

public class Bill_DAL 
{
    public Bill_DTO getBillInformation(Customer_DTO dtoCustomer, Supplier_DTO dtoSupplier, int month, int year)
    {
        Bill_DTO dotBill = null;
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT B.BILL_ID, B.SUPPLIER_ID, CUSTOMER_ID, BILL_AMOUNT, INVOICE_DATE, PAYMENT_DATE, STATUS\n"
                    + "FROM BILL B JOIN SUPPLIER S ON B.SUPPLIER_ID = S.SUPPLIER_ID\n"
                    + "WHERE CUSTOMER_ID = ? AND SUPPLIER_NAME = ? "
                    + "AND EXTRACT(month FROM INVOICE_DATE) = ? AND EXTRACT(YEAR FROM INVOICE_DATE) = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoCustomer.getId());
            prest.setString(2, dtoSupplier.getName());
            prest.setInt(3, month);
            prest.setInt(4, year);
            prest.executeUpdate();
            ResultSet rs = prest.executeQuery();
            
            while(rs.next())
            {
                dotBill  = new Bill_DTO(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getDate(5), rs.getDate(6), rs.getString(7));
            }
                
            con.close();
            rs.close();
            prest.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return dotBill;
    }

}
