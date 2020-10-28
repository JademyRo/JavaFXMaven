package ro.jademy.javafx.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ro.jademy.javafx.demo.events.ViewChangeEvent;
import ro.jademy.javafx.demo.events.TaskEvent;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private void initialize() throws IOException {
        // load top menu and add to border pane
        MenuBar menuBar = FXMLLoader.load(getClass().getResource("/fxml/menuBar.fxml"));
        mainContainer.setTop(menuBar);
        // load side menu and add to border pane
        VBox sideMenu = FXMLLoader.load(getClass().getResource("/fxml/sideMenu.fxml"));
        mainContainer.setLeft(sideMenu);
        // load status bar and add to border pane
        HBox statusBar = FXMLLoader.load(getClass().getResource("/fxml/statusBar.fxml"));
        mainContainer.setBottom(statusBar);
        // load main view and add to border pane
        Pane dashboard = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
        mainContainer.setCenter(dashboard);

        Pane users = FXMLLoader.load(getClass().getResource("/fxml/users.fxml"));

        mainContainer.addEventHandler(ViewChangeEvent.DASHBOARD, viewChangeEvent -> mainContainer.setCenter(dashboard));
        mainContainer.addEventHandler(ViewChangeEvent.USERS, viewChangeEvent -> mainContainer.setCenter(users));
        mainContainer.addEventHandler(TaskEvent.LONG_RUNNING_TASK, taskEvent -> {
            // receive event in the main controller and pass it down to the status bar
            // this is needed because of event bubbling. read more here: https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
            TaskEvent statusEvent = new TaskEvent(TaskEvent.LONG_RUNNING_TASK_STATUS, taskEvent.getTask());
            statusBar.fireEvent(statusEvent);
        });
    }
}
