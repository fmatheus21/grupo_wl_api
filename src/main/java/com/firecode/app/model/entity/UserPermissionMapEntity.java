package com.firecode.app.model.entity;

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

@Entity
@Table(name = "user_permission_map", catalog = "grupowl", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_user"}),
    @UniqueConstraint(columnNames = {"id"})})

public class UserPermissionMapEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "id_permission", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PermissionEntity idPermission;

    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private UserEntity idUser;

    public UserPermissionMapEntity() {
    }

    public UserPermissionMapEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PermissionEntity getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(PermissionEntity idPermission) {
        this.idPermission = idPermission;
    }

    public UserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof UserPermissionMapEntity)) {
            return false;
        }
        UserPermissionMapEntity other = (UserPermissionMapEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.UserPermissionMapEntity[ id=" + id + " ]";
    }

}
