package BUS;

import DAL.Account_DAL;
import DTO.Account_DTO;
import DTO.Customer_DTO;

public class CustomerHome_BUS 
{
    Account_DAL dalAccount = new Account_DAL();
    
    public Account_DTO getPaymentAccount(Customer_DTO dtoCustomer)
    {
        return dalAccount.getPaymentAccount(dtoCustomer);
    }

}
