package com.icss.bean;

import java.util.Date;

public class TbCustomer {
	
	private Integer id;
	
	private String customerid;
	
    private String customername;

    private String sex;

    private String telephone;

    private String email;

    private String school;

    private String major;
    
    private String majortext;

    private String education;
    
    private String educationtext;

    private String operator;
    
    private String operatortext;

    private String collector;
    
    private String collectortext;

    private Date createtime;

    private String source;
    
    private String sourcetext;

    private String customertype;
    
    private String customertypetext;

    private String course;
    
    private String coursetext;

    private String jobobjective;
    
    private String jobobjectivetext;

    private Date birthday;

    private String placeofbirth;
    
    private String address;

    private String politicalstatus;
    
    private String politicalstatustext;

    private String nationality;
    
    private String nayionalitytext;

    private String maritalstatus;
    
    private String maritalstatustext;

    private String workservice;

    private String workexp;

    private String workplace;
    
    private String workplacetext;

    private String salary;
    
    private String salarytext;

    private String ecp;

    private String bank;

    private String bankno;

    private String memo;

    private String status;

    private String assigntype;

    private String classificationstandard;
    
    private String resumeurl;

    public TbCustomer() {
	}

	public TbCustomer(String customerid, String assigntype) {
		this.customerid = customerid;
		this.assigntype = assigntype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

    public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? "" : sex.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? "" : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? "" : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? "" : major.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? "" : education.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? "" : operator.trim();
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector == null ? "" : collector.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? "" : source.trim();
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype == null ? "" : customertype.trim();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? "" : course.trim();
    }

    public String getJobobjective() {
        return jobobjective;
    }

    public void setJobobjective(String jobobjective) {
        this.jobobjective = jobobjective == null ? "" : jobobjective.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth == null ? "" : placeofbirth.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPoliticalstatus() {
        return politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus == null ? "" : politicalstatus.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? "" : nationality.trim();
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus == null ? "" : maritalstatus.trim();
    }

    public String getWorkservice() {
        return workservice;
    }

    public void setWorkservice(String workservice) {
        this.workservice = workservice == null ? "" : workservice.trim();
    }

    public String getWorkexp() {
        return workexp;
    }

    public void setWorkexp(String workexp) {
        this.workexp = workexp == null ? "" : workexp.trim();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? "" : workplace.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? "" : salary.trim();
    }

    
    public String getEcp() {
        return ecp;
    }

    public void setEcp(String ecp) {
        this.ecp = ecp == null ? "" : ecp.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? "" : bank.trim();
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? "" : bankno.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? "" : memo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? "" : status.trim();
    }

    public String getAssigntype() {
        return assigntype;
    }

    public void setAssigntype(String assigntype) {
        this.assigntype = assigntype == null ? "" : assigntype.trim();
    }

    public String getClassificationstandard() {
        return classificationstandard;
    }

    public void setClassificationstandard(String classificationstandard) {
        this.classificationstandard = classificationstandard == null ? "" : classificationstandard.trim();
    }

	public String getMajortext() {
		return majortext;
	}

	public void setMajortext(String majortext) {
		this.majortext = majortext;
	}

	public String getEducationtext() {
		return educationtext;
	}

	public void setEducationtext(String educationtext) {
		this.educationtext = educationtext;
	}

	public String getOperatortext() {
		return operatortext;
	}

	public void setOperatortext(String operatortext) {
		this.operatortext = operatortext;
	}

	public String getCollectortext() {
		return collectortext;
	}

	public void setCollectortext(String collectortext) {
		this.collectortext = collectortext;
	}

	public String getSourcetext() {
		return sourcetext;
	}

	public void setSourcetext(String sourcetext) {
		this.sourcetext = sourcetext;
	}

	public String getCustomertypetext() {
		return customertypetext;
	}

	public void setCustomertypetext(String customertypetext) {
		this.customertypetext = customertypetext;
	}

	public String getCoursetext() {
		return coursetext;
	}

	public void setCoursetext(String coursetext) {
		this.coursetext = coursetext;
	}

	public String getJobobjectivetext() {
		return jobobjectivetext;
	}

	public void setJobobjectivetext(String jobobjectivetext) {
		this.jobobjectivetext = jobobjectivetext;
	}

	public String getPoliticalstatustext() {
		return politicalstatustext;
	}

	public void setPoliticalstatustext(String politicalstatustext) {
		this.politicalstatustext = politicalstatustext;
	}

	public String getNayionalitytext() {
		return nayionalitytext;
	}

	public void setNayionalitytext(String nayionalitytext) {
		this.nayionalitytext = nayionalitytext;
	}

	public String getMaritalstatustext() {
		return maritalstatustext;
	}

	public void setMaritalstatustext(String maritalstatustext) {
		this.maritalstatustext = maritalstatustext;
	}

	public String getWorkplacetext() {
		return workplacetext;
	}

	public void setWorkplacetext(String workplacetext) {
		this.workplacetext = workplacetext;
	}

	public String getSalarytext() {
		return salarytext;
	}

	public void setSalarytext(String salarytext) {
		this.salarytext = salarytext;
	}

	public String getResumeurl() {
		return resumeurl;
	}

	public void setResumeurl(String resumeurl) {
		this.resumeurl = resumeurl;
	}
	
	
}