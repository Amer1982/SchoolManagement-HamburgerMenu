
package mySchoolProject;


import static business.dao.JpaDao.ENTITY_MANAGER_FACTORY;
import business.dao.UserJpaDao;
import business.entity.Privilege;
import business.entity.User;
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
import javax.persistence.EntityManager;
import org.apache.commons.codec.digest.DigestUtils;
import utils.AccessPrivilege;
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
    
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();


    @FXML
    public void loginActionButton(ActionEvent actionEvent) throws IOException {
        
    String username = usernameTextField.getText();
    String password = DigestUtils.md5Hex(passwordTextField.getText()); //treba hashirati password
    
        UserJpaDao userJpaDao = new UserJpaDao();
        User user = userJpaDao.login(username, password);
        System.out.println("logovani User je "+ user);
        
          if (username.isEmpty() || password.isEmpty()) {
            loginMessageLabel.setText("Please enter your username and password");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Please enter your username and password");
            alert1.show();
            return;
            
        } else if (user==null) {
            loginMessageLabel.setText("Invalid username and/or password! Please try again");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Invalid username and/or password! Please try again");
            alert1.show();
            return;
        }
        
        /*UserJpaDao userJpaDao = new UserJpaDao();
        User user = userJpaDao.login(username, password);
        System.out.println("logovani User je "+ user);
        
        if (user==null) {
            loginMessageLabel.setText("Invalid username and/or password! Please try again");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Invalid username and/or password! Please try again");
            alert1.showAndWait();
            return;
        }*/
        
        Privilege userPrivilege = user.getIdPrivilege();
        
            //Za studenta
            if(AccessPrivilege.STUDENT.getId() == userPrivilege.getId()){
        //if (username.equals("Amer") && password.equals("student")) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainStudentView.fxml"));
                
        newStage(loader);
        
        //za automatski popunjeno polje dataInfo
        MainStudentViewController studentController = loader.getController();
        studentController.showInformation(user.getFirstName()+" "+user.getLastName()); 
                //usernameTextField.getText() + " " + passwordTextField.getText());

             //Za profesora
        } 
            else if (AccessPrivilege.TEACHER.getId()==userPrivilege.getId()){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainTeacherView.fxml"));
      
        newStage(loader);
        
        //za automatski popunjeno polje dataInfo
            MainTeacherViewController teacherController = loader.getController();
        teacherController.showInformation(user.getFirstName()+" "+user.getLastName());

            //za admina
        } else if(AccessPrivilege.ADMIN.getId()==userPrivilege.getId()){
          FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/mainView.fxml"));
         
        newStage(loader);
        
        //za automatski popunjeno polje dataInfo
        MainViewController controller = loader.getController();
        controller.showInformation(user.getFirstName()+" "+user.getLastName());
         
        } 
            
        usernameTextField.clear();
        passwordTextField.clear();
    }

    private void newStage(FXMLLoader loader) throws IOException {
        Parent home = loader.load();
        //Parent parent = FxmlLoader.load(getClass().getClassLoader().getResource("home/professorFxml/Home.fxml"));

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
