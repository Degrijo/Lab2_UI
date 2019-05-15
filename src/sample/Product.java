package sample;


public class Product {
    private String productName;
    private String producerName;
    private int producerId; //учетный номер пользователя
    private int productNumber;
    private String address;
    private String town;
    private String street;
    private Integer house;

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
        address = " t. " + town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        address += " str. " + street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
        address += " h. " + house;
    }

    public String getAddress() {
        return address;
    }
}
