package com.firecode.app.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "event_participation", catalog = "grupowl", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_employee"}),
    @UniqueConstraint(columnNames = {"id"})})

public class EventParticipationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "filter", nullable = false, length = 2147483647)
    private String filter;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idEventParticipation" )
    private EventGuestEntity eventGuestEntity;

    @JoinColumn(name = "id_employee", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private EmployeeEntity idEmployee;

    @JoinColumn(name = "id_event", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EventEntity idEvent;

    @JoinColumn(name = "id_rate", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RateEntity idRate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idEventParticipation")
    private RecipeEntity recipeEntity;

    public EventParticipationEntity() {
    }

    public EventParticipationEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public EventGuestEntity getEventGuestEntity() {
        return eventGuestEntity;
    }

    public void setEventGuestEntity(EventGuestEntity eventGuestEntity) {
        this.eventGuestEntity = eventGuestEntity;
    }

    public EmployeeEntity getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(EmployeeEntity idEmployee) {
        this.idEmployee = idEmployee;
    }

    public EventEntity getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(EventEntity idEvent) {
        this.idEvent = idEvent;
    }

    public RateEntity getIdRate() {
        return idRate;
    }

    public void setIdRate(RateEntity idRate) {
        this.idRate = idRate;
    }

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
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
        if (!(object instanceof EventParticipationEntity)) {
            return false;
        }
        EventParticipationEntity other = (EventParticipationEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.EventParticipationEntity[ id=" + id + " ]";
    }

}
