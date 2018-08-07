package edu.msg.ro.persistence.user.dao;

import edu.msg.ro.persistence.user.entity.Permission;
import edu.msg.ro.persistence.user.entity.Role;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless(name = "PermissionManagementImpl", mappedName = "PermissionManagementImpl")
public class PermissionManagementImpl implements PermissionManagement {

    private static final Logger logger = LogManager.getLogger(PermissionManagementImpl.class);

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Permission addPermission(Permission permission) {
        logger.log(Level.ERROR,"aaaaaaaaaaa");
        em.persist(permission);
        return permission;
    }

    @Override
    public Permission updatePermission(Permission permission) {
        em.merge(permission);
        return permission;
    }

    @Override
    public boolean removePermissionById(long id) {
        Permission permission = getPermissionForId(id);
        if(permission == null)
            return false;
        em.remove(permission);
        return true;
    }

    @Override
    public boolean removePermissionForRole(Role role, Permission permission) {
        em.persist(role);
        List<Permission> permissions = getPermissionsForRole(role);
        return permissions.remove(permission);

    }

    @Override
    public boolean removeAllPermissionsForRole(Role role) {
        em.persist(role);
        List<Permission> permissions = getPermissionsForRole(role);
        permissions.clear();
        return true;
    }

    @Override
    public Permission getPermissionForId(long id) {
        Query query = em.createQuery("SELECT p FROM Permission p WHERE p.id=:id");
        query.setParameter("id",id);
        return (Permission) query.getSingleResult();
    }

    @Override
    public List<Permission> getPermissionsForRole(Role role) {
        Query query = em.createQuery("SELECT r.permissions FROM Role r WHERE r=:role");
        query.setParameter("role",role);
        return query.getResultList();

    }

    @Override
    public List<Permission> getAllPermissions() {
        Query query = em.createQuery("SELECT p FROM Permission p");
        return query.getResultList();
    }

    @Override
    public Permission createPermissionForRole(Role role, Permission permission) {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        role.setPermissions(permissions);
        em.merge(role);
        return permission;
    }
}
