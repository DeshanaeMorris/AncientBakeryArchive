module dea.ancientbakery {
    requires javafx.controls;
    requires javafx.fxml;


    opens dea.ancientbakery to javafx.fxml;
    exports dea.ancientbakery;
}