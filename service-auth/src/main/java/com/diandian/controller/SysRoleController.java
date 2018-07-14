package com.diandian.controller;

import com.diandian.domain.SysRole;
import com.diandian.dto.SysRoleDTO;
import com.diandian.service.BaseService;
import com.diandian.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author caipiaoping
 */
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController<SysRole,SysRoleDTO>{

    @Autowired
    private SysRoleService service;

    @Override
    protected BaseService<SysRole> getService() {
        return service;
    }

    @Override
    protected SysRole newModel() {
        return new SysRole();
    }

    @Override
    protected SysRoleDTO newDTO() {
        return new SysRoleDTO();
    }


}
