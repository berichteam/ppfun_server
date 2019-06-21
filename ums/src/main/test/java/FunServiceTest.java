import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.persistence.mapper.FunMapper;
import com.pipi.common.service.inter.FunService;
import com.pipi.ums.UMSApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UMSApplication.class)
public class FunServiceTest {

        @Autowired
        private FunService funService;

        @Test
        public void testSelectAllMineStaredFunByPage() {
            List<FunDTO> list = funService.selectAllMineStaredFunByPage(1L);
            System.out.print(list);
        }
    }

