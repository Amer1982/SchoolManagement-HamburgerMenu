/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.TeacherJpaDao;
import business.entity.Teacher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class TeachersViewController implements Initializable {

    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextArea txtfAdditional;
    @FXML
    private ToggleGroup doctor;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn<Teacher, Integer> col_teacherID;
    @FXML
    private TableColumn<Teacher, String> col_firstName;
    @FXML
    private TableColumn<Teacher, String> col_lastName;
    @FXML
    private TableColumn<Teacher, String> col_available;
    @FXML
    private TableColumn<Teacher, String> col_phone;
    @FXML
    private TableColumn<Teacher, Date> col_DoB;
    @FXML
    private TableColumn<Teacher, String> col_street;
    @FXML
    private TableColumn<Teacher, String> col_city;
    @FXML
    private TableColumn<Teacher, String> col_country;
    @FXML
    private TableColumn<Teacher, String> col_email;
    @FXML
    private TableColumn<Teacher, String> col_additionalInfo;
    
    private ObservableList<Teacher> observableListClasses = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayTeachers();
    }    
    private void displayTeachers(){
        List<Teacher> teacherList = new TeacherJpaDao().getAll();
               
        observableListClasses=FXCollections.observableArrayList(teacherList);
        
        teacherTable.setItems(observableListClasses);
        
        col_teacherID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getFirstName());
        });
        col_lastName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getLastName());
        });
        col_available.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_DoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        col_street.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getStreet());
        });
        col_city.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getIdCity().getCity());
        });
        col_country.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getIdCity().getIdCountry().getCountry());
        });
        col_email.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        col_additionalInfo.setCellValueFactory(new PropertyValueFactory<>("id"));
             
        
        teacherTable.getColumns().addAll(col_teacherID, col_firstName, col_lastName, col_available, col_phone,
                col_DoB, col_street, col_city, col_country, col_email, col_additionalInfo);
    }   
}
