package ro.jademy.javafx.demo.controller;

import com.github.javafaker.Faker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ro.jademy.javafx.demo.model.Role;
import ro.jademy.javafx.demo.model.User;

import java.util.stream.Collectors;

public class UsersController {

    private ObservableList<User> users = FXCollections.observableArrayList();

    public UsersController() {

        Faker faker = new Faker();

        Role adminRole = new Role("admin");
        Role userRole = new Role("user");

        // create a random list of users
        for (int i = 0; i < 30; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(), faker.internet().safeEmailAddress(), userRole);

            // add the admin role to some of them
            if (i < 5) {
                user.getRoles().add(adminRole);
            }
            users.add(user);
        }
    }

    @FXML
    private TableView<User> userTable;

    @FXML
    private void initialize() {

        // dynamically create the table columns and set what values the column cells will extract from the model
        // we're using the built in property value factory for the string properties in the user model
        TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<User, String> rolesColumn = new TableColumn<>("Roles");
        // the roles property is a list, so we need to convert it to a string
        rolesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoles().stream().map(Role::getName).collect(Collectors.joining(", "))));

        // add the the columns to the table
        userTable.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn, rolesColumn);

        // add the user information
        userTable.setItems(users);
    }
}
