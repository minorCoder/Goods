package com.niyaowoa.goods.interceptor;

/**
 * Created by niyaowoa on 2017-04-06 .
 */

import com.niyaowoa.goods.entity.User;
import com.niyaowoa.goods.utils.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @Project: EWMS3
 * @Title: com.fh.interceptor
 * @Description: 拦截器
 * @author: Mipo
 * @version V3.0
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        String path = request.getServletPath();
        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
            return true;
        } else {
            // shiro管理的session
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            User user = (User) session.getAttribute(Const.SESSION_USER);
            if (user != null) {
                return true;
            } else {
                // 登陆过滤
                response.sendRedirect(request.getContextPath() + Const.LOGIN);
                return false;
                // return true;
            }
        }
    }

}