package com.websocket.demo.entity;

import java.security.Principal;

/**
 * @Author hezhan
 * @Date 2019/9/24 16:51
 * 权限验证类
 */
public class FastPrincipal implements Principal {

    private final String name;

    public FastPrincipal(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
