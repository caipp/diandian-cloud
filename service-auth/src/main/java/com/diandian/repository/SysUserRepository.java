package com.diandian.repository;


import com.diandian.domain.SysUser;

import java.util.Optional;

public interface SysUserRepository extends BaseRepository<SysUser> {

    Optional<SysUser> findOneWithRolesByUsername(String username);

    Optional<SysUser> findByUsername(String username);
}
