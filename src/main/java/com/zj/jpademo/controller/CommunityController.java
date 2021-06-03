package com.zj.jpademo.controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zj.jpademo.domain.CommunityNetwork;
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
@RequestMapping("/community")
public class CommunityController {
    @RequestMapping("/communitynetwork")
    public String communitynetworkbyid(String cid)
    {
        String json =" ";
        CommunityNetwork cn = new CommunityNetwork();
        JsonObject o = cn.get_data_and_links(cid);
        System.out.println(o);
        json = new Gson().toJson(o);
        System.out.println(json);
        return json;
    }
}
