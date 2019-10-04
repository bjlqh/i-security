package com.lqh.security.demo.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

/**
 * Author: liqihua
 * Date: 2019/10/4 19:47
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        //1.指定服务器
        configureFor(8062);
        //2.把以前做过的服务都清空，把新加的服务都加进来
        removeAllMappings();
        //3.告诉服务器怎么处理请求，一个测试桩
        mock("/order/1", "mock/response/01.txt");
        mock("/order/2", "mock/response/02.txt");

    }

    private static void mock(final String url, final String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        File file = resource.getFile();
        String content = FileUtils.readFileToString(file, "utf-8");
        System.out.println(content);

        Object[] objects = FileUtils.readLines(file, "utf-8").toArray();
        System.out.println("---------------------");
        System.out.println(Arrays.toString(objects));
        String join = StringUtils.join(objects, "\n");
        System.out.println(join);
        System.out.println("---------------------");

        stubFor(get(urlPathEqualTo(url))
                .willReturn(aResponse().withBody(content).withStatus(200).withStatusMessage("请求成功")));
    }
}
