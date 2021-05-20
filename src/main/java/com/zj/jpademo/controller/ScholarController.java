package com.zj.jpademo.controller;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zj.jpademo.domain.ScholarNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.zj.jpademo.domain.Scholar;
import com.zj.jpademo.service.ScholarService;
import com.zj.jpademo.domain.Daxuan;

@RestController
public class ScholarController {
    @Autowired
    private ScholarService ScholarService;
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

    @RequestMapping("/findbypage")
    public Page<Scholar> findByPage(String page){
        int pageNo = 1;
        try
        {
            pageNo = Integer.parseInt(page);
            if(pageNo < 1) { pageNo = 1;}
        }catch(Exception e){}
        System.out.println(pageNo);
        return ScholarService.findByPage(pageNo,10);
    }

    @RequestMapping("/daxuan")
    public String daxuan(String personname)
    {
        String json =" ";
        Daxuan app = new Daxuan();
        JsonObject o = app.getData(personname);
        System.out.println(o);
        json = new Gson().toJson(o);

        System.out.println(json);
        return json;
    }

    @RequestMapping("/scholarnetwork")
    public String scholarnetwork(String personname)
    {
        String json =" ";
        ScholarNetwork sn = new ScholarNetwork();
        JsonObject o = sn.getData(personname);
        System.out.println(o);
        json = new Gson().toJson(o);
        System.out.println(json);
        return json;
    }

    /**
     public String list(@RequestParam(value="pageNo",required = false,defalutValue="1") String pageNostr,Map<String, Object> map )
     {
     int pageNo = 1;
     try
     {
     //对pageNo的校验
     pageNo = Integer.parseInt(pageNostr);
     if(pageNo < 1)
     {
     pageNo = 1;
     }
     }catch (Exception e){}
     Page<Scholar> page = ScholarService.getPage(pageNo, 5);
     map.put("page",page);
     return "emp/list"
     **/

}