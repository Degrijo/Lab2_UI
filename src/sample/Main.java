package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Animation;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TableView<Table> tabl = new TableView<Table>();
        tabl.setMaxSize(1000.0, 500.0);
        TableColumn<Table, String> tablcol1 = new TableColumn<>("Название товара");
        TableColumn<Table, String> tablcol2 = new TableColumn<>("Название производителя");
        TableColumn<Table, String> tablcol3 = new TableColumn<>("УНП производителя");
        TableColumn<Table, String> tablcol4 = new TableColumn<>("Количество на складе");
        TableColumn<Table, String> tablcol5 = new TableColumn<>("Адрес склада");
        tablcol1.setMinWidth(tabl.getMaxWidth()/5);
        tablcol2.setMinWidth(tabl.getMaxWidth()/5);
        tablcol3.setMinWidth(tabl.getMaxWidth()/5);
        tablcol4.setMinWidth(tabl.getMaxWidth()/5);
        tablcol5.setMinWidth(tabl.getMaxWidth()/5);
        tablcol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tablcol2.setCellValueFactory(new PropertyValueFactory<>("producerName"));
        tablcol3.setCellValueFactory(new PropertyValueFactory<>("userNumber"));
        tablcol4.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
        tablcol5.setCellValueFactory(new PropertyValueFactory<>("adress"));
        tabl.getColumns().addAll(tablcol1, tablcol2, tablcol3, tablcol4, tablcol5);
        FlowPane root = new FlowPane();
        root.getChildren().addAll(tabl);
        primaryStage.setTitle("Ppvis_2");
        primaryStage.setScene(new Scene(root, 1100, 600));
        moveColumnText(tablcol1, tabl);
        moveColumnText(tablcol2, tabl);
        moveColumnText(tablcol3, tabl);
        moveColumnText(tablcol4, tabl);
        moveColumnText(tablcol5, tabl);
        primaryStage.show();
    }

    private void moveColumnText(TableColumn tableColumn1, TableView<Table> table) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev -> {
            int coef = 7;
            if (tableColumn1.getText() != null && !tableColumn1.getText().isEmpty()) {
                if (tableColumn1.getText().length() > tableColumn1.getMinWidth()/coef) {
                    tableColumn1.setText(tableColumn1.getText().substring((int) tableColumn1.getMinWidth()/coef)+tableColumn1.getText().substring(0, (int) tableColumn1.getMinWidth()/coef));
                } else {
                    String s = " " + tableColumn1.getText();
                    tableColumn1.setText(s);
                }
                table.refresh();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
