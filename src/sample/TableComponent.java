package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class TableComponent {
    private TableView<Product> tabl = new TableView<>();
    private Button page = new Button("0");
    private Button nodes = new Button("0");
    private List<Product> products;
    private int currentPage = 0;
    private int noteNum = 5;

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public AnchorPane getRoot(){
        tabl.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        TableColumn<Product, String> tablcol1 = new TableColumn<>("Product name");
        TableColumn<Product, String> tablcol2 = new TableColumn<>("Producer name");
        TableColumn<Product, String> tablcol3 = new TableColumn<>("Producer id");
        TableColumn<Product, String> tablcol4 = new TableColumn<>("Product number");
        TableColumn<Product, String> tablcol5 = new TableColumn<>("Address");
        tabl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablcol1.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol2.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol3.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol4.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol5.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tablcol2.setCellValueFactory(new PropertyValueFactory<>("producerName"));
        tablcol3.setCellValueFactory(new PropertyValueFactory<>("producerId"));
        tablcol4.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
        tablcol5.setCellValueFactory(new PropertyValueFactory<>("address"));
        tabl.getColumns().addAll(tablcol1, tablcol2, tablcol3, tablcol4, tablcol5);
        AnchorPane root = new AnchorPane();
        HBox hb = new HBox();
        Button begin = new Button("<<");
        Button prev = new Button("<");
        Button next = new Button(">");
        Button end = new Button(">>");
        TextField tf = new TextField("");
        tf.setPrefWidth(50);
        Button but = new Button("Submit");
        hb.getChildren().addAll(begin, prev, page, next, end, tf, but, nodes);
        begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (products != null) {
                    getBeginPage();
                }
            }
        });
        prev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (products != null) {
                    getPrevPage();
                }
            }
        });
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (products != null) {
                    getNextPage();
                }
            }
        });
        end.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (products != null) {
                    getEndPage();
                }
            }
        });
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (products != null) {
                    try {
                        Integer.parseInt(tf.getText());
                    } catch (Exception InvocationTargetException) {
                        mistake("Wrong type field", "Number fields should fill by integer numbers");
                    } finally {
                        if (Integer.parseInt(tf.getText()) > 0) {
                            setNoteNum(Integer.parseInt(tf.getText()));
                            getBeginPage();
                        } else {
                            mistake("Wrong type field", "Number fields should be > 0");
                        }
                    }
                }
            }
        });
        root.getChildren().addAll(tabl, hb);
        tabl.prefWidthProperty().bind(root.widthProperty());
        AnchorPane.setTopAnchor(tabl, 30.0);
        AnchorPane.setBottomAnchor(hb, 10.0);
        AnchorPane.setLeftAnchor(hb, 30.0);
        return root;
    }

    private void mistake(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }

    public ObservableList<Product> getCurrentPage(){
        if (currentPage < pageNum() - 1){
            return getProducts(currentPage*noteNum, currentPage*noteNum + noteNum);
        }
        else{
            return getProducts(currentPage*noteNum, products.size());
        }
    }

    public void getPage(){
        tabl.setItems(getCurrentPage());
        refresh();
    }

    public void getNextPage(){
        if (currentPage + 1 < pageNum()){
            currentPage++;
        }
        tabl.setItems(getCurrentPage());
        refresh();
    }

    public void getPrevPage(){
        if (currentPage - 1 >= 0){
            currentPage--;
        }
        tabl.setItems(getCurrentPage());
        refresh();
    }

    public void getBeginPage(){
        currentPage = 0;
        tabl.setItems(getCurrentPage());
        refresh();
    }

    public void getEndPage(){
        if (pageNum() != 0) {
            currentPage = pageNum() - 1;
        }
        tabl.setItems(getCurrentPage());
        refresh();
    }

    public void setNoteNum(int index){
        noteNum = index;
    }

    private int pageNum(){
        if (products.size() % noteNum == 0){
            return products.size()/noteNum;
        }
        else{
            return products.size()/noteNum + 1;
        }
    }

    private ObservableList<Product> getProducts(int start, int finish){
        List<Product> rezult = new LinkedList<Product>();
        if (start >= 0 && finish <= products.size() && start <= finish){
            for (int i = start; i < finish; i++){
                rezult.add(products.get(i));
            }
        }
        return FXCollections.observableList(rezult);
    }

    public void refresh(){
        page.setText(Integer.toString(currentPage));
        nodes.setText(Integer.toString(products.size()));
        tabl.refresh();
    }
}
