package com.niyaowoa.goods.controller.rent;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.entity.User;
import com.niyaowoa.goods.service.good.CategoryService;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.rent.RentService;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.Const;
import com.niyaowoa.goods.utils.DateUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Rent")
public class RentController extends BaseController {

    @Autowired
    RentService rentService;
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
            list = rentService.getAllRent(pd);
        else {
            User user = (User)session.getAttribute(Const.SESSION_USER);
            pd.put("user_id",user.getId());
            list = rentService.getreservationById(pd);
        }

        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("rent/list");
        return mv;
    }

    /**
     * 跳转到借用添加
     * @return
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List goods = goodService.getAllGood(pd);
        List users = userService.getAllUser(pd);
        mv.addObject("goods",goods);
        mv.addObject("users",users);
        mv.setViewName("rent/add");
        return mv;
    }

    /**
     * 判断编码是否存在
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Iscode")
    @ResponseBody
    public Object Iscode() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        pd = rentService.getrent(pd);
        map.put("msg",pd==null?"true":"false");
        return AppUtil.returnObject(pd,map);
     }

    /**
     * 借用添加
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "/add")
     @ResponseBody
     public Object add() throws Exception {
        PageData pd = this.getPageData();
        pd.put("rent_time",DateUtil.getTime());
        Map map = new HashedMap();
        PageData re = rentService.getisrent(pd);
        if(re!=null){
            map.put("msg","用户有借用记录");
            return AppUtil.returnObject(pd,map);
        }
        Object result = rentService.saverent(pd);
        map.put("msg",(Integer)result>0?"保存成功":"保存失败");
        return AppUtil.returnObject(pd,map);
     }

    /**
     * 借用记录删除
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public Object delete() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result = rentService.deleteRent(pd);
        map.put("msg",(Integer)result>0?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 展示借用信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/show")
    @Transactional
    public ModelAndView show() throws Exception {
        PageData pd = this.getPageData();
        ModelAndView mv = this.getModelAndView();
        pd = rentService.getrent(pd);
        List list = rentService.getrent_good(pd);
        mv.addObject("varList",list);
        mv.addObject("user_rent",pd);
        mv.setViewName("rent/show");
        return mv;
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
        String rent_id = pd.getString("rent_id");
        List list = DeleteDeal.dealString(rent_id);
        if(list!=null)pd.put("list",list);
        Object result = rentService.deleteRents(list);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

}
