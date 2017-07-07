package com.niyaowoa.goods.service.user;

import java.util.List;

import javax.annotation.Resource;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SystemService")
public class UserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 添加用户
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveUser(PageData pd) throws Exception {
		return dao.save("UserMapper.save", pd);
	}

	/**
	 * 获取用户用户名和密码
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getUser(PageData pd) throws Exception {
		return (PageData) dao.findForObject("UserMapper.getUser", pd);
	}

	/**
	 * 通过ID得到用户(包括具体信息)
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public PageData getUserByID(PageData pd) throws Exception {
		dao.update("UserMapper.updateInfo",pd);
		return (PageData) dao.findForObject("UserMapper.getUserByID", pd);
	}

	/**
	 * 更新最后登录时间
	 *
	 * @param pd
	 * @throws Exception
	 */
	public void updateLastLogin(PageData pd) throws Exception {
		dao.update("UserMapper.updateLastLogin", pd);
	}

	/**
	 * 得到用户列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllUser(PageData pd) throws Exception {
		return (List) dao.findForList("UserMapper.getAllUser", pd);
	}

	/**
	 * 得到所有管理员用户
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllMangeUser(PageData pd) throws Exception {
		return (List) dao.findForList("UserMapper.getAllMangeUser", pd);
	}

	/**
	 * 停用
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStop(PageData pd) throws Exception {
		return dao.update("UserMapper.updateStop", pd);
	}

	/**
	 *
	 * 启用
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStart(PageData pd) throws Exception {
		return dao.update("UserMapper.updateStart", pd);
	}

	/**
	 * 删除用户
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object deleteUser(PageData pd) throws Exception {
		return dao.delete("UserMapper.deleteU", pd);
	}

	/**
	 * 多用户删除
	 *
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Object deleteMuchUser(List list) throws Exception {
		return dao.batchDelete("UserMapper.deleteMuchU", list);
	}

	/**
	 * 用户是否存在
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object getUsername(PageData pd) throws Exception {
		return dao.findForObject("UserMapper.getUsername", pd);
	}

	/**
	 * 插入用户信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveinfo(PageData pd) throws Exception {
		return dao.save("UserMapper.saveinfo", pd);
	}

	/**
	 * 更新密码
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updatePassword(PageData pd) throws Exception {
		return dao.update("UserMapper.updatePassword",pd);

	}

	/**
	 * 更新用户信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateUser(PageData pd) throws Exception {
		return dao.update("UserMapper.updateUser",pd);
	}
}
