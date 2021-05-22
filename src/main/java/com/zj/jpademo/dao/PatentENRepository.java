package com.zj.jpademo.dao;

import com.zj.jpademo.domain.PatentEN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentENRepository extends JpaRepository<PatentEN, Integer> {
}
