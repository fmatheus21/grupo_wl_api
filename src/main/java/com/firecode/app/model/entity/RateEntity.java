package com.firecode.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rate", catalog = "grupowl", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"}),
    @UniqueConstraint(columnNames = {"id"})})

public class RateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "value", nullable = false, precision = 8, scale = 2)
    private BigDecimal value;

    @Basic(optional = false)
    @NotNull
    @Column(name = "employee", nullable = false)
    private boolean employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRate")
    private Collection<EventParticipationEntity> eventParticipationEntityCollection;

    public RateEntity() {
    }

    public RateEntity(Integer id) {
        this.id = id;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public Collection<EventParticipationEntity> getEventParticipationEntityCollection() {
        return eventParticipationEntityCollection;
    }

    public void setEventParticipationEntityCollection(Collection<EventParticipationEntity> eventParticipationEntityCollection) {
        this.eventParticipationEntityCollection = eventParticipationEntityCollection;
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
        if (!(object instanceof RateEntity)) {
            return false;
        }
        RateEntity other = (RateEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.RateEntity[ id=" + id + " ]";
    }

}
