package com.niyaowoa.goods.controller.revert;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.entity.User;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.rent.RentService;
import com.niyaowoa.goods.service.revert.RevertService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Revert")
public class RevertController extends BaseController {

    @Autowired
    RevertService revertService;
    /**
     * 展示归还列表
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
            list = revertService.getAllRevert(pd);
        else {
            User user = (User)session.getAttribute(Const.SESSION_USER);
            pd.put("user_id",user.getId());
            list = revertService.getreservationById(pd);
        }

        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("revert/list");
        return mv;
    }

    /**
     *
     * 添加归还记录
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "/add")
     @ResponseBody
     public Object add() throws Exception {
        PageData pd = this.getPageData();
        pd.put("revert_time",DateUtil.getTime());
        Map map = new HashedMap();
        Object result = revertService.saverevert(pd);
        map.put("msg",(Integer)result>0?"归还成功":"归还失败");
        return AppUtil.returnObject(pd,map);
     }

    /**
     *
     * 删除归还记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result = revertService.deleteRevert(pd);
        map.put("msg",(Integer)result>0?"删除成功":"删除失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * rent批量删除
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteMuch")
    @ResponseBody
    public Object deleteMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        String revert_id = pd.getString("revert_id");
        List list = DeleteDeal.dealString(revert_id);
        if(list!=null)pd.put("list",list);
        Object result = revertService.deleteReverts(list);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

}
