package sample;

public class Table {
    private String productName;
    private String producerName;
    private int userNumber; //учетный номер пользователя
    private int productNumber;
    private String adress;


    public Table(String productName) {
        this.productName = productName;
    }

    public String getProductName(){
        return this.productName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerName() {
        return this.producerName;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getUserNumber() {
        return this.userNumber;
    }

    public void setProductNumber(int productNumber){
        this.productNumber = productNumber;
    }

    public int getProductNumber(){
        return this.productNumber;
    }

    public void setAdress(String adress){
        this.adress = adress;
    }

    public String getAdress(){
        return this.adress;
    }
}