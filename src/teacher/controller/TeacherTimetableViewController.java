/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class TeacherTimetableViewController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbbSelectClass;
    @FXML
    private DatePicker btnDate;
    @FXML
    private DatePicker btnTime;
    @FXML
    private JFXButton btnScheduleClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
