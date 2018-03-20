package com.example.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.base.BaseServiceImpl;
import com.example.dao.UserDao;
import com.example.model.User;
import com.example.service.UserServer;
import com.example.util.MyLog;

@Service
public class UserServerImpl extends BaseServiceImpl<User,UserDao> implements UserServer{

	@Resource
	private UserDao userDao;
	
	@MyLog(value = "My日志")
	public List<User> findAll() {
		return baseDao.findAll();
	}
}
