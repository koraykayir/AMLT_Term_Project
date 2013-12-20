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
@Table(name = "cbr_day_x_attraction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrDayXAttraction.findAll", query = "SELECT c FROM CbrDayXAttraction c"),
    @NamedQuery(name = "CbrDayXAttraction.findById", query = "SELECT c FROM CbrDayXAttraction c WHERE c.id = :id"),
    @NamedQuery(name = "CbrDayXAttraction.findByDay", query = "SELECT c FROM CbrDayXAttraction c WHERE c.fkDay = :day ORDER BY c.position"),
    @NamedQuery(name = "CbrDayXAttraction.findByPosition", query = "SELECT c FROM CbrDayXAttraction c WHERE c.position = :position")})
public class CbrDayXAttraction implements Serializable {
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
    @JoinColumn(name = "fk_day", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrDay fkDay;
    @JoinColumn(name = "fk_attraction", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrAttraction fkAttraction;

    public CbrDayXAttraction() {
    }

    public CbrDayXAttraction(Integer id) {
        this.id = id;
    }

    public CbrDayXAttraction(Integer id, int position) {
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

    public CbrDay getFkDay() {
        return fkDay;
    }

    public void setFkDay(CbrDay fkDay) {
        this.fkDay = fkDay;
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
        if (!(object instanceof CbrDayXAttraction)) {
            return false;
        }
        CbrDayXAttraction other = (CbrDayXAttraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrDayXAttraction[ id=" + id + " ]";
    }
    
}
