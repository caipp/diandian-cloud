package com.diandian.service;

import com.diandian.domain.SysUser;
import com.diandian.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@Service
public class SysUserService extends BaseService<SysUser> {

    @Autowired
    private SysUserRepository repository;

    @Override
    protected SysUserRepository getRepository() {
        return repository;
    }

    public SysUser getUserByUsername(String username) {
        SysUser user = repository.findByUsername(username).get();
        return user;
    }
}
