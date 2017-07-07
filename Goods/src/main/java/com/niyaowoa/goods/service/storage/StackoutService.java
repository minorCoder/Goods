package com.niyaowoa.goods.service.storage;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("stackoutService")
public class StackoutService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 添加出库
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveStackout(PageData pd) throws Exception {
		return dao.save("StackoutMapper.save", pd);
	}

	/**
	 * 获取出库
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getStackout(PageData pd) throws Exception {
		return (PageData) dao.findForObject("StackoutMapper.getStackout", pd);
	}

	/**
	 * 获取出库列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllStackout(PageData pd) throws Exception {
		return (List) dao.findForList("StackoutMapper.getAllStackout", pd);
	}


	/**
	 * 删除出库
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object deleteStackout(PageData pd) throws Exception {
		return dao.delete("StackoutMapper.deleteStackout", pd);
	}


	/**
	 * 更新出库信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStackout(PageData pd) throws Exception {
		return dao.update("StackoutMapper.updateStackout",pd);
	}

	/**
	 * 多出库删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteStackouts(List list)  throws Exception  {
		return dao.batchDelete("StackoutMapper.deleteStackouts",list);
    }

}
