package com.diandian.repository;


import com.diandian.domain.SysUser;

import java.util.Optional;

public interface UserRepository extends BaseRepository<SysUser> {

    Optional<SysUser> findOneWithRolesByUsername(String username);

}
