package com.niyaowoa.goods.service.good;

import com.niyaowoa.goods.dao.DaoSupport;
import com.niyaowoa.goods.entity.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 添加分类
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object saveCategory(PageData pd) throws Exception {
		return dao.save("CategoryMapper.save", pd);
	}

	/**
	 * 获取分类
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getCategory(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CategoryMapper.getCategory", pd);
	}

	public PageData getCategoryByName(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CategoryMapper.getCategoryByName", pd);
	}
	/**
	 * 获取分类列表
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List getAllCategory(PageData pd) throws Exception {
		return (List) dao.findForList("CategoryMapper.getAllCategory", pd);
	}


	/**
	 * 删除分类
	 *
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object deleteCategory(PageData pd) throws Exception {
		return dao.delete("CategoryMapper.deleteCategory", pd);
	}


	/**
	 * 更新分类
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Object updateCategory(PageData pd) throws Exception {
		return dao.update("CategoryMapper.updateCategory",pd);
	}

	/**
	 * 多分类删除
	 * @param list
	 * @return
	 * @throws Exception
	 */
    public Object deleteMuchCategory(List list)  throws Exception  {
		return dao.batchDelete("CategoryMapper.deleteMuchCategory",list);
    }
}
