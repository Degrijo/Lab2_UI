package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) {
        TableView<Product> tabl = new TableView<>();
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
        tabl.prefWidthProperty().bind(primaryStage.widthProperty());
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu searchMenu = new Menu("Search");
        Menu removeMenu = new Menu("Remove");
        MenuItem openItem = new MenuItem("Open");
        Button label = new Button("0");
        label.setPrefWidth(50);
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.openFile(primaryStage);
                tabl.getItems().clear();
                tabl.setItems(controller.getCurrentPage());
                tabl.refresh();
                label.setText(String.valueOf(controller.getNodeNumber()));
            }
        });
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.saveFile(primaryStage);
            }
        });
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
                getAddRecordDialog(tabl, label);
            }
        });
        MenuItem clearItem = new MenuItem("Clear");
        clearItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+C"));
        clearItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.clearModel();
                tabl.getItems().clear();
                label.setText("0");
            }
        });
        MenuItem productNameSearch = new MenuItem("by name of the product");
        productNameSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+1"));
        productNameSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchDialog("by name of the product");
            }
        });
        MenuItem productNumberSearch = new MenuItem("by number of the product");
        productNumberSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+2"));
        productNumberSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchDialog("by number of the product");
            }
        });
        MenuItem producerNameSearch = new MenuItem("by name of the producer");
        producerNameSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+3"));
        producerNameSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchDialog("by name of the producer");
            }
        });
        MenuItem producerIdSearch = new MenuItem("by the producer id");
        producerIdSearch.setAccelerator(KeyCombination.keyCombination("Ctrl+4"));
        producerIdSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSearchDialog("by the producer id");
            }
        });
        MenuItem productNameRemove = new MenuItem("by the name of product");
        productNameRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+5"));
        productNameRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by name of the product", tabl, label);
                label.setText(String.valueOf(controller.getNodeNumber()));
            }
        });
        MenuItem productNumberRemove = new MenuItem("by the number of the product");
        productNumberRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+6"));
        productNumberRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by number of the product", tabl, label);
                label.setText(String.valueOf(controller.getNodeNumber()));
            }
        });
        MenuItem producerNameRemove = new MenuItem("by the producer name");
        producerNameRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+7"));
        producerNameRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by name of the producer", tabl, label);
                label.setText(String.valueOf(controller.getNodeNumber()));
            }
        });
        MenuItem producerIdRemove = new MenuItem("by the producer id");
        producerIdRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+8"));
        producerIdRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by the producer id", tabl, label);
                label.setText(String.valueOf(controller.getNodeNumber()));
            }
        });
        fileMenu.getItems().addAll(openItem, saveItem, exitItem);
        editMenu.getItems().addAll(addItem, clearItem);
        searchMenu.getItems().addAll(productNameSearch, productNumberSearch, producerNameSearch, producerIdSearch);
        removeMenu.getItems().addAll(productNameRemove, productNumberRemove, producerNameRemove, producerIdRemove);
        menuBar.getMenus().addAll(fileMenu, editMenu, searchMenu, removeMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane root = new AnchorPane();
        HBox hb = new HBox();
        Button begin = new Button("<<");
        Button prev = new Button("<");
        Button next = new Button(">");
        Button end = new Button(">>");
        TextField tf = new TextField("5");
        tf.setPrefWidth(50);
        Button but = new Button("Submit");
        hb.getChildren().addAll(begin, prev, next, end, tf, but, label);
        begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getBeginPage());
                tabl.refresh();
            }
        });
        prev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getPrevPage());
                tabl.refresh();
            }
        });
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getNextPage());
                tabl.refresh();
            }
        });
        end.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getEndPage());
                tabl.refresh();
            }
        });
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer.parseInt(tf.getText());
                } catch (Exception InvocationTargetException) {
                    mistake("Wrong type field", "Number fields should fill by integer numbers");
                } finally {
                    if (Integer.parseInt(tf.getText()) > 0) {
                        tabl.getItems().clear();
                        tabl.setItems(controller.setNoteNum(Integer.parseInt(tf.getText())));
                        tabl.refresh();
                    }
                    else{
                        mistake("Wrong type field", "Number fields should be > 0");
                    }
                }
            }
        });
        root.getChildren().addAll(menuBar, tabl, hb);
        primaryStage.setTitle("Ppvis_2");
        root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
        AnchorPane.setTopAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(tabl, menuBar.getHeight());
        AnchorPane.setBottomAnchor(hb, 10.0);
        AnchorPane.setLeftAnchor(hb, primaryStage.getWidth() / 2 - 100);
    }

    private void mistake(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void getAddRecordDialog(TableView<Product> tabl, Button label) {
        Stage dialog = new Stage();
        dialog.setTitle("Adding record");
        FlowPane dialogRoot = new FlowPane(Orientation.VERTICAL);
        dialogRoot.setVgap(10);
        TextField tf1 = new TextField();
        tf1.setPromptText("String");
        Label label1 = new Label("Enter the product's name:");
        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.getChildren().addAll(label1, tf1);
        TextField tf2 = new TextField();
        tf2.setPromptText("String");
        Label label2 = new Label("Enter the producer's name:");
        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.getChildren().addAll(label2, tf2);
        TextField tf3 = new TextField();
        tf3.setPromptText("Number");
        Label label3 = new Label("Enter the producer's id:");
        HBox hb3 = new HBox();
        hb3.setSpacing(10);
        hb3.getChildren().addAll(label3, tf3);
        TextField tf4 = new TextField();
        tf4.setPromptText("Number");
        Label label4 = new Label("Enter the product's number:");
        HBox hb4 = new HBox();
        hb4.setSpacing(10);
        hb4.getChildren().addAll(label4, tf4);
        TextField tf5 = new TextField();
        tf5.setPromptText("String");
        Label label5 = new Label("Enter address:");
        HBox hb5 = new HBox();
        hb5.setSpacing(10);
        hb5.getChildren().addAll(label5, tf5);
        Button but = new Button("Submit");
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf1.getText().isEmpty() || !tf2.getText().isEmpty() || !tf3.getText().isEmpty() ||
                        !tf4.getText().isEmpty() || !tf5.getText().isEmpty()) {
                    try {
                        if (!tf3.getText().isEmpty()) {
                            Integer.parseInt(tf3.getText());
                        }
                        if (!tf4.getText().isEmpty()) {
                            Integer.parseInt(tf4.getText());
                        }
                    } catch (Exception InvocationTargetException) {
                        mistake("Wrong type fields", "Number fields should fill by integer numbers");
                    } finally {
                        controller.addRecord(tf1.getText(), tf2.getText(), tf3.getText(),
                                tf4.getText(), tf5.getText());
                        tf1.clear();
                        tf2.clear();
                        tf3.clear();
                        tf4.clear();
                        tf5.clear();
                        tabl.getItems().clear();
                        tabl.setItems(controller.getCurrentPage());
                        tabl.refresh();
                        label.setText(String.valueOf(controller.getNodeNumber()));
                    }
                } else {
                    mistake("Empty fields", "You should fill minimum one field for creation record");
                }
            }
        });
        dialogRoot.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, but);
        dialogRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(dialogRoot, 400, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    private void getSearchDialog(String byWhat) {
        Stage dialog = new Stage();
        dialog.setTitle("Search " + byWhat);
        AnchorPane dialogRoot = new AnchorPane();
        TextField tf = new TextField();
        Label label = new Label("Enter " + byWhat + ":");
        Button labelPag = new Button("0");
        HBox hb = new HBox();
        hb.setSpacing(10);
        Button but = new Button("Search");
        hb.prefWidthProperty().bind(dialog.widthProperty());
        controller.clearDialogModel();
        dialogRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
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
        tablcol3.setCellValueFactory(new PropertyValueFactory<>("producerId"));
        tablcol4.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
        tablcol5.setCellValueFactory(new PropertyValueFactory<>("address"));
        tabl.getColumns().addAll(tablcol1, tablcol2, tablcol3, tablcol4, tablcol5);
        tabl.prefWidthProperty().bind(dialog.widthProperty());
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf.getText().isEmpty())
                    if (byWhat.equals("by name of the product")) {
                        Product product = new Product();
                        product.setProductName(tf.getText());
                        controller.searchRecords(byWhat, product);
                    } else if (byWhat.equals("by name of the producer")) {
                        Product product = new Product();
                        product.setProducerName(tf.getText());
                        controller.searchRecords(byWhat, product);
                    } else if (byWhat.equals("by the number of the product")) {
                        Product product = new Product();
                        try {
                            product.setProductNumber(Integer.parseInt(tf.getText()));
                        } catch (Exception InvocationTargetException) {
                            mistake("Wrong type field", "Number fields should fill by integer numbers");
                        } finally {
                            controller.searchRecords(byWhat, product);
                        }
                    } else if (byWhat.equals("by the producer id")) {
                        Product product = new Product();
                        try {
                            product.setProducerId(Integer.parseInt(tf.getText()));
                        } catch (Exception InvocationTargetException) {
                            mistake("Wrong type field", "Number fields should fill by integer numbers");
                        } finally {
                            controller.searchRecords(byWhat, product);
                        }
                    } else {
                        mistake("Empty field", "You should input value of search parameter");
                    }
                tabl.getItems().clear();
                tabl.setItems(controller.getCurrentDialofPage());
                tabl.refresh();
                labelPag.setText(String.valueOf(controller.getNodeNumber()));
            }
        });
        hb.getChildren().addAll(label, tf, but);
        HBox pag = new HBox();
        Button begin = new Button("<<");
        Button prev = new Button("<");
        Button next = new Button(">");
        Button end = new Button(">>");
        TextField tfPag = new TextField("5");
        tfPag.setPrefWidth(50);
        Button butPag = new Button("Submit");
        pag.getChildren().addAll(begin, prev, next, end, tfPag, butPag, labelPag);
        begin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getBeginDialogPage());
                tabl.refresh();
            }
        });
        prev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getPrevDialogPage());
                tabl.refresh();
            }
        });
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getNextDialogPage());
                tabl.refresh();
            }
        });
        end.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabl.getItems().clear();
                tabl.setItems(controller.getEndDialogPage());
                tabl.refresh();
            }
        });
        butPag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer.parseInt(tfPag.getText());
                } catch (Exception InvocationTargetException) {
                    mistake("Wrong type field", "Number fields should fill by integer numbers");
                } finally {
                    if (Integer.parseInt(tfPag.getText()) > 0) {
                        tabl.getItems().clear();
                        tabl.setItems(controller.setNoteNumDialog(Integer.parseInt(tfPag.getText())));
                        tabl.refresh();
                    }
                    else{
                        mistake("Wrong type field", "Number fields should be > 0");
                    }
                }
            }
        });
        dialogRoot.getChildren().addAll(hb, tabl, pag);
        AnchorPane.setTopAnchor(hb, 0.0);
        AnchorPane.setTopAnchor(tabl, 50.0);
        AnchorPane.setBottomAnchor(pag, 50.0);
        AnchorPane.setLeftAnchor(pag, dialogRoot.getWidth() / 2);
        Scene scene = new Scene(dialogRoot, 700, 700);
        dialog.setScene(scene);
        dialog.show();
    }

    private void getRemoveDialog(String byWhat, TableView<Product> tabl, Button tablLab) {
        Stage dialog = new Stage();
        dialog.setTitle("Remove " + byWhat);
        FlowPane dialogRoot = new FlowPane(Orientation.VERTICAL);
        dialogRoot.setAlignment(Pos.TOP_LEFT);
        dialogRoot.setVgap(10);
        TextField tf = new TextField();
        Label label = new Label("Enter " + byWhat + ":");
        HBox hb = new HBox();
        hb.setSpacing(10);
        Button but = new Button("Remove");
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf.getText().isEmpty()){
                    Product product = new Product();
                    int counter = 0;
                    if (byWhat.equals("by name of the product")) {
                        product.setProductName(tf.getText());
                        counter = controller.removeRecords(byWhat, product);
                        System.out.println("Counter "+counter);
                    } else if (byWhat.equals("by name of the producer")) {
                        product.setProducerName(tf.getText());
                        counter = controller.removeRecords(byWhat, product);
                    } else if (byWhat.equals("by the number of the product")) {
                        try {
                            product.setProductNumber(Integer.parseInt(tf.getText()));
                        } catch (Exception InvocationTargetException) {
                            mistake("Wrong type field", "Number fields should fill by integer numbers");
                        } finally {
                            counter = controller.removeRecords(byWhat, product);
                        }
                    } else if (byWhat.equals("by the producer id")) {
                        try {
                            product.setProducerId(Integer.parseInt(tf.getText()));
                        } catch (Exception InvocationTargetException) {
                            mistake("Wrong type field", "Number fields should fill by integer numbers");
                        } finally {
                            counter = controller.removeRecords(byWhat, product);
                        }
                    }
                    tf.clear();
                    tabl.setItems(controller.getCurrentPage());
                    tabl.refresh();
                    mistake("Remove massage", "Were remove "+String.valueOf(counter)+" nodes");
                    tablLab.setText(String.valueOf(controller.getNodeNumber()));
                } else {
                    mistake("Empty field", "You should input value of search parameter");
                }
            }
        });
        but.setPrefWidth(200);
        hb.getChildren().addAll(label, tf);
        dialogRoot.getChildren().addAll(hb, but);
        Scene scene = new Scene(dialogRoot, 500, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
