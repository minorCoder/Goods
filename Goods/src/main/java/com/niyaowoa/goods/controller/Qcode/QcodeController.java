package com.niyaowoa.goods.controller.Qcode;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.entity.User;
import com.niyaowoa.goods.service.good.CategoryService;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.rent.RentService;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.Const;
import com.niyaowoa.goods.utils.DeleteDeal;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Qcode")
public class QcodeController extends BaseController {

    @Autowired
    RentService rentService;

    @Autowired
    GoodService goodService;
    @Autowired
    UserService userService;

    /**
     * 处理二维码扫描的结果
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deal")
    public ModelAndView deal() throws Exception {
        ModelAndView mv = this.getModelAndView();
        HttpSession session = this.getRequest().getSession();
        //判断是否登录
        if(session==null){
            mv.setViewName("/index");
            return mv;
        }
        User user = (User)session.getAttribute(Const.SESSION_USER);
        if(Integer.parseInt(user.getRole())>0){
            mv.setViewName("/index");
            return mv;
        }
        PageData pd = this.getPageData();
        PageData mid = userService.getUserByID(pd);
        List users = new ArrayList();
        users.add(mid);
        pd.put("user_id",pd.get("id"));
        pd = rentService.getisrent(pd);
        if(pd==null){
            List goods = goodService.getAllGood(pd);
            mv.addObject("goods",goods);
            mv.addObject("users",users);
            mv.setViewName("rent/add");
            return mv;
        }else {
            pd = rentService.getrent(pd);
            List list = rentService.getrent_good(pd);
            mv.addObject("varList",list);
            mv.addObject("user_rent",pd);
            mv.setViewName("rent/show");
            return mv;
        }

    }


}
