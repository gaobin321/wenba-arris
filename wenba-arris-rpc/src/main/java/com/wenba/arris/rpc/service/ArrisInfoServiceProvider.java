package com.wenba.arris.rpc.service;

import com.wenba.arris.dto.ArrisInfo;
import com.wenba.arris.service.ArrisInfoServiceFacade;
import com.wenba.arris.service.arris.ArrisInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ArrisInfoServiceProvider implements ArrisInfoServiceFacade {

    @Autowired
    ArrisInfoService arrisService;

    
    //获取表达式列表总记录数量
    @Override
    public int selArrisInfoListNum(ArrisInfo ai) {
        return arrisService.selArrisInfoListNum(ai);
    }

    //查询表达式列表
    @Override
    public List<ArrisInfo> selArrisInfoList(ArrisInfo ai) {
        return arrisService.selArrisInfoList(ai);
    }

    //检验表达式版本和表达式名称
    @Override
    public int checkArrisVN(ArrisInfo ai) {
        return arrisService.checkArrisVN(ai);
    }

    //新增表达式
    @Override
    public int insArrisInfoList(ArrisInfo ai) {
        return arrisService.insArrisInfoList(ai);
    }
    @Override
    public int insArrisInfoList(List<ArrisInfo> pList) {
        return arrisService.insArrisInfoList(pList);
    }

    //更新表达式
    @Override
    public int updArrisInfoList(ArrisInfo ai) {
        return arrisService.updArrisInfoList(ai);
    }
    @Override
    public int updArrisInfoList(List<ArrisInfo> pList) {
        return arrisService.updArrisInfoList(pList);
    }

    //删除表达式
    @Override
    public int delArrisInfoList(ArrisInfo ai) {
        return arrisService.delArrisInfoList(ai);
    }
    @Override
    public int delArrisInfoList(List<Integer> pList) {
        return arrisService.delArrisInfoList(pList);
    }

    //检验表达式是否存在
    @Override
    public int checkArrisID(int id) {
        return arrisService.checkArrisID(id);
    }
}
