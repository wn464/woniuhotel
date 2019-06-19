package com.project.dao;

import com.project.bean.UserBean;

public interface IUserDao {
UserBean finduserbyid();

UserBean login(String username);
}
