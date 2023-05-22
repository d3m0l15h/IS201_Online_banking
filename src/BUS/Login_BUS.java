package BUS;

import DAL.Employee_DAL;
import DAL.Customer_DAL;
import DAL.User_Login_DAL;
import DTO.Employee_DTO;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;

public class Login_BUS 
{
    User_Login_DAL dalUserLogin = new User_Login_DAL();
    Employee_DAL dalAdmin = new Employee_DAL();
    Customer_DAL dalCustomer = new Customer_DAL();
    
    
    public boolean checkRole(User_Login_DTO dtoUserLogin)
    {
        return dalUserLogin.checkRole(dtoUserLogin);
    }
    
    public Customer_DTO getCustomerInfo(User_Login_DTO dtoUserLogin)
    {
        return dalCustomer.getCustomerInfo(dtoUserLogin);
    }
    
    public Employee_DTO getAdminInfo(User_Login_DTO dtoUserLogin)
    {
        return dalAdmin.getAdminInfo(dtoUserLogin);
    }
    
    public boolean updateNumberOfFailedLogin(String username)
    {
        return dalUserLogin.updateNumberOfFailedLogin(username);
    }
    
    public boolean updateLastAccessTime(String username)
    {
        return dalUserLogin.updateLastAccessTime(username);
    }
    
    public User_Login_DTO getUserLogin(String username)
    {
        return dalUserLogin.getUserLogin(username);
    }
}
