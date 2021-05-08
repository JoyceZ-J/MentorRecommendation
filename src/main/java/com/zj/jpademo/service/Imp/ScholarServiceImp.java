package com.zj.jpademo.service.Imp;

import com.zj.jpademo.dao.ScholarRepository;
import com.zj.jpademo.domain.Scholar;
import com.zj.jpademo.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ScholarServiceImp  implements ScholarService {
    @Resource
    @Autowired
    private ScholarRepository scholarRepository;

    @Override
    public List<Scholar> getAllScholars() {
        return scholarRepository.findAll();
    }

    @Override
    public void addScholar(Scholar scholar) {
        scholarRepository.save(scholar);
    }

    @Override
    public Scholar getScholarById(int id) {
        return scholarRepository.findById(id).get();
    }

    @Override
    public void deleteScholarById(int id) {
        scholarRepository.deleteById(id);
    }

    @Override
    public void updateScholar(Scholar scholar) {
        scholarRepository.save(scholar);
    }

    @Override
    public List<Scholar> findScholarByName(String name) {
        return scholarRepository.findByName(name);
    }

    @Override
    public List<Scholar> findScholarLikeName(String name) {
        return scholarRepository.findByNameLike("%" + name + "%");

    }

    @Override
    public List<Scholar> findScholarByDepartment(String dep) {
        return scholarRepository.findByDepartment(dep);
    }

    @Override
    public List<Scholar> findScholarByDepartmentLike(String dep) {
        return scholarRepository.findByDepartmentLike("%" + dep + "%");

    }

    @Override
    public List<Scholar> findScholarByField(String field) {
        return scholarRepository.findByFieldofStudy(field);
    }

    @Override
    public List<Scholar> findScholarByFieldLike(String field)
    {
        return scholarRepository.findByFieldofStudyLike(field);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param size
     * @return
     */
    @Override
    public Page<Scholar> findByPage(Integer pageNum, Integer size) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, size);
        Page<Scholar> page= scholarRepository.findAll(pageRequest);
        return page;
    }

}
