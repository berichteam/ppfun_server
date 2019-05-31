package com.pipi.common.service;

import com.pipi.common.domain.Fun;
import com.pipi.common.domain.Users;
import com.pipi.common.repository.FunRepository;
import com.pipi.common.repository.UserRepository;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.service.inter.UserService;
import com.pipi.common.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author lazyb
 * @create 2019/5/24
 * @desc 用户接口实现
 **/
@CommonsLog
@Service("funService")
public class FunServiceImpl implements FunService {

    @Autowired
    private FunRepository funRepository;

    @Override
    public Page<Fun> findAllByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page - 1, size);
        return funRepository.findAll(pageable);
    }
}
