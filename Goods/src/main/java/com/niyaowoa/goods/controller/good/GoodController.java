package com.niyaowoa.goods.controller.good;

import com.niyaowoa.goods.controller.base.BaseController;
import com.niyaowoa.goods.entity.PageData;
import com.niyaowoa.goods.service.good.CategoryService;
import com.niyaowoa.goods.service.good.GoodService;
import com.niyaowoa.goods.utils.AppUtil;
import com.niyaowoa.goods.utils.DeleteDeal;
import com.niyaowoa.goods.utils.UserUtil;
import com.sun.tools.internal.ws.processor.model.Model;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niyaowoa on 2017-04-15 .
 */
@Controller
@RequestMapping("/Good")
public class GoodController extends BaseController {

    @Autowired
    GoodService goodService;

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
        List list = goodService.getAllGood(pd);
        mv.addObject("tot",list.size());
        mv.addObject("varList",list);
        mv.setViewName("good/list");
        return mv;
    }
    /**
     * 跳转添加物品页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        List list = categoryService.getAllCategory(pd);
        mv.addObject("varList",list);
        mv.setViewName("good/add");
        return mv;
    }


    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload(MultipartFile file) {
            if(!file.isEmpty()) {
                String bathpath = getRequest().getSession().getServletContext().getRealPath("/");
                File Folder = new File(bathpath + "static/photos/");
                if (!Folder.exists()) {
                    Folder.mkdirs();
                }
                File target = new File(Folder, file.getOriginalFilename());

                try {
                    file.transferTo(target);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return "success";
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
        pd = goodService.getGood(pd);
        map.put("msg",pd==null?"true":"false");
        return AppUtil.returnObject(pd,map);
    }

    /**
     * 添加物品
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        String startName = pd.getString("good_photo");
        String tr = startName.substring(startName.lastIndexOf("."));
        changeName(startName,pd.getString("good_code"));
        String url = "photos/"+pd.getString("good_code")+tr;
        pd.put("good_photo",url);
        Object result = goodService.savegood(pd);
        map.put("msg",(Integer)result>0?"添加成功":"添加失败");
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
        pd = goodService.getGood(pd);
        String bathpath = getRequest().getSession().getServletContext().getRealPath("/");
        File file = new File (bathpath+"static/"+pd.getString("good_photo"));
        if(file.exists()&&file.isFile()){file.delete();}
        Object result = goodService.deleteGood(pd);
        map.put("msg",(Integer)result>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 删除多物品
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delMuch")
    @ResponseBody
    public Object delMuch() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        List list = DeleteDeal.dealString(pd.getString("good_code"));
        String bathpath = getRequest().getSession().getServletContext().getRealPath("/");
        goodService.deletePhotoes(list,bathpath);
        Object result = goodService.deleteGoods(list);
        map.put("msg",(Integer)result>0?"添加成功":"添加失败");
        return AppUtil.returnObject(pd,map);
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
        List list = categoryService.getAllCategory(pd);
        pd = goodService.getGood(pd);
        mv.addObject("good",pd);
        mv.addObject("varList",list);
        mv.setViewName("good/edit");
        return mv;
    }


    /**
     * 修改物品
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        if (pd.containsKey("good_photo")) {
            String startName = pd.getString("good_photo");
            String tr = startName.substring(startName.lastIndexOf("."));
            changeName(startName, pd.getString("good_code"));
            String url = "photos/" + pd.getString("good_code") + tr;
            pd.put("good_photo", url);
        }
        Object result = goodService.updateGood(pd);
        map.put("msg",(Integer)result>0?"修改成功":"修改失败");
        return AppUtil.returnObject(pd,map);
    }
    /**
     * 转换名字为编号
     * @param startName
     * @param to
     */
    public void changeName(String startName,String to){
        String bathpath = getRequest().getSession().getServletContext().getRealPath("/");
        File Folder = new File(bathpath + "static/photos/");
        if(!Folder.exists()){
            Folder.mkdirs();
        }
        File fileName = new File(Folder.getPath()+"\\"+startName);
        String tr = startName.substring(startName.lastIndexOf("."));
        if(fileName.exists()){
            File tofile = new File(Folder.getPath()+"\\"+to+tr);
            if(!tofile.exists())
                fileName.renameTo(tofile);
            else {
                tofile.delete();
                fileName.renameTo(new File(Folder.getPath()+"\\"+to+tr));
            }
        }
    }

    /**
     * 展示页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/show")
    public ModelAndView show() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        pd = goodService.getGood(pd);
        mv.addObject("good",pd);
        mv.setViewName("good/show");
        return mv;
    }

    /**
     * 查找某个物品的信息页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/find")
    @ResponseBody
    public Object find() throws Exception {
        PageData pd = this.getPageData();
        Map map = new HashedMap();
        pd = goodService.getGood(pd);
        map.put("good",pd);
        return AppUtil.returnObject(pd,map);
    }

}
