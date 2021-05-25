package com.zj.jpademo.service.Imp;

import com.zj.jpademo.dao.ScholarRepository;
import com.zj.jpademo.domain.Scholar;
import com.zj.jpademo.service.ScholarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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

    @Override
    public Page<Scholar> findByCondition(Integer page, Integer size, String name, String sex,
                                         String department, String postRank, String fieldofStudy,
                                         String eduBackg, String tutor, String students, String patents, String papers) {
        Pageable pageable = PageRequest.of(page, size);
        return scholarRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (StringUtils.isNotEmpty(sex)) {
                predicates.add(criteriaBuilder.like(root.get("sex"), "%" + sex + "%"));
            }
            if (StringUtils.isNotEmpty(department)) {
                predicates.add(criteriaBuilder.like(root.get("department"), "%" + department + "%"));
            }
            if (StringUtils.isNotEmpty(postRank)) {
                predicates.add(criteriaBuilder.like(root.get("postRank"), "%" + postRank + "%"));
            }
            if (StringUtils.isNotEmpty(fieldofStudy)) {
                predicates.add(criteriaBuilder.like(root.get("fieldofStudy"), "%" + fieldofStudy + "%"));
            }
            if (StringUtils.isNotEmpty(eduBackg)) {
                predicates.add(criteriaBuilder.like(root.get("eduBackg"), "%" + eduBackg + "%"));
            }
            if (StringUtils.isNotEmpty(tutor)) {
                predicates.add(criteriaBuilder.like(root.get("tutor"), "%" + tutor + "%"));
            }
            if (StringUtils.isNotEmpty(students)) {
                predicates.add(criteriaBuilder.like(root.get("students"), "%" + students + "%"));
            }
            if (StringUtils.isNotEmpty(patents)) {
                predicates.add(criteriaBuilder.like(root.get("patents"), "%" + patents + "%"));
            }
            if (StringUtils.isNotEmpty(papers)) {
                predicates.add(criteriaBuilder.like(root.get("papers"), "%" + papers + "%"));
            }

            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
    }
}
