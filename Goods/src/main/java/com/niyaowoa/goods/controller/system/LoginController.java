package com.niyaowoa.goods.controller.system;


import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.entity.User;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-05 .
 */

@Controller
@RequestMapping("/system")
public class LoginController extends BaseController{

    @Autowired
    UserService userService;
    /**
     * 跳转到登录界面
     * @return mv
     */
    @RequestMapping(value = "/index_to_login")
    public ModelAndView gologin(){
       ModelAndView mv = getModelAndView();
       mv.setViewName("system/login");
       return mv;
    }

    /**
     * 登录验证
     * @return mv
     */
    @RequestMapping(value = "/submitLogin")
    @ResponseBody
    public Object login() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        PageData pd = this.getPageData();
        String errInfo = "";
        String message = "";
        // shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        String USERNAME = pd.getString("username");
        String PASSWORD = pd.getString("password");
        pd.put("username", USERNAME);
        //MD5加密
        String password = MD5Util.convertMD5(PASSWORD);
        pd.put("password",password);
        pd = userService.getUser(pd);
        if (pd != null) {
            if(Integer.parseInt(pd.getString("statue"))==0){
                errInfo = "usererror"; // 验证成功
                message = "用户未启用";
            }else {
                    //更新最后登录时间
                    pd.put("LAST_LOGIN", DateUtil.getTime().toString());
                    userService.updateLastLogin(pd);
                    //设置user对象
                    User user = new User();
                    user.setId(Integer.parseInt(pd.getString("id")));
                    user.setUsername(pd.getString("username"));
                    user.setPassword(pd.getString("password"));
                    user.setName(pd.getString("name"));
                    user.setAddress(pd.getString("address"));
                    user.setID_card(pd.getString("ID_card"));
                    user.setRole(pd.getString("role"));
                    user.setSex(pd.getString("sex"));
                    user.setPhone(pd.getString("phone"));
                    //system.getReg_date(DateUtil.fomatDate(pd.getString("reg_date")));
                    session.setAttribute(Const.SESSION_USER, user);
                    session.setAttribute("role",user.getRole());
                    session.removeAttribute(Const.SESSION_SECURITY_CODE);
                    // shiro加入身份验证
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(
                            USERNAME, PASSWORD);

                    try {
                        subject.login(token);
                    } catch (AuthenticationException e) {
                        errInfo = "身份验证失败！";
                    }
            }

        } else {
            errInfo = "usererror"; // 用户名或密码有误
            message = "用户名或密码有误";
        }
        if (Tools.isEmpty(errInfo)) {
            errInfo = "success"; // 验证成功
            message = "验证成功";
        }
        map.put("result", errInfo);
        map.put("message",message);
        return AppUtil.returnObject(new PageData(), map);
    }


    /**
     * 跳转到后台界面
     * @return mv
     */
    @RequestMapping(value = "/goAdmin")
    public ModelAndView goAdmin(){
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/admin");
        return mv;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();

        // shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        session.removeAttribute(Const.SESSION_USER);
        session.removeAttribute("role");


        // shiro销毁登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        mv.setViewName("system/login");
        return mv;
    }

    /**
     * 改密码
     * @return
     */
    @RequestMapping(value = "/gochang_password")
    public ModelAndView change(){
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/change_password");
        return mv;
    }

    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Object updatepassword() throws Exception {
        PageData pd = this.getPageData();
        HttpSession session = this.getRequest().getSession();
        User user = (User)session.getAttribute(Const.SESSION_USER);
        Map map = new HashMap();
        //解密后
        if(user.getPassword().equals(MD5Util.convertMD5(pd.getString("oldpassword")))){
            map.put("msg","原密码不正确");
            return AppUtil.returnObject(pd,map);
        }
        pd.put("username",pd.getString("username").trim());
        pd.put("newpassword",MD5Util.convertMD5(pd.getString("newpassword")));
        Object result = userService.updatePassword(pd);

        map.put("msg",((Integer)result>0)?"更新成功":"更新失败");
        return AppUtil.returnObject(pd,map);

    }
}
