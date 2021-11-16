/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author bnc
 */
public class DataValidation {

    Alert alert1 = new Alert(Alert.AlertType.ERROR);

    public boolean notEmpty(TextField inputTextField) {
        boolean isEmpty = true;
        alert1.setTitle("Error. Fields can not be empty");
        alert1.setHeaderText(null);
        if (inputTextField.getText().isEmpty()) {
            alert1.setContentText("Please fill in " + inputTextField.getPromptText());
            alert1.show();
            isEmpty = false;
        }
        return isEmpty;
    }

    public boolean indexNo(TextField inputTextField) {
        boolean isIndexNo = true;
        alert1.setTitle("Index no. not valid");
        alert1.setHeaderText(null);
        if (!inputTextField.getText().matches("[0-9]*[/][0-9]*")) {
            alert1.setContentText("Please enter valid index form number. Ex. 123/45");
            alert1.show();
            isIndexNo = false;
        }
        return isIndexNo;
    }

    public boolean phoneNo(TextField inputTextField) {
        boolean isPhone = true;
        alert1.setTitle("Phone no. not valid");
        alert1.setHeaderText(null);
        if (!inputTextField.getText().matches("[+]+[0-9]*") || inputTextField.getText().length() <= 11 || inputTextField.getText().length() > 15) {
            alert1.setContentText("Please enter valid phone number. Format ex. +38733456789");
            alert1.show();
            isPhone = false;
        }
        return isPhone;
    }

    public boolean email(TextField inputTextField) {
        boolean isEmail = true;
        alert1.setTitle("Email no. not valid");
        alert1.setHeaderText(null);
        if (!inputTextField.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]+@[a-zA-Z]+([.][a-zA-Z]+)+")) {
            alert1.setContentText("Please enter valid e-mail address");
            alert1.show();
            isEmail = false;
        }
        return isEmail;
    }

    public boolean textAlphabet(TextField inputTextField) {
        boolean isAlphabet = true;
        alert1.setTitle("Error");
        alert1.setHeaderText(null);
        if (!inputTextField.getText().matches("[A-Z][a-z A-ZŠĐČĆŽšđčćž]+")) {

            alert1.setContentText(inputTextField.getPromptText() + " should only be alphabetical and start with capital letter");
            alert1.show();
            isAlphabet = false;
        }
        return isAlphabet;
    }
}
