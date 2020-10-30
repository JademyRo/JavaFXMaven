package ro.jademy.javafx.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import ro.jademy.javafx.demo.event.TaskEvent;

public class StatusBarController {

    @FXML
    private HBox statusBar;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private void initialize() {
        statusBar.addEventFilter(TaskEvent.LONG_RUNNING_TASK_STATUS, taskEvent -> {
            // bind the visibility and progress of the status bar to the task received on the event
            progressBar.visibleProperty().bind(taskEvent.getTask().runningProperty());
            progressBar.progressProperty().bind(taskEvent.getTask().progressProperty());
        });
    }
}
