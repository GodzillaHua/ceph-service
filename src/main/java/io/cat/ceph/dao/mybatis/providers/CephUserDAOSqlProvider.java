package io.cat.ceph.dao.mybatis.providers;

import io.cat.ceph.dao.TableNames;
import io.cat.ceph.domain.CephUser;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

/**
 * @author GodzillaHua
 **/
public class CephUserDAOSqlProvider {

    public String updateCephUserSql(final CephUser cephUser){
        return new SQL(){{
            UPDATE(TableNames.CEPH_USER_TALBLE);
            SET("update_time  = #{updateTime}");
            if (!Objects.isNull(cephUser.getAccessKey())){
                SET("access_key = #{accessKey}");
            }
            if (!Objects.isNull(cephUser.getSecretKey())){
                SET("secret_key = #{secretKey}");
            }
            if (cephUser.getSuspend() != null){
                SET("suspend = #{suspend}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
