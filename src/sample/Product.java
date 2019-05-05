package sample;


public class Product {
    private String productName;
    private String producerName;
    private int producerId; //учетный номер пользователя
    private int productNumber;
    private String address;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerId(int userNumber) {
        this.producerId = userNumber;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
