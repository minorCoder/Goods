package com.niyaowoa.goods.service.revert;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.rent.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("revertService")
public class RevertService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	RentService rentService;
	@Autowired
	GoodService goodService;

	// ======================================================================================

	/**
	 * 添加归还记录
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Object saverevert(PageData pd) throws Exception {
		int tot = Integer.parseInt(pd.getString("tot"));
		//JSONArray jsonArray = new JSONArray();
		for(int i = 0;i<tot;i++){
			Map map = new HashMap();
			//Map map1 = new HashMap();
			map.put("rent_id",pd.getString("rent_id"));
			map.put("good_code",pd.getString("good_info["+i+"][good_code]"));
			map.put("num",pd.getString("good_info["+i+"][num]"));
			map.put("value",pd.getString("good_info["+i+"][value]"));
			dao.update("GoodMapper.updateGoodNumin",map);
		}
		dao.save("RentMapper.updateStatus",pd);
		dao.save("UserMapper.updaterevert",pd);
		return dao.save("RevertMapper.saverevert",pd);
	}

	/**
	 * 获取归还
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getrent(PageData pd) throws Exception {
		return (PageData) dao.findForObject("RevertMapper.getRevert", pd);
	}

	/**
	 * 获取归还用列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllRevert(PageData pd) throws Exception {
		return (List) dao.findForList("RevertMapper.getAllRevert", pd);
	}


	/**
	 * 删除归还记录
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Object deleteRevert(PageData pd) throws Exception {
		return dao.delete("RevertMapper.delete",pd);
	}

	/**
	 * 归还信息批量删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteReverts(List list)  throws Exception  {
		return dao.batchDelete("RevertMapper.deleteReverts",list);
    }

    public List getreservationById(PageData pd) throws Exception {
		return (List) dao.findForList("RevertMapper.getreservationById", pd);
    }
}
