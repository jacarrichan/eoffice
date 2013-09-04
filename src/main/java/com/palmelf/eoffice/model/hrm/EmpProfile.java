package com.palmelf.eoffice.model.hrm;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;

@Entity
@Table(name = "emp_profile")
public class EmpProfile extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9018852711008368767L;
	public static short CHECK_FLAG_NONE = 0;
	public static short CHECK_FLAG_PASS = 1;
	public static short CHECK_FLAG_NOT_PASS = 2;

	public static short DELETE_FLAG_NOT = 0;
	public static short DELETE_FLAG_HAD = 1;
	private Long profileId;
	private String profileNo;
	private String fullname;
	private String address;
	private Date birthday;
	private String homeZip;
	private String sex;
	private String marriage;
	private String designation;
	private String position;
	private String phone;
	private String mobile;
	private String openBank;
	private String bankNo;
	private String qq;
	private String email;
	private String hobby;
	private String religion;
	private String party;
	private String nationality;
	private String race;
	private String birthPlace;
	private String eduDegree;
	private String eduMajor;
	private String eduCollege;
	private Date startWorkDate;
	private String eduCase;
	private String awardPunishCase;
	private String trainingCase;
	private String workCase;
	private String idCard;
	private String photo;
	private String standardMiNo;
	private BigDecimal standardMoney;
	private String standardName;
	private String creator;
	private Date createtime;
	private String checkName;
	private Date checktime;
	private Short approvalStatus;
	private String memo;
	private String depName;
	private Long depId;
	private Short delFlag;
	private String opprovalOpinion;
	private Long userId;
	private Long jobId;
	private StandSalary standSalary;

	public EmpProfile() {
	}

	public EmpProfile(Long in_profileId) {
		this.setProfileId(in_profileId);
	}
	@Transient
	public Long getStandardId() {
		return this.getStandSalary() == null ? null : this.getStandSalary().getStandardId();
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "profileId", unique = true, nullable = false)
	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Column(name = "jobId")
	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "standardId")
	public StandSalary getStandSalary() {
		return this.standSalary;
	}

	public void setStandSalary(StandSalary standSalary) {
		this.standSalary = standSalary;
	}

	@Column(name = "profileNo", nullable = false, length = 100)
	public String getProfileNo() {
		return this.profileNo;
	}

	public void setProfileNo(String profileNo) {
		this.profileNo = profileNo;
	}

	@Column(name = "fullname", nullable = false, length = 64)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "address", length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "birthday", length = 19)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "homeZip", length = 32)
	public String getHomeZip() {
		return this.homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	@Column(name = "sex", length = 32)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "marriage", length = 32)
	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	@Column(name = "designation", length = 64)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "position", length = 128)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "phone", length = 64)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile", length = 64)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "openBank", length = 100)
	public String getOpenBank() {
		return this.openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	@Column(name = "bankNo", length = 100)
	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "qq", length = 64)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "hobby", length = 300)
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "religion", length = 100)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "party", length = 100)
	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@Column(name = "nationality", length = 100)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "race", length = 100)
	public String getRace() {
		return this.race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Column(name = "birthPlace", length = 128)
	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(name = "eduDegree", length = 100)
	public String getEduDegree() {
		return this.eduDegree;
	}

	public void setEduDegree(String eduDegree) {
		this.eduDegree = eduDegree;
	}

	@Column(name = "eduMajor", length = 100)
	public String getEduMajor() {
		return this.eduMajor;
	}

	public void setEduMajor(String eduMajor) {
		this.eduMajor = eduMajor;
	}

	@Column(name = "eduCollege", length = 128)
	public String getEduCollege() {
		return this.eduCollege;
	}

	public void setEduCollege(String eduCollege) {
		this.eduCollege = eduCollege;
	}

	@Column(name = "startWorkDate", length = 19)
	public Date getStartWorkDate() {
		return this.startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	@Column(name = "eduCase", length = 2048)
	public String getEduCase() {
		return this.eduCase;
	}

	public void setEduCase(String eduCase) {
		this.eduCase = eduCase;
	}

	@Column(name = "awardPunishCase", length = 2048)
	public String getAwardPunishCase() {
		return this.awardPunishCase;
	}

	public void setAwardPunishCase(String awardPunishCase) {
		this.awardPunishCase = awardPunishCase;
	}

	@Column(name = "trainingCase", length = 2048)
	public String getTrainingCase() {
		return this.trainingCase;
	}

	public void setTrainingCase(String trainingCase) {
		this.trainingCase = trainingCase;
	}

	@Column(name = "workCase", length = 2048)
	public String getWorkCase() {
		return this.workCase;
	}

	public void setWorkCase(String workCase) {
		this.workCase = workCase;
	}

	@Column(name = "idCard", length = 64)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "photo", length = 128)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "standardMiNo", length = 100)
	public String getStandardMiNo() {
		return this.standardMiNo;
	}

	public void setStandardMiNo(String standardMiNo) {
		this.standardMiNo = standardMiNo;
	}

	@Column(name = "standardMoney", precision = 18)
	public BigDecimal getStandardMoney() {
		return this.standardMoney;
	}

	public void setStandardMoney(BigDecimal standardMoney) {
		this.standardMoney = standardMoney;
	}

	@Column(name = "standardName", length = 128)
	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	@Column(name = "creator", length = 64)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "createtime", length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "checkName", length = 128)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Column(name = "checktime", length = 19)
	public Date getChecktime() {
		return this.checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	@Column(name = "approvalStatus")
	public Short getApprovalStatus() {
		return this.approvalStatus;
	}

	public void setApprovalStatus(Short approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "depName", length = 100)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Column(name = "depId")
	public Long getDepId() {
		return this.depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	@Column(name = "delFlag", nullable = false)
	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "opprovalOpinion", length = 1024)
	public String getOpprovalOpinion() {
		return this.opprovalOpinion;
	}

	public void setOpprovalOpinion(String opprovalOpinion) {
		this.opprovalOpinion = opprovalOpinion;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setStandardId(Long aValue) {
		if (aValue == null) {
			this.standSalary = null;
		} else if (this.standSalary == null) {
			this.standSalary = new StandSalary(aValue);
			this.standSalary.setVersion(new Integer(0));
		} else {
			this.standSalary.setStandardId(aValue);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EmpProfile)) {
			return false;
		}
		EmpProfile rhs = (EmpProfile) object;
		return new EqualsBuilder().append(this.profileId, rhs.profileId).append(this.profileNo, rhs.profileNo)
				.append(this.fullname, rhs.fullname).append(this.address, rhs.address)
				.append(this.birthday, rhs.birthday).append(this.homeZip, rhs.homeZip).append(this.sex, rhs.sex)
				.append(this.marriage, rhs.marriage).append(this.designation, rhs.designation)
				.append(this.position, rhs.position).append(this.phone, rhs.phone).append(this.mobile, rhs.mobile)
				.append(this.openBank, rhs.openBank).append(this.bankNo, rhs.bankNo).append(this.qq, rhs.qq)
				.append(this.email, rhs.email).append(this.hobby, rhs.hobby).append(this.religion, rhs.religion)
				.append(this.party, rhs.party).append(this.nationality, rhs.nationality).append(this.race, rhs.race)
				.append(this.birthPlace, rhs.birthPlace).append(this.eduDegree, rhs.eduDegree)
				.append(this.eduMajor, rhs.eduMajor).append(this.eduCollege, rhs.eduCollege)
				.append(this.startWorkDate, rhs.startWorkDate).append(this.eduCase, rhs.eduCase)
				.append(this.awardPunishCase, rhs.awardPunishCase).append(this.trainingCase, rhs.trainingCase)
				.append(this.workCase, rhs.workCase).append(this.idCard, rhs.idCard).append(this.photo, rhs.photo)
				.append(this.standardMiNo, rhs.standardMiNo).append(this.standardMoney, rhs.standardMoney)
				.append(this.standardName, rhs.standardName).append(this.creator, rhs.creator)
				.append(this.createtime, rhs.createtime).append(this.checkName, rhs.checkName)
				.append(this.checktime, rhs.checktime).append(this.approvalStatus, rhs.approvalStatus)
				.append(this.opprovalOpinion, rhs.opprovalOpinion).append(this.memo, rhs.memo)
				.append(this.depName, rhs.depName).append(this.depId, rhs.depId).append(this.delFlag, rhs.delFlag)
				.append(this.userId, rhs.userId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.profileId).append(this.profileNo)
				.append(this.fullname).append(this.address).append(this.birthday).append(this.homeZip).append(this.sex)
				.append(this.marriage).append(this.designation).append(this.position).append(this.phone)
				.append(this.mobile).append(this.openBank).append(this.bankNo).append(this.qq).append(this.email)
				.append(this.hobby).append(this.religion).append(this.party).append(this.nationality).append(this.race)
				.append(this.birthPlace).append(this.eduDegree).append(this.eduMajor).append(this.eduCollege)
				.append(this.startWorkDate).append(this.eduCase).append(this.awardPunishCase).append(this.trainingCase)
				.append(this.workCase).append(this.idCard).append(this.photo).append(this.standardMiNo)
				.append(this.standardMoney).append(this.standardName).append(this.creator).append(this.createtime)
				.append(this.checkName).append(this.checktime).append(this.approvalStatus).append(this.memo)
				.append(this.depName).append(this.depId).append(this.delFlag).append(this.opprovalOpinion)
				.append(this.userId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("profileId", this.profileId).append("profileNo", this.profileNo)
				.append("fullname", this.fullname).append("address", this.address).append("birthday", this.birthday)
				.append("homeZip", this.homeZip).append("sex", this.sex).append("marriage", this.marriage)
				.append("designation", this.designation).append("position", this.position).append("phone", this.phone)
				.append("mobile", this.mobile).append("openBank", this.openBank).append("bankNo", this.bankNo)
				.append("qq", this.qq).append("email", this.email).append("hobby", this.hobby)
				.append("religion", this.religion).append("party", this.party).append("nationality", this.nationality)
				.append("race", this.race).append("birthPlace", this.birthPlace).append("eduDegree", this.eduDegree)
				.append("eduMajor", this.eduMajor).append("eduCollege", this.eduCollege)
				.append("startWorkDate", this.startWorkDate).append("eduCase", this.eduCase)
				.append("awardPunishCase", this.awardPunishCase).append("trainingCase", this.trainingCase)
				.append("workCase", this.workCase).append("idCard", this.idCard).append("photo", this.photo)
				.append("standardMiNo", this.standardMiNo).append("standardMoney", this.standardMoney)
				.append("standardName", this.standardName).append("creator", this.creator)
				.append("createtime", this.createtime).append("checkName", this.checkName)
				.append("checktime", this.checktime).append("approvalStatus", this.approvalStatus)
				.append("memo", this.memo).append("depName", this.depName).append("depId", this.depId)
				.append("delFlag", this.delFlag).append("opprovalOpinion", this.opprovalOpinion)
				.append("userId", this.userId).toString();
	}
}
