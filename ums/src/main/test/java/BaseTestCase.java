import com.pipi.common.domain.Users;
import com.pipi.ums.UMSApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UMSApplication.class)
public class BaseTestCase {

    @Autowired
    private WebApplicationContext wac;

    public MockMvc mvc;
    public MockHttpSession session;
    public Cookie[] cookies = new Cookie[1];

    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        cookies[0] = new Cookie("mykey","myvalue");
        Users user =new Users("root","root");
        user.setId(1L);
        session.setAttribute("user",user);
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testLoad() {
    }

    public void testGetIsOK(String url) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url).session(session).cookie(cookies))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}