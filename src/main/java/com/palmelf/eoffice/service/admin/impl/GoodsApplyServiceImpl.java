package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.GoodsApplyDao;
import com.palmelf.eoffice.model.admin.GoodsApply;
import com.palmelf.eoffice.model.admin.OfficeGoods;
import com.palmelf.eoffice.model.info.ShortMessage;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.admin.GoodsApplyService;
import com.palmelf.eoffice.service.admin.OfficeGoodsService;
import com.palmelf.eoffice.service.info.ShortMessageService;

import javax.annotation.Resource;

public class GoodsApplyServiceImpl extends BaseServiceImpl<GoodsApply>
		implements GoodsApplyService {
	private GoodsApplyDao dao;

	@Resource
	private OfficeGoodsService officeGoodsService;

	@Resource
	private ShortMessageService shortMessageService;

	public GoodsApplyServiceImpl(GoodsApplyDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Integer check(Long applyId, Short approvalStatus) {
		GoodsApply goodsApply = this.dao.get(applyId);
		OfficeGoods officeGoods = goodsApply.getOfficeGoods();
		String content = "";

		Integer rStatus = Integer.valueOf(1);

		if (GoodsApply.PASS_APPLY.equals(approvalStatus)) {
			Integer left = Integer.valueOf(officeGoods.getStockCounts()
					.intValue() - goodsApply.getUseCounts().intValue());
			if (left.intValue() > 0) {
				content = "你申请的办公用品为:" + officeGoods.getGoodsName()
						+ ",已经通过审批，请查收~";
				rStatus = Integer.valueOf(1);
			} else {
				content = "你申请的办公用品为:" + officeGoods.getGoodsName()
						+ ",已经通过审批，请查收~";
				rStatus = Integer.valueOf(2);
			}
			officeGoods.setStockCounts(left);
			this.officeGoodsService.save(officeGoods);
		} else {
			rStatus = Integer.valueOf(3);
			content = "你申请的办公用品为:" + officeGoods.getGoodsName() + ",未通过审批，请查看~";
		}

		goodsApply.setApprovalStatus(approvalStatus);
		this.dao.save(goodsApply);

		Long receiveId = goodsApply.getUserId();
		this.shortMessageService.save(AppUser.SYSTEM_USER,
				receiveId.toString(), content, ShortMessage.MSG_TYPE_SYS);

		return rStatus;
	}
}
