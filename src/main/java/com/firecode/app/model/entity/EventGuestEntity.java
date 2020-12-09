package com.firecode.app.model.entity;

import com.firecode.app.controller.util.AppUtil;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "event_guest", catalog = "grupowl", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"document"}),
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"id_event_participation"})})

public class EventGuestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "name_guest", nullable = false, length = 70)
    private String nameGuest;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "document", nullable = false, length = 15)
    private String document;

    @JoinColumn(name = "id_event_participation", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private EventParticipationEntity idEventParticipation;

    @JoinColumn(name = "id_rate", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RateEntity idRate;

    public EventGuestEntity() {
    }

    public EventGuestEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameGuest() {
        if (nameGuest != null) {
            return AppUtil.convertFirstUppercaseCharacter(nameGuest);
        }
        return nameGuest;
    }

    public void setNameGuest(String nameGuest) {
        this.nameGuest = AppUtil.convertAllUppercaseCharacters(nameGuest);
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = AppUtil.removeSpecialCharacters(document);
    }

    public EventParticipationEntity getIdEventParticipation() {
        return idEventParticipation;
    }

    public void setIdEventParticipation(EventParticipationEntity idEventParticipation) {
        this.idEventParticipation = idEventParticipation;
    }

    public RateEntity getIdRate() {
        return idRate;
    }

    public void setIdRate(RateEntity idRate) {
        this.idRate = idRate;
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
        if (!(object instanceof EventGuestEntity)) {
            return false;
        }
        EventGuestEntity other = (EventGuestEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.EventGuestEntity[ id=" + id + " ]";
    }

}
