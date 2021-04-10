package com.zj.jpademo;

import com.zj.jpademo.dao.ScholarRepository;
import com.zj.jpademo.domain.Scholar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JpademoApplicationTests {
    @Resource
    private ScholarRepository ScholarRepository;
    @Test
    void contextLoads() {
    }
    @Test
    public void testfindbyname()
    {
        System.out.println(ScholarRepository.findByName("张洁"));
    }


}
