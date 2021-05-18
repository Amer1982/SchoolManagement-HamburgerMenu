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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.Constants;

public class MainViewController implements Initializable {

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
            VBox menu = FXMLLoader.load(getClass().getResource(Constants.SIDEMENUVIEW));
            drawer.setSidePane(menu);//set the node

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //transition animation of the hamburger icon
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        //transition.setRate(-1);
        //event handler mouse event
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e) -> {
            //2 slucaj
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
                //Nisam mogao kliknuti nista ispod overlay za drawer kada je zatvoren pa sam dodao ovaj dio koda koji ga 
                //šalje iza AncorPane
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
        //1 slucaj
        /*{drawer.toggle();}
            );
            
        drawer.setOnDrawerOpening((event) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            //dodajem sjenu ispod drawer
            //drawer.setMinWidth(160);
        });
        drawer.setOnDrawerClosed((event) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
        });    */
    }
}
