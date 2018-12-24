package com.wenba.arris.web.controller;

import com.wenba.arris.base.BaseResult;
import com.wenba.arris.dto.ArrisExpr;
import com.wenba.arris.service.ArrisExprServiceRpc;
import com.wenba.arris.utils.base.DataResult;
import com.wenba.arris.utils.base.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/config/arris")
@ComponentScan(basePackages = "com.wenba.arris.service")
public class ArrisExprController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ArrisExprServiceRpc arrisExprServiceRpc;


    /**
     *  查询表达式详情列表
     */
    @RequestMapping(value = "/selArrisExprList", method = RequestMethod.GET)
    public PageResult<ArrisExpr> selArrisExprList(ArrisExpr ae){

        log.info("selArrisExprList 输入参数: " + ae.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        PageResult<ArrisExpr> dataResult = new PageResult<>();
        List<ArrisExpr> resultList = new ArrayList<>();

        try {
            dataResult.setPage(ae.getPageNumber());
            ae.setPageNumber(ae.getPageNumber() - 1);
            dataResult.setSize(ae.getPageSize());

            //获取表达式详情列表总记录数量
            int total = arrisExprServiceRpc.selArrisExprListNum(ae);

            dataResult.setTotal(new Long((long)total));

            //查询表达式详情列表
            resultList = arrisExprServiceRpc.selArrisExprList(ae);

            if(null != resultList && resultList.size() > 0) {
                dataResult.setCode(1);
                dataResult.setMsg("success");
                dataResult.setData(resultList);
            }else {
                dataResult.setCode(1);
                dataResult.setMsg("未查询到结果");
                dataResult.setData(resultList);
            }
        }catch (Exception e) {
            e.printStackTrace();
            dataResult.setCode(-1);
            dataResult.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("selArrisExprList 输出参数: " + resultList.toString());
        log.info("selArrisExprList cost time:{} ms", endTime - startTime);
        return dataResult;
    }



    /**
     *  新增表达式详情
     */
    @RequestMapping(value = "/insArrisExprList", method = RequestMethod.POST)
    public DataResult insArrisExprList(@RequestBody List<ArrisExpr> aeList){

        log.info("insArrisExprList 输入参数: " + aeList.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<Map<Integer,BaseResult>>> result = new DataResult<>();
        List<Map<Integer,BaseResult>> list = new ArrayList<>();
        Map<Integer,BaseResult> map = null;

        try {
            if(null != aeList && aeList.size() > 0) {
                for(ArrisExpr ae : aeList) {
                    BaseResult bResult = new BaseResult();
                    map = new HashMap<>();
                /*//检验表达式详情版本
                int i = arrisExprServiceRpc.checkArrisVNExprV(ae);
                if(i == 0) {*/

                    //查询表达式详情版本
                    int v = arrisExprServiceRpc.selArrisExprV(ae);
                    ae.setExprVers(v + 1);
                    //新增表达式详情
                    int j = arrisExprServiceRpc.insArrisExprList(ae);
                    if(j > 0) {
                        bResult.setCode(1);
                        bResult.setMsg("success");
                        map.put(ae.getInfoId(),bResult);
                        list.add(map);
                    }else{
                        bResult.setCode(-1);
                        bResult.setMsg("fail");
                        map.put(ae.getInfoId(),bResult);
                        list.add(map);
                    }
                /*}else{
                    bResult.setCode(-1);
                    bResult.setMsg("该表达式详情版本已存在");
                    map.put(ae.getInfoId(),bResult);
                    list.add(map);
                }*/
                }
            }else {
                result.setCode(-4);
                result.setMsg("参数为空");
            }

            result.setCode(1);
            result.setMsg("success");
            result.setData(list);
        }catch (Exception e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("insArrisExprList 输出参数: " + result.toString());
        log.info("insArrisExprList cost time:{} ms", endTime - startTime);
        return result;
    }


    /**
     *  更新表达式详情
     */
    @RequestMapping(value = "/updArrisExprList", method = RequestMethod.POST)
    public BaseResult updArrisExprList(@RequestBody List<ArrisExpr> aeList){

        log.info("updArrisExprList 输入参数: " + aeList.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<ArrisExpr> pList = new ArrayList<>();

        try {
            if (null != aeList && aeList.size() > 0) {
                for (ArrisExpr ae : aeList) {
                    //检验表达式版本详情
                    int i = arrisExprServiceRpc.checkArrisExprID(ae.getId());
                    if(i >= 1) {
                        sbS.append(ae.getId() + ",");
                        pList.add(ae);
                    }else{
                        sbF.append(ae.getId() + ",");
                    }
                }
                if(null != pList && pList.size() > 0) {
                    //更新表达式
                    int j = arrisExprServiceRpc.updArrisExprList(pList);
                    if(j > 0) {
                        if(sbF.length() > 0) {
                            bResult.setCode(1);
                            bResult.setMsg(sbF.toString() + "该表达式详情不存在" + "\r\n" + sbS.toString() + "该表达式详情更新成功");
                        }else {
                            bResult.setCode(1);
                            bResult.setMsg("success");
                        }
                    }else {
                        if(sbF.length() > 0) {
                            bResult.setCode(-1);
                            bResult.setMsg(sbF.toString() + "该表达式详情不存在" + "\r\n" + sbS.toString() + "该表达式详情更新失败");
                        }else{
                            bResult.setCode(-1);
                            bResult.setMsg("fail");
                        }
                    }
                }else {
                    bResult.setCode(-1);
                    bResult.setMsg(sbF.toString() + "该表达式详情不存在");
                }
            }else {
                bResult.setCode(-4);
                bResult.setMsg("参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            bResult.setCode(-1);
            bResult.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("updArrisExprList 输出参数: " + bResult.toString());
        log.info("updArrisExprList cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /**
     *  删除表达式详情
     */
    @RequestMapping(value = "/delArrisExprList", method = RequestMethod.POST)
    public BaseResult delArrisExprList(@RequestBody List<Integer> idList){

        log.info("delArrisExprList 输入参数: " + idList.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<Integer> pList = new ArrayList<>();

        try {
            if (null != idList && idList.size() > 0) {
                for (Integer id : idList) {
                    //检验表达式
                    int i = arrisExprServiceRpc.checkArrisExprID(id);
                    if(i >= 1) {
                        sbS.append(id + ",");
                        pList.add(id);
                    }else{
                        sbF.append(id + ",");
                    }
                }
                if(null != pList && pList.size() > 0) {
                    //删除表达式详情
                    int j = arrisExprServiceRpc.delArrisExprList(pList);
                    if(j > 0) {
                        if(sbF.length() > 0) {
                            bResult.setCode(1);
                            bResult.setMsg(sbF.toString() + "该表达式详情不存在" + "\r\n" + sbS.toString() + "该表达式详情删除成功");
                        }else {
                            bResult.setCode(1);
                            bResult.setMsg("success");
                        }
                    }else {
                        if(sbF.length() > 0) {
                            bResult.setCode(-1);
                            bResult.setMsg(sbF.toString() + "该表达式详情不存在" + "\r\n" + sbS.toString() + "该表达式详情删除失败");
                        }else{
                            bResult.setCode(-1);
                            bResult.setMsg("fail");
                        }
                    }
                }else {
                    bResult.setCode(-1);
                    bResult.setMsg(sbF.toString() + "该表达式不存在");
                }
            }else {
                bResult.setCode(-4);
                bResult.setMsg("参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            bResult.setCode(-1);
            bResult.setMsg("fail");
        }


        long endTime = System.currentTimeMillis();
        log.info("delArrisExprList 输出参数: " + bResult.toString());
        log.info("delArrisExprList cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /**
     *  调用表达式详情
     */
    @RequestMapping(value = "/transArrisExprList", method = RequestMethod.POST)
    public DataResult transArrisExprList(@RequestBody List<Map<String,Object>> mapList){

        log.info("transArrisExprList 输入参数: " + mapList.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        DataResult<List<Map<Integer, DataResult<String>>>> result = new DataResult<>();

        List<Map<Integer, DataResult<String>>> list = new ArrayList<>();

        Map<Integer, DataResult<String>> map = new HashMap<>();

        DataResult<String> dataResult = new DataResult<>();

        try {
            if(null != mapList && mapList.size() > 0) {
                int id = 0;
                int i = 0;
                for(Map<String,Object> m : mapList) {
                    //校验表达式ID是否存在
                    if(m.containsKey("ID")) {
                        id = Integer.parseInt(m.get("ID").toString());
                        //检验表达式详情是否存在
                        i = arrisExprServiceRpc.checkArrisExprID(id);
                        if(i > 0) {
                            //调用表达式详情
                            Map<Integer, DataResult<String>> dResult = arrisExprServiceRpc.transArrisExprList(m);
                            list.add(dResult);
                        }else{
                            dataResult.setCode(-1);
                            dataResult.setMsg("该表达式详情不存在");
                            map.put(id,dataResult);
                            list.add(map);
                        }
                    }else {
                        dataResult.setCode(-1);
                        dataResult.setMsg("该表达式详情不存在!");
                        map.put(id,dataResult);
                        list.add(map);
                    }
                }
            }else {
                result.setCode(-4);
                result.setMsg("参数为空");
            }

            result.setCode(1);
            result.setMsg("success");
            result.setData(list);
        }catch (Exception e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMsg("fail");
        }

        long endTime = System.currentTimeMillis();
        log.info("transArrisExprList 输出参数: " + result.toString());
        log.info("transArrisExprList cost time:{} ms", endTime - startTime);
        return result;
    }

}
