package io.cat.ceph.web;

import io.cat.ceph.exception.CephServiceException;
import io.cat.ceph.web.api.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GodzillaHua
 **/
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(value = CephServiceException.class)
    @ResponseBody
    public Result handlerCephServiceException(HttpServletRequest request, CephServiceException e){
        logger.error("uri:" + request.getRequestURI(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handlerException(HttpServletRequest request, Exception e){
        logger.error("uri:" + request.getRequestURI(), e);
        return Result.error(e.getMessage());
    }

}
