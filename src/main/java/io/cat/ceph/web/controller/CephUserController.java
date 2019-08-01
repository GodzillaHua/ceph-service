package io.cat.ceph.web.controller;

import io.cat.ceph.domain.CephUser;
import io.cat.ceph.dto.CephUserDto;
import io.cat.ceph.exception.CephServiceException;
import io.cat.ceph.service.CephUserService;
import io.cat.ceph.web.api.Result;
import io.cat.ceph.web.form.CreateCephUserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author GodzillaHua
 **/
@RestController
@RequestMapping("/ceph-user")
public class CephUserController {

    @Autowired
    private CephUserService cephUserService;

    @RequestMapping("/list")
    public Result<List<CephUser>> listCephUsers() throws CephServiceException {
        return Result.ok(this.cephUserService.getAll());
    }

    @RequestMapping("/detail")
    public Result<CephUser> detailCephUser(@RequestParam("name") String name) throws CephServiceException {
        return Result.ok(this.cephUserService.getCephUser(name));
    }

    @RequestMapping("/create")
    public Result createCephUser(@Valid CreateCephUserForm form, BindingResult bindingResult) throws CephServiceException {
        if (bindingResult.hasErrors()){
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        CephUserDto cephUserDto = new CephUserDto();
        BeanUtils.copyProperties(form, cephUserDto);
        this.cephUserService.createCephUser(cephUserDto);
        return Result.ok();
    }

    @RequestMapping("/update-credential")
    public Result updateCephUserCredential(@RequestParam(name = "name") String name) throws CephServiceException {
        this.cephUserService.updateCephUserCredential(name);
        return Result.ok();
    }

    @RequestMapping("/delete")
    public Result deleteCephUser(@RequestParam(name = "name") String name) throws CephServiceException {
        this.cephUserService.deleteCephUser(name);
        return Result.ok();
    }
}
