package com.niyaowoa.goods.controller.user;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import com.niyaowoa.goods.utils.MD5Util;
import com.niyaowoa.goods.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-08 .
 */
@Controller
@RequestMapping("/OptUser")
public class OptUserController extends BaseController {
    @Autowired
    UserService userService;

    /**
     *
     * 用户列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView list() throws Exception {
        PageData pd  = this.getPageData();
        List list = userService.getAllUser(pd);
        ModelAndView mv  = getModelAndView();
        mv.addObject("varList",list);
        mv.addObject("tot",list.size());
        mv.setViewName("user/list");
        return mv;
    }

    /**
     * 展示用户界面
     * @return
     */
    @RequestMapping(value="/show")
    public ModelAndView show() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd = userService.getUserByID(pd);
        if(!pd.containsKey("overdue")){
            pd.put("overdue",0);
        }
        int over = Integer.parseInt(pd.getString("overdue"));
        pd.put("overdue",over>=0?over:0);
        pd.put("arrears_amount",over*10);
        mv.addObject("user",pd);
        mv.setViewName("user/show");
        return mv;
    }

    @RequestMapping(value="/add")
    public ModelAndView add() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        mv.setViewName("user/add");
        return mv;
    }
    /**
     * 删除用户
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = userService.deleteUser(pd);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 用户批量删除
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteMuch")
    @ResponseBody
    public Object deleteMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        String userid = pd.getString("id");
        List list = DeleteDeal.dealString(userid);
        if(list!=null)pd.put("list",list);
        Object result = userService.deleteMuchUser(list);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

    /*************停用 启用账户***************/
    /**
     * 停用账户
     * @return
     * @throws Exception
     */
    @RequestMapping("/stop")
    @ResponseBody
    public Object stop() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = userService.updateStop(pd);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 启用账户
     * @return
     * @throws Exception
     */
    @RequestMapping("/start")
    @ResponseBody
    public Object start() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = userService.updateStart(pd);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 判断用户名是否存在
     * @return
     * @throws Exception
     */
    @RequestMapping("/Isuser")
    @ResponseBody
    public Object IsUser() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = userService.getUsername(pd);
        map.put("msg",result==null?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 添加用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public Object addUser() throws Exception {
        PageData pd = this.getPageData();
        pd = UserUtil.addUse(pd);
        Object result1 = userService.saveUser(pd);
        pd = userService.getUser(pd);
        pd.put("arrears_amount",0);
        pd.put("overdue",0);
        Object result2 = userService.saveinfo(pd);
        Map map = new HashMap();
        map.put("msg",((Integer)result1>0&&(Integer)result2>0)?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 跳转到更新密码的页面
     * @return
     */
    @RequestMapping(value = "/gochang_password")
    public  ModelAndView gochang_password() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd = userService.getUserByID(pd);
        mv.addObject("user",pd);
        mv.setViewName("user/change_password");
        return mv;
    }

    /**
     * 更新密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Object updatePassword() throws Exception {
        PageData pd = this.getPageData();
        pd.put("username",pd.getString("username").trim());
        pd.put("newpassword", MD5Util.convertMD5(pd.getString("newpassword")));
        Object result = userService.updatePassword(pd);
        Map map = new HashMap();
        map.put("msg",((Integer)result>0)?"更新成功":"更新失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 跳到编辑
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goedit")
    public  ModelAndView goedit() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd = userService.getUserByID(pd);
        mv.addObject("user",pd);
        mv.setViewName("user/edit");
        return mv;
    }

    /**
     * 更新密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit() throws Exception {
        PageData pd = this.getPageData();
        pd.put("username",pd.getString("username").trim());
        Object result = userService.updateUser(pd);
        Map map = new HashMap();
        map.put("msg",((Integer)result>0)?"更新成功":"更新失败");
        return AppUtil.returnObject(pd,map);
    }


}
