package ro.jademy.javafx.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ro.jademy.javafx.demo.events.ViewChangeEvent;

import java.io.IOException;

public class SideMenuController {

    @FXML
    private Button dashboard;
    @FXML
    private Button users;

    @FXML
    private void initialize() throws IOException {
        // show dashboard pane
        dashboard.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Changing view to dashboard");

            ViewChangeEvent dashboardChangeEvent = new ViewChangeEvent(ViewChangeEvent.DASHBOARD);
            dashboard.fireEvent(dashboardChangeEvent);
        });

        // show users pane
        users.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("Changing view to users");

            ViewChangeEvent dashboardChangeEvent = new ViewChangeEvent(ViewChangeEvent.USERS);
            dashboard.fireEvent(dashboardChangeEvent);
        });
    }
}
