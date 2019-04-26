package sample;

import java.util.LinkedList;

public class Controller {
    private Products model = new Products();

    protected void addRecord(String productName, String producerName, int producerId, int productNumber, String address){
        model.addProduct(new Product(productName, producerName, producerId, productNumber, address));
    }

    protected void saveFile(){
        // записываем модель в файл
    }

    protected void openFile(){
        // открываем файл, берем оттуда тект, парсим получаем лист продуктов и делаем addRecords
    }

    protected LinkedList<Product> searchRecords(String parameter, Product template){
        return model.searchProductSet(parameter, template);
    }

    protected void removeRecords(String parameter, Product template){
        model.removeProductSet(parameter, template);
    }
}
