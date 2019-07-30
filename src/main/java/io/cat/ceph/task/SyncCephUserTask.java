package io.cat.ceph.task;

import io.cat.ceph.exception.CephServiceException;
import io.cat.ceph.service.CephUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author GodzillaHua
 **/
@Component
public class SyncCephUserTask {

    private static Logger logger = LoggerFactory.getLogger(SyncCephUserTask.class);

    @Autowired
    private CephUserService cephUserService;

    @Scheduled(cron = "*/5 * * * * *")
    public void run(){
        try {
            cephUserService.syncCephUser();
        } catch (CephServiceException e) {
            logger.error("sync ceph user error", e);
        }
    }
}
