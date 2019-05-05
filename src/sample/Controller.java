package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Controller {
    private Products model = new Products();

    public ObservableList<Product> getModel(){
        return FXCollections.observableList(model.getAll());
    }

    public void addRecord(String productName, String producerName, String producerId, String productNumber, String address){
        Product temp = new Product();
        if (!productName.equals("")) {
            temp.setProductName(productName);
        }
        if (!producerName.equals("")) {
            temp.setProducerName(producerName);
        }
        if (!producerId.equals("")) {
            temp.setProducerId(Integer.parseInt(producerId));
        }
        if (!productNumber.equals("")) {
            temp.setProductNumber(Integer.parseInt(productNumber));
        }
        if (!address.equals("")) {
            temp.setAddress(address);
        }
        model.addProduct(temp);
    }

    public ObservableList<Product> getCurrentPage(){
        return FXCollections.observableList(model.getCurrentPage());
    }

    public ObservableList<Product> getBeginPage(){
        return FXCollections.observableList(model.getBeginPage());
    }

    public ObservableList<Product> getPrevPage(){
        return FXCollections.observableList(model.getPrevPage());
    }

    public ObservableList<Product> getNextPage(){
        return FXCollections.observableList(model.getNextPage());
    }

    public ObservableList<Product> getEndPage(){
        return FXCollections.observableList(model.getEndPage());
    }

    public ObservableList<Product> setNoteNum(int index){
        return FXCollections.observableList(model.setNoteNum(index));
    }

    public void clearModel(){ model.clear(); }

    public void saveFile(){
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("products");
            doc.appendChild(rootElement);
            for (int i = 0; i < model.size(); i++){
                Element element = doc.createElement("product");
                doc.appendChild(element);
                Attr attr1 = doc.createAttribute("productName");
                Attr attr2 = doc.createAttribute("producerName");
                Attr attr3 = doc.createAttribute("producerId");
                Attr attr4 = doc.createAttribute("productNumber");
                Attr attr5 = doc.createAttribute("address");
                attr1.setValue(model.get(i).getProductName());
                attr2.setValue(model.get(i).getProducerName());
                attr3.setValue(Integer.toString(model.get(i).getProducerId()));
                attr4.setValue(Integer.toString(model.get(i).getProductNumber()));
                attr5.setValue(model.get(i).getAddress());
                element.setAttributeNode(attr1);
                element.setAttributeNode(attr2);
                element.setAttributeNode(attr3);
                element.setAttributeNode(attr4);
                element.setAttributeNode(attr5);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\degri\\IdeaProjects\\Ppvis_2\\loads\\test.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFile(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse("c:\\file.xml", handler);
            List<Product> objects = handler.getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // очистить текущую модель и загрузить полученный лист
        }
    }

    public ObservableList<Product> searchRecords(String parameter, Product template){
        return FXCollections.observableList(model.searchProductSet(parameter, template));
    }

    public void removeRecords(String parameter, Product template){
        model.removeProductSet(parameter, template);
    }
}
