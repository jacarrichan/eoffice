package com.cyjt.oa.action.document;

import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.JsonUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.document.PaintTemplate;
import com.cyjt.oa.service.document.PaintTemplateService;
import flexjson.JSONSerializer;
import java.util.List;
import javax.annotation.Resource;

public class PaintTemplateAction extends BaseAction {

	@Resource
	private PaintTemplateService paintTemplateService;
	private PaintTemplate paintTemplate;
	private Long ptemplateId;

	public Long getPtemplateId() {
		return this.ptemplateId;
	}

	public void setPtemplateId(Long ptemplateId) {
		this.ptemplateId = ptemplateId;
	}

	public PaintTemplate getPaintTemplate() {
		return this.paintTemplate;
	}

	public void setPaintTemplate(PaintTemplate paintTemplate) {
		this.paintTemplate = paintTemplate;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.paintTemplateService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		JSONSerializer json = JsonUtil.getJSONSerializer(new String[0]);
		json.exclude(new String[] { "class" });
		buff.append(json.serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.paintTemplateService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		PaintTemplate paintTemplate = this.paintTemplateService
				.get(this.ptemplateId);

		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer json = JsonUtil.getJSONSerializer(new String[0]);
		json.exclude(new String[] { "class" });
		sb.append(json.serialize(paintTemplate));

		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.paintTemplate.getPtemplateId() == null) {
			this.paintTemplateService.save(this.paintTemplate);
		} else {
			PaintTemplate orgPaintTemplate = this.paintTemplateService
					.get(this.paintTemplate.getPtemplateId());
			try {
				BeanUtil.copyNotNullProperties(orgPaintTemplate,
						this.paintTemplate);
				this.paintTemplateService.save(orgPaintTemplate);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return "success";
	}
}
