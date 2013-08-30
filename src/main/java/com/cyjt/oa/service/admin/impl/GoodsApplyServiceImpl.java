package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.GoodsApplyDao;
import com.cyjt.oa.model.admin.GoodsApply;
import com.cyjt.oa.model.admin.OfficeGoods;
import com.cyjt.oa.model.info.ShortMessage;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.admin.GoodsApplyService;
import com.cyjt.oa.service.admin.OfficeGoodsService;
import com.cyjt.oa.service.info.ShortMessageService;
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
