package sample;

import java.util.LinkedList;
import java.util.List;

public class Products {
    private List<Product> all = new LinkedList<Product>();

    public void addProduct(Product obj) {
        all.add(obj);
    }

    public void addProducts(List<Product> obj){
        all.addAll(obj);
    }

    public List<Product> getAll(){
        return all;
    }

    public Product get(int index){
        return all.get(index);
    }


    public int size(){
        return all.size();
    }

    public void clear(){
        all.clear();
    }

    public List<Product> searchProductSet(String parameter, Product template) {
        List<Product> rezult = new LinkedList<Product>();
        if (parameter.equals("by name of the product")) {
            for (Product obj : all) {
                if (template.getProductName().equals(obj.getProductName())) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("by name of the producer")) {
            for (Product obj : all) {
                if (template.getProducerName().equals(obj.getProducerName())) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("by the producer id")) {
            for (Product obj : all) {
                if (template.getProducerId() == obj.getProducerId()) {
                    rezult.add(obj);
                }
            }
        }
        if (parameter.equals("by the number of the product")) {
            for (Product obj : all) {
                if (template.getProductNumber() == obj.getProductNumber()) {
                    rezult.add(obj);
                }
            }
        }
        return rezult;
    }

    public int removeProductSet(String parameter, Product template) {
        List<Product> queryset = searchProductSet(parameter, template);
        for (Product obj : queryset){
            all.remove(obj);
        }
        return queryset.size();
    }
}
