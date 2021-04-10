package com.zj.jpademo.service;

import com.zj.jpademo.domain.Scholar;

import java.util.List;

public interface ScholarService {
    //查询所有的学者信息
    public List<Scholar> getAllScholars();

    //增加学者信息
    public void addScholar(Scholar scholar);

    //根据id查询学者信息
    public Scholar getScholarById(int id);
    //根据id删除学者信息
    public void deleteScholarById(int id);
    //根据Name查询学者信息
    public List<Scholar> findScholarByName(String name);
    //根据Name模糊查询学者信息
    public List<Scholar> findScholarLikeName(String name);

    //根据学校查询学者信息
    public List<Scholar> findScholarByDepartment(String dep);
    //根据学校模糊查询学者信息
    public List<Scholar> findScholarByDepartmentLike(String dep);

    //根据领域查询学者信息
    public List<Scholar> findScholarByField(String field);
    //根据领域模糊查询学者信息
    public List<Scholar> findScholarByFieldLike(String field);

    //更新学者信息
    public void updateScholar(Scholar scholar);

}
