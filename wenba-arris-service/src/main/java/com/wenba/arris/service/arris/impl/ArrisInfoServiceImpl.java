package com.wenba.arris.service.arris.impl;

import com.wenba.arris.dao.ArrisInfoDao;
import com.wenba.arris.dto.ArrisInfo;
import com.wenba.arris.expression.ExpressionFactory;
import com.wenba.arris.service.arris.ArrisInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ArrisInfoServiceImpl implements ArrisInfoService {

    private static final Logger log = LoggerFactory.getLogger(ArrisInfoServiceImpl.class);

    @Autowired
    private ArrisInfoDao arrisInfoDao;


    private ExpressionFactory factory = ExpressionFactory.getInstance();


    //获取表达式列表总记录数量
    @Override
    public int selArrisInfoListNum(ArrisInfo ai) {
        return arrisInfoDao.selArrisInfoListNum(ai);
    }

    //查询表达式列表
    @Override
    public List<ArrisInfo> selArrisInfoList(ArrisInfo ai) {
        return arrisInfoDao.selArrisInfoList(ai);
    }

    //检验表达式版本和表达式名称
    @Override
    public int checkArrisVN(ArrisInfo ai) {
        return arrisInfoDao.checkArrisVN(ai);
    }

    //新增表达式
    @Override
    public int insArrisInfoList(ArrisInfo ai) {
        return arrisInfoDao.insArrisInfoList(ai);
    }
    public int insArrisInfoList(List<ArrisInfo> pList) {
        return arrisInfoDao.insArrisInfoList(pList);
    }


    //更新表达式
    @Override
    public int updArrisInfoList(ArrisInfo ai) {
        return arrisInfoDao.updArrisInfoList(ai);
    }
    @Override
    public int updArrisInfoList(List<ArrisInfo> pList) {
        return arrisInfoDao.updArrisInfoList(pList);
    }

    //删除表达式
    @Override
    public int delArrisInfoList(ArrisInfo ai) {
        return arrisInfoDao.delArrisInfoList(ai);
    }
    @Override
    public int delArrisInfoList(List<Integer> pList) {
        return arrisInfoDao.delArrisInfoList(pList);
    }

    //检验表达式是否存在
    @Override
    public int checkArrisID(int id) {
        return arrisInfoDao.checkArrisID(id);
    }


}
