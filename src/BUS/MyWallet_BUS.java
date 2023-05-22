package BUS;

import DAL.Transaction_DAL;
import DAL.Transaction_Type_DAL;
import DTO.Account_DTO;
import DTO.Transaction_DTO;
import DTO.Transaction_Type_DTO;
import java.util.ArrayList;
import java.util.TreeMap;

public class MyWallet_BUS 
{
    Transaction_DAL dalTransaction = new Transaction_DAL();
    Transaction_Type_DAL dalTransactionType = new Transaction_Type_DAL();
    
    public String getLatestTransactionDate(Account_DTO dtoAccount)
    {
        return dalTransaction.getLatestTransactionDate(dtoAccount);
    }
    
    public ArrayList<Transaction_DTO> getTransactionHistory(Account_DTO dtoAccount)
    {
        return dalTransaction.getTransactionHistory(dtoAccount);
    }
    
    public TreeMap<String, Transaction_Type_DTO> getTransactionTypeList()
    {
        return dalTransactionType.getTransactionTypeList();
    }
}
