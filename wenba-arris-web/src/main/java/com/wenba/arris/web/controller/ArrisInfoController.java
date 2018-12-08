package com.wenba.arris.web.controller;

import com.wenba.arris.dto.ArrisInfo;
import com.wenba.arris.service.ArrisInfoServiceRpc;
import com.wenba.arris.service.arris.ArrisInfoService;
import com.wenba.arris.utils.base.BaseResult;
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
import java.util.List;


@RestController
@RequestMapping("/config/arris")
@ComponentScan(basePackages = "com.wenba.arris.service")
public class ArrisInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private ArrisInfoServiceRpc arrisInfoServiceRpc;

    @Autowired
    private ArrisInfoService arrisInfoService;

    /**
     *  查询表达式列表
     */
    @RequestMapping(value = "/selArrisInfoList", method = RequestMethod.GET)
    public PageResult<ArrisInfo> selArrisInfoList(ArrisInfo ai){

        log.info("selArrisInfoList 输入参数: " + ai.toString());
        long startTime = System.currentTimeMillis();


        //返回数据
        PageResult<ArrisInfo> dataResult = new PageResult<>();
        List<ArrisInfo> resultList = new ArrayList<>();

        try {
            dataResult.setPage(ai.getPageNumber());
            ai.setPageNumber(ai.getPageNumber() - 1);
            dataResult.setSize(ai.getPageSize());

            //获取表达式列表总记录数量
            int total = arrisInfoServiceRpc.selArrisInfoListNum(ai);


            dataResult.setTotal(new Long((long)total));

            //查询表达式列表
            resultList = arrisInfoServiceRpc.selArrisInfoList(ai);

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
            System.out.println("selArrisInfoList exception: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        log.info("selArrisInfoList 输出参数: " + resultList.toString());
        log.info("selArrisInfoList cost time:{} ms", endTime - startTime);
        return dataResult;
    }



    /**
     *  新增表达式
     */
    @RequestMapping(value = "/insArrisInfoList", method = RequestMethod.POST)
    public BaseResult insArrisInfoList(@RequestBody List<ArrisInfo> aiList){

        log.info("insArrisInfoList 输入参数: " + aiList.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<ArrisInfo> pList = new ArrayList<>();

        try {
            if(null != aiList && aiList.size() > 0) {
                for(ArrisInfo ai : aiList) {
                    //表达式的当前发布版本默认从1开始
                    ai.setArrisVers(1);
                    //检验表达式版本和表达式名称
                    int i = arrisInfoServiceRpc.checkArrisVN(ai);
                    if(i >= 1) {
                        sbF.append(ai.getArrisName() + "-" + ai.getArrisVers() + ",");
                    }else{
                        sbS.append(ai.getArrisName() + "-" + ai.getArrisVers() + ",");
                        pList.add(ai);
                    }
                }
                if(null != pList && pList.size() > 0) {
                    //新增表达式
                    int j = arrisInfoServiceRpc.insArrisInfoList(pList);
                    if(j > 0) {
                        if(sbF.length() > 0) {
                            bResult.setCode(1);
                            bResult.setMsg(sbF.toString() + "该表达式名称和版本已存在" + "\r\n" + sbS.toString() + "该表达式新增成功");
                        }else {
                            bResult.setCode(1);
                            bResult.setMsg("success");
                        }
                    }else {
                        if(sbF.length() > 0) {
                            bResult.setCode(-1);
                            bResult.setMsg(sbF.toString() + "该表达式名称和版本已存在" + "\r\n" + sbS.toString() + "该表达式新增失败");
                        }else{
                            bResult.setCode(-1);
                            bResult.setMsg("fail");
                        }
                    }
                }else {
                    bResult.setCode(-1);
                    bResult.setMsg(sbF.toString() + "该表达式名称和版本已存在");
                }
            }else {
                bResult.setCode(-4);
                bResult.setMsg("参数为空");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        log.info("insArrisInfoList 输出参数: " + bResult.toString());
        log.info("insArrisInfoList cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /**
     *  更新表达式
     */
    @RequestMapping(value = "/updArrisInfoList", method = RequestMethod.POST)
    public BaseResult updArrisInfoList(@RequestBody List<ArrisInfo> aiList){

        log.info("updArrisInfoList 输入参数: " + aiList.toString());
        long startTime = System.currentTimeMillis();

        //返回数据
        BaseResult bResult = new BaseResult();
        StringBuilder sbF = new StringBuilder();
        StringBuilder sbS = new StringBuilder();

        List<ArrisInfo> pList = new ArrayList<>();

        try {
            if (null != aiList && aiList.size() > 0) {
                for (ArrisInfo ai : aiList) {
                    //检验表达式
                    int i = arrisInfoServiceRpc.checkArrisID(ai.getId());
                    if(i >= 1) {
                        sbS.append(ai.getId() + ",");
                        pList.add(ai);
                    }else{
                        sbF.append(ai.getId() + ",");
                    }
                }
                if(null != pList && pList.size() > 0) {
                    //更新表达式
                    int j = arrisInfoServiceRpc.updArrisInfoList(pList);
                    if(j > 0) {
                        if(sbF.length() > 0) {
                            bResult.setCode(1);
                            bResult.setMsg(sbF.toString() + "该表达式不存在" + "\r\n" + sbS.toString() + "该表达式更新成功");
                        }else {
                            bResult.setCode(1);
                            bResult.setMsg("success");
                        }
                    }else {
                        if(sbF.length() > 0) {
                            bResult.setCode(-1);
                            bResult.setMsg(sbF.toString() + "该表达式不存在" + "\r\n" + sbS.toString() + "该表达式更新失败");
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
        }


        long endTime = System.currentTimeMillis();
        log.info("updArrisInfoList 输出参数: " + bResult.toString());
        log.info("updArrisInfoList cost time:{} ms", endTime - startTime);
        return bResult;
    }


    /**
     *  删除表达式
     */
    @RequestMapping(value = "/delArrisInfoList", method = RequestMethod.POST)
    public BaseResult delArrisInfoList(@RequestBody List<Integer> idList){

        log.info("delArrisInfoList 输入参数: " + idList.toString());
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
                    int i = arrisInfoServiceRpc.checkArrisID(id);
                    if(i >= 1) {
                        sbS.append(id + ",");
                        pList.add(id);
                    }else{
                        sbF.append(id + ",");
                    }
                }
                if(null != pList && pList.size() > 0) {
                    //删除表达式
                    int j = arrisInfoServiceRpc.delArrisInfoList(pList);
                    if(j > 0) {
                        if(sbF.length() > 0) {
                            bResult.setCode(1);
                            bResult.setMsg(sbF.toString() + "该表达式不存在" + "\r\n" + sbS.toString() + "该表达式删除成功");
                        }else {
                            bResult.setCode(1);
                            bResult.setMsg("success");
                        }
                    }else {
                        if(sbF.length() > 0) {
                            bResult.setCode(-1);
                            bResult.setMsg(sbF.toString() + "该表达式不存在" + "\r\n" + sbS.toString() + "该表达式删除失败");
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
        }


        long endTime = System.currentTimeMillis();
        log.info("delArrisInfoList 输出参数: " + bResult.toString());
        log.info("delArrisInfoList cost time:{} ms", endTime - startTime);
        return bResult;
    }


}
