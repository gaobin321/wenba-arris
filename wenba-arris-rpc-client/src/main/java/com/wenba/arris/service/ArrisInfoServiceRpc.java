package com.wenba.arris.service;

import com.wenba.arris.dto.ArrisInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("arrisInfoServiceRpc")
//@ImportResource(locations={"classpath:template-dubbo-client.xml"})
public class ArrisInfoServiceRpc implements ArrisInfoServiceFacade {


    @Autowired
    public ArrisInfoServiceFacade arrisInfoServiceFacade;

    @Override
    public int selArrisInfoListNum(ArrisInfo ai) {
        return arrisInfoServiceFacade.selArrisInfoListNum(ai);
    }

    @Override
    public List<ArrisInfo> selArrisInfoList(ArrisInfo ai) {
        return arrisInfoServiceFacade.selArrisInfoList(ai);
    }

    @Override
    public int checkArrisVN(ArrisInfo ai) {
        return arrisInfoServiceFacade.checkArrisVN(ai);
    }

    @Override
    public int insArrisInfoList(ArrisInfo ai) {
        return arrisInfoServiceFacade.insArrisInfoList(ai);
    }
    @Override
    public int insArrisInfoList(List<ArrisInfo> pList) {
        return arrisInfoServiceFacade.insArrisInfoList(pList);
    }

    @Override
    public int updArrisInfoList(ArrisInfo ai) {
        return arrisInfoServiceFacade.updArrisInfoList(ai);
    }
    @Override
    public int updArrisInfoList(List<ArrisInfo> pList) {
        return arrisInfoServiceFacade.updArrisInfoList(pList);
    }

    @Override
    public int delArrisInfoList(ArrisInfo ai) {
        return arrisInfoServiceFacade.delArrisInfoList(ai);
    }
    @Override
    public int delArrisInfoList(List<Integer> pList) {
        return arrisInfoServiceFacade.delArrisInfoList(pList);
    }

    @Override
    public int checkArrisID(int id) {
        return arrisInfoServiceFacade.checkArrisID(id);
    }
}