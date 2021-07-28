/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.StudentJpaDao;
import business.entity.Student;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class StudentViewController implements Initializable {

    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXTextField txtSearchLName;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private Text rbtnGender;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private JFXTextField txtFee;
    @FXML
    private DatePicker dateDaE;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> col_id;
    @FXML
    private TableColumn<Student, String> col_indexNo;
    @FXML
    private TableColumn<Student, String> col_firstName;
    @FXML
    private TableColumn<Student, String> col_lastName;
    @FXML
    private TableColumn<Student, String> col_phone;
    @FXML
    private TableColumn<Student, String> col_gender;
    @FXML
    private TableColumn<Student, String> col_address;
    @FXML
    private TableColumn<Student, Date> col_DoB;
    
    private ObservableList<Student> observableListStudents = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       displayStudents();   
        
    } 
    
    private void displayStudents(){
        List<Student> studentList = new StudentJpaDao().getAll();
               
        observableListStudents=FXCollections.observableArrayList(studentList);
        
        studentTable.setItems(observableListStudents);
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_indexNo.setCellValueFactory(new PropertyValueFactory<>("indexNo"));
        col_firstName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getFirstName());
        });
        col_lastName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getLastName());
        });
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_gender.setCellValueFactory(new  PropertyValueFactory<>("gender"));
        col_DoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        
        
        studentTable.getColumns().addAll(col_id, col_indexNo, col_firstName, col_lastName, col_phone, col_gender, col_DoB);
    }
}

