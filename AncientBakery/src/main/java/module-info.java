module dea.ancientbakery {
    requires javafx.controls;
    requires javafx.fxml;

    opens dea.ancientbakery to javafx.fxml;

    exports dea.ancientbakery;
    exports dea.ancientbakery.features;
    exports dea.ancientbakery.model;
}
