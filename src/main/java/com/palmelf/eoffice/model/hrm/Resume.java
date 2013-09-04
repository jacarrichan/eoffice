package com.palmelf.eoffice.model.hrm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.palmelf.core.model.BaseModel;
import com.palmelf.eoffice.model.system.FileAttach;

@Entity
@Table(name = "resume")
public class Resume extends BaseModel {

	private static final long serialVersionUID = 1L;
	public static String PASS = "通过";
	public static String NOTPASS = "不通过";
	public static String READY_INTERVIEW = "准备安排面试";
	public static String PASS_INTERVIEW = "通过面试";
	private Long resumeId;
	private String fullname;
	private Integer age;
	private Date birthday;
	private String address;
	private String zip;
	private String sex;
	private String position;
	private String phone;
	private String mobile;
	private String email;
	private String hobby;
	private String religion;
	private String party;
	private String nationality;
	private String race;
	private String birthPlace;
	private String eduCollege;
	private String eduDegree;
	private String eduMajor;
	private Date startWorkDate;
	private String idNo;
	private String photo;
	private String status;
	private String memo;
	private String registor;
	private Date regTime;
	private String workCase;
	private String trainCase;
	private String projectCase;
	private Set<FileAttach> resumeFiles = new HashSet<FileAttach>();

	public Resume() {
	}

	public Resume(Long in_resumeId) {
		this.setResumeId(in_resumeId);
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "resumeId", unique = true, nullable = false)
	public Long getResumeId() {
		return this.resumeId;
	}

	public void setResumeId(Long resumeId) {
		this.resumeId = resumeId;
	}

	@Column(name = "fullname", nullable = false, length = 64)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "birthday", length = 19)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "address", length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip", length = 32)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "sex", length = 32)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "position", length = 64)
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

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "hobby", length = 256)
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "religion", length = 128)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "party", length = 128)
	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@Column(name = "nationality", length = 32)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "race", length = 32)
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

	@Column(name = "eduCollege", length = 128)
	public String getEduCollege() {
		return this.eduCollege;
	}

	public void setEduCollege(String eduCollege) {
		this.eduCollege = eduCollege;
	}

	@Column(name = "eduDegree", length = 128)
	public String getEduDegree() {
		return this.eduDegree;
	}

	public void setEduDegree(String eduDegree) {
		this.eduDegree = eduDegree;
	}

	@Column(name = "eduMajor", length = 128)
	public String getEduMajor() {
		return this.eduMajor;
	}

	public void setEduMajor(String eduMajor) {
		this.eduMajor = eduMajor;
	}

	@Column(name = "startWorkDate", length = 19)
	public Date getStartWorkDate() {
		return this.startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	@Column(name = "idNo", length = 64)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "photo", length = 128)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "status", length = 64)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "registor", length = 64)
	public String getRegistor() {
		return this.registor;
	}

	public void setRegistor(String registor) {
		this.registor = registor;
	}

	@Column(name = "regTime", length = 19)
	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@Column(name = "workCase")
	public String getWorkCase() {
		return this.workCase;
	}

	public void setWorkCase(String workCase) {
		this.workCase = workCase;
	}

	@Column(name = "trainCase")
	public String getTrainCase() {
		return this.trainCase;
	}

	public void setTrainCase(String trainCase) {
		this.trainCase = trainCase;
	}

	@Column(name = "projectCase")
	public String getProjectCase() {
		return this.projectCase;
	}

	public void setProjectCase(String projectCase) {
		this.projectCase = projectCase;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "resume_file", joinColumns = { @JoinColumn(name = "resumeId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "fileId", nullable = false, updatable = false) })
	public Set<FileAttach> getResumeFiles() {
		return this.resumeFiles;
	}

	public void setResumeFiles(Set<FileAttach> resumeFiles) {
		this.resumeFiles = resumeFiles;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Resume)) {
			return false;
		}
		Resume rhs = (Resume) object;
		return new EqualsBuilder().append(this.resumeId, rhs.resumeId).append(this.fullname, rhs.fullname)
				.append(this.age, rhs.age).append(this.birthday, rhs.birthday).append(this.address, rhs.address)
				.append(this.zip, rhs.zip).append(this.sex, rhs.sex).append(this.position, rhs.position)
				.append(this.phone, rhs.phone).append(this.mobile, rhs.mobile).append(this.email, rhs.email)
				.append(this.hobby, rhs.hobby).append(this.religion, rhs.religion).append(this.party, rhs.party)
				.append(this.nationality, rhs.nationality).append(this.race, rhs.race)
				.append(this.birthPlace, rhs.birthPlace).append(this.eduCollege, rhs.eduCollege)
				.append(this.eduDegree, rhs.eduDegree).append(this.eduMajor, rhs.eduMajor)
				.append(this.startWorkDate, rhs.startWorkDate).append(this.idNo, rhs.idNo)
				.append(this.photo, rhs.photo).append(this.status, rhs.status).append(this.memo, rhs.memo)
				.append(this.registor, rhs.registor).append(this.regTime, rhs.regTime)
				.append(this.workCase, rhs.workCase).append(this.trainCase, rhs.trainCase)
				.append(this.projectCase, rhs.projectCase).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.resumeId).append(this.fullname).append(this.age)
				.append(this.birthday).append(this.address).append(this.zip).append(this.sex).append(this.position)
				.append(this.phone).append(this.mobile).append(this.email).append(this.hobby).append(this.religion)
				.append(this.party).append(this.nationality).append(this.race).append(this.birthPlace)
				.append(this.eduCollege).append(this.eduDegree).append(this.eduMajor).append(this.startWorkDate)
				.append(this.idNo).append(this.photo).append(this.status).append(this.memo).append(this.registor)
				.append(this.regTime).append(this.workCase).append(this.trainCase).append(this.projectCase)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("resumeId", this.resumeId).append("fullname", this.fullname)
				.append("age", this.age).append("birthday", this.birthday).append("address", this.address)
				.append("zip", this.zip).append("sex", this.sex).append("position", this.position)
				.append("phone", this.phone).append("mobile", this.mobile).append("email", this.email)
				.append("hobby", this.hobby).append("religion", this.religion).append("party", this.party)
				.append("nationality", this.nationality).append("race", this.race)
				.append("birthPlace", this.birthPlace).append("eduCollege", this.eduCollege)
				.append("eduDegree", this.eduDegree).append("eduMajor", this.eduMajor)
				.append("startWorkDate", this.startWorkDate).append("idNo", this.idNo).append("photo", this.photo)
				.append("status", this.status).append("memo", this.memo).append("registor", this.registor)
				.append("regTime", this.regTime).append("workCase", this.workCase).append("trainCase", this.trainCase)
				.append("projectCase", this.projectCase).toString();
	}
}
