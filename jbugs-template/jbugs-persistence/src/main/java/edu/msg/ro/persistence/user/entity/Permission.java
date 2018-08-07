package edu.msg.ro.persistence.user.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity<Long>{

    @Transient
    private final static int MAX_STRING_LENGTH = 20;

    @Column(name = "type", nullable = false, length = MAX_STRING_LENGTH, unique = true)
    private String type;

    @Column(name = "description")
    private String description;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "pid"),
            inverseJoinColumns = { @JoinColumn(name = "rid")})
    private List<Role> roles;

    public Permission() {
    }

    public Permission(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Permission(String type, String description, List<Role> roles) {
        this.type = type;
        this.description = description;
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Permission that = (Permission) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(description, that.description) &&
                Objects.equals(id,that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), type, description);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
