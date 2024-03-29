package org.example.test.domain;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class TestDeptRepository {

    @Autowired
    DeptRepository deptRepository;

    @Test
    @Commit
    public void dept01() {
        for(int i= 1;i<101;i++) {
            deptRepository.save(new Dept(i, i + "dName", "loc_"+i));
        }
    }
}
