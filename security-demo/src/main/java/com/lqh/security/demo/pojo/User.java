package com.lqh.security.demo.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.lqh.security.demo.model.GeneralViews;
import com.lqh.security.demo.validator.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    public interface UserSimpleView extends GeneralViews.PublicView {
    }

    ;

    public interface UserDetailView extends UserSimpleView {
    }

    ;

    @JsonView(UserSimpleView.class)
    private String id;

    @MyConstraint(message = "这是个测试")
    @JsonView(UserSimpleView.class)
    private String username;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;


    @Past(message = "生日只能是过去的时间")
    @JsonView(UserSimpleView.class)
    private Date birthday;
}
