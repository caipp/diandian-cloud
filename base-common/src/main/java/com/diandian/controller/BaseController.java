package com.diandian.controller;

import com.diandian.domain.BaseEntity;
import com.diandian.domain.User;
import com.diandian.enums.ResultCode;
import com.diandian.service.BaseService;
import com.diandian.util.ApiResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author caipiaoping
 */
public abstract class BaseController<M extends BaseEntity> {

    @ApiOperation(value="列表", notes="")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public ApiResult list() {
        List<M> list= getService().getAll(getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,list);
    }


    @ApiOperation(value="获取对象", notes="根据url的id来获取指定对象")
    @RequestMapping(value = "/{id}", method = { RequestMethod.GET })
    public ApiResult get(@PathVariable("id") Long id) {
        M model = getService().get(id,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,model);
    }

    @ApiOperation(value="创建对象", notes="根据对象创建实体")
    @ApiImplicitParam(value = "对象",required = true)
    @RequestMapping(value="", method= RequestMethod.POST)
    public ApiResult post(@RequestBody M model) {
        model = getService().save(model,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,model);
    }

    @ApiOperation(value="更新对象", notes="根据url的id来指定更新对象，并根据传过来的对象信息来更新")
    @ApiImplicitParam(value = "对象",required = true)
    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ApiResult put(@PathVariable Long id, @RequestBody M model) {
        model = getService().update(model,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,model);
    }

    @ApiOperation(value="删除对象", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ApiResult delete(@PathVariable Long id) {
        getService().delete(id,getCurrentUser());
        return new ApiResult();
    }

    @ApiOperation(value="分页查询", notes="根据分页信息查询数据")
    @ApiImplicitParam(value = "对象",required = true)
    @RequestMapping(value="/query", method= RequestMethod.POST)
    public ApiResult query(@RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Page<M> pageInfo = getService().query(new PageRequest(page-1,size),null,getCurrentUser());
        return new ApiResult(ResultCode.SUCCESS,null,pageInfo);
    }


    /**
     * 初始化 service
     * @return service
     */
    protected abstract BaseService<M> getService();

    protected User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }


}
