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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "cbr_attraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrAttraction.findAll", query = "SELECT c FROM CbrAttraction c"),
    @NamedQuery(name = "CbrAttraction.findById", query = "SELECT c FROM CbrAttraction c WHERE c.id = :id"),
    @NamedQuery(name = "CbrAttraction.findByName", query = "SELECT c FROM CbrAttraction c WHERE c.name = :name"),
    @NamedQuery(name = "CbrAttraction.findByVisitDuration", query = "SELECT c FROM CbrAttraction c WHERE c.visitDuration = :visitDuration"),
    @NamedQuery(name = "CbrAttraction.findByVisitCost", query = "SELECT c FROM CbrAttraction c WHERE c.visitCost = :visitCost"),
    @NamedQuery(name = "CbrAttraction.findByOpeningTime", query = "SELECT c FROM CbrAttraction c WHERE c.openingTime = :openingTime"),
    @NamedQuery(name = "CbrAttraction.findByClosingTime", query = "SELECT c FROM CbrAttraction c WHERE c.closingTime = :closingTime")})
public class CbrAttraction implements Serializable {
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAttraction2", fetch = FetchType.LAZY)
    private List<CbrAttractionXAttraction> cbrAttractionXAttractionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAttraction1", fetch = FetchType.LAZY)
    private List<CbrAttractionXAttraction> cbrAttractionXAttractionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAttraction", fetch = FetchType.LAZY)
    private List<CbrDayXAttraction> cbrDayXAttractionList;
    @Column(name = "visit_duration")
    private Integer visitDuration;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "visit_cost")
    private Double visitCost;
    @Column(name = "opening_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingTime;
    @Column(name = "closing_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAttraction", fetch = FetchType.LAZY)
    private List<CbrAttractionXCategory> cbrAttractionXCategoryList;

    public CbrAttraction() {
    }

    public CbrAttraction(Integer id) {
        this.id = id;
    }

    public CbrAttraction(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVisitCost() {
        return visitCost;
    }

    public void setVisitCost(Double visitCost) {
        this.visitCost = visitCost;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    @XmlTransient
    public List<CbrAttractionXCategory> getCbrAttractionXCategoryList() {
        return cbrAttractionXCategoryList;
    }

    public void setCbrAttractionXCategoryList(List<CbrAttractionXCategory> cbrAttractionXCategoryList) {
        this.cbrAttractionXCategoryList = cbrAttractionXCategoryList;
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
        if (!(object instanceof CbrAttraction)) {
            return false;
        }
        CbrAttraction other = (CbrAttraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrAttraction[ id=" + id + " ]";
    }

    public Integer getVisitDuration() {
        return visitDuration;
    }

    public void setVisitDuration(Integer visitDuration) {
        this.visitDuration = visitDuration;
    }

    @XmlTransient
    public List<CbrDayXAttraction> getCbrDayXAttractionList() {
        return cbrDayXAttractionList;
    }

    public void setCbrDayXAttractionList(List<CbrDayXAttraction> cbrDayXAttractionList) {
        this.cbrDayXAttractionList = cbrDayXAttractionList;
    }

    @XmlTransient
    public List<CbrAttractionXAttraction> getCbrAttractionXAttractionList() {
        return cbrAttractionXAttractionList;
    }

    public void setCbrAttractionXAttractionList(List<CbrAttractionXAttraction> cbrAttractionXAttractionList) {
        this.cbrAttractionXAttractionList = cbrAttractionXAttractionList;
    }

    @XmlTransient
    public List<CbrAttractionXAttraction> getCbrAttractionXAttractionList1() {
        return cbrAttractionXAttractionList1;
    }

    public void setCbrAttractionXAttractionList1(List<CbrAttractionXAttraction> cbrAttractionXAttractionList1) {
        this.cbrAttractionXAttractionList1 = cbrAttractionXAttractionList1;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
}
