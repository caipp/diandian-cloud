package com.diandian.repository;

import com.diandian.domain.SysResource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caipiaoping
 */
public interface SysResourceRepository extends BaseRepository<SysResource> {

    @Query("SELECT distinct re FROM SysResource re,SysRole ro,SysUser u WHERE re.id in elements ( ro.resources ) AND ro.id in elements ( u.roles ) AND u.id=?")
    List<SysResource> findListByUserId(String id);
}
