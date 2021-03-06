/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.controllers;

import com.jfoenix.controls.JFXButton;
import controllers.MainStudentViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import student.utils.studentConstants;
import utils.Constants;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class SideMenuViewStudentController implements Initializable {

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnMessages;
    @FXML
    private JFXButton btnTimetable;
    @FXML
    private JFXButton btnClasses;
    @FXML
    private JFXButton btnFinances;
    @FXML
    private JFXButton btnLogut;
    @FXML
    private JFXButton btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            StackPane homePage = FXMLLoader.load(getClass().getResource("/student/views/homeView.fxml"));
             MainStudentViewController.tempAnchorPane.getChildren().setAll(homePage);
            
        } catch (IOException ex) {
            Logger.getLogger(SideMenuViewStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void openHome(ActionEvent event) {
        switchPane(studentConstants.HOMEVIEW);
    }

    @FXML
    private void openMessage(ActionEvent event) {
        switchPane(studentConstants.MESSAGEVIEW);
    }

    @FXML
    private void openTimetable(ActionEvent event) {
        switchPane(studentConstants.TIMETABLEVIEW);
    }

    @FXML
    private void openClasses(ActionEvent event) {
        switchPane(studentConstants.CLASSESVIEW);
    }

    @FXML
    private void openFinances(ActionEvent event) {
        switchPane(studentConstants.FINANCEVIEW);
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            //loginScene nestaje
            btnLogut.getScene().getWindow().hide();
            //Ponovo se poziva Login Scene
            Parent root = FXMLLoader.load(getClass().getResource(Constants.LOGINVIEW));
            
            Stage primaryStage = new Stage();
            primaryStage.setTitle("School Management");
            primaryStage.setScene(new Scene(root));
            //primaryStage.getIcons().add(new Image("@../images/logo.png"));

            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(SideMenuViewStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void onExit(ActionEvent event) {
        System.exit(0);
    }
    private void switchPane(String pane){
       try {
           MainStudentViewController.tempAnchorPane.getChildren().clear(); 
           StackPane pane2 = FXMLLoader.load(getClass().getResource(pane));
           //punimo listu ostalim stageovima
           ObservableList<Node> elements = pane2.getChildren();
           MainStudentViewController.tempAnchorPane.getChildren().setAll(elements);
                       
        } catch (IOException ex) {
            Logger.getLogger(SideMenuViewStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
