package sample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    private List<Product> products = new LinkedList<>();
    private Product product;
    private boolean productName = false;
    private boolean producerName = false;
    private boolean producerId = false;
    private boolean productNumber = false;
    private boolean address = false;

    @Override
    public void startElement(String uri, String localName,String qName,
            Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("product")){
            product = new Product();
        } else if (qName.equalsIgnoreCase("productName")) {
            productName = true;
        } else if (qName.equalsIgnoreCase("producerName")) {
            producerName = true;
        } else if (qName.equalsIgnoreCase("producerId")) {
            producerId = true;
        } else if (qName.equalsIgnoreCase("productNumber")) {
            productNumber = true;
        } else if (qName.equalsIgnoreCase("address")) {
            address = true;
        }
    }

    @Override
    public void endElement(String uri, String localName,String qName){
        if (qName.equalsIgnoreCase("product")){
            products.add(product);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        new String(ch, start, length);
        if (productName){
            product.setProductName(new String(ch, start, length));
            productName = false;
        } else if (producerName){
            product.setProducerName(new String(ch, start, length));
            producerName = false;
        } else if (producerId){
            product.setProducerId(Integer.parseInt(new String(ch, start, length)));
            producerId = false;
        } else if (productNumber){
            product.setProductNumber(Integer.parseInt(new String(ch, start, length)));
            productNumber = false;
        } else if (address){
            product.setAddress(new String(ch, start, length));
            address = false;
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
