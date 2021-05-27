package com.zj.jpademo.dao;

import com.zj.jpademo.domain.PatentBrazil;
import com.zj.jpademo.domain.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatentBrazilRepository extends JpaRepository<PatentBrazil, Integer> {
    List<PatentBrazil> findByTitle(String title);
    List<PatentBrazil> findByTitleLike(String title);
    List<PatentBrazil> findByInventors(String inventor);
    List<PatentBrazil> findByInventorsLike(String inventor);
    List<PatentBrazil> findByPublishTime(String time);
    List<PatentBrazil> findByPublishTimeLike(String time);

}
