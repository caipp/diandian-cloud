package com.diandian.persistence;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2017-12-05
 */
public class DynamicSpecifications {
    private static final ConversionService conversionService = new DefaultConversionService();

    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if ((filters != null) && !(filters.isEmpty())) {

                    List<Predicate> predicates = Lists.newArrayList();
                    for (SearchFilter filter : filters) {

                        // nested path translate
                        String[] names = StringUtils.split(filter.fieldName, "$");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }

                        // convert value
                        Class attributeClass = expression.getJavaType();
                        if (!attributeClass.equals(String.class) && filter.value instanceof String
                                && conversionService.canConvert(String.class, attributeClass)) {
                            filter.value = conversionService.convert(filter.value, attributeClass);
                        }

                        switch (filter.operator) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.value));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, "%" + filter.value + "%"));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                                break;
                        }
                    }

                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }
        };
    }
}
