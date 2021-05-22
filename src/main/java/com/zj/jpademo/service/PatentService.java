package com.zj.jpademo.service;

import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.PatentEN;
import com.zj.jpademo.domain.PatentUS;
import org.springframework.data.domain.Page;

public interface PatentService {
    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    public Page<PatentBrazil> findPatentBrazilByPage(Integer pageNum, Integer size);

    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    public Page<PatentEN> findPatentENByPage(Integer pageNum, Integer size);

    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    public Page<PatentUS> findPatentUSByPage(Integer pageNum, Integer size);
}
