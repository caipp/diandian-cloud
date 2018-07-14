package com.diandian.service;

import com.diandian.domain.SysRole;
import com.diandian.domain.SysUser;
import com.diandian.repository.SysRoleRepository;
import com.diandian.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@Service
public class SysRoleService extends BaseService<SysRole> {

    @Autowired
    private SysRoleRepository repository;

    @Override
    protected SysRoleRepository getRepository() {
        return repository;
    }

}
