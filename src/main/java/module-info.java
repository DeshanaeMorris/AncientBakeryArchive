module com.ancientbakery.ancientbakeryarchive {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.ancientbakery.ancientbakeryarchive to javafx.fxml;
    exports com.ancientbakery.ancientbakeryarchive;
}