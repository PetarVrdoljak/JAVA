module hr.java.vjezbe.vrdoljak7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens hr.java.vjezbe.vrdoljak7 to javafx.fxml;
    opens hr.java.vjezbe.vrdoljak7.entitet to javafx.base;
    exports hr.java.vjezbe.vrdoljak7;
}