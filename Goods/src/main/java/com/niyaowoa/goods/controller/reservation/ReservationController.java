package com.niyaowoa.goods.controller.reservation;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.entity.User;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.rent.RentService;
import com.niyaowoa.goods.service.reservation.ReservationService;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.Const;
import com.niyaowoa.goods.utils.DateUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Reservation")
public class ReservationController extends BaseController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    GoodService goodService;
    @Autowired
    UserService userService;
    /**
     * 展示借用列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView golist() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        HttpSession session = this.getRequest().getSession();
        List list;
        if(session.getAttribute("role").equals("0"))
            list = reservationService.getAllReservation(pd);
        else {
            User user = (User)session.getAttribute(Const.SESSION_USER);
            pd.put("reservator_id",user.getId());
            list = reservationService.getreservationById(pd);
        }
        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("reservation/list");
        return mv;
    }

    /**
     * 预约申请
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List goods = goodService.getAllGood(pd);
        User user = (User)(this.getRequest().getSession()).getAttribute(Const.SESSION_USER);
        List list = reservationService.getAllReservation(pd);
        int num = 1;
        if(list!=null)num = list.size()+1;
        mv.addObject("code",num);
        mv.addObject("goods",goods);
        mv.addObject("user",user);
        mv.setViewName("reservation/add");
        return mv;
    }
    /**
     * 预约添加
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() throws Exception {
        PageData pd = this.getPageData();
        pd.put("reservation_date",DateUtil.getTime());
        Map map = new HashedMap();
        PageData re = reservationService.getisreservation(pd);
        if(re!=null){
            map.put("msg","用户有预约记录");
            return AppUtil.returnObject(pd,map);
        }
        Object result =reservationService.savereservation(pd);
        map.put("msg",(Integer)result>0?"预约成功等待审核":"预约失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 审核
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/show")
    public ModelAndView show() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List goods = reservationService.getreservation_good(pd);
        pd = reservationService.getreservation(pd);
        mv.addObject("reservation",pd);
        mv.addObject("goods",goods);
        mv.setViewName("reservation/show");
        return mv;
    }

    /**
     * 预约添加
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public Object check() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result =reservationService.updateReservation(pd);
        map.put("msg",(Integer)result>0?"审核成功":"审核失败");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 预约后借用
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/rent")
    public ModelAndView rent() throws Exception {
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        Map map = new HashedMap();
        List goods = reservationService.getreservation_good(pd);
        pd = reservationService.getreservation(pd);
        mv.addObject("reservation",pd);
        mv.addObject("goods",goods);
        mv.setViewName("reservation/rent");
        return mv;
    }

    /**
     * 预约删除
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result =reservationService.deleteReservation(pd);
        map.put("msg",(Integer)result>0?"删除成功":"删除失败");
        return AppUtil.returnObject(pd,map);
    }


    /**
     * 预约批量删除
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteMuch")
    @ResponseBody
    public Object deleteMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        String reservation_id = pd.getString("reservation_id");
        List list = DeleteDeal.dealString(reservation_id);
        if(list!=null)pd.put("list",list);
        Object result = reservationService.deletereservations(list);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

}
