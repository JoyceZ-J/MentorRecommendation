package com.zj.jpademo.service.Imp;

import com.zj.jpademo.dao.PatentBrazilRepository;
import com.zj.jpademo.dao.PatentENRepository;
import com.zj.jpademo.dao.PatentUSRepository;
import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.PatentEN;
import com.zj.jpademo.domain.PatentUS;
import com.zj.jpademo.domain.Scholar;
import com.zj.jpademo.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service
public class PatentServiceImp implements PatentService {
    @Resource
    @Autowired
    private PatentBrazilRepository patentBrazilRepository;

    @Resource
    @Autowired
    private PatentENRepository patentENRepository;

    @Resource
    @Autowired
    private PatentUSRepository patentUSRepository;

    //巴西
    @Override
    public Page<PatentBrazil> findPatentBrazilByPage(Integer pageNum, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, size);
        Page<PatentBrazil> pages = patentBrazilRepository.findAll(pageRequest);
        return pages;
    }
    public List<PatentBrazil> findPatentBrazilByTitle(String title) {
        return patentBrazilRepository.findByTitle(title);
    }
    public List<PatentBrazil> findPatentBrazilLikeTitle(String title)
    {
        return patentBrazilRepository.findByTitleLike("%" + title + "%");
    }
    public List<PatentBrazil> findPatentBrazilByInventor(String inventor)
    {
        return patentBrazilRepository.findByInventors(inventor);
    }

    public List<PatentBrazil> findPatentBrazilLikeInventor(String inventor)
    {
        return patentBrazilRepository.findByInventorsLike("%" + inventor + "%");
    }

    public List<PatentBrazil> findPatentBrazilByTime(String time)
    {
        return patentBrazilRepository.findByPublishTime(time);
    }

    public List<PatentBrazil> findPatentBrazilLikeTime(String time)
    {
        return patentBrazilRepository.findByPublishTimeLike("%" + time + "%");
    }



    //EN
    @Override
    public Page<PatentEN> findPatentENByPage(Integer pageNum, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, size);
        Page<PatentEN> pages = patentENRepository.findAll(pageRequest);
        return pages;
    }
    public List<PatentEN> findPatentENByTitle(String title) {
        return patentENRepository.findByPatentName(title);
    }
    public List<PatentEN> findPatentENLikeTitle(String title)
    {
        return patentENRepository.findByPatentNameLike("%" + title + "%");
    }
    public List<PatentEN> findPatentENByInventor(String inventor)
    {
        return patentENRepository.findByInventor(inventor);
    }

    public List<PatentEN> findPatentENLikeInventor(String inventor)
    {
        return patentENRepository.findByInventorLike("%" + inventor + "%");
    }

    public List<PatentEN> findPatentENByTime(String time)
    {
        return patentENRepository.findByPublicationDate(time);
    }

    public List<PatentEN> findPatentENLikeTime(String time)
    {
        return patentENRepository.findByPublicationDateLike("%" + time + "%");
    }
    //US
    @Override
    public Page<PatentUS> findPatentUSByPage(Integer pageNum, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, size);
        Page<PatentUS> pages = patentUSRepository.findAll(pageRequest);
        return pages;
    }
    public List<PatentUS> findPatentUSByTitle(String title) {
        return patentUSRepository.findByPatentName(title);
    }
    public List<PatentUS> findPatentUSLikeTitle(String title)
    {
        return patentUSRepository.findByPatentNameLike("%" + title + "%");
    }
    public List<PatentUS> findPatentUSByInventor(String inventor)
    {
        return patentUSRepository.findByInventor(inventor);
    }

    public List<PatentUS> findPatentUSLikeInventor(String inventor)
    {
        return patentUSRepository.findByInventorLike("%" + inventor + "%");
    }

    public List<PatentUS> findPatentUSByTime(String time)
    {
        return patentUSRepository.findByPublicationDate(time);
    }

    public List<PatentUS> findPatentUSLikeTime(String time)
    {
        return patentUSRepository.findByPublicationDateLike("%" + time + "%");
    }
}
