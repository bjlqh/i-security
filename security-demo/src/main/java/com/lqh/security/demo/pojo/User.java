package com.lqh.security.demo.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    public interface SimpleView {
    }

    ;

    public interface DetailView extends SimpleView {
    }

    ;

    @JsonView(SimpleView.class)
    private long id;
    @JsonView(SimpleView.class)
    private String username;
    @NotBlank
    @JsonView(DetailView.class)
    private String password;
    @JsonView(SimpleView.class)
    private Date birthday;
}
