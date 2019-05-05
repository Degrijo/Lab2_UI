package sample;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    private List<Product> products = new LinkedList<>();
    private Product product;
    private StringBuilder data;
    private boolean attr1 = false;
    private boolean attr2 = false;
    private boolean attr3 = false;
    private boolean attr4 = false;
    private boolean attr5 = false;

    @Override
    public void startElement(String uri, String localName,String qName,
            Attributes attributes) throws SAXException {
        product = new Product();
        if (qName.equalsIgnoreCase("productName")) {
            attr1 = true;
        } else if (qName.equalsIgnoreCase("producerName")) {
            attr2 = true;
        } else if (qName.equalsIgnoreCase("producerId")) {
            attr3 = true;
        } else if (qName.equalsIgnoreCase("productNumber")) {
            attr4 = true;
        } else if (qName.equalsIgnoreCase("address")) {
            attr5 = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName,String qName){
        if (attr1){
            product.setProductName(data.toString());
            attr1 = false;
        } else if (attr2){
            product.setProducerName(data.toString());
            attr2 = false;
        } else if (attr3){
            product.setProducerId(Integer.parseInt(data.toString()));
            attr3 = false;
        } else if (attr4){
            product.setProductNumber(Integer.parseInt(data.toString()));
            attr4 = false;
        } else if (attr5){
            product.setAddress(data.toString());
            attr5 = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        System.out.println(products);
    }

    public List<Product> getProducts() {
        return products;
    }
}
