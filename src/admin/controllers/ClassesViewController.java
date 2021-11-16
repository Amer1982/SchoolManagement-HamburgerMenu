/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.SubjectJpaDao;
import business.dao.TeacherJpaDao;
import business.entity.Subject;
import business.entity.Teacher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class ClassesViewController implements Initializable {

    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField txtSubject;
    @FXML
    private JFXTextField txtAbbreviation;
    @FXML
    private ChoiceBox<Teacher> cbTeachers;
    @FXML
    private TableView<Subject> subjectTable;
    @FXML
    private TableColumn<Subject, String> col_abbrevation;
    @FXML
    private TableColumn<Subject, String> col_subject;
    @FXML
    private TableColumn<Subject, String> col_assignedTchr;
    
    private int currentID;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Subject> observableListClasses = FXCollections.observableArrayList();
    Subject subject = new Subject();
    SubjectJpaDao subjectJpaDao = new SubjectJpaDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displaySubjects();
        teacherChoiceBox();

        btnSave.setOnAction(this::saveOrUpdateHandleAction);
        btnUpdate.setOnAction(this::saveOrUpdateHandleAction);
        btnEdit.setOnAction(this::sendToGrid);
        btnDelete.setOnAction(this::deleteHandleAction);

    }

    private void displaySubjects() {

        List<Subject> subjectList = new SubjectJpaDao().getAll();

        observableListClasses = FXCollections.observableArrayList(subjectList);

        subjectTable.setItems(observableListClasses);

        col_abbrevation.setCellValueFactory(new PropertyValueFactory<>("abbreviation"));
        col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        //
        col_assignedTchr.setCellValueFactory(cellData -> {
            String teachers = new String();
            for (Teacher teacher : cellData.getValue().getTeacherSet()) {
                teachers += teacher.getIdUser().getFirstName() + " " + teacher.getIdUser().getLastName() + ", ";
            }
            return new SimpleObjectProperty<>(teachers);
        });
    }

    @FXML
    private void saveOrUpdateHandleAction(ActionEvent event) {
        
        String subjectName = txtSubject.getText();
        Subject subject= subjectJpaDao.findBySubject(subjectName);
        
        if (subject == null) {
                    subject=new Subject();
                } else {
                    subject.setSubject(subjectName);
                    subject.setId(currentID);
                }

        subject.setSubject(txtSubject.getText());
        subject.setAbbreviation(txtAbbreviation.getText());
        subject.getTeacherSet().add(cbTeachers.getValue());
        
        if (subject.getId()!=null) {
                subjectJpaDao.update(subject);
                int index = observableListClasses.indexOf(subject);
                observableListClasses.set(index, subject);
            
        } else {
            subjectJpaDao.save(subject);
            observableListClasses.addAll(subject);
        }
        
        clearInputs();
        displaySubjects();
    }          

    @FXML
    private void deleteHandleAction(ActionEvent event) {
        subject = subjectTable.getSelectionModel().getSelectedItem();
        if (subject != null) {

            subjectJpaDao.delete(subject);
        }
        displaySubjects();
    }

    private void teacherChoiceBox() {
        List<Teacher> teachers = new TeacherJpaDao().getAll();
        ObservableList<Teacher> observableListTeachers = FXCollections.observableArrayList(teachers);
        cbTeachers.setItems(observableListTeachers);

        Platform.runLater(() -> {
            SkinBase<ChoiceBox<String>> skin = (SkinBase<ChoiceBox<String>>) cbTeachers.getSkin();
            for (Node child : skin.getChildren()) {
                if (child instanceof Label) {
                    Label label = (Label) child;
                    if (label.getText().isEmpty()) {
                        label.setText("Assign teacher");
                    }
                    return;
                }
            }
        });
    }

    private void clearInputs() {
        txtSubject.clear();
        txtAbbreviation.clear();
        cbTeachers.valueProperty().setValue(null);
    }

    @FXML
    private void sendToGrid(ActionEvent event) {
        
        subject = subjectTable.getSelectionModel().getSelectedItem();
        
        this.currentID = subject.getId();
        this.txtSubject.setText(subject.getSubject());
        this.txtAbbreviation.setText(subject.getAbbreviation());
        
    }
}
