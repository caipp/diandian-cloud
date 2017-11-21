package com.diandian.service;

import com.diandian.domain.BaseEntity;
import com.diandian.domain.User;
import com.diandian.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


/**
 * @author caipiaoping
 */
public abstract class BaseService<M extends BaseEntity> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public M get(Long id, User currentUser) {
        return getRepository().getOne(id);
    }

    public List<M> getAll(User currentUser) {
        return getRepository().findAll();
    }

    public M save(M model, User currentUser) {
        return getRepository().save(model);
    }

    public M update(M model, User currentUser) {
        return getRepository().save(model);
    }

    public void delete(Long id, User currentUser) {
        getRepository().delete(id);
    }

    public Page<M> query(Pageable pageable, Specification spec, User currentUser) {
        return getRepository().findAll(spec, pageable);
    }

    protected abstract BaseRepository<M> getRepository();

}
