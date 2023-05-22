package DTO;

public class Transaction_Type_DTO 
{
    private String id;
    private String name;
    private int fee;

    public Transaction_Type_DTO() {
    }

    public Transaction_Type_DTO(String id, String name, int fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFee() {
        return fee;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    
}
