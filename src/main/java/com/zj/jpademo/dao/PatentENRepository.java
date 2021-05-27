package com.zj.jpademo.dao;

import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.PatentEN;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.List;

public interface PatentENRepository extends JpaRepository<PatentEN, Integer> {
    List<PatentEN> findByPatentName(String title);
    List<PatentEN> findByPatentNameLike(String title);
    List<PatentEN> findByInventor(String inventor);
    List<PatentEN> findByInventorLike(String inventor);
    List<PatentEN> findByPublicationDate(String time);
    List<PatentEN> findByPublicationDateLike(String time);
}
