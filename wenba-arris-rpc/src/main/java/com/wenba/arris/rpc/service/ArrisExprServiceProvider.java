package com.wenba.arris.rpc.service;

import com.wenba.arris.utils.base.DataResult;
import com.wenba.arris.dto.ArrisExpr;
import com.wenba.arris.service.ArrisExprServiceFacade;
import com.wenba.arris.service.arris.ArrisExprService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class ArrisExprServiceProvider implements ArrisExprServiceFacade {

    @Autowired
    ArrisExprService arrisExprService;


    //获取表达式详情列表总记录数量
    @Override
    public int selArrisExprListNum(ArrisExpr ae) {
        return arrisExprService.selArrisExprListNum(ae);
    }

    //查询表达式详情列表
    @Override
    public List<ArrisExpr> selArrisExprList(ArrisExpr ae) {
        return arrisExprService.selArrisExprList(ae);
    }

    //检验表达式详情版本
    @Override
    public int checkArrisVNExprV(ArrisExpr ae) {
        return arrisExprService.checkArrisVNExprV(ae);
    }

    //新增表达式
    @Override
    public int insArrisExprList(ArrisExpr ae) {
        return arrisExprService.insArrisExprList(ae);
    }

    //更新表达式
    @Override
    public int updArrisExprList(ArrisExpr ae) {
        return arrisExprService.updArrisExprList(ae);
    }
    @Override
    public int updArrisExprList(List<ArrisExpr> pList) {
        return arrisExprService.updArrisExprList(pList);
    }

    //删除表达式
    @Override
    public int delArrisExprList(ArrisExpr ae) {
        return arrisExprService.delArrisExprList(ae);
    }
    @Override
    public int delArrisExprList(List<Integer> pList) {
        return arrisExprService.delArrisExprList(pList);
    }

    //调用表达式详情
    @Override
    public Map<Integer, DataResult<String>> transArrisExprList(Map<String,Object> map) {
        return arrisExprService.transArrisExprList(map);
    }

    //检验表达式详情是否存在
    @Override
    public int checkArrisExprID(int id) {
        return arrisExprService.checkArrisExprID(id);
    }

    //查询表达式详情版本
    @Override
    public int selArrisExprV(ArrisExpr ae) {
        return arrisExprService.selArrisExprV(ae);
    }
}
