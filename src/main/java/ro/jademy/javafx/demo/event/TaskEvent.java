package ro.jademy.javafx.demo.event;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventType;

public class TaskEvent extends Event {

    public static final EventType<TaskEvent> LONG_RUNNING_TASK = new EventType<>(Event.ANY, "LONG_RUNNING_TASK");
    public static final EventType<TaskEvent> LONG_RUNNING_TASK_STATUS = new EventType<>(Event.ANY, "LONG_RUNNING_TASK_STATUS");

    private final Task<?> task;

    public TaskEvent(EventType<? extends Event> eventType, Task<?> task) {
        super(eventType);
        this.task = task;
    }

    public Task<?> getTask() {
        return task;
    }
}
