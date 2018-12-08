package com.wenba.arris.service;

import com.wenba.arris.utils.base.DataResult;
import com.wenba.arris.dto.ArrisExpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("arrisExprServiceRpc")
//@ImportResource(locations={"classpath:template-dubbo-client.xml"})
public class ArrisExprServiceRpc implements ArrisExprServiceFacade {


    @Autowired
    public ArrisExprServiceFacade arrisExprServiceFacade;


    @Override
    public int selArrisExprListNum(ArrisExpr ae) {
        return arrisExprServiceFacade.selArrisExprListNum(ae);
    }

    @Override
    public List<ArrisExpr> selArrisExprList(ArrisExpr ae) {
        return arrisExprServiceFacade.selArrisExprList(ae);
    }

    @Override
    public int checkArrisVNExprV(ArrisExpr ae) {
        return arrisExprServiceFacade.checkArrisVNExprV(ae);
    }

    @Override
    public int insArrisExprList(ArrisExpr ae) {
        return arrisExprServiceFacade.insArrisExprList(ae);
    }

    @Override
    public int updArrisExprList(ArrisExpr ae) {
        return arrisExprServiceFacade.updArrisExprList(ae);
    }
    @Override
    public int updArrisExprList(List<ArrisExpr> pList) {
        return arrisExprServiceFacade.updArrisExprList(pList);
    }

    @Override
    public int delArrisExprList(ArrisExpr ae) {
        return arrisExprServiceFacade.delArrisExprList(ae);
    }
    @Override
    public int delArrisExprList(List<Integer> pList) {
        return arrisExprServiceFacade.delArrisExprList(pList);
    }

    @Override
    public Map<Integer, DataResult<String>> transArrisExprList(Map<String, Object> map) {
        return arrisExprServiceFacade.transArrisExprList(map);
    }

    @Override
    public int checkArrisExprID(int id) {
        return arrisExprServiceFacade.checkArrisExprID(id);
    }

    @Override
    public int selArrisExprV(ArrisExpr ae) {
        return arrisExprServiceFacade.selArrisExprV(ae);
    }
}