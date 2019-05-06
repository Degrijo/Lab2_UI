package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Controller {
    private Products model = new Products();
    private Products dialogModel = new Products();

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

    public ObservableList<Product> getCurrentDialofPage(){
        return FXCollections.observableList(dialogModel.getCurrentPage());
    }

    public ObservableList<Product> getBeginDialogPage(){
        return FXCollections.observableList(dialogModel.getBeginPage());
    }

    public ObservableList<Product> getPrevDialogPage(){
        return FXCollections.observableList(dialogModel.getPrevPage());
    }

    public ObservableList<Product> getNextDialogPage(){
        return FXCollections.observableList(dialogModel.getNextPage());
    }

    public ObservableList<Product> getEndDialogPage(){
        return FXCollections.observableList(dialogModel.getEndPage());
    }

    public ObservableList<Product> setNoteNumDialog(int index){
        return FXCollections.observableList(dialogModel.setNoteNum(index));
    }

    public void clearDialogModel(){ dialogModel.clear(); }

    public void clearModel(){ model.clear(); }

    public int getNodeNumber(){
        return model.size();
    }

    public int getNodeNumberDialog(){
        return dialogModel.size();
    }

    public void saveFile(Stage stage){
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("products");
            doc.appendChild(root);
            for (int i = 0; i < model.size(); i++){
                Element element = doc.createElement("product");
                Element productName = doc.createElement("productName");
                productName.appendChild(doc.createTextNode(model.get(i).getProductName()));
                element.appendChild(productName);
                Element producerName = doc.createElement("producerName");
                producerName.appendChild(doc.createTextNode(model.get(i).getProducerName()));
                element.appendChild(producerName);
                Element producerId = doc.createElement("producerId");
                producerId.appendChild(doc.createTextNode(Integer.toString(model.get(i).getProducerId())));
                element.appendChild(producerId);
                Element productNumber = doc.createElement("productNumber");
                productNumber.appendChild(doc.createTextNode(Integer.toString(model.get(i).getProductNumber())));
                element.appendChild(productNumber);
                Element address = doc.createElement("address");
                address.appendChild(doc.createTextNode(model.get(i).getAddress()));
                element.appendChild(address);
                root.appendChild(element);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select directory for save");
            fileChooser.setInitialFileName("products");
            fileChooser.setInitialDirectory(new File("C:\\Users\\degri\\IdeaProjects\\Ppvis_2\\loads"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
            File file = fileChooser.showSaveDialog(stage);
            if (file != null){
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file for open");
        fileChooser.setInitialDirectory(new File("C:\\Users\\degri\\IdeaProjects\\Ppvis_2\\loads"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                MyHandler handler = new MyHandler();
                saxParser.parse(file, handler);
                model.clear();
                model.addProducts(handler.getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void searchRecords(String parameter, Product template){
        dialogModel.addProducts(model.searchProductSet(parameter, template));
    }

    public int removeRecords(String parameter, Product template){
        return model.removeProductSet(parameter, template);
    }
}
