package com.pipi.common.repository;

import com.pipi.common.domain.Fun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Ryan
 * @create 2019/5/31
 * @desc 文章库
 **/
@Repository
public interface FunRepository extends JpaRepository<Fun, Long>, JpaSpecificationExecutor<Fun> {


}
