package sample;


public class Product {
    private String productName;
    private String producerName;
    private int producerId; //учетный номер пользователя
    private int productNumber;
    private String address;

    public Product(String productName, String producerName, int producerId, int productNumber, String address){
        this.productName = productName;
        this.producerName = producerName;
        this.producerId = producerId;
        this.productNumber = productNumber;
        this.address = address;
    }

    protected void setProductName(String productName) {
        this.productName = productName;
    }

    protected String getProductName() {
        return productName;
    }

    protected void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    protected String getProducerName() {
        return producerName;
    }

    protected void setProducerId(int userNumber) {
        this.producerId = userNumber;
    }

    protected int getProducerId() {
        return producerId;
    }

    protected void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    protected int getProductNumber() {
        return productNumber;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    protected String getAddress() {
        return address;
    }
}
