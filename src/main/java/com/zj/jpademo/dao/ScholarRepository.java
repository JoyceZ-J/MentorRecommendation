package com.zj.jpademo.dao;

import com.zj.jpademo.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScholarRepository extends JpaRepository<Scholar,Integer> {
  List<Scholar> findByName(String name);
  List<Scholar> findByNameLike(String name);
  List<Scholar> findByDepartmentLike(String dep);
  List<Scholar> findByDepartment(String dep);
  List<Scholar> findByFieldofStudy(String field);
  List<Scholar> findByFieldofStudyLike(String field);

}
