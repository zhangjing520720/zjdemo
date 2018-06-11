package com.sdy.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sdy.dao.DaoSupport;
import com.sdy.domain.Page;
import com.sdy.domain.User;
import com.sdy.util.PageData;

@Service("userService")
public class UserService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/*
	*根据ID获取对象
	*/
	public User findOneById(User user) {
		try {
			user = (User) dao.findForObject("UserMapper.findOneById", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return user;
    }
	/*
	*用户列表
	*/
	public List<PageData> infoListPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserMapper.infolistPage", page);
	}
}
