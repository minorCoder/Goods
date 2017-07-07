package com.niyaowoa.goods.service.good;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service("goodService")
public class GoodService {

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
	public Object savegood(PageData pd) throws Exception {
		return dao.save("GoodMapper.save", pd);
	}

	/**
	 * 获取物品
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getGood(PageData pd) throws Exception {
		return (PageData) dao.findForObject("GoodMapper.getGood", pd);
	}

	/**
	 * 获取物品列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllGood(PageData pd) throws Exception {
		return (List) dao.findForList("GoodMapper.getAllGood", pd);
	}


	/**
	 * 删除物品
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object deleteGood(PageData pd) throws Exception {
		return dao.delete("GoodMapper.deleteGood", pd);
	}


	/**
	 * 更新物品信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateGood(PageData pd) throws Exception {
		return dao.update("GoodMapper.updateGood",pd);
	}

	/**
	 * 出库更新
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateGoodNumout(PageData pd) throws Exception {
		return dao.update("GoodMapper.updateGoodNumout",pd);
	}
	/**
	 * 入库更新
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateGoodNumin(PageData pd) throws Exception {
		return dao.update("GoodMapper.updateGoodNumin",pd);
	}
	/**
	 * 多物品删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteGoods(List list)  throws Exception  {
		return dao.batchDelete("GoodMapper.deleteGoods",list);
    }

	/**
	 * 删除图片
	 * @param list
	 * @param bathpath
	 * @throws Exception
	 */
	public void deletePhotoes(List list,String bathpath) throws Exception {
		PageData pd = new PageData();

		for(Object i:list) {
    		pd.put("good_code",i);
			pd = getGood(pd);
			File file = new File(bathpath + "static/" + pd.getString("good_photo"));
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		}
	}


}
