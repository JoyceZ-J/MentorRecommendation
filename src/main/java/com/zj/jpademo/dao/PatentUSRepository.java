package com.zj.jpademo.dao;

import com.zj.jpademo.domain.PatentEN;
import com.zj.jpademo.domain.PatentUS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.List;
public interface PatentUSRepository extends JpaRepository<PatentUS, Integer> {
    List<PatentUS> findByPatentName(String title);
    List<PatentUS> findByPatentNameLike(String title);
    List<PatentUS> findByInventor(String inventor);
    List<PatentUS> findByInventorLike(String inventor);
    List<PatentUS> findByPublicationDate(String time);
    List<PatentUS> findByPublicationDateLike(String time);
}
