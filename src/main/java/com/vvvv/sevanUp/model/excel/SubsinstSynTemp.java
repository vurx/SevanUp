package com.vvvv.sevanUp.model.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
// 支持链式调用
@Accessors(chain = true)
public class SubsinstSynTemp implements Serializable {
    private Integer id;

    private String systemid;

    private String reginid;

    private String prodspeccode;

    private String useridtype;

    private String productno;

    private String vproductid;

    private String spid;

    private String productoffertype;

    private String prodofferid;

    private String status;

    private String subscribetime;

    private String effdate;

    private String expdate;

    private String channelplayerid;

    private Integer dealFlag;

    private String outParam;

    private Date createData;

    private static final long serialVersionUID = 1L;



}