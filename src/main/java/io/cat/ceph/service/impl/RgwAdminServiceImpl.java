package io.cat.ceph.service.impl;

import io.cat.ceph.domain.CephUser;
import io.cat.ceph.dto.CephUserDto;
import io.cat.ceph.service.RgwAdminService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.twonote.rgwadmin4j.RgwAdmin;
import org.twonote.rgwadmin4j.model.S3Credential;
import org.twonote.rgwadmin4j.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author GodzillaHua
 **/
@Component
public class RgwAdminServiceImpl implements RgwAdminService {

    private static Logger logger = LoggerFactory.getLogger(RgwAdminServiceImpl.class);

    private final RgwAdmin rgwAdmin;

    public RgwAdminServiceImpl(RgwAdmin rgwAdmin) {
        this.rgwAdmin = rgwAdmin;
    }

    @Override
    public List<CephUser> getUsers() {
        List<String> names = rgwAdmin.listUser();
        if (names == null || names.isEmpty()) {
            return new ArrayList<>(0);
        }
        List<CephUser> cephUsers = new ArrayList<>(names.size());
        for (String name : names) {
            Optional<User> userOpt = rgwAdmin.getUserInfo(name);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (user.getS3Credentials() == null || user.getS3Credentials().isEmpty()) {
                    logger.warn("user {} has no credentials", user.getUserId());
                    continue;
                }
                CephUser cephUser = new CephUser();
                cephUser.setName(user.getUserId());
                cephUser.setDisplayName(user.getDisplayName());
                cephUser.setEmail(user.getEmail());
                cephUser.setSuspend(user.getSuspended());
                S3Credential credential = user.getS3Credentials().get(0);
                cephUser.setAccessKey(credential.getAccessKey());
                cephUser.setSecretKey(credential.getSecretKey());
                cephUsers.add(cephUser);
            }
        }
        return cephUsers;
    }

    @Override
    public void createUser(CephUserDto cephUserDto) {
        this.rgwAdmin.createUser(cephUserDto.getName(), Collections.singletonMap("email", cephUserDto.getEmail()));
    }

    @Override
    public boolean exists(String name) {
        return this.rgwAdmin.getUserInfo(name).isPresent();
    }

    @Override
    public void updateCephUserCredential(CephUser cephUser) {
        String accessKey = cephUser.getAccessKey();
        if (StringUtils.isNoneBlank(accessKey)) {
            this.rgwAdmin.removeS3Credential(cephUser.getName(), accessKey);
        }
        this.rgwAdmin.createS3Credential(cephUser.getName());
    }

    @Override
    public void deleteUser(CephUser cephUser) {
        this.rgwAdmin.removeUser(cephUser.getName());
    }
}