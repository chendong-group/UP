package com.lx.curriculum_vitae.mapper;

import com.lx.curriculum_vitae.dao.UserDao;
import com.lx.curriculum_vitae.model.User;
import com.lx.curriculum_vitae.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }
}
