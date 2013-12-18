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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "cbr_case_x_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrCaseXCategory.findAll", query = "SELECT c FROM CbrCaseXCategory c"),
    @NamedQuery(name = "CbrCaseXCategory.findById", query = "SELECT c FROM CbrCaseXCategory c WHERE c.id = :id"),
    @NamedQuery(name = "CbrCaseXCategory.findByWeight", query = "SELECT c FROM CbrCaseXCategory c WHERE c.weight = :weight")})
public class CbrCaseXCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private double weight;
    @JoinColumn(name = "fk_category", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrCategory fkCategory;
    @JoinColumn(name = "fk_case", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrCase fkCase;

    public CbrCaseXCategory() {
    }

    public CbrCaseXCategory(Integer id) {
        this.id = id;
    }

    public CbrCaseXCategory(Integer id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CbrCategory getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(CbrCategory fkCategory) {
        this.fkCategory = fkCategory;
    }

    public CbrCase getFkCase() {
        return fkCase;
    }

    public void setFkCase(CbrCase fkCase) {
        this.fkCase = fkCase;
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
        if (!(object instanceof CbrCaseXCategory)) {
            return false;
        }
        CbrCaseXCategory other = (CbrCaseXCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrCaseXCategory[ id=" + id + " ]";
    }
    
}
