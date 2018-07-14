package com.diandian.service;

import com.diandian.domain.BaseEntity;
import com.diandian.domain.User;
import com.diandian.persistence.DynamicSpecifications;
import com.diandian.persistence.SearchFilter;
import com.diandian.persistence.SearchRequest;
import com.diandian.repository.BaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;


/**
 * @author caipiaoping
 */
public abstract class BaseService<M extends BaseEntity> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public M get(String id, User currentUser) {
        return getRepository().findOne(id);
    }

    public List<M> getAll(User currentUser) {
        return getRepository().findAll();
    }

    public M save(M model, User currentUser) {
        beforeSave(model, currentUser);
        getRepository().save(model);
        afterSave(model, currentUser);
        return model;
    }

    protected void beforeSave(M model, User currentUser) {
    }

    protected void afterSave(M model, User currentUser) {
    }

    public M update(M model, User currentUser) {
        beforeUpdate(model, currentUser);
        getRepository().save(model);
        afterUpdate(model, currentUser);
        return model;
    }

    protected void beforeUpdate(M model, User currentUser) {
    }

    protected void afterUpdate(M model, User currentUser) {
    }

    public void delete(String id, User currentUser) {
        beforeDelete(id, currentUser);
        getRepository().delete(id);
        afterDelete(id, currentUser);
    }

    protected void afterDelete(String id, User currentUser) {
    }

    protected void beforeDelete(String id, User currentUser) {
    }


    public Page<M> query(SearchRequest searchRequest, User currentUser) {
        return getRepository().findAll(buildSpecification(currentUser.getId(), searchRequest.getSearchParams()), buildPageRequest(searchRequest.getPage(),searchRequest.getSize(),searchRequest.getSortType()));
    }

    /**
     * 创建分页请求.
     */
    protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
        if (StringUtils.isEmpty(sortType) || "auto".equals(sortType)) {
            sort = new Sort(Sort.Direction.DESC, "id");
        } else{
            String[] splits = sortType.split("_");

            if(splits.length == 2){
                if(splits[1].equalsIgnoreCase(Sort.Direction.ASC.toString())){
                    sort = new Sort(Sort.Direction.ASC, splits[0]);
                }else{
                    sort = new Sort(Sort.Direction.DESC, splits[0]);
                }
            }
        }
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 创建动态查询条件组合.
     */
    protected Specification<M> buildSpecification(String userId, Map<String, Object> searchParams) {
        if(searchParams == null){
            return null;
        }
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<M> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return spec;
    }

    /**
     * 抽象Repository
     * @return
     */
    protected abstract BaseRepository<M> getRepository();

}
