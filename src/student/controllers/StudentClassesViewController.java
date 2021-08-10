/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.controllers;

import business.dao.AdminJpaDao;
import business.dao.SubjectJpaDao;
import business.entity.Admin;
import business.entity.Subject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
public class StudentClassesViewController implements Initializable {
    @FXML
    private TableView<Subject> subjectTable;
    @FXML
    private TableColumn<Subject, String> col_subject;
    @FXML
    private TableColumn<Subject, String> col_asgnTeacher;
    @FXML
    private TableColumn<Subject, Integer> col_grade;
    @FXML
    private TableColumn<Subject, Integer> col_1stSemester;
    @FXML
    private TableColumn<Subject, Integer> col_2ndSemester;
    @FXML
    private TableColumn<Subject, String> col_remarks;
    
    private ObservableList<Subject> observableListSubject = FXCollections.observableArrayList();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displaySubjects();
        
    }
        private void displaySubjects(){
            
        List<Subject> subjectList = new SubjectJpaDao().getAll();
               
        observableListSubject=FXCollections.observableArrayList(subjectList);
        
        subjectTable.setItems(observableListSubject);   
        
        col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        
        col_asgnTeacher.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getTeacherList().toString());
        });
  
        }
}
