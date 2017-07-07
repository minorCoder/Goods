package com.niyaowoa.goods.service.storage;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("stackinService")
public class StackinService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 添加入库
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveStackin(PageData pd) throws Exception {
		return dao.save("StackinMapper.save", pd);
	}

	/**
	 * 获取入库
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getStackin(PageData pd) throws Exception {
		return (PageData) dao.findForObject("StackinMapper.getStackin", pd);
	}

	/**
	 * 获取入库列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllStackin(PageData pd) throws Exception {
		return (List) dao.findForList("StackinMapper.getAllStackin", pd);
	}


	/**
	 * 删除入库
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object deleteStackin(PageData pd) throws Exception {
		return dao.delete("StackinMapper.deleteStackin", pd);
	}


	/**
	 * 更新入库信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStackin(PageData pd) throws Exception {
		return dao.update("StackinMapper.updateStackin",pd);
	}

	/**
	 * 多入库删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteStackins(List list)  throws Exception  {
		return dao.batchDelete("StackinMapper.deleteStackins",list);
    }

}
