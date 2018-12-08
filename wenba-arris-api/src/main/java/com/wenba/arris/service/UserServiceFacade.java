package com.wenba.arris.service;

import com.wenba.arris.dto.UserDto;

/**
 * 协议接口 提供服务的接口用于提供 rpc 协议中方法的声明:
 * [接口名 (UserServiceFacade),接口方法名(queryUserById),方法参数类型和名字(Long userId),返回类型(UserDto)]
 */
public interface UserServiceFacade {

    /**
     * 根据ID查询
     *
     * @param userId
     * @return
     */
    UserDto queryUserById(Integer userId) ;

}
