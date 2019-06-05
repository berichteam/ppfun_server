package com.pipi.common.repository;

import com.pipi.common.domain.FunImages;
import com.pipi.common.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ryan
 * @create 2019/6/5
 * @desc 图片库
 **/
@Repository
public interface FunImagesRepository extends JpaRepository<FunImages, Long> {

}
