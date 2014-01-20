/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "cbr_case")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrCase.findAll", query = "SELECT c FROM CbrCase c"),
    @NamedQuery(name = "CbrCase.findById", query = "SELECT c FROM CbrCase c WHERE c.id = :id"),
    @NamedQuery(name = "CbrCase.findByStartTime", query = "SELECT c FROM CbrCase c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "CbrCase.findByEndTime", query = "SELECT c FROM CbrCase c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "CbrCase.findByMoney", query = "SELECT c FROM CbrCase c WHERE c.money = :money")})
public class CbrCase implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "success_ratio")
    private double successRatio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "days")
    private int days;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCase", fetch = FetchType.LAZY)
    private List<CbrDay> cbrDayList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCase", fetch = FetchType.LAZY)
    private List<CbrCaseXCategory> cbrCaseXCategoryList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "money")
    private Integer money;

    public CbrCase() {
    }

    public CbrCase(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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
        if (!(object instanceof CbrCase)) {
            return false;
        }
        CbrCase other = (CbrCase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrCase[ id=" + id + " ]";
    }

    @XmlTransient
    public List<CbrCaseXCategory> getCbrCaseXCategoryList() {
        return cbrCaseXCategoryList;
    }

    public void setCbrCaseXCategoryList(List<CbrCaseXCategory> cbrCaseXCategoryList) {
        this.cbrCaseXCategoryList = cbrCaseXCategoryList;
    }

    @XmlTransient
    public List<CbrDay> getCbrDayList() {
        return cbrDayList;
    }

    public void setCbrDayList(List<CbrDay> cbrDayList) {
        this.cbrDayList = cbrDayList;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(double successRatio) {
        this.successRatio = successRatio;
    }
    
}
