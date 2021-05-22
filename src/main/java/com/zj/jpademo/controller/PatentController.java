package com.zj.jpademo.controller;

import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.PatentEN;
import com.zj.jpademo.domain.PatentUS;
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

@RestController
public class PatentController {
    @Autowired
    private PatentService patentService;

    @RequestMapping("/patent/findByPage")
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

    @RequestMapping("/patent/findByPage/Brazil")
    public Page<PatentBrazil> findByPageBrazil(@RequestParam(value = "pageNo")int pageNo) {
        System.out.println(pageNo);
        return patentService.findPatentBrazilByPage(pageNo,10);
    }

    @RequestMapping("/patent/findByPage/US")
    public Page<PatentUS> findByPageUS(@RequestParam(value = "pageNo")int pageNo){
        System.out.println(pageNo);
        return patentService.findPatentUSByPage(pageNo,10);
    }

    @RequestMapping("/patent/findByPage/EN")
    public Page<PatentEN> findByPageEN(@RequestParam(value = "pageNo")int pageNo){
        System.out.println(pageNo);
        return patentService.findPatentENByPage(pageNo,10);
    }
}
