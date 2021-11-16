/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import admin.utils.DataValidation;
import business.dao.CityJpaDao;
import business.dao.CountryJpaDao;
import business.dao.TeacherJpaDao;
import business.dao.UserJpaDao;
import business.entity.City;
import business.entity.Country;
import business.entity.Privilege;
import business.entity.Street;
import business.entity.Teacher;
import business.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class TeachersViewController implements Initializable {

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
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXTextField txtStreet;
    @FXML
    private JFXTextField txtNumber;
    @FXML
    private ChoiceBox<City> cityCB;
    @FXML
    private ChoiceBox<Country> countryCB;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private GridPane teacherGridPane;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab addNew;
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
    private TableColumn<Teacher, String> col_Username;
    @FXML
    private TableColumn<Teacher, String> col_phone;
    @FXML
    private TableColumn<Teacher, String> col_email;
    @FXML
    private TableColumn<Teacher, String> col_available;
    @FXML
    private TableColumn<Teacher, BigDecimal> col_salary;
    @FXML
    private TableColumn<Teacher, Date> col_DoB;
    @FXML
    private TableColumn<Teacher, String> col_street;
    @FXML
    private TableColumn<Teacher, Integer> col_number;
    @FXML
    private TableColumn<Teacher, String> col_city;
    @FXML
    private TableColumn<Teacher, String> col_country;

    //private Teacher teacher;
    private ObservableList<Teacher> observableListTeachers = FXCollections.observableArrayList();
    private int currentID;
    
    Teacher teacher = new Teacher();
                TeacherJpaDao teacherJpaDao = new TeacherJpaDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        txtEmail.setDisable(true);
        btnUpdate.setVisible(false);
        displayTeachers();

        cityChoiceBox();
        countryChoiceBox();

        btnSave.setOnAction(this::saveHandleAction);
        btnEdit.setOnAction(this::sendToGrid);
        btnUpdate.setOnAction(this::saveHandleAction);
        btnDelete.setOnAction(this::deleteHandleAction);
    }

    private void displayTeachers() {
        List<Teacher> teacherList = new TeacherJpaDao().getAll();

        observableListTeachers = FXCollections.observableArrayList(teacherList);

        teacherTable.setItems(observableListTeachers);

        col_teacherID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getFirstName());
        });
        col_lastName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getLastName());
        });
        col_Username.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getUsername());
        });
        col_available.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_DoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        col_street.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getStreet());
        });
        col_number.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty(cellData.getValue().getIdStreet().getNumber());
        });
        col_city.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getIdCity().getCity());
        });
        col_country.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdStreet().getIdCity().getIdCountry().getCountry());
        });
        col_email.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("teacherSalary"));

         //Search opcija
        teacherTable.setItems(observableListTeachers);
        FilteredList<Teacher> filteredData = new FilteredList<>(observableListTeachers, e -> true);
        txtSearch.setOnKeyReleased(e -> {
            txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Teacher>) user1 -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (user1.getIdUser().getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user1.getIdUser().getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user1.getIdUser().getUsername().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user1.getIdStreet().getIdCity().getCity().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (user1.getIdStreet().getIdCity().getIdCountry().getCountry().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Teacher> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(teacherTable.comparatorProperty());
            teacherTable.setItems(sortedData);
        });
    }

    @FXML
    public void teacherSelected() {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }

    @FXML
    private void saveHandleAction(ActionEvent event) {

        DataValidation dataValidation = new DataValidation();
        if (dataValidation.notEmpty(txtFName)
                && dataValidation.notEmpty(txtLName)
                && dataValidation.notEmpty(txtSalary)
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

                    teacher.setIdUser(new User());
                } else {
                    teacher.setIdUser(user);
                    teacher.setId(currentID);
                    
                }
                //User
                
                teacher.getIdUser().setFirstName(String.valueOf(txtFName.getText()));
                teacher.getIdUser().setLastName(String.valueOf(txtLName.getText()));
                teacher.getIdUser().setUsername(String.valueOf(txtUsername.getText()));
                teacher.getIdUser().setPassword(String.valueOf(txtPassword.getText()));
                Privilege privilege = new Privilege(2, "teacher");
                teacher.getIdUser().setIdPrivilege(privilege);

                //Teacher
                teacher.setPhoneNumber(String.valueOf(txtPhone.getText()));
                teacher.setEMail(String.valueOf(txtFName.getText().toLowerCase() +"."+ txtLName.getText().toLowerCase() + "@myschool.com"));
                teacher.setDateOfBirth(Date.valueOf(dateDoB.getValue()));
                teacher.setTeacherSalary(new BigDecimal(txtSalary.getText()));

                //Street
                teacher.setIdStreet(new Street());
                teacher.getIdStreet().setStreet(String.valueOf(txtStreet.getText()));
                teacher.getIdStreet().setNumber(Integer.valueOf(txtNumber.getText()));
                //City
                teacher.getIdStreet().setIdCity(cityCB.getSelectionModel().getSelectedItem());
                //teacher.getIdStreet().setIdCity(new City(Integer.valueOf("2")));
                teacher.getIdStreet().getIdCity().setIdCountry(countryCB.getSelectionModel().getSelectedItem());

                if (teacher.getId() != null) {
                    teacherJpaDao.update(teacher);
                    int index = observableListTeachers.indexOf(teacher);
                    observableListTeachers.set(index, teacher);
                } else {
                    teacherJpaDao.save(teacher);
                    observableListTeachers.add(teacher);
                }

                clearInputs();
                displayTeachers();

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    private void deleteHandleAction(ActionEvent event) {
        teacher = teacherTable.getSelectionModel().getSelectedItem();
        if (teacher != null) { 
            teacherJpaDao.delete(teacher);
        }
        displayTeachers();
    }

    
    private void cityChoiceBox() {
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

    private void countryChoiceBox() {
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
        txtUsername.clear();
        txtPassword.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtSalary.clear();
        dateDoB.setValue(null);
        txtStreet.clear();
        txtNumber.clear();
        cityCB.valueProperty().setValue(null);
        countryCB.valueProperty().setValue(null);
    }

    @FXML
    private void sendToGrid(ActionEvent event) {
        teacher = teacherTable.getSelectionModel().getSelectedItem();

        this.currentID = teacher.getId();
        this.txtFName.setText(teacher.getIdUser().getFirstName());
        this.txtLName.setText(teacher.getIdUser().getLastName());
        this.txtUsername.setText(teacher.getIdUser().getUsername());
        this.txtUsername.setDisable(true);
        this.txtPassword.setText(teacher.getIdUser().getPassword());
        this.txtPhone.setText(teacher.getPhoneNumber());
        this.txtEmail.setText(teacher.getEMail());
        this.txtSalary.setText(teacher.getTeacherSalary().toString());
        this.dateDoB.setValue(teacher.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.txtStreet.setText(teacher.getIdStreet().getStreet());
        this.txtNumber.setText(Integer.toString(teacher.getIdStreet().getNumber()));
        this.cityCB.getSelectionModel().select(teacher.getIdStreet().getIdCity());
        this.countryCB.getSelectionModel().select(teacher.getIdStreet().getIdCity().getIdCountry());

        tabPane.getSelectionModel().select(addNew);
        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
    }
}






