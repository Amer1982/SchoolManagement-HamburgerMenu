/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bnc
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByUsername", query = "SELECT s FROM Student s WHERE s.username = :username"),
    @NamedQuery(name = "Student.findByPassword", query = "SELECT s FROM Student s WHERE s.password = :password"),
    @NamedQuery(name = "Student.findByFirstName", query = "SELECT s FROM Student s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "Student.findByLastName", query = "SELECT s FROM Student s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "Student.findByIndexNo", query = "SELECT s FROM Student s WHERE s.indexNo = :indexNo"),
    @NamedQuery(name = "Student.findByPhoneNumber", query = "SELECT s FROM Student s WHERE s.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Student.findByGender", query = "SELECT s FROM Student s WHERE s.gender = :gender"),
    @NamedQuery(name = "Student.findByDateOfBirth", query = "SELECT s FROM Student s WHERE s.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Student.findByDateOfEntry", query = "SELECT s FROM Student s WHERE s.dateOfEntry = :dateOfEntry")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "index_no")
    private String indexNo;
    @Basic(optional = false)
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "date_of_entry")
    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;
    @JoinColumn(name = "id_finance", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Finance idFinance;
    @JoinColumn(name = "id_privilege", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Privilege idPrivilege;
    @JoinColumn(name = "id_street", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Street idStreet;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String firstName, String lastName, String indexNo, int phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNo = indexNo;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public Finance getIdFinance() {
        return idFinance;
    }

    public void setIdFinance(Finance idFinance) {
        this.idFinance = idFinance;
    }

    public Privilege getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(Privilege idPrivilege) {
        this.idPrivilege = idPrivilege;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.entity.Student[ id=" + id + " ]";
    }
    
}
