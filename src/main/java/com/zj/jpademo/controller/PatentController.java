package com.zj.jpademo.controller;

import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.PatentEN;
import com.zj.jpademo.domain.PatentUS;
import com.zj.jpademo.domain.Scholar;
import com.zj.jpademo.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/patent")
public class PatentController {
    @Autowired
    private PatentService patentService;

    @RequestMapping("/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "country", defaultValue = "EN")String country, @RequestParam(value = "pageNo")int pageNo) throws ServletException, IOException {
        try
        {
            if(pageNo < 1) { pageNo = 1;}
        }catch(Exception e){}
        if (country.equals("Brazil")) {
           request.getRequestDispatcher("/patent/findByPage/Brazil?pageNo=" + pageNo).forward(request, response);
        }
        else if (country.equals("US")) {
            request.getRequestDispatcher("/patent/findByPage/US?pageNo=" + pageNo).forward(request, response);
        }
        else {
            request.getRequestDispatcher("/patent/findByPage/EN?pageNo=" + pageNo).forward(request, response);
        }
    }
    //巴西
    @RequestMapping("/Brazil/findByPage")
    public Page<PatentBrazil> findByPageBrazil(@RequestParam(value = "pageNo")int pageNo) {
        System.out.println(pageNo);
        return patentService.findPatentBrazilByPage(pageNo,10);
    }

    //查询，通过专利名称查询
    @RequestMapping("/Brazil/findbytitle")
    public List<PatentBrazil> findByTitleBrazil(String title) {
        return  patentService.findPatentBrazilByTitle(title);
    }

    @RequestMapping("/Brazil/findbytitlelike")
    public List<PatentBrazil> findLikeTitleBrazil(String title) {
        return  patentService.findPatentBrazilLikeTitle(title);
    }

    //通过发明人
    @RequestMapping("/Brazil/findbyinventor")
    public List<PatentBrazil> findByInventorBrazil(String inventor) {
        return  patentService.findPatentBrazilByInventor(inventor);
    }

    @RequestMapping("/Brazil/findbyinventorlike")
    public List<PatentBrazil> findLikeInventorBrazil(String inventor) {
        return  patentService.findPatentBrazilLikeInventor(inventor);
    }

    //查询，通过时间
    @RequestMapping("/Brazil/findbytime")
    public List<PatentBrazil> findByTimeBrazil(String time) {
        return  patentService.findPatentBrazilByTime(time);
    }
    @RequestMapping("/Brazil/findbytimelike")
    public List<PatentBrazil> findLikeTimeBrazil(String time) {
        return  patentService.findPatentBrazilLikeTime(time);
    }



   //美国
    @RequestMapping("/US/findByPage")
    public Page<PatentUS> findByPageUS(@RequestParam(value = "pageNo")int pageNo){
        System.out.println(pageNo);
        return patentService.findPatentUSByPage(pageNo,10);
    }
    //查询，通过专利名称查询
    @RequestMapping("/US/findbytitle")
    public List<PatentUS> findByTitleUS(String title) {
        return  patentService.findPatentUSByTitle(title);
    }

    @RequestMapping("/US/findbytitlelike")
    public List<PatentUS> findLikeTitleUS(String title) {
        return  patentService.findPatentUSLikeTitle(title);
    }

    //通过发明人
    @RequestMapping("/US/findbyinventor")
    public List<PatentUS> findByInventorUS(String inventor) {
        return  patentService.findPatentUSByInventor(inventor);
    }

    @RequestMapping("/US/findbyinventorlike")
    public List<PatentUS> findLikeInventorUS(String inventor) {
        return  patentService.findPatentUSLikeInventor(inventor);
    }

    //查询，通过时间
    @RequestMapping("/US/findbytime")
    public List<PatentUS> findByTimeUS(String time) {
        return  patentService.findPatentUSByTime(time);
    }
    @RequestMapping("/US/findbytimelike")
    public List<PatentUS> findLikeTimeUS(String time) {
        return  patentService.findPatentUSLikeTime(time);
    }

    //中国
    @RequestMapping("/EN/findByPage")
    public Page<PatentEN> findByPageEN(@RequestParam(value = "pageNo")int pageNo){
        System.out.println(pageNo);
        return patentService.findPatentENByPage(pageNo,10);

    }
    //查询，通过专利名称查询
    @RequestMapping("/EN/findbytitle")
    public List<PatentEN> findByTitleEN(String title) {
        return  patentService.findPatentENByTitle(title);
    }

    @RequestMapping("/EN/findbytitlelike")
    public List<PatentEN> findLikeTitleEN(String title) {
        return  patentService.findPatentENLikeTitle(title);
    }

    //通过发明人
    @RequestMapping("/EN/findbyinventor")
    public List<PatentEN> findByInventorEN(String inventor) {
        return  patentService.findPatentENByInventor(inventor);
    }

    @RequestMapping("/EN/findbyinventorlike")
    public List<PatentEN> findLikeInventorEN(String inventor) {
        return  patentService.findPatentENLikeInventor(inventor);
    }

    //查询，通过时间
    @RequestMapping("/EN/findbytime")
    public List<PatentEN> findByTimeEN(String time) {
        return  patentService.findPatentENByTime(time);
    }
    @RequestMapping("/EN/findbytimelike")
    public List<PatentEN> findLikeTimeEN(String time) {
        return  patentService.findPatentENLikeTime(time);
    }
}
