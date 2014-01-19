/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Koray
 */
@Entity
@Table(name = "cbr_attraction_x_attraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrAttractionXAttraction.findAll", query = "SELECT c FROM CbrAttractionXAttraction c"),
    @NamedQuery(name = "CbrAttractionXAttraction.findById", query = "SELECT c FROM CbrAttractionXAttraction c WHERE c.id = :id"),
    @NamedQuery(name = "CbrAttractionXAttraction.findByBusTime", query = "SELECT c FROM CbrAttractionXAttraction c WHERE c.busTime = :busTime")})
//   @NamedQuery(name = "CbrAttractionXAttraction.CbrGetSpecificAtt", query = "SELECT * FROM cbr_attraction_x_Attraction WHERE fk_attraction1 = :id1 AND fk_attraction2 = :id2")
public class CbrAttractionXAttraction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bus_time")
    private int busTime;
    @JoinColumn(name = "fk_attraction2", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrAttraction fkAttraction2;
    @JoinColumn(name = "fk_attraction1", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrAttraction fkAttraction1;

    public CbrAttractionXAttraction() {
    }

    public CbrAttractionXAttraction(Integer id) {
        this.id = id;
    }

    public CbrAttractionXAttraction(Integer id, int busTime) {
        this.id = id;
        this.busTime = busTime;
    }
    
    
    public CbrAttractionXAttraction(CbrAttraction fk1, CbrAttraction fk2) {
        this.fkAttraction1 = fk1;
        this.fkAttraction2 = fk2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBusTime() {
        return busTime;
    }

    public void setBusTime(int busTime) {
        this.busTime = busTime;
    }

    public CbrAttraction getFkAttraction2() {
        return fkAttraction2;
    }

    public void setFkAttraction2(CbrAttraction fkAttraction2) {
        this.fkAttraction2 = fkAttraction2;
    }

    public CbrAttraction getFkAttraction1() {
        return fkAttraction1;
    }

    public void setFkAttraction1(CbrAttraction fkAttraction1) {
        this.fkAttraction1 = fkAttraction1;
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
        if (!(object instanceof CbrAttractionXAttraction)) {
            return false;
        }
        CbrAttractionXAttraction other = (CbrAttractionXAttraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrAttractionXAttraction[ id=" + id + " ]";
    }
    
}
