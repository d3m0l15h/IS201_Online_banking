package BUS;

import DAL.Account_DAL;
import DAL.Customer_DAL;
import DAL.Procedure_DAL;
import DAL.Report.JasperReport_DAL;
import DAL.User_Login_DAL;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.Transfer_Detail_DTO;
import DTO.User_Login_DTO;

public class Transfer_BUS 
{
    Account_DAL dalAccount = new Account_DAL();
    Customer_DAL dalCustomer = new Customer_DAL();
    User_Login_DAL dalUserLogin = new User_Login_DAL();
    Procedure_DAL dalProcedure = new Procedure_DAL();
    JasperReport_DAL dalReport = new JasperReport_DAL();
    
    public Customer_DTO getCustomerInfo(Account_DTO dtoAccount)
    {
        return dalCustomer.getCustomerInfo(dtoAccount);
    }
    
    public User_Login_DTO getUserLogin(Customer_DTO dtoCustomer)
    {
        return dalUserLogin.getUserLogin(dtoCustomer);
    }
    
    public boolean isValidPaymentAccount(Account_DTO dtoAccount)
    {
        return dalAccount.isValidPaymentAccount(dtoAccount);
    }
    
    public int transfer(Transfer_Detail_DTO dtoTransferDetail)
    {
        return dalProcedure.transfer(dtoTransferDetail);
    }
    
    public void showTransferReceipt(long transactionID) 
    {
        dalReport.showTransferReceipt(transactionID);
    }
}
