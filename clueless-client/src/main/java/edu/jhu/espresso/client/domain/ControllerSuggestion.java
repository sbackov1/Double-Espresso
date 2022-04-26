package edu.jhu.espresso.client.domain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

public class ControllerSuggestion {

    @FXML
    private ToggleGroup sugChar1;

    @FXML
    private ToggleGroup sugWeap1;

    @FXML
    private Button suggest;

    @FXML
    private Button cancelSuggestion;

    @FXML
    void exitWindow(ActionEvent event) { // cancel suggestion action button

    }

    @FXML
    void sendSuggestion(ActionEvent event) { // confirm suggestion action button

    }
}
