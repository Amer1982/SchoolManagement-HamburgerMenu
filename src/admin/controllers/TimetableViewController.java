/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author bnc
 */
public class TimetableViewController implements Initializable {

    @FXML
    private JFXComboBox<?> cmbbSelectClass;
    @FXML
    private DatePicker btnDate;
    @FXML
    private DatePicker btnTime;
    @FXML
    private JFXButton btnScheduleClass;
    @FXML
    private Pane calendarPane;
    
    
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       displayCalendar();
    }   
    private void displayCalendar (){
        /* izbacuje mi gresku sa verzijom
        CalendarView calendarView = new CalendarView();
        
            Calendar teacher = new Calendar("Teacher");
            Calendar student = new Calendar("Student");
            
            teacher.setStyle(Calendar.Style.STYLE1);
            student.setStyle(Calendar.Style.STYLE2);
            
            calendarView.setRequestedTime(LocalTime.now());
            
            Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                        @Override
                        public void run() {
                                while (true) {
                                        Platform.runLater(() -> {
                                                calendarView.setToday(LocalDate.now());
                                                calendarView.setTime(LocalTime.now());
                                        });

                                        try {
                                                // update every 10 seconds
                                                sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }

                                }
                        };
                };

                updateTimeThread.setPriority(Thread.MIN_PRIORITY);
                updateTimeThread.setDaemon(true);
                updateTimeThread.start();
        
                calendarPane.getChildren().add(calendarView);*/
    }    
}
