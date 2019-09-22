package com.lqh.test;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ASecurityTest {

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
     * 400 401 403 404 405(请求方法不支持)
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

    @Test
    public void whenDeleteSuccess() throws Exception {
        MockHttpServletRequestBuilder builder = delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(builder)
                .andExpect(status().isOk());
    }
}
