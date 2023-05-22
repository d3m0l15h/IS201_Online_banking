package DTO;

import java.util.Date;

public class Customer_DTO
{
    private long id;
    private String firstName, lastName , gender, address, phoneNumber, idCard;
    private Date dateOfBirth;
    private long userLoginID;

    public Customer_DTO()
    {

    }
    
    public Customer_DTO(long id)
    {
        this.id = id;
    }
    
    public Customer_DTO(long id, String firstName, String lastName, String gender, Date dateOfBirth, String address, String phoneNumber, String idCard)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
    }
    
    public Customer_DTO(long id, String firstName, String lastName, String gender, Date dateOfBirth, String address, String phoneNumber, String idCard, long userLoginID)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
        this.userLoginID = userLoginID;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdCard() {
        return idCard;
    }
    
    
    public String getGender() {
        return gender;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getIDCard() {
        return idCard;
    }
    
    public long getUserLoginID() {
        return userLoginID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserLoginID(long userLoginID) {
        this.userLoginID = userLoginID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
}
