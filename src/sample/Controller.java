package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    private static class MyHandler extends DefaultHandler {
        private List<Product> products = new LinkedList<>();
        private Product product;
        private boolean productName = false;
        private boolean producerName = false;
        private boolean producerId = false;
        private boolean productNumber = false;
        private boolean town = false;
        private boolean street = false;
        private boolean house = false;

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("product")) {
                product = new Product();
            } else if (qName.equalsIgnoreCase("productName")) {
                productName = true;
            } else if (qName.equalsIgnoreCase("producerName")) {
                producerName = true;
            } else if (qName.equalsIgnoreCase("producerId")) {
                producerId = true;
            } else if (qName.equalsIgnoreCase("productNumber")) {
                productNumber = true;
            } else if (qName.equalsIgnoreCase("town")) {
                town = true;
            } else if (qName.equalsIgnoreCase("street")) {
                street = true;
            } else if (qName.equalsIgnoreCase("house")) {
                house = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equalsIgnoreCase("product")) {
                products.add(product);
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            new String(ch, start, length);
            if (productName) {
                product.setProductName(new String(ch, start, length));
                productName = false;
            } else if (producerName) {
                product.setProducerName(new String(ch, start, length));
                producerName = false;
            } else if (producerId) {
                product.setProducerId(Integer.parseInt(new String(ch, start, length)));
                producerId = false;
            } else if (productNumber) {
                product.setProductNumber(Integer.parseInt(new String(ch, start, length)));
                productNumber = false;
            } else if (town) {
                product.setTown(new String(ch, start, length));
                town = false;
            } else if (street) {
                product.setStreet(new String(ch, start, length));
                street = false;
            } else if (house) {
                product.setHouse(Integer.parseInt(new String(ch, start, length)));
                house = false;
            }
        }

        public List<Product> getProducts() {
            return products;
        }
    }

    private Products model = new Products();

    public List<Product> getModel() {
        return model.getAll();
    }

    public void addProduct(Product product) {
        model.addProduct(product);
    }

    public void clearModel() {
        model.clear();
    }

    public int getNodeNumber() {
        return model.size();
    }

    public void saveFile(Stage stage) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element root = doc.createElement("products");
            doc.appendChild(root);
            for (int i = 0; i < model.size(); i++) {
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
                Element town = doc.createElement("town");
                town.appendChild(doc.createTextNode(model.get(i).getTown()));
                Element street = doc.createElement("street");
                street.appendChild(doc.createTextNode(model.get(i).getStreet()));
                Element house = doc.createElement("house");
                house.appendChild(doc.createTextNode(Integer.toString(model.get(i).getHouse())));
                element.appendChild(address);
                address.appendChild(town);
                address.appendChild(street);
                address.appendChild(house);
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
            if (file != null) {
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFile(Stage stage) {
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

    public List<Product> searchRecords(String parameter, Product template) {
        return model.searchProductSet(parameter, template);
    }

    public int removeRecords(String parameter, Product template) {
        return model.removeProductSet(parameter, template);
    }

}
