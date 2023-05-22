package BUS;

import DAL.Employee_DAL;
import DAL.Customer_DAL;
import DAL.User_Login_DAL;
import DTO.Employee_DTO;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;

public class UserLoginManagement_BUS 
{
    Employee_DAL dalAdmin = new Employee_DAL();
    Customer_DAL dalCustomer = new Customer_DAL();
    User_Login_DAL dalUserLogin = new User_Login_DAL();
    
    public  Employee_DTO getAdminInfo(int adminId)
    {
        return dalAdmin.getAdminInfo(adminId);
    }
    public Customer_DTO getInformation(long id)
    {
        return dalCustomer.getInformation(id);
    }
    
    public User_Login_DTO getUserLogin(Employee_DTO dtoAdmin)
    {
        return dalUserLogin.getUserLogin(dtoAdmin);
    }
    
    public User_Login_DTO getUserLogin(Customer_DTO dtoCustomer)
    {
        return dalUserLogin.getUserLogin(dtoCustomer);
    }
    
    public boolean updateInfo(User_Login_DTO dtoUserLogin)
    {
        return dalUserLogin.updateInfo(dtoUserLogin);
    }
}
