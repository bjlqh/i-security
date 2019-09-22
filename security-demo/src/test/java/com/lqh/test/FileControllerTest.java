package com.lqh.test;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: liqihua
 * Date: 2019/9/21 22:40
 */
public class FileControllerTest extends ASecurityTest {

    @Test
    public void whenUploadSuccess() throws Exception {
        MockMultipartHttpServletRequestBuilder builder = fileUpload("/file")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes()));

        String result = mockMvc.perform(builder).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
