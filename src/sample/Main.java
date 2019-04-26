package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.geometry.Orientation;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) {
        //Pagination pagination = new Pagination();
        TableView<Product> tabl = new TableView<Product>();
        tabl.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        TableColumn<Product, String> tablcol1 = new TableColumn<>("Название товара");
        TableColumn<Product, String> tablcol2 = new TableColumn<>("Название производителя");
        TableColumn<Product, String> tablcol3 = new TableColumn<>("УНП производителя");
        TableColumn<Product, String> tablcol4 = new TableColumn<>("Количество на складе");
        TableColumn<Product, String> tablcol5 = new TableColumn<>("Адрес склада");
        tabl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablcol1.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol2.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol3.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol4.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol5.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tablcol2.setCellValueFactory(new PropertyValueFactory<>("producerName"));
        tablcol3.setCellValueFactory(new PropertyValueFactory<>("userNumber"));
        tablcol4.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
        tablcol5.setCellValueFactory(new PropertyValueFactory<>("adress"));
        tabl.getColumns().addAll(tablcol1, tablcol2, tablcol3, tablcol4, tablcol5);
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu searchMenu = new Menu("Search");
        Menu removeMenu = new Menu("Remove");
        MenuItem openItem = new MenuItem("Open");
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("Esc"));
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        MenuItem addItem = new MenuItem("Add record");
        addItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        addItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getAddRecordDialog();
            }
        });
        MenuItem importItem = new MenuItem("Import records");
        importItem.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        MenuItem productNameSearch = new MenuItem("by name of fhe product");
        productNameSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+1"));
        productNameSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("search", "by name of the product");
            }
        });
        MenuItem productNumberSearch = new MenuItem("by number of the product");
        productNumberSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+2"));
        productNumberSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("search", "by number of the product");
            }
        });
        MenuItem producerNameSearch = new MenuItem("by name of the producer");
        producerNameSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+3"));
        producerNameSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("search", "by name of the producer");
            }
        });
        MenuItem producerIdSearch = new MenuItem("by the producer id");
        producerIdSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+4"));
        producerIdSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("search", "by the producer id");
            }
        });
        MenuItem productNameRemove = new MenuItem("by the name of product");
        productNameRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+5"));
        productNameRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("remove", "by the name of product");
            }
        });
        MenuItem productNumberRemove = new MenuItem("by the number of the product");
        productNumberRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+6"));
        productNumberRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("remove", "by the number of the product");
            }
        });
        MenuItem producerNameRemove = new MenuItem("by the producer name");
        producerNameRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+7"));
        producerNameRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("remove", "by the producer name");
            }
        });
        MenuItem producerIdRemove = new MenuItem("by the producer id");
        producerIdRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+8"));
        producerIdRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchRemoveDialog("remove", "by the producer id");
            }
        });
        fileMenu.getItems().addAll(openItem, saveItem, exitItem);
        editMenu.getItems().addAll(addItem, importItem);
        searchMenu.getItems().addAll(productNameSearch, productNumberSearch, producerNameSearch, producerIdSearch);
        removeMenu.getItems().addAll(productNameRemove, productNumberRemove, producerNameRemove, producerIdRemove);
        menuBar.getMenus().addAll(fileMenu, editMenu, searchMenu, removeMenu);
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        root.getChildren().addAll(menuBar, tabl);
        primaryStage.setTitle("Ppvis_2");
        tabl.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }

    private void mistake(String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void getAddRecordDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Adding record");
        FlowPane dialogRoot = new FlowPane(Orientation.VERTICAL);
        dialogRoot.setVgap(10);
        TextField tf1 = new TextField("String");
        Label label1 = new Label("Enter the product's name:");
        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.getChildren().addAll(label1, tf1);
        TextField tf2 = new TextField("String");
        Label label2 = new Label("Enter the producer's name:");
        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.getChildren().addAll(label2, tf2);
        TextField tf3 = new TextField("Number");
        Label label3 = new Label("Enter the producer's id:");
        HBox hb3 = new HBox();
        hb3.setSpacing(10);
        hb3.getChildren().addAll(label3, tf3);
        TextField tf4 = new TextField("Number");
        Label label4 = new Label("Enter the product's number:");
        HBox hb4 = new HBox();
        hb4.setSpacing(10);
        hb4.getChildren().addAll(label4, tf4);
        TextField tf5 = new TextField("String");
        Label label5 = new Label("Enter address:");
        HBox hb5 = new HBox();
        hb5.setSpacing(10);
        hb5.getChildren().addAll(label5, tf5);
        Button but = new Button("Submit");
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf1.getText().isEmpty() || !tf2.getText().isEmpty() || !tf3.getText().isEmpty() ||
                        !tf4.getText().isEmpty() || !tf5.getText().isEmpty()){
                    try {
                        Integer.parseInt(tf3.getText());
                        Integer.parseInt(tf4.getText());
                    }
                    catch (Exception InvocationTargetException){
                        mistake("Wrong type fields", "Number fields should fill by integer numbers");
                    }
                    finally {
                        controller.addRecord(tf1.getText(), tf2.getText(), Integer.parseInt(tf3.getText()),
                                Integer.parseInt(tf4.getText()), tf5.getText());
                        tf1.clear();
                        tf2.clear();
                        tf3.clear();
                        tf4.clear();
                        tf5.clear();
                    }
                } else{
                    mistake("Empty fields", "You should fill minimum one field for creation record");
                }
            }
        });
        dialogRoot.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, but);
        dialogRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(dialogRoot, 400, 500);
        dialog.setScene(scene);
        dialog.show();
    }

    private void getSearchRemoveDialog(String type, String byWhat) {
        Stage dialog = new Stage();
        dialog.setTitle(type + byWhat);
        FlowPane dialogRoot = new FlowPane(Orientation.VERTICAL);
        dialogRoot.setVgap(10);
        TextField tf = new TextField();
        Label label = new Label("Enter " + byWhat + ":");
        HBox hb = new HBox();
        hb.setSpacing(5);
        Button but = new Button(type);
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        hb.getChildren().addAll(label, tf, but);
        TableView<Product> tabl = new TableView<Product>();
        tabl.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        TableColumn<Product, String> tablcol1 = new TableColumn<>("Название товара");
        TableColumn<Product, String> tablcol2 = new TableColumn<>("Название производителя");
        TableColumn<Product, String> tablcol3 = new TableColumn<>("УНП производителя");
        TableColumn<Product, String> tablcol4 = new TableColumn<>("Количество на складе");
        TableColumn<Product, String> tablcol5 = new TableColumn<>("Адрес склада");
        tabl.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablcol1.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol2.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol3.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol4.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol5.setMaxWidth(1f * Integer.MAX_VALUE * 20);
        tablcol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tablcol2.setCellValueFactory(new PropertyValueFactory<>("producerName"));
        tablcol3.setCellValueFactory(new PropertyValueFactory<>("userNumber"));
        tablcol4.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
        tablcol5.setCellValueFactory(new PropertyValueFactory<>("adress"));
        tabl.getColumns().addAll(tablcol1, tablcol2, tablcol3, tablcol4, tablcol5);
        dialogRoot.getChildren().addAll(hb, tabl);
        dialogRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        tabl.prefWidthProperty().bind(dialog.widthProperty());
        Scene scene = new Scene(dialogRoot, 700, 500);
        dialog.setScene(scene);
        dialog.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
