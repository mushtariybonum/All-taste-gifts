package com.example.alltestgiftsnew;

import com.example.alltestgiftsnew.other_classes.Listener;
import com.example.alltestgiftsnew.other_classes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class item_Controller {

        @FXML
        private ResourceBundle resourceBundle;

        @FXML
        private URL location;

        @FXML
        private ImageView img;

        @FXML
        private Label name;

        @FXML
        private Label price;

        @FXML
        private AnchorPane productCard;

        private Product product;

        @FXML
        private void click(MouseEvent mouseEvent) throws IOException {
            myListener.onClickListener(product);
        }

        private Listener myListener;

        public void setData(Product product, Listener listener){
            this.product = product;
            this.myListener = listener;
            name.setText(product.getName());
            price.setText(HelloApplication.CURRENCY + product.getPrice());
            Image image = new Image(getClass().getResourceAsStream(product.getImg_src()));
            img.setImage(image);
        }
    }

