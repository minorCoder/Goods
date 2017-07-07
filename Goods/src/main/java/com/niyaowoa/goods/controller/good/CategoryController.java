package com.niyaowoa.goods.controller.good;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.good.CategoryService;
import com.niyaowoa.goods.utils.AppUtil;
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
@RequestMapping("/Category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;

    /**
     * 展示分类列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView golist() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List list = categoryService.getAllCategory(pd);
        mv.addObject("varList",list);
        mv.addObject("tot",list.size());
        mv.setViewName("category/list");
        return mv;
    }

    /**
     * 跳转编辑页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goedit")
    public ModelAndView goedit() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd = categoryService.getCategory(pd);
        mv.addObject("category",pd);
        mv.setViewName("category/edit");
        return mv;
    }



    /**
     * 查看是否存在分类pid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Ispid")
    @ResponseBody
    public Object Ispid() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        String pstart="";
        if(pd.containsKey("startpid"))
         pstart = pd.getString("startpid").trim();
        pd = categoryService.getCategory(pd);
        if(pd==null){
            map.put("msg","true");
        }else {
            if(pd.getString("pid").trim().equals(pstart)){
                map.put("msg","true");
            }else{
                map.put("msg","false");
            }
        }
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 查看是否存在分类名
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/IsName")
    @ResponseBody
    public Object IsName() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashMap();
        String pstart = "";
        if(pd.containsKey("oldName"))
           pstart = pd.getString("oldName").trim();
        pd = categoryService.getCategoryByName(pd);
        if(pd==null){
            map.put("msg","true");
        }else {
            if(pd.getString("name").equals(pstart)){
                map.put("msg","true");
            }else{
                map.put("msg","false");
            }
        }
        return AppUtil.returnObject(pd,map);
    }


    /**
     * 编辑分类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit() throws Exception {
        PageData pd  = this.getPageData();
        Map map = new HashedMap();
        Object result  = categoryService.updateCategory(pd);
        map.put("msg",(Integer)result>0?"更新成功":"更新失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 删除分类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() throws Exception {
        PageData pd  = this.getPageData();
        Map map = new HashedMap();
        Object result  = categoryService.deleteCategory(pd);
        map.put("msg",(Integer)result>0?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 多分类删除
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMuch")
    @ResponseBody
    public Object deleteMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        String categoryidid = pd.getString("id");
        List list = DeleteDeal.dealToString(categoryidid);
        if(list!=null)pd.put("list",list);
        Object result = categoryService.deleteMuchCategory(list);
        map.put("msg",(Integer)result>0?"成功":"失败");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 跳转到add界面
     * @return
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd(){
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("category/add");
        return mv;
    }

    /**
     * 添加分类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        Object result = categoryService.saveCategory(pd);
        map.put("msg",(Integer)result>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }



}
