package com.zj.jpademo.controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zj.jpademo.domain.CommunityNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
@RestController
@RequestMapping("/community")
public class CommunityController {
    @RequestMapping("/communitynetwork")
    public String communitynetworkbyid(String cid) {
        String json = " ";
        CommunityNetwork cn = new CommunityNetwork();
        JsonObject o = cn.get_data_and_links(cid);
        System.out.println(o);
        json = new Gson().toJson(o);
        //System.out.println(json);
        return json;
    }


    @RequestMapping("/algonetwork")
    /**
     * 读取文件的中的内容
     * 以字符串形式返回
     *
     * @param filePath 文件路径
     * @return java.lang.String 文件信息
     */
    private  String readFile() {
        String filePath = "algonetwork.json";
        StringBuffer info = new StringBuffer();
        BufferedReader br = null;
        try {
            // yml配置文件路径=> file_path: aaa/bbb.text
            // 获取 流读对象 读取文件内容
            InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(filePath).getInputStream());
            br = new BufferedReader(inputStreamReader);
            int line = 1;
            String tempString = null;
            while ((tempString = br.readLine()) != null) {
                info.append(tempString).append("\n");
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return info.toString();
        }
    }


    /*
    public String algonetwork() {
        String fileName = "algonetwork.json";
        //读取json文件
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

     */
    }
