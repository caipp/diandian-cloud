package com.diandian.service;

import com.diandian.domain.SysUser;
import com.diandian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 12546 on 2016/10/22.
 */
@Service
public class UserService extends BaseService<SysUser> {

    @Autowired
    private UserRepository userRepo;

    @Override
    protected UserRepository getRepository() {
        return userRepo;
    }

}
