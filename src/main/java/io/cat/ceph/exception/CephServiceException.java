package io.cat.ceph.exception;

/**
 * @author GodzillaHua
 **/
public class CephServiceException extends Exception {

    public CephServiceException() {
    }

    public CephServiceException(String message) {
        super(message);
    }

    public CephServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CephServiceException(Throwable cause) {
        super(cause);
    }
}
