package com.diandian.service;

import com.diandian.domain.SysResource;
import com.diandian.domain.SysRole;
import com.diandian.domain.User;
import com.diandian.repository.SysResourceRepository;
import com.diandian.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author caipiaoping
 * @date 2016/10/22
 */
@Service
public class SysResourceService extends BaseService<SysResource> {

    @Autowired
    private SysResourceRepository repository;

    @Override
    protected SysResourceRepository getRepository() {
        return repository;
    }

    public List<SysResource> getUserResource(User user) {
        List<SysResource> resources = repository.findListByUserId(user.getId());
        return resources;
    }
}
