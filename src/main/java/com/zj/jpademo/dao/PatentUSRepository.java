package com.zj.jpademo.dao;

import com.zj.jpademo.domain.PatentUS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentUSRepository extends JpaRepository<PatentUS, Integer> {
}
