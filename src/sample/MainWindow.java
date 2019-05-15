package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;


public class MainWindow extends Application {

    private Controller controller = new Controller();
    private TableComponent tabl = new TableComponent();

    @Override
    public void start(Stage primaryStage) {
        tabl.setProducts(controller.getModel());
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
                controller.openFile(primaryStage);
                tabl.getBeginPage();
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
                getAddRecordDialog();
            }
        });
        MenuItem clearItem = new MenuItem("Clear");
        clearItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+C"));
        clearItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.clearModel();
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
                getRemoveDialog("by name of the product");
            }
        });
        MenuItem productNumberRemove = new MenuItem("by the number of the product");
        productNumberRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+6"));
        productNumberRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by number of the product");
            }
        });
        MenuItem producerNameRemove = new MenuItem("by the producer name");
        producerNameRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+7"));
        producerNameRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by name of the producer");
            }
        });
        MenuItem producerIdRemove = new MenuItem("by the producer id");
        producerIdRemove.setAccelerator(KeyCombination.keyCombination("Ctrl+8"));
        producerIdRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRemoveDialog("by the producer id");
            }
        });
        fileMenu.getItems().addAll(openItem, saveItem, exitItem);
        editMenu.getItems().addAll(addItem, clearItem);
        searchMenu.getItems().addAll(productNameSearch, productNumberSearch, producerNameSearch, producerIdSearch);
        removeMenu.getItems().addAll(productNameRemove, productNumberRemove, producerNameRemove, producerIdRemove);
        menuBar.getMenus().addAll(fileMenu, editMenu, searchMenu, removeMenu);
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane root = tabl.getRoot();
        root.getChildren().add(menuBar);
        AnchorPane.setTopAnchor(menuBar,0.0);
        primaryStage.setTitle("Ppvis_2");
        root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }

    private void getAddRecordDialog() {
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
        TextField tf6 = new TextField();
        TextField tf7 = new TextField();
        tf5.setPromptText("String");
        tf5.setPrefWidth(100);
        tf6.setPromptText("String");
        tf6.setPrefWidth(100);
        tf7.setPromptText("Number");
        tf7.setPrefWidth(50);
        Label label5 = new Label("Enter town name:");
        Label label6 = new Label("street:");
        Label label7 = new Label("house number:");
        HBox hb5 = new HBox();
        hb5.setSpacing(10);
        hb5.getChildren().addAll(label5, tf5, label6, tf6, label7, tf7);
        Button but = new Button("Submit");
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf1.getText().isEmpty() || !tf2.getText().isEmpty() || !tf3.getText().isEmpty() ||
                        !tf4.getText().isEmpty() || !tf5.getText().isEmpty() || !tf6.getText().isEmpty() ||
                        !tf7.getText().isEmpty()) {
                    Product temp = new Product();
                    if (!tf1.getText().isEmpty()){
                        temp.setProductName(tf1.getText());
                    }
                    if (!tf2.getText().isEmpty()){
                        temp.setProducerName(tf2.getText());
                    }
                    if (!tf5.getText().isEmpty()){
                        temp.setTown(tf5.getText());
                    }
                    if (!tf6.getText().isEmpty()){
                        temp.setStreet(tf6.getText());
                    }
                    try {
                        if (!tf3.getText().isEmpty()) {
                            temp.setProducerId(Integer.parseInt(tf3.getText()));
                        }
                        if (!tf4.getText().isEmpty()) {
                            temp.setProductNumber(Integer.parseInt(tf4.getText()));
                        }
                        if (!tf7.getText().isEmpty()) {
                            temp.setHouse(Integer.parseInt(tf7.getText()));
                        }
                    } catch (Exception InvocationTargetException) {
                        mistake("Wrong type fields", "Number fields should fill by integer numbers");
                    } finally {
                        controller.addProduct(temp);
                        tf1.clear();
                        tf2.clear();
                        tf3.clear();
                        tf4.clear();
                        tf5.clear();
                        tf6.clear();
                        tf7.clear();
                        tabl.getPage();
                    }
                } else {
                    mistake("Empty fields", "You should fill minimum one field for creation record");
                }
            }
        });
        dialogRoot.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, but);
        dialogRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(dialogRoot, 570, 250);
        dialog.setScene(scene);
        dialog.show();
    }

    private void getSearchDialog(String byWhat) {
        Stage dialog = new Stage();
        dialog.setTitle("Search " + byWhat);
        TextField tf = new TextField();
        Label label = new Label("Enter" + byWhat.substring(2) + ":");
        HBox hb = new HBox();
        hb.setSpacing(10);
        Button but = new Button("Search");
        hb.prefWidthProperty().bind(dialog.widthProperty());
        TableComponent dialogTabl = new TableComponent();
        AnchorPane dialogRoot = dialogTabl.getRoot();
        dialogRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
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
                    } else if (byWhat.equals("by number of the product")) {
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
                            List<Product> products = controller.searchRecords(byWhat, product);
                            dialogTabl.setProducts(products);
                            dialogTabl.getBeginPage();
                        }
                    } else {
                        mistake("Empty field", "You should input value of search parameter");
                    }
                }
            });
        hb.getChildren().addAll(label, tf, but);
        dialogRoot.getChildren().add(hb);
        AnchorPane.setTopAnchor(hb, 0.0);
        Scene scene = new Scene(dialogRoot, 700, 700);
        dialog.setScene(scene);
        dialog.show();
    }

    private void getRemoveDialog(String byWhat) {
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
                    tabl.getBeginPage();
                    mistake("Remove massage", "Were remove "+String.valueOf(counter)+" nodes");
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

    private void mistake(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
