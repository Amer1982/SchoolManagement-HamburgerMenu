/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.AdminJpaDao;
import business.entity.Admin;
import business.entity.Privilege;
import business.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class AdminViewController implements Initializable {

    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
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
    private JFXTextField txtNumber;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextArea txtfAdditional;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Admin> adminTable;
    @FXML
    private TableColumn<Admin, Integer> col_adminID;
    @FXML
    private TableColumn<Admin, String> col_username;
    @FXML
    private TableColumn<Admin, String> col_password;
    @FXML
    private TableColumn<Admin, String> col_firstName;
    @FXML
    private TableColumn<Admin, String> col_lastName;
    @FXML
    private TableColumn<Admin, String> col_phone;
    @FXML
    private TableColumn<Admin, String> col_email;
    @FXML
    private TableColumn<Admin, String> col_street;
    @FXML
    private TableColumn<Admin, Integer> col_number;
    @FXML
    private TableColumn<Admin, String> col_city;
    @FXML
    private TableColumn<Admin, Integer> col_zip;
    @FXML
    private TableColumn<Admin, String> col_country;
    @FXML
    private TableColumn<Admin, BigDecimal> col_salary;
    
    
    private ObservableList<Admin> observableListAdmins = FXCollections.observableArrayList();
    private ObservableList<User> observableListUsers = FXCollections.observableArrayList();
    
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAdmins();   
        
        btnSave.setOnAction(this::SaveHandleAction);
        
    } 
    
    private void displayAdmins(){
        List<Admin> adminList = new AdminJpaDao().getAll();
               
        observableListAdmins=FXCollections.observableArrayList(adminList);
        
        adminTable.setItems(observableListAdmins);
        
        col_adminID.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        col_username.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getUsername());
        });
        col_password.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getPassword());
        });
        col_firstName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getFirstName());
        });
        col_lastName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getLastName());
        });
        
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        
        col_street.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getStreet());
        });
        col_number.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty(cellData.getValue().getIdStreet().getNumber());
        });
        col_city.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getIdCity().getCity());
        });
        col_zip.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty(cellData.getValue().getIdStreet().getIdCity().getZIPcode());
        });
        col_country.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getIdCity().getIdCountry().getCountry());
        });
        col_salary.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getIdFinance().getAdminSalary());
        });
        
        
        adminTable.getColumns().addAll(col_adminID, col_username, col_password, col_firstName, col_lastName, 
                col_phone, col_email, col_street, col_number, col_city, col_zip, col_country, col_salary);
    }
    
    private String getAddress(int id){
        return "";
    }
    
    private void initializeCols(){
        
        
    }
    
    private void loadData(){
        //observableListAdmins.removeAll(observableListAdmins);
        
    }

    @FXML
    private void SaveHandleAction(ActionEvent event) {
        
         
        Admin admin = new Admin();
        
        User user = new User();
        user.setFirstName(String.valueOf(txtFName.getText()));
        user.setUsername(String.valueOf(txtFName.getText()));
        user.setLastName(String.valueOf(txtLName.getText()));
        user.setPassword(String.valueOf(txtLName.getText()));
        Privilege privilege = new Privilege(3, "admin");
        user.setIdPrivilege(privilege);
               
       /*admin.getIdUser().setFirstName(String.valueOf(txtFName.getText()));
       admin.getIdUser().setLastName(String.valueOf(txtLName.getText()));
       admin.getIdUser().setUsername(String.valueOf(txtFName.getText()));
       admin.getIdUser().setPassword(String.valueOf(txtLName.getText()));
       admin.setPhoneNumber(String.valueOf(txtPhone.getText()));
       admin.setEMail(String.valueOf(txtEmail.getText()));
       admin.getIdStreet().setStreet(String.valueOf(txtAddress.getText()));
       admin.getIdStreet().setNumber(Integer.valueOf(txtNumber.getText()));
       admin.getIdStreet().getIdCity().setCity(String.valueOf(txtCity.getText()));
       admin.getIdStreet().getIdCity().getIdCountry().setCountry(String.valueOf(txtCountry.getText()));
       Privilege privilege = new Privilege(3, "admin");
       admin.getIdUser().setIdPrivilege(privilege);*/
        
        observableListUsers.add(user);
        clearInputs();
    }

    @FXML
    private void UpdateHandleAction(ActionEvent event) {
    }

    @FXML
    private void DeleteHandleAction(ActionEvent event) {
    }
    
    private void clearInputs() {
        txtFName.clear();
        txtLName.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtCity.clear();
        txtCountry.clear();
        txtfAdditional.clear();
    }
}
