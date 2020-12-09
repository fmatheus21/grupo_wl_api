package com.firecode.app.model.repository.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.firecode.app.model.entity.EventParticipationEntity;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import com.firecode.app.model.repository.query.EventParticipationRepositoryQuery;

public class EventParticipationRepositoryImpl implements EventParticipationRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<EventParticipationEntity> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<EventParticipationEntity> criteriaQuery = builder.createQuery(EventParticipationEntity.class);
        Root<EventParticipationEntity> root = criteriaQuery.from(EventParticipationEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<EventParticipationEntity> typedQuery = manager.createQuery(criteriaQuery);

        addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    private Predicate[] createRestrictions(RepositoryFilter filter, CriteriaBuilder builder, Root<EventParticipationEntity> root) {
        List<Predicate> predicates = new ArrayList();

        if (!StringUtils.isEmpty(filter.getFilter())) {
            predicates.add(builder.like(builder.lower(root.<String>get("filter")), "%" + filter.getFilter().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPageRestrictions(TypedQuery<EventParticipationEntity> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }

    private Long total(RepositoryFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<EventParticipationEntity> root = criteriaQuery.from(EventParticipationEntity.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }

}
