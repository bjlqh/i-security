package com.lqh.test;

import com.lqh.security.demo.DemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(get("/user")
                .param("username", "jack")
                .param("age", "16")
                .param("ageTo", "18")
                .param("xxx", UUID.randomUUID().toString().substring(0, 6))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"));

    }

    @Test
    public void whenGetInfoFail() throws Exception {
        MockHttpServletRequestBuilder builder = get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(builder)
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();
        System.out.println("====" + result.getResponse().getContentAsString());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        String content = "{\"username\":\"tom\",\"password\": null,\"birthday\":" + date.getTime() + "}";

        MockHttpServletRequestBuilder builder = post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content);

        String contentAsString = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(contentAsString);
    }

    /**
     * 400 401 404 405(请求方法不支持)
     *
     * @throws Exception
     */
    @Test
    public void whenUpdateSuccess() throws Exception {
        //jdk8 提供的时间+1年
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\": null,\"birthday\":" + date.getTime() + "}";

        MockHttpServletRequestBuilder builder = put("/user/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        String result = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
