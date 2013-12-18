/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "cbr_attraction_x_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrAttractionXCategory.findAll", query = "SELECT c FROM CbrAttractionXCategory c"),
    @NamedQuery(name = "CbrAttractionXCategory.findById", query = "SELECT c FROM CbrAttractionXCategory c WHERE c.id = :id")})
public class CbrAttractionXCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "fk_category", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrCategory fkCategory;
    @JoinColumn(name = "fk_attraction", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrAttraction fkAttraction;

    public CbrAttractionXCategory() {
    }

    public CbrAttractionXCategory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CbrCategory getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(CbrCategory fkCategory) {
        this.fkCategory = fkCategory;
    }

    public CbrAttraction getFkAttraction() {
        return fkAttraction;
    }

    public void setFkAttraction(CbrAttraction fkAttraction) {
        this.fkAttraction = fkAttraction;
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
        if (!(object instanceof CbrAttractionXCategory)) {
            return false;
        }
        CbrAttractionXCategory other = (CbrAttractionXCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrAttractionXCategory[ id=" + id + " ]";
    }
    
}
