package DTO;

public class Account_Type_DTO 
{
    private String id;
    private String name;
    private double interestRate;

    public Account_Type_DTO() 
    {
        
    }

    public Account_Type_DTO(String id, String name, double interestRate) 
    {
        this.id = id;
        this.name = name;
        this.interestRate = interestRate;
    }

    public String getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public double getInterestRate() 
    {
        return interestRate;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setInterestRate(double interestRate) 
    {
        this.interestRate = interestRate;
    }
}
