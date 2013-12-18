/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Stefan
 */
@Entity
@Table(name = "cbr_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrCategory.findAll", query = "SELECT c FROM CbrCategory c"),
    @NamedQuery(name = "CbrCategory.findById", query = "SELECT c FROM CbrCategory c WHERE c.id = :id"),
    @NamedQuery(name = "CbrCategory.findByName", query = "SELECT c FROM CbrCategory c WHERE c.name = :name")})
public class CbrCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "fkParent", fetch = FetchType.LAZY)
    private List<CbrCategory> cbrCategoryList;
    @JoinColumn(name = "fk_parent", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CbrCategory fkParent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCategory", fetch = FetchType.LAZY)
    private List<CbrCaseXCategory> cbrCaseXCategoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCategory", fetch = FetchType.LAZY)
    private List<CbrAttractionXCategory> cbrAttractionXCategoryList;

    public CbrCategory() {
    }

    public CbrCategory(Integer id) {
        this.id = id;
    }

    public CbrCategory(Integer id, String name) {
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

    @XmlTransient
    public List<CbrCategory> getCbrCategoryList() {
        return cbrCategoryList;
    }

    public void setCbrCategoryList(List<CbrCategory> cbrCategoryList) {
        this.cbrCategoryList = cbrCategoryList;
    }

    public CbrCategory getFkParent() {
        return fkParent;
    }

    public void setFkParent(CbrCategory fkParent) {
        this.fkParent = fkParent;
    }

    @XmlTransient
    public List<CbrCaseXCategory> getCbrCaseXCategoryList() {
        return cbrCaseXCategoryList;
    }

    public void setCbrCaseXCategoryList(List<CbrCaseXCategory> cbrCaseXCategoryList) {
        this.cbrCaseXCategoryList = cbrCaseXCategoryList;
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
        if (!(object instanceof CbrCategory)) {
            return false;
        }
        CbrCategory other = (CbrCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrCategory[ id=" + id + " ]";
    }
    
}
