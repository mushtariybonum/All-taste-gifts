package com.example.alltestgiftsnew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloController {

        @FXML
        private ResourceBundle resourceBundle;

        @FXML
        private URL location;

        @FXML
        private Pane button_candy;

        @FXML
        private Button button_cart;

        @FXML
        private Pane button_flowers;

        @FXML
        private Pane button_jevellery;

        @FXML
        private Pane button_souvenirs;

        @FXML
        private Pane button_toys;

        @FXML
        private Pane in_sale_products;

        @FXML
        private Pane new_products;

        @FXML
        private Pane popular_products;

        @FXML
        private TextField searchBar;

        private Stage stage;
        private Scene scene;
        private Parent root;


        public void GoToProductList(MouseEvent event) throws IOException{
                root = FXMLLoader.load(getClass().getResource("productList.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }


        public void GoToCart(ActionEvent event) throws IOException{
                root = FXMLLoader.load(getClass().getResource("cart.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }


}