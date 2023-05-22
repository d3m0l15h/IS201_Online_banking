package BUS;

import DAL.Customer_DAL;
import DAL.Procedure_DAL;
import DAL.Report.JasperReport_DAL;

import DTO.Customer_DTO;
import DTO.Employee_DTO;
import java.util.ArrayList;

public class CustomerManagement_BUS 
{
    Customer_DAL dalCustomer = new Customer_DAL();
    JasperReport_DAL dalReport = new JasperReport_DAL();
    Procedure_DAL dalProcedure = new Procedure_DAL ();
    
    public boolean insert(Customer_DTO dtoCustomer)
    {
        return dalCustomer.insert(dtoCustomer);
    }
    
    public boolean delete(Customer_DTO dtoCustomer)
    {
        return dalProcedure.delete(dtoCustomer);
    }
    
    public boolean update(Customer_DTO dtoCustomer)
    {
        return dalCustomer.update(dtoCustomer);
    }
    
    public Customer_DTO getInformation(long id)
    {
        return dalCustomer.getInformation(id);
    }
    
    public ArrayList<Customer_DTO> getCustomersList()
    {
        return dalCustomer.getCustomersList();
    }
    
    public void showCustomerList(Employee_DTO dtoEmployee) 
    {
        dalReport.showCustomerList(dtoEmployee);
    }
   
}