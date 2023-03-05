module fr.location.abiproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires charm.glisten;
    requires java.sql;

    opens fr.location.abiproject to javafx.fxml;
    exports fr.location.abiproject;

}