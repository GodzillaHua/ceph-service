package io.cat.ceph.service;

import io.cat.ceph.domain.CephUser;
import io.cat.ceph.dto.CephUserDto;
import io.cat.ceph.exception.CephServiceException;

import java.util.List;

/**
 * @author GodzillaHua
 **/
public interface CephUserService {

    /**
     * sync ceph rgw user to database
     * @throws CephServiceException
     */
    void syncCephUser() throws CephServiceException;

    /**
     * get ceph user from database
     * @param name username of ceph
     * @return user detail info
     * @throws CephServiceException
     */
    CephUser getCephUser(String name) throws CephServiceException;

    /**
     * get all ceph user from database
     * @return ceph user list
     * @throws CephServiceException
     */
    List<CephUser> getAll() throws CephServiceException;

    /**
     * create ceph user
     * @param cephUserDto ceph user dto
     * @throws CephServiceException
     */
    void createCephUser(CephUserDto cephUserDto) throws CephServiceException;

    /**
     * update ceph user credential
     * @param name user name
     * @throws CephServiceException
     */
    void updateCephUserCredential(String name) throws CephServiceException;

    /**
     * delete ceph user
     * @param name username
     * @throws CephServiceException
     */
    void deleteCephUser(String name) throws CephServiceException;

}
