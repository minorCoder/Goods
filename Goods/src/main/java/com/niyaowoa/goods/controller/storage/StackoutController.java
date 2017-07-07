package com.niyaowoa.goods.controller.storage;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.storage.StackinService;
import com.niyaowoa.goods.service.storage.StackoutService;
import com.niyaowoa.goods.service.storage.StorageService;
import com.niyaowoa.goods.service.user.UserService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.DateUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Stackout")
public class StackoutController extends BaseController {

    @Autowired
    StackoutService stackoutService;
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
        List list = stackoutService.getAllStackout(pd);
        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("stackout/list");
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
        List storages = storageService.getStorages(pd);
        List users = userService.getAllMangeUser(pd);
        //如果找到仓库放入仓库编码
        if(storages!=null) pd.put("storage_id",((PageData)storages.get(0)).get("storage_id"));
        //查找对应仓库的武器和数量
        List goods = storageService.getGoods(pd);
        mv.addObject("users",users);
        mv.addObject("goods",goods);
        mv.addObject("storages",storages);
        mv.setViewName("stackout/add");
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
        Map map = new HashMap();
        pd.put("create_time",DateUtil.getTime().toString());
        pd.put("storage_id",pd.getString("storage"));
        pd.put("good_id",pd.getString("good_code"));
        Integer result2 = -1;
        Integer result3 = -1;
        //查看库存中是否已存在
        PageData stack = storageService.getStack(pd);
        //判断库存数量是否足够
        if(Integer.parseInt(stack.getString("num"))<Integer.parseInt(pd.getString("num"))){
            map.put("msg","数量不足");
            return AppUtil.returnObject(pd,map);
        }
        Object result = stackoutService.saveStackout(pd);
        //更新库存数量及物品显示数量
        if(stack==null){
            result2 =(Integer) storageService.saveStack(pd);
        }else{
            result2 = (Integer) storageService.updateStackout(pd);
            result3 = (Integer) goodService.updateGoodNumout(pd);
        }

        map.put("msg",(Integer)result>0&&result2>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 判断批次是否存在
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Iscode")
    @ResponseBody
    public Object Iscode() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = stackoutService.getStackout(pd);
        map.put("msg",result==null?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 联动获取仓库库存
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGood",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getGood() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        List list = storageService.getGoods(pd);
        return AppUtil.returnObject(list);
    }



    /**
     * 删除出库记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        Object result = stackoutService.deleteStackout(pd);
        map.put("msg",(Integer)result>0?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 删除出库记录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMuch")
    @ResponseBody
    public Object deleteMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        List list = DeleteDeal.dealString(pd.getString("batchcode"));
        Object result = stackoutService.deleteStackouts(list);
        map.put("msg",(Integer)result>0?"删除成功":"删除失败");
        return AppUtil.returnObject(pd,map);
    }


}
