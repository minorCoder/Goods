package com.niyaowoa.goods.service.rent;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.utils.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("rentService")
public class RentService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 添加借用记录
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Object saverent(PageData pd) throws Exception {
		int tot = Integer.parseInt(pd.getString("tot"));
		//JSONArray jsonArray = new JSONArray();
		List list = new ArrayList();
		for(int i = 0;i<tot;i++){
			Map map = new HashMap();
			Map map1 = new HashMap();
			map.put("rent_id",pd.getString("rent_id"));
			map.put("good_code",pd.getString("good_info["+i+"][good_code]"));
			map.put("num",pd.getString("good_info["+i+"][num]"));
			map.put("value",pd.getString("good_info["+i+"][value]"));
			dao.update("GoodMapper.updateGoodNumout",map);
			list.add(map);
		}

		//List list = jsonArray.toList(jsonArray, HashMap.class);
		//dao.batchUpdate("GoodMapper.updateGoodout",list);
		dao.save("RentMapper.save", pd);
		return dao.batchSave("RentMapper.save_rent",list);
	}

	/**
	 * 获取借用
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getrent(PageData pd) throws Exception {
		return (PageData) dao.findForObject("RentMapper.getRent", pd);
	}

	/**
	 * 查找借用信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getrent_good(PageData pd ) throws Exception {
		return  (List) dao.findForList("RentMapper.getrent_good",pd);
	}
	/**
	 * 获取借用列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllRent(PageData pd) throws Exception {
		return (List) dao.findForList("RentMapper.getAllRent", pd);
	}


	/**
	 * 删除借用
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Object deleteRent(PageData pd) throws Exception {
		dao.delete("RentMapper.deleteRent_good",pd);
		return dao.delete("RentMapper.deleteRent", pd);
	}


	/**
	 * 更新借用信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateRent(PageData pd) throws Exception {
		return dao.update("RentMapper.updateRent",pd);
	}


	/**
	 * 借用信息批量删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteGoods(List list)  throws Exception  {
		return dao.batchDelete("RentMapper.deleteRents",list);
    }

	/**
	 * 是否有借用
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getisrent(PageData pd) throws Exception {
		return (PageData) dao.findForObject("RentMapper.getisrent", pd);
	}

	/**
	 * 批量删除rent
	 * @param list
	 * @return
	 */
	@Transactional
    public Object deleteRents(List list) throws Exception {
    	dao.batchDelete("RentMapper.deleteRent_goods",list);
    	return dao.batchDelete("RentMapper.deleteRents",list);
    }

	/**
	 * 用户权限
	 * @param pd
	 * @return
	 * @throws Exception
	 */
    public List getreservationById(PageData pd) throws Exception {
		return (List) dao.findForList("RentMapper.getreservationById", pd);
    }
}
