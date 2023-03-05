module fr.location.abiproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens fr.location.abiproject to javafx.fxml;
    exports fr.location.abiproject;
}