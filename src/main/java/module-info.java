module com.ancientbakery.ancientbakeryarchive {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;


    opens com.ancientbakery.ancientbakeryarchive to javafx.fxml;
    exports com.ancientbakery.ancientbakeryarchive;
}