/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
        @Table(name = "admin")
        @XmlRootElement
        @NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findById", query = "SELECT a FROM Admin a WHERE a.id = :id"),
    @NamedQuery(name = "Admin.findByPhoneNumber", query = "SELECT a FROM Admin a WHERE a.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Admin.findByEMail", query = "SELECT a FROM Admin a WHERE a.eMail = :eMail"),
    @NamedQuery(name = "Admin.findByAdminSalary", query = "SELECT a FROM Admin a WHERE a.adminSalary = :adminSalary"),
    @NamedQuery(name = "Admin.findByAdditionalInfo", query = "SELECT a FROM Admin a WHERE a.additionalInfo = :additionalInfo"),
    @NamedQuery(name = "Admin.salary", query = "SELECT SUM(a.adminSalary) FROM Admin a")})

public class Admin implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "admin_salary")
    private BigDecimal adminSalary;
    @Column(name = "additional_info")
    private String additionalInfo;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User idUser;
    @JoinColumn(name = "id_street", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Street idStreet;

    public Admin() {
    }

    public Admin(Integer id) {
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

    public BigDecimal getAdminSalary() {
        return adminSalary;
    }

    public void setAdminSalary(BigDecimal adminSalary) {
        this.adminSalary = adminSalary;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.entity.Admin[ id=" + id + " ]";
    }

}
