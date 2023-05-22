package BUS;
import DAL.User_Login_DAL;
import DAL.Customer_DAL;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;

public class PersonalInformation_BUS 
{
    User_Login_DAL dalUserLogin = new User_Login_DAL();
    Customer_DAL dalCustomer = new Customer_DAL();
    
    public User_Login_DTO getUserLogin(Customer_DTO dtoCustomer)
    {
        return dalUserLogin.getUserLogin(dtoCustomer);
    }
     
    public boolean updateInfo(User_Login_DTO dtoUserLogin)
    {
        return dalUserLogin.updateInfo(dtoUserLogin);
    }
}
