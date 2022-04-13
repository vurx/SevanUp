package com.vvvv.sevanUp.instance.excel.threadPool;


import com.vvvv.sevanUp.mapper.excel.SubsinstSynTempMapper;
import com.vvvv.sevanUp.model.excel.SubsinstSynTemp;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class VTask implements Callable {
    private List<SubsinstSynTemp> temps;
    private SubsinstSynTempMapper mapper;
    private String code;
    private String msg;

    public VTask(List<SubsinstSynTemp> temps, SubsinstSynTempMapper mapper) {
        this.temps = temps;
        this.mapper = mapper;
    }

    @Override
    public RspVo call() {
        RspVo rspVo = new RspVo();
        ArrayList<Object> failList = new ArrayList<>();
        for (SubsinstSynTemp temp : temps) {
            try {
                mapper.insert(temp);
            } catch (Exception e) {
                log.error("数据插入失败：{}", temp.toString());
                failList.add(temp.getSystemid());
            }
        }
        if (0 != failList.size()) {
            rspVo.setResultCode("9999");
            rspVo.setResultMsg(failList.toString());
        } else {
            rspVo.setResultCode("0");
            rspVo.setResultMsg("成功");
        }
        return rspVo;
    }

}
