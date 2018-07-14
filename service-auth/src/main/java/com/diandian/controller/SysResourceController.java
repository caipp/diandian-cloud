package com.diandian.controller;

import com.diandian.domain.SysResource;
import com.diandian.dto.SysResourceDTO;
import com.diandian.dto.SysResourceTreeDTO;
import com.diandian.enums.ResultCode;
import com.diandian.service.BaseService;
import com.diandian.service.SysResourceService;
import com.diandian.util.ApiResult;
import com.diandian.util.TreeBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caipiaoping
 */
@RestController
@RequestMapping("/resource")
public class SysResourceController extends BaseController<SysResource,SysResourceDTO>{

    @Autowired
    private SysResourceService service;

    @Override
    protected BaseService<SysResource> getService() {
        return service;
    }

    @Override
    protected SysResource newModel() {
        return new SysResource();
    }

    @Override
    protected SysResourceDTO newDTO() {
        return new SysResourceDTO();
    }


    @ApiOperation(value="资源树", notes="")
    @RequestMapping(value={"/tree"}, method= RequestMethod.GET)
    public ApiResult tree() {
        List<SysResource> list = new LinkedList<SysResource>();

        list = service.getUserResource(getCurrentUser());
        List<SysResourceTreeDTO> dtos = new TreeBuilder<SysResourceTreeDTO>().bulid(converterTreeDTO(list));
        Collections.sort(dtos,new TreeBuilder<>());
        return new ApiResult(ResultCode.SUCCESS,null,dtos);
    }

    private SysResourceTreeDTO converterTreeDTO(SysResource model) {
        SysResourceTreeDTO dto = new SysResourceTreeDTO();
        BeanUtils.copyProperties(model,dto);
        if(model.getParent() != null){
            dto.setParent_id(model.getParent().getId());
        }
        return dto;
    }
    private List<SysResourceTreeDTO> converterTreeDTO(List<SysResource> list) {
        List<SysResourceTreeDTO> dtoList = new ArrayList<>();
        for(SysResource model :list){
            dtoList.add(converterTreeDTO(model));
        }

        return dtoList;
    }

}
