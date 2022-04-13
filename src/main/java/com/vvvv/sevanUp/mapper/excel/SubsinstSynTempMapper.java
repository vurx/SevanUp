package com.vvvv.sevanUp.mapper.excel;

import com.vvvv.sevanUp.model.excel.SubsinstSynTemp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsinstSynTempMapper {
    int insert(SubsinstSynTemp record);
    int insertList(List<SubsinstSynTemp> list);
}