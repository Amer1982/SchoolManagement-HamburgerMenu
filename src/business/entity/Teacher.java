/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Teacher.findByUsername", query = "SELECT t FROM Teacher t WHERE t.username = :username"),
    @NamedQuery(name = "Teacher.findByPassword", query = "SELECT t FROM Teacher t WHERE t.password = :password"),
    @NamedQuery(name = "Teacher.findByFirstName", query = "SELECT t FROM Teacher t WHERE t.firstName = :firstName"),
    @NamedQuery(name = "Teacher.findByLastName", query = "SELECT t FROM Teacher t WHERE t.lastName = :lastName"),
    @NamedQuery(name = "Teacher.findByPhoneNumber", query = "SELECT t FROM Teacher t WHERE t.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Teacher.findByEMail", query = "SELECT t FROM Teacher t WHERE t.eMail = :eMail")})
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Column(name = "e_mail")
    private String eMail;
    @JoinColumn(name = "id_finance", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Finance idFinance;
    @JoinColumn(name = "id_privilege", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Privilege idPrivilege;
    @JoinColumn(name = "id_street", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Street idStreet;

    public Teacher() {
    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public Teacher(Integer id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
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
        return "business.entity.Teacher[ id=" + id + " ]";
    }
    
}
