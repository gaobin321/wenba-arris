package com.wenba.arris.service;

import com.wenba.arris.dto.ArrisInfo;

import java.util.List;

public interface ArrisInfoServiceFacade {


    //获取表达式列表总记录数量
    int selArrisInfoListNum(ArrisInfo ai);

    //查询表达式列表
    List<ArrisInfo> selArrisInfoList(ArrisInfo ai);

    //检验表达式版本和表达式名称
    int checkArrisVN(ArrisInfo ai);

    //新增表达式
    int insArrisInfoList(ArrisInfo ai);
    int insArrisInfoList(List<ArrisInfo> pList);

    //更新表达式
    int updArrisInfoList(ArrisInfo ai);
    int updArrisInfoList(List<ArrisInfo> pList);

    //删除表达式
    int delArrisInfoList(ArrisInfo ai);
    int delArrisInfoList(List<Integer> pList);

    //检验表达式是否存在
    int checkArrisID(int id);
}
