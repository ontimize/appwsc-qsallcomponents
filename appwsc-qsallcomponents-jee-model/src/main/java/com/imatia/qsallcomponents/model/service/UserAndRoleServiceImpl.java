package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.constants.ApplicationConstants;
import com.imatia.qsallcomponents.api.constants.entities.*;
import com.imatia.qsallcomponents.api.services.IUserAndRoleService;
import com.imatia.qsallcomponents.model.dao.RoleDao;
import com.imatia.qsallcomponents.model.dao.ServerRoleDao;
import com.imatia.qsallcomponents.model.dao.UserDao;
import com.imatia.qsallcomponents.model.dao.UserRoleDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.security.SecurityTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * The Class UserAndRoleServiceImpl.
 */
@Service("UserAndRoleService")
@Secured({PermissionsProviderSecured.SECURED})
@Transactional(rollbackFor = Exception.class)
public class UserAndRoleServiceImpl implements IUserAndRoleService {

    private static final String PIVOT_ROLES_SEPARATOR = ", ";

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * The user roles dao.
     */
    @Autowired
    private UserRoleDao userRolesDao;

    /**
     * The user dao.
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * The server role dao.
     */
    @Autowired
    private ServerRoleDao serverRoleDao;

    /**
     * The DAO helper.
     */
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

//	@Autowired
//	private IPasswordEncryptHelper passwordEncryptHelper;

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    @Transactional(readOnly = true)
    public EntityResult userQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {

        return this.daoHelper.query(this.userDao, keysValues, attributes);
    }

    @Override
    public AdvancedEntityResult userPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.userDao, keysValues, attributes, recordNumber, startIndex, orderBy);
    }

    @Override
    public EntityResult userUpdate(Map<?, ?> attributesValues, Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        try {

            return this.daoHelper.update(this.userDao, attributesValues, keysValues);
        } finally {

            this.invalidateSecurityManager();
        }
    }

    @Override
    public EntityResult userDelete(Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        Map<String, Object> updateValues = new HashMap<String, Object>();

        updateValues.put(User.DOWN_DATE, new Date());

        return this.userUpdate(updateValues, keysValues);
    }

    @Override
    public EntityResult userInsert(Map<?, ?> keysValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.userDao, keysValues);
    }

    @Override
    @Transactional(readOnly = true)
    public EntityResult roleQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {

        return this.daoHelper.query(this.roleDao, keysValues, attributes);
    }

    @Override
    public EntityResult roleUpdate(Map<?, ?> attributesValues, Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        try {

            return this.daoHelper.update(this.roleDao, attributesValues, keysValues);
        } finally {

            this.invalidateSecurityManager();
        }
    }

    @Override
    public EntityResult roleDelete(Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        try {

            this.serverRoleDao.unsafeDelete(keysValues);

            return this.daoHelper.delete(this.roleDao, keysValues);
        } finally {
            this.invalidateSecurityManager();
        }
    }

    @Override
    public EntityResult roleInsert(Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        try {

            return this.daoHelper.insert(this.roleDao, keysValues);
        } finally {

            this.invalidateSecurityManager();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EntityResult serverRoleQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {

        if (!keysValues.containsKey(Role.ID_ROLE)) {

            return this.daoHelper.query(this.serverRoleDao, keysValues, attributes, ServerRoleDao.ID_SERVER_ROLE_ALL_QUERY);
        } else {

            return this.daoHelper.query(this.serverRoleDao, keysValues, attributes, ServerRoleDao.ID_SERVER_ROLE_QUERY);
        }
    }

    @Override
    public EntityResult serverRoleUpdate(Map<?, ?> attributesValues, Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        if (ApplicationConstants.S.equals(attributesValues.get(RoleServerPermission.ACTIVED))) {

            // insert
            Map<String, Object> valuesToInsert = new HashMap<>();

            valuesToInsert.put(Role.ID_ROLE, keysValues.get(Role.ID_ROLE));
            valuesToInsert.put(ServerPermission.ID_SERVER_PERMISSION, keysValues.get(ServerPermission.ID_SERVER_PERMISSION));

            return this.daoHelper.insert(this.serverRoleDao, valuesToInsert);
        } else if (keysValues.get(RoleServerPermission.ID_ROLE_SERVER_PERMISSION) != null) {

            // delete
            Map<String, Object> valuesToDelete = new HashMap<>();

            valuesToDelete.put(RoleServerPermission.ID_ROLE_SERVER_PERMISSION, keysValues.get(RoleServerPermission.ID_ROLE_SERVER_PERMISSION));

            return this.daoHelper.delete(this.serverRoleDao, valuesToDelete);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public EntityResult rolesForUserQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {

        if (!keysValues.containsKey(User.ID_USER)) {

            return this.daoHelper.query(this.userRolesDao, keysValues, attributes, UserRoleDao.ROLES_WITHOUT_USER_QUERY);
        }

        return this.daoHelper.query(this.userRolesDao, keysValues, attributes, UserRoleDao.ROLES_WITH_USER_QUERY);
    }

    @Override
    public EntityResult rolesForUserUpdate(Map<?, ?> attributesValues, Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        if (ApplicationConstants.S.equals(attributesValues.get(RoleServerPermission.ACTIVED))) {

            // insert
            Map<String, Object> valuesToInsert = new HashMap<>();

            valuesToInsert.put(User.ID_USER, keysValues.get(User.ID_USER));
            valuesToInsert.put(Role.ID_ROLE, keysValues.get(Role.ID_ROLE));

            return this.daoHelper.insert(this.userRolesDao, valuesToInsert);
        } else if (keysValues.get(UserRole.ID_USER_ROLE) != null) {

            // delete
            Map<String, Object> valuesToDelete = new HashMap<>();

            valuesToDelete.put(UserRole.ID_USER_ROLE, keysValues.get(UserRole.ID_USER_ROLE));

            return this.daoHelper.delete(this.userRolesDao, valuesToDelete);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public EntityResult searchUsersQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {

        EntityResult res = this.daoHelper.query(this.userRolesDao, keysValues, attributes, UserRoleDao.DEFAULT_QUERY);

        return this.pivotRoles(res);
    }

    @Override
    public EntityResult searchUsersUpdate(Map<?, ?> attributes, Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        try {

            return this.daoHelper.update(this.userDao, attributes, keysValues);
        } finally {

            this.invalidateSecurityManager();
        }
    }

    @Override
    public EntityResult searchUsersDelete(Map<?, ?> keysValues) throws OntimizeJEERuntimeException {

        return this.userDelete(keysValues);
    }

    /**
     * Pivot roles.
     *
     * @param res the res
     * @return the entity result
     */
    private EntityResult pivotRoles(EntityResult res) {

        List<Object> l = new ArrayList<>();
        HashMap<Object, Object> hresgistro = new HashMap<>();
        EntityResult respivot = new EntityResultMapImpl(res.getOrderColumns());

        HashMap<String, Object> hres = (HashMap<String, Object>) res.getRecordValues(0);


        for (int i = 0; i <= res.calculateRecordNumber(); i++) {


            if (hresgistro.containsKey(hres.get(User.ID_USER))) {

                Map<String, Object> aux = (Map<String, Object>) hresgistro.get(hres.get(User.ID_USER));

                aux.put(Role.ROLE_NAME, ((String) aux.get(Role.ROLE_NAME)) + UserAndRoleServiceImpl.PIVOT_ROLES_SEPARATOR + hres.get(Role.ROLE_NAME));
                hresgistro.put(hres.get(User.ID_USER), aux);
            } else {

                hresgistro.put(hres.get(User.ID_USER), hres);
                l.add(hres.get(User.ID_USER));
            }

        }

        for (int i = 0; i < l.size(); i++) {

            respivot.addRecord((Map) hresgistro.get(l.get(i)));
        }

        return respivot;
    }

    /**
     * Refresh security manager.
     */
    private void invalidateSecurityManager() {

        SecurityTools.invalidateSecurityManager(this.daoHelper.getApplicationContext());
    }
}
