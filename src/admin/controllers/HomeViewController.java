/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import business.dao.AdminJpaDao;
import static business.dao.JpaDao.ENTITY_MANAGER_FACTORY;
import business.dao.StudentJpaDao;
import business.dao.TeacherJpaDao;
import business.entity.Admin;
import business.entity.Student;
import business.entity.Teacher;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class HomeViewController implements Initializable {
    private final EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane MessageHolder;
    @FXML
    private Label lblStudentNo;
    @FXML
    private Label lblTeacherNo;
    @FXML
    private Label lblAdminNo;
    
    AdminJpaDao adminJpaDao= new AdminJpaDao();
    StudentJpaDao studentJpaDao= new StudentJpaDao();
    TeacherJpaDao teacherJpaDao= new TeacherJpaDao();
    Admin admin= new Admin();
    
    double Admin_salary = 2730;
    double Student_fee = 32158;
    double Teacher_salary = 4700;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initPieChart();
        showStudents();
        showTeachers();
        showAdmins();
    }

    private void initPieChart() {
        
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Admin salary", Admin_salary),
                new PieChart.Data("Student fee", Student_fee),
                new PieChart.Data("Teacher salary", Teacher_salary),
                new PieChart.Data("Profit", Student_fee - Admin_salary - Teacher_salary)
        );
        
        pieChart.setData(data);
    }
    
    private void showStudents(){
        Query query = entityManager.createQuery("SELECT s FROM Student s");
        List<Student> studentsList = query.getResultList();
        lblStudentNo.setText(String.valueOf(studentsList.size()));
    }
    
    private void showTeachers(){
        Query query = entityManager.createQuery("SELECT t FROM Teacher t");
        List<Teacher> teachersList = query.getResultList();
        lblTeacherNo.setText(String.valueOf(teachersList.size()));
    }
    
    private void showAdmins(){
        Query query = entityManager.createQuery("SELECT a FROM Admin a");
        List<Admin> adminsList = query.getResultList();
        lblAdminNo.setText(String.valueOf(adminsList.size()));
    }
    
}
