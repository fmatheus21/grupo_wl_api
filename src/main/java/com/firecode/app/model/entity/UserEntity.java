package com.firecode.app.model.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", catalog = "grupowl", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"}),
    @UniqueConstraint(columnNames = {"id_person_physical"}),
    @UniqueConstraint(columnNames = {"username"})})

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "password", nullable = false, length = 70)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUser")
    private UserPermissionMapEntity userPermissionMapEntity;

    @JoinColumn(name = "id_person_physical", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private PersonPhysicalEntity idPersonPhysical;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission_map", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private List<PermissionEntity> permissions;

    public UserEntity() {
    }

    public UserEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPermissionMapEntity getUserPermissionMapEntity() {
        return userPermissionMapEntity;
    }

    public void setUserPermissionMapEntity(UserPermissionMapEntity userPermissionMapEntity) {
        this.userPermissionMapEntity = userPermissionMapEntity;
    }

    public PersonPhysicalEntity getIdPersonPhysical() {
        return idPersonPhysical;
    }

    public void setIdPersonPhysical(PersonPhysicalEntity idPersonPhysical) {
        this.idPersonPhysical = idPersonPhysical;
    }

    public List<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEntity> permissions) {
        this.permissions = permissions;
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.firecode.app.model.entity.UserEntity[ id=" + id + " ]";
    }

}
