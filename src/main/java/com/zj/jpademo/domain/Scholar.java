package com.zj.jpademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Scholar")
@Table
public class Scholar {
    @Id
    private Integer id;
    private String personID;
    private String orgID;
    private String name;
    private String sex;
    private String department;
    private String postRank;
    private String fieldofStudy;
    private String tel;
    private String email;
    private String eduBackg;
    private String students;

    /*构造函数*/

    public Scholar() {
    }

    public Scholar(Integer id, String personID, String orgID, String name, String sex, String department, String postRank, String fieldofStudy, String tel, String email, String eduBackg, String students) {
        this.id = id;
        this.personID = personID;
        this.orgID = orgID;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.postRank = postRank;
        this.fieldofStudy = fieldofStudy;
        this.tel = tel;
        this.email = email;
        this.eduBackg = eduBackg;
        this.students = students;
    }

    public String toString() {
        return "Scholar{" +
                "id=" + id +
                ", personID='" + personID + '\'' +
                ", orgID='" + orgID + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", postRnak='" + postRank + '\'' +
                ", fieldofStudy='" + fieldofStudy + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", eduBackg='" + eduBackg + '\'' +
                ", students=" + students +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }
}
