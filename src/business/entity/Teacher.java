/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bnc
 */
@Entity
@Table(name = "teacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher.findById", query = "SELECT t FROM Teacher t WHERE t.id = :id"),
    @NamedQuery(name = "Teacher.findByPhoneNumber", query = "SELECT t FROM Teacher t WHERE t.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Teacher.findByEMail", query = "SELECT t FROM Teacher t WHERE t.eMail = :eMail"),
    @NamedQuery(name = "Teacher.findByDateOfBirth", query = "SELECT t FROM Teacher t WHERE t.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Teacher.findByTeacherSalary", query = "SELECT t FROM Teacher t WHERE t.teacherSalary = :teacherSalary")})
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "teacher_salary")
    private BigDecimal teacherSalary;
    
    @ManyToMany(mappedBy = "teacherSet")
    //private List<Subject> subjectList;
    private Set<Subject> subjectSet;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User idUser;
    @JoinColumn(name = "id_street", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Street idStreet;
   
    public Teacher() {
    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getTeacherSalary() {
        return teacherSalary;
    }

    public void setTeacherSalary(BigDecimal teacherSalary) {
        this.teacherSalary = teacherSalary;
    }


    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    /*@XmlTransient
    public List<Subject> getSubjectList() {
    return subjectList;
    }
    public void setSubjectList(List<Subject> subjectList) {
    this.subjectList = subjectList;
    }*/
    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Street getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(Street idStreet) {
        this.idStreet = idStreet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "prof. " + getIdUser().getFirstName() + " " + getIdUser().getLastName();
    }

}
