package com.zj.jpademo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Scholar")
@Table(name = "Scholars")//设置数据库中表名字
public class Scholar {
    @Id
    private Integer id;
    private String name;
    private String orgID;
    private String sex;
    private String department;
    private String postRank;
    private String fieldofStudy;
    private String tel;
    private String email;
    private String eduBackg;
    private String tutor;
    private String students;
    private String patents;
    private String papers;


    /*构造函数*/
    public Scholar() {
    }

    public Scholar(Integer id, String name, String orgID, String sex, String department, String postRank, String fieldofStudy, String tel, String email, String eduBackg, String tutor, String students, String patents, String papers) {
        this.id = id;
        this.name = name;
        this.orgID = orgID;
        this.sex = sex;
        this.department = department;
        this.postRank = postRank;
        this.fieldofStudy = fieldofStudy;
        this.tel = tel;
        this.email = email;
        this.eduBackg = eduBackg;
        this.tutor = tutor;
        this.students = students;
        this.patents = patents;
        this.papers = papers;
    }
    /*set,get*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostRank() {
        return postRank;
    }

    public void setPostRank(String postRank) {
        this.postRank = postRank;
    }

    public String getFieldofStudy() {
        return fieldofStudy;
    }

    public void setFieldofStudy(String fieldofStudy) {
        this.fieldofStudy = fieldofStudy;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEduBackg() {
        return eduBackg;
    }

    public void setEduBackg(String eduBackg) {
        this.eduBackg = eduBackg;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getPatents() {
        return patents;
    }

    public void setPatents(String patents) {
        this.patents = patents;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    @Override
    public String toString() {
        return "Scholar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orgID='" + orgID + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", postRank='" + postRank + '\'' +
                ", fieldofStudy='" + fieldofStudy + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", eduBackg='" + eduBackg + '\'' +
                ", tutor='" + tutor + '\'' +
                ", students='" + students + '\'' +
                ", patents='" + patents + '\'' +
                ", papers='" + papers + '\'' +
                '}';
    }
}
