package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.util.AppUtil;
import com.cyjt.oa.dao.hrm.UserContractDao;
import com.cyjt.oa.dao.system.AppUserDao;
import com.cyjt.oa.model.hrm.UserContract;
import com.cyjt.oa.model.info.ShortMessage;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.hrm.UserContractService;
import com.cyjt.oa.service.info.ShortMessageService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class UserContractServiceImpl extends BaseServiceImpl<UserContract>
		implements UserContractService {
	private UserContractDao dao;

	@Resource
	private AppUserDao appUserDao;

	@Resource
	private ShortMessageService shortMessageService;

	public UserContractServiceImpl(UserContractDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkContractNo(String contractNo) {
		return this.dao.checkContractNo(contractNo);
	}

	public List<UserContract> findTime(Long contractId) {
		return this.dao.findTime(contractId);
	}

	public void sendContractTime() {
		List<UserContract> list = this.dao.findByExpireDate();
		if (list.size() > 0) {
			StringBuffer sb = new StringBuffer("合同信息：");
			for (UserContract contract : list) {
				if (contract.getStatus().intValue() == 2)
					sb.append(contract.getContractType() + "已经终止合同.");
				else {
					sb.append(contract.getContractType() + "合同快要到期了.");
				}
			}
			sb.append("上司请提前提醒员工合同到期信息");
			Map map = AppUtil.getSysConfig();
			String username = (String) map.get("userContractStockUser");
			if (StringUtils.isNotEmpty(username)) {
				AppUser user = this.appUserDao.findByUserName(username);
				if (user != null) {
					this.shortMessageService.save(AppUser.SYSTEM_USER, user
							.getUserId().toString(), sb.toString(),
							ShortMessage.MSG_TYPE_SYS);
					this.logger.info("messages had sent to the manager!"
							+ user.getUsername());
				} else {
					this.logger.info("can not find the user in the system.");
				}
			} else {
				this.logger.info("can not find the name in the map.");
			}
			this.logger.info(sb.toString());
		} else {
			this.logger.info("没有续约合同.");
		}
	}
}
