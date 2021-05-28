/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySchoolProject;

import controllers.MainStudentViewController;
import controllers.MainTeacherViewController;
import controllers.MainViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import utils.Constants;

public class LoginViewController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private AnchorPane login;


    @FXML
    public void loginActionButton(ActionEvent actionEvent) throws IOException {
        

            //Za studenta
        if (usernameTextField.getText().equals("Amer") && passwordTextField.getText().equals("student")) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainStudentView.fxml"));
                
        newStage(loader);
        
        //za automatski popunjeno polje dataInfo
        MainStudentViewController studentController = loader.getController();
        studentController.showInformation(usernameTextField.getText() + " " + passwordTextField.getText());

             //Za profesora
        } else if (usernameTextField.getText().equals("Amer") && passwordTextField.getText().equals("profa")) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainTeacherView.fxml"));
      
        newStage(loader);
        
        //za automatski popunjeno polje dataInfo
            MainTeacherViewController teacherController = loader.getController();
        teacherController.showInformation(usernameTextField.getText() + " " + passwordTextField.getText());

            //za admina
        } else if (usernameTextField.getText().equals("Amer") && passwordTextField.getText().equals("admin")) {
          FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainView.fxml"));
         
        newStage(loader);
        
        //za automatski popunjeno polje dataInfo
        MainViewController controller = loader.getController();
        controller.showInformation(usernameTextField.getText() + " " + passwordTextField.getText());
            

        } else {
            loginMessageLabel.setText("Invalid username and/or password! Please try again");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Invalid username and/or password! Please try again");
            alert1.showAndWait();
        }
        usernameTextField.clear();
        passwordTextField.clear();
    }

    private void newStage(FXMLLoader loader) throws IOException {
        Parent home = loader.load();
        //Parent parent = FxmlLoader.load(getClass().getClassLoader().getResource("home/professorFxml/Home.fxml"));

        /*//za automatski popunjeno polje dataInfo
        MainViewController controller = loader.getController();
        controller.showInformation(usernameTextField.getText() + " " + passwordTextField.getText());*/

        //otvara novi scene na istom stageu(za hamburger app mi nije potrebno. Koristim hide())
        //login.getChildren().setAll((home));

        Stage stage = new Stage();
        stage.setScene(new Scene(home));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(Constants.MAIN_TITLE);
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.show();
        
        //loginScene nestaje
        loginButton.getScene().getWindow().hide();
    }
}
