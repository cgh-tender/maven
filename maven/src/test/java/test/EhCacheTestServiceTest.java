package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EhCacheTestServiceTest extends SpringTestCase {
    static Logger logger = LogManager.getLogger("EhCacheTestServiceTest");
//    @Autowired
//    private EhCacheTestService ehCacheTestService;

    @Test
    public void getTimestampTest() throws Exception {
        Map c = new HashMap<String, List<String>>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("ccc","ddd");
        mockMvc.perform(post("").header("","").headers(headers)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getHeader("");
        logger.info("111111");
        logger.error("2222");
    }
}