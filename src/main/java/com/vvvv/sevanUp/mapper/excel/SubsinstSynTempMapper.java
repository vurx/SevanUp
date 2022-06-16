package com.vvvv.sevanUp.mapper.excel;

import com.vvvv.sevanUp.model.excel.SubsinstSynTemp;
import com.vvvv.sevanUp.model.excel.SubsinstSynTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsinstSynTempMapper {
    int insert(SubsinstSynTemp record);
    int insertList(List<SubsinstSynTemp> list);
}