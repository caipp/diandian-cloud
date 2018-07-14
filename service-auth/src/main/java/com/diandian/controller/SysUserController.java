package com.diandian.controller;

import com.diandian.domain.SysUser;
import com.diandian.dto.SysUserDTO;
import com.diandian.enums.ResultCode;
import com.diandian.service.BaseService;
import com.diandian.service.SysRoleService;
import com.diandian.service.SysUserService;
import com.diandian.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author caipiaoping
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController<SysUser,SysUserDTO>{

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @Autowired
    private SysUserService service;

    @Override
    protected BaseService<SysUser> getService() {
        return service;
    }

    @Override
    protected SysUser newModel() {
        return new SysUser();
    }

    @Override
    protected SysUserDTO newDTO() {
        return new SysUserDTO();
    }


    @RequestMapping(value = "/check/name", method = RequestMethod.GET)
    public ApiResult checkName(@RequestParam String name, @RequestParam(required=false) String exclude) {

        SysUser user  = this.service.getUserByUsername(name);
        if(user == null){
            return new ApiResult(ResultCode.SUCCESS,null,false);
        }else if(user.getId().equals(exclude)){
            return new ApiResult(ResultCode.SUCCESS,null,false);
        }

        return new ApiResult(ResultCode.SUCCESS,null,true);
    }
}
