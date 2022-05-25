module com.example.alltestgiftsnew {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires json;


    opens com.example.alltestgiftsnew to javafx.fxml;
    exports com.example.alltestgiftsnew;
}