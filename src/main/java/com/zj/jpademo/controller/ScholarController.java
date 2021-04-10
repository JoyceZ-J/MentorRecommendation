package com.zj.jpademo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zj.jpademo.domain.Scholar;
import com.zj.jpademo.dao.ScholarRepository;
import com.zj.jpademo.service.ScholarService;

@RestController
public class ScholarController {
    @Autowired
    private ScholarService ScholarService;
    @Autowired
    private ScholarRepository scholarRepository;
    //查询所有的学者信息
    @RequestMapping("/all")
    public List<Scholar> getallS()
    {return ScholarService.getAllScholars();}
    //增加学者信息
    @RequestMapping("/add")
    public void addS()
    {   Scholar s = new Scholar();
        s.setName("");
        ScholarService.addScholar(s);}
    //根据id查询学者信息
    @RequestMapping("/findbyid")
    Scholar getSbyid(int id)
    {
        return ScholarService.getScholarById(id);
    }

    @RequestMapping("/delbyid")
    //根据id删除学者信息
    public void delSbyid(int id)
    {
        ScholarService.deleteScholarById(id);
    }
    @RequestMapping("/updatetype1")
    //更新学者信息
    public void updateS(Scholar scholar)
    {
        ScholarService.updateScholar(scholar);
    }

    //查询，通过name查询
    @RequestMapping("/findbyname")
    public List<Scholar> findByScholarName(String name) {
        return  ScholarService.findScholarByName(name);
    }

    @RequestMapping("/findbynamelike")
    public List<Scholar> findLikeScholarName(String name) {
        return  ScholarService.findScholarLikeName(name);
    }

    //查询，通过学校查询
    @RequestMapping("/findbydep")
    public List<Scholar> findByDep(String dep) {
        return  ScholarService.findScholarByDepartment(dep);
    }

    @RequestMapping("/findbydeplike")
    public List<Scholar> findLikeDep(String dep) {
        return ScholarService.findScholarByDepartmentLike(dep);
    }

    //查询，通过领域查询
    @RequestMapping("/findbyfield")
    public List<Scholar> findByField(String field) {
        return  ScholarService.findScholarByField(field);
    }

    @RequestMapping("/findbyfieldlike")
    public List<Scholar> findLikeField(String field) {
        return  ScholarService.findScholarByFieldLike(field);
    }

}