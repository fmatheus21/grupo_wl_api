/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firecode.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fmatheus
 */
@Entity
@Table(name = "recipe", catalog = "grupowl", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"id_event_participation"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeEntity.findAll", query = "SELECT r FROM RecipeEntity r"),
    @NamedQuery(name = "RecipeEntity.findById", query = "SELECT r FROM RecipeEntity r WHERE r.id = :id"),
    @NamedQuery(name = "RecipeEntity.findByValueEmployee", query = "SELECT r FROM RecipeEntity r WHERE r.valueEmployee = :valueEmployee"),
    @NamedQuery(name = "RecipeEntity.findByValueGuest", query = "SELECT r FROM RecipeEntity r WHERE r.valueGuest = :valueGuest")})
public class RecipeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "value_employee", nullable = false, precision = 8, scale = 2)
    private BigDecimal valueEmployee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value_guest", nullable = false, precision = 8, scale = 2)
    private BigDecimal valueGuest;
    @JoinColumn(name = "id_event_participation", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private EventParticipationEntity idEventParticipation;

    public RecipeEntity() {
    }

    public RecipeEntity(Integer id) {
        this.id = id;
    }

    public RecipeEntity(Integer id, BigDecimal valueEmployee, BigDecimal valueGuest) {
        this.id = id;
        this.valueEmployee = valueEmployee;
        this.valueGuest = valueGuest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValueEmployee() {
        return valueEmployee;
    }

    public void setValueEmployee(BigDecimal valueEmployee) {
        this.valueEmployee = valueEmployee;
    }

    public BigDecimal getValueGuest() {
        return valueGuest;
    }

    public void setValueGuest(BigDecimal valueGuest) {
        this.valueGuest = valueGuest;
    }

    public EventParticipationEntity getIdEventParticipation() {
        return idEventParticipation;
    }

    public void setIdEventParticipation(EventParticipationEntity idEventParticipation) {
        this.idEventParticipation = idEventParticipation;
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
        if (!(object instanceof RecipeEntity)) {
            return false;
        }
        RecipeEntity other = (RecipeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.RecipeEntity[ id=" + id + " ]";
    }
    
}
