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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cbr_day")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CbrDay.findAll", query = "SELECT c FROM CbrDay c"),
    @NamedQuery(name = "CbrDay.findById", query = "SELECT c FROM CbrDay c WHERE c.id = :id"),
    @NamedQuery(name = "CbrDay.findByCase", query = "SELECT c FROM CbrDay c WHERE c.fkCase = :case ORDER BY c.position"),
    @NamedQuery(name = "CbrDay.findByStartingTime", query = "SELECT c FROM CbrDay c WHERE c.startingTime = :startingTime"),
    @NamedQuery(name = "CbrDay.findByPosition", query = "SELECT c FROM CbrDay c WHERE c.position = :position")})
public class CbrDay implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDay", fetch = FetchType.LAZY)
    private List<CbrDayXAttraction> cbrDayXAttractionList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "starting_time")
    @Temporal(TemporalType.TIME)
    private Date startingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "position")
    private int position;
    @JoinColumn(name = "fk_case", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CbrCase fkCase;

    public CbrDay() {
    }

    public CbrDay(Integer id) {
        this.id = id;
    }

    public CbrDay(Integer id, int position) {
        this.id = id;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
        if (!(object instanceof CbrDay)) {
            return false;
        }
        CbrDay other = (CbrDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.travelling.entity.CbrDay[ id=" + id + " ]";
    }

    @XmlTransient
    public List<CbrDayXAttraction> getCbrDayXAttractionList() {
        return cbrDayXAttractionList;
    }

    public void setCbrDayXAttractionList(List<CbrDayXAttraction> cbrDayXAttractionList) {
        this.cbrDayXAttractionList = cbrDayXAttractionList;
    }
    
}
