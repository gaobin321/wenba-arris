package com.wenba.arris.dto;

import java.io.Serializable;

/**
 * DTO date transport object 指用于传递数据的对象
 * dto 数据结构通过 在协议层 声明类的方式 来定义
 */
public class UserDto implements Serializable {
    private Long id;
    private String name;


    public UserDto() {
    }

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
