package com.pipi.common.query;

import com.pipi.common.domain.Fun;
import com.pipi.common.enums.MatchType;
import com.pipi.common.enums.QueryWord;
import com.pipi.common.util.BaseQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class FunQuery extends BaseQuery<Fun> {
    @QueryWord(column = "authority", func = MatchType.equal)
    private Integer authority;

//    @QueryWord(func = MatchType.like)
//    private String itemName;
//
//    @QueryWord(func = MatchType.le)
//    private Integer itemPrice;

    @Override
    public Specification<Fun> toSpec() {
        Specification<Fun> spec = super.toSpecWithAnd();
        return ((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            predicatesList.add(spec.toPredicate(root, criteriaQuery, criteriaBuilder));
            if (authority != null) {
                predicatesList.add(
                        criteriaBuilder.and());
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        });
    }
}
