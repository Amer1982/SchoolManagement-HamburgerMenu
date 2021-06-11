/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.AdminJpaDao;
import business.entity.Admin;
import business.entity.City;
import business.entity.Country;
import business.entity.Street;
import business.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAdmins();   
        
    } 
    
    private void displayAdmins(){
        List<Admin> adminList = new AdminJpaDao().getAll();
        observableListAdmins=FXCollections.observableArrayList(adminList);
        adminTable.setItems(observableListAdmins);
        
        col_adminID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        col_street.setCellValueFactory(new PropertyValueFactory<>("street"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_zip.setCellValueFactory(new PropertyValueFactory<>("zIPcode"));
        col_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("adminSalary"));
        
        adminTable.setItems(observableListAdmins);
        
        adminTable.getColumns().addAll(col_adminID, col_username, col_password, col_firstName, col_lastName, 
                col_phone, col_email, col_street, col_number, col_city, col_zip, col_country, col_salary);
    }
    
    private void initializeCols(){
        
        
    }
    
    private void loadData(){
        //observableListAdmins.removeAll(observableListAdmins);
        
    }

    @FXML
    private void SaveHandleAction(ActionEvent event) {
        Admin admin = new Admin();
        //admin.setFirstName(txtFName.getText());
        //admin.setLastName(txtLName.getText());
        admin.setPhoneNumber(txtPhone.getText());
        admin.setEMail(txtEmail.getText());
        //admin.setStreet(txtAddress.getText());
        //admin.setCity(txtCity.getText());
        //admin.setCountry(txtCountry.getText());
        //admin.setAdditionalInfo(txtfAdditional.getText());
        //observableListAdmins.add(admin);
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
