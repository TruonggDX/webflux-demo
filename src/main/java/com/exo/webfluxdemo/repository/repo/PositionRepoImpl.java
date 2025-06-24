package com.exo.webfluxdemo.repository.repo;

import com.exo.webfluxdemo.entity.PositionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class PositionRepoImpl implements PositionRepo {

  private final R2dbcEntityTemplate template;

  @Override
  public Flux<PositionEntity> searchByCodeOrName(String keyword) {
    Criteria criteria = Criteria.empty();
    if (keyword != null && !keyword.trim().isEmpty()) {
      Criteria codeCriteria = Criteria.where("code").like("%" + keyword + "%");
      Criteria nameCriteria = Criteria.where("name").like("%" + keyword + "%");
      criteria = codeCriteria.or(nameCriteria);
    }
    return template.select(PositionEntity.class)
        .matching(Query.query(criteria))
        .all();
  }
}
