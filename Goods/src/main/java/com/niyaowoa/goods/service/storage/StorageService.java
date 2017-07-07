package com.niyaowoa.goods.service.storage;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service("storageService")
public class StorageService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 添加物品
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveStorage(PageData pd) throws Exception {
		return dao.save("StorageMapper.save", pd);
	}

	/**
	 * 存入仓库
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveStack(PageData pd) throws Exception {
		return dao.save("StorageMapper.saveStack", pd);
	}

	/**
	 * 获取物品
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getStorage(PageData pd) throws Exception {
		return (PageData) dao.findForObject("StorageMapper.getStorage", pd);
	}

	/**
	 * 获取仓库列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllStorage(PageData pd) throws Exception {
		return (List) dao.findForList("StorageMapper.getAllStorage", pd);
	}


	/**
	 * 删除物品
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object deleteStorage(PageData pd) throws Exception {
		return dao.delete("StorageMapper.deleteStorage", pd);
	}


	/**
	 * 更新物品信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStorage(PageData pd) throws Exception {
		return dao.update("StorageMapper.updateStorage",pd);
	}

	/**
	 * 更新库存
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStack(PageData pd) throws Exception {
		return dao.update("StorageMapper.updateStack",pd);
	}

	/**
	 * 出库存
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateStackout(PageData pd) throws Exception {
		return dao.update("StorageMapper.updateStackout",pd);
	}

	/**
	 * 多物品删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteStorages(List list)  throws Exception  {
		return dao.batchDelete("StorageMapper.deleteStorages",list);
    }

	/**
	 *
	 * @param pd
	 * @return
	 */
	public List getStorages(PageData pd) throws Exception {
		return (List) dao.findForList("StorageMapper.getStorages", pd);
	}

	/**
	 * 得到库存
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getStack(PageData pd) throws Exception {
		return (PageData) dao.findForObject("StorageMapper.getStack", pd);
	}

	/**
	 * 通过仓库编码获取库存
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getGoods(PageData pd) throws Exception {
		return (List) dao.findForList("StorageMapper.getGoods", pd);
	}
}
