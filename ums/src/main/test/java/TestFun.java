import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pipi.common.vo.FunImagesVo;
import com.pipi.common.vo.FunVo;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestFun extends BaseTestCase {
    @Test
    @Transactional
    public void createFun() throws Exception {


        String test ="{\n" +
                "\n" +
                "\"title\":\"美女1\",\n" +
                "\n" +
                "\"content\":\"这是一个美女1\",\n" +
                "\n" +
                "\"images\":[{\n" +
                "\n" +
                "\t\"id\":3,\n" +
                "\n" +
                "\t\"blur\":1,\n" +
                "\n" +
                "\t\"desc\": \"\"\n" +
                "}],\n" +
                "\n" +
                "\"authority\":3,\n" +
                "\n" +
                "\"password\":\"\",\n" +
                "\n" +
                "\"fee\":1\n" +
                "\n" +
                "}";
        Map<String, Object> content = new HashMap<>();

        FunVo funVo = new FunVo();
        funVo.setTitle("这是标题");
        funVo.setContent("这是内容");
        List<FunImagesVo> funImagesVoList = new ArrayList<>();
        FunImagesVo funImagesVo = new FunImagesVo();
        funImagesVo.setId(1);
        funImagesVo.setBlur(0);
        funImagesVoList.add(funImagesVo);
        funVo.setImages(funImagesVoList);
        funVo.setAuthority(1);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(test);
        mvc.perform(MockMvcRequestBuilders.post( "/article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
