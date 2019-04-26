package sample;

import java.util.LinkedList;

public class Products {
    private LinkedList<Product> all = new LinkedList<Product>();

    public void addProduct(Product obj) {
        all.add(obj);
    }

    public void addProducts(LinkedList<Product> objects){
        all.addAll(objects);
    }

    public LinkedList<Product> searchProductSet(String parameter, Product template) {
        LinkedList<Product> rezult = new LinkedList<>();
        if (parameter.equals("productName")) {
            for (Product obj : all) {
                if (template.getProductName().equals(obj.getProductName())) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("producerName")) {
            for (Product obj : all) {
                if (template.getProducerName().equals(obj.getProducerName())) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("producerId")) {
            for (Product obj : all) {
                if (template.getProducerId() == obj.getProducerId()) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("productNumber")) {
            for (Product obj : all) {
                if (template.getProductNumber() == obj.getProductNumber()) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("address")) {
            for (Product obj : all) {
                if (template.getAddress().equals(obj.getAddress())) {
                    rezult.add(obj);
                }
            }
        }
        return rezult;
    }

    public void removeProductSet(String parameter, Product template) {
        LinkedList<Product> queryset = searchProductSet(parameter, template);
        for (Product obj : queryset) all.remove(obj);
    }
}
