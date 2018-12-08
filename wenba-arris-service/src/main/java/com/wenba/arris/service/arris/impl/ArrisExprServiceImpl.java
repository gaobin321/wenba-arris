package com.wenba.arris.service.arris.impl;

import com.wenba.arris.dao.ArrisExprDao;
import com.wenba.arris.dto.ArrisExpr;
import com.wenba.arris.expression.Expression;
import com.wenba.arris.expression.ExpressionFactory;
import com.wenba.arris.expression.tokens.Valuable;
import com.wenba.arris.service.arris.ArrisExprService;
import com.wenba.arris.utils.base.DataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class ArrisExprServiceImpl implements ArrisExprService {

    private static final Logger log = LoggerFactory.getLogger(ArrisExprServiceImpl.class);

    @Autowired
    private ArrisExprDao arrisExprDao;


    private ExpressionFactory factory = ExpressionFactory.getInstance();


    //获取表达式详情列表总记录数量
    @Override
    public int selArrisExprListNum(ArrisExpr ae) {
        return arrisExprDao.selArrisExprListNum(ae);
    }

    //查询表达式详情列表
    @Override
    public List<ArrisExpr> selArrisExprList(ArrisExpr ae) {
        return arrisExprDao.selArrisExprList(ae);
    }

    //查询表达式详情列表
    @Override
    public int checkArrisVNExprV(ArrisExpr ae) {
        return arrisExprDao.checkArrisVNExprV(ae);
    }

    //新增表达式详情
    @Override
    public int insArrisExprList(ArrisExpr ae) {
        checkExpre(ae.getInfoId(), ae.getArrisDetail());
        return arrisExprDao.insArrisExprList(ae);
    }


    //更新表达式
    @Override
    public int updArrisExprList(ArrisExpr ae) {
        return arrisExprDao.updArrisExprList(ae);
    }
    @Override
    public int updArrisExprList(List<ArrisExpr> pList) {
        return arrisExprDao.updArrisExprList(pList);
    }

    //删除表达式详情
    @Override
    public int delArrisExprList(ArrisExpr ae) {
        return arrisExprDao.delArrisExprList(ae);
    }
    @Override
    public int delArrisExprList(List<Integer> pList) {
        return arrisExprDao.delArrisExprList(pList);
    }

    //检验表达式详情是否存在
    @Override
    public int checkArrisExprID(int id) {
        return arrisExprDao.checkArrisExprID(id);
    }

    //查询表达式详情版本
    @Override
    public int selArrisExprV(ArrisExpr ae) {
        return arrisExprDao.selArrisExprV(ae);
    }

    //调用表达式详情
    @Override
    public Map<Integer, DataResult<String>> transArrisExprList(Map<String,Object> map) {

        //返回数据
        Map<Integer, DataResult<String>> resultMap = new HashMap<>();
        DataResult<String> dResult = new DataResult<>();

        Expression exp;
        List<Valuable> resList = null;

        //查询出需要执行的表达式
        int id = Integer.parseInt(map.get("ID").toString());
        map.remove("ID");

        //查询要执行表达式详情
        String expressions = arrisExprDao.selArrisExprDetael(id);
        log.info("该ID:" + id + "----->的表达式: " + expressions);

        if(null != expressions && !"".equals(expressions)) {
            if(null != map && map.size() > 0) {
                exp = factory.getExpression(expressions);
                exp.lexicalAnalysis();
                for(String s : map.keySet()) {
                    exp.initVariable(s,map.get(s));
                }
                resList = exp.evaluate();

                if(null == resList || resList.size() == 0) {
                    dResult.setCode(-1);
                    dResult.setMsg("该ID:表达式输出结果为空!");
                    resultMap.put(id,dResult);
                }else {
                    StringBuffer sb = new StringBuffer();
                    for(Valuable result : resList) {
                        sb.append(result.getValue());
                    }
                    dResult.setCode(1);
                    dResult.setMsg("success");
                    dResult.setData(sb.toString());
                    resultMap.put(id,dResult);
                    log.info("该ID:" + id + "----->表达式输出结果: " + sb.toString());
                }
            }else {
                dResult.setCode(-1);
                dResult.setMsg("该ID:表达式输入参数为空!");
                resultMap.put(id,dResult);
            }
        }else {
            dResult.setCode(-1);
            dResult.setMsg("该ID:表达式不存在!");
            resultMap.put(id,dResult);
        }

        return resultMap;
    }


    private void checkExpre(Integer infoId, String arrisDetael) {
        try{
            log.info(infoId + "的表达式详情: " + arrisDetael);
            if(null != arrisDetael && !"".equals(arrisDetael)) {
                Expression exp = factory.getExpression(arrisDetael);
                //词法分析
                exp.lexicalAnalysis();
            }
        } catch (Exception e){
            e.printStackTrace();
            log.info(infoId + "新增表达式失败: " + e);
        }
    }

}
