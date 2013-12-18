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
@Table(name = "cbr_case_attraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrCaseAttraction.findAll", query = "SELECT c FROM CbrCaseAttraction c"),
    @NamedQuery(name = "CbrCaseAttraction.findById", query = "SELECT c FROM CbrCaseAttraction c WHERE c.id = :id"),
    @NamedQuery(name = "CbrCaseAttraction.findByPosition", query = "SELECT c FROM CbrCaseAttraction c WHERE c.position = :position"),
    @NamedQuery(name = "CbrCaseAttraction.findByTimeMinutes", query = "SELECT c FROM CbrCaseAttraction c WHERE c.timeMinutes = :timeMinutes")})
public class CbrCaseAttraction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "position")
    private int position;
    @Column(name = "time_minutes")
    private Integer timeMinutes;
    @JoinColumn(name = "fk_case", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrCase fkCase;
    @JoinColumn(name = "fk_attraction", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrAttraction fkAttraction;

    public CbrCaseAttraction() {
    }

    public CbrCaseAttraction(Integer id) {
        this.id = id;
    }

    public CbrCaseAttraction(Integer id, int position) {
        this.id = id;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(Integer timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public CbrCase getFkCase() {
        return fkCase;
    }

    public void setFkCase(CbrCase fkCase) {
        this.fkCase = fkCase;
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
        if (!(object instanceof CbrCaseAttraction)) {
            return false;
        }
        CbrCaseAttraction other = (CbrCaseAttraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrCaseAttraction[ id=" + id + " ]";
    }
    
}
