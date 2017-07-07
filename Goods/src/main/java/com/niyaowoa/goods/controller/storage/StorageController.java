package com.niyaowoa.goods.controller.storage;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.good.CategoryService;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.service.storage.StorageService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Storage")
public class StorageController extends BaseController {

    @Autowired
    StorageService storageService;


    /**
     * 展示库存列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView golist() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List list = storageService.getAllStorage(pd);
        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("storage/list");
        return mv;
    }

    /**
     * 跳转到add界面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("storage/add");
        return mv;
    }


    /**
     * 跳转到edit
     * @return
     * @throws Exception
     */
    @RequestMapping("/goedit")
    public ModelAndView goedit() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd = storageService.getStorage(pd);
        mv.addObject("storage",pd);
        mv.setViewName("storage/edit");
        return mv;
    }
    /**
     * 展示仓库列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/storage_list")
    public ModelAndView storage_list() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List list = storageService.getStorages(pd);
        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("storage/storage_list");
        return mv;
    }
    /**
     * 判断物品编码是否存在
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Iscode")
    @ResponseBody
    public Object Iscode() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        // pd.put("good_code",Integer.parseInt(pd.getString("good_code")));
        pd = storageService.getStorage(pd);
        map.put("msg",pd==null?"true":"false");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 删除物品
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result = storageService.deleteStorage(pd);
        map.put("msg",(Integer)result>0?"删除成功":"删除失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 删除多仓库
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delMuch")
    @ResponseBody
    public Object delMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        List list = DeleteDeal.dealString(pd.getString("storage_id"));
        Object result = storageService.deleteStorages(list);
        map.put("msg",(Integer)result>0?"删除成功":"删除失败");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 添加仓库
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result = storageService.saveStorage(pd);
        map.put("msg",(Integer)result>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 添加仓库
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result = storageService.updateStorage(pd);
        map.put("msg",(Integer)result>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }


}
