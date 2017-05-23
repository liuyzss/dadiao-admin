package com.dadiao.wang;

import com.dadiao.wang.util.HttpClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DadiaoAdminApplicationTests {

    @Resource
    private HttpClientService httpClientService;

    @Test
    public void contextLoads() {
        int a = 0;
        String str;
        try {
            str = httpClientService.doGet("http://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(httpClientService);
    }

}
