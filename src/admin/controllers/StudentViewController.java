/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import admin.utils.DataValidation;
import business.dao.CityJpaDao;
import business.dao.CountryJpaDao;
import business.dao.StudentJpaDao;
import business.dao.UserJpaDao;
import business.entity.City;
import business.entity.Country;
import business.entity.Privilege;
import business.entity.Street;
import business.entity.Student;
import business.entity.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private JFXTextField txtStreet;
    @FXML
    private JFXTextField txtStreetNo;
    @FXML
    private JFXTextField txtIndexNo;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXRadioButton rbtnMale;
    @FXML
    private JFXRadioButton rbtnFemale;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtFee;
    @FXML
    private JFXTextField txtZipCode;
    @FXML
    private ChoiceBox<City> cityCB;
    @FXML
    private ChoiceBox<Country> countryCB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private GridPane teacherForm;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private Tab addNew;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> col_StudentID;
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
    private TableColumn<Student, String> col_street;
    @FXML
    private TableColumn<Student, Integer> col_number;
    @FXML
    private TableColumn<Student, String> col_city;
    @FXML
    private TableColumn<Student, String> col_country;
    @FXML
    private TableColumn<Student, Integer> col_zip;
    @FXML
    private TableColumn<Student, Date> col_DoB;
    @FXML
    private TableColumn<Student, Date> col_DoE;
    @FXML
    private TableColumn<Student, BigDecimal> col_studentFee;

    private int currentID;
    private String radioButtonLabel = "male";
    private ObservableList<Student> observableListStudents = FXCollections.observableArrayList();
    Student student = new Student();
    StudentJpaDao studentJpaDao = new StudentJpaDao();
    @FXML
    private TableColumn<Student, String> col_username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayStudents();

        cityChoiceBox();
        countryChoiceBox();
        selectGender();
        btnUpdate.setVisible(false);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);

        btnDelete.setOnAction(this::deleteHandleAction);
        btnSave.setOnAction(this::saveHandleAction);
        btnUpdate.setOnAction(this::saveHandleAction);
        btnEdit.setOnAction(this::sendToGrid);
    }

    private void displayStudents() {
        List<Student> studentList = new StudentJpaDao().getAll();

        observableListStudents = FXCollections.observableArrayList(studentList);

        studentTable.setItems(observableListStudents);

        
        col_StudentID.setCellValueFactory(new PropertyValueFactory<>("id"));

        col_indexNo.setCellValueFactory(new PropertyValueFactory<>("indexNo"));
        col_firstName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getFirstName());
        });
        col_lastName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getLastName());
        });
        col_username.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getIdUser().getUsername());
        });
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
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
        col_DoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        col_DoE.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
        col_studentFee.setCellValueFactory(new PropertyValueFactory<>("studentFee"));

               
        //Search opcija
        studentTable.setItems(observableListStudents);
        FilteredList<Student> filteredData = new FilteredList<>(observableListStudents, e -> true);
        txtSearch.setOnKeyReleased(e -> {
            txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Student>) user1 -> {
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
                    else if (user1.getGender().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Student> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
            studentTable.setItems(sortedData);
        });
    }

    @FXML
    private void saveHandleAction(ActionEvent event) {
        
        DataValidation dataValidation = new DataValidation();
        if (dataValidation.notEmpty(txtFName)
                && dataValidation.notEmpty(txtLName)
                && dataValidation.notEmpty(txtEmail)
                && dataValidation.notEmpty(txtFee)
                && dataValidation.notEmpty(txtIndexNo)
                && dataValidation.notEmpty(txtPassword)
                && dataValidation.notEmpty(txtPhone)
                && dataValidation.notEmpty(txtStreet)
                && dataValidation.notEmpty(txtStreetNo)
                && dataValidation.notEmpty(txtUsername)
                && dataValidation.textAlphabet(txtFName)
                && dataValidation.textAlphabet(txtLName)
                && dataValidation.textAlphabet(txtStreet)
                && dataValidation.email(txtEmail)
                && dataValidation.indexNo(txtIndexNo)
                && dataValidation.phoneNo(txtPhone)) {
            try {

                UserJpaDao userJpaDao = new UserJpaDao();

                String username = txtUsername.getText();
                User user = userJpaDao.findByUsername(username);

                if (user == null) {

                    student.setIdUser(new User());
                } else {
                    student.setIdUser(user);
                    student.setId(currentID);
                }

                //User
                student.getIdUser().setFirstName(String.valueOf(txtFName.getText()));
                student.getIdUser().setLastName(String.valueOf(txtLName.getText()));
                student.getIdUser().setUsername(String.valueOf(txtUsername.getText()));
                student.getIdUser().setPassword(String.valueOf(txtPassword.getText()));
                Privilege privilege = new Privilege(1, "student");
                student.getIdUser().setIdPrivilege(privilege);

                //Student
                student.setEMail(String.valueOf(txtEmail.getText()));
                student.setIndexNo(String.valueOf(txtIndexNo.getText()));
                student.setPhoneNumber(String.valueOf(txtPhone.getText()));
                student.setGender(radioButtonLabel);
                student.setDateOfBirth(Date.valueOf(dateDoB.getValue()));
                student.setDateOfEntry(Date.valueOf(LocalDate.now()));
                student.setStudentFee(new BigDecimal(txtFee.getText()));

                //Street
                student.setIdStreet(new Street());
                student.getIdStreet().setStreet(String.valueOf(txtStreet.getText()));
                student.getIdStreet().setNumber(Integer.valueOf(txtStreetNo.getText()));

                student.getIdStreet().setIdCity(cityCB.getSelectionModel().getSelectedItem());
                student.getIdStreet().getIdCity().setIdCountry(countryCB.getSelectionModel().getSelectedItem());

                if (student.getId() != null) {
                    studentJpaDao.update(student);
                    int index = observableListStudents.indexOf(student);
                    observableListStudents.set(index, student);
                } else {
                    studentJpaDao.save(student);
                    observableListStudents.add(student);
                }

                clearInputs();
                displayStudents();

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    private void deleteHandleAction(ActionEvent event) {
        student = studentTable.getSelectionModel().getSelectedItem();
        if (student != null) {
            studentJpaDao.delete(student);
        }
        displayStudents();
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

    private void selectGender() {
        rbtnFemale.setOnAction(this::onGenderChange);
        rbtnMale.setOnAction(this::onGenderChange);
    }

    private void onGenderChange(ActionEvent event) {
        if (rbtnFemale.isSelected()) {
            radioButtonLabel = rbtnFemale.getText().toLowerCase();
        } else {
            radioButtonLabel = rbtnMale.getText().toLowerCase();
        }
    }

    private void clearInputs() {

        txtEmail.clear();
        txtFName.clear();
        txtUsername.clear();
        txtPassword.clear();
        dateDoB.setValue(null);
        cityCB.valueProperty().setValue(null);
        countryCB.valueProperty().setValue(null);
        txtFee.clear();
        txtIndexNo.clear();
        txtLName.clear();
        txtPhone.clear();
        txtStreet.clear();
        txtStreetNo.clear();
        txtZipCode.clear();
    }

    @FXML
    private void sendToGrid(ActionEvent event) {
        student = studentTable.getSelectionModel().getSelectedItem();

        this.currentID = student.getId();
        this.txtFName.setText(student.getIdUser().getFirstName());
        this.txtLName.setText(student.getIdUser().getLastName());
        this.txtUsername.setText(student.getIdUser().getUsername());
        this.txtUsername.setDisable(true);
        this.txtPassword.setText(student.getIdUser().getPassword());
        this.txtPhone.setText(student.getPhoneNumber());
        if ("male".equals(student.getGender())) {
            rbtnMale.setSelected(true);
        }else rbtnFemale.setSelected(true);
        this.txtIndexNo.setText(student.getIndexNo());
        this.dateDoB.setValue(student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.txtEmail.setText(student.getEMail());
        this.txtFee.setText(student.getStudentFee().toString());
        this.txtStreet.setText(student.getIdStreet().getStreet());
        this.txtStreetNo.setText(Integer.toString(student.getIdStreet().getNumber()));
        this.cityCB.getSelectionModel().select(student.getIdStreet().getIdCity());
        this.countryCB.getSelectionModel().select(student.getIdStreet().getIdCity().getIdCountry());
        this.txtZipCode.setText(Integer.toString(student.getIdStreet().getIdCity().getZIPcode()));
        this.txtZipCode.setDisable(true);
        this.countryCB.setUserData(student.getIdStreet().getIdCity().getIdCountry().getCountry());

        tabPane.getSelectionModel().select(addNew);
        btnSave.setVisible(false);
        btnUpdate.setVisible(true);
    }

    @FXML
    private void studentSelected(MouseEvent event) {
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);

    }
}
