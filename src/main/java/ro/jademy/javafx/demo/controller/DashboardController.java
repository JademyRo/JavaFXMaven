package ro.jademy.javafx.demo.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ro.jademy.javafx.demo.event.TaskEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashboardController {

    @FXML
    private Button taskButton;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Task<Void> task;

    @FXML
    private void initialize() {

        taskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (task != null && task.isRunning()) {
                task.cancel();
            } else {
                // create a new task and start executing it on a background thread
                task = new LongRunningTask();
                executorService.execute(task);

                // send task event
                TaskEvent taskEvent = new TaskEvent(TaskEvent.LONG_RUNNING_TASK, task);
                taskButton.fireEvent(taskEvent);
            }

            task.setOnRunning(workerStateEvent -> taskButton.setText("Cancel time consuming task!"));
            task.setOnSucceeded(workerStateEvent -> taskButton.setText("Start time consuming task!"));
            task.setOnCancelled(workerStateEvent -> taskButton.setText("Start time consuming task!"));
        });
    }
}

class LongRunningTask extends Task<Void> {
    @Override
    public Void call() {
        System.out.println("Started task!");
        final int max = 10;
        for (int i = 1; i <= max; i++) {
            if (isCancelled()) {
                break;
            }
            updateProgress(i, max);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Task was canceled!");
            }
        }
        return null;
    }
}
