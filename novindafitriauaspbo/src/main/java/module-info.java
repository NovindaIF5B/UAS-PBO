module com.tutorial.uswatunhasanah {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tutorial.uswatunhasanah to javafx.fxml;
    exports com.tutorial.uswatunhasanah;
}