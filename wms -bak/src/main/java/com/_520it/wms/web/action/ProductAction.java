package com._520it.wms.web.action;

import java.io.File;

import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.FileUploadUtil;
import com._520it.wms.util.StringUtil;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com._520it.wms.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductService service;

	@Setter
	private IBrandService brandService;

	@Getter
	private ProductQueryObject qo = new ProductQueryObject();

	@Getter
	private Product product = new Product();

	@Setter
	private File pic;

	@Setter
	private String picFileName;

	@RequiredPermission("product列表")
	@InputConfig(methodName = "input")
	public String execute() {
		try {
			put("brands", brandService.list());
			put("result", service.pageQuery(qo));

		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("product编辑")
	public String input() {
		try {
			put("brands", brandService.list());
			if (product.getId() != null) {
				product = service.get(product.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("product保存/更新")
	public String saveOrUpdate() {
		try {

			if (pic != null && StringUtil.hasLength(product.getImagePath())) {
				System.out.println(pic + " " + product.getImagePath());
				FileUploadUtil.deleteFile(product.getImagePath());
			}
			if (pic != null) {
				String imagePath = FileUploadUtil.uploadFile(pic, picFileName);
				product.setImagePath(imagePath);
			}
			if (product.getId() == null) {
				service.save(product);
				addActionMessage("保存成功");
			} else {

				service.update(product);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("product删除")
	public String delete() throws Exception {
		try {
			if (product.getImagePath() != null) {
				FileUploadUtil.deleteFile(product.getImagePath());
			}
			if (product.getId() != null) {
				service.delete(product.getId());
				putMsg("删除成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String selectProductList() throws Exception {
		put("brands", brandService.list());
		put("result", service.pageQuery(qo));
		return "selectProductList";
	}

}
