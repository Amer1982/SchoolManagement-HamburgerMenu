/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import admin.utils.DataValidation;
import business.dao.AdminJpaDao;
import business.dao.CityJpaDao;
import business.dao.CountryJpaDao;
import business.dao.UserJpaDao;
import business.entity.Admin;
import business.entity.City;
import business.entity.Country;
import business.entity.Privilege;
import business.entity.Street;
import business.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Tab;
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
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtStreet;
    @FXML
    private JFXTextField txtNumber;
    @FXML
    private ChoiceBox<City> cityCB;
    @FXML
    private ChoiceBox<Country> countryCB;
    @FXML
    private JFXTextArea txtfAdditional;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab addNew;
    @FXML
    private TableView<Admin> adminTable;
    @FXML
    private TableColumn<Admin, Integer> col_adminID;
    @FXML
    private TableColumn<Admin, String> col_username;
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
    @FXML
    private TableColumn<Admin, String> col_AdditionalInfo;

    private int currentID;
    Admin admin = new Admin();
    AdminJpaDao adminJpaDao = new AdminJpaDao();
    private ObservableList<Admin> observableListAdmins = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAdmins();
        cityComboBox();
        countryComboBox();
        btnUpdate.setVisible(false);
        btnEdit.setDisable(true);
        txtEmail.setDisable(true);
        btnDelete.setDisable(true);

        btnSave.setOnAction(this::saveHandleAction);
        btnUpdate.setOnAction(this::saveHandleAction);
        btnDelete.setOnAction(this::deleteHandleAction);
        btnEdit.setOnAction(this::sendToGrid);
    }

    private void displayAdmins() {
        List<Admin> adminList = new AdminJpaDao().getAll();

        observableListAdmins = FXCollections.observableArrayList(adminList);

        adminTable.setItems(observableListAdmins);

        col_adminID.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_username.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getUsername());
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
        col_salary.setCellValueFactory(new PropertyValueFactory<>("adminSalary"));
        col_AdditionalInfo.setCellValueFactory(new PropertyValueFactory<>("additionalInfo"));

        /*Ovaj mi dio ne treba jer ih automatski ubaci u kolone i pravi mi duplikate
        adminTable.getColumns().addAll(col_adminID, col_username, col_password, col_firstName, col_lastName,
                col_phone, col_email, col_street, col_number, col_city, col_zip, col_country, col_salary);*/
        adminTable.setItems(observableListAdmins);
        adminTable.setEditable(true);
        
        //Search opcija
        
        FilteredList<Admin> filteredData = new FilteredList<>(observableListAdmins, e -> true);
        txtSearch.setOnKeyReleased(e -> {
            txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Admin>) user1 -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user1.getIdUser().getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user1.getIdUser().getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (user1.getIdUser().getUsername().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (user1.getIdStreet().getIdCity().getCity().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (user1.getIdStreet().getIdCity().getIdCountry().getCountry().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Admin> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(adminTable.comparatorProperty());
            adminTable.setItems(sortedData);
        });
    }

    @FXML
    private void saveHandleAction(ActionEvent event) {
     
        DataValidation dataValidation = new DataValidation();
        if (dataValidation.notEmpty(txtFName)
                && dataValidation.notEmpty(txtLName)
                && dataValidation.notEmpty(txtPassword)
                && dataValidation.notEmpty(txtPhone)
                && dataValidation.notEmpty(txtStreet)
                && dataValidation.notEmpty(txtNumber)
                && dataValidation.notEmpty(txtUsername)
                && dataValidation.textAlphabet(txtFName)
                && dataValidation.textAlphabet(txtLName)
                && dataValidation.textAlphabet(txtStreet)
                && dataValidation.phoneNo(txtPhone)) {
            try {
                
                UserJpaDao userJpaDao = new UserJpaDao();

                String username = txtUsername.getText();
                User user = userJpaDao.findByUsername(username);

                if (user == null) {
                    admin.setIdUser(new User());
                } else {
                    admin.setIdUser(user);
                    admin.setId(currentID);
                }
                
                admin.getIdUser().setFirstName(String.valueOf(txtFName.getText()));
                admin.getIdUser().setLastName(String.valueOf(txtLName.getText()));
                admin.getIdUser().setUsername(String.valueOf(txtUsername.getText()));
                admin.getIdUser().setPassword(String.valueOf(txtPassword.getText()));
                Privilege privilege = new Privilege(3, "admin");
                admin.getIdUser().setIdPrivilege(privilege);

                admin.setPhoneNumber(String.valueOf(txtPhone.getText()));
                admin.setEMail(String.valueOf(txtFName.getText().toLowerCase() +"."+ txtLName.getText().toLowerCase() + "@myschool.com"));
                admin.setAdminSalary(new BigDecimal(txtSalary.getText()));
                admin.setAdditionalInfo(String.valueOf(txtfAdditional.getText()));

                admin.setIdStreet(new Street());
                admin.getIdStreet().setStreet(String.valueOf(txtStreet.getText()));
                admin.getIdStreet().setNumber(Integer.valueOf(txtNumber.getText()));

                admin.getIdStreet().setIdCity(cityCB.getSelectionModel().getSelectedItem());
                admin.getIdStreet().getIdCity().setIdCountry(countryCB.getSelectionModel().getSelectedItem());
                
                if (admin.getId() != null) {
                    adminJpaDao.update(admin);
                    int index = observableListAdmins.indexOf(admin);
                    observableListAdmins.set(index, admin);
                } else {
                    adminJpaDao.save(admin);
                    observableListAdmins.add(admin);
                }

                clearInputs();
                displayAdmins();

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    private void deleteHandleAction(ActionEvent event) {
        admin = adminTable.getSelectionModel().getSelectedItem();
        if (admin != null) {
            adminJpaDao.delete(admin);
        }
        displayAdmins();
    }

    private void cityComboBox() {
        List<City> cities = new CityJpaDao().getAll();
        ObservableList<City> observableListCities = FXCollections.observableArrayList(cities);
        cityCB.setItems(observableListCities);
        //za prompt text
        Platform.runLater(() -> {
            SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) cityCB.getSkin();
            for (Node child : skin.getChildren()) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    if (label.getText().isEmpty()) {
                        label.setText("City");
                    }
                    return;
                }
            }
        });
    }

    private void countryComboBox() {
        List<Country> countries = new CountryJpaDao().getAll();
        ObservableList<Country> observableListCountries = FXCollections.observableArrayList(countries);
        countryCB.setItems(observableListCountries);

        Platform.runLater(() -> {
            SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) countryCB.getSkin();
            for (Node child : skin.getChildren()) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    if (label.getText().isEmpty()) {
                        label.setText("Country");
                    }
                    return;
                }
            }
        });
    }

    private void clearInputs() {
        txtFName.clear();
        txtLName.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtStreet.clear();
        txtNumber.clear();
        cityCB.valueProperty().setValue(null);
        countryCB.valueProperty().setValue(null);
        txtfAdditional.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtSalary.clear();
    }

    @FXML
    private void sendToGrid(ActionEvent event) {
        admin = adminTable.getSelectionModel().getSelectedItem();

        this.currentID = admin.getId();
        this.txtFName.setText(admin.getIdUser().getFirstName());
        this.txtLName.setText(admin.getIdUser().getLastName());
        this.txtUsername.setText(admin.getIdUser().getUsername());
        this.txtUsername.setDisable(true);
        this.txtPassword.setText(admin.getIdUser().getPassword());
        this.txtPhone.setText(admin.getPhoneNumber());
        this.txtEmail.setText(admin.getEMail());
        this.txtSalary.setText(admin.getAdminSalary().toString());
        this.txtStreet.setText(admin.getIdStreet().getStreet());
        this.txtNumber.setText(Integer.toString(admin.getIdStreet().getNumber()));
        this.cityCB.getSelectionModel().select(admin.getIdStreet().getIdCity());
        this.countryCB.getSelectionModel().select(admin.getIdStreet().getIdCity().getIdCountry());
        this.txtfAdditional.setText(admin.getAdditionalInfo());

        //prebacuje na tab addNew
        tabPane.getSelectionModel().select(addNew);
        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
    }

    @FXML
    private void adminSelected(MouseEvent event) {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }
}
