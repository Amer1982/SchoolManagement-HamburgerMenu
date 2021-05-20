/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bnc
 */
@Entity
@Table(name = "finance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finance.findAll", query = "SELECT f FROM Finance f"),
    @NamedQuery(name = "Finance.findById", query = "SELECT f FROM Finance f WHERE f.id = :id"),
    @NamedQuery(name = "Finance.findByStudentFee", query = "SELECT f FROM Finance f WHERE f.studentFee = :studentFee"),
    @NamedQuery(name = "Finance.findByTeacherSalary", query = "SELECT f FROM Finance f WHERE f.teacherSalary = :teacherSalary"),
    @NamedQuery(name = "Finance.findByAdminSalary", query = "SELECT f FROM Finance f WHERE f.adminSalary = :adminSalary")})
public class Finance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "student_fee")
    private BigDecimal studentFee;
    @Basic(optional = false)
    @Column(name = "teacher_salary")
    private BigDecimal teacherSalary;
    @Basic(optional = false)
    @Column(name = "admin_salary")
    private BigDecimal adminSalary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFinance")
    private List<Teacher> teacherList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFinance")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFinance")
    private List<Admin> adminList;

    public Finance() {
    }

    public Finance(Integer id) {
        this.id = id;
    }

    public Finance(Integer id, BigDecimal studentFee, BigDecimal teacherSalary, BigDecimal adminSalary) {
        this.id = id;
        this.studentFee = studentFee;
        this.teacherSalary = teacherSalary;
        this.adminSalary = adminSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getStudentFee() {
        return studentFee;
    }

    public void setStudentFee(BigDecimal studentFee) {
        this.studentFee = studentFee;
    }

    public BigDecimal getTeacherSalary() {
        return teacherSalary;
    }

    public void setTeacherSalary(BigDecimal teacherSalary) {
        this.teacherSalary = teacherSalary;
    }

    public BigDecimal getAdminSalary() {
        return adminSalary;
    }

    public void setAdminSalary(BigDecimal adminSalary) {
        this.adminSalary = adminSalary;
    }

    @XmlTransient
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
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
        if (!(object instanceof Finance)) {
            return false;
        }
        Finance other = (Finance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "business.entity.Finance[ id=" + id + " ]";
    }
    
}
