package DTO;

import java.util.Date;

public class Account_DTO 
{
    private long id;
    private String accountTypeID;
    private long currentBalance;
    private Date openDay;
    private Date maturityDate;
    private long anticipatedInterest;
    private String Status;
    private long CustomerID;

    public Account_DTO() 
    {
        
    }

    public Account_DTO(long id) 
    {
        this.id = id;
    }

    // Contructor cho một tài khoản tiết kiệm mới
    public Account_DTO(String accountTypeID, long currentBalance, Date maturityDate, long anticipatedInterest, long CustomerID) {
        this.accountTypeID = accountTypeID;
        this.currentBalance = currentBalance;
        this.maturityDate = maturityDate;
        this.anticipatedInterest = anticipatedInterest;
        this.CustomerID = CustomerID;
    }
    
    // Contructor khởi tạo 1 tài khoản thanh toán
    public Account_DTO(long id, String accountTypeID, long currentBalance, Date openDay, String Status, long CustomerID) 
    {
        this.id = id;
        this.accountTypeID = accountTypeID;
        this.currentBalance = currentBalance;
        this.openDay = openDay;
        this.Status = Status;
        this.CustomerID = CustomerID;
    }
    

    public Account_DTO(long id, long currentBalance, Date openDay, String accountTypeID, String Status, long CustomerID) 
    {
        this.id = id;
        this.currentBalance = currentBalance;
        this.openDay = openDay;
        this.accountTypeID = accountTypeID;
        this.Status = Status;
        this.CustomerID = CustomerID;
    }
    
    // Contructor đầy đủ
    public Account_DTO(long id, String accountTypeID, long currentBalance, Date openDay, Date maturityDate, long anticipatedInterest, String Status, long CustomerID) {
        this.id = id;
        this.accountTypeID = accountTypeID;
        this.currentBalance = currentBalance;
        this.openDay = openDay;
        this.maturityDate = maturityDate;
        this.anticipatedInterest = anticipatedInterest;
        this.Status = Status;
        this.CustomerID = CustomerID;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public long getAnticipatedInterest() {
        return anticipatedInterest;
    }
    
    

    public long getId() 
    {
        return id;
    }

    public long getCurrentBalance() 
    {
        return currentBalance;
    }

    public Date getOpenDay() 
    {
        return openDay;
    }

    public String getAccountTypeID() 
    {
        return accountTypeID;
    }

    public String getStatus() 
    {
        return Status;
    }

    public long getCustomerID() {
        return CustomerID;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public void setCurrentBalance(long currentBalance) 
    {
        this.currentBalance = currentBalance;
    }

    public void setOpenDay(Date openDay) 
    {
        this.openDay = openDay;
    }

    public void setAccountTypeID(String accountTypeID) 
    {
        this.accountTypeID = accountTypeID;
    }


    public void setStatus(String Status) 
    {
        this.Status = Status;
    }

    public void setCustomerID(long CustomerID) 
    {
        this.CustomerID = CustomerID;
    }
    
    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public void setAnticipatedInterest(long anticipatedInterest) {
        this.anticipatedInterest = anticipatedInterest;
    }
}
