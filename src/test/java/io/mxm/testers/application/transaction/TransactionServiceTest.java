package io.mxm.testers.application.transaction;

import io.mxm.testers.MyTestApplication;
import io.mxm.testers.dto.StudentRegisterInfo;
import io.mxm.testers.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MyTestApplication.class)
@AutoConfigureMockMvc

class TransactionServiceTest {


}