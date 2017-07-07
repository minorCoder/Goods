package com.niyaowoa.goods.controller.storage;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.storage.StackinService;
import com.niyaowoa.goods.service.storage.StorageService;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.DateUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Stackin")
public class StackinController extends BaseController {

    @Autowired
    StackinService stackinService;
    @Autowired
    UserService userService;
    @Autowired
    GoodService goodService;
    @Autowired
    StorageService storageService;
    /**
     * 展示入库列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView golist() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List list = stackinService.getAllStackin(pd);
        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("stackin/list");
        return mv;
    }
    /**
     * 跳转添加界面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List users = userService.getAllMangeUser(pd);
        List goods = goodService.getAllGood(pd);
        List storages = storageService.getStorages(pd);
        mv.addObject("users",users);
        mv.addObject("goods",goods);
        mv.addObject("storages",storages);
        mv.setViewName("stackin/add");
        return mv;
    }

    /**
     * 添加
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() throws Exception {
        PageData pd = this.getPageData();
        pd.put("create_time",DateUtil.getTime().toString());
        Object result = stackinService.saveStackin(pd);
        pd.put("storage_id",pd.getString("storage"));
        pd.put("good_id",pd.getString("good_code"));
        Integer result2 = -1;
        Integer result3 = -1;
        //查看库存中是否已存在
        PageData stack = storageService.getStack(pd);
        if(stack==null){
            result2 =(Integer) storageService.saveStack(pd);
            result3 = (Integer) goodService.updateGoodNumin(pd);
        }else{
            result2 = (Integer) goodService.updateGoodNumin(pd);
            result3 = (Integer) storageService.updateStack(pd);
        }
        Map map = new HashMap();
        map.put("msg",(Integer)result>0&&result2>0&&result3>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 判断批次是否存在
     * @return
     * @throws Exception
     */
    @RequestMapping("/Iscode")
    @ResponseBody
    public Object Iscode() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = stackinService.getStackin(pd);
        map.put("msg",result==null?"true":"false");
        return AppUtil.returnObject(pd,map);
    }



    /**
     * 删除入库记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = stackinService.deleteStackin(pd);
        map.put("msg",(Integer)result>0?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 删除入库记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMuch")
    @ResponseBody
    public Object deleteMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        List list = DeleteDeal.dealString(pd.getString("batchcode"));
        Object result = stackinService.deleteStackins(list);
        map.put("msg",(Integer)result>0?"删除成功":"删除失败");
        return AppUtil.returnObject(pd,map);
    }


}
