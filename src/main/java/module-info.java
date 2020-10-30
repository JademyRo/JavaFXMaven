module ro.jademy.javafx.demo {
    requires java.sql;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafaker;
    opens ro.jademy.javafx.demo;
    opens ro.jademy.javafx.demo.model;
    opens ro.jademy.javafx.demo.controller;
}