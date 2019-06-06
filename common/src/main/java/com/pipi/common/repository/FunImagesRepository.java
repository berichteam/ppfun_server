package com.pipi.common.repository;

import com.pipi.common.domain.FunImages;
import com.pipi.common.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ryan
 * @create 2019/6/5
 * @desc 图片库
 **/
@Repository
public interface FunImagesRepository extends JpaRepository<FunImages, Long> {
    @Query(value = "update fun_images set fun_id=?1 ,blur=?2 ,`desc` = ?3 ,updated_at =NOW() where id=?4 ", nativeQuery = true)
    @Modifying
    void updateOneWithOutBlur(int funId, int blur, String desc, int id);

    @Query(value = "update fun_images set fun_id=?1 ,blur=?2 ,`desc` = ?3 ,image_blur_url =?4,updated_at =NOW() where id=?5 ", nativeQuery = true)
    @Modifying
    void updateOneWithBlur(int funId, int blur, String desc,String imageBlurUrl, int id);

    @Query(value = "select image_url from fun_images where id=?1 ", nativeQuery = true)
    String getUrlById(int id);
}
