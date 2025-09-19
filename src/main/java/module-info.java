module com.compassmaster.todoapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.compassmaster.todoapp to javafx.fxml;
    exports com.compassmaster.todoapp;
}