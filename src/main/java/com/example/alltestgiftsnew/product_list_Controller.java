package com.example.alltestgiftsnew;

import com.example.alltestgiftsnew.other_classes.Listener;
import com.example.alltestgiftsnew.other_classes.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

import javafx.scene.Node;

public class product_list_Controller implements Initializable {


    @FXML
    private HBox HelloView;

    @FXML
    private ResourceBundle resourceBundle;

    @FXML
    private URL location;

    @FXML
    private Button button_cart;

    @FXML
    private Button buy_button;

    @FXML
    private Button go_back_button;

    @FXML
    private GridPane grid;

    @FXML
    private Text product_description;

    @FXML
    private ImageView product_image;

    @FXML
    private Label product_price;

    @FXML
    private Label product_name;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox product_card;

    @FXML
    private Button delete_button;

    @FXML
    private Text description_cart;

    @FXML
    private Button go_back_button_cart;

    @FXML
    private GridPane grid_cart;

    @FXML
    private VBox product_card_basket;

    @FXML
    private ImageView product_image_cart;

    @FXML
    private Label product_name_cart;

    @FXML
    private Label product_price_cart;

    @FXML
    private ScrollPane scroll_cart;

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

    private Image image;
    private List<Product> products = new ArrayList<>();
    private Listener myListener;
    private Scene scene;
    private Stage stage;
    private Parent root;

    public void GoToCart(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("cart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GoBack(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



//    public void listItems() throws FileNotFoundException, JsonProcessingException {
//        String object = "";
//        ObjectMapper objectMapper = new ObjectMapper();
//        File myObj = new File("src/main/java/com/example/alltestgiftsnew/api/flowers.json");
//        Scanner myReader = new Scanner(myObj);
//        while (myReader.hasNextLine()) {
//            String data = myReader.nextLine();
//            object += data;
//        }
//        JsonNode jsonNode = objectMapper.readTree(object);
//        for(int i = 0; i < 11; i++){
//            Product product = new Product();
//            product.setId(jsonNode.get("flowers").get(i).get("id").asText());
//            product.setName(jsonNode.get("flowers").get(i).get("name").asText());
//            product.setImg_src(jsonNode.get("flowers").get(i).get("img_src").asText());
//            product.setDescription(jsonNode.get("flowers").get(i).get("description").asText());
//            product.setPrice(jsonNode.get("flowers").get(i).get("price").asText());
//            product.setData(jsonNode.get("flowers").get(i).get("data").asText());
//            products.add(product);
//        }
//        myReader.close();
//    }

    private List<Product> getData() throws IOException {
        List<Product> products = new LinkedList<>();

        OkHttpClient client = new OkHttpClient();

        String requestURL = "https://magic-aliexpress1.p.rapidapi.com/api/bestSales/products?page=1&priceMax=20&priceMin=5&sort=EVALUATE_RATE_ASC&searchName=shoes";
        Request request = new Request.Builder()
                .url(requestURL)
                .get()
                .addHeader("X-RapidAPI-Host", "magic-aliexpress1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "d1018a7bb1msh2c6057d4afc2b72p18b277jsn956bb8fca56a")
                .build();

        Response response = client.newCall(request).execute();
        //here you can check status code

        //jsonData - the whole data as a string
        String jsonData = response.body().string();

        //jsonObj - the whole data as a JSONObject
        JSONObject jsonObj = new JSONObject(jsonData);

        //ja_data - "docs" part, e.g. here are all the products as a JSONArray
        JSONArray ja_data = jsonObj.getJSONArray("docs");


        for(int i = 0; i < ja_data.length(); i++)
        {
            //so for each product I need to retrieve information
            //object is a product as a JSONObject
            JSONObject object = ja_data.getJSONObject(i);
            Object price = object.get("app_sale_price");
            //System.out.println(price);

            Object title = object.get("product_title");


            //"product_main_image_url" not working, so we'll take a URL from metadata->image->imgURL
//            JSONObject metadata = object.getJSONObject("product_main_image_url");
//
//            JSONObject image = metadata.getJSONObject("image");
            Object imageURL = object.get("product_main_image_url");

            //Object imageURL = object.getJSONObject("metadata").getJSONObject("image").get("imgUrl");


            Product product = new Product();
            product.setName(title.toString());
            product.setImgSrc(imageURL.toString());

            //casting Object to Double
            if (price instanceof Double)
                product.setPrice((Double) price);
            else if (price instanceof Integer)
                product.setPrice((Integer) price);
            else
                product.setPrice(-1);

            products.add(product);
        }
        return products;
    }


    private void setChosenProduct(Product product) throws IOException {

        product_name.setText(product.getName());
        product_price.setText(HelloApplication.CURRENCY + product.getPrice());

        URL url = new URL(product.getImg_src());

        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream("output.jpg")) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }

        File file = new File("output.jpg");
        Image image = new Image(file.toURI().toString());
        product_image.setImage(image);

//        product_name.setText(product.getName());
//        product_price.setText(HelloApplication.CURRENCY + product.getPrice());
//        product_description.setText(product.getDescription());
//        image = new Image(getClass().getResourceAsStream(product.getImg_src()));
//        product_image.setImage(image);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
////        try {
////            listItems();
////            for (Product product:
////                 products) {
////
////            System.out.println(product.getName());
////            }
////        } catch (FileNotFoundException | JsonProcessingException e) {
////            e.printStackTrace();
////        }
//
//        try {
//            products.addAll(getData());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (products.size() > 0) {
//            try {
//                setChosenProduct(products.get(0));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            myListener = new Listener() {
//                @Override
//                public void onClickListener(Product product) throws IOException {
//                    setChosenProduct(product);
//                }
//            };
//        }
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < products.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("product.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                item_Controller itemController = fxmlLoader.getController();
//                itemController.setData(products.get(i), myListener);
//
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//
//                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    public void GoToFlowersList(MouseEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("productList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


//        try {
//            listItems();
//            for (Product product:
//                 products) {
//
//            System.out.println(product.getName());
//            }
//        } catch (FileNotFoundException | JsonProcessingException e) {
//            e.printStackTrace();
//        }

        AnchorPane anchorPane = new AnchorPane();
        HelloView.getChildren().addAll(anchorPane)


        try {
            products.addAll(getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (products.size() > 0) {
            try {
                setChosenProduct(products.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            myListener = new Listener() {
                @Override
                public void onClickListener(Product product) throws IOException {
                    setChosenProduct(product);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                item_Controller itemController = fxmlLoader.getController();
                itemController.setData(products.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
