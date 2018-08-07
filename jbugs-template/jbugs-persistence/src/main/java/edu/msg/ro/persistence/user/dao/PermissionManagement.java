package edu.msg.ro.persistence.user.dao;

import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface PermissionManagement extends Serializable {

    Permission addPermission(Permission permission);
    Permission updatePermission(Permission permission);
    boolean removePermissionById(long id);
    boolean removePermissionForRole(Role role, Permission permission);
    boolean removeAllPermissionsForRole(Role role);
    Permission getPermissionForId(long id);
    List<Permission> getPermissionsForRole(Role role);
    List<Permission> getAllPermissions();
    Permission createPermissionForRole(Role role, Permission permission);


}
