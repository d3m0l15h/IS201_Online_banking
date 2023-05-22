package DTO;

import java.util.Date;

public class Transaction_DTO 
{
    private int id;
    private String transactionTypeID;
    private Date trasactionDate;
    private long totalTransactionAmount;
    private int accountID;
    
    public Transaction_DTO()
    {
        
    }

    public Transaction_DTO(int id, String transactionTypeID, Date trasactionDate, long totalTransactionAmount, int accountID) 
    {
        this.id = id;
        this.transactionTypeID = transactionTypeID;
        this.trasactionDate = trasactionDate;
        this.totalTransactionAmount = totalTransactionAmount;
        this.accountID = accountID;
    }

    

    public int getId() {
        return id;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getTransactionTypeID() {
        return transactionTypeID;
    }

    public Date getTrasactionDate() {
        return trasactionDate;
    }

    public long getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setTransactionTypeID(String transactionTypeID) {
        this.transactionTypeID = transactionTypeID;
    }

    public void setTrasactionDate(Date trasactionDate) {
        this.trasactionDate = trasactionDate;
    }

    public void setTotalTransactionAmount(long totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }
}
