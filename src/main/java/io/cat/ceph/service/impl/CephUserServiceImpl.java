package io.cat.ceph.service.impl;

import io.cat.ceph.dao.CephUserDAO;
import io.cat.ceph.domain.CephUser;
import io.cat.ceph.exception.CephServiceException;
import io.cat.ceph.service.CephUserService;
import io.cat.ceph.service.RgwAdminService;
import io.cat.ceph.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author GodzillaHua
 **/
@Component
public class CephUserServiceImpl implements CephUserService {

    private static final Logger logger = LoggerFactory.getLogger(CephUserServiceImpl.class);

    private final RgwAdminService rgwAdminService;
    private final CephUserDAO cephUserDAO;

    public CephUserServiceImpl(RgwAdminService rgwAdminService, CephUserDAO cephUserDAO) {
        this.rgwAdminService = rgwAdminService;
        this.cephUserDAO = cephUserDAO;
    }

    @Override
    public void syncCephUser() throws CephServiceException {

        List<CephUser> syncCephUsers = this.rgwAdminService.getUsers();

        if (syncCephUsers.isEmpty()) {
            return;
        }

        for (CephUser cephUser : syncCephUsers) {
            CephUser dbCephUser = getUser(cephUser.getName());
            if (dbCephUser == null) {
                Date now = DateUtils.now();
                cephUser.setCreateTime(now);
                cephUser.setUpdateTime(now);
                try {
                    this.cephUserDAO.saveCephUser(cephUser);
                } catch (DataAccessException e) {
                    logger.error("save sync ceph user error", e);
                }
            } else {
                boolean flag = false;

                CephUser updateCephUser = new CephUser();

                if (!StringUtils.equals(cephUser.getAccessKey(), dbCephUser.getAccessKey())) {
                    flag = true;
                    updateCephUser.setAccessKey(cephUser.getAccessKey());
                }

                if (!StringUtils.equals(cephUser.getSecretKey(), dbCephUser.getSecretKey())) {
                    flag = true;
                    updateCephUser.setSecretKey(cephUser.getSecretKey());
                }

                if (!cephUser.getSuspend().equals(dbCephUser.getSuspend())) {
                    flag = true;
                    updateCephUser.setSuspend(cephUser.getSuspend());
                }

                if (flag) {

                    updateCephUser.setId(dbCephUser.getId());
                    updateCephUser.setUpdateTime(DateUtils.now());
                    try {
                        this.cephUserDAO.updateCeph(updateCephUser);
                    }catch (DataAccessException e){
                        logger.error("update sync ceph user error", e);
                    }
                }

            }
        }


    }

    @Override
    public CephUser getUser(String name) throws CephServiceException {
        try {
            return this.cephUserDAO.getCephUserByName(name);
        } catch (DataAccessException e) {
            throw new CephServiceException("get user by name error", e);
        }
    }
}
