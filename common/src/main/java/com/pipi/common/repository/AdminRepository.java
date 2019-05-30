package com.pipi.common.repository;

import com.pipi.common.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lazyb
 * @create 2019/5/30
 * @desc
 **/
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByName(String name);

}
