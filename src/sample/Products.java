package sample;

import java.util.LinkedList;
import java.util.List;

public class Products {
    private List<Product> all = new LinkedList<Product>();
    private int currentPage = 0;
    private int noteNum = 5;

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

    public List<Product> getCurrentPage(){
        if (currentPage < pageNum() - 1){
            return getProducts(currentPage*noteNum, currentPage*noteNum + 5);
        }
        else{
            return getProducts(currentPage*noteNum, all.size());
        }
    }

    public List<Product> getNextPage(){
        if (currentPage + 1 < pageNum()){
            currentPage++;
        }
        return getCurrentPage();
    }

    public List<Product> getPrevPage(){
        if (currentPage - 1 >= 0){
            currentPage--;
        }
        return getCurrentPage();
    }

    public List<Product> getBeginPage(){
        currentPage = 0;
        return getCurrentPage();
    }

    public List<Product> getEndPage(){
        currentPage = pageNum() - 1;
        return getCurrentPage();
    }

    public List<Product> setNoteNum(int index){
        noteNum = index;
        return getCurrentPage();
    }

    private int pageNum(){
        if (all.size() % noteNum == 0){
            return all.size()/noteNum;
        }
        else{
            return all.size()/noteNum + 1;
        }
    }

    private List<Product> getProducts(int start, int finish){
        List<Product> rezult = new LinkedList<Product>();
        if (start >= 0 && finish <= all.size() && start <= finish){
            for (int i = start; i < finish; i++){
                rezult.add(all.get(i));
            }
        }
        return rezult;
    }

    public int size(){
        return all.size();
    }

    public void clear(){
        all.clear();
    }

    public List<Product> searchProductSet(String parameter, Product template) {
        List<Product> rezult = new LinkedList<Product>();
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
        List<Product> queryset = searchProductSet(parameter, template);
        for (Product obj : queryset) all.remove(obj);
    }
}
