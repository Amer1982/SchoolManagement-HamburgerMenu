package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.Constants;

public class MainTeacherViewController implements Initializable {

    @FXML
    private StackPane mainStackPane;
    @FXML
    private JFXToolbar toolbar;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXDrawer drawer;
    //temporary pane 
    public static AnchorPane tempAnchorPane;
    @FXML
    private Label dataInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tempAnchorPane = contentPane;
        initDrawer();
    }

    private void initDrawer() {

        try {
            VBox menu = FXMLLoader.load(getClass().getResource(Constants.TEACHERSIDEMENUVIEW));
            drawer.setSidePane(menu);

        } catch (IOException ex) {
            Logger.getLogger(MainTeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e) -> {
            
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
              
                AnchorPane.clearConstraints(drawer);
                AnchorPane.setLeftAnchor(drawer, -150.0);
                AnchorPane.setTopAnchor(drawer, 0.0);
                AnchorPane.setBottomAnchor(drawer, 0.0);
            } else {
                drawer.open();
                AnchorPane.setRightAnchor(drawer, 0.0);
                AnchorPane.setLeftAnchor(drawer, 0.0);
                AnchorPane.setTopAnchor(drawer, 60.0);
                AnchorPane.setBottomAnchor(drawer, 0.0);
            }
        });
    }
    public void showInformation(String usernameTextField) {
        dataInfo.setText(usernameTextField);
    }
}
