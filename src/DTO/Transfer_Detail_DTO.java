package DTO;

public class Transfer_Detail_DTO 
{
    private int id;
    private long senderAccount;
    private long receiverAccount;
    private String receiverBank;
    private long amount;
    private String content;
    private int transactionID;

    public Transfer_Detail_DTO() {
    }

    public Transfer_Detail_DTO(int id, long senderAccount, long receiverAccount, String receiverBank, long amount, String content, int transactionID) {
        this.id = id;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.receiverBank = receiverBank;
        this.amount = amount;
        this.content = content;
        this.transactionID = transactionID;
    }

    public Transfer_Detail_DTO(long senderAccount, long receiverAccount, String receiverBank, long amount, String content) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.receiverBank = receiverBank;
        this.amount = amount;
        this.content = content;
    }
    
    public void setSenderAccount(long senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setReceiverAccount(long receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public int getId() {
        return id;
    }

    public long getSenderAccount() {
        return senderAccount;
    }

    public long getReceiverAccount() {
        return receiverAccount;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public long getAmount() {
        return amount;
    }

    public String getContent() {
        return content;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderAccount(int senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setReceiverAccount(int receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    
    
}
