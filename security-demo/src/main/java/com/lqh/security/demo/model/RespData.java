package com.lqh.security.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * Author: liqihua
 * Date: 2019/11/27 23:40
 */
@Data
public class RespData<T> {
    @JsonView(GeneralViews.PublicView.class)
    protected boolean success = true;
    @JsonView(GeneralViews.PublicView.class)
    protected int code = 0;
    @JsonView(GeneralViews.PublicView.class)
    protected String msg = "success";
    @JsonView(GeneralViews.PublicView.class)
    protected T data;

    public RespData(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RespData() {
    }
}
