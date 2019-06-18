package com.pipi.common.service;

import com.pipi.common.domain.Admin;
import com.pipi.common.repository.AdminRepository;
import com.pipi.common.service.inter.AdminService;
import com.pipi.common.util.PasswordEncryption;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lazyb
 * @create 2019/5/30
 * @desc
 **/
@CommonsLog
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin register(String name, String password) {
        Admin admin = adminRepository.findByName(name);
        if (admin != null) {
            log.error("name is exit");
            return null;
        }
        admin = new Admin(name, PasswordEncryption.BCRYPT.encrypt(password));
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public Admin login(String name, String password) {
        Admin admin = adminRepository.findByName(name);
        if (admin == null) {
            log.error("name is error");
            return null;
        }
        if (!PasswordEncryption.BCRYPT.check(password, admin.getPassword())) {
            log.error("password is error");
            return null;
        }
        return admin;
    }

    @Override
    public Admin findByName(String name) {
        return adminRepository.findByName(name);
    }
}