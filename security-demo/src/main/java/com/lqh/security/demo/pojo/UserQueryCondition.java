package com.lqh.security.demo.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryCondition implements Serializable {
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("用户起始年龄")
    private int age;
    @ApiModelProperty("用户终止年龄")
    private int ageTo;
    @ApiModelProperty("xxx")
    private String xxx;
}
