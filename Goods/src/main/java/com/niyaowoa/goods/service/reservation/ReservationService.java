package com.niyaowoa.goods.service.reservation;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("reservationService")
public class ReservationService {

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
	public Object savereservation(PageData pd) throws Exception {
		int tot = Integer.parseInt(pd.getString("tot"));
		//JSONArray jsonArray = new JSONArray();
		List list = new ArrayList();
		for(int i = 0;i<tot;i++){
			Map map = new HashMap();
			//Map map1 = new HashMap();
			map.put("reservation_id",pd.getString("reservation_id"));
			map.put("good_code",pd.getString("good_info["+i+"][good_code]"));
			map.put("num",pd.getString("good_info["+i+"][num]"));
			map.put("value",pd.getString("good_info["+i+"][value]"));
			//dao.update("GoodMapper.updateGoodNumout",map);
			list.add(map);
		}

		//List list = jsonArray.toList(jsonArray, HashMap.class);
		//dao.batchUpdate("GoodMapper.updateGoodout",list);
		dao.save("ReservationMapper.save", pd);
		return dao.batchSave("ReservationMapper.save_reservation",list);
	}

	/**
	 * 获取借用
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getreservation(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ReservationMapper.getReservation", pd);
	}

	/**
	 * 查找預約信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getreservation_good(PageData pd ) throws Exception {
		return  (List) dao.findForList("ReservationMapper.getReservation_good",pd);
	}
	/**
	 * 获取借用列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllReservation(PageData pd) throws Exception {
		return (List) dao.findForList("ReservationMapper.getAllReservation", pd);
	}


	/**
	 * 删除借用
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Object deleteReservation(PageData pd) throws Exception {
		dao.delete("ReservationMapper.deleteReservation_good",pd);
		return dao.delete("ReservationMapper.deleteReservation", pd);
	}


	/**
	 * 更新借用信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateReservation(PageData pd) throws Exception {
		return dao.update("ReservationMapper.updateReservation",pd);
	}


	/**
	 * 借用信息批量删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Transactional
    public Object deletereservations(List list)  throws Exception  {
		dao.batchDelete("ReservationMapper.deleteReservation_goods",list);
    	return dao.batchDelete("ReservationMapper.deleteReservations",list);
    }

	/**
	 * 是否有预约
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getisreservation(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ReservationMapper.getisReservation", pd);
	}

	public List getreservationById(PageData pd) throws Exception {
		return (List) dao.findForList("ReservationMapper.getreservationById", pd);
	}
}
