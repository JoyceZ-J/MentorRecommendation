package com.zj.jpademo.service;

import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.PatentEN;
import com.zj.jpademo.domain.PatentUS;
import org.springframework.data.domain.Page;
import java.util.List;

public interface PatentService {
    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    public Page<PatentBrazil> findPatentBrazilByPage(Integer pageNum, Integer size);
    public List<PatentBrazil> findPatentBrazilByTitle(String title);
    public List<PatentBrazil> findPatentBrazilLikeTitle(String title);
    public List<PatentBrazil> findPatentBrazilByInventor(String inventor);
    public List<PatentBrazil> findPatentBrazilLikeInventor(String inventor);
    public List<PatentBrazil> findPatentBrazilByTime(String time);
    public List<PatentBrazil> findPatentBrazilLikeTime(String time);

    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    public Page<PatentEN> findPatentENByPage(Integer pageNum, Integer size);
    public List<PatentEN> findPatentENByTitle(String title);
    public List<PatentEN> findPatentENLikeTitle(String title);
    public List<PatentEN> findPatentENByInventor(String inventor);
    public List<PatentEN> findPatentENLikeInventor(String inventor);
    public List<PatentEN> findPatentENByTime(String time);
    public List<PatentEN> findPatentENLikeTime(String time);

    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    public Page<PatentUS> findPatentUSByPage(Integer pageNum, Integer size);
    public List<PatentUS> findPatentUSByTitle(String title);
    public List<PatentUS> findPatentUSLikeTitle(String title);
    public List<PatentUS> findPatentUSByInventor(String inventor);
    public List<PatentUS> findPatentUSLikeInventor(String inventor);
    public List<PatentUS> findPatentUSByTime(String time);
    public List<PatentUS> findPatentUSLikeTime(String time);
}
