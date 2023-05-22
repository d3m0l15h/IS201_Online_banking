package DTO;

import java.util.Date;

public class Supplier_DTO 
{
    private int id;
    private String name;
    private String serviceName;
    private Date contractSigningDate;
    private String address;
    private String phoneNumber;

    public Supplier_DTO() {
    }

    public Supplier_DTO(String name) {
        this.name = name;
    }

    
    public Supplier_DTO(int id, String name, String serviceName, Date contractSigningDate, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.serviceName = serviceName;
        this.contractSigningDate = contractSigningDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Date getContractSigningDate() {
        return contractSigningDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setContractSigningDate(Date contractSigningDate) {
        this.contractSigningDate = contractSigningDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
