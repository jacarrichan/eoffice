/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : joffice21

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-09-03 19:54:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `albumId` bigint(20) NOT NULL AUTO_INCREMENT,
  `facePicPath` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`albumId`),
  UNIQUE KEY `albumId` (`albumId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------

-- ----------------------------
-- Table structure for `appointment`
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `appointId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `endTime` datetime NOT NULL,
  `inviteEmails` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isMobile` int(11) DEFAULT NULL,
  `isMsg` int(11) DEFAULT NULL,
  `location` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `notes` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startTime` datetime NOT NULL,
  `subject` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`appointId`),
  UNIQUE KEY `appointId` (`appointId`),
  KEY `FKA8155B9F50662A29` (`userId`),
  KEY `FKA8155B9F8D18473` (`userId`),
  CONSTRAINT `FKA8155B9F8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKA8155B9F50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of appointment
-- ----------------------------

-- ----------------------------
-- Table structure for `app_function`
-- ----------------------------
DROP TABLE IF EXISTS `app_function`;
CREATE TABLE `app_function` (
  `functionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `funKey` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `funName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`functionId`),
  UNIQUE KEY `functionId` (`functionId`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_function
-- ----------------------------
INSERT INTO `app_function` VALUES ('1', '_ArchFlowConfEdit', '修改配置');
INSERT INTO `app_function` VALUES ('2', '_ArchiveTypeTempQuery', '查看');
INSERT INTO `app_function` VALUES ('3', '_ArchivesTypeAdd', '添加类别');
INSERT INTO `app_function` VALUES ('4', '_ArchivesTypeEdit', '修改类别');
INSERT INTO `app_function` VALUES ('5', '_ArchivesTypeDel', '删除类别');
INSERT INTO `app_function` VALUES ('6', '_ArchivesTempAdd', '添加模板');
INSERT INTO `app_function` VALUES ('7', '_ArchivesTempEdit', '编辑模板');
INSERT INTO `app_function` VALUES ('8', '_ArchviesTempDel', '删除模板');
INSERT INTO `app_function` VALUES ('9', '_AchivesDrafAdd', '发文');
INSERT INTO `app_function` VALUES ('10', '_ArchivesDrafmQuery', '查看');
INSERT INTO `app_function` VALUES ('11', '_ArchivesDrafmEdit', '编辑');
INSERT INTO `app_function` VALUES ('12', '_ArchivesDrafmDel', '删除');
INSERT INTO `app_function` VALUES ('13', '_ArchivesIssueQuery', '查看');
INSERT INTO `app_function` VALUES ('14', '_ArchivesIssueEdit', '编辑');
INSERT INTO `app_function` VALUES ('15', '_ArchivesIssueLeadQuery', '查看');
INSERT INTO `app_function` VALUES ('16', '_ArchivesIssueLeadEdit', '编辑');
INSERT INTO `app_function` VALUES ('17', '_ArchivesIssueChargeQuery', '查看');
INSERT INTO `app_function` VALUES ('18', '_ArchivesIssueChargeEdit', '编辑');
INSERT INTO `app_function` VALUES ('19', '_ArchivesIssueProofQuery', '查看');
INSERT INTO `app_function` VALUES ('20', '_ArchivesIssueProofEdit', '管理附件');
INSERT INTO `app_function` VALUES ('21', '_ArchivesDocumentQuery', '查看');
INSERT INTO `app_function` VALUES ('22', '_ArchivesIssueMonitorQuery', '查看');
INSERT INTO `app_function` VALUES ('23', '_ArchivesIssueHasten', '催办');
INSERT INTO `app_function` VALUES ('24', '_ArchivesIssueManageQuery', '查看');
INSERT INTO `app_function` VALUES ('25', '_ArchivesIssueSearchQuery', '查看');
INSERT INTO `app_function` VALUES ('26', '_DocHistoryQuery', '查看');
INSERT INTO `app_function` VALUES ('27', '_DocHistoryDel', '删除');
INSERT INTO `app_function` VALUES ('28', '_ArchivesSignQuery', '查看');
INSERT INTO `app_function` VALUES ('29', '_ArchivesSignUp', '签收');
INSERT INTO `app_function` VALUES ('30', '_ArchivesRecQuery', '查看');
INSERT INTO `app_function` VALUES ('31', '_ArchivesRecAdd', '添加');
INSERT INTO `app_function` VALUES ('32', '_ArchivesRecEdit', '编辑');
INSERT INTO `app_function` VALUES ('33', '_ArchivesRecDel', '删除');
INSERT INTO `app_function` VALUES ('34', '_ArchivesHandleQuery', '查看');
INSERT INTO `app_function` VALUES ('35', '_LeaderReadQuery', '查看');
INSERT INTO `app_function` VALUES ('36', '_ArchDispatchQuery', '查看');
INSERT INTO `app_function` VALUES ('37', '_ArchUndertakeQuery', '查看');
INSERT INTO `app_function` VALUES ('38', '_ArchivesRecCtrlQuery', '查看');
INSERT INTO `app_function` VALUES ('39', '_ArchivesRecHasten', '催办');
INSERT INTO `app_function` VALUES ('40', '_ArchReadQuery', '查看');
INSERT INTO `app_function` VALUES ('41', '_ArchRecTypeQuery', '查看');
INSERT INTO `app_function` VALUES ('42', '_ArchRecTypeAdd', '添加');
INSERT INTO `app_function` VALUES ('43', '_ArchRecTypeEdit', '编辑');
INSERT INTO `app_function` VALUES ('44', '_ArchRecTypeDel', '删除');
INSERT INTO `app_function` VALUES ('45', '_AppUserQuery', '查看账号');
INSERT INTO `app_function` VALUES ('46', '_AppUserAdd', '添加账号');
INSERT INTO `app_function` VALUES ('47', '_AppUserEdit', '编辑账号');
INSERT INTO `app_function` VALUES ('48', '_AppUserDel', '删除账号');
INSERT INTO `app_function` VALUES ('49', '_AppUserReset', '重置密码');
INSERT INTO `app_function` VALUES ('50', '_AppUserAgent', '代办账号设置');
INSERT INTO `app_function` VALUES ('51', '_AppRoleList', '查看角色');
INSERT INTO `app_function` VALUES ('52', '_AppRoleAdd', '添加角色');
INSERT INTO `app_function` VALUES ('53', '_AppRoleEdit', '编辑角色');
INSERT INTO `app_function` VALUES ('54', '_AppRoleDel', '删除角色');
INSERT INTO `app_function` VALUES ('55', '_AppRoleGrant', '授权角色');
INSERT INTO `app_function` VALUES ('56', '_DepartmentQuery', '查看科室');
INSERT INTO `app_function` VALUES ('57', '_DepartmentAdd', '新建科室');
INSERT INTO `app_function` VALUES ('58', '_DepartmentEdit', '修改科室');
INSERT INTO `app_function` VALUES ('59', '_DepartmentDel', '删除科室');
INSERT INTO `app_function` VALUES ('60', '_UserSubAdd', '添加下属');
INSERT INTO `app_function` VALUES ('61', '_FileAttachQuery', '查看附件');
INSERT INTO `app_function` VALUES ('62', '_FileAttachEdit', '编辑附件');
INSERT INTO `app_function` VALUES ('63', '_FileAttachDel', '删除附件');
INSERT INTO `app_function` VALUES ('64', '_FlowQuery', '查看流程');
INSERT INTO `app_function` VALUES ('65', '_ProTypeManage', '流程类型');
INSERT INTO `app_function` VALUES ('66', '_FlowAdd', '发布流程');
INSERT INTO `app_function` VALUES ('67', '_FlowEdit', '编辑流程');
INSERT INTO `app_function` VALUES ('68', '_FlowDel', '删除流程');
INSERT INTO `app_function` VALUES ('69', '_FlowCheck', '查看');
INSERT INTO `app_function` VALUES ('70', '_FlowSetting', '人员设置');
INSERT INTO `app_function` VALUES ('71', '_DocFolderSharedManage', '公共文件夹管理');
INSERT INTO `app_function` VALUES ('72', '_DocPrivilegeQuery', '查看权限');
INSERT INTO `app_function` VALUES ('73', '_DocPrivilegeAdd', '添加权限');
INSERT INTO `app_function` VALUES ('74', '_DocPrivilegeEdit', '编辑权限');
INSERT INTO `app_function` VALUES ('75', '_DocPrivilegeDel', '删除权限');
INSERT INTO `app_function` VALUES ('76', '_PlanTypeQuery', '查看类型');
INSERT INTO `app_function` VALUES ('77', '_PlanTypeAdd', '添加类型');
INSERT INTO `app_function` VALUES ('78', '_PlanTypeEdit', '编辑类型');
INSERT INTO `app_function` VALUES ('79', '_PlanTypeDel', '删除类型');
INSERT INTO `app_function` VALUES ('80', '_NewDepPlan', '新建部门任务计划');
INSERT INTO `app_function` VALUES ('81', '_NewsQuery', '查看新闻');
INSERT INTO `app_function` VALUES ('82', '_NewsAdd', '添加新闻');
INSERT INTO `app_function` VALUES ('83', '_NewsEdit', '编辑新闻');
INSERT INTO `app_function` VALUES ('84', '_NewsDel', '删除新闻');
INSERT INTO `app_function` VALUES ('85', '_NewsCommentQuery', '查看评论');
INSERT INTO `app_function` VALUES ('86', '_NewsCommentDel', '删除评论');
INSERT INTO `app_function` VALUES ('87', '_NewsTypeQuery', '查看新闻类型');
INSERT INTO `app_function` VALUES ('88', '_NewsTypeAdd', '添加新闻类型');
INSERT INTO `app_function` VALUES ('89', '_NewsTypeEdit', '修改新闻类型');
INSERT INTO `app_function` VALUES ('90', '_NewsTypeDel', '删除新闻类型');
INSERT INTO `app_function` VALUES ('91', '_NoticeQuery', '查看公告');
INSERT INTO `app_function` VALUES ('92', '_NoticeAdd', '添加公告');
INSERT INTO `app_function` VALUES ('93', '_NoticeEdit', '编辑公告');
INSERT INTO `app_function` VALUES ('94', '_NoticeDel', '删除公告');
INSERT INTO `app_function` VALUES ('95', '_HolidayRecordQuery', '查看假期设置');
INSERT INTO `app_function` VALUES ('96', '_HolidayRecordAdd', '添加假期设置');
INSERT INTO `app_function` VALUES ('97', '_HolidayRecordEdit', '修改假期设置');
INSERT INTO `app_function` VALUES ('98', '_HolidayRecordDel', '删除假期设置');
INSERT INTO `app_function` VALUES ('99', '_DutySectonQuery', '查看班次定义');
INSERT INTO `app_function` VALUES ('100', '_DutySectonAdd', '添加班次定义');
INSERT INTO `app_function` VALUES ('101', '_DutySectonEdit', '修改班次定义');
INSERT INTO `app_function` VALUES ('102', '_DutySectonDel', '删除班次定义');
INSERT INTO `app_function` VALUES ('103', '_DutySystemQuery', '查看班制定义');
INSERT INTO `app_function` VALUES ('104', '_DutySystemAdd', '添加班制定义');
INSERT INTO `app_function` VALUES ('105', '_DutySystemEdit', '修改班制定义');
INSERT INTO `app_function` VALUES ('106', '_DutySystemDel', '删除班制定义');
INSERT INTO `app_function` VALUES ('107', '_DutyQuery', '查看排班');
INSERT INTO `app_function` VALUES ('108', '_DutyAdd', '添加排班');
INSERT INTO `app_function` VALUES ('109', '_DutyEdit', '修改排班');
INSERT INTO `app_function` VALUES ('110', '_DutyDel', '删除排班');
INSERT INTO `app_function` VALUES ('111', '_DutyRegisterQuery', '查看考勤信息');
INSERT INTO `app_function` VALUES ('112', '_DutyRegisterAdd', '补签');
INSERT INTO `app_function` VALUES ('113', '_DutyRegisterDel', '删除考勤信息');
INSERT INTO `app_function` VALUES ('114', '_AddConferenceView', '会议申请');
INSERT INTO `app_function` VALUES ('115', '_DaiConfApply', '待审批会议查询');
INSERT INTO `app_function` VALUES ('116', '_ConfApplyView', '未通过审批会议查询');
INSERT INTO `app_function` VALUES ('117', '_BoardRooQuery', '会议室管理');
INSERT INTO `app_function` VALUES ('118', '_BoardTypeQuery', '会议类型管理');
INSERT INTO `app_function` VALUES ('119', '_ConfSummaryView', '会议纪要查询');
INSERT INTO `app_function` VALUES ('120', '_AddConfSummaryView', '创建会议纪要');
INSERT INTO `app_function` VALUES ('121', '_OfficeGoodsQuery', '查看办公用品');
INSERT INTO `app_function` VALUES ('122', '_OfficeGoodsTypeManage', '用品类型管理');
INSERT INTO `app_function` VALUES ('123', '_OfficeGoodsAdd', '添加用品');
INSERT INTO `app_function` VALUES ('124', '_OfficeGoodsEdit', '编辑用品');
INSERT INTO `app_function` VALUES ('125', '_OfficeGoodsDel', '删除用品');
INSERT INTO `app_function` VALUES ('126', '_InStockQuery', '查看入库记录');
INSERT INTO `app_function` VALUES ('127', '_InStockAdd', '添加入库记录');
INSERT INTO `app_function` VALUES ('128', '_InStockEdit', '编辑入库记录');
INSERT INTO `app_function` VALUES ('129', '_InStockDel', '删除入库记录');
INSERT INTO `app_function` VALUES ('130', '_GoodsApplyQuery', '查看申请记录');
INSERT INTO `app_function` VALUES ('131', '_GoodsApplyAdd', '添加申请记录');
INSERT INTO `app_function` VALUES ('132', '_GoodsApplyEdit', '编辑申请记录');
INSERT INTO `app_function` VALUES ('133', '_GoodsApplyDel', '删除申请记录');
INSERT INTO `app_function` VALUES ('134', '_GoodsApplyCheck', '审批申请');
INSERT INTO `app_function` VALUES ('135', '_CarQuery', '查看车辆');
INSERT INTO `app_function` VALUES ('136', '_CarAdd', '添加车辆');
INSERT INTO `app_function` VALUES ('137', '_CarEdit', '编辑车辆');
INSERT INTO `app_function` VALUES ('138', '_CarDel', '删除车辆');
INSERT INTO `app_function` VALUES ('139', '_CarRepairQuery', '查看维修记录');
INSERT INTO `app_function` VALUES ('140', '_CarRepairAdd', '添加维修记录');
INSERT INTO `app_function` VALUES ('141', '_CarRepairEdit', '编辑维修记录');
INSERT INTO `app_function` VALUES ('142', '_CarRepairDel', '删除维修记录');
INSERT INTO `app_function` VALUES ('143', '_CarApplyQuery', '查看车辆申请记录');
INSERT INTO `app_function` VALUES ('144', '_CarApplyAdd', '添加申请记录');
INSERT INTO `app_function` VALUES ('145', '_CarApplyEdit', '编辑申请记录');
INSERT INTO `app_function` VALUES ('146', '_CarApplyDel', '删除申请记录');
INSERT INTO `app_function` VALUES ('147', '_CarApplyCheck', '审批申请');
INSERT INTO `app_function` VALUES ('148', '_DepreTypeQuery', '查看折算类型');
INSERT INTO `app_function` VALUES ('149', '_DepreTypeAdd', '添加类型');
INSERT INTO `app_function` VALUES ('150', '_DepreTypeEdit', '编辑类型');
INSERT INTO `app_function` VALUES ('151', '_DepreTypeDel', '删除类型');
INSERT INTO `app_function` VALUES ('152', '_FixedAssetsQuery', '查看固定资产');
INSERT INTO `app_function` VALUES ('153', '_AssetsTypeManage', '资产类型管理');
INSERT INTO `app_function` VALUES ('154', '_FixedAssetsAdd', '添加资产');
INSERT INTO `app_function` VALUES ('155', '_FixedAssetsEdit', '编辑资产');
INSERT INTO `app_function` VALUES ('156', '_FixedAssetsDel', '删除资产');
INSERT INTO `app_function` VALUES ('157', '_Depreciate', '进行折算');
INSERT INTO `app_function` VALUES ('158', '_DepreRecordQuery', '查看折算记录');
INSERT INTO `app_function` VALUES ('159', '_BookTypeQuery', '查看类型');
INSERT INTO `app_function` VALUES ('160', '_BookTypeAdd', '添加图书类别');
INSERT INTO `app_function` VALUES ('161', '_BookTypeEdit', '编辑图书类别');
INSERT INTO `app_function` VALUES ('162', '_BookTypeDel', '删除图书类别');
INSERT INTO `app_function` VALUES ('163', '_BookQuery', '查看图书');
INSERT INTO `app_function` VALUES ('164', '_BookAdd', '添加图书');
INSERT INTO `app_function` VALUES ('165', '_BookEdit', '编辑图书');
INSERT INTO `app_function` VALUES ('166', '_BookDel', '删除图书');
INSERT INTO `app_function` VALUES ('167', '_BookBorrowQuery', '查看记录');
INSERT INTO `app_function` VALUES ('168', '_BookBorrowAdd', '添加借阅记录');
INSERT INTO `app_function` VALUES ('169', '_BookBorrowEdit', '编辑借阅记录');
INSERT INTO `app_function` VALUES ('170', '_BookReturn', '归还');
INSERT INTO `app_function` VALUES ('171', '_BookBorrowDel', '删除借阅记录');
INSERT INTO `app_function` VALUES ('172', '_BookReturnQuery', '查看记录');
INSERT INTO `app_function` VALUES ('173', '_BookReturnAdd', '添加归还记录');
INSERT INTO `app_function` VALUES ('174', '_BookReturnEdit', '编辑归还记录');
INSERT INTO `app_function` VALUES ('175', '_BookReturnDel', '删除归还记录');
INSERT INTO `app_function` VALUES ('176', '_JobQuery', '查看');
INSERT INTO `app_function` VALUES ('177', '_JobAdd', '添加');
INSERT INTO `app_function` VALUES ('178', '_JobEdit', '编辑');
INSERT INTO `app_function` VALUES ('179', '_JobDel', '删除');
INSERT INTO `app_function` VALUES ('180', '_JobRec', '恢复职位');
INSERT INTO `app_function` VALUES ('181', '_EmpProfileReg', '登记');
INSERT INTO `app_function` VALUES ('182', '_EmpProfileQuery', '查看');
INSERT INTO `app_function` VALUES ('183', '_EmpProfileAdd', '登记');
INSERT INTO `app_function` VALUES ('184', '_EmpProfileEdit', '编辑');
INSERT INTO `app_function` VALUES ('185', '_EmpProfileDel', '删除');
INSERT INTO `app_function` VALUES ('186', '_EmpProfileCheck', '审核');
INSERT INTO `app_function` VALUES ('187', '_EmpProfileRec', '恢复档案');
INSERT INTO `app_function` VALUES ('188', '_SalaryItemQuery', '查看');
INSERT INTO `app_function` VALUES ('189', '_SalaryItemAdd', '添加');
INSERT INTO `app_function` VALUES ('190', '_SalaryItemEdit', '编辑');
INSERT INTO `app_function` VALUES ('191', '_SalaryItemDel', '删除');
INSERT INTO `app_function` VALUES ('192', '_StandSalaryReg', '制定');
INSERT INTO `app_function` VALUES ('193', '_StandSalaryQuery', '查看');
INSERT INTO `app_function` VALUES ('194', '_StandSalaryAdd', '制定');
INSERT INTO `app_function` VALUES ('195', '_StandSalaryEdit', '编辑');
INSERT INTO `app_function` VALUES ('196', '_StandSalaryDel', '删除');
INSERT INTO `app_function` VALUES ('197', '_StandSalaryCheck', '审核');
INSERT INTO `app_function` VALUES ('198', '_SalaryPayoffReg', '登记');
INSERT INTO `app_function` VALUES ('199', '_SalaryPayoffQuery', '查看');
INSERT INTO `app_function` VALUES ('200', '_SalaryPayoffAdd', '登记');
INSERT INTO `app_function` VALUES ('201', '_SalaryPayoffEdit', '编辑');
INSERT INTO `app_function` VALUES ('202', '_SalaryPayoffDel', '删除');
INSERT INTO `app_function` VALUES ('203', '_SalaryPayoffCheck', '审核');
INSERT INTO `app_function` VALUES ('204', '_JobChangeReg', '登记');
INSERT INTO `app_function` VALUES ('205', '_JobChangeQuery', '查看');
INSERT INTO `app_function` VALUES ('206', '_JobChangeAdd', '登记');
INSERT INTO `app_function` VALUES ('207', '_JobChangeEdit', '编辑');
INSERT INTO `app_function` VALUES ('208', '_JobChangeDel', '删除');
INSERT INTO `app_function` VALUES ('209', '_JobChangeCheck', '审核');
INSERT INTO `app_function` VALUES ('210', '_HireIssueQuery', '查看');
INSERT INTO `app_function` VALUES ('211', '_HireIssueAdd', '添加');
INSERT INTO `app_function` VALUES ('212', '_HireIssueEdit', '编辑');
INSERT INTO `app_function` VALUES ('213', '_HireIssueDel', '删除');
INSERT INTO `app_function` VALUES ('214', '_HireIssueCheck', '审核');
INSERT INTO `app_function` VALUES ('215', '_ResumeQuery', '查看');
INSERT INTO `app_function` VALUES ('216', '_ResumeAdd', '添加');
INSERT INTO `app_function` VALUES ('217', '_ResumeEdit', '编辑');
INSERT INTO `app_function` VALUES ('218', '_ResumeDel', '删除');

-- ----------------------------
-- Table structure for `app_role`
-- ----------------------------
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE `app_role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `isDefaultIn` smallint(6) NOT NULL,
  `rights` text COLLATE utf8_unicode_ci,
  `roleDesc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `roleName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_role
-- ----------------------------
INSERT INTO `app_role` VALUES ('-1', '0', '__ALL', '超级管理员,具有所有权限', '管理员', '1');
INSERT INTO `app_role` VALUES ('1', '0', 'SystemSetting,AppUserView,_AppUserQuery,_AppUserAdd,_AppUserEdit,_AppUserDel,DepartmentView,_DepartmentQuery,_DepartmentAdd,_DepartmentEdit,_DepartmentDel,PublicDocument,NewPublicDocumentForm,Task,NewWorkPlanForm,_NewDepPlan,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView,DutyManager,Duty,HolidayRecordView,_HolidayRecordQuery,_HolidayRecordAdd,_HolidayRecordEdit,_HolidayRecordDel,DutySectionView,_DutySectonQuery,_DutySectonAdd,_DutySectonEdit,_DutySectonDel,DutySystemView,_DutySystemQuery,_DutySystemAdd,_DutySystemEdit,_DutySystemDel,DutyView,_DutyQuery,_DutyAdd,_DutyEdit,_DutyDel,DutyRegisterView,_DutyRegisterQuery,_DutyRegisterAdd,_DutyRegisterDel', '管理人事的经理', '[人事经理]', '1');
INSERT INTO `app_role` VALUES ('2', '0', 'SystemSetting,PublicDocument,NewPublicDocumentForm,Task,NewWorkPlanForm,_NewDepPlan,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView,Administrator,GoodManeger,OfficeGoodsManageView,_OfficeGoodsQuery,_OfficeGoodsTypeManage,_OfficeGoodsAdd,_OfficeGoodsEdit,_OfficeGoodsDel,InStockView,_InStockQuery,_InStockAdd,_InStockEdit,_InStockDel,GoodsApplyView,_GoodsApplyQuery,_GoodsApplyAdd,_GoodsApplyEdit,_GoodsApplyDel,CarManeger,CarView,_CarQuery,_CarAdd,_CarEdit,_CarDel,CartRepairView,_CarRepairQuery,_CarRepairAdd,_CarRepairEdit,_CarRepairDel,CarApplyView,_CarApplyQuery,_CarApplyAdd,_CarApplyEdit,_CarApplyDel,FixedAssetsManage,DepreTypeView,_DepreTypeQuery,_DepreTypeAdd,_DepreTypeEdit,_DepreTypeDel,FixedAssetsManageView,_FixedAssetsQuery,_AssetsTypeManage,_FixedAssetsAdd,_FixedAssetsEdit,_FixedAssetsDel,_Depreciate,DepreRecordView,_DepreRecordQuery,BookManager,BookTypeView,_BookTypeQuery,_BookTypeAdd,_BookTypeEdit,_BookTypeDel,BookManageView,_BookQuery,_BookAdd,_BookEdit,_BookDel,BookBorrowView,_BookBorrowQuery,_BookBorrowAdd,_BookBorrowEdit,_BookReturn,_BookBorrowDel,BookReturnView,_BookReturnQuery,_BookReturnAdd,_BookReturnEdit,_BookReturnDel', '管理行政', '[行政经理]', '1');
INSERT INTO `app_role` VALUES ('3', '0', 'SystemSetting,PublicDocument,NewPublicDocumentForm,DocFolderSharedView,_DocFolderSharedManage,_DocPrivilegeQuery,_DocPrivilegeAdd,_DocPrivilegeEdit,_DocPrivilegeDel,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView', '管理文档', '[文档管理员]', '1');
INSERT INTO `app_role` VALUES ('4', '0', 'SystemSetting,Task,PlanTypeView,_PlanTypeQuery,_PlanTypeAdd,_PlanTypeEdit,_PlanTypeDel,NewWorkPlanForm,_NewDepPlan,Info,NewsView,_NewsQuery,_NewsAdd,_NewsEdit,_NewsDel,NewsCommentView,_NewsCommentQuery,_NewsCommentDel,NewsTypeView,_NewsTypeQuery,_NewsTypeAdd,_NewsTypeEdit,_NewsTypeDel,NoticeView,_NoticeQuery,_NoticeAdd,_NoticeEdit,_NoticeDel,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView', '管理新闻公告等信息', '[信息管理员]', '1');
INSERT INTO `app_role` VALUES ('5', '0', 'SystemSetting,PublicDocument,NewPublicDocumentForm,Task,NewWorkPlanForm,_NewDepPlan,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView,customer,CustomerView,_CustomerQuery,_CustomerAdd,_CustomerEdit,_CustomerDel,CusLinkmanView,_CusLinkmanQuery,_CusLinkmanAdd,_CusLinkmanEdit,_CusLinkmanDel,CusConnectionView,_CusConnectionQuery,_CusConnectionAdd,_CusConnectionEdit,_CusConnectionDel,ProjectView,_ProjectQuery,_ProjectAdd,_ProjectEdit,_ProjectDel,ContractView,_ContractQuery,_ContractAdd,_ContractEdit,_ContractDel,ProductView,_ProductQuery,_ProductAdd,_ProductEdit,_ProductDel,ProviderView,_ProviderQuery,_ProviderAdd,_ProviderEdit,_ProviderDel', '管理客户信息', '[客户经理]', '1');
INSERT INTO `app_role` VALUES ('6', '0', 'Archive,ArchFlowConfView,_ArchFlowConfEdit,ArchiveIssue,ArchiveTypeTempView,_ArchiveTypeTempQuery,_ArchivesTypeAdd,_ArchivesTypeEdit,_ArchivesTypeDel,_ArchivesTempAdd,_ArchivesTempEdit,_ArchviesTempDel,ArchivesDraftView,_AchivesDrafAdd,ArchivesDraftManage,_ArchivesDrafmQuery,_ArchivesDrafmEdit,_ArchivesDrafmDel,ArchivesIssueAudit,_ArchivesIssueQuery,_ArchivesIssueEdit,ArchivesIssueLead,_ArchivesIssueLeadQuery,_ArchivesIssueLeadEdit,ArchivesIssueCharge,_ArchivesIssueChargeQuery,_ArchivesIssueChargeEdit,ArchivesIssueProof,_ArchivesIssueProofQuery,_ArchivesIssueProofEdit,ArchivesDocument,_ArchivesDocumentQuery,ArchivesIssueMonitor,_ArchivesIssueMonitorQuery,_ArchivesIssueHasten,ArchivesIssueManage,_ArchivesIssueManageQuery,ArchivesIssueSearch,_ArchivesIssueSearchQuery,DocHistoryView,_DocHistoryQuery,_DocHistoryDel,ArchiveReceive,ArchivesSignView,_ArchivesSignQuery,_ArchivesSignUp,ArchivesRecView,_ArchivesRecQuery,_ArchivesRecAdd,_ArchivesRecEdit,_ArchivesRecDel,ArchivesHandleView,_ArchivesHandleQuery,LeaderReadView,_LeaderReadQuery,ArchDispatchView,_ArchDispatchQuery,ArchUndertakeView,_ArchUndertakeQuery,ArchivesRecCtrlView,_ArchivesRecCtrlQuery,_ArchivesRecHasten,ArchReadView,_ArchReadQuery,ArchRecTypeView,_ArchRecTypeQuery,_ArchRecTypeAdd,_ArchRecTypeEdit,_ArchRecTypeDel', '公文管理', '公文管理', '1');

-- ----------------------------
-- Table structure for `app_tips`
-- ----------------------------
DROP TABLE IF EXISTS `app_tips`;
CREATE TABLE `app_tips` (
  `tipsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `disheight` int(11) DEFAULT NULL,
  `disleft` int(11) DEFAULT NULL,
  `dislevel` int(11) DEFAULT NULL,
  `distop` int(11) DEFAULT NULL,
  `diswidth` int(11) DEFAULT NULL,
  `tipsName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tipsId`),
  UNIQUE KEY `tipsId` (`tipsId`),
  KEY `FK459BBE9650662A29` (`userId`),
  KEY `FK459BBE968D18473` (`userId`),
  CONSTRAINT `FK459BBE968D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK459BBE9650662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_tips
-- ----------------------------

-- ----------------------------
-- Table structure for `app_user`
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `accessionTime` datetime NOT NULL,
  `address` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `delFlag` smallint(6) NOT NULL,
  `education` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `fax` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `title` smallint(6) DEFAULT NULL,
  `username` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `zip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `depId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`),
  KEY `FK459C57299C84EE5` (`depId`),
  KEY `FK459C57291E88585B` (`depId`),
  CONSTRAINT `FK459C57291E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK459C57299C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', '2011-08-20 16:44:36', '', '0', '', 'csx@jee-soft.cn', '', '超级管理员', '', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', '', '', '', '1', '1', 'admin', '', '1');
INSERT INTO `app_user` VALUES ('2', '2011-08-20 16:44:36', '', '1', '', '152@163.com', '', '系统', '', '0', '', '', '', '0', '1', 'system', '', '1');
INSERT INTO `app_user` VALUES ('3', '2012-06-24 00:00:00', '', '0', null, '772929989@qq.com', '', '陈健', '', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', '', '', '', '1', '1', 'jacarri', '', '2');
INSERT INTO `app_user` VALUES ('4', '2012-06-24 00:00:00', '', '0', null, 'fetion1989@163.com', '', '康敬浦', '', 'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=', '', '', '', '1', '1', 'akang', '', '2');

-- ----------------------------
-- Table structure for `archives`
-- ----------------------------
DROP TABLE IF EXISTS `archives`;
CREATE TABLE `archives` (
  `archivesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `archType` smallint(6) NOT NULL,
  `archivesNo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `depSignNo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileCounts` int(11) NOT NULL,
  `handlerUids` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `handlerUnames` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issueDate` datetime NOT NULL,
  `issueDep` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `issuer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issuerId` bigint(20) DEFAULT NULL,
  `keywords` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgArchivesId` bigint(20) DEFAULT NULL,
  `privacyLevel` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recDepIds` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recDepNames` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shortContent` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sources` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `typeName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `urgentLevel` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `arc_typeId` bigint(20) DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  `depId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`archivesId`),
  UNIQUE KEY `archivesId` (`archivesId`),
  KEY `FK99B337F1A08FBBA7` (`typeId`),
  KEY `FK99B337F19C84EE5` (`depId`),
  KEY `FK99B337F14EA33D42` (`typeId`),
  KEY `FK99B337F14615E34` (`arc_typeId`),
  KEY `FK99B337F11E88585B` (`depId`),
  KEY `FK99B337F1630380C` (`typeId`),
  KEY `FK99B337F1E944E22A` (`arc_typeId`),
  CONSTRAINT `FK99B337F1E944E22A` FOREIGN KEY (`arc_typeId`) REFERENCES `arch_rec_type` (`recTypeId`),
  CONSTRAINT `FK99B337F11E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK99B337F14615E34` FOREIGN KEY (`arc_typeId`) REFERENCES `arch_rec_type` (`recTypeId`),
  CONSTRAINT `FK99B337F14EA33D42` FOREIGN KEY (`typeId`) REFERENCES `archives_type` (`typeId`),
  CONSTRAINT `FK99B337F1630380C` FOREIGN KEY (`typeId`) REFERENCES `archives_type` (`typeId`),
  CONSTRAINT `FK99B337F19C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK99B337F1A08FBBA7` FOREIGN KEY (`typeId`) REFERENCES `arch_rec_type` (`recTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of archives
-- ----------------------------

-- ----------------------------
-- Table structure for `archives_attend`
-- ----------------------------
DROP TABLE IF EXISTS `archives_attend`;
CREATE TABLE `archives_attend` (
  `attendId` bigint(20) NOT NULL AUTO_INCREMENT,
  `attendType` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `executeTime` datetime DEFAULT NULL,
  `fullname` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userID` bigint(20) NOT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`attendId`),
  UNIQUE KEY `attendId` (`attendId`),
  KEY `FK201B22A88023925F` (`archivesId`),
  KEY `FK201B22A8CB218229` (`archivesId`),
  CONSTRAINT `FK201B22A8CB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK201B22A88023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of archives_attend
-- ----------------------------

-- ----------------------------
-- Table structure for `archives_dep`
-- ----------------------------
DROP TABLE IF EXISTS `archives_dep`;
CREATE TABLE `archives_dep` (
  `archDepId` bigint(20) NOT NULL AUTO_INCREMENT,
  `handleFeedback` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isMain` smallint(6) NOT NULL,
  `signFullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signNo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signTime` datetime DEFAULT NULL,
  `signUserID` bigint(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  `depId` bigint(20) DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`archDepId`),
  UNIQUE KEY `archDepId` (`archDepId`),
  KEY `FK9CB08AC1A08FBBA7` (`typeId`),
  KEY `FK9CB08AC19C84EE5` (`depId`),
  KEY `FK9CB08AC18023925F` (`archivesId`),
  KEY `FK9CB08AC185733F9D` (`typeId`),
  KEY `FK9CB08AC11E88585B` (`depId`),
  KEY `FK9CB08AC1CB218229` (`archivesId`),
  CONSTRAINT `FK9CB08AC1CB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK9CB08AC11E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK9CB08AC18023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK9CB08AC185733F9D` FOREIGN KEY (`typeId`) REFERENCES `arch_rec_type` (`recTypeId`),
  CONSTRAINT `FK9CB08AC19C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK9CB08AC1A08FBBA7` FOREIGN KEY (`typeId`) REFERENCES `arch_rec_type` (`recTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of archives_dep
-- ----------------------------

-- ----------------------------
-- Table structure for `archives_doc`
-- ----------------------------
DROP TABLE IF EXISTS `archives_doc`;
CREATE TABLE `archives_doc` (
  `docId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `creator` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creatorId` bigint(20) DEFAULT NULL,
  `curVersion` int(11) NOT NULL,
  `docName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `docPath` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `docStatus` smallint(6) NOT NULL,
  `mender` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menderId` bigint(20) DEFAULT NULL,
  `updatetime` datetime NOT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`docId`),
  UNIQUE KEY `docId` (`docId`),
  KEY `FK9CB08BEAD23E98A1` (`fileId`),
  KEY `FK9CB08BEA8023925F` (`archivesId`),
  KEY `FK9CB08BEAE6FEA217` (`fileId`),
  KEY `FK9CB08BEACB218229` (`archivesId`),
  CONSTRAINT `FK9CB08BEACB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK9CB08BEA8023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK9CB08BEAD23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK9CB08BEAE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of archives_doc
-- ----------------------------

-- ----------------------------
-- Table structure for `archives_handle`
-- ----------------------------
DROP TABLE IF EXISTS `archives_handle`;
CREATE TABLE `archives_handle` (
  `handleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `fillTime` datetime DEFAULT NULL,
  `handleOpinion` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isPass` smallint(6) NOT NULL,
  `userFullname` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) NOT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`handleId`),
  UNIQUE KEY `handleId` (`handleId`),
  KEY `FK2AFE92368023925F` (`archivesId`),
  KEY `FK2AFE9236CB218229` (`archivesId`),
  CONSTRAINT `FK2AFE9236CB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK2AFE92368023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of archives_handle
-- ----------------------------

-- ----------------------------
-- Table structure for `archives_type`
-- ----------------------------
DROP TABLE IF EXISTS `archives_type`;
CREATE TABLE `archives_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeDesc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of archives_type
-- ----------------------------

-- ----------------------------
-- Table structure for `arch_dispatch`
-- ----------------------------
DROP TABLE IF EXISTS `arch_dispatch`;
CREATE TABLE `arch_dispatch` (
  `dispatchId` bigint(20) NOT NULL AUTO_INCREMENT,
  `archUserType` smallint(6) DEFAULT NULL,
  `disRoleId` bigint(20) DEFAULT NULL,
  `disRoleName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dispatchTime` datetime NOT NULL,
  `fullname` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isRead` smallint(6) DEFAULT NULL,
  `readFeedback` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dispatchId`),
  UNIQUE KEY `dispatchId` (`dispatchId`),
  KEY `FKB566A6438023925F` (`archivesId`),
  KEY `FKB566A643CB218229` (`archivesId`),
  CONSTRAINT `FKB566A643CB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FKB566A6438023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_dispatch
-- ----------------------------

-- ----------------------------
-- Table structure for `arch_flow_conf`
-- ----------------------------
DROP TABLE IF EXISTS `arch_flow_conf`;
CREATE TABLE `arch_flow_conf` (
  `configId` bigint(20) NOT NULL AUTO_INCREMENT,
  `archType` smallint(6) DEFAULT NULL,
  `processDefId` bigint(20) DEFAULT NULL,
  `processName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`configId`),
  UNIQUE KEY `configId` (`configId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_flow_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `arch_fond`
-- ----------------------------
DROP TABLE IF EXISTS `arch_fond`;
CREATE TABLE `arch_fond` (
  `archFondId` bigint(20) NOT NULL AUTO_INCREMENT,
  `afName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `afNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `caseNums` int(11) DEFAULT NULL,
  `clearupDesc` text COLLATE utf8_unicode_ci,
  `createTime` datetime DEFAULT NULL,
  `creatorId` bigint(20) DEFAULT NULL,
  `creatorName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descp` text COLLATE utf8_unicode_ci,
  `openStyle` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shortDesc` text COLLATE utf8_unicode_ci,
  `status` smallint(6) DEFAULT NULL,
  `typeName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `proTypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`archFondId`),
  UNIQUE KEY `archFondId` (`archFondId`),
  KEY `FK9C1FBA887B33B268` (`proTypeId`),
  KEY `FK9C1FBA888FF3BBDE` (`proTypeId`),
  CONSTRAINT `FK9C1FBA888FF3BBDE` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FK9C1FBA887B33B268` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_fond
-- ----------------------------
INSERT INTO `arch_fond` VALUES ('1', '全宗A', '001', '1', '1', '2011-08-20 00:00:00', '1', '超级管理员', '1', '对内开放', '1', '0', '全宗分类', '2011-08-20 00:00:00', '14');

-- ----------------------------
-- Table structure for `arch_hasten`
-- ----------------------------
DROP TABLE IF EXISTS `arch_hasten`;
CREATE TABLE `arch_hasten` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `handlerFullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `handlerUserId` bigint(20) DEFAULT NULL,
  `hastenFullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId` (`recordId`),
  KEY `FK15C22F4C8023925F` (`archivesId`),
  KEY `FK15C22F4CCB218229` (`archivesId`),
  CONSTRAINT `FK15C22F4CCB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK15C22F4C8023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_hasten
-- ----------------------------

-- ----------------------------
-- Table structure for `arch_rec_type`
-- ----------------------------
DROP TABLE IF EXISTS `arch_rec_type`;
CREATE TABLE `arch_rec_type` (
  `recTypeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `depId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recTypeId`),
  UNIQUE KEY `recTypeId` (`recTypeId`),
  KEY `FK73E9CB529C84EE5` (`depId`),
  KEY `FK73E9CB521E88585B` (`depId`),
  CONSTRAINT `FK73E9CB521E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK73E9CB529C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_rec_type
-- ----------------------------

-- ----------------------------
-- Table structure for `arch_rec_user`
-- ----------------------------
DROP TABLE IF EXISTS `arch_rec_user`;
CREATE TABLE `arch_rec_user` (
  `archRecId` bigint(20) NOT NULL AUTO_INCREMENT,
  `depName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) NOT NULL,
  `depId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`archRecId`),
  UNIQUE KEY `archRecId` (`archRecId`),
  KEY `FK73EA27E39C84EE5` (`depId`),
  KEY `FK73EA27E31E88585B` (`depId`),
  CONSTRAINT `FK73EA27E31E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK73EA27E39C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_rec_user
-- ----------------------------

-- ----------------------------
-- Table structure for `arch_roll`
-- ----------------------------
DROP TABLE IF EXISTS `arch_roll`;
CREATE TABLE `arch_roll` (
  `rollId` bigint(20) NOT NULL AUTO_INCREMENT,
  `afNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `catNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checker` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `creatorName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `decp` text COLLATE utf8_unicode_ci,
  `editCompany` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `editDep` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `fileNums` int(11) DEFAULT NULL,
  `keyWords` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openStyle` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rollNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `rolllName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `setupTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `timeLimit` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `archFondId` bigint(20) NOT NULL,
  `proTypeId` bigint(20) NOT NULL,
  PRIMARY KEY (`rollId`),
  UNIQUE KEY `rollId` (`rollId`),
  KEY `FK9C252EC6BC6A1BC7` (`archFondId`),
  KEY `FK9C252EC67B33B268` (`proTypeId`),
  KEY `FK9C252EC6E365503D` (`archFondId`),
  KEY `FK9C252EC68FF3BBDE` (`proTypeId`),
  CONSTRAINT `FK9C252EC68FF3BBDE` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FK9C252EC67B33B268` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FK9C252EC6BC6A1BC7` FOREIGN KEY (`archFondId`) REFERENCES `arch_fond` (`archFondId`),
  CONSTRAINT `FK9C252EC6E365503D` FOREIGN KEY (`archFondId`) REFERENCES `arch_fond` (`archFondId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_roll
-- ----------------------------
INSERT INTO `arch_roll` VALUES ('5', '001', '徐雪斌', '001', '徐雪斌', '2011-08-20 00:00:00', '超级管理员', '说明', '单位', '部门', '2011-08-20 00:00:00', null, '撒旦', '不开放', '001', '案卷A', '2011-08-20 00:00:00', '2011-08-20 00:00:00', '1', '永久', '案卷', '1', '16');

-- ----------------------------
-- Table structure for `arch_template`
-- ----------------------------
DROP TABLE IF EXISTS `arch_template`;
CREATE TABLE `arch_template` (
  `templateId` bigint(20) NOT NULL AUTO_INCREMENT,
  `tempName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `tempPath` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`templateId`),
  UNIQUE KEY `templateId` (`templateId`),
  KEY `FK55A82E434EA33D42` (`typeId`),
  KEY `FK55A82E43D23E98A1` (`fileId`),
  KEY `FK55A82E43630380C` (`typeId`),
  KEY `FK55A82E43E6FEA217` (`fileId`),
  CONSTRAINT `FK55A82E43E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK55A82E434EA33D42` FOREIGN KEY (`typeId`) REFERENCES `archives_type` (`typeId`),
  CONSTRAINT `FK55A82E43630380C` FOREIGN KEY (`typeId`) REFERENCES `archives_type` (`typeId`),
  CONSTRAINT `FK55A82E43D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of arch_template
-- ----------------------------

-- ----------------------------
-- Table structure for `assets_type`
-- ----------------------------
DROP TABLE IF EXISTS `assets_type`;
CREATE TABLE `assets_type` (
  `assetsTypeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`assetsTypeId`),
  UNIQUE KEY `assetsTypeId` (`assetsTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of assets_type
-- ----------------------------

-- ----------------------------
-- Table structure for `boardroo`
-- ----------------------------
DROP TABLE IF EXISTS `boardroo`;
CREATE TABLE `boardroo` (
  `roomId` bigint(20) NOT NULL AUTO_INCREMENT,
  `containNum` bigint(20) NOT NULL,
  `roomDesc` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `roomName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`roomId`),
  UNIQUE KEY `roomId` (`roomId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of boardroo
-- ----------------------------

-- ----------------------------
-- Table structure for `boardstate`
-- ----------------------------
DROP TABLE IF EXISTS `boardstate`;
CREATE TABLE `boardstate` (
  `stateId` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`stateId`),
  UNIQUE KEY `stateId` (`stateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of boardstate
-- ----------------------------

-- ----------------------------
-- Table structure for `board_type`
-- ----------------------------
DROP TABLE IF EXISTS `board_type`;
CREATE TABLE `board_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeDesc` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of board_type
-- ----------------------------

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookId` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `author` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `bookName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `isbn` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `leftAmount` int(11) NOT NULL,
  `location` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `publisher` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  UNIQUE KEY `bookId` (`bookId`),
  KEY `FK2E3AE9FF6D8ECD` (`typeId`),
  KEY `FK2E3AE9B7D8E917` (`typeId`),
  CONSTRAINT `FK2E3AE9B7D8E917` FOREIGN KEY (`typeId`) REFERENCES `book_type` (`typeId`),
  CONSTRAINT `FK2E3AE9FF6D8ECD` FOREIGN KEY (`typeId`) REFERENCES `book_type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for `book_bor_ret`
-- ----------------------------
DROP TABLE IF EXISTS `book_bor_ret`;
CREATE TABLE `book_bor_ret` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `borrowIsbn` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `borrowTime` datetime NOT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `lastReturnTime` datetime DEFAULT NULL,
  `registerName` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `returnTime` datetime NOT NULL,
  `bookSnId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId` (`recordId`),
  KEY `FKF62CE3D122153EB8` (`bookSnId`),
  KEY `FKF62CE3D1E146A382` (`bookSnId`),
  CONSTRAINT `FKF62CE3D1E146A382` FOREIGN KEY (`bookSnId`) REFERENCES `book_sn` (`bookSnId`),
  CONSTRAINT `FKF62CE3D122153EB8` FOREIGN KEY (`bookSnId`) REFERENCES `book_sn` (`bookSnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of book_bor_ret
-- ----------------------------

-- ----------------------------
-- Table structure for `book_sn`
-- ----------------------------
DROP TABLE IF EXISTS `book_sn`;
CREATE TABLE `book_sn` (
  `bookSnId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookSN` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) NOT NULL,
  `bookId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookSnId`),
  UNIQUE KEY `bookSnId` (`bookSnId`),
  KEY `FK3DAE37146188662` (`bookId`),
  KEY `FK3DAE371631095AC` (`bookId`),
  CONSTRAINT `FK3DAE371631095AC` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`),
  CONSTRAINT `FK3DAE37146188662` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of book_sn
-- ----------------------------

-- ----------------------------
-- Table structure for `book_type`
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of book_type
-- ----------------------------

-- ----------------------------
-- Table structure for `borrow_file_list`
-- ----------------------------
DROP TABLE IF EXISTS `borrow_file_list`;
CREATE TABLE `borrow_file_list` (
  `listId` bigint(20) NOT NULL AUTO_INCREMENT,
  `afName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `afNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `listType` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rollNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rolllName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `archFondId` bigint(20) DEFAULT NULL,
  `rollId` bigint(20) DEFAULT NULL,
  `recordId` bigint(20) DEFAULT NULL,
  `rollFileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`listId`),
  UNIQUE KEY `listId` (`listId`),
  KEY `FK34F7B79808E8F` (`rollFileId`),
  KEY `FK34F7B7679EA774` (`recordId`),
  KEY `FK34F7B7BC6A1BC7` (`archFondId`),
  KEY `FK34F7B790FF34ED` (`rollId`),
  KEY `FK34F7B7307BC305` (`rollFileId`),
  KEY `FK34F7B77C5EB0EA` (`recordId`),
  KEY `FK34F7B7E365503D` (`archFondId`),
  KEY `FK34F7B7B7FA6963` (`rollId`),
  CONSTRAINT `FK34F7B7B7FA6963` FOREIGN KEY (`rollId`) REFERENCES `arch_roll` (`rollId`),
  CONSTRAINT `FK34F7B7307BC305` FOREIGN KEY (`rollFileId`) REFERENCES `roll_file` (`rollFileId`),
  CONSTRAINT `FK34F7B7679EA774` FOREIGN KEY (`recordId`) REFERENCES `borrow_record` (`recordId`),
  CONSTRAINT `FK34F7B77C5EB0EA` FOREIGN KEY (`recordId`) REFERENCES `borrow_record` (`recordId`),
  CONSTRAINT `FK34F7B790FF34ED` FOREIGN KEY (`rollId`) REFERENCES `arch_roll` (`rollId`),
  CONSTRAINT `FK34F7B79808E8F` FOREIGN KEY (`rollFileId`) REFERENCES `roll_file` (`rollFileId`),
  CONSTRAINT `FK34F7B7BC6A1BC7` FOREIGN KEY (`archFondId`) REFERENCES `arch_fond` (`archFondId`),
  CONSTRAINT `FK34F7B7E365503D` FOREIGN KEY (`archFondId`) REFERENCES `arch_fond` (`archFondId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of borrow_file_list
-- ----------------------------

-- ----------------------------
-- Table structure for `borrow_record`
-- ----------------------------
DROP TABLE IF EXISTS `borrow_record`;
CREATE TABLE `borrow_record` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `borrowDate` datetime DEFAULT NULL,
  `borrowNum` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `borrowReason` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `borrowRemark` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `borrowType` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkContent` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  `checkId` bigint(20) DEFAULT NULL,
  `checkName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkUserName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `returnDate` datetime DEFAULT NULL,
  `returnStatus` smallint(6) DEFAULT NULL,
  `checkUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId` (`recordId`),
  KEY `FKB7C991DBE9FC2931` (`checkUserId`),
  KEY `FKB7C991DBA267837B` (`checkUserId`),
  CONSTRAINT `FKB7C991DBA267837B` FOREIGN KEY (`checkUserId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKB7C991DBE9FC2931` FOREIGN KEY (`checkUserId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of borrow_record
-- ----------------------------

-- ----------------------------
-- Table structure for `calendar_plan`
-- ----------------------------
DROP TABLE IF EXISTS `calendar_plan`;
CREATE TABLE `calendar_plan` (
  `planId` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignerId` bigint(20) NOT NULL,
  `assignerName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `color` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(1200) COLLATE utf8_unicode_ci NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `feedback` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `showStyle` smallint(6) NOT NULL,
  `startTime` datetime DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `statusName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `taskType` smallint(6) NOT NULL,
  `urgent` smallint(6) NOT NULL,
  `urgentName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`planId`),
  UNIQUE KEY `planId` (`planId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of calendar_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `carId` bigint(20) NOT NULL AUTO_INCREMENT,
  `auditTime` datetime DEFAULT NULL,
  `buyDate` datetime DEFAULT NULL,
  `buyInsureTime` datetime DEFAULT NULL,
  `carNo` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `carType` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `cartImage` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `engineNo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `factoryModel` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`carId`),
  UNIQUE KEY `carId` (`carId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for `cart_repair`
-- ----------------------------
DROP TABLE IF EXISTS `cart_repair`;
CREATE TABLE `cart_repair` (
  `repairId` bigint(20) NOT NULL AUTO_INCREMENT,
  `executant` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `fee` decimal(18,0) DEFAULT NULL,
  `notes` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reason` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `repairDate` datetime NOT NULL,
  `repairType` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `carId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`repairId`),
  UNIQUE KEY `repairId` (`repairId`),
  KEY `FKA682DD8C12CEC36E` (`carId`),
  KEY `FKA682DD8C6652A2E4` (`carId`),
  CONSTRAINT `FKA682DD8C6652A2E4` FOREIGN KEY (`carId`) REFERENCES `car` (`carId`),
  CONSTRAINT `FKA682DD8C12CEC36E` FOREIGN KEY (`carId`) REFERENCES `car` (`carId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cart_repair
-- ----------------------------

-- ----------------------------
-- Table structure for `car_apply`
-- ----------------------------
DROP TABLE IF EXISTS `car_apply`;
CREATE TABLE `car_apply` (
  `applyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `applyDate` datetime NOT NULL,
  `approvalStatus` smallint(6) NOT NULL,
  `department` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `mileage` decimal(18,0) DEFAULT NULL,
  `notes` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `oilUse` decimal(18,0) DEFAULT NULL,
  `proposer` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `reason` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` datetime NOT NULL,
  `userFullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) NOT NULL,
  `carId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`applyId`),
  UNIQUE KEY `applyId` (`applyId`),
  KEY `FKE8F7374312CEC36E` (`carId`),
  KEY `FKE8F737436652A2E4` (`carId`),
  CONSTRAINT `FKE8F737436652A2E4` FOREIGN KEY (`carId`) REFERENCES `car` (`carId`),
  CONSTRAINT `FKE8F7374312CEC36E` FOREIGN KEY (`carId`) REFERENCES `car` (`carId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of car_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyDesc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `companyNo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `legalPerson` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `logo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `setup` datetime DEFAULT NULL,
  `site` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`companyId`),
  UNIQUE KEY `companyId` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of company
-- ----------------------------

-- ----------------------------
-- Table structure for `conference`
-- ----------------------------
DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference` (
  `confId` bigint(20) NOT NULL AUTO_INCREMENT,
  `attendUsers` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `attendUsersName` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkReason` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkUserId` bigint(20) DEFAULT NULL,
  `compere` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `compereName` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `confContent` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `confProperty` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `confTopic` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `endTime` datetime NOT NULL,
  `feeBudget` double DEFAULT NULL,
  `importLevel` smallint(6) NOT NULL,
  `isEmail` smallint(6) DEFAULT NULL,
  `isMobile` smallint(6) DEFAULT NULL,
  `recorder` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recorderName` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `roomId` bigint(20) NOT NULL,
  `roomLocation` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `roomName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `startTime` datetime NOT NULL,
  `status` smallint(6) NOT NULL,
  `typeId` bigint(20) NOT NULL,
  PRIMARY KEY (`confId`),
  UNIQUE KEY `confId` (`confId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of conference
-- ----------------------------

-- ----------------------------
-- Table structure for `conf_attach`
-- ----------------------------
DROP TABLE IF EXISTS `conf_attach`;
CREATE TABLE `conf_attach` (
  `confId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`confId`,`fileId`),
  KEY `FKE6F7ABC0524F0650` (`confId`),
  KEY `FKE6F7ABC0D23E98A1` (`fileId`),
  KEY `FKE6F7ABC09D4CF61A` (`confId`),
  KEY `FKE6F7ABC0E6FEA217` (`fileId`),
  CONSTRAINT `FKE6F7ABC0E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKE6F7ABC0524F0650` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`),
  CONSTRAINT `FKE6F7ABC09D4CF61A` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`),
  CONSTRAINT `FKE6F7ABC0D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of conf_attach
-- ----------------------------

-- ----------------------------
-- Table structure for `conf_attend`
-- ----------------------------
DROP TABLE IF EXISTS `conf_attend`;
CREATE TABLE `conf_attend` (
  `attendId` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userType` smallint(6) DEFAULT NULL,
  `confId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`attendId`),
  UNIQUE KEY `attendId` (`attendId`),
  KEY `FKE6F7BC15524F0650` (`confId`),
  KEY `FKE6F7BC159D4CF61A` (`confId`),
  CONSTRAINT `FKE6F7BC159D4CF61A` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`),
  CONSTRAINT `FKE6F7BC15524F0650` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of conf_attend
-- ----------------------------

-- ----------------------------
-- Table structure for `conf_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `conf_privilege`;
CREATE TABLE `conf_privilege` (
  `privilegeId` int(11) NOT NULL AUTO_INCREMENT,
  `confId` bigint(20) DEFAULT NULL,
  `fullname` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `rights` smallint(6) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`privilegeId`),
  UNIQUE KEY `privilegeId` (`privilegeId`),
  KEY `FKAE450356524F0650` (`confId`),
  KEY `FKAE4503569D4CF61A` (`confId`),
  CONSTRAINT `FKAE4503569D4CF61A` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`),
  CONSTRAINT `FKAE450356524F0650` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of conf_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for `conf_summary`
-- ----------------------------
DROP TABLE IF EXISTS `conf_summary`;
CREATE TABLE `conf_summary` (
  `sumId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `creator` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  `sumContent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `confId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sumId`),
  UNIQUE KEY `sumId` (`sumId`),
  KEY `FKB185998B524F0650` (`confId`),
  KEY `FKB185998B9D4CF61A` (`confId`),
  CONSTRAINT `FKB185998B9D4CF61A` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`),
  CONSTRAINT `FKB185998B524F0650` FOREIGN KEY (`confId`) REFERENCES `conference` (`confId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of conf_summary
-- ----------------------------

-- ----------------------------
-- Table structure for `conf_sum_attach`
-- ----------------------------
DROP TABLE IF EXISTS `conf_sum_attach`;
CREATE TABLE `conf_sum_attach` (
  `sumId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`sumId`,`fileId`),
  KEY `FK85AB1AB412CAE533` (`sumId`),
  KEY `FK85AB1AB4D23E98A1` (`fileId`),
  KEY `FK85AB1AB4278AEEA9` (`sumId`),
  KEY `FK85AB1AB4E6FEA217` (`fileId`),
  CONSTRAINT `FK85AB1AB4E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK85AB1AB412CAE533` FOREIGN KEY (`sumId`) REFERENCES `conf_summary` (`sumId`),
  CONSTRAINT `FK85AB1AB4278AEEA9` FOREIGN KEY (`sumId`) REFERENCES `conf_summary` (`sumId`),
  CONSTRAINT `FK85AB1AB4D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of conf_sum_attach
-- ----------------------------

-- ----------------------------
-- Table structure for `contract`
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `contractId` bigint(20) NOT NULL AUTO_INCREMENT,
  `consignAddress` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `consignee` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contractAmount` decimal(19,2) NOT NULL,
  `contractNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `creator` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `expireDate` datetime NOT NULL,
  `mainItem` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salesAfterItem` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serviceDep` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serviceMan` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signupTime` datetime NOT NULL,
  `signupUser` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `subject` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `validDate` datetime NOT NULL,
  `projectId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`contractId`),
  UNIQUE KEY `contractId` (`contractId`),
  KEY `FKDE3511121FBDC8B3` (`projectId`),
  KEY `FKDE3511126ABBB87D` (`projectId`),
  CONSTRAINT `FKDE3511126ABBB87D` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`),
  CONSTRAINT `FKDE3511121FBDC8B3` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of contract
-- ----------------------------

-- ----------------------------
-- Table structure for `contract_attach`
-- ----------------------------
DROP TABLE IF EXISTS `contract_attach`;
CREATE TABLE `contract_attach` (
  `fileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fileId`),
  UNIQUE KEY `fileId` (`fileId`),
  KEY `FK4EECE32EDAB710B` (`contractId`),
  KEY `FK4EECE32D23E98A1` (`fileId`),
  KEY `FK3B89E212EDAB710B` (`contractId`),
  KEY `FK3B89E212D23E98A1` (`fileId`),
  KEY `FK4EECE3238A960D5` (`contractId`),
  KEY `FK4EECE32E6FEA217` (`fileId`),
  KEY `FK3B89E21238A960D5` (`contractId`),
  KEY `FK3B89E212E6FEA217` (`fileId`),
  CONSTRAINT `FK3B89E212E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK3B89E21238A960D5` FOREIGN KEY (`contractId`) REFERENCES `user_contract` (`contractId`),
  CONSTRAINT `FK3B89E212D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK3B89E212EDAB710B` FOREIGN KEY (`contractId`) REFERENCES `user_contract` (`contractId`),
  CONSTRAINT `FK4EECE3238A960D5` FOREIGN KEY (`contractId`) REFERENCES `user_contract` (`contractId`),
  CONSTRAINT `FK4EECE32D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK4EECE32E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK4EECE32EDAB710B` FOREIGN KEY (`contractId`) REFERENCES `user_contract` (`contractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of contract_attach
-- ----------------------------

-- ----------------------------
-- Table structure for `contract_config`
-- ----------------------------
DROP TABLE IF EXISTS `contract_config`;
CREATE TABLE `contract_config` (
  `configId` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(18,0) NOT NULL,
  `itemName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `itemSpec` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `notes` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`configId`),
  UNIQUE KEY `configId` (`configId`),
  KEY `FK3EAA770F8C579FD9` (`contractId`),
  KEY `FK3EAA770FA117A94F` (`contractId`),
  CONSTRAINT `FK3EAA770FA117A94F` FOREIGN KEY (`contractId`) REFERENCES `contract` (`contractId`),
  CONSTRAINT `FK3EAA770F8C579FD9` FOREIGN KEY (`contractId`) REFERENCES `contract` (`contractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of contract_config
-- ----------------------------

-- ----------------------------
-- Table structure for `contract_event`
-- ----------------------------
DROP TABLE IF EXISTS `contract_event`;
CREATE TABLE `contract_event` (
  `eventId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime NOT NULL,
  `creator` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `eventDescp` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `eventName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `contractId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`eventId`),
  UNIQUE KEY `eventId` (`eventId`),
  KEY `FK4C77512DEDAB710B` (`contractId`),
  KEY `FK4C77512D38A960D5` (`contractId`),
  CONSTRAINT `FK4C77512D38A960D5` FOREIGN KEY (`contractId`) REFERENCES `user_contract` (`contractId`),
  CONSTRAINT `FK4C77512DEDAB710B` FOREIGN KEY (`contractId`) REFERENCES `user_contract` (`contractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of contract_event
-- ----------------------------

-- ----------------------------
-- Table structure for `contract_file`
-- ----------------------------
DROP TABLE IF EXISTS `contract_file`;
CREATE TABLE `contract_file` (
  `contractId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`contractId`,`fileId`),
  KEY `FKE16F78898C579FD9` (`contractId`),
  KEY `FKE16F7889D23E98A1` (`fileId`),
  KEY `FKE16F7889A117A94F` (`contractId`),
  KEY `FKE16F7889E6FEA217` (`fileId`),
  CONSTRAINT `FKE16F7889E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKE16F78898C579FD9` FOREIGN KEY (`contractId`) REFERENCES `contract` (`contractId`),
  CONSTRAINT `FKE16F7889A117A94F` FOREIGN KEY (`contractId`) REFERENCES `contract` (`contractId`),
  CONSTRAINT `FKE16F7889D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of contract_file
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountsNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyScale` int(11) DEFAULT NULL,
  `currencyUnit` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerManager` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `customerName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `customerNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `customerSource` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerType` smallint(6) NOT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `industryType` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openBank` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `otherDesc` varchar(800) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `principal` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `registerFun` decimal(19,2) DEFAULT NULL,
  `rights` smallint(6) NOT NULL,
  `site` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `taxNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `turnOver` decimal(19,2) DEFAULT NULL,
  `zip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `customerId` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for `cus_connection`
-- ----------------------------
DROP TABLE IF EXISTS `cus_connection`;
CREATE TABLE `cus_connection` (
  `connId` bigint(20) NOT NULL AUTO_INCREMENT,
  `contactor` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `creator` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `endDate` datetime NOT NULL,
  `notes` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startDate` datetime NOT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`connId`),
  UNIQUE KEY `connId` (`connId`),
  KEY `FKE2170F9C4ECFFA71` (`customerId`),
  KEY `FKE2170F9C639003E7` (`customerId`),
  CONSTRAINT `FKE2170F9C639003E7` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  CONSTRAINT `FKE2170F9C4ECFFA71` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cus_connection
-- ----------------------------

-- ----------------------------
-- Table structure for `cus_linkman`
-- ----------------------------
DROP TABLE IF EXISTS `cus_linkman`;
CREATE TABLE `cus_linkman` (
  `linkmanId` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `hobby` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homeAddress` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homePhone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homeZip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isPrimary` smallint(6) NOT NULL,
  `mobile` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `msn` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qq` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` smallint(6) NOT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`linkmanId`),
  UNIQUE KEY `linkmanId` (`linkmanId`),
  KEY `FK6BDD93224ECFFA71` (`customerId`),
  KEY `FK6BDD9322639003E7` (`customerId`),
  CONSTRAINT `FK6BDD9322639003E7` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  CONSTRAINT `FK6BDD93224ECFFA71` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cus_linkman
-- ----------------------------

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `depId` bigint(20) NOT NULL AUTO_INCREMENT,
  `depDesc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `depLevel` int(11) DEFAULT NULL,
  `depName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`depId`),
  UNIQUE KEY `depId` (`depId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '维护系统', '2', '信息部门', '0', '0.1.');
INSERT INTO `department` VALUES ('2', '项目部项目部项目部项目部项目部项目部项目部', '2', '项目部', '0', '0.2.');

-- ----------------------------
-- Table structure for `depre_record`
-- ----------------------------
DROP TABLE IF EXISTS `depre_record`;
CREATE TABLE `depre_record` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `calTime` datetime NOT NULL,
  `depreAmount` decimal(18,4) NOT NULL,
  `workCapacity` decimal(18,0) DEFAULT NULL,
  `assetsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId` (`recordId`),
  KEY `FKBC728AE88A70CA0` (`assetsId`),
  KEY `FKBC728AE9D671616` (`assetsId`),
  CONSTRAINT `FKBC728AE9D671616` FOREIGN KEY (`assetsId`) REFERENCES `fixed_assets` (`assetsId`),
  CONSTRAINT `FKBC728AE88A70CA0` FOREIGN KEY (`assetsId`) REFERENCES `fixed_assets` (`assetsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of depre_record
-- ----------------------------

-- ----------------------------
-- Table structure for `depre_type`
-- ----------------------------
DROP TABLE IF EXISTS `depre_type`;
CREATE TABLE `depre_type` (
  `depreTypeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `calMethod` smallint(6) NOT NULL,
  `deprePeriod` int(11) NOT NULL,
  `typeDesc` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`depreTypeId`),
  UNIQUE KEY `depreTypeId` (`depreTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of depre_type
-- ----------------------------

-- ----------------------------
-- Table structure for `diary`
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary` (
  `diaryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dayTime` datetime NOT NULL,
  `diaryType` smallint(6) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`diaryId`),
  UNIQUE KEY `diaryId` (`diaryId`),
  KEY `FK5B263C350662A29` (`userId`),
  KEY `FK5B263C38D18473` (`userId`),
  CONSTRAINT `FK5B263C38D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK5B263C350662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of diary
-- ----------------------------

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `dicId` bigint(20) NOT NULL AUTO_INCREMENT,
  `descp` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `itemValue` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `sn` smallint(6) NOT NULL,
  `proTypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dicId`),
  UNIQUE KEY `dicId` (`dicId`),
  KEY `FK1AA570967B33B268` (`proTypeId`),
  KEY `FK1AA570968FF3BBDE` (`proTypeId`),
  CONSTRAINT `FK1AA570968FF3BBDE` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FK1AA570967B33B268` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', 'arch', '案卷保管期限', '暂时', '0', '3');
INSERT INTO `dictionary` VALUES ('2', 'arch', '案卷保管期限', '长期', '1', '3');
INSERT INTO `dictionary` VALUES ('3', 'arch', '案卷保管期限', '长久', '2', '3');
INSERT INTO `dictionary` VALUES ('4', 'arch', '案卷保管期限', '永久', '3', '3');
INSERT INTO `dictionary` VALUES ('5', 'arch', '案卷开放形式', '不开放', '0', '4');
INSERT INTO `dictionary` VALUES ('6', 'arch', '案卷开放形式', '对内开放', '1', '4');
INSERT INTO `dictionary` VALUES ('7', 'arch', '案卷开放形式', '对外开放', '2', '4');
INSERT INTO `dictionary` VALUES ('8', 'arch', '文件保管期限', '二年', '0', '6');
INSERT INTO `dictionary` VALUES ('9', 'arch', '文件保管期限', '五年', '1', '6');
INSERT INTO `dictionary` VALUES ('10', 'arch', '文件保管期限', '十年', '2', '6');
INSERT INTO `dictionary` VALUES ('11', 'arch', '文件保管期限', '二十年', '3', '6');
INSERT INTO `dictionary` VALUES ('12', 'arch', '文件保管期限', '五十年', '4', '6');
INSERT INTO `dictionary` VALUES ('13', 'arch', '文件开放形式', '对内开放', '0', '7');
INSERT INTO `dictionary` VALUES ('14', 'arch', '文件开放形式', '对外开放', '1', '7');
INSERT INTO `dictionary` VALUES ('15', 'arch', '文件开放形式', '不开放', '1', '7');
INSERT INTO `dictionary` VALUES ('16', 'arch', '文件密级', '公开资料', '0', '8');
INSERT INTO `dictionary` VALUES ('17', 'arch', '文件密级', '内部资料', '1', '8');
INSERT INTO `dictionary` VALUES ('18', 'arch', '文件密级', '秘密', '2', '8');
INSERT INTO `dictionary` VALUES ('19', 'arch', '文件密级', '机密', '3', '8');
INSERT INTO `dictionary` VALUES ('20', 'arch', '文件密级', '绝密', '4', '8');
INSERT INTO `dictionary` VALUES ('21', 'arch', '全宗开放形式', '对内开放', '0', '10');
INSERT INTO `dictionary` VALUES ('22', 'arch', '全宗开放形式', '对外开放', '1', '10');
INSERT INTO `dictionary` VALUES ('23', 'arch', '全宗开放形式', '不开放', '2', '10');
INSERT INTO `dictionary` VALUES ('24', 'arch', '借阅方式', '线上借阅', '0', '12');
INSERT INTO `dictionary` VALUES ('25', 'arch', '借阅方式', '实物借阅', '1', '12');
INSERT INTO `dictionary` VALUES ('26', 'arch', '借阅目的', '学术研究', '0', '13');
INSERT INTO `dictionary` VALUES ('27', 'arch', '借阅目的', '经济建设', '1', '13');
INSERT INTO `dictionary` VALUES ('28', 'arch', '借阅目的', '工作考察', '2', '13');
INSERT INTO `dictionary` VALUES ('29', '', '民族', '独龙族', '0', null);
INSERT INTO `dictionary` VALUES ('30', '', '民族', '鄂伦春族', '0', null);
INSERT INTO `dictionary` VALUES ('31', '', '民族', '俄罗斯族', '0', null);
INSERT INTO `dictionary` VALUES ('32', '', '民族', '鄂温克族', '0', null);
INSERT INTO `dictionary` VALUES ('33', '', '民族', '高山族', '0', null);
INSERT INTO `dictionary` VALUES ('34', '', '民族', '仡佬族', '0', null);
INSERT INTO `dictionary` VALUES ('35', '', '民族', '哈尼族', '0', null);
INSERT INTO `dictionary` VALUES ('36', '', '民族', '啥萨克族', '0', null);
INSERT INTO `dictionary` VALUES ('37', '', '民族', '赫哲族', '0', null);
INSERT INTO `dictionary` VALUES ('38', '', '民族', '回族', '0', null);
INSERT INTO `dictionary` VALUES ('39', '', '民族', '基诺族', '0', null);
INSERT INTO `dictionary` VALUES ('40', '', '民族', '京族', '0', null);
INSERT INTO `dictionary` VALUES ('41', '', '民族', '景颇族', '0', null);
INSERT INTO `dictionary` VALUES ('42', '', '民族', '柯尔克孜族', '0', null);
INSERT INTO `dictionary` VALUES ('43', '', '民族', '拉祜族', '0', null);
INSERT INTO `dictionary` VALUES ('44', '', '民族', '黎族', '0', null);
INSERT INTO `dictionary` VALUES ('45', '', '民族', '僳僳族', '0', null);
INSERT INTO `dictionary` VALUES ('46', '', '民族', '珞巴族', '0', null);
INSERT INTO `dictionary` VALUES ('47', '', '民族', '满族', '0', null);
INSERT INTO `dictionary` VALUES ('48', '', '民族', '毛南族', '0', null);
INSERT INTO `dictionary` VALUES ('49', '', '民族', '门巴族', '0', null);
INSERT INTO `dictionary` VALUES ('50', '', '民族', '蒙古族', '0', null);
INSERT INTO `dictionary` VALUES ('51', '', '民族', '苗族', '0', null);
INSERT INTO `dictionary` VALUES ('52', '', '民族', '仫佬族', '0', null);
INSERT INTO `dictionary` VALUES ('53', '', '民族', '纳西族', '0', null);
INSERT INTO `dictionary` VALUES ('54', '', '民族', '怒族', '0', null);
INSERT INTO `dictionary` VALUES ('55', '', '民族', '普米族', '0', null);
INSERT INTO `dictionary` VALUES ('56', '', '民族', '羌族', '0', null);
INSERT INTO `dictionary` VALUES ('57', '', '民族', '撒拉族', '0', null);
INSERT INTO `dictionary` VALUES ('58', '', '民族', '畲族', '0', null);
INSERT INTO `dictionary` VALUES ('59', '', '民族', '水族', '0', null);
INSERT INTO `dictionary` VALUES ('60', '', '民族', '塔吉克族', '0', null);
INSERT INTO `dictionary` VALUES ('61', '', '民族', '塔塔尔族', '0', null);
INSERT INTO `dictionary` VALUES ('62', '', '民族', '土族', '0', null);
INSERT INTO `dictionary` VALUES ('63', '', '民族', '土家族', '0', null);
INSERT INTO `dictionary` VALUES ('64', '', '民族', '佤族', '0', null);
INSERT INTO `dictionary` VALUES ('65', '', '民族', '维吾尔族', '0', null);
INSERT INTO `dictionary` VALUES ('66', '', '民族', '乌孜别克族', '0', null);
INSERT INTO `dictionary` VALUES ('67', '', '民族', '锡伯族', '0', null);
INSERT INTO `dictionary` VALUES ('68', '', '民族', '瑶族', '0', null);
INSERT INTO `dictionary` VALUES ('69', '', '民族', '彝族', '0', null);
INSERT INTO `dictionary` VALUES ('70', '', '民族', '藏族', '0', null);
INSERT INTO `dictionary` VALUES ('71', '', '民族', '壮族', '0', null);
INSERT INTO `dictionary` VALUES ('72', '', '学历', '博士', '0', null);
INSERT INTO `dictionary` VALUES ('73', '', '学历', '研究生', '0', null);
INSERT INTO `dictionary` VALUES ('74', '', '学历', '本科', '0', null);
INSERT INTO `dictionary` VALUES ('75', '', '学历', '大专', '0', null);
INSERT INTO `dictionary` VALUES ('76', '', '学历', '中专', '0', null);
INSERT INTO `dictionary` VALUES ('77', '', '学历', '初中', '0', null);
INSERT INTO `dictionary` VALUES ('78', '', '学历', '小学', '0', null);
INSERT INTO `dictionary` VALUES ('79', '', '学历', '其它', '0', null);
INSERT INTO `dictionary` VALUES ('80', '', '政治面貌', '群众', '0', null);
INSERT INTO `dictionary` VALUES ('81', '', '政治面貌', '共青团员', '0', null);
INSERT INTO `dictionary` VALUES ('82', '', '政治面貌', '中共党员', '0', null);
INSERT INTO `dictionary` VALUES ('83', '', '国籍', '中华人民共和国', '0', null);
INSERT INTO `dictionary` VALUES ('84', '', '国籍', '美国', '0', null);
INSERT INTO `dictionary` VALUES ('85', '', '国籍', '俄罗斯', '0', null);
INSERT INTO `dictionary` VALUES ('86', '', '国籍', '日本', '0', null);
INSERT INTO `dictionary` VALUES ('87', '', '国籍', '韩国', '0', null);
INSERT INTO `dictionary` VALUES ('88', '', '国籍', '新加波', '0', null);
INSERT INTO `dictionary` VALUES ('89', '', '国籍', '马来西亚', '0', null);
INSERT INTO `dictionary` VALUES ('90', '', '国籍', '英国', '0', null);
INSERT INTO `dictionary` VALUES ('91', '', '国籍', '德国', '0', null);
INSERT INTO `dictionary` VALUES ('92', '', '国籍', '意大利', '0', null);
INSERT INTO `dictionary` VALUES ('93', '', '国籍', '澳大利亚', '0', null);
INSERT INTO `dictionary` VALUES ('94', '', '国籍', '巴西', '0', null);
INSERT INTO `dictionary` VALUES ('95', '', '专业', '管理科学', '0', null);
INSERT INTO `dictionary` VALUES ('96', '', '专业', '信息管理和信息系统', '0', null);
INSERT INTO `dictionary` VALUES ('97', '', '专业', '工业工程', '0', null);
INSERT INTO `dictionary` VALUES ('98', '', '专业', '工程管理', '0', null);
INSERT INTO `dictionary` VALUES ('99', '', '专业', '农业经理管理', '0', null);
INSERT INTO `dictionary` VALUES ('100', '', '专业', '工商管理', '0', null);
INSERT INTO `dictionary` VALUES ('101', '', '专业', '市场营销', '0', null);
INSERT INTO `dictionary` VALUES ('102', '', '专业', '会计学', '0', null);
INSERT INTO `dictionary` VALUES ('103', '', '专业', '涉外会计', '0', null);
INSERT INTO `dictionary` VALUES ('104', '', '专业', '会计电算化', '0', null);
INSERT INTO `dictionary` VALUES ('105', '', '专业', '财政金融', '0', null);
INSERT INTO `dictionary` VALUES ('106', '', '专业', '财务管理', '0', null);
INSERT INTO `dictionary` VALUES ('107', '', '专业', '技术经济', '0', null);
INSERT INTO `dictionary` VALUES ('108', '', '专业', '文秘', '0', null);
INSERT INTO `dictionary` VALUES ('109', '', '专业', '国际商务', '0', null);
INSERT INTO `dictionary` VALUES ('110', '', '专业', '物流管理', '0', null);
INSERT INTO `dictionary` VALUES ('111', '', '专业', '行政管理', '0', null);
INSERT INTO `dictionary` VALUES ('112', '', '专业', '公共事业管理', '0', null);
INSERT INTO `dictionary` VALUES ('113', '', '专业', '旅游管理', '0', null);
INSERT INTO `dictionary` VALUES ('114', '', '专业', '宾馆/酒店管理', '0', null);
INSERT INTO `dictionary` VALUES ('115', '', '专业', '人力资源管理', '0', null);
INSERT INTO `dictionary` VALUES ('116', '', '专业', '公共关系学', '0', null);
INSERT INTO `dictionary` VALUES ('117', '', '专业', '物业管理', '0', null);
INSERT INTO `dictionary` VALUES ('118', '', '专业', '房地产经营管理', '0', null);
INSERT INTO `dictionary` VALUES ('119', '', '专业', '劳动与社会保障', '0', null);
INSERT INTO `dictionary` VALUES ('120', '', '专业', '土地资源管理', '0', null);
INSERT INTO `dictionary` VALUES ('121', '', '专业', '图书档案学', '0', null);
INSERT INTO `dictionary` VALUES ('122', '', '专业', '计算机科学与技术', '0', null);
INSERT INTO `dictionary` VALUES ('123', '', '专业', '计算机应用', '0', null);
INSERT INTO `dictionary` VALUES ('124', '', '专业', '计算机信息管理', '0', null);
INSERT INTO `dictionary` VALUES ('125', '', '专业', '计算机网络', '0', null);
INSERT INTO `dictionary` VALUES ('126', '', '专业', '电子商务', '0', null);
INSERT INTO `dictionary` VALUES ('127', '', '专业', '通信工程', '0', null);
INSERT INTO `dictionary` VALUES ('128', '', '专业', '电气工程及其自动化', '0', null);
INSERT INTO `dictionary` VALUES ('129', '', '专业', '软件工程', '0', null);
INSERT INTO `dictionary` VALUES ('130', '', '专业', '自动化', '0', null);
INSERT INTO `dictionary` VALUES ('131', '', '专业', '电子信息工程', '0', null);
INSERT INTO `dictionary` VALUES ('132', '', '专业', '电子科学与技术', '0', null);
INSERT INTO `dictionary` VALUES ('133', '', '专业', '电子信息科学与技术', '0', null);
INSERT INTO `dictionary` VALUES ('134', '', '专业', '微电子学', '0', null);
INSERT INTO `dictionary` VALUES ('135', '', '专业', '光信息科学与技术', '0', null);
INSERT INTO `dictionary` VALUES ('136', '', '专业', '机械设计制造及其自动化', '0', null);
INSERT INTO `dictionary` VALUES ('137', '', '专业', '材料成型及控制工程', '0', null);
INSERT INTO `dictionary` VALUES ('138', '', '专业', '工业设计', '0', null);
INSERT INTO `dictionary` VALUES ('139', '', '专业', '过程装备与控制工程', '0', null);
INSERT INTO `dictionary` VALUES ('140', '', '专业', '机械电子工程/机电一体化', '0', null);
INSERT INTO `dictionary` VALUES ('141', '', '专业', '模具设计与制造', '0', null);
INSERT INTO `dictionary` VALUES ('142', '', '专业', '机械制造工艺与设备', '0', null);
INSERT INTO `dictionary` VALUES ('143', '', '专业', '测控技术与仪器', '0', null);
INSERT INTO `dictionary` VALUES ('144', '', '专业', '热能与动力工程', '0', null);
INSERT INTO `dictionary` VALUES ('145', '', '专业', '核工程与核技术', '0', null);
INSERT INTO `dictionary` VALUES ('146', '', '专业', '电力系统及自动化', '0', null);
INSERT INTO `dictionary` VALUES ('147', '', '专业', '制冷与低温技术', '0', null);
INSERT INTO `dictionary` VALUES ('148', '', '专业', '冶金工程', '0', null);
INSERT INTO `dictionary` VALUES ('149', '', '专业', '金属材料工程', '0', null);
INSERT INTO `dictionary` VALUES ('150', '', '专业', '无机非金属料工程', '0', null);
INSERT INTO `dictionary` VALUES ('151', '', '专业', '高分子材料与工程', '0', null);
INSERT INTO `dictionary` VALUES ('152', '', '专业', '材料物理', '0', null);
INSERT INTO `dictionary` VALUES ('153', '', '专业', '材料化学', '0', null);
INSERT INTO `dictionary` VALUES ('154', '', '专业', '材料科学与工程', '0', null);
INSERT INTO `dictionary` VALUES ('155', '', '专业', '食品科学与工程', '0', null);
INSERT INTO `dictionary` VALUES ('156', '', '专业', '轻化工程', '0', null);
INSERT INTO `dictionary` VALUES ('157', '', '专业', '包装工程', '0', null);
INSERT INTO `dictionary` VALUES ('158', '', '专业', '印刷工程', '0', null);
INSERT INTO `dictionary` VALUES ('159', '', '专业', '纺织工程', '0', null);
INSERT INTO `dictionary` VALUES ('160', '', '专业', '服装设计与工程', '0', null);
INSERT INTO `dictionary` VALUES ('161', '', '专业', '建筑学', '0', null);
INSERT INTO `dictionary` VALUES ('162', '', '专业', '城市规划', '0', null);
INSERT INTO `dictionary` VALUES ('163', '', '专业', '园林规划与设计', '0', null);
INSERT INTO `dictionary` VALUES ('164', '', '专业', '土木工程', '0', null);
INSERT INTO `dictionary` VALUES ('165', '', '专业', '道路与桥梁', '0', null);
INSERT INTO `dictionary` VALUES ('166', '', '专业', '建设环境与设备工程', '0', null);
INSERT INTO `dictionary` VALUES ('167', '', '专业', '给水排水工程', '0', null);
INSERT INTO `dictionary` VALUES ('168', '', '专业', '供热通风与空调工程', '0', null);
INSERT INTO `dictionary` VALUES ('169', '', '专业', '工业与民用建筑', '0', null);
INSERT INTO `dictionary` VALUES ('170', '', '专业', '室内装潢设计', '0', null);
INSERT INTO `dictionary` VALUES ('171', '', '专业', '建筑工程', '0', null);
INSERT INTO `dictionary` VALUES ('172', '', '专业', '工程造价管理', '0', null);
INSERT INTO `dictionary` VALUES ('173', '', '专业', '力学', '0', null);
INSERT INTO `dictionary` VALUES ('174', '', '专业', '应用力学', '0', null);
INSERT INTO `dictionary` VALUES ('175', '', '专业', '环境科学', '0', null);
INSERT INTO `dictionary` VALUES ('176', '', '专业', '生态学', '0', null);
INSERT INTO `dictionary` VALUES ('177', '', '专业', '环境工程', '0', null);
INSERT INTO `dictionary` VALUES ('178', '', '专业', '安全工程', '0', null);
INSERT INTO `dictionary` VALUES ('179', '', '专业', '制药工程', '0', null);
INSERT INTO `dictionary` VALUES ('180', '', '专业', '交通运输', '0', null);
INSERT INTO `dictionary` VALUES ('181', '', '专业', '交通工程', '0', null);
INSERT INTO `dictionary` VALUES ('182', '', '专业', '油气储运工程', '0', null);
INSERT INTO `dictionary` VALUES ('183', '', '专业', '飞行技术', '0', null);
INSERT INTO `dictionary` VALUES ('184', '', '专业', '航海技术', '0', null);
INSERT INTO `dictionary` VALUES ('185', '', '专业', '轮机工程', '0', null);
INSERT INTO `dictionary` VALUES ('186', '', '专业', '汽车工程', '0', null);
INSERT INTO `dictionary` VALUES ('187', '', '专业', '飞行器设计与工程', '0', null);
INSERT INTO `dictionary` VALUES ('188', '', '专业', '飞行器动力工程', '0', null);
INSERT INTO `dictionary` VALUES ('189', '', '专业', '飞行器制造工程', '0', null);
INSERT INTO `dictionary` VALUES ('190', '', '专业', '飞行器环境与生命保障工程', '0', null);
INSERT INTO `dictionary` VALUES ('191', '', '专业', '船舶与海洋工程', '0', null);
INSERT INTO `dictionary` VALUES ('192', '', '专业', '水利水电工程', '0', null);
INSERT INTO `dictionary` VALUES ('193', '', '专业', '水文与水资源工程', '0', null);
INSERT INTO `dictionary` VALUES ('194', '', '专业', '港口航道与海岸工程', '0', null);
INSERT INTO `dictionary` VALUES ('195', '', '专业', '测绘工程', '0', null);
INSERT INTO `dictionary` VALUES ('196', '', '专业', '公安技术', '0', null);
INSERT INTO `dictionary` VALUES ('197', '', '专业', '武器系统与发射工程', '0', null);
INSERT INTO `dictionary` VALUES ('198', '', '专业', '探测制导与控制技术', '0', null);
INSERT INTO `dictionary` VALUES ('199', '', '专业', '弹药工程与爆炸技术', '0', null);
INSERT INTO `dictionary` VALUES ('200', '', '专业', '数学与应用数学', '0', null);
INSERT INTO `dictionary` VALUES ('201', '', '专业', '信息与计算科学', '0', null);
INSERT INTO `dictionary` VALUES ('202', '', '专业', '物理学', '0', null);
INSERT INTO `dictionary` VALUES ('203', '', '专业', '应用物理学', '0', null);
INSERT INTO `dictionary` VALUES ('204', '', '专业', '化学', '0', null);
INSERT INTO `dictionary` VALUES ('205', '', '专业', '应用化学', '0', null);
INSERT INTO `dictionary` VALUES ('206', '', '专业', '化学工程与工艺', '0', null);
INSERT INTO `dictionary` VALUES ('207', '', '专业', '精细化工', '0', null);
INSERT INTO `dictionary` VALUES ('208', '', '专业', '化工设备与机械', '0', null);
INSERT INTO `dictionary` VALUES ('209', '', '专业', '生物工程', '0', null);
INSERT INTO `dictionary` VALUES ('210', '', '专业', '生物医学工程', '0', null);
INSERT INTO `dictionary` VALUES ('211', '', '专业', '生物科学,技术', '0', null);
INSERT INTO `dictionary` VALUES ('212', '', '专业', '天文学', '0', null);
INSERT INTO `dictionary` VALUES ('213', '', '专业', '地质学', '0', null);
INSERT INTO `dictionary` VALUES ('214', '', '专业', '宝石鉴定与加工', '0', null);
INSERT INTO `dictionary` VALUES ('215', '', '专业', '地理科学', '0', null);
INSERT INTO `dictionary` VALUES ('216', '', '专业', '地球物理学', '0', null);
INSERT INTO `dictionary` VALUES ('217', '', '专业', '大气科学', '0', null);
INSERT INTO `dictionary` VALUES ('218', '', '专业', '海洋科学', '0', null);
INSERT INTO `dictionary` VALUES ('219', '', '专业', '地矿', '0', null);
INSERT INTO `dictionary` VALUES ('220', '', '专业', '石油工程', '0', null);
INSERT INTO `dictionary` VALUES ('221', '', '专业', '经济学', '0', null);
INSERT INTO `dictionary` VALUES ('222', '', '专业', '国际经济与贸易', '0', null);
INSERT INTO `dictionary` VALUES ('223', '', '专业', '财政学', '0', null);
INSERT INTO `dictionary` VALUES ('224', '', '专业', '金融学', '0', null);
INSERT INTO `dictionary` VALUES ('225', '', '专业', '经济管理', '0', null);
INSERT INTO `dictionary` VALUES ('226', '', '专业', '经济信息管理', '0', null);
INSERT INTO `dictionary` VALUES ('227', '', '专业', '工业外贸', '0', null);
INSERT INTO `dictionary` VALUES ('228', '', '专业', '国际金融', '0', null);
INSERT INTO `dictionary` VALUES ('229', '', '专业', '投资经济管理', '0', null);
INSERT INTO `dictionary` VALUES ('230', '', '专业', '统计学', '0', null);
INSERT INTO `dictionary` VALUES ('231', '', '专业', '审计学', '0', null);
INSERT INTO `dictionary` VALUES ('232', '', '专业', '中国语言文学', '0', null);
INSERT INTO `dictionary` VALUES ('233', '', '专业', '英语', '0', null);
INSERT INTO `dictionary` VALUES ('234', '', '专业', '俄语', '0', null);
INSERT INTO `dictionary` VALUES ('235', '', '专业', '德语', '0', null);
INSERT INTO `dictionary` VALUES ('236', '', '专业', '法语', '0', null);
INSERT INTO `dictionary` VALUES ('237', '', '专业', '日语', '0', null);
INSERT INTO `dictionary` VALUES ('238', '', '专业', '西班牙语', '0', null);
INSERT INTO `dictionary` VALUES ('239', '', '专业', '阿拉伯语', '0', null);
INSERT INTO `dictionary` VALUES ('240', '', '专业', '朝鲜语', '0', null);
INSERT INTO `dictionary` VALUES ('241', '', '专业', '其它外语', '0', null);
INSERT INTO `dictionary` VALUES ('242', '', '专业', '新闻学', '0', null);
INSERT INTO `dictionary` VALUES ('243', '', '专业', '广播电视新闻', '0', null);
INSERT INTO `dictionary` VALUES ('244', '', '专业', '广告学', '0', null);
INSERT INTO `dictionary` VALUES ('245', '', '专业', '编辑出版学', '0', null);
INSERT INTO `dictionary` VALUES ('246', '', '专业', '外贸英语', '0', null);
INSERT INTO `dictionary` VALUES ('247', '', '专业', '商务英语', '0', null);
INSERT INTO `dictionary` VALUES ('248', '', '专业', '音乐,舞蹈,作曲', '0', null);
INSERT INTO `dictionary` VALUES ('249', '', '专业', '绘画,艺术设计', '0', null);
INSERT INTO `dictionary` VALUES ('250', '', '专业', '戏剧,表演', '0', null);
INSERT INTO `dictionary` VALUES ('251', '', '专业', '导演,广播电视编导', '0', null);
INSERT INTO `dictionary` VALUES ('252', '', '专业', '戏剧影视文学', '0', null);
INSERT INTO `dictionary` VALUES ('253', '', '专业', '戏剧影视美术设计', '0', null);
INSERT INTO `dictionary` VALUES ('254', '', '专业', '摄影,动画', '0', null);
INSERT INTO `dictionary` VALUES ('255', '', '专业', '播音,主持,录音', '0', null);
INSERT INTO `dictionary` VALUES ('256', '', '专业', '服装设计', '0', null);
INSERT INTO `dictionary` VALUES ('257', '', '专业', '法学', '0', null);
INSERT INTO `dictionary` VALUES ('258', '', '专业', '马克思主义理论', '0', null);
INSERT INTO `dictionary` VALUES ('259', '', '专业', '社会学', '0', null);
INSERT INTO `dictionary` VALUES ('260', '', '专业', '政治学与行政学', '0', null);
INSERT INTO `dictionary` VALUES ('261', '', '专业', '国际政治', '0', null);
INSERT INTO `dictionary` VALUES ('262', '', '专业', '外交学', '0', null);
INSERT INTO `dictionary` VALUES ('263', '', '专业', '思想政治教育', '0', null);
INSERT INTO `dictionary` VALUES ('264', '', '专业', '公安学', '0', null);
INSERT INTO `dictionary` VALUES ('265', '', '专业', '经济法', '0', null);
INSERT INTO `dictionary` VALUES ('266', '', '专业', '国际经济法', '0', null);
INSERT INTO `dictionary` VALUES ('267', '', '专业', '哲学', '0', null);
INSERT INTO `dictionary` VALUES ('268', '', '专业', '逻辑学', '0', null);
INSERT INTO `dictionary` VALUES ('269', '', '专业', '宗教学', '0', null);
INSERT INTO `dictionary` VALUES ('270', '', '专业', '教育学', '0', null);
INSERT INTO `dictionary` VALUES ('271', '', '专业', '学前教育', '0', null);
INSERT INTO `dictionary` VALUES ('272', '', '专业', '体育学', '0', null);
INSERT INTO `dictionary` VALUES ('273', '', '专业', '基础医学', '0', null);
INSERT INTO `dictionary` VALUES ('274', '', '专业', '预防医学', '0', null);
INSERT INTO `dictionary` VALUES ('275', '', '专业', '临床医学与医学技术', '0', null);
INSERT INTO `dictionary` VALUES ('276', '', '专业', '口腔医学', '0', null);
INSERT INTO `dictionary` VALUES ('277', '', '专业', '中医学', '0', null);
INSERT INTO `dictionary` VALUES ('278', '', '专业', '法医学', '0', null);
INSERT INTO `dictionary` VALUES ('279', '', '专业', '护理学', '0', null);
INSERT INTO `dictionary` VALUES ('280', '', '专业', '药学', '0', null);
INSERT INTO `dictionary` VALUES ('281', '', '专业', '心理学', '0', null);
INSERT INTO `dictionary` VALUES ('282', '', '专业', '医学检验', '0', null);
INSERT INTO `dictionary` VALUES ('283', '', '专业', '植物生产', '0', null);
INSERT INTO `dictionary` VALUES ('284', '', '专业', '农学', '0', null);
INSERT INTO `dictionary` VALUES ('285', '', '专业', '园艺', '0', null);
INSERT INTO `dictionary` VALUES ('286', '', '专业', '植物保护学', '0', null);
INSERT INTO `dictionary` VALUES ('287', '', '专业', '茶学', '0', null);
INSERT INTO `dictionary` VALUES ('288', '', '专业', '草业科学', '0', null);
INSERT INTO `dictionary` VALUES ('289', '', '专业', '森林资源', '0', null);
INSERT INTO `dictionary` VALUES ('290', '', '专业', '环境生态', '0', null);
INSERT INTO `dictionary` VALUES ('291', '', '专业', '园林', '0', null);
INSERT INTO `dictionary` VALUES ('292', '', '专业', '动物生产', '0', null);
INSERT INTO `dictionary` VALUES ('293', '', '专业', '动物医学', '0', null);
INSERT INTO `dictionary` VALUES ('294', '', '专业', '水产类', '0', null);
INSERT INTO `dictionary` VALUES ('295', '', '专业', '农业工程', '0', null);
INSERT INTO `dictionary` VALUES ('296', '', '专业', '林业工程', '0', null);
INSERT INTO `dictionary` VALUES ('297', '', '专业', '历史学', '0', null);
INSERT INTO `dictionary` VALUES ('298', '', '专业', '考古学', '0', null);
INSERT INTO `dictionary` VALUES ('299', '', '专业', '博物馆学', '0', null);

-- ----------------------------
-- Table structure for `document`
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `docId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `docName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `haveAttach` smallint(6) DEFAULT NULL,
  `isShared` smallint(6) NOT NULL,
  `sharedDepIds` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sharedDepNames` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sharedRoleIds` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sharedRoleNames` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sharedUserIds` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sharedUserNames` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`docId`),
  UNIQUE KEY `docId` (`docId`),
  KEY `FK335CD11BE6F968F2` (`folderId`),
  KEY `FK335CD11B50662A29` (`userId`),
  KEY `FK335CD11B6A3A8E3C` (`folderId`),
  KEY `FK335CD11B8D18473` (`userId`),
  CONSTRAINT `FK335CD11B8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK335CD11B50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK335CD11B6A3A8E3C` FOREIGN KEY (`folderId`) REFERENCES `doc_folder` (`folderId`),
  CONSTRAINT `FK335CD11BE6F968F2` FOREIGN KEY (`folderId`) REFERENCES `doc_folder` (`folderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of document
-- ----------------------------

-- ----------------------------
-- Table structure for `doc_file`
-- ----------------------------
DROP TABLE IF EXISTS `doc_file`;
CREATE TABLE `doc_file` (
  `docId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`docId`,`fileId`),
  KEY `FK3223AC23176F25EB` (`docId`),
  KEY `FK3223AC23D23E98A1` (`fileId`),
  KEY `FK3223AC232C2F2F61` (`docId`),
  KEY `FK3223AC23E6FEA217` (`fileId`),
  CONSTRAINT `FK3223AC23E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK3223AC23176F25EB` FOREIGN KEY (`docId`) REFERENCES `document` (`docId`),
  CONSTRAINT `FK3223AC232C2F2F61` FOREIGN KEY (`docId`) REFERENCES `document` (`docId`),
  CONSTRAINT `FK3223AC23D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of doc_file
-- ----------------------------

-- ----------------------------
-- Table structure for `doc_folder`
-- ----------------------------
DROP TABLE IF EXISTS `doc_folder`;
CREATE TABLE `doc_folder` (
  `folderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `folderName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `isShared` smallint(6) NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `path` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `folderId` (`folderId`),
  KEY `FK383DC55550662A29` (`userId`),
  KEY `FK383DC5558D18473` (`userId`),
  CONSTRAINT `FK383DC5558D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK383DC55550662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of doc_folder
-- ----------------------------

-- ----------------------------
-- Table structure for `doc_history`
-- ----------------------------
DROP TABLE IF EXISTS `doc_history`;
CREATE TABLE `doc_history` (
  `historyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `docName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `mender` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `path` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `updatetime` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  `docId` bigint(20) DEFAULT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`historyId`),
  UNIQUE KEY `historyId` (`historyId`),
  KEY `FK2F74588DB7505EF8` (`docId`),
  KEY `FK2F74588DD23E98A1` (`fileId`),
  KEY `FK2F74588D9C33E2EE` (`docId`),
  KEY `FK2F74588DE6FEA217` (`fileId`),
  CONSTRAINT `FK2F74588DE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK2F74588D9C33E2EE` FOREIGN KEY (`docId`) REFERENCES `archives_doc` (`docId`),
  CONSTRAINT `FK2F74588DB7505EF8` FOREIGN KEY (`docId`) REFERENCES `archives_doc` (`docId`),
  CONSTRAINT `FK2F74588DD23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of doc_history
-- ----------------------------

-- ----------------------------
-- Table structure for `doc_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `doc_privilege`;
CREATE TABLE `doc_privilege` (
  `privilegeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `fdFlag` smallint(6) NOT NULL,
  `flag` smallint(6) NOT NULL,
  `rights` int(11) NOT NULL,
  `udrId` int(11) DEFAULT NULL,
  `udrName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `docId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`privilegeId`),
  UNIQUE KEY `privilegeId` (`privilegeId`),
  KEY `FK5EE2506A176F25EB` (`docId`),
  KEY `FK5EE2506AE6F968F2` (`folderId`),
  KEY `FK5EE2506A2C2F2F61` (`docId`),
  KEY `FK5EE2506A6A3A8E3C` (`folderId`),
  CONSTRAINT `FK5EE2506A6A3A8E3C` FOREIGN KEY (`folderId`) REFERENCES `doc_folder` (`folderId`),
  CONSTRAINT `FK5EE2506A176F25EB` FOREIGN KEY (`docId`) REFERENCES `document` (`docId`),
  CONSTRAINT `FK5EE2506A2C2F2F61` FOREIGN KEY (`docId`) REFERENCES `document` (`docId`),
  CONSTRAINT `FK5EE2506AE6F968F2` FOREIGN KEY (`folderId`) REFERENCES `doc_folder` (`folderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of doc_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for `duty`
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty` (
  `dutyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `endTime` datetime DEFAULT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` datetime NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `systemId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dutyId`),
  UNIQUE KEY `dutyId` (`dutyId`),
  KEY `FK2F3AD64056C727` (`systemId`),
  KEY `FK2F3AD650662A29` (`userId`),
  KEY `FK2F3AD6253A4B1D` (`systemId`),
  KEY `FK2F3AD68D18473` (`userId`),
  CONSTRAINT `FK2F3AD68D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK2F3AD6253A4B1D` FOREIGN KEY (`systemId`) REFERENCES `duty_system` (`systemId`),
  CONSTRAINT `FK2F3AD64056C727` FOREIGN KEY (`systemId`) REFERENCES `duty_system` (`systemId`),
  CONSTRAINT `FK2F3AD650662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of duty
-- ----------------------------

-- ----------------------------
-- Table structure for `duty_register`
-- ----------------------------
DROP TABLE IF EXISTS `duty_register`;
CREATE TABLE `duty_register` (
  `registerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dayOfWeek` int(11) NOT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `inOffFlag` smallint(6) NOT NULL,
  `reason` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regFlag` smallint(6) NOT NULL,
  `regMins` int(11) NOT NULL,
  `registerDate` datetime NOT NULL,
  `registerTime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `sectionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`registerId`),
  UNIQUE KEY `registerId` (`registerId`),
  KEY `FK79F7F5EC3BBDEDF7` (`sectionId`),
  KEY `FK79F7F5EC50662A29` (`userId`),
  KEY `FK79F7F5ECF34AE8C1` (`sectionId`),
  KEY `FK79F7F5EC8D18473` (`userId`),
  CONSTRAINT `FK79F7F5EC8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK79F7F5EC3BBDEDF7` FOREIGN KEY (`sectionId`) REFERENCES `duty_section` (`sectionId`),
  CONSTRAINT `FK79F7F5EC50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK79F7F5ECF34AE8C1` FOREIGN KEY (`sectionId`) REFERENCES `duty_section` (`sectionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of duty_register
-- ----------------------------

-- ----------------------------
-- Table structure for `duty_section`
-- ----------------------------
DROP TABLE IF EXISTS `duty_section`;
CREATE TABLE `duty_section` (
  `sectionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dutyEndTime` datetime NOT NULL,
  `dutyEndTime1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dutyStartTime` datetime NOT NULL,
  `dutyStartTime1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `earlyOffTime` datetime NOT NULL,
  `earlyOffTime1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `endSignin` datetime NOT NULL,
  `endSignin1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sectionName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `signOutTime` datetime NOT NULL,
  `signOutTime1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startSignin` datetime NOT NULL,
  `startSignin1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sectionId`),
  UNIQUE KEY `sectionId` (`sectionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of duty_section
-- ----------------------------

-- ----------------------------
-- Table structure for `duty_system`
-- ----------------------------
DROP TABLE IF EXISTS `duty_system`;
CREATE TABLE `duty_system` (
  `systemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `isDefault` smallint(6) NOT NULL,
  `systemDesc` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `systemName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `systemSetting` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`systemId`),
  UNIQUE KEY `systemId` (`systemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of duty_system
-- ----------------------------

-- ----------------------------
-- Table structure for `emp_profile`
-- ----------------------------
DROP TABLE IF EXISTS `emp_profile`;
CREATE TABLE `emp_profile` (
  `profileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approvalStatus` smallint(6) DEFAULT NULL,
  `awardPunishCase` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bankNo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthPlace` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `checkName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checktime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `creator` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `delFlag` smallint(6) NOT NULL,
  `depId` bigint(20) DEFAULT NULL,
  `depName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `designation` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eduCase` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eduCollege` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eduDegree` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eduMajor` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `hobby` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homeZip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idCard` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `jobId` bigint(20) DEFAULT NULL,
  `marriage` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `memo` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nationality` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openBank` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `opprovalOpinion` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `party` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profileNo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `qq` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `race` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `religion` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `standardMiNo` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `standardMoney` decimal(18,0) DEFAULT NULL,
  `standardName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startWorkDate` datetime DEFAULT NULL,
  `trainingCase` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `workCase` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  `standardId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`profileId`),
  UNIQUE KEY `profileId` (`profileId`),
  KEY `FK198E713213F41857` (`standardId`),
  KEY `FK198E71329D731ED4` (`jobId`),
  KEY `FK198E713268F4074D` (`standardId`),
  KEY `FK198E71329C3463CA` (`jobId`),
  CONSTRAINT `FK198E71329C3463CA` FOREIGN KEY (`jobId`) REFERENCES `job` (`jobId`),
  CONSTRAINT `FK198E713213F41857` FOREIGN KEY (`standardId`) REFERENCES `stand_salary` (`standardId`),
  CONSTRAINT `FK198E713268F4074D` FOREIGN KEY (`standardId`) REFERENCES `stand_salary` (`standardId`),
  CONSTRAINT `FK198E71329D731ED4` FOREIGN KEY (`jobId`) REFERENCES `job` (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of emp_profile
-- ----------------------------

-- ----------------------------
-- Table structure for `errands_register`
-- ----------------------------
DROP TABLE IF EXISTS `errands_register`;
CREATE TABLE `errands_register` (
  `dateId` bigint(20) NOT NULL AUTO_INCREMENT,
  `approvalId` bigint(20) NOT NULL,
  `approvalName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `approvalOption` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descp` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `endTime` datetime NOT NULL,
  `flag` smallint(6) DEFAULT NULL,
  `startTime` datetime NOT NULL,
  `status` smallint(6) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dateId`),
  UNIQUE KEY `dateId` (`dateId`),
  KEY `FK7952CC150662A29` (`userId`),
  KEY `FK7952CC18D18473` (`userId`),
  CONSTRAINT `FK7952CC18D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK7952CC150662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of errands_register
-- ----------------------------

-- ----------------------------
-- Table structure for `file_attach`
-- ----------------------------
DROP TABLE IF EXISTS `file_attach`;
CREATE TABLE `file_attach` (
  `fileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `creator` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ext` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `filePath` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `fileType` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `totalBytes` double NOT NULL,
  PRIMARY KEY (`fileId`),
  UNIQUE KEY `fileId` (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of file_attach
-- ----------------------------
INSERT INTO `file_attach` VALUES ('1', '2011-08-20 18:07:18', '超级管理员', 'txt', 'Noname2.txt', 'arch/upload/201108/48219f7137e14c639172b3f8aaa73f4a.txt', 'arch/upload', '51.37 KB', '52607');

-- ----------------------------
-- Table structure for `fixed_assets`
-- ----------------------------
DROP TABLE IF EXISTS `fixed_assets`;
CREATE TABLE `fixed_assets` (
  `assetsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `assetCurValue` decimal(18,4) NOT NULL,
  `assetValue` decimal(18,4) NOT NULL,
  `assetsName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `assetsNo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `assetsTypeId` bigint(20) DEFAULT NULL,
  `beDep` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `buyDate` date NOT NULL,
  `custodian` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defPerWorkGross` decimal(18,0) DEFAULT NULL,
  `depreRate` decimal(18,0) DEFAULT NULL,
  `depreTypeId` bigint(20) DEFAULT NULL,
  `intendTerm` decimal(18,0) DEFAULT NULL,
  `intendWorkGross` decimal(18,0) DEFAULT NULL,
  `manuDate` date DEFAULT NULL,
  `manufacturer` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `model` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remainValRate` decimal(18,6) NOT NULL,
  `startDepre` date DEFAULT NULL,
  `workGrossUnit` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`assetsId`),
  UNIQUE KEY `assetsId` (`assetsId`),
  KEY `FK577FEAEA2FB766A` (`assetsTypeId`),
  KEY `FK577FEAED2BC831E` (`depreTypeId`),
  KEY `FK577FEAEEDF96634` (`assetsTypeId`),
  KEY `FK577FEAE27BC7214` (`depreTypeId`),
  CONSTRAINT `FK577FEAE27BC7214` FOREIGN KEY (`depreTypeId`) REFERENCES `depre_type` (`depreTypeId`),
  CONSTRAINT `FK577FEAEA2FB766A` FOREIGN KEY (`assetsTypeId`) REFERENCES `assets_type` (`assetsTypeId`),
  CONSTRAINT `FK577FEAED2BC831E` FOREIGN KEY (`depreTypeId`) REFERENCES `depre_type` (`depreTypeId`),
  CONSTRAINT `FK577FEAEEDF96634` FOREIGN KEY (`assetsTypeId`) REFERENCES `assets_type` (`assetsTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of fixed_assets
-- ----------------------------

-- ----------------------------
-- Table structure for `form_data`
-- ----------------------------
DROP TABLE IF EXISTS `form_data`;
CREATE TABLE `form_data` (
  `dataId` bigint(20) NOT NULL AUTO_INCREMENT,
  `blobValue` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `boolValue` smallint(6) DEFAULT NULL,
  `dateValue` datetime DEFAULT NULL,
  `decValue` decimal(18,4) DEFAULT NULL,
  `fieldLabel` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `fieldName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `fieldType` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `intValue` int(11) DEFAULT NULL,
  `isShowed` smallint(6) NOT NULL,
  `longValue` bigint(20) DEFAULT NULL,
  `strValue` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `textValue` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `formId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dataId`),
  UNIQUE KEY `dataId` (`dataId`),
  KEY `FK6BD6F6457CCCF1A8` (`formId`),
  KEY `FK6BD6F645C7CAE172` (`formId`),
  CONSTRAINT `FK6BD6F645C7CAE172` FOREIGN KEY (`formId`) REFERENCES `process_form` (`formId`),
  CONSTRAINT `FK6BD6F6457CCCF1A8` FOREIGN KEY (`formId`) REFERENCES `process_form` (`formId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of form_data
-- ----------------------------

-- ----------------------------
-- Table structure for `form_def`
-- ----------------------------
DROP TABLE IF EXISTS `form_def`;
CREATE TABLE `form_def` (
  `formDefId` bigint(20) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `columns` int(11) NOT NULL,
  `deployId` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `extDef` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `formName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `isEnabled` smallint(6) NOT NULL,
  PRIMARY KEY (`formDefId`),
  UNIQUE KEY `formDefId` (`formDefId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of form_def
-- ----------------------------
INSERT INTO `form_def` VALUES ('1', '开始', '1', '19', null, '开始-表单', '1');
INSERT INTO `form_def` VALUES ('2', '登记拟办', '1', '19', null, '登记拟办-表单', '1');
INSERT INTO `form_def` VALUES ('3', '领导批示', '1', '19', null, '领导批示-表单', '1');
INSERT INTO `form_def` VALUES ('4', '公文分发', '1', '19', null, '公文分发-表单', '1');
INSERT INTO `form_def` VALUES ('5', '承办传阅', '1', '19', null, '承办传阅-表单', '1');
INSERT INTO `form_def` VALUES ('6', '开始', '1', '25', null, '开始-表单', '1');
INSERT INTO `form_def` VALUES ('7', '发文核稿', '1', '25', null, '发文核稿-表单', '1');
INSERT INTO `form_def` VALUES ('8', '科室审核', '1', '25', null, '科室审核-表单', '1');
INSERT INTO `form_def` VALUES ('9', '主管领导审批', '1', '25', null, '主管领导审批-表单', '1');
INSERT INTO `form_def` VALUES ('10', '分管领导签发', '1', '25', null, '分管领导签发-表单', '1');
INSERT INTO `form_def` VALUES ('11', '发文校对', '1', '25', null, '发文校对-表单', '1');
INSERT INTO `form_def` VALUES ('12', '发文分发', '1', '25', null, '发文分发-表单', '1');

-- ----------------------------
-- Table structure for `fun_url`
-- ----------------------------
DROP TABLE IF EXISTS `fun_url`;
CREATE TABLE `fun_url` (
  `urlId` bigint(20) NOT NULL AUTO_INCREMENT,
  `urlPath` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `functionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`urlId`),
  UNIQUE KEY `urlId` (`urlId`),
  KEY `FKE19DAAAFDAC48F03` (`functionId`),
  KEY `FKE19DAAAF5E05B44D` (`functionId`),
  CONSTRAINT `FKE19DAAAF5E05B44D` FOREIGN KEY (`functionId`) REFERENCES `app_function` (`functionId`),
  CONSTRAINT `FKE19DAAAFDAC48F03` FOREIGN KEY (`functionId`) REFERENCES `app_function` (`functionId`)
) ENGINE=InnoDB AUTO_INCREMENT=550 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of fun_url
-- ----------------------------
INSERT INTO `fun_url` VALUES ('1', '/archive/saveArchFlowConf.do', '1');
INSERT INTO `fun_url` VALUES ('2', '/archive/getArchFlowConf.do', '1');
INSERT INTO `fun_url` VALUES ('3', '/archive/depListArchRecUser.do', '1');
INSERT INTO `fun_url` VALUES ('4', '/archive/selectArchRecUser.do', '1');
INSERT INTO `fun_url` VALUES ('5', '/archive/saveArchRecUser.do', '1');
INSERT INTO `fun_url` VALUES ('6', '/flow/listProDefinition.do', '1');
INSERT INTO `fun_url` VALUES ('7', '/archive/treeArchivesType.do', '2');
INSERT INTO `fun_url` VALUES ('8', '/archive/listArchTemplate.do', '2');
INSERT INTO `fun_url` VALUES ('9', '/archive/treeArchivesType.do', '3');
INSERT INTO `fun_url` VALUES ('10', '/archive/listArchTemplate.do', '3');
INSERT INTO `fun_url` VALUES ('11', '/archive/saveArchivesType.do', '3');
INSERT INTO `fun_url` VALUES ('12', '/archive/treeArchivesType.do', '4');
INSERT INTO `fun_url` VALUES ('13', '/archive/listArchTemplate.do', '4');
INSERT INTO `fun_url` VALUES ('14', '/archive/saveArchivesType.do', '4');
INSERT INTO `fun_url` VALUES ('15', '/archive/getArchivesType.do', '4');
INSERT INTO `fun_url` VALUES ('16', '/archive/treeArchivesType.do', '5');
INSERT INTO `fun_url` VALUES ('17', '/archive/listArchTemplate.do', '5');
INSERT INTO `fun_url` VALUES ('18', '/archive/multiDelArchivesType.do', '5');
INSERT INTO `fun_url` VALUES ('19', '/archive/treeArchivesType.do', '6');
INSERT INTO `fun_url` VALUES ('20', '/archive/listArchTemplate.do', '6');
INSERT INTO `fun_url` VALUES ('21', '/archive/saveArchTemplate.do', '6');
INSERT INTO `fun_url` VALUES ('22', '/archive/comboArchivesType.do', '6');
INSERT INTO `fun_url` VALUES ('23', '/archive/treeArchivesType.do', '7');
INSERT INTO `fun_url` VALUES ('24', '/archive/listArchTemplate.do', '7');
INSERT INTO `fun_url` VALUES ('25', '/archive/saveArchTemplate.do', '7');
INSERT INTO `fun_url` VALUES ('26', '/archive/comboArchivesType.do', '7');
INSERT INTO `fun_url` VALUES ('27', '/archive/getArchTemplate.do', '7');
INSERT INTO `fun_url` VALUES ('28', '/archive/treeArchivesType.do', '8');
INSERT INTO `fun_url` VALUES ('29', '/archive/listArchTemplate.do', '8');
INSERT INTO `fun_url` VALUES ('30', '/archive/multiDelArchTemplate.do', '8');
INSERT INTO `fun_url` VALUES ('31', '/archive/getFlowArchFlowConf.do', '9');
INSERT INTO `fun_url` VALUES ('32', '/archive/listArchives.do', '10');
INSERT INTO `fun_url` VALUES ('33', '/archive/listArchives.do', '11');
INSERT INTO `fun_url` VALUES ('34', '/archive/getFlowArchFlowConf.do', '11');
INSERT INTO `fun_url` VALUES ('35', '/archive/getArchives.do', '11');
INSERT INTO `fun_url` VALUES ('36', '/archive/listArchives.do', '12');
INSERT INTO `fun_url` VALUES ('37', '/archive/multiDelArchives.do', '12');
INSERT INTO `fun_url` VALUES ('38', '/archive/listArchives.do', '13');
INSERT INTO `fun_url` VALUES ('39', '/archive/listArchives.do', '14');
INSERT INTO `fun_url` VALUES ('40', '/archive/getArchives.do', '14');
INSERT INTO `fun_url` VALUES ('41', '/archive/listArchives.do', '15');
INSERT INTO `fun_url` VALUES ('42', '/archive/listArchives.do', '16');
INSERT INTO `fun_url` VALUES ('43', '/archive/getArchives.do', '16');
INSERT INTO `fun_url` VALUES ('44', '/archive/listArchives.do', '17');
INSERT INTO `fun_url` VALUES ('45', '/archive/listArchives.do', '18');
INSERT INTO `fun_url` VALUES ('46', '/archive/getArchives.do', '18');
INSERT INTO `fun_url` VALUES ('47', '/archive/listArchives.do', '19');
INSERT INTO `fun_url` VALUES ('48', '/archive/listArchives.do', '20');
INSERT INTO `fun_url` VALUES ('49', '/archive/getArchives.do', '20');
INSERT INTO `fun_url` VALUES ('50', '/archive/listArchives.do', '21');
INSERT INTO `fun_url` VALUES ('51', '/archive/listArchives.do', '22');
INSERT INTO `fun_url` VALUES ('52', '/archive/listArchives.do', '23');
INSERT INTO `fun_url` VALUES ('53', '/archive/hastenArchives.do', '23');
INSERT INTO `fun_url` VALUES ('54', '/archive/listArchives.do', '24');
INSERT INTO `fun_url` VALUES ('55', '/archive/listArchives.do', '25');
INSERT INTO `fun_url` VALUES ('56', '/archive/listDocHistory.do', '26');
INSERT INTO `fun_url` VALUES ('57', '/archive/listDocHistory.do', '27');
INSERT INTO `fun_url` VALUES ('58', '/archive/multiDelDocHistory.do', '27');
INSERT INTO `fun_url` VALUES ('59', '/archive/listArchivesDep.do', '28');
INSERT INTO `fun_url` VALUES ('60', '/archive/getArchives.do', '29');
INSERT INTO `fun_url` VALUES ('61', '/archive/listArchivesDep.do', '29');
INSERT INTO `fun_url` VALUES ('62', '/archive/getFlowArchFlowConf.do', '29');
INSERT INTO `fun_url` VALUES ('63', '/archive/listArchives.do', '30');
INSERT INTO `fun_url` VALUES ('64', '/archive/listArchives.do', '31');
INSERT INTO `fun_url` VALUES ('65', '/archive/getFlowArchFlowConf.do', '31');
INSERT INTO `fun_url` VALUES ('66', '/archive/listArchives.do', '32');
INSERT INTO `fun_url` VALUES ('67', '/archive/getFlowArchFlowConf.do', '32');
INSERT INTO `fun_url` VALUES ('68', '/archive/getArchives.do', '32');
INSERT INTO `fun_url` VALUES ('69', '/archive/listArchives.do', '33');
INSERT INTO `fun_url` VALUES ('70', '/archive/multiDelArchives.do', '33');
INSERT INTO `fun_url` VALUES ('71', '/archive/listArchivesHandle.do', '34');
INSERT INTO `fun_url` VALUES ('72', '/archive/listLeaderRead.do', '35');
INSERT INTO `fun_url` VALUES ('73', '/archive/listArchDispatch.do', '36');
INSERT INTO `fun_url` VALUES ('74', '/archive/listArchDispatch.do', '37');
INSERT INTO `fun_url` VALUES ('75', '/archive/listArchives.do', '38');
INSERT INTO `fun_url` VALUES ('76', '/archive/listArchives.do', '39');
INSERT INTO `fun_url` VALUES ('77', '/archive/hastenArchives.do', '39');
INSERT INTO `fun_url` VALUES ('78', '/archive/listArchDispatch.do', '40');
INSERT INTO `fun_url` VALUES ('79', '/archive/listArchRecType.do', '41');
INSERT INTO `fun_url` VALUES ('80', '/archive/listArchRecType.do', '42');
INSERT INTO `fun_url` VALUES ('81', '/archive/saveArchRecType.do', '42');
INSERT INTO `fun_url` VALUES ('82', '/archive/listArchRecType.do', '43');
INSERT INTO `fun_url` VALUES ('83', '/archive/saveArchRecType.do', '43');
INSERT INTO `fun_url` VALUES ('84', '/archive/getArchRecType.do', '43');
INSERT INTO `fun_url` VALUES ('85', '/archive/listArchRecType.do', '44');
INSERT INTO `fun_url` VALUES ('86', '/archive/multiDelArchRecType.do', '44');
INSERT INTO `fun_url` VALUES ('87', '/system/listAppUser.do', '45');
INSERT INTO `fun_url` VALUES ('88', '/system/listAppUser.do', '46');
INSERT INTO `fun_url` VALUES ('89', '/system/chooseRolesAppUser.do', '46');
INSERT INTO `fun_url` VALUES ('90', '/system/selectedRolesAppUser.do', '46');
INSERT INTO `fun_url` VALUES ('91', '/system/listDepartment.do', '46');
INSERT INTO `fun_url` VALUES ('92', '/system/listAppUser.do', '47');
INSERT INTO `fun_url` VALUES ('93', '/system/chooseRolesAppUser.do', '47');
INSERT INTO `fun_url` VALUES ('94', '/system/selectedRolesAppUser.do', '47');
INSERT INTO `fun_url` VALUES ('95', '/system/listDepartment.do', '47');
INSERT INTO `fun_url` VALUES ('96', '/system/listAppUser.do', '48');
INSERT INTO `fun_url` VALUES ('97', '/system/multiDelAppUser.do', '48');
INSERT INTO `fun_url` VALUES ('98', '/system/listAppUser.do', '49');
INSERT INTO `fun_url` VALUES ('99', '/system/chooseRolesAppUser.do', '49');
INSERT INTO `fun_url` VALUES ('100', '/system/selectedRolesAppUser.do', '49');
INSERT INTO `fun_url` VALUES ('101', '/system/createPasswordAppUser.do', '49');
INSERT INTO `fun_url` VALUES ('102', '/system/saveAgentAppUser.do', '50');
INSERT INTO `fun_url` VALUES ('103', '/system/listAppRole.do', '51');
INSERT INTO `fun_url` VALUES ('104', '/system/listAppRole.do', '52');
INSERT INTO `fun_url` VALUES ('105', '/system/saveAppRole.do', '52');
INSERT INTO `fun_url` VALUES ('106', '/system/listAppRole.do', '53');
INSERT INTO `fun_url` VALUES ('107', '/system/saveAppRole.do', '53');
INSERT INTO `fun_url` VALUES ('108', '/system/getAppRole.do', '53');
INSERT INTO `fun_url` VALUES ('109', '/system/listAppRole.do', '54');
INSERT INTO `fun_url` VALUES ('110', '/system/mulDelAppRole.do', '54');
INSERT INTO `fun_url` VALUES ('111', '/system/listAppRole.do', '55');
INSERT INTO `fun_url` VALUES ('112', '/system/grantXmlAppRole.do', '55');
INSERT INTO `fun_url` VALUES ('113', '/system/getAppRole.do', '55');
INSERT INTO `fun_url` VALUES ('114', '/system/grantAppRole.do', '55');
INSERT INTO `fun_url` VALUES ('115', '/system/listDepartment.do', '56');
INSERT INTO `fun_url` VALUES ('116', '/system/selectAppUser.do', '56');
INSERT INTO `fun_url` VALUES ('117', '/system/listDepartment.do', '57');
INSERT INTO `fun_url` VALUES ('118', '/system/addDepartment.do', '57');
INSERT INTO `fun_url` VALUES ('119', '/system/selectAppUser.do', '57');
INSERT INTO `fun_url` VALUES ('120', '/system/saveAppUser.do', '57');
INSERT INTO `fun_url` VALUES ('121', '/system/listDepartment.do', '58');
INSERT INTO `fun_url` VALUES ('122', '/system/addDepartment.do', '58');
INSERT INTO `fun_url` VALUES ('123', '/system/detailDepartment.do', '58');
INSERT INTO `fun_url` VALUES ('124', '/system/selectAppUser.do', '58');
INSERT INTO `fun_url` VALUES ('125', '/system/saveAppUser.do', '58');
INSERT INTO `fun_url` VALUES ('126', '/system/listDepartment.do', '59');
INSERT INTO `fun_url` VALUES ('127', '/system/removeDepartment.do', '59');
INSERT INTO `fun_url` VALUES ('128', '/system/selectAppUser.do', '59');
INSERT INTO `fun_url` VALUES ('129', '/system/saveAppUser.do', '59');
INSERT INTO `fun_url` VALUES ('130', '/system/myUserSub.do', '60');
INSERT INTO `fun_url` VALUES ('131', '/system/addMyUserSub.do', '60');
INSERT INTO `fun_url` VALUES ('132', '/system/selectAppUser.do', '60');
INSERT INTO `fun_url` VALUES ('133', '/system/saveAppUser.do', '60');
INSERT INTO `fun_url` VALUES ('134', '/system/listFileAttach.do', '61');
INSERT INTO `fun_url` VALUES ('135', '/system/saveFileAttach.do', '62');
INSERT INTO `fun_url` VALUES ('136', '/system/listFileAttach.do', '62');
INSERT INTO `fun_url` VALUES ('137', '/system/getFileAttach.do', '62');
INSERT INTO `fun_url` VALUES ('138', '/system/listFileAttach.do', '63');
INSERT INTO `fun_url` VALUES ('139', '/system/multiDelFileAttach.do', '63');
INSERT INTO `fun_url` VALUES ('140', '/flow/rootProType.do', '64');
INSERT INTO `fun_url` VALUES ('141', '/flow/listProDefinition.do', '64');
INSERT INTO `fun_url` VALUES ('142', '/flow/processDetail.do', '64');
INSERT INTO `fun_url` VALUES ('143', '/flow/rootProType.do', '65');
INSERT INTO `fun_url` VALUES ('144', '/flow/saveProType.do', '65');
INSERT INTO `fun_url` VALUES ('145', '/flow/removeProType.do', '65');
INSERT INTO `fun_url` VALUES ('146', '/flow/getProType.do', '65');
INSERT INTO `fun_url` VALUES ('147', '/flow/rootProType.do', '66');
INSERT INTO `fun_url` VALUES ('148', '/flow/listProDefinition.do', '66');
INSERT INTO `fun_url` VALUES ('149', '/flow/saveProDefinition.do', '66');
INSERT INTO `fun_url` VALUES ('150', '/flow/listProType.do', '66');
INSERT INTO `fun_url` VALUES ('151', '/flow/getProDefinition.do', '66');
INSERT INTO `fun_url` VALUES ('152', '/flow/rootProType.do', '67');
INSERT INTO `fun_url` VALUES ('153', '/flow/listProDefinition.do', '67');
INSERT INTO `fun_url` VALUES ('154', '/flow/saveProDefinition.do', '67');
INSERT INTO `fun_url` VALUES ('155', '/flow/listProType.do', '67');
INSERT INTO `fun_url` VALUES ('156', '/flow/getProDefinition.do', '67');
INSERT INTO `fun_url` VALUES ('157', '/flow/rootProType.do', '68');
INSERT INTO `fun_url` VALUES ('158', '/flow/listProDefinition.do', '68');
INSERT INTO `fun_url` VALUES ('159', '/flow/multiDelProDefinition.do', '68');
INSERT INTO `fun_url` VALUES ('160', '/flow/processDetail.do', '69');
INSERT INTO `fun_url` VALUES ('161', '/flow/saveProUserAssign.do', '70');
INSERT INTO `fun_url` VALUES ('162', '/flow/listProUserAssign.do', '70');
INSERT INTO `fun_url` VALUES ('163', '/document/saveDocFolder.do', '71');
INSERT INTO `fun_url` VALUES ('164', '/document/getDocFolder.do', '71');
INSERT INTO `fun_url` VALUES ('165', '/document/removeDocFolder.do', '71');
INSERT INTO `fun_url` VALUES ('166', '/document/listDocPrivilege.do', '72');
INSERT INTO `fun_url` VALUES ('167', '/document/listDocPrivilege.do', '73');
INSERT INTO `fun_url` VALUES ('168', '/document/addDocPrivilege.do', '73');
INSERT INTO `fun_url` VALUES ('169', '/document/listDocPrivilege.do', '74');
INSERT INTO `fun_url` VALUES ('170', '/document/changeDocPrivilege.do', '74');
INSERT INTO `fun_url` VALUES ('171', '/document/listDocPrivilege.do', '75');
INSERT INTO `fun_url` VALUES ('172', '/document/multiDelDocPrivilege.do', '75');
INSERT INTO `fun_url` VALUES ('173', '/task/listPlanType.do', '76');
INSERT INTO `fun_url` VALUES ('174', '/task/listPlanType.do', '77');
INSERT INTO `fun_url` VALUES ('175', '/task/savePlanType.do', '77');
INSERT INTO `fun_url` VALUES ('176', '/task/listPlanType.do', '78');
INSERT INTO `fun_url` VALUES ('177', '/task/savePlanType.do', '78');
INSERT INTO `fun_url` VALUES ('178', '/task/getPlanType.do', '78');
INSERT INTO `fun_url` VALUES ('179', '/task/listPlanType.do', '79');
INSERT INTO `fun_url` VALUES ('180', '/task/multiDelPlanType.do', '79');
INSERT INTO `fun_url` VALUES ('181', '/info/categoryNews.do', '81');
INSERT INTO `fun_url` VALUES ('182', '/info/treeNewsType.do', '81');
INSERT INTO `fun_url` VALUES ('183', '/info/categoryNews.do', '82');
INSERT INTO `fun_url` VALUES ('184', '/info/treeNewsType.do', '82');
INSERT INTO `fun_url` VALUES ('185', '/info/saveNews.do', '82');
INSERT INTO `fun_url` VALUES ('186', '/info/categoryNews.do', '83');
INSERT INTO `fun_url` VALUES ('187', '/info/treeNewsType.do', '83');
INSERT INTO `fun_url` VALUES ('188', '/info/saveNews.do', '83');
INSERT INTO `fun_url` VALUES ('189', '/info/categoryNews.do', '84');
INSERT INTO `fun_url` VALUES ('190', '/info/treeNewsType.do', '84');
INSERT INTO `fun_url` VALUES ('191', '/info/multiDelNews.do', '84');
INSERT INTO `fun_url` VALUES ('192', '/info/multiDelNewsComment.do', '86');
INSERT INTO `fun_url` VALUES ('193', '/info/listNewsType.do', '87');
INSERT INTO `fun_url` VALUES ('194', '/info/listNewsType.do', '88');
INSERT INTO `fun_url` VALUES ('195', '/info/addNewsType.do', '88');
INSERT INTO `fun_url` VALUES ('196', '/info/listNewsType.do', '89');
INSERT INTO `fun_url` VALUES ('197', '/info/addNewsType.do', '89');
INSERT INTO `fun_url` VALUES ('198', '/info/detailNewsType.do', '89');
INSERT INTO `fun_url` VALUES ('199', '/info/sortNewsType.do', '89');
INSERT INTO `fun_url` VALUES ('200', '/info/listNewsType.do', '90');
INSERT INTO `fun_url` VALUES ('201', '/info/removeNewsType.do', '90');
INSERT INTO `fun_url` VALUES ('202', '/info/saveNotice.do', '92');
INSERT INTO `fun_url` VALUES ('203', '/info/saveNotice.do', '93');
INSERT INTO `fun_url` VALUES ('204', '/info/multiDelNotice.do', '94');
INSERT INTO `fun_url` VALUES ('205', '/personal/listHolidayRecord.do', '95');
INSERT INTO `fun_url` VALUES ('206', '/personal/listHolidayRecord.do', '96');
INSERT INTO `fun_url` VALUES ('207', '/personal/saveHolidayRecord.do', '96');
INSERT INTO `fun_url` VALUES ('208', '/personal/listHolidayRecord.do', '97');
INSERT INTO `fun_url` VALUES ('209', '/personal/getHolidayRecord.do', '97');
INSERT INTO `fun_url` VALUES ('210', '/personal/saveHolidayRecord.do', '97');
INSERT INTO `fun_url` VALUES ('211', '/personal/listHolidayRecord.do', '98');
INSERT INTO `fun_url` VALUES ('212', '/personal/multiDelHolidayRecord.do', '98');
INSERT INTO `fun_url` VALUES ('213', '/personal/listDutySection.do', '99');
INSERT INTO `fun_url` VALUES ('214', '/personal/listDutySection.do', '100');
INSERT INTO `fun_url` VALUES ('215', '/personal/saveDutySection.do', '100');
INSERT INTO `fun_url` VALUES ('216', '/personal/listDutySection.do', '101');
INSERT INTO `fun_url` VALUES ('217', '/personal/saveDutySection.do', '101');
INSERT INTO `fun_url` VALUES ('218', '/personal/getDutySection.do', '101');
INSERT INTO `fun_url` VALUES ('219', '/personal/listDutySection.do', '102');
INSERT INTO `fun_url` VALUES ('220', '/personal/multiDelDutySection.do', '102');
INSERT INTO `fun_url` VALUES ('221', '/personal/listDutySystem.do', '103');
INSERT INTO `fun_url` VALUES ('222', '/personal/listDutySystem.do', '104');
INSERT INTO `fun_url` VALUES ('223', '/personal/settingDutySystem.do', '104');
INSERT INTO `fun_url` VALUES ('224', '/personal/saveDutySystem.do', '104');
INSERT INTO `fun_url` VALUES ('225', '/personal/listDutySystem.do', '105');
INSERT INTO `fun_url` VALUES ('226', '/personal/getDutySystem.do', '105');
INSERT INTO `fun_url` VALUES ('227', '/personal/saveDutySystem.do', '105');
INSERT INTO `fun_url` VALUES ('228', '/personal/listDutySystem.do', '106');
INSERT INTO `fun_url` VALUES ('229', '/personal/multiDelDutySystem.do', '106');
INSERT INTO `fun_url` VALUES ('230', '/personal/listDuty.do', '107');
INSERT INTO `fun_url` VALUES ('231', '/personal/listDuty.do', '108');
INSERT INTO `fun_url` VALUES ('232', '/personal/saveDuty.do', '108');
INSERT INTO `fun_url` VALUES ('233', '/personal/comboDutySystem.do', '108');
INSERT INTO `fun_url` VALUES ('234', '/personal/listDuty.do', '109');
INSERT INTO `fun_url` VALUES ('235', '/personal/saveDuty.do', '109');
INSERT INTO `fun_url` VALUES ('236', '/personal/comboDutySystem.do', '109');
INSERT INTO `fun_url` VALUES ('237', '/personal/getDuty.do', '109');
INSERT INTO `fun_url` VALUES ('238', '/personal/listDuty.do', '110');
INSERT INTO `fun_url` VALUES ('239', '/personal/multiDelDuty.do', '110');
INSERT INTO `fun_url` VALUES ('240', '/personal/listDutyRegister.do', '111');
INSERT INTO `fun_url` VALUES ('241', '/personal/listDutyRegister.do', '112');
INSERT INTO `fun_url` VALUES ('242', '/personal/saveDutyRegister.do', '112');
INSERT INTO `fun_url` VALUES ('243', '/personal/comboDutySection.do', '112');
INSERT INTO `fun_url` VALUES ('244', '/personal/listDutyRegister.do', '113');
INSERT INTO `fun_url` VALUES ('245', '/personal/multiDelDutyRegister.do', '113');
INSERT INTO `fun_url` VALUES ('246', '/admin/tempConference.do', '114');
INSERT INTO `fun_url` VALUES ('247', '/admin/sendConference.do', '114');
INSERT INTO `fun_url` VALUES ('248', '/admin/daiApplyConference.do', '115');
INSERT INTO `fun_url` VALUES ('249', '/admin/confApplyConference.do', '116');
INSERT INTO `fun_url` VALUES ('250', '/admin/listBoardRoo.do', '117');
INSERT INTO `fun_url` VALUES ('251', '/admin/saveBoardRoo.do', '117');
INSERT INTO `fun_url` VALUES ('252', '/admin/multiDelBoardRoo.do', '117');
INSERT INTO `fun_url` VALUES ('253', '/admin/getBoardRoo.do', '117');
INSERT INTO `fun_url` VALUES ('254', '/admin/listBoardType.do', '118');
INSERT INTO `fun_url` VALUES ('255', '/admin/saveBoardType.do', '118');
INSERT INTO `fun_url` VALUES ('256', '/admin/multiDelBoardType.do', '118');
INSERT INTO `fun_url` VALUES ('257', '/admin/getBoardType.do', '118');
INSERT INTO `fun_url` VALUES ('258', '/admin/listConfSummary.do', '119');
INSERT INTO `fun_url` VALUES ('259', '/admin/multiConfSummary.do', '119');
INSERT INTO `fun_url` VALUES ('260', '/admin/saveConfSummary.do', '120');
INSERT INTO `fun_url` VALUES ('261', '/admin/sendConfSummary.do', '120');
INSERT INTO `fun_url` VALUES ('262', '/admin/getTitleConfSummary.do', '120');
INSERT INTO `fun_url` VALUES ('263', '/admin/listOfficeGoods.do', '121');
INSERT INTO `fun_url` VALUES ('264', '/admin/treeOfficeGoodsType.do', '121');
INSERT INTO `fun_url` VALUES ('265', '/admin/listOfficeGoods.do', '122');
INSERT INTO `fun_url` VALUES ('266', '/admin/treeOfficeGoodsType.do', '122');
INSERT INTO `fun_url` VALUES ('267', '/admin/multiDelOfficeGoodsType.do', '122');
INSERT INTO `fun_url` VALUES ('268', '/admin/saveOfficeGoodsType.do', '122');
INSERT INTO `fun_url` VALUES ('269', '/admin/getOfficeGoodsType.do', '122');
INSERT INTO `fun_url` VALUES ('270', '/admin/listOfficeGoods.do', '123');
INSERT INTO `fun_url` VALUES ('271', '/admin/saveOfficeGoods.do', '123');
INSERT INTO `fun_url` VALUES ('272', '/admin/treeOfficeGoodsType.do', '123');
INSERT INTO `fun_url` VALUES ('273', '/admin/listOfficeGoods.do', '124');
INSERT INTO `fun_url` VALUES ('274', '/admin/saveOfficeGoods.do', '124');
INSERT INTO `fun_url` VALUES ('275', '/admin/treeOfficeGoodsType.do', '124');
INSERT INTO `fun_url` VALUES ('276', '/admin/getOfficeGoods.do', '124');
INSERT INTO `fun_url` VALUES ('277', '/admin/listOfficeGoods.do', '125');
INSERT INTO `fun_url` VALUES ('278', '/admin/multiDelOfficeGoods.do', '125');
INSERT INTO `fun_url` VALUES ('279', '/admin/listInStock.do', '126');
INSERT INTO `fun_url` VALUES ('280', '/admin/listInStock.do', '127');
INSERT INTO `fun_url` VALUES ('281', '/admin/listOfficeGoods.do', '127');
INSERT INTO `fun_url` VALUES ('282', '/admin/saveInStock.do', '127');
INSERT INTO `fun_url` VALUES ('283', '/admin/treeOfficeGoodsType.do', '127');
INSERT INTO `fun_url` VALUES ('284', '/admin/listInStock.do', '128');
INSERT INTO `fun_url` VALUES ('285', '/admin/listOfficeGoods.do', '128');
INSERT INTO `fun_url` VALUES ('286', '/admin/saveInStock.do', '128');
INSERT INTO `fun_url` VALUES ('287', '/admin/treeOfficeGoodsType.do', '128');
INSERT INTO `fun_url` VALUES ('288', '/admin/getInStock.do', '128');
INSERT INTO `fun_url` VALUES ('289', '/admin/listInStock.do', '129');
INSERT INTO `fun_url` VALUES ('290', '/admin/multiDelInStock.do', '129');
INSERT INTO `fun_url` VALUES ('291', '/admin/listGoodsApply.do', '130');
INSERT INTO `fun_url` VALUES ('292', '/admin/listGoodsApply.do', '131');
INSERT INTO `fun_url` VALUES ('293', '/admin/saveGoodsApply.do', '131');
INSERT INTO `fun_url` VALUES ('294', '/admin/listOfficeGoods.do', '131');
INSERT INTO `fun_url` VALUES ('295', '/admin/treeOfficeGoodsType.do', '131');
INSERT INTO `fun_url` VALUES ('296', '/admin/listGoodsApply.do', '132');
INSERT INTO `fun_url` VALUES ('297', '/admin/saveGoodsApply.do', '132');
INSERT INTO `fun_url` VALUES ('298', '/admin/listOfficeGoods.do', '132');
INSERT INTO `fun_url` VALUES ('299', '/admin/treeOfficeGoodsType.do', '132');
INSERT INTO `fun_url` VALUES ('300', '/admin/getGoodsApply.do', '132');
INSERT INTO `fun_url` VALUES ('301', '/admin/listGoodsApply.do', '133');
INSERT INTO `fun_url` VALUES ('302', '/admin/multiDelGoodsApply.do', '133');
INSERT INTO `fun_url` VALUES ('303', '/admin/checkGoodsApply.do', '134');
INSERT INTO `fun_url` VALUES ('304', '/admin/getGoodsApply.do', '134');
INSERT INTO `fun_url` VALUES ('305', '/admin/listCar.do', '135');
INSERT INTO `fun_url` VALUES ('306', '/admin/listCar.do', '136');
INSERT INTO `fun_url` VALUES ('307', '/admin/saveCar.do', '136');
INSERT INTO `fun_url` VALUES ('308', '/admin/delphotoCar.do', '136');
INSERT INTO `fun_url` VALUES ('309', '/admin/listCar.do', '137');
INSERT INTO `fun_url` VALUES ('310', '/admin/saveCar.do', '137');
INSERT INTO `fun_url` VALUES ('311', '/admin/getCar.do', '137');
INSERT INTO `fun_url` VALUES ('312', '/admin/delphotoCar.do', '137');
INSERT INTO `fun_url` VALUES ('313', '/admin/listCar.do', '138');
INSERT INTO `fun_url` VALUES ('314', '/admin/multiDelCar.do', '138');
INSERT INTO `fun_url` VALUES ('315', '/admin/listCartRepair.do', '139');
INSERT INTO `fun_url` VALUES ('316', '/admin/listCartRepair.do', '140');
INSERT INTO `fun_url` VALUES ('317', '/admin/saveCartRepair.do', '140');
INSERT INTO `fun_url` VALUES ('318', '/admin/listCartRepair.do', '141');
INSERT INTO `fun_url` VALUES ('319', '/admin/saveCartRepair.do', '141');
INSERT INTO `fun_url` VALUES ('320', '/admin/getCartRepair.do', '141');
INSERT INTO `fun_url` VALUES ('321', '/admin/listCartRepair.do', '142');
INSERT INTO `fun_url` VALUES ('322', '/admin/multiDelCartRepair.do', '142');
INSERT INTO `fun_url` VALUES ('323', '/admin/listCarApply.do', '143');
INSERT INTO `fun_url` VALUES ('324', '/admin/listCarApply.do', '144');
INSERT INTO `fun_url` VALUES ('325', '/admin/saveCarApply.do', '144');
INSERT INTO `fun_url` VALUES ('326', '/admin/listCarApply.do', '145');
INSERT INTO `fun_url` VALUES ('327', '/admin/saveCarApply.do', '145');
INSERT INTO `fun_url` VALUES ('328', '/admin/getCarApply.do', '145');
INSERT INTO `fun_url` VALUES ('329', '/admin/listCarApply.do', '146');
INSERT INTO `fun_url` VALUES ('330', '/admin/multiDelCarApply.do', '146');
INSERT INTO `fun_url` VALUES ('331', '/admin/checkCarApply.do', '147');
INSERT INTO `fun_url` VALUES ('332', '/admin/getCarApply.do', '147');
INSERT INTO `fun_url` VALUES ('333', '/admin/listDepreType.do', '148');
INSERT INTO `fun_url` VALUES ('334', '/admin/listDepreType.do', '149');
INSERT INTO `fun_url` VALUES ('335', '/admin/saveDepreType.do', '149');
INSERT INTO `fun_url` VALUES ('336', '/admin/listDepreType.do', '150');
INSERT INTO `fun_url` VALUES ('337', '/admin/saveDepreType.do', '150');
INSERT INTO `fun_url` VALUES ('338', '/admin/getDepreType.do', '150');
INSERT INTO `fun_url` VALUES ('339', '/admin/listDepreType.do', '151');
INSERT INTO `fun_url` VALUES ('340', '/admin/multiDelDepreType.do', '151');
INSERT INTO `fun_url` VALUES ('341', '/admin/listFixedAssets.do', '152');
INSERT INTO `fun_url` VALUES ('342', '/admin/treeAssetsType.do', '152');
INSERT INTO `fun_url` VALUES ('343', '/admin/treeAssetsType.do', '153');
INSERT INTO `fun_url` VALUES ('344', '/admin/multiDelAssetsType.do', '153');
INSERT INTO `fun_url` VALUES ('345', '/admin/saveAssetsType.do', '153');
INSERT INTO `fun_url` VALUES ('346', '/admin/getAssetsType.do', '153');
INSERT INTO `fun_url` VALUES ('347', '/admin/listFixedAssets.do', '154');
INSERT INTO `fun_url` VALUES ('348', '/system/selectDepartment.do', '154');
INSERT INTO `fun_url` VALUES ('349', '/admin/treeAssetsType.do', '154');
INSERT INTO `fun_url` VALUES ('350', '/admin/saveFixedAssets.do', '154');
INSERT INTO `fun_url` VALUES ('351', '/admin/comboxAssetsType.do', '154');
INSERT INTO `fun_url` VALUES ('352', '/admin/comboxDepreType.do', '154');
INSERT INTO `fun_url` VALUES ('353', '/admin/listFixedAssets.do', '155');
INSERT INTO `fun_url` VALUES ('354', '/admin/treeAssetsType.do', '155');
INSERT INTO `fun_url` VALUES ('355', '/system/selectDepartment.do', '155');
INSERT INTO `fun_url` VALUES ('356', '/admin/saveFixedAssets.do', '155');
INSERT INTO `fun_url` VALUES ('357', '/admin/comboxAssetsType.do', '155');
INSERT INTO `fun_url` VALUES ('358', '/admin/comboxDepreType.do', '155');
INSERT INTO `fun_url` VALUES ('359', '/admin/getFixedAssets.do', '155');
INSERT INTO `fun_url` VALUES ('360', '/admin/listFixedAssets.do', '156');
INSERT INTO `fun_url` VALUES ('361', '/admin/treeAssetsType.do', '156');
INSERT INTO `fun_url` VALUES ('362', '/admin/multiDelFixedAssets.do', '156');
INSERT INTO `fun_url` VALUES ('363', '/admin/depreciateDepreRecord.do', '157');
INSERT INTO `fun_url` VALUES ('364', '/admin/workDepreRecord.do', '157');
INSERT INTO `fun_url` VALUES ('365', '/admin/listDepreRecord.do', '158');
INSERT INTO `fun_url` VALUES ('366', '/admin/listBookType.do', '159');
INSERT INTO `fun_url` VALUES ('367', '/admin/listBookType.do', '160');
INSERT INTO `fun_url` VALUES ('368', '/admin/saveBookType.do', '160');
INSERT INTO `fun_url` VALUES ('369', '/admin/listBookType.do', '161');
INSERT INTO `fun_url` VALUES ('370', '/admin/saveBookType.do', '161');
INSERT INTO `fun_url` VALUES ('371', '/admin/getBookType.do', '161');
INSERT INTO `fun_url` VALUES ('372', '/admin/listBookType.do', '162');
INSERT INTO `fun_url` VALUES ('373', '/admin/multiDelBookType.do', '162');
INSERT INTO `fun_url` VALUES ('374', '/admin/listBook.do', '163');
INSERT INTO `fun_url` VALUES ('375', '/admin/treeBookType.do', '163');
INSERT INTO `fun_url` VALUES ('376', '/admin/listBook.do', '164');
INSERT INTO `fun_url` VALUES ('377', '/admin/treeBookType.do', '164');
INSERT INTO `fun_url` VALUES ('378', '/admin/saveBook.do', '164');
INSERT INTO `fun_url` VALUES ('379', '/admin/treeBook.do', '164');
INSERT INTO `fun_url` VALUES ('380', '/admin/listBookType.do', '165');
INSERT INTO `fun_url` VALUES ('381', '/admin/treeBookType.do', '165');
INSERT INTO `fun_url` VALUES ('382', '/admin/saveBookType.do', '165');
INSERT INTO `fun_url` VALUES ('383', '/admin/treeBook.do', '165');
INSERT INTO `fun_url` VALUES ('384', '/admin/getBook.do', '165');
INSERT INTO `fun_url` VALUES ('385', '/admin/listBook.do', '166');
INSERT INTO `fun_url` VALUES ('386', '/admin/treeBookType.do', '166');
INSERT INTO `fun_url` VALUES ('387', '/admin/multiDelBook.do', '166');
INSERT INTO `fun_url` VALUES ('388', '/admin/listBorrowBookBorRet.do', '167');
INSERT INTO `fun_url` VALUES ('389', '/admin/listBorrowBookBorRet.do', '168');
INSERT INTO `fun_url` VALUES ('390', '/admin/saveBorrowBookBorRet.do', '168');
INSERT INTO `fun_url` VALUES ('391', '/admin/listBook.do', '168');
INSERT INTO `fun_url` VALUES ('392', '/admin/treeBookType.do', '168');
INSERT INTO `fun_url` VALUES ('393', '/admin/getBook.do', '168');
INSERT INTO `fun_url` VALUES ('394', '/admin/getBorrowSnBookSn.do', '168');
INSERT INTO `fun_url` VALUES ('395', '/admin/listBorrowBookBorRet.do', '169');
INSERT INTO `fun_url` VALUES ('396', '/admin/saveBorrowBookBorRet.do', '169');
INSERT INTO `fun_url` VALUES ('397', '/admin/listBook.do', '169');
INSERT INTO `fun_url` VALUES ('398', '/admin/treeBookType.do', '169');
INSERT INTO `fun_url` VALUES ('399', '/admin/getBook.do', '169');
INSERT INTO `fun_url` VALUES ('400', '/admin/getBorrowSnBookSn.do', '169');
INSERT INTO `fun_url` VALUES ('401', '/admin/getBookBorRet.do', '169');
INSERT INTO `fun_url` VALUES ('402', '/admin/listBook.do', '170');
INSERT INTO `fun_url` VALUES ('403', '/admin/treeBookType.do', '170');
INSERT INTO `fun_url` VALUES ('404', '/admin/getReturnSnBookSn.do', '170');
INSERT INTO `fun_url` VALUES ('405', '/admin/getBorRetTimeBookBorRet.do', '170');
INSERT INTO `fun_url` VALUES ('406', '/admin/listReturnBookBorRet.do', '170');
INSERT INTO `fun_url` VALUES ('407', '/admin/saveReturnBookBorRet.do', '170');
INSERT INTO `fun_url` VALUES ('408', '/admin/getBookBorRet.do', '170');
INSERT INTO `fun_url` VALUES ('409', '/admin/listBorrowBookBorRet.do', '171');
INSERT INTO `fun_url` VALUES ('410', '/admin/multiDelBookBorRet.do', '171');
INSERT INTO `fun_url` VALUES ('411', '/admin/listReturnBookBorRet.do', '172');
INSERT INTO `fun_url` VALUES ('412', '/admin/listBook.do', '173');
INSERT INTO `fun_url` VALUES ('413', '/admin/treeBookType.do', '173');
INSERT INTO `fun_url` VALUES ('414', '/admin/getReturnSnBookSn.do', '173');
INSERT INTO `fun_url` VALUES ('415', '/admin/getBorRetTimeBookBorRet.do', '173');
INSERT INTO `fun_url` VALUES ('416', '/admin/listReturnBookBorRet.do', '173');
INSERT INTO `fun_url` VALUES ('417', '/admin/saveReturnBookBorRet.do', '173');
INSERT INTO `fun_url` VALUES ('418', '/admin/listBook.do', '174');
INSERT INTO `fun_url` VALUES ('419', '/admin/treeBookType.do', '174');
INSERT INTO `fun_url` VALUES ('420', '/admin/getReturnSnBookSn.do', '174');
INSERT INTO `fun_url` VALUES ('421', '/admin/getBorRetTimeBookBorRet.do', '174');
INSERT INTO `fun_url` VALUES ('422', '/admin/listReturnBookBorRet.do', '174');
INSERT INTO `fun_url` VALUES ('423', '/admin/saveReturnBookBorRet.do', '174');
INSERT INTO `fun_url` VALUES ('424', '/admin/getBookBorRet.do', '174');
INSERT INTO `fun_url` VALUES ('425', '/admin/listReturnBookBorRet.do', '175');
INSERT INTO `fun_url` VALUES ('426', '/admin/multiDelBookBorRet.do', '175');
INSERT INTO `fun_url` VALUES ('427', '/hrm/listJob.do', '176');
INSERT INTO `fun_url` VALUES ('428', '/hrm/listJob.do', '177');
INSERT INTO `fun_url` VALUES ('429', '/hrm/saveJob.do', '177');
INSERT INTO `fun_url` VALUES ('430', '/hrm/listJob.do', '178');
INSERT INTO `fun_url` VALUES ('431', '/hrm/saveJob.do', '178');
INSERT INTO `fun_url` VALUES ('432', '/hrm/getJob.do', '178');
INSERT INTO `fun_url` VALUES ('433', '/hrm/listJob.do', '179');
INSERT INTO `fun_url` VALUES ('434', '/hrm/multiDelJob.do', '179');
INSERT INTO `fun_url` VALUES ('435', '/hrm/listJob.do', '180');
INSERT INTO `fun_url` VALUES ('436', '/hrm/recoveryJob.do', '180');
INSERT INTO `fun_url` VALUES ('437', '/hrm/saveEmpProfile.do', '181');
INSERT INTO `fun_url` VALUES ('438', '/hrm/numberEmpProfile.do', '181');
INSERT INTO `fun_url` VALUES ('439', '/hrm/comboJob.do', '181');
INSERT INTO `fun_url` VALUES ('440', '/hrm/comboStandSalary.do', '181');
INSERT INTO `fun_url` VALUES ('441', '/hrm/listEmpProfile.do', '182');
INSERT INTO `fun_url` VALUES ('442', '/hrm/listEmpProfile.do', '183');
INSERT INTO `fun_url` VALUES ('443', '/hrm/saveEmpProfile.do', '183');
INSERT INTO `fun_url` VALUES ('444', '/hrm/delphotoEmpProfile.do', '183');
INSERT INTO `fun_url` VALUES ('445', '/hrm/numberEmpProfile.do', '183');
INSERT INTO `fun_url` VALUES ('446', '/hrm/comboJob.do', '183');
INSERT INTO `fun_url` VALUES ('447', '/hrm/comboStandSalary.do', '183');
INSERT INTO `fun_url` VALUES ('448', '/hrm/listEmpProfile.do', '184');
INSERT INTO `fun_url` VALUES ('449', '/hrm/saveEmpProfile.do', '184');
INSERT INTO `fun_url` VALUES ('450', '/hrm/numberEmpProfile.do', '184');
INSERT INTO `fun_url` VALUES ('451', '/hrm/delphotoEmpProfile.do', '184');
INSERT INTO `fun_url` VALUES ('452', '/hrm/comboJob.do', '184');
INSERT INTO `fun_url` VALUES ('453', '/hrm/comboStandSalary.do', '184');
INSERT INTO `fun_url` VALUES ('454', '/hrm/getEmpProfile.do', '184');
INSERT INTO `fun_url` VALUES ('455', '/hrm/listEmpProfile.do', '185');
INSERT INTO `fun_url` VALUES ('456', '/hrm/multiDelEmpProfile.do', '185');
INSERT INTO `fun_url` VALUES ('457', '/hrm/listEmpProfile.do', '186');
INSERT INTO `fun_url` VALUES ('458', '/hrm/checkEmpProfile.do', '186');
INSERT INTO `fun_url` VALUES ('459', '/hrm/listEmpProfile.do', '187');
INSERT INTO `fun_url` VALUES ('460', '/hrm/recoveryEmpProfile.do', '187');
INSERT INTO `fun_url` VALUES ('461', '/hrm/listSalaryItem.do', '188');
INSERT INTO `fun_url` VALUES ('462', '/hrm/listSalaryItem.do', '189');
INSERT INTO `fun_url` VALUES ('463', '/hrm/saveSalaryItem.do', '189');
INSERT INTO `fun_url` VALUES ('464', '/hrm/listSalaryItem.do', '190');
INSERT INTO `fun_url` VALUES ('465', '/hrm/saveSalaryItem.do', '190');
INSERT INTO `fun_url` VALUES ('466', '/hrm/getSalaryItem.do', '190');
INSERT INTO `fun_url` VALUES ('467', '/hrm/listSalaryItem.do', '191');
INSERT INTO `fun_url` VALUES ('468', '/hrm/multiDelSalaryItem.do', '191');
INSERT INTO `fun_url` VALUES ('469', '/hrm/listSalaryItem.do', '192');
INSERT INTO `fun_url` VALUES ('470', '/hrm/saveStandSalary.do', '192');
INSERT INTO `fun_url` VALUES ('471', '/hrm/numberStandSalary.do', '192');
INSERT INTO `fun_url` VALUES ('472', '/hrm/listStandSalaryItem.do', '192');
INSERT INTO `fun_url` VALUES ('473', '/hrm/listStandSalary.do', '193');
INSERT INTO `fun_url` VALUES ('474', '/hrm/listSalaryItem.do', '194');
INSERT INTO `fun_url` VALUES ('475', '/hrm/listStandSalary.do', '194');
INSERT INTO `fun_url` VALUES ('476', '/hrm/saveStandSalary.do', '194');
INSERT INTO `fun_url` VALUES ('477', '/hrm/numberStandSalary.do', '194');
INSERT INTO `fun_url` VALUES ('478', '/hrm/listStandSalaryItem.do', '194');
INSERT INTO `fun_url` VALUES ('479', '/hrm/listStandSalary.do', '195');
INSERT INTO `fun_url` VALUES ('480', '/hrm/listSalaryItem.do', '195');
INSERT INTO `fun_url` VALUES ('481', '/hrm/saveStandSalary.do', '195');
INSERT INTO `fun_url` VALUES ('482', '/hrm/getStandSalary.do', '195');
INSERT INTO `fun_url` VALUES ('483', '/hrm/listStandSalaryItem.do', '195');
INSERT INTO `fun_url` VALUES ('484', '/hrm/listStandSalary.do', '196');
INSERT INTO `fun_url` VALUES ('485', '/hrm/multiDelStandSalary.do', '196');
INSERT INTO `fun_url` VALUES ('486', '/hrm/listStandSalaryItem.do', '197');
INSERT INTO `fun_url` VALUES ('487', '/hrm/getStandSalary.do', '197');
INSERT INTO `fun_url` VALUES ('488', '/hrm/listStandSalary.do', '197');
INSERT INTO `fun_url` VALUES ('489', '/hrm/checkStandSalary.do', '197');
INSERT INTO `fun_url` VALUES ('490', '/hrm/listEmpProfile.do', '198');
INSERT INTO `fun_url` VALUES ('491', '/hrm/saveSalaryPayoff.do', '198');
INSERT INTO `fun_url` VALUES ('492', '/hrm/listSalaryPayoff.do', '199');
INSERT INTO `fun_url` VALUES ('493', '/hrm/listSalaryPayoff.do', '200');
INSERT INTO `fun_url` VALUES ('494', '/hrm/listEmpProfile.do', '200');
INSERT INTO `fun_url` VALUES ('495', '/hrm/saveSalaryPayoff.do', '200');
INSERT INTO `fun_url` VALUES ('496', '/hrm/listSalaryPayoff.do', '201');
INSERT INTO `fun_url` VALUES ('497', '/hrm/listEmpProfile.do', '201');
INSERT INTO `fun_url` VALUES ('498', '/hrm/saveSalaryPayoff.do', '201');
INSERT INTO `fun_url` VALUES ('499', '/hrm/getSalaryPayoff.do', '201');
INSERT INTO `fun_url` VALUES ('500', '/hrm/listSalaryPayoff.do', '202');
INSERT INTO `fun_url` VALUES ('501', '/hrm/multiDelSalaryPayoff.do', '202');
INSERT INTO `fun_url` VALUES ('502', '/hrm/checkSalaryPayoff.do', '203');
INSERT INTO `fun_url` VALUES ('503', '/hrm/listSalaryPayoff.do', '203');
INSERT INTO `fun_url` VALUES ('504', '/hrm/saveJobChange.do', '204');
INSERT INTO `fun_url` VALUES ('505', '/hrm/listEmpProfile.do', '204');
INSERT INTO `fun_url` VALUES ('506', '/hrm/comboJob.do', '204');
INSERT INTO `fun_url` VALUES ('507', '/hrm/comboStandSalary.do', '204');
INSERT INTO `fun_url` VALUES ('508', '/hrm/listJobChange.do', '205');
INSERT INTO `fun_url` VALUES ('509', '/hrm/saveJobChange.do', '206');
INSERT INTO `fun_url` VALUES ('510', '/hrm/listJobChange.do', '206');
INSERT INTO `fun_url` VALUES ('511', '/hrm/listEmpProfile.do', '206');
INSERT INTO `fun_url` VALUES ('512', '/hrm/comboJob.do', '206');
INSERT INTO `fun_url` VALUES ('513', '/hrm/comboStandSalary.do', '206');
INSERT INTO `fun_url` VALUES ('514', '/hrm/saveJobChange.do', '207');
INSERT INTO `fun_url` VALUES ('515', '/hrm/listJobChange.do', '207');
INSERT INTO `fun_url` VALUES ('516', '/hrm/listEmpProfile.do', '207');
INSERT INTO `fun_url` VALUES ('517', '/hrm/getJobChange.do', '207');
INSERT INTO `fun_url` VALUES ('518', '/hrm/comboJob.do', '207');
INSERT INTO `fun_url` VALUES ('519', '/hrm/comboStandSalary.do', '207');
INSERT INTO `fun_url` VALUES ('520', '/hrm/listJobChange.do', '208');
INSERT INTO `fun_url` VALUES ('521', '/hrm/multiDelJobChange.do', '208');
INSERT INTO `fun_url` VALUES ('522', '/hrm/loadJobChange.do', '209');
INSERT INTO `fun_url` VALUES ('523', '/hrm/checkJobChange.do', '209');
INSERT INTO `fun_url` VALUES ('524', '/hrm/listJobChange.do', '209');
INSERT INTO `fun_url` VALUES ('525', '/hrm/listHireIssue.do', '210');
INSERT INTO `fun_url` VALUES ('526', '/hrm/loadHireIssue.do', '210');
INSERT INTO `fun_url` VALUES ('527', '/hrm/listHireIssue.do', '211');
INSERT INTO `fun_url` VALUES ('528', '/hrm/saveHireIssue.do', '211');
INSERT INTO `fun_url` VALUES ('529', '/hrm/loadHireIssue.do', '211');
INSERT INTO `fun_url` VALUES ('530', '/hrm/listHireIssue.do', '212');
INSERT INTO `fun_url` VALUES ('531', '/hrm/saveHireIssue.do', '212');
INSERT INTO `fun_url` VALUES ('532', '/hrm/loadHireIssue.do', '212');
INSERT INTO `fun_url` VALUES ('533', '/hrm/getHireIssue.do', '212');
INSERT INTO `fun_url` VALUES ('534', '/hrm/multiDelHireIssue.do', '213');
INSERT INTO `fun_url` VALUES ('535', '/hrm/loadHireIssue.do', '213');
INSERT INTO `fun_url` VALUES ('536', '/hrm/listHireIssue.do', '213');
INSERT INTO `fun_url` VALUES ('537', '/hrm/multiDelHireIssue.do', '214');
INSERT INTO `fun_url` VALUES ('538', '/hrm/checkHireIssue.do', '214');
INSERT INTO `fun_url` VALUES ('539', '/hrm/loadHireIssue.do', '214');
INSERT INTO `fun_url` VALUES ('540', '/hrm/listResume.do', '215');
INSERT INTO `fun_url` VALUES ('541', '/hrm/listResume.do', '216');
INSERT INTO `fun_url` VALUES ('542', '/hrm/saveResume.do', '216');
INSERT INTO `fun_url` VALUES ('543', '/hrm/delphotoResume.do', '216');
INSERT INTO `fun_url` VALUES ('544', '/hrm/getResume.do', '217');
INSERT INTO `fun_url` VALUES ('545', '/hrm/listResume.do', '217');
INSERT INTO `fun_url` VALUES ('546', '/hrm/saveResume.do', '217');
INSERT INTO `fun_url` VALUES ('547', '/hrm/delphotoResume.do', '217');
INSERT INTO `fun_url` VALUES ('548', '/hrm/multiDelResume.do', '218');
INSERT INTO `fun_url` VALUES ('549', '/hrm/listResume.do', '218');

-- ----------------------------
-- Table structure for `global_type`
-- ----------------------------
DROP TABLE IF EXISTS `global_type`;
CREATE TABLE `global_type` (
  `proTypeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `catKey` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `depth` int(11) NOT NULL,
  `nodeKey` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `path` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` int(11) NOT NULL,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`proTypeId`),
  UNIQUE KEY `proTypeId` (`proTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of global_type
-- ----------------------------
INSERT INTO `global_type` VALUES ('1', 'DIC', '1', 'arch', '0', '0.1', '0', '档案系统设置');
INSERT INTO `global_type` VALUES ('2', 'DIC', '2', 'arch', '1', '0.1.1', '0', '案卷管理设置');
INSERT INTO `global_type` VALUES ('3', 'DIC', '3', 'arch', '2', '0.1.1.1', '0', '案卷保管期限');
INSERT INTO `global_type` VALUES ('4', 'DIC', '3', 'arch', '2', '0.1.1.2', '0', '案卷开放形式');
INSERT INTO `global_type` VALUES ('5', 'DIC', '2', 'arch', '1', '0.1.2', '0', '文件管理设置');
INSERT INTO `global_type` VALUES ('6', 'DIC', '3', 'arch', '5', '0.1.2.1', '0', '文件保管期限');
INSERT INTO `global_type` VALUES ('7', 'DIC', '3', 'arch', '5', '0.1.2.2', '0', '文件开放形式');
INSERT INTO `global_type` VALUES ('8', 'DIC', '3', 'arch', '5', '0.1.2.3', '0', '文件密级');
INSERT INTO `global_type` VALUES ('9', 'DIC', '2', 'arch', '1', '0.1.3', '0', '全宗管理设置');
INSERT INTO `global_type` VALUES ('10', 'DIC', '3', 'arch', '9', '0.1.3.1', '0', '全宗开放形式');
INSERT INTO `global_type` VALUES ('11', 'DIC', '2', 'arch', '1', '0.1.4', '0', '借阅设置');
INSERT INTO `global_type` VALUES ('12', 'DIC', '3', 'arch', '11', '0.1.4.1', '0', '借阅方式');
INSERT INTO `global_type` VALUES ('13', 'DIC', '3', 'arch', '11', '0.1.4.2', '0', '借阅目的');
INSERT INTO `global_type` VALUES ('14', 'AR_FD', '1', '001', '0', '0.14.', '1', '全宗分类');
INSERT INTO `global_type` VALUES ('16', 'AR_RL', '1', '001', '0', '0.16.', '1', '案卷');
INSERT INTO `global_type` VALUES ('17', 'AR_RLF', '1', 'txt', '0', '0.17.', '1', '文本文件');

-- ----------------------------
-- Table structure for `goods_apply`
-- ----------------------------
DROP TABLE IF EXISTS `goods_apply`;
CREATE TABLE `goods_apply` (
  `applyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `applyDate` date NOT NULL,
  `applyNo` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `approvalStatus` smallint(6) NOT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `proposer` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `useCounts` int(11) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `goodsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`applyId`),
  UNIQUE KEY `applyId` (`applyId`),
  KEY `FK1C9225454339E596` (`goodsId`),
  KEY `FK1C92254557F9EF0C` (`goodsId`),
  CONSTRAINT `FK1C92254557F9EF0C` FOREIGN KEY (`goodsId`) REFERENCES `office_goods` (`goodsId`),
  CONSTRAINT `FK1C9225454339E596` FOREIGN KEY (`goodsId`) REFERENCES `office_goods` (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of goods_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `hire_issue`
-- ----------------------------
DROP TABLE IF EXISTS `hire_issue`;
CREATE TABLE `hire_issue` (
  `hireId` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkDate` datetime DEFAULT NULL,
  `checkFullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkOpinion` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `endDate` datetime NOT NULL,
  `hireCount` int(11) NOT NULL,
  `jobCondition` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `jobName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `modifyFullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regDate` datetime NOT NULL,
  `regFullname` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `startDate` datetime NOT NULL,
  `status` smallint(6) NOT NULL,
  `title` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`hireId`),
  UNIQUE KEY `hireId` (`hireId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of hire_issue
-- ----------------------------

-- ----------------------------
-- Table structure for `holiday_record`
-- ----------------------------
DROP TABLE IF EXISTS `holiday_record`;
CREATE TABLE `holiday_record` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `descp` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `endTime` datetime NOT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isAll` smallint(6) NOT NULL,
  `startTime` datetime NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId` (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of holiday_record
-- ----------------------------

-- ----------------------------
-- Table structure for `index_display`
-- ----------------------------
DROP TABLE IF EXISTS `index_display`;
CREATE TABLE `index_display` (
  `indexId` bigint(20) NOT NULL AUTO_INCREMENT,
  `colNum` int(11) NOT NULL,
  `portalId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `rowsNum` int(11) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`indexId`),
  UNIQUE KEY `indexId` (`indexId`),
  KEY `FK1CAAF65550662A29` (`userId`),
  KEY `FK1CAAF6558D18473` (`userId`),
  CONSTRAINT `FK1CAAF6558D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK1CAAF65550662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of index_display
-- ----------------------------

-- ----------------------------
-- Table structure for `in_message`
-- ----------------------------
DROP TABLE IF EXISTS `in_message`;
CREATE TABLE `in_message` (
  `receiveId` bigint(20) NOT NULL AUTO_INCREMENT,
  `delFlag` smallint(6) NOT NULL,
  `readFlag` smallint(6) NOT NULL,
  `userFullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `messageId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`receiveId`),
  UNIQUE KEY `receiveId` (`receiveId`),
  KEY `FKAD01BEED899EB997` (`messageId`),
  KEY `FKAD01BEED9E5EC30D` (`messageId`),
  CONSTRAINT `FKAD01BEED9E5EC30D` FOREIGN KEY (`messageId`) REFERENCES `short_message` (`messageId`),
  CONSTRAINT `FKAD01BEED899EB997` FOREIGN KEY (`messageId`) REFERENCES `short_message` (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of in_message
-- ----------------------------

-- ----------------------------
-- Table structure for `in_stock`
-- ----------------------------
DROP TABLE IF EXISTS `in_stock`;
CREATE TABLE `in_stock` (
  `buyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(18,0) NOT NULL,
  `buyer` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inCounts` int(11) DEFAULT NULL,
  `inDate` datetime NOT NULL,
  `price` decimal(18,0) DEFAULT NULL,
  `providerName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `stockNo` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `goodsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`buyId`),
  UNIQUE KEY `buyId` (`buyId`),
  KEY `FKFEF0377C4339E596` (`goodsId`),
  KEY `FKFEF0377C57F9EF0C` (`goodsId`),
  CONSTRAINT `FKFEF0377C57F9EF0C` FOREIGN KEY (`goodsId`) REFERENCES `office_goods` (`goodsId`),
  CONSTRAINT `FKFEF0377C4339E596` FOREIGN KEY (`goodsId`) REFERENCES `office_goods` (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of in_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_deployment`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_deployment`;
CREATE TABLE `jbpm4_deployment` (
  `DBID_` bigint(20) NOT NULL,
  `NAME_` text COLLATE utf8_unicode_ci,
  `TIMESTAMP_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_deployment
-- ----------------------------


-- ----------------------------
-- Table structure for `jbpm4_deployprop`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_deployprop`;
CREATE TABLE `jbpm4_deployprop` (
  `DBID_` bigint(20) NOT NULL,
  `DEPLOYMENT_` bigint(20) DEFAULT NULL,
  `OBJNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STRINGVAL_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LONGVAL_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  CONSTRAINT `FK_DEPLPROP_DEPL` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_deployprop
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_execution`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_execution`;
CREATE TABLE `jbpm4_execution` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ACTIVITYNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PROCDEFID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HASVARS_` bit(1) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `HISACTINST_` bigint(20) DEFAULT NULL,
  `PARENT_` bigint(20) DEFAULT NULL,
  `INSTANCE_` bigint(20) DEFAULT NULL,
  `SUPEREXEC_` bigint(20) DEFAULT NULL,
  `SUBPROCINST_` bigint(20) DEFAULT NULL,
  `PARENT_IDX_` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  UNIQUE KEY `ID_` (`ID_`),
  KEY `FK_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `FK_EXEC_INSTANCE` (`INSTANCE_`),
  KEY `FK_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `FK_EXEC_PARENT` (`PARENT_`),
  CONSTRAINT `FK_EXEC_INSTANCE` FOREIGN KEY (`INSTANCE_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUBPI` FOREIGN KEY (`SUBPROCINST_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUPEREXEC` FOREIGN KEY (`SUPEREXEC_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_execution
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_actinst`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_actinst`;
CREATE TABLE `jbpm4_hist_actinst` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `HPROCI_` bigint(20) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EXECUTION_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `START_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TRANSITION_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NEXTIDX_` int(11) DEFAULT NULL,
  `HTASK_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_HACTI_HPROCI` (`HPROCI_`),
  KEY `FK_HTI_HTASK` (`HTASK_`),
  CONSTRAINT `FK_HACTI_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HTI_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_hist_actinst
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_detail`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_detail`;
CREATE TABLE `jbpm4_hist_detail` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USERID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TIME_` datetime DEFAULT NULL,
  `HPROCI_` bigint(20) DEFAULT NULL,
  `HPROCIIDX_` int(11) DEFAULT NULL,
  `HACTI_` bigint(20) DEFAULT NULL,
  `HACTIIDX_` int(11) DEFAULT NULL,
  `HTASK_` bigint(20) DEFAULT NULL,
  `HTASKIDX_` int(11) DEFAULT NULL,
  `HVAR_` bigint(20) DEFAULT NULL,
  `HVARIDX_` int(11) DEFAULT NULL,
  `MESSAGE_` text COLLATE utf8_unicode_ci,
  `OLD_STR_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NEW_STR_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OLD_INT_` int(11) DEFAULT NULL,
  `NEW_INT_` int(11) DEFAULT NULL,
  `OLD_TIME_` datetime DEFAULT NULL,
  `NEW_TIME_` datetime DEFAULT NULL,
  `PARENT_` bigint(20) DEFAULT NULL,
  `PARENT_IDX_` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_HDETAIL_HVAR` (`HVAR_`),
  KEY `FK_HDETAIL_HPROCI` (`HPROCI_`),
  KEY `FK_HDETAIL_HTASK` (`HTASK_`),
  KEY `FK_HDETAIL_HACTI` (`HACTI_`),
  CONSTRAINT `FK_HDETAIL_HACTI` FOREIGN KEY (`HACTI_`) REFERENCES `jbpm4_hist_actinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HVAR` FOREIGN KEY (`HVAR_`) REFERENCES `jbpm4_hist_var` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_hist_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_procinst`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_procinst`;
CREATE TABLE `jbpm4_hist_procinst` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PROCDEFID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `START_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ENDACTIVITY_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NEXTIDX_` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_hist_procinst
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_task`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_task`;
CREATE TABLE `jbpm4_hist_task` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `EXECUTION_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OUTCOME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `NEXTIDX_` int(11) DEFAULT NULL,
  `SUPERTASK_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_HSUPERT_SUB` (`SUPERTASK_`),
  CONSTRAINT `FK_HSUPERT_SUB` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_hist_task
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_hist_var`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_hist_var`;
CREATE TABLE `jbpm4_hist_var` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `PROCINSTID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EXECUTIONID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VARNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HPROCI_` bigint(20) DEFAULT NULL,
  `HTASK_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_HVAR_HPROCI` (`HPROCI_`),
  KEY `FK_HVAR_HTASK` (`HTASK_`),
  CONSTRAINT `FK_HVAR_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HVAR_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_hist_var
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_id_group`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_id_group`;
CREATE TABLE `jbpm4_id_group` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PARENT_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_GROUP_PARENT` (`PARENT_`),
  CONSTRAINT `FK_GROUP_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_id_group` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_id_group
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_id_membership`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_id_membership`;
CREATE TABLE `jbpm4_id_membership` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USER_` bigint(20) DEFAULT NULL,
  `GROUP_` bigint(20) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_MEM_GROUP` (`GROUP_`),
  KEY `FK_MEM_USER` (`USER_`),
  CONSTRAINT `FK_MEM_GROUP` FOREIGN KEY (`GROUP_`) REFERENCES `jbpm4_id_group` (`DBID_`),
  CONSTRAINT `FK_MEM_USER` FOREIGN KEY (`USER_`) REFERENCES `jbpm4_id_user` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_id_membership
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_id_user`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_id_user`;
CREATE TABLE `jbpm4_id_user` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GIVENNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAMILYNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BUSINESSEMAIL_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_id_user
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_job`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_job`;
CREATE TABLE `jbpm4_job` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `STATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ISEXCLUSIVE_` bit(1) DEFAULT NULL,
  `LOCKOWNER_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LOCKEXPTIME_` datetime DEFAULT NULL,
  `EXCEPTION_` text COLLATE utf8_unicode_ci,
  `RETRIES_` int(11) DEFAULT NULL,
  `PROCESSINSTANCE_` bigint(20) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  `CFG_` bigint(20) DEFAULT NULL,
  `SIGNAL_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EVENT_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_JOB_CFG` (`CFG_`),
  CONSTRAINT `FK_JOB_CFG` FOREIGN KEY (`CFG_`) REFERENCES `jbpm4_lob` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_job
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_lob`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_lob`;
CREATE TABLE `jbpm4_lob` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `BLOB_VALUE_` blob,
  `DEPLOYMENT_` bigint(20) DEFAULT NULL,
  `NAME_` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`DBID_`),
  KEY `FK_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  CONSTRAINT `FK_LOB_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_lob
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_participation`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_participation`;
CREATE TABLE `jbpm4_participation` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `GROUPID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `USERID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TASK_` bigint(20) DEFAULT NULL,
  `SWIMLANE_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_PART_SWIMLANE` (`SWIMLANE_`),
  KEY `FK_PART_TASK` (`TASK_`),
  CONSTRAINT `FK_PART_SWIMLANE` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`),
  CONSTRAINT `FK_PART_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_participation
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_property`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_property`;
CREATE TABLE `jbpm4_property` (
  `KEY_` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `VALUE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_property
-- ----------------------------
INSERT INTO `jbpm4_property` VALUES ('next.dbid', '1', '10001');

-- ----------------------------
-- Table structure for `jbpm4_swimlane`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_swimlane`;
CREATE TABLE `jbpm4_swimlane` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_SWIMLANE_EXEC` (`EXECUTION_`),
  CONSTRAINT `FK_SWIMLANE_EXEC` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_swimlane
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_task`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_task`;
CREATE TABLE `jbpm4_task` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCR_` text COLLATE utf8_unicode_ci,
  `STATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FORM_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` int(11) DEFAULT NULL,
  `SIGNALLING_` bit(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HASVARS_` bit(1) DEFAULT NULL,
  `SUPERTASK_` bigint(20) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  `PROCINST_` bigint(20) DEFAULT NULL,
  `SWIMLANE_` bigint(20) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_TASK_SWIML` (`SWIMLANE_`),
  KEY `FK_TASK_SUPERTASK` (`SUPERTASK_`),
  CONSTRAINT `FK_TASK_SUPERTASK` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_task` (`DBID_`),
  CONSTRAINT `FK_TASK_SWIML` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_task
-- ----------------------------

-- ----------------------------
-- Table structure for `jbpm4_variable`
-- ----------------------------
DROP TABLE IF EXISTS `jbpm4_variable`;
CREATE TABLE `jbpm4_variable` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `KEY_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONVERTER_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HIST_` bit(1) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  `TASK_` bigint(20) DEFAULT NULL,
  `LOB_` bigint(20) DEFAULT NULL,
  `DATE_VALUE_` datetime DEFAULT NULL,
  `DOUBLE_VALUE_` double DEFAULT NULL,
  `CLASSNAME_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LONG_VALUE_` bigint(20) DEFAULT NULL,
  `STRING_VALUE_` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TEXT_VALUE_` text COLLATE utf8_unicode_ci,
  `EXESYS_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `FK_VAR_EXESYS` (`EXESYS_`),
  KEY `FK_VAR_LOB` (`LOB_`),
  KEY `FK_VAR_TASK` (`TASK_`),
  KEY `FK_VAR_EXECUTION` (`EXECUTION_`),
  CONSTRAINT `FK_VAR_EXECUTION` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_EXESYS` FOREIGN KEY (`EXESYS_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_LOB` FOREIGN KEY (`LOB_`) REFERENCES `jbpm4_lob` (`DBID_`),
  CONSTRAINT `FK_VAR_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of jbpm4_variable
-- ----------------------------

-- ----------------------------
-- Table structure for `job`
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `jobId` bigint(20) NOT NULL AUTO_INCREMENT,
  `delFlag` smallint(6) DEFAULT NULL,
  `jobName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `memo` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `depId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`jobId`),
  UNIQUE KEY `jobId` (`jobId`),
  KEY `FK19BBD9C84EE5` (`depId`),
  KEY `FK19BBD1E88585B` (`depId`),
  CONSTRAINT `FK19BBD1E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK19BBD9C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of job
-- ----------------------------

-- ----------------------------
-- Table structure for `job_change`
-- ----------------------------
DROP TABLE IF EXISTS `job_change`;
CREATE TABLE `job_change` (
  `changeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `changeReason` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkOpinion` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkTime` datetime DEFAULT NULL,
  `memo` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newDepId` bigint(20) DEFAULT NULL,
  `newDepName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newJobId` bigint(20) NOT NULL,
  `newJobName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `newStandardId` bigint(20) DEFAULT NULL,
  `newStandardName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newStandardNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `newTotalMoney` decimal(18,0) DEFAULT NULL,
  `orgDepId` bigint(20) DEFAULT NULL,
  `orgDepName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgJobId` bigint(20) NOT NULL,
  `orgJobName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `orgStandardId` bigint(20) DEFAULT NULL,
  `orgStandardName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgStandardNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgTotalMoney` decimal(18,0) DEFAULT NULL,
  `profileId` bigint(20) NOT NULL,
  `profileNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `regName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `userName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`changeId`),
  UNIQUE KEY `changeId` (`changeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of job_change
-- ----------------------------

-- ----------------------------
-- Table structure for `leader_read`
-- ----------------------------
DROP TABLE IF EXISTS `leader_read`;
CREATE TABLE `leader_read` (
  `readId` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `depLevel` int(11) DEFAULT NULL,
  `depName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isPass` smallint(6) NOT NULL,
  `leaderName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `leaderOpinion` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `archivesId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`readId`),
  UNIQUE KEY `readId` (`readId`),
  KEY `FK9A268B0C8023925F` (`archivesId`),
  KEY `FK9A268B0CCB218229` (`archivesId`),
  CONSTRAINT `FK9A268B0CCB218229` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`),
  CONSTRAINT `FK9A268B0C8023925F` FOREIGN KEY (`archivesId`) REFERENCES `archives` (`archivesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of leader_read
-- ----------------------------

-- ----------------------------
-- Table structure for `mail`
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `mailId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `copyToIDs` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `copyToNames` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileIds` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `filenames` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `importantFlag` smallint(6) NOT NULL,
  `mailStatus` smallint(6) NOT NULL,
  `recipientIDs` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recipientNames` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sendTime` datetime NOT NULL,
  `sender` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `senderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mailId`),
  UNIQUE KEY `mailId` (`mailId`),
  KEY `FK3305B7CC9D6613` (`senderId`),
  KEY `FK3305B78508C05D` (`senderId`),
  CONSTRAINT `FK3305B78508C05D` FOREIGN KEY (`senderId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK3305B7CC9D6613` FOREIGN KEY (`senderId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mail
-- ----------------------------

-- ----------------------------
-- Table structure for `mail_attach`
-- ----------------------------
DROP TABLE IF EXISTS `mail_attach`;
CREATE TABLE `mail_attach` (
  `mailId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`mailId`,`fileId`),
  KEY `FKE5D359CD154DD61A` (`mailId`),
  KEY `FKE5D359CDD23E98A1` (`fileId`),
  KEY `FKE5D359CD604BC5E4` (`mailId`),
  KEY `FKE5D359CDE6FEA217` (`fileId`),
  CONSTRAINT `FKE5D359CDE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKE5D359CD154DD61A` FOREIGN KEY (`mailId`) REFERENCES `mail` (`mailId`),
  CONSTRAINT `FKE5D359CD604BC5E4` FOREIGN KEY (`mailId`) REFERENCES `mail` (`mailId`),
  CONSTRAINT `FKE5D359CDD23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mail_attach
-- ----------------------------

-- ----------------------------
-- Table structure for `mail_box`
-- ----------------------------
DROP TABLE IF EXISTS `mail_box`;
CREATE TABLE `mail_box` (
  `boxId` bigint(20) NOT NULL AUTO_INCREMENT,
  `delFlag` smallint(6) NOT NULL,
  `note` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `readFlag` smallint(6) NOT NULL,
  `replyFlag` smallint(6) NOT NULL,
  `sendTime` datetime NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `mailId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`boxId`),
  UNIQUE KEY `boxId` (`boxId`),
  KEY `FKFF67FF43154DD61A` (`mailId`),
  KEY `FKFF67FF43D62D3D9F` (`folderId`),
  KEY `FKFF67FF4350662A29` (`userId`),
  KEY `FKFF67FF43604BC5E4` (`mailId`),
  KEY `FKFF67FF43DE66ADE9` (`folderId`),
  KEY `FKFF67FF438D18473` (`userId`),
  CONSTRAINT `FKFF67FF438D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKFF67FF43154DD61A` FOREIGN KEY (`mailId`) REFERENCES `mail` (`mailId`),
  CONSTRAINT `FKFF67FF4350662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKFF67FF43604BC5E4` FOREIGN KEY (`mailId`) REFERENCES `mail` (`mailId`),
  CONSTRAINT `FKFF67FF43D62D3D9F` FOREIGN KEY (`folderId`) REFERENCES `mail_folder` (`folderId`),
  CONSTRAINT `FKFF67FF43DE66ADE9` FOREIGN KEY (`folderId`) REFERENCES `mail_folder` (`folderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mail_box
-- ----------------------------

-- ----------------------------
-- Table structure for `mail_folder`
-- ----------------------------
DROP TABLE IF EXISTS `mail_folder`;
CREATE TABLE `mail_folder` (
  `folderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `depLevel` int(11) NOT NULL,
  `folderName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `folderType` smallint(6) NOT NULL,
  `isPublic` smallint(6) NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `path` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `folderId` (`folderId`),
  KEY `FKEE1187F650662A29` (`userId`),
  KEY `FKEE1187F68D18473` (`userId`),
  CONSTRAINT `FKEE1187F68D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKEE1187F650662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mail_folder
-- ----------------------------
INSERT INTO `mail_folder` VALUES ('1', '1', '收件箱', '1', '1', '0', '0.1.', null);
INSERT INTO `mail_folder` VALUES ('2', '1', '发件箱', '2', '1', '0', '0.2.', null);
INSERT INTO `mail_folder` VALUES ('3', '1', '草稿箱', '3', '1', '0', '0.3.', null);
INSERT INTO `mail_folder` VALUES ('4', '1', '垃圾箱', '4', '1', '0', '0.4.', null);

-- ----------------------------
-- Table structure for `mobile_msg`
-- ----------------------------
DROP TABLE IF EXISTS `mobile_msg`;
CREATE TABLE `mobile_msg` (
  `msgId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `mobileNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`msgId`),
  UNIQUE KEY `msgId` (`msgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mobile_msg
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `isDeskImage` smallint(6) DEFAULT NULL,
  `issuer` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `replyCounts` int(11) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `subject` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `subjectIcon` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updateTime` datetime NOT NULL,
  `viewCounts` int(11) DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`newsId`),
  UNIQUE KEY `newsId` (`newsId`),
  KEY `FK338AD3107499CC` (`typeId`),
  KEY `FK338AD3376FCE42` (`typeId`),
  CONSTRAINT `FK338AD3376FCE42` FOREIGN KEY (`typeId`) REFERENCES `news_type` (`typeId`),
  CONSTRAINT `FK338AD3107499CC` FOREIGN KEY (`typeId`) REFERENCES `news_type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `news_comment`
-- ----------------------------
DROP TABLE IF EXISTS `news_comment`;
CREATE TABLE `news_comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `newsId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  UNIQUE KEY `commentId` (`commentId`),
  KEY `FKFAA69CF31413E64B` (`newsId`),
  KEY `FKFAA69CF350662A29` (`userId`),
  KEY `FKFAA69CF36797C5C1` (`newsId`),
  KEY `FKFAA69CF38D18473` (`userId`),
  CONSTRAINT `FKFAA69CF38D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKFAA69CF31413E64B` FOREIGN KEY (`newsId`) REFERENCES `news` (`newsId`),
  CONSTRAINT `FKFAA69CF350662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKFAA69CF36797C5C1` FOREIGN KEY (`newsId`) REFERENCES `news` (`newsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of news_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `news_type`
-- ----------------------------
DROP TABLE IF EXISTS `news_type`;
CREATE TABLE `news_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` smallint(6) DEFAULT NULL,
  `typeName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of news_type
-- ----------------------------

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `effectiveDate` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `noticeContent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `noticeTitle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`noticeId`),
  UNIQUE KEY `noticeId` (`noticeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for `office_goods`
-- ----------------------------
DROP TABLE IF EXISTS `office_goods`;
CREATE TABLE `office_goods` (
  `goodsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `goodsNo` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `isWarning` smallint(6) NOT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `specifications` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `stockCounts` int(11) NOT NULL,
  `unit` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `warnCounts` int(11) NOT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`goodsId`),
  UNIQUE KEY `goodsId` (`goodsId`),
  KEY `FK81603C1341609D54` (`typeId`),
  KEY `FK81603C137B72FBCA` (`typeId`),
  CONSTRAINT `FK81603C137B72FBCA` FOREIGN KEY (`typeId`) REFERENCES `office_goods_type` (`typeId`),
  CONSTRAINT `FK81603C1341609D54` FOREIGN KEY (`typeId`) REFERENCES `office_goods_type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of office_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `office_goods_type`
-- ----------------------------
DROP TABLE IF EXISTS `office_goods_type`;
CREATE TABLE `office_goods_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of office_goods_type
-- ----------------------------

-- ----------------------------
-- Table structure for `out_mail`
-- ----------------------------
DROP TABLE IF EXISTS `out_mail`;
CREATE TABLE `out_mail` (
  `mailId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bCCAddresses` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bCCAnames` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cCAddresses` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cCNames` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileIds` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileNames` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mailDate` datetime NOT NULL,
  `readFlag` smallint(6) NOT NULL,
  `receiverAddresses` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `receiverNames` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `replyFlag` smallint(6) NOT NULL,
  `senderAddresses` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `senderName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `uid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mailId`),
  UNIQUE KEY `mailId` (`mailId`),
  KEY `FK33B70084E400CCB` (`folderId`),
  KEY `FK33B700862724041` (`folderId`),
  CONSTRAINT `FK33B700862724041` FOREIGN KEY (`folderId`) REFERENCES `out_mail_folder` (`folderId`),
  CONSTRAINT `FK33B70084E400CCB` FOREIGN KEY (`folderId`) REFERENCES `out_mail_folder` (`folderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of out_mail
-- ----------------------------

-- ----------------------------
-- Table structure for `out_mail_file`
-- ----------------------------
DROP TABLE IF EXISTS `out_mail_file`;
CREATE TABLE `out_mail_file` (
  `mailId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`mailId`,`fileId`),
  KEY `FKFB7C03538AC67646` (`mailId`),
  KEY `FKFB7C0353D23E98A1` (`fileId`),
  KEY `FKFB7C03536FA9FA3C` (`mailId`),
  KEY `FKFB7C0353E6FEA217` (`fileId`),
  CONSTRAINT `FKFB7C0353E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKFB7C03536FA9FA3C` FOREIGN KEY (`mailId`) REFERENCES `out_mail` (`mailId`),
  CONSTRAINT `FKFB7C03538AC67646` FOREIGN KEY (`mailId`) REFERENCES `out_mail` (`mailId`),
  CONSTRAINT `FKFB7C0353D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of out_mail_file
-- ----------------------------

-- ----------------------------
-- Table structure for `out_mail_folder`
-- ----------------------------
DROP TABLE IF EXISTS `out_mail_folder`;
CREATE TABLE `out_mail_folder` (
  `folderId` bigint(20) NOT NULL AUTO_INCREMENT,
  `depLevel` int(11) NOT NULL,
  `folderName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `folderType` smallint(6) NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `path` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `folderId` (`folderId`),
  KEY `FKCDD108550662A29` (`userId`),
  KEY `FKCDD10858D18473` (`userId`),
  CONSTRAINT `FKCDD10858D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKCDD108550662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of out_mail_folder
-- ----------------------------
INSERT INTO `out_mail_folder` VALUES ('1', '1', '收件箱', '1', '0', '0.1', null);
INSERT INTO `out_mail_folder` VALUES ('2', '1', '发件箱', '2', '0', '0.2', null);
INSERT INTO `out_mail_folder` VALUES ('3', '1', '草稿箱', '3', '0', '0.3', null);
INSERT INTO `out_mail_folder` VALUES ('4', '1', '垃圾箱', '4', '0', '0.4', null);

-- ----------------------------
-- Table structure for `out_mail_user_seting`
-- ----------------------------
DROP TABLE IF EXISTS `out_mail_user_seting`;
CREATE TABLE `out_mail_user_seting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mailAddress` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `mailPass` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `popHost` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `popPort` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `smtpHost` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `smtpPort` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK9AD4317D50662A29` (`userId`),
  KEY `FK9AD4317D8D18473` (`userId`),
  CONSTRAINT `FK9AD4317D8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK9AD4317D50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of out_mail_user_seting
-- ----------------------------

-- ----------------------------
-- Table structure for `out_mail_user_seting_out_mail`
-- ----------------------------
DROP TABLE IF EXISTS `out_mail_user_seting_out_mail`;
CREATE TABLE `out_mail_user_seting_out_mail` (
  `out_mail_user_seting_id` bigint(20) NOT NULL,
  `outMails_mailId` bigint(20) NOT NULL,
  PRIMARY KEY (`out_mail_user_seting_id`,`outMails_mailId`),
  UNIQUE KEY `outMails_mailId` (`outMails_mailId`),
  KEY `FKB2CFCC8A8A862497` (`outMails_mailId`),
  KEY `FKB2CFCC8A3095CF5C` (`out_mail_user_seting_id`),
  KEY `FKB2CFCC8A6F69A88D` (`outMails_mailId`),
  KEY `FKB2CFCC8AB0F557D2` (`out_mail_user_seting_id`),
  CONSTRAINT `FKB2CFCC8AB0F557D2` FOREIGN KEY (`out_mail_user_seting_id`) REFERENCES `out_mail_user_seting` (`id`),
  CONSTRAINT `FKB2CFCC8A3095CF5C` FOREIGN KEY (`out_mail_user_seting_id`) REFERENCES `out_mail_user_seting` (`id`),
  CONSTRAINT `FKB2CFCC8A6F69A88D` FOREIGN KEY (`outMails_mailId`) REFERENCES `out_mail` (`mailId`),
  CONSTRAINT `FKB2CFCC8A8A862497` FOREIGN KEY (`outMails_mailId`) REFERENCES `out_mail` (`mailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of out_mail_user_seting_out_mail
-- ----------------------------

-- ----------------------------
-- Table structure for `paint_template`
-- ----------------------------
DROP TABLE IF EXISTS `paint_template`;
CREATE TABLE `paint_template` (
  `ptemplateId` bigint(20) NOT NULL AUTO_INCREMENT,
  `isActivate` smallint(6) NOT NULL,
  `path` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `templateName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ptemplateId`),
  UNIQUE KEY `ptemplateId` (`ptemplateId`),
  KEY `FKBAB68F3BD23E98A1` (`fileId`),
  KEY `FKBAB68F3BE6FEA217` (`fileId`),
  CONSTRAINT `FKBAB68F3BE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKBAB68F3BD23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of paint_template
-- ----------------------------

-- ----------------------------
-- Table structure for `phone_book`
-- ----------------------------
DROP TABLE IF EXISTS `phone_book`;
CREATE TABLE `phone_book` (
  `phoneId` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` datetime DEFAULT NULL,
  `childs` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyAddress` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyFax` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyPhone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `duty` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `homeAddress` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homeZip` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isShared` smallint(6) NOT NULL,
  `mobile` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MSN` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nickName` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `QQ` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spouse` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`phoneId`),
  UNIQUE KEY `phoneId` (`phoneId`),
  KEY `FK9340EB1ACCA72C1C` (`groupId`),
  KEY `FK9340EB1A50662A29` (`userId`),
  KEY `FK9340EB1AD4E09C66` (`groupId`),
  KEY `FK9340EB1A8D18473` (`userId`),
  CONSTRAINT `FK9340EB1A8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK9340EB1A50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK9340EB1ACCA72C1C` FOREIGN KEY (`groupId`) REFERENCES `phone_group` (`groupId`),
  CONSTRAINT `FK9340EB1AD4E09C66` FOREIGN KEY (`groupId`) REFERENCES `phone_group` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of phone_book
-- ----------------------------

-- ----------------------------
-- Table structure for `phone_group`
-- ----------------------------
DROP TABLE IF EXISTS `phone_group`;
CREATE TABLE `phone_group` (
  `groupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `isShared` smallint(6) NOT NULL,
  `SN` int(11) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`groupId`),
  UNIQUE KEY `groupId` (`groupId`),
  KEY `FKD5244C6E50662A29` (`userId`),
  KEY `FKD5244C6E8D18473` (`userId`),
  CONSTRAINT `FKD5244C6E8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKD5244C6E50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of phone_group
-- ----------------------------

-- ----------------------------
-- Table structure for `picture`
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `picId` bigint(20) NOT NULL AUTO_INCREMENT,
  `albumId` varchar(20) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`picId`),
  UNIQUE KEY `picId` (`picId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------

-- ----------------------------
-- Table structure for `plan_attend`
-- ----------------------------
DROP TABLE IF EXISTS `plan_attend`;
CREATE TABLE `plan_attend` (
  `attendId` bigint(20) NOT NULL AUTO_INCREMENT,
  `isDep` smallint(6) NOT NULL,
  `isPrimary` smallint(6) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `depId` bigint(20) DEFAULT NULL,
  `planId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`attendId`),
  UNIQUE KEY `attendId` (`attendId`),
  KEY `FK5148407051E47031` (`planId`),
  KEY `FK514840709C84EE5` (`depId`),
  KEY `FK5148407050662A29` (`userId`),
  KEY `FK5148407078DFA4A7` (`planId`),
  KEY `FK514840701E88585B` (`depId`),
  KEY `FK514840708D18473` (`userId`),
  CONSTRAINT `FK514840708D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK514840701E88585B` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`),
  CONSTRAINT `FK5148407050662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK5148407051E47031` FOREIGN KEY (`planId`) REFERENCES `work_plan` (`planId`),
  CONSTRAINT `FK5148407078DFA4A7` FOREIGN KEY (`planId`) REFERENCES `work_plan` (`planId`),
  CONSTRAINT `FK514840709C84EE5` FOREIGN KEY (`depId`) REFERENCES `department` (`depId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of plan_attend
-- ----------------------------

-- ----------------------------
-- Table structure for `plan_file`
-- ----------------------------
DROP TABLE IF EXISTS `plan_file`;
CREATE TABLE `plan_file` (
  `planId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`planId`,`fileId`),
  KEY `FK7D4BB4D251E47031` (`planId`),
  KEY `FK7D4BB4D2D23E98A1` (`fileId`),
  KEY `FK7D4BB4D278DFA4A7` (`planId`),
  KEY `FK7D4BB4D2E6FEA217` (`fileId`),
  CONSTRAINT `FK7D4BB4D2E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK7D4BB4D251E47031` FOREIGN KEY (`planId`) REFERENCES `work_plan` (`planId`),
  CONSTRAINT `FK7D4BB4D278DFA4A7` FOREIGN KEY (`planId`) REFERENCES `work_plan` (`planId`),
  CONSTRAINT `FK7D4BB4D2D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of plan_file
-- ----------------------------

-- ----------------------------
-- Table structure for `plan_type`
-- ----------------------------
DROP TABLE IF EXISTS `plan_type`;
CREATE TABLE `plan_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of plan_type
-- ----------------------------

-- ----------------------------
-- Table structure for `process_form`
-- ----------------------------
DROP TABLE IF EXISTS `process_form`;
CREATE TABLE `process_form` (
  `formId` bigint(20) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime NOT NULL,
  `creatorId` bigint(20) DEFAULT NULL,
  `creatorName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` int(11) NOT NULL,
  `runId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`formId`),
  UNIQUE KEY `formId` (`formId`),
  KEY `FK76CA5494C2A20ACC` (`runId`),
  KEY `FK76CA549417A1F9C2` (`runId`),
  CONSTRAINT `FK76CA549417A1F9C2` FOREIGN KEY (`runId`) REFERENCES `process_run` (`runId`),
  CONSTRAINT `FK76CA5494C2A20ACC` FOREIGN KEY (`runId`) REFERENCES `process_run` (`runId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of process_form
-- ----------------------------

-- ----------------------------
-- Table structure for `process_run`
-- ----------------------------
DROP TABLE IF EXISTS `process_run`;
CREATE TABLE `process_run` (
  `runId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `creator` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `piId` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `runStatus` smallint(6) NOT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `defId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`runId`),
  UNIQUE KEY `runId` (`runId`),
  KEY `FKC1738BBBE5E7736` (`defId`),
  KEY `FKC1738BB50662A29` (`userId`),
  KEY `FKC1738BB419F9C80` (`defId`),
  KEY `FKC1738BB8D18473` (`userId`),
  CONSTRAINT `FKC1738BB8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKC1738BB419F9C80` FOREIGN KEY (`defId`) REFERENCES `pro_definition` (`defId`),
  CONSTRAINT `FKC1738BB50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKC1738BBBE5E7736` FOREIGN KEY (`defId`) REFERENCES `pro_definition` (`defId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of process_run
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productId` bigint(20) NOT NULL AUTO_INCREMENT,
  `costPrice` decimal(18,0) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `productDesc` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productModel` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `salesPrice` decimal(18,0) DEFAULT NULL,
  `unit` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updatetime` datetime NOT NULL,
  `providerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `productId` (`productId`),
  KEY `FKED8DCCEF5A4BCDD7` (`providerId`),
  KEY `FKED8DCCEF6F0BD74D` (`providerId`),
  CONSTRAINT `FKED8DCCEF6F0BD74D` FOREIGN KEY (`providerId`) REFERENCES `provider` (`providerId`),
  CONSTRAINT `FKED8DCCEF5A4BCDD7` FOREIGN KEY (`providerId`) REFERENCES `provider` (`providerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectId` bigint(20) NOT NULL AUTO_INCREMENT,
  `fax` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `isContract` smallint(6) NOT NULL,
  `mobile` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `otherContacts` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `projectNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `reqDesc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `customerId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`projectId`),
  UNIQUE KEY `projectId` (`projectId`),
  KEY `FKED904B194ECFFA71` (`customerId`),
  KEY `FKED904B1950662A29` (`userId`),
  KEY `FKED904B19639003E7` (`customerId`),
  KEY `FKED904B198D18473` (`userId`),
  CONSTRAINT `FKED904B198D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKED904B194ECFFA71` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  CONSTRAINT `FKED904B1950662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKED904B19639003E7` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `project_file`
-- ----------------------------
DROP TABLE IF EXISTS `project_file`;
CREATE TABLE `project_file` (
  `projectId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`projectId`,`fileId`),
  KEY `FK37FA6AE21FBDC8B3` (`projectId`),
  KEY `FK37FA6AE2D23E98A1` (`fileId`),
  KEY `FK37FA6AE26ABBB87D` (`projectId`),
  KEY `FK37FA6AE2E6FEA217` (`fileId`),
  CONSTRAINT `FK37FA6AE2E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK37FA6AE21FBDC8B3` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`),
  CONSTRAINT `FK37FA6AE26ABBB87D` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`),
  CONSTRAINT `FK37FA6AE2D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project_file
-- ----------------------------

-- ----------------------------
-- Table structure for `provider`
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `providerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `contactor` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openBank` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `providerName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `rank` int(11) DEFAULT NULL,
  `site` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`providerId`),
  UNIQUE KEY `providerId` (`providerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of provider
-- ----------------------------

-- ----------------------------
-- Table structure for `pro_definition`
-- ----------------------------
DROP TABLE IF EXISTS `pro_definition`;
CREATE TABLE `pro_definition` (
  `defId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `defXml` text COLLATE utf8_unicode_ci NOT NULL,
  `deployId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `drawDefXml` text COLLATE utf8_unicode_ci,
  `isDefault` smallint(6) NOT NULL,
  `name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`defId`),
  UNIQUE KEY `defId` (`defId`),
  KEY `FK61CF5A25A96E6152` (`typeId`),
  KEY `FK61CF5A25689FC61C` (`typeId`),
  CONSTRAINT `FK61CF5A25689FC61C` FOREIGN KEY (`typeId`) REFERENCES `pro_type` (`typeId`),
  CONSTRAINT `FK61CF5A25A96E6152` FOREIGN KEY (`typeId`) REFERENCES `pro_type` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pro_definition
-- ----------------------------
-- ----------------------------
-- Table structure for `pro_type`
-- ----------------------------
DROP TABLE IF EXISTS `pro_type`;
CREATE TABLE `pro_type` (
  `typeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`typeId`),
  UNIQUE KEY `typeId` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pro_type
-- ----------------------------
INSERT INTO `pro_type` VALUES ('1', '公文');
INSERT INTO `pro_type` VALUES ('2', '请假');

-- ----------------------------
-- Table structure for `pro_user_assign`
-- ----------------------------
DROP TABLE IF EXISTS `pro_user_assign`;
CREATE TABLE `pro_user_assign` (
  `assignId` bigint(20) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `deployId` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `isSigned` smallint(6) DEFAULT NULL,
  `roleId` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `roleName` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`assignId`),
  UNIQUE KEY `assignId` (`assignId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pro_user_assign
-- ----------------------------
INSERT INTO `pro_user_assign` VALUES ('1', '登记拟办', '19', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('2', '领导批示', '19', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('3', '公文分发', '19', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('4', '承办传阅', '19', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('5', '发文核稿', '25', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('6', '科室审核', '25', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('7', '主管领导审批', '25', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('8', '分管领导签发', '25', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('9', '发文校对', '25', null, '', '', '', '');
INSERT INTO `pro_user_assign` VALUES ('10', '发文分发', '25', null, '', '', '', '');

-- ----------------------------
-- Table structure for `region`
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `regionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) DEFAULT NULL,
  `regionName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `regionType` smallint(6) NOT NULL,
  PRIMARY KEY (`regionId`),
  UNIQUE KEY `regionId` (`regionId`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES ('1', '0', '北京', '2');
INSERT INTO `region` VALUES ('2', '0', '上海', '2');
INSERT INTO `region` VALUES ('3', '0', '天津', '2');
INSERT INTO `region` VALUES ('4', '0', '重庆', '2');
INSERT INTO `region` VALUES ('5', '0', '河北', '1');
INSERT INTO `region` VALUES ('6', '0', '山西', '1');
INSERT INTO `region` VALUES ('7', '0', '内蒙古', '1');
INSERT INTO `region` VALUES ('8', '0', '辽宁', '1');
INSERT INTO `region` VALUES ('9', '0', '吉林', '1');
INSERT INTO `region` VALUES ('10', '0', '黑龙江', '1');
INSERT INTO `region` VALUES ('11', '0', '江苏', '1');
INSERT INTO `region` VALUES ('12', '0', '浙江', '1');
INSERT INTO `region` VALUES ('13', '0', '安徽', '1');
INSERT INTO `region` VALUES ('14', '0', '福建', '1');
INSERT INTO `region` VALUES ('15', '0', '江西', '1');
INSERT INTO `region` VALUES ('16', '0', '山东', '1');
INSERT INTO `region` VALUES ('17', '0', '河南', '1');
INSERT INTO `region` VALUES ('18', '0', '湖北', '1');
INSERT INTO `region` VALUES ('19', '0', '湖南', '1');
INSERT INTO `region` VALUES ('20', '0', '广东', '1');
INSERT INTO `region` VALUES ('21', '0', '广西', '1');
INSERT INTO `region` VALUES ('22', '0', '海南', '1');
INSERT INTO `region` VALUES ('23', '0', '四川', '1');
INSERT INTO `region` VALUES ('24', '0', '贵州', '1');
INSERT INTO `region` VALUES ('25', '0', '云南', '1');
INSERT INTO `region` VALUES ('26', '0', '西藏', '1');
INSERT INTO `region` VALUES ('27', '0', '陕西', '1');
INSERT INTO `region` VALUES ('28', '0', '甘肃', '1');
INSERT INTO `region` VALUES ('29', '0', '青海', '1');
INSERT INTO `region` VALUES ('30', '0', '宁夏', '1');
INSERT INTO `region` VALUES ('31', '0', '新疆', '1');
INSERT INTO `region` VALUES ('32', '0', '台湾', '1');
INSERT INTO `region` VALUES ('33', '0', '港澳', '1');
INSERT INTO `region` VALUES ('34', '5', '石家庄', '2');
INSERT INTO `region` VALUES ('35', '5', '唐山', '2');
INSERT INTO `region` VALUES ('36', '5', '秦皇岛', '2');
INSERT INTO `region` VALUES ('37', '5', '邯郸', '2');
INSERT INTO `region` VALUES ('38', '5', '邢台', '2');
INSERT INTO `region` VALUES ('39', '5', '保定', '2');
INSERT INTO `region` VALUES ('40', '5', '张家口', '2');
INSERT INTO `region` VALUES ('41', '5', '承德', '2');
INSERT INTO `region` VALUES ('42', '5', '沧州', '2');
INSERT INTO `region` VALUES ('43', '5', '廊坊', '2');
INSERT INTO `region` VALUES ('44', '5', '衡水', '2');
INSERT INTO `region` VALUES ('45', '6', '太原', '2');
INSERT INTO `region` VALUES ('46', '6', '大同', '2');
INSERT INTO `region` VALUES ('47', '6', '阳泉', '2');
INSERT INTO `region` VALUES ('48', '6', '长治', '2');
INSERT INTO `region` VALUES ('49', '6', '晋城', '2');
INSERT INTO `region` VALUES ('50', '6', '朔州', '2');
INSERT INTO `region` VALUES ('51', '6', '晋中', '2');
INSERT INTO `region` VALUES ('52', '6', '运城', '2');
INSERT INTO `region` VALUES ('53', '6', '忻州', '2');
INSERT INTO `region` VALUES ('54', '6', '临汾', '2');
INSERT INTO `region` VALUES ('55', '6', '吕梁', '2');
INSERT INTO `region` VALUES ('56', '7', '呼和浩特', '2');
INSERT INTO `region` VALUES ('57', '7', '包头', '2');
INSERT INTO `region` VALUES ('58', '7', '乌海', '2');
INSERT INTO `region` VALUES ('59', '7', '赤峰', '2');
INSERT INTO `region` VALUES ('60', '7', '通辽', '2');
INSERT INTO `region` VALUES ('61', '7', '鄂尔多斯', '2');
INSERT INTO `region` VALUES ('62', '7', '呼伦贝尔', '2');
INSERT INTO `region` VALUES ('63', '7', '巴彦淖尔', '2');
INSERT INTO `region` VALUES ('64', '7', '乌兰察布', '2');
INSERT INTO `region` VALUES ('65', '7', '兴安', '2');
INSERT INTO `region` VALUES ('66', '7', '锡林郭勒', '2');
INSERT INTO `region` VALUES ('67', '7', '阿拉善', '2');
INSERT INTO `region` VALUES ('68', '8', '沈阳', '2');
INSERT INTO `region` VALUES ('69', '8', '大连', '2');
INSERT INTO `region` VALUES ('70', '8', '鞍山', '2');
INSERT INTO `region` VALUES ('71', '8', '抚顺', '2');
INSERT INTO `region` VALUES ('72', '8', '本溪', '2');
INSERT INTO `region` VALUES ('73', '8', '丹东', '2');
INSERT INTO `region` VALUES ('74', '8', '锦州', '2');
INSERT INTO `region` VALUES ('75', '8', '营口', '2');
INSERT INTO `region` VALUES ('76', '8', '阜新', '2');
INSERT INTO `region` VALUES ('77', '8', '辽阳', '2');
INSERT INTO `region` VALUES ('78', '8', '盘锦', '2');
INSERT INTO `region` VALUES ('79', '8', '铁岭', '2');
INSERT INTO `region` VALUES ('80', '8', '朝阳', '2');
INSERT INTO `region` VALUES ('81', '8', '葫芦岛', '2');
INSERT INTO `region` VALUES ('82', '9', '长春', '2');
INSERT INTO `region` VALUES ('83', '9', '吉林', '2');
INSERT INTO `region` VALUES ('84', '9', '四平', '2');
INSERT INTO `region` VALUES ('85', '9', '辽源', '2');
INSERT INTO `region` VALUES ('86', '9', '通化', '2');
INSERT INTO `region` VALUES ('87', '9', '白山', '2');
INSERT INTO `region` VALUES ('88', '9', '松原', '2');
INSERT INTO `region` VALUES ('89', '9', '白城', '2');
INSERT INTO `region` VALUES ('90', '9', '延边', '2');
INSERT INTO `region` VALUES ('91', '10', '哈尔滨', '2');
INSERT INTO `region` VALUES ('92', '10', '齐齐哈尔', '2');
INSERT INTO `region` VALUES ('93', '10', '鸡西', '2');
INSERT INTO `region` VALUES ('94', '10', '鹤岗', '2');
INSERT INTO `region` VALUES ('95', '10', '双鸭山', '2');
INSERT INTO `region` VALUES ('96', '10', '大庆', '2');
INSERT INTO `region` VALUES ('97', '10', '伊春', '2');
INSERT INTO `region` VALUES ('98', '10', '佳木斯', '2');
INSERT INTO `region` VALUES ('99', '10', '七台河', '2');
INSERT INTO `region` VALUES ('100', '10', '牡丹江', '2');
INSERT INTO `region` VALUES ('101', '10', '黑河', '2');
INSERT INTO `region` VALUES ('102', '10', '绥化', '2');
INSERT INTO `region` VALUES ('103', '10', '大兴安岭', '2');
INSERT INTO `region` VALUES ('104', '11', '南京', '2');
INSERT INTO `region` VALUES ('105', '11', '无锡', '2');
INSERT INTO `region` VALUES ('106', '11', '徐州', '2');
INSERT INTO `region` VALUES ('107', '11', '常州', '2');
INSERT INTO `region` VALUES ('108', '11', '苏州', '2');
INSERT INTO `region` VALUES ('109', '11', '南通', '2');
INSERT INTO `region` VALUES ('110', '11', '连云港', '2');
INSERT INTO `region` VALUES ('111', '11', '淮安', '2');
INSERT INTO `region` VALUES ('112', '11', '盐城', '2');
INSERT INTO `region` VALUES ('113', '11', '扬州', '2');
INSERT INTO `region` VALUES ('114', '11', '镇江', '2');
INSERT INTO `region` VALUES ('115', '11', '泰州', '2');
INSERT INTO `region` VALUES ('116', '11', '宿迁', '2');
INSERT INTO `region` VALUES ('117', '12', '杭州', '2');
INSERT INTO `region` VALUES ('118', '12', '宁波', '2');
INSERT INTO `region` VALUES ('119', '12', '温州', '2');
INSERT INTO `region` VALUES ('120', '12', '嘉兴', '2');
INSERT INTO `region` VALUES ('121', '12', '湖州', '2');
INSERT INTO `region` VALUES ('122', '12', '绍兴', '2');
INSERT INTO `region` VALUES ('123', '12', '金华', '2');
INSERT INTO `region` VALUES ('124', '12', '衢州', '2');
INSERT INTO `region` VALUES ('125', '12', '舟山', '2');
INSERT INTO `region` VALUES ('126', '12', '台州', '2');
INSERT INTO `region` VALUES ('127', '12', '丽水', '2');
INSERT INTO `region` VALUES ('128', '13', '合肥', '2');
INSERT INTO `region` VALUES ('129', '13', '芜湖', '2');
INSERT INTO `region` VALUES ('130', '13', '蚌埠', '2');
INSERT INTO `region` VALUES ('131', '13', '淮南', '2');
INSERT INTO `region` VALUES ('132', '13', '马鞍山', '2');
INSERT INTO `region` VALUES ('133', '13', '淮北', '2');
INSERT INTO `region` VALUES ('134', '13', '铜陵', '2');
INSERT INTO `region` VALUES ('135', '13', '安庆', '2');
INSERT INTO `region` VALUES ('136', '13', '黄山', '2');
INSERT INTO `region` VALUES ('137', '13', '滁州', '2');
INSERT INTO `region` VALUES ('138', '13', '阜阳', '2');
INSERT INTO `region` VALUES ('139', '13', '宿州', '2');
INSERT INTO `region` VALUES ('140', '13', '巢湖', '2');
INSERT INTO `region` VALUES ('141', '13', '六安', '2');
INSERT INTO `region` VALUES ('142', '13', '亳州', '2');
INSERT INTO `region` VALUES ('143', '13', '池州', '2');
INSERT INTO `region` VALUES ('144', '13', '宣城', '2');
INSERT INTO `region` VALUES ('145', '14', '福州', '2');
INSERT INTO `region` VALUES ('146', '14', '厦门', '2');
INSERT INTO `region` VALUES ('147', '14', '莆田', '2');
INSERT INTO `region` VALUES ('148', '14', '三明', '2');
INSERT INTO `region` VALUES ('149', '14', '泉州', '2');
INSERT INTO `region` VALUES ('150', '14', '漳州', '2');
INSERT INTO `region` VALUES ('151', '14', '南平', '2');
INSERT INTO `region` VALUES ('152', '14', '龙岩', '2');
INSERT INTO `region` VALUES ('153', '14', '宁德', '2');
INSERT INTO `region` VALUES ('154', '15', '南昌', '2');
INSERT INTO `region` VALUES ('155', '15', '景德镇', '2');
INSERT INTO `region` VALUES ('156', '15', '萍乡', '2');
INSERT INTO `region` VALUES ('157', '15', '九江', '2');
INSERT INTO `region` VALUES ('158', '15', '新余', '2');
INSERT INTO `region` VALUES ('159', '15', '鹰潭', '2');
INSERT INTO `region` VALUES ('160', '15', '赣州', '2');
INSERT INTO `region` VALUES ('161', '15', '吉安', '2');
INSERT INTO `region` VALUES ('162', '15', '宜春', '2');
INSERT INTO `region` VALUES ('163', '15', '抚州', '2');
INSERT INTO `region` VALUES ('164', '15', '上饶', '2');
INSERT INTO `region` VALUES ('165', '16', '济南', '2');
INSERT INTO `region` VALUES ('166', '16', '青岛', '2');
INSERT INTO `region` VALUES ('167', '16', '淄博', '2');
INSERT INTO `region` VALUES ('168', '16', '枣庄', '2');
INSERT INTO `region` VALUES ('169', '16', '东营', '2');
INSERT INTO `region` VALUES ('170', '16', '烟台', '2');
INSERT INTO `region` VALUES ('171', '16', '潍坊', '2');
INSERT INTO `region` VALUES ('172', '16', '济宁', '2');
INSERT INTO `region` VALUES ('173', '16', '泰安', '2');
INSERT INTO `region` VALUES ('174', '16', '日照', '2');
INSERT INTO `region` VALUES ('175', '16', '莱芜', '2');
INSERT INTO `region` VALUES ('176', '16', '临沂', '2');
INSERT INTO `region` VALUES ('177', '16', '德州', '2');
INSERT INTO `region` VALUES ('178', '16', '聊城', '2');
INSERT INTO `region` VALUES ('179', '16', '滨州', '2');
INSERT INTO `region` VALUES ('180', '16', '菏泽', '2');
INSERT INTO `region` VALUES ('181', '17', '郑州', '2');
INSERT INTO `region` VALUES ('182', '17', '开封', '2');
INSERT INTO `region` VALUES ('183', '17', '洛阳', '2');
INSERT INTO `region` VALUES ('184', '17', '平顶山', '2');
INSERT INTO `region` VALUES ('185', '17', '焦作', '2');
INSERT INTO `region` VALUES ('186', '17', '鹤壁', '2');
INSERT INTO `region` VALUES ('187', '17', '新乡', '2');
INSERT INTO `region` VALUES ('188', '17', '安阳', '2');
INSERT INTO `region` VALUES ('189', '17', '濮阳', '2');
INSERT INTO `region` VALUES ('190', '17', '许昌', '2');
INSERT INTO `region` VALUES ('191', '17', '渭河', '2');
INSERT INTO `region` VALUES ('192', '17', '三门峡', '2');
INSERT INTO `region` VALUES ('193', '17', '南阳', '2');
INSERT INTO `region` VALUES ('194', '17', '商丘', '2');
INSERT INTO `region` VALUES ('195', '17', '信阳', '2');
INSERT INTO `region` VALUES ('196', '17', '周口', '2');
INSERT INTO `region` VALUES ('197', '17', '驻马店', '2');
INSERT INTO `region` VALUES ('198', '18', '武汉', '2');
INSERT INTO `region` VALUES ('199', '18', '黄石', '2');
INSERT INTO `region` VALUES ('200', '18', '襄樊', '2');
INSERT INTO `region` VALUES ('201', '18', '十堰', '2');
INSERT INTO `region` VALUES ('202', '18', '荆州', '2');
INSERT INTO `region` VALUES ('203', '18', '宜昌', '2');
INSERT INTO `region` VALUES ('204', '18', '荆门', '2');
INSERT INTO `region` VALUES ('205', '18', '鄂州', '2');
INSERT INTO `region` VALUES ('206', '18', '孝感', '2');
INSERT INTO `region` VALUES ('207', '18', '黄冈', '2');
INSERT INTO `region` VALUES ('208', '18', '咸宁', '2');
INSERT INTO `region` VALUES ('209', '18', '随州', '2');
INSERT INTO `region` VALUES ('210', '18', '恩施', '2');
INSERT INTO `region` VALUES ('211', '19', '长沙', '2');
INSERT INTO `region` VALUES ('212', '19', '株洲', '2');
INSERT INTO `region` VALUES ('213', '19', '湘潭', '2');
INSERT INTO `region` VALUES ('214', '19', '衡阳', '2');
INSERT INTO `region` VALUES ('215', '19', '邵阳', '2');
INSERT INTO `region` VALUES ('216', '19', '岳阳', '2');
INSERT INTO `region` VALUES ('217', '19', '常德', '2');
INSERT INTO `region` VALUES ('218', '19', '张家界', '2');
INSERT INTO `region` VALUES ('219', '19', '溢阳', '2');
INSERT INTO `region` VALUES ('220', '19', '郴州', '2');
INSERT INTO `region` VALUES ('221', '19', '永州', '2');
INSERT INTO `region` VALUES ('222', '19', '怀化', '2');
INSERT INTO `region` VALUES ('223', '19', '娄底', '2');
INSERT INTO `region` VALUES ('224', '19', '湘西', '2');
INSERT INTO `region` VALUES ('225', '20', '广州', '2');
INSERT INTO `region` VALUES ('226', '20', '深圳', '2');
INSERT INTO `region` VALUES ('227', '20', '珠海', '2');
INSERT INTO `region` VALUES ('228', '20', '汕头', '2');
INSERT INTO `region` VALUES ('229', '20', '韶关', '2');
INSERT INTO `region` VALUES ('230', '20', '佛山', '2');
INSERT INTO `region` VALUES ('231', '20', '江门', '2');
INSERT INTO `region` VALUES ('232', '20', '湛江', '2');
INSERT INTO `region` VALUES ('233', '20', '茂名', '2');
INSERT INTO `region` VALUES ('234', '20', '肇庆', '2');
INSERT INTO `region` VALUES ('235', '20', '惠州', '2');
INSERT INTO `region` VALUES ('236', '20', '梅州', '2');
INSERT INTO `region` VALUES ('237', '20', '汕尾', '2');
INSERT INTO `region` VALUES ('238', '20', '河源', '2');
INSERT INTO `region` VALUES ('239', '20', '阳江', '2');
INSERT INTO `region` VALUES ('240', '20', '清远', '2');
INSERT INTO `region` VALUES ('241', '20', '东莞', '2');
INSERT INTO `region` VALUES ('242', '20', '中山', '2');
INSERT INTO `region` VALUES ('243', '20', '潮州', '2');
INSERT INTO `region` VALUES ('244', '20', '揭阳', '2');
INSERT INTO `region` VALUES ('245', '20', '云浮', '2');
INSERT INTO `region` VALUES ('246', '21', '南宁', '2');
INSERT INTO `region` VALUES ('247', '21', '柳州', '2');
INSERT INTO `region` VALUES ('248', '21', '桂林', '2');
INSERT INTO `region` VALUES ('249', '21', '梧州', '2');
INSERT INTO `region` VALUES ('250', '21', '北海', '2');
INSERT INTO `region` VALUES ('251', '21', '防城港', '2');
INSERT INTO `region` VALUES ('252', '21', '钦州', '2');
INSERT INTO `region` VALUES ('253', '21', '贵港', '2');
INSERT INTO `region` VALUES ('254', '21', '玉林', '2');
INSERT INTO `region` VALUES ('255', '21', '百色', '2');
INSERT INTO `region` VALUES ('256', '21', '贺州', '2');
INSERT INTO `region` VALUES ('257', '21', '河池', '2');
INSERT INTO `region` VALUES ('258', '21', '来宾', '2');
INSERT INTO `region` VALUES ('259', '21', '崇左', '2');
INSERT INTO `region` VALUES ('260', '22', '白沙黎族自治县', '2');
INSERT INTO `region` VALUES ('261', '22', '西沙群岛', '2');
INSERT INTO `region` VALUES ('262', '22', '儋州', '2');
INSERT INTO `region` VALUES ('263', '22', '屯昌县', '2');
INSERT INTO `region` VALUES ('264', '22', '安定县', '2');
INSERT INTO `region` VALUES ('265', '22', '琼中黎族苗族自治县', '2');
INSERT INTO `region` VALUES ('266', '22', '昌江黎族自治县', '2');
INSERT INTO `region` VALUES ('267', '22', '东方', '2');
INSERT INTO `region` VALUES ('268', '22', '三亚', '2');
INSERT INTO `region` VALUES ('269', '22', '中沙群岛的岛礁及其海域', '2');
INSERT INTO `region` VALUES ('270', '22', '琼海', '2');
INSERT INTO `region` VALUES ('271', '22', '澄迈县', '2');
INSERT INTO `region` VALUES ('272', '22', '五指山', '2');
INSERT INTO `region` VALUES ('273', '22', '海口', '2');
INSERT INTO `region` VALUES ('274', '22', '文昌', '2');
INSERT INTO `region` VALUES ('275', '22', '陵水黎族自治县', '2');
INSERT INTO `region` VALUES ('276', '22', '保亭黎族苗族自治县', '2');
INSERT INTO `region` VALUES ('277', '22', '南沙群岛', '2');
INSERT INTO `region` VALUES ('278', '22', '乐东黎族自治县', '2');
INSERT INTO `region` VALUES ('279', '22', '临高县', '2');
INSERT INTO `region` VALUES ('280', '22', '万宁', '2');
INSERT INTO `region` VALUES ('281', '23', '成都', '2');
INSERT INTO `region` VALUES ('282', '23', '自贡', '2');
INSERT INTO `region` VALUES ('283', '23', '攀枝花', '2');
INSERT INTO `region` VALUES ('284', '23', '泸州', '2');
INSERT INTO `region` VALUES ('285', '23', '德阳', '2');
INSERT INTO `region` VALUES ('286', '23', '绵阳', '2');
INSERT INTO `region` VALUES ('287', '23', '广元', '2');
INSERT INTO `region` VALUES ('288', '23', '遂宁', '2');
INSERT INTO `region` VALUES ('289', '23', '内江', '2');
INSERT INTO `region` VALUES ('290', '23', '乐山', '2');
INSERT INTO `region` VALUES ('291', '23', '南充', '2');
INSERT INTO `region` VALUES ('292', '23', '宜宾', '2');
INSERT INTO `region` VALUES ('293', '23', '广安', '2');
INSERT INTO `region` VALUES ('294', '23', '达州', '2');
INSERT INTO `region` VALUES ('295', '23', '眉山', '2');
INSERT INTO `region` VALUES ('296', '23', '雅安', '2');
INSERT INTO `region` VALUES ('297', '23', '巴中', '2');
INSERT INTO `region` VALUES ('298', '23', '资阳', '2');
INSERT INTO `region` VALUES ('299', '23', '阿坝', '2');
INSERT INTO `region` VALUES ('300', '23', '甘孜', '2');
INSERT INTO `region` VALUES ('301', '23', '凉山', '2');
INSERT INTO `region` VALUES ('302', '24', '贵阳', '2');
INSERT INTO `region` VALUES ('303', '24', '六盘水', '2');
INSERT INTO `region` VALUES ('304', '24', '遵义', '2');
INSERT INTO `region` VALUES ('305', '24', '安顺', '2');
INSERT INTO `region` VALUES ('306', '24', '铜仁', '2');
INSERT INTO `region` VALUES ('307', '24', '毕节', '2');
INSERT INTO `region` VALUES ('308', '24', '黔西南', '2');
INSERT INTO `region` VALUES ('309', '24', '黔东南', '2');
INSERT INTO `region` VALUES ('310', '24', '黔南', '2');
INSERT INTO `region` VALUES ('311', '25', '昆明', '2');
INSERT INTO `region` VALUES ('312', '25', '曲靖', '2');
INSERT INTO `region` VALUES ('313', '25', '玉溪', '2');
INSERT INTO `region` VALUES ('314', '25', '保山', '2');
INSERT INTO `region` VALUES ('315', '25', '昭通', '2');
INSERT INTO `region` VALUES ('316', '25', '丽江', '2');
INSERT INTO `region` VALUES ('317', '25', '普洱', '2');
INSERT INTO `region` VALUES ('318', '25', '临沧', '2');
INSERT INTO `region` VALUES ('319', '25', '文山', '2');
INSERT INTO `region` VALUES ('320', '25', '红河', '2');
INSERT INTO `region` VALUES ('321', '25', '西双版纳', '2');
INSERT INTO `region` VALUES ('322', '25', '楚雄', '2');
INSERT INTO `region` VALUES ('323', '25', '大理', '2');
INSERT INTO `region` VALUES ('324', '25', '德宏', '2');
INSERT INTO `region` VALUES ('325', '25', '怒江', '2');
INSERT INTO `region` VALUES ('326', '25', '迪庆', '2');
INSERT INTO `region` VALUES ('327', '26', '拉萨', '2');
INSERT INTO `region` VALUES ('328', '26', '昌都', '2');
INSERT INTO `region` VALUES ('329', '26', '山南', '2');
INSERT INTO `region` VALUES ('330', '26', '日喀则', '2');
INSERT INTO `region` VALUES ('331', '26', '那曲', '2');
INSERT INTO `region` VALUES ('332', '26', '阿里', '2');
INSERT INTO `region` VALUES ('333', '26', '林芝', '2');
INSERT INTO `region` VALUES ('334', '27', '西安', '2');
INSERT INTO `region` VALUES ('335', '27', '铜川', '2');
INSERT INTO `region` VALUES ('336', '27', '宝鸡', '2');
INSERT INTO `region` VALUES ('337', '27', '咸阳', '2');
INSERT INTO `region` VALUES ('338', '27', '渭南', '2');
INSERT INTO `region` VALUES ('339', '27', '延安', '2');
INSERT INTO `region` VALUES ('340', '27', '汉中', '2');
INSERT INTO `region` VALUES ('341', '27', '榆林', '2');
INSERT INTO `region` VALUES ('342', '27', '安康', '2');
INSERT INTO `region` VALUES ('343', '27', '商洛', '2');
INSERT INTO `region` VALUES ('344', '28', '兰州', '2');
INSERT INTO `region` VALUES ('345', '28', '嘉峪关', '2');
INSERT INTO `region` VALUES ('346', '28', '金昌', '2');
INSERT INTO `region` VALUES ('347', '28', '白银', '2');
INSERT INTO `region` VALUES ('348', '28', '天水', '2');
INSERT INTO `region` VALUES ('349', '28', '武威', '2');
INSERT INTO `region` VALUES ('350', '28', '张掖', '2');
INSERT INTO `region` VALUES ('351', '28', '平凉', '2');
INSERT INTO `region` VALUES ('352', '28', '酒泉', '2');
INSERT INTO `region` VALUES ('353', '28', '庆阳', '2');
INSERT INTO `region` VALUES ('354', '28', '定西', '2');
INSERT INTO `region` VALUES ('355', '28', '陇南', '2');
INSERT INTO `region` VALUES ('356', '28', '临夏', '2');
INSERT INTO `region` VALUES ('357', '28', '甘南', '2');
INSERT INTO `region` VALUES ('358', '29', '西宁', '2');
INSERT INTO `region` VALUES ('359', '29', '海东', '2');
INSERT INTO `region` VALUES ('360', '29', '海北', '2');
INSERT INTO `region` VALUES ('361', '29', '黄南', '2');
INSERT INTO `region` VALUES ('362', '29', '海南', '2');
INSERT INTO `region` VALUES ('363', '29', '果洛', '2');
INSERT INTO `region` VALUES ('364', '29', '玉树', '2');
INSERT INTO `region` VALUES ('365', '29', '海西', '2');
INSERT INTO `region` VALUES ('366', '30', '银川', '2');
INSERT INTO `region` VALUES ('367', '30', '石嘴山', '2');
INSERT INTO `region` VALUES ('368', '30', '吴忠', '2');
INSERT INTO `region` VALUES ('369', '30', '固原', '2');
INSERT INTO `region` VALUES ('370', '30', '中卫', '2');
INSERT INTO `region` VALUES ('371', '31', '乌鲁木齐', '2');
INSERT INTO `region` VALUES ('372', '31', '克拉玛依', '2');
INSERT INTO `region` VALUES ('373', '31', '吐鲁番', '2');
INSERT INTO `region` VALUES ('374', '31', '哈密', '2');
INSERT INTO `region` VALUES ('375', '31', '和田', '2');
INSERT INTO `region` VALUES ('376', '31', '阿克苏', '2');
INSERT INTO `region` VALUES ('377', '31', '喀什', '2');
INSERT INTO `region` VALUES ('378', '31', '克孜勒苏柯尔克孜', '2');
INSERT INTO `region` VALUES ('379', '31', '巴音郭楞蒙古', '2');
INSERT INTO `region` VALUES ('380', '31', '昌吉', '2');
INSERT INTO `region` VALUES ('381', '31', '博尔塔拉蒙古', '2');
INSERT INTO `region` VALUES ('382', '31', '伊犁哈萨克', '2');
INSERT INTO `region` VALUES ('383', '31', '塔城', '2');
INSERT INTO `region` VALUES ('384', '31', '阿勒泰', '2');
INSERT INTO `region` VALUES ('385', '32', '台北', '2');
INSERT INTO `region` VALUES ('386', '32', '高雄', '2');
INSERT INTO `region` VALUES ('387', '32', '基隆', '2');
INSERT INTO `region` VALUES ('388', '32', '台中', '2');
INSERT INTO `region` VALUES ('389', '32', '台南', '2');
INSERT INTO `region` VALUES ('390', '32', '新竹', '2');
INSERT INTO `region` VALUES ('391', '33', '香港', '2');
INSERT INTO `region` VALUES ('392', '33', '澳门', '2');

-- ----------------------------
-- Table structure for `regulation`
-- ----------------------------
DROP TABLE IF EXISTS `regulation`;
CREATE TABLE `regulation` (
  `regId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issueDate` datetime DEFAULT NULL,
  `issueDep` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issueDepId` bigint(20) DEFAULT NULL,
  `issueFullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issueUserId` bigint(20) DEFAULT NULL,
  `keywords` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recDepIds` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recDeps` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recUserIds` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recUsers` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `proTypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`regId`),
  UNIQUE KEY `regId` (`regId`),
  KEY `FKFE5EEBAA7B33B268` (`proTypeId`),
  KEY `FKFE5EEBAA8FF3BBDE` (`proTypeId`),
  CONSTRAINT `FKFE5EEBAA8FF3BBDE` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FKFE5EEBAA7B33B268` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of regulation
-- ----------------------------

-- ----------------------------
-- Table structure for `reg_attach`
-- ----------------------------
DROP TABLE IF EXISTS `reg_attach`;
CREATE TABLE `reg_attach` (
  `regId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`regId`,`fileId`),
  KEY `FK60DC24507C851A4E` (`regId`),
  KEY `FK60DC2450D23E98A1` (`fileId`),
  KEY `FK60DC2450C7830A18` (`regId`),
  KEY `FK60DC2450E6FEA217` (`fileId`),
  CONSTRAINT `FK60DC2450E6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK60DC24507C851A4E` FOREIGN KEY (`regId`) REFERENCES `regulation` (`regId`),
  CONSTRAINT `FK60DC2450C7830A18` FOREIGN KEY (`regId`) REFERENCES `regulation` (`regId`),
  CONSTRAINT `FK60DC2450D23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of reg_attach
-- ----------------------------

-- ----------------------------
-- Table structure for `report_param`
-- ----------------------------
DROP TABLE IF EXISTS `report_param`;
CREATE TABLE `report_param` (
  `paramId` bigint(20) NOT NULL AUTO_INCREMENT,
  `defaultVal` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paramKey` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `paramName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `paramType` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `paramTypeStr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` int(11) NOT NULL,
  `reportId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paramId`),
  UNIQUE KEY `paramId` (`paramId`),
  KEY `FK383119C2B808FCC6` (`reportId`),
  KEY `FK383119C2F21B5B3C` (`reportId`),
  CONSTRAINT `FK383119C2F21B5B3C` FOREIGN KEY (`reportId`) REFERENCES `report_template` (`reportId`),
  CONSTRAINT `FK383119C2B808FCC6` FOREIGN KEY (`reportId`) REFERENCES `report_template` (`reportId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of report_param
-- ----------------------------

-- ----------------------------
-- Table structure for `report_template`
-- ----------------------------
DROP TABLE IF EXISTS `report_template`;
CREATE TABLE `report_template` (
  `reportId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `descp` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `isDefaultIn` smallint(6) DEFAULT NULL,
  `reportKey` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reportLocation` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`reportId`),
  UNIQUE KEY `reportId` (`reportId`),
  UNIQUE KEY `reportKey` (`reportKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of report_template
-- ----------------------------

-- ----------------------------
-- Table structure for `resume`
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `resumeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthPlace` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `eduCollege` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eduDegree` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eduMajor` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `hobby` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `memo` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nationality` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `party` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectCase` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `race` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  `registor` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `religion` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startWorkDate` datetime DEFAULT NULL,
  `status` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trainCase` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `workCase` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zip` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`resumeId`),
  UNIQUE KEY `resumeId` (`resumeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resume
-- ----------------------------

-- ----------------------------
-- Table structure for `resume_file`
-- ----------------------------
DROP TABLE IF EXISTS `resume_file`;
CREATE TABLE `resume_file` (
  `resumeId` bigint(20) NOT NULL,
  `fileId` bigint(20) NOT NULL,
  PRIMARY KEY (`resumeId`,`fileId`),
  KEY `FKFB43FA4ED23E98A1` (`fileId`),
  KEY `FKFB43FA4EF0900C56` (`resumeId`),
  KEY `FKFB43FA4EE6FEA217` (`fileId`),
  KEY `FKFB43FA4ED881BA0` (`resumeId`),
  CONSTRAINT `FKFB43FA4ED881BA0` FOREIGN KEY (`resumeId`) REFERENCES `resume` (`resumeId`),
  CONSTRAINT `FKFB43FA4ED23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKFB43FA4EE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FKFB43FA4EF0900C56` FOREIGN KEY (`resumeId`) REFERENCES `resume` (`resumeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resume_file
-- ----------------------------

-- ----------------------------
-- Table structure for `role_fun`
-- ----------------------------
DROP TABLE IF EXISTS `role_fun`;
CREATE TABLE `role_fun` (
  `roleId` bigint(20) NOT NULL,
  `functionId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`functionId`),
  KEY `FKF0211236DAC48F03` (`functionId`),
  KEY `FKF02112364B10D4BF` (`roleId`),
  KEY `FKF02112365E05B44D` (`functionId`),
  KEY `FKF021123637C2F09` (`roleId`),
  CONSTRAINT `FKF021123637C2F09` FOREIGN KEY (`roleId`) REFERENCES `app_role` (`roleId`),
  CONSTRAINT `FKF02112364B10D4BF` FOREIGN KEY (`roleId`) REFERENCES `app_role` (`roleId`),
  CONSTRAINT `FKF02112365E05B44D` FOREIGN KEY (`functionId`) REFERENCES `app_function` (`functionId`),
  CONSTRAINT `FKF0211236DAC48F03` FOREIGN KEY (`functionId`) REFERENCES `app_function` (`functionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `roll_file`
-- ----------------------------
DROP TABLE IF EXISTS `roll_file`;
CREATE TABLE `roll_file` (
  `rollFileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `afNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `archStatus` smallint(6) DEFAULT NULL,
  `catNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `creatorName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dutyPerson` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `fileNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `fileTime` datetime DEFAULT NULL,
  `keyWords` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notes` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `openStyle` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pageNo` int(11) DEFAULT NULL,
  `pageNums` int(11) DEFAULT NULL,
  `rollNo` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `secretLevel` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seqNo` int(11) DEFAULT NULL,
  `tidyName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tidyTime` datetime DEFAULT NULL,
  `timeLimit` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rollId` bigint(20) DEFAULT NULL,
  `proTypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rollFileId`),
  UNIQUE KEY `rollFileId` (`rollFileId`),
  KEY `FK1FF2F3FE90FF34ED` (`rollId`),
  KEY `FK1FF2F3FE7B33B268` (`proTypeId`),
  KEY `FK1FF2F3FEB7FA6963` (`rollId`),
  KEY `FK1FF2F3FE8FF3BBDE` (`proTypeId`),
  CONSTRAINT `FK1FF2F3FE8FF3BBDE` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FK1FF2F3FE7B33B268` FOREIGN KEY (`proTypeId`) REFERENCES `global_type` (`proTypeId`),
  CONSTRAINT `FK1FF2F3FE90FF34ED` FOREIGN KEY (`rollId`) REFERENCES `arch_roll` (`rollId`),
  CONSTRAINT `FK1FF2F3FEB7FA6963` FOREIGN KEY (`rollId`) REFERENCES `arch_roll` (`rollId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of roll_file
-- ----------------------------
INSERT INTO `roll_file` VALUES ('1', null, '0', '001', '1', '2011-08-20 00:00:00', '超级管理员', '', '111', '111', '2011-08-20 00:00:00', '1', '1', '对内开放', null, null, null, '公开资料', null, null, null, '二年', null, '5', '17');

-- ----------------------------
-- Table structure for `roll_file_list`
-- ----------------------------
DROP TABLE IF EXISTS `roll_file_list`;
CREATE TABLE `roll_file_list` (
  `listId` bigint(20) NOT NULL AUTO_INCREMENT,
  `downLoads` int(11) DEFAULT NULL,
  `shortDesc` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sn` int(11) DEFAULT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  `rollFileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`listId`),
  UNIQUE KEY `listId` (`listId`),
  KEY `FK67C9443F9808E8F` (`rollFileId`),
  KEY `FK67C9443FD23E98A1` (`fileId`),
  KEY `FK67C9443F307BC305` (`rollFileId`),
  KEY `FK67C9443FE6FEA217` (`fileId`),
  CONSTRAINT `FK67C9443FE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK67C9443F307BC305` FOREIGN KEY (`rollFileId`) REFERENCES `roll_file` (`rollFileId`),
  CONSTRAINT `FK67C9443F9808E8F` FOREIGN KEY (`rollFileId`) REFERENCES `roll_file` (`rollFileId`),
  CONSTRAINT `FK67C9443FD23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of roll_file_list
-- ----------------------------
INSERT INTO `roll_file_list` VALUES ('1', '0', '', '1', '1', '1');

-- ----------------------------
-- Table structure for `salary_item`
-- ----------------------------
DROP TABLE IF EXISTS `salary_item`;
CREATE TABLE `salary_item` (
  `salaryItemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `defaultVal` decimal(18,0) NOT NULL,
  `itemName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`salaryItemId`),
  UNIQUE KEY `salaryItemId` (`salaryItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of salary_item
-- ----------------------------

-- ----------------------------
-- Table structure for `salary_payoff`
-- ----------------------------
DROP TABLE IF EXISTS `salary_payoff`;
CREATE TABLE `salary_payoff` (
  `recordId` bigint(20) NOT NULL AUTO_INCREMENT,
  `achieveAmount` decimal(18,0) DEFAULT NULL,
  `acutalAmount` decimal(18,0) DEFAULT NULL,
  `checkName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkOpinion` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkStatus` smallint(6) DEFAULT NULL,
  `checkTime` datetime DEFAULT NULL,
  `deductAmount` decimal(18,0) NOT NULL,
  `deductDesc` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `encourageAmount` decimal(18,0) NOT NULL,
  `encourageDesc` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `endTime` datetime NOT NULL,
  `fullname` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `idNo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `memo` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profileNo` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regTime` datetime NOT NULL,
  `register` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `standAmount` decimal(18,0) NOT NULL,
  `standardId` bigint(20) NOT NULL,
  `startTime` datetime NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`recordId`),
  UNIQUE KEY `recordId` (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of salary_payoff
-- ----------------------------

-- ----------------------------
-- Table structure for `seal`
-- ----------------------------
DROP TABLE IF EXISTS `seal`;
CREATE TABLE `seal` (
  `sealId` bigint(20) NOT NULL AUTO_INCREMENT,
  `belongId` bigint(20) DEFAULT NULL,
  `belongName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `sealName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `sealPath` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fileId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sealId`),
  UNIQUE KEY `sealId` (`sealId`),
  KEY `FK35CDFDD23E98A1` (`fileId`),
  KEY `FK35CDFDE6FEA217` (`fileId`),
  CONSTRAINT `FK35CDFDE6FEA217` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`),
  CONSTRAINT `FK35CDFDD23E98A1` FOREIGN KEY (`fileId`) REFERENCES `file_attach` (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of seal
-- ----------------------------

-- ----------------------------
-- Table structure for `short_message`
-- ----------------------------
DROP TABLE IF EXISTS `short_message`;
CREATE TABLE `short_message` (
  `messageId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `msgType` smallint(6) NOT NULL,
  `sendTime` datetime NOT NULL,
  `sender` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `senderId` bigint(20) NOT NULL,
  PRIMARY KEY (`messageId`),
  UNIQUE KEY `messageId` (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of short_message
-- ----------------------------

-- ----------------------------
-- Table structure for `sms_history`
-- ----------------------------
DROP TABLE IF EXISTS `sms_history`;
CREATE TABLE `sms_history` (
  `smsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(128) DEFAULT NULL,
  `recipients` varchar(128) DEFAULT NULL,
  `sendTime` datetime NOT NULL,
  `smsContent` varchar(1024) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`smsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_history
-- ----------------------------

-- ----------------------------
-- Table structure for `sms_mobile`
-- ----------------------------
DROP TABLE IF EXISTS `sms_mobile`;
CREATE TABLE `sms_mobile` (
  `smsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(128) DEFAULT NULL,
  `recipients` varchar(128) DEFAULT NULL,
  `sendTime` datetime NOT NULL,
  `smsContent` varchar(1024) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`smsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_mobile
-- ----------------------------

-- ----------------------------
-- Table structure for `stand_salary`
-- ----------------------------
DROP TABLE IF EXISTS `stand_salary`;
CREATE TABLE `stand_salary` (
  `standardId` bigint(20) NOT NULL AUTO_INCREMENT,
  `checkName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkOpinion` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkTime` datetime DEFAULT NULL,
  `framer` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `memo` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyName` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `setdownTime` datetime DEFAULT NULL,
  `standardName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `standardNo` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) NOT NULL,
  `totalMoney` decimal(18,0) NOT NULL,
  PRIMARY KEY (`standardId`),
  UNIQUE KEY `standardId` (`standardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stand_salary
-- ----------------------------

-- ----------------------------
-- Table structure for `stand_salary_item`
-- ----------------------------
DROP TABLE IF EXISTS `stand_salary_item`;
CREATE TABLE `stand_salary_item` (
  `itemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(18,0) NOT NULL,
  `itemName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `salaryItemId` bigint(20) DEFAULT NULL,
  `standardId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  UNIQUE KEY `itemId` (`itemId`),
  KEY `FK5020097F13F41857` (`standardId`),
  KEY `FK5020097F68F4074D` (`standardId`),
  CONSTRAINT `FK5020097F68F4074D` FOREIGN KEY (`standardId`) REFERENCES `stand_salary` (`standardId`),
  CONSTRAINT `FK5020097F13F41857` FOREIGN KEY (`standardId`) REFERENCES `stand_salary` (`standardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stand_salary_item
-- ----------------------------

-- ----------------------------
-- Table structure for `suggest_box`
-- ----------------------------
DROP TABLE IF EXISTS `suggest_box`;
CREATE TABLE `suggest_box` (
  `boxId` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(4000) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isOpen` smallint(6) DEFAULT NULL,
  `phone` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `queryPwd` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recFullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recUid` bigint(20) DEFAULT NULL,
  `replyContent` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `replyFullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `replyId` bigint(20) DEFAULT NULL,
  `replyTime` datetime DEFAULT NULL,
  `senderFullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `senderId` bigint(20) DEFAULT NULL,
  `senderIp` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `subject` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`boxId`),
  UNIQUE KEY `boxId` (`boxId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of suggest_box
-- ----------------------------

-- ----------------------------
-- Table structure for `system_log`
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `exeOperation` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `userId` bigint(20) NOT NULL,
  `username` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`logId`),
  UNIQUE KEY `logId` (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('1', '2012-09-03 19:49:44', '添加或保存用户信息', '1', '超级管理员');
INSERT INTO `system_log` VALUES ('2', '2012-09-03 19:50:27', '添加或保存用户信息', '1', '超级管理员');

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `configId` bigint(20) NOT NULL AUTO_INCREMENT,
  `configDesc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `configKey` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `configName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `dataType` smallint(6) NOT NULL,
  `dataValue` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeKey` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`configId`),
  UNIQUE KEY `configId` (`configId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主机IP', 'host', '主机Host', '1', '192.168.1.11', 'mailConfig', '系统邮件配置');
INSERT INTO `sys_config` VALUES ('2', '邮件发送的邮箱用户名', 'username', '用户名', '1', 'lyy', 'mailConfig', '系统邮件配置');
INSERT INTO `sys_config` VALUES ('3', '邮件发送的邮箱密码', 'password', '密码', '1', '111111', 'mailConfig', '系统邮件配置');
INSERT INTO `sys_config` VALUES ('4', '发送人的邮箱或用户名', 'from', '来自', '1', 'lyy', 'mailConfig', '系统邮件配置');
INSERT INTO `sys_config` VALUES ('5', '当库存产生警报时，接收消息的人员', 'goodsStockUser', '用户帐号', '1', 'admin', 'adminConfig', '行政管理配置');
INSERT INTO `sys_config` VALUES ('6', '登录时是否需要验证码', 'codeConfig', '验证码', '2', '2', 'codeConfig', '验证码配置');
INSERT INTO `sys_config` VALUES ('7', '意见接收人ID', 'suggestId', '意见接收人ID', '1', '1', 'suggestConfig', '意见箱配置');
INSERT INTO `sys_config` VALUES ('8', '意见接收人', 'suggestName', '意见接收人', '1', '管理员', 'suggestConfig', '意见箱配置');
INSERT INTO `sys_config` VALUES ('9', '新闻评论配置', 'commentConfig', '新闻评论配置', '2', '1', 'commentConfig', '新闻评论配置');

-- ----------------------------
-- Table structure for `user_agent`
-- ----------------------------
DROP TABLE IF EXISTS `user_agent`;
CREATE TABLE `user_agent` (
  `grantId` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grantFullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grantTitle` int(11) DEFAULT NULL,
  `grantUId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`grantId`),
  UNIQUE KEY `grantId` (`grantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_agent
-- ----------------------------

-- ----------------------------
-- Table structure for `user_contract`
-- ----------------------------
DROP TABLE IF EXISTS `user_contract`;
CREATE TABLE `user_contract` (
  `contractId` bigint(20) NOT NULL AUTO_INCREMENT,
  `breakBurden` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contractNo` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `contractType` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expireDate` datetime DEFAULT NULL,
  `fullname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isCompeted` int(11) DEFAULT NULL,
  `isSecret` int(11) DEFAULT NULL,
  `otherItems` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signDate` datetime DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `timeLimit` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`contractId`),
  UNIQUE KEY `contractId` (`contractId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_contract
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`userId`),
  KEY `FK143BF46A4B10D4BF` (`roleId`),
  KEY `FK143BF46A50662A29` (`userId`),
  KEY `FK143BF46A37C2F09` (`roleId`),
  KEY `FK143BF46A8D18473` (`userId`),
  CONSTRAINT `FK143BF46A8D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK143BF46A37C2F09` FOREIGN KEY (`roleId`) REFERENCES `app_role` (`roleId`),
  CONSTRAINT `FK143BF46A4B10D4BF` FOREIGN KEY (`roleId`) REFERENCES `app_role` (`roleId`),
  CONSTRAINT `FK143BF46A50662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '-1');

-- ----------------------------
-- Table structure for `user_sub`
-- ----------------------------
DROP TABLE IF EXISTS `user_sub`;
CREATE TABLE `user_sub` (
  `subId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `subUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`subId`),
  UNIQUE KEY `subId` (`subId`),
  KEY `FKF022FBCCA5303869` (`subUserId`),
  KEY `FKF022FBCC5D9B92B3` (`subUserId`),
  CONSTRAINT `FKF022FBCC5D9B92B3` FOREIGN KEY (`subUserId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FKF022FBCCA5303869` FOREIGN KEY (`subUserId`) REFERENCES `app_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_sub
-- ----------------------------
INSERT INTO `user_sub` VALUES ('1', '4', '3');

-- ----------------------------
-- Table structure for `work_plan`
-- ----------------------------
DROP TABLE IF EXISTS `work_plan`;
CREATE TABLE `work_plan` (
  `planId` bigint(20) NOT NULL AUTO_INCREMENT,
  `endTime` datetime NOT NULL,
  `icon` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isPersonal` smallint(6) NOT NULL,
  `issueScope` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `participants` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `planContent` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `planName` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `principal` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` datetime NOT NULL,
  `status` smallint(6) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `typeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`planId`),
  UNIQUE KEY `planId` (`planId`),
  KEY `FK40FE3F97C6C4B02B` (`typeId`),
  KEY `FK40FE3F9750662A29` (`userId`),
  KEY `FK40FE3F97EDBFE4A1` (`typeId`),
  KEY `FK40FE3F978D18473` (`userId`),
  CONSTRAINT `FK40FE3F978D18473` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK40FE3F9750662A29` FOREIGN KEY (`userId`) REFERENCES `app_user` (`userId`),
  CONSTRAINT `FK40FE3F97C6C4B02B` FOREIGN KEY (`typeId`) REFERENCES `plan_type` (`typeId`),
  CONSTRAINT `FK40FE3F97EDBFE4A1` FOREIGN KEY (`typeId`) REFERENCES `plan_type` (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of work_plan
-- ----------------------------
