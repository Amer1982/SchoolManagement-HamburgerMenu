
package admin.controllers;

import admin.utils.adminConstants;
import com.jfoenix.controls.JFXButton;
import controllers.MainViewController;
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
import utils.Constants;


public class SideMenuViewController implements Initializable {

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnLogut;
    @FXML
    private JFXButton btnExit;
    @FXML
    private JFXButton btnStudents;
    @FXML
    private JFXButton btnTeachers;
    @FXML
    private JFXButton btnTimetable;
    @FXML
    private JFXButton btnClasses;
    @FXML
    private JFXButton btnFinances;
    @FXML
    private JFXButton btnAdmins;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //da se automatski pojavljuje i homepage
        try {
            StackPane homePage = FXMLLoader.load(getClass().getResource(adminConstants.HOMEVIEW));
            MainViewController.tempAnchorPane.getChildren().setAll(homePage);
            
        } catch (IOException ex) {
            Logger.getLogger(SideMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void openHome(ActionEvent event) {
        switchPane(adminConstants.HOMEVIEW);
    }
    
    @FXML
    private void openStudents(ActionEvent event) {
        switchPane(adminConstants.STUDENTVIEW);
    }

    @FXML
    private void openTeachers(ActionEvent event) {
        switchPane(adminConstants.TEACHERSVIEW);
    }
    
    @FXML
    private void openAdmin(ActionEvent event) {
        switchPane(adminConstants.ADMINSVIEW);
    }

    @FXML
    private void openTimetable(ActionEvent event) {
        switchPane(adminConstants.TIMETABLEVIEW);
    }

    @FXML
    private void openClasses(ActionEvent event) {
        switchPane(adminConstants.CLASSESVIEW);
    }

    @FXML
    private void openFinances(ActionEvent event) {
        switchPane(adminConstants.FINANCEVIEW);
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
            Logger.getLogger(SideMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onExit(ActionEvent event) {
        System.exit(0);
    }
    private void switchPane(String pane){
       try {
           
           MainViewController.tempAnchorPane.getChildren().clear(); 
           StackPane pane2 = FXMLLoader.load(getClass().getResource(pane));
           //punimo listu ostalim stageovima
           ObservableList<Node> elements = pane2.getChildren();
           MainViewController.tempAnchorPane.getChildren().setAll(elements);
                       
        } catch (IOException ex) {
            Logger.getLogger(SideMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
