/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.SubjectJpaDao;
import business.entity.Subject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private JFXComboBox<?> cmbbAssignTeacher;
    @FXML
    private TableView<Subject> subjectTable;
    @FXML
    private TableColumn<Subject, String> col_abbrevation;
    @FXML
    private TableColumn<Subject, String> col_subject;
    @FXML
    private TableColumn<Subject, String> col_assignedTchr;
    @FXML
    private JFXTextField txtAbbreviation;

    /**
     * Initializes the controller class.
     */
     private ObservableList<Subject> observableListClasses = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displaySubjects();   
        
    } 
    
    private void displaySubjects(){
        List<Subject> subjectList = new SubjectJpaDao().getAll();
               
        observableListClasses=FXCollections.observableArrayList(subjectList);
        
        subjectTable.setItems(observableListClasses);
        
        col_abbrevation.setCellValueFactory(new PropertyValueFactory<>("abbreviation"));
        col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        col_assignedTchr.setCellValueFactory(new PropertyValueFactory<>("teacher"));     
        
        subjectTable.getColumns().addAll(col_abbrevation, col_subject, col_assignedTchr);
    }   
}
