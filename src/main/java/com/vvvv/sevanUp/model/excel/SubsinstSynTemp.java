package com.vvvv.sevanUp.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.Date;

public class SubsinstSynTemp implements Serializable {

    private Integer id;

    @ExcelProperty(index = 0)
    private String systemid;

    @ExcelProperty(index = 1)
    private String reginid;

    @ExcelProperty(index = 2)
    private String prodspeccode;

    @ExcelProperty(index = 3)
    private String useridtype;

    @ExcelProperty(index = 4)
    private String productno;

    @ExcelProperty(index = 5)
    private String vproductid;

    @ExcelProperty(index = 6)
    private String spid;

    @ExcelProperty(index = 7)
    private String productoffertype;

    @ExcelProperty(index = 8)
    private String prodofferid;

    @ExcelProperty(index = 9)
    private String status;

    @ExcelProperty(index = 10)
    private String subscribetime;

    @ExcelProperty(index = 11)
    private String effdate;

    @ExcelProperty(index = 12)
    private String expdate;

    @ExcelProperty(index = 13)
    private String channelplayerid;


    private Short dealFlag;


    private String outParam;


    private Date createData;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SUBSINST_SYN_TEMP
     *
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.ID
     *
     * @return the value of SUBSINST_SYN_TEMP.ID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.ID
     *
     * @param id the value for SUBSINST_SYN_TEMP.ID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.SYSTEMID
     *
     * @return the value of SUBSINST_SYN_TEMP.SYSTEMID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.SYSTEMID
     *
     * @param systemid the value for SUBSINST_SYN_TEMP.SYSTEMID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.REGINID
     *
     * @return the value of SUBSINST_SYN_TEMP.REGINID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getReginid() {
        return reginid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.REGINID
     *
     * @param reginid the value for SUBSINST_SYN_TEMP.REGINID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setReginid(String reginid) {
        this.reginid = reginid == null ? null : reginid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.PRODSPECCODE
     *
     * @return the value of SUBSINST_SYN_TEMP.PRODSPECCODE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getProdspeccode() {
        return prodspeccode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.PRODSPECCODE
     *
     * @param prodspeccode the value for SUBSINST_SYN_TEMP.PRODSPECCODE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setProdspeccode(String prodspeccode) {
        this.prodspeccode = prodspeccode == null ? null : prodspeccode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.USERIDTYPE
     *
     * @return the value of SUBSINST_SYN_TEMP.USERIDTYPE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getUseridtype() {
        return useridtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.USERIDTYPE
     *
     * @param useridtype the value for SUBSINST_SYN_TEMP.USERIDTYPE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setUseridtype(String useridtype) {
        this.useridtype = useridtype == null ? null : useridtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.PRODUCTNO
     *
     * @return the value of SUBSINST_SYN_TEMP.PRODUCTNO
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getProductno() {
        return productno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.PRODUCTNO
     *
     * @param productno the value for SUBSINST_SYN_TEMP.PRODUCTNO
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setProductno(String productno) {
        this.productno = productno == null ? null : productno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.VPRODUCTID
     *
     * @return the value of SUBSINST_SYN_TEMP.VPRODUCTID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getVproductid() {
        return vproductid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.VPRODUCTID
     *
     * @param vproductid the value for SUBSINST_SYN_TEMP.VPRODUCTID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setVproductid(String vproductid) {
        this.vproductid = vproductid == null ? null : vproductid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.SPID
     *
     * @return the value of SUBSINST_SYN_TEMP.SPID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getSpid() {
        return spid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.SPID
     *
     * @param spid the value for SUBSINST_SYN_TEMP.SPID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setSpid(String spid) {
        this.spid = spid == null ? null : spid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.PRODUCTOFFERTYPE
     *
     * @return the value of SUBSINST_SYN_TEMP.PRODUCTOFFERTYPE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getProductoffertype() {
        return productoffertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.PRODUCTOFFERTYPE
     *
     * @param productoffertype the value for SUBSINST_SYN_TEMP.PRODUCTOFFERTYPE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setProductoffertype(String productoffertype) {
        this.productoffertype = productoffertype == null ? null : productoffertype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.PRODOFFERID
     *
     * @return the value of SUBSINST_SYN_TEMP.PRODOFFERID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getProdofferid() {
        return prodofferid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.PRODOFFERID
     *
     * @param prodofferid the value for SUBSINST_SYN_TEMP.PRODOFFERID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setProdofferid(String prodofferid) {
        this.prodofferid = prodofferid == null ? null : prodofferid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.STATUS
     *
     * @return the value of SUBSINST_SYN_TEMP.STATUS
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.STATUS
     *
     * @param status the value for SUBSINST_SYN_TEMP.STATUS
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.SUBSCRIBETIME
     *
     * @return the value of SUBSINST_SYN_TEMP.SUBSCRIBETIME
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getSubscribetime() {
        return subscribetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.SUBSCRIBETIME
     *
     * @param subscribetime the value for SUBSINST_SYN_TEMP.SUBSCRIBETIME
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setSubscribetime(String subscribetime) {
        this.subscribetime = subscribetime == null ? null : subscribetime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.EFFDATE
     *
     * @return the value of SUBSINST_SYN_TEMP.EFFDATE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getEffdate() {
        return effdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.EFFDATE
     *
     * @param effdate the value for SUBSINST_SYN_TEMP.EFFDATE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setEffdate(String effdate) {
        this.effdate = effdate == null ? null : effdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.EXPDATE
     *
     * @return the value of SUBSINST_SYN_TEMP.EXPDATE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getExpdate() {
        return expdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.EXPDATE
     *
     * @param expdate the value for SUBSINST_SYN_TEMP.EXPDATE
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setExpdate(String expdate) {
        this.expdate = expdate == null ? null : expdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.CHANNELPLAYERID
     *
     * @return the value of SUBSINST_SYN_TEMP.CHANNELPLAYERID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getChannelplayerid() {
        return channelplayerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.CHANNELPLAYERID
     *
     * @param channelplayerid the value for SUBSINST_SYN_TEMP.CHANNELPLAYERID
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setChannelplayerid(String channelplayerid) {
        this.channelplayerid = channelplayerid == null ? null : channelplayerid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.DEAL_FLAG
     *
     * @return the value of SUBSINST_SYN_TEMP.DEAL_FLAG
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public Short getDealFlag() {
        return dealFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.DEAL_FLAG
     *
     * @param dealFlag the value for SUBSINST_SYN_TEMP.DEAL_FLAG
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setDealFlag(Short dealFlag) {
        this.dealFlag = dealFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.OUT_PARAM
     *
     * @return the value of SUBSINST_SYN_TEMP.OUT_PARAM
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public String getOutParam() {
        return outParam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.OUT_PARAM
     *
     * @param outParam the value for SUBSINST_SYN_TEMP.OUT_PARAM
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setOutParam(String outParam) {
        this.outParam = outParam == null ? null : outParam.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSINST_SYN_TEMP.CREATE_DATA
     *
     * @return the value of SUBSINST_SYN_TEMP.CREATE_DATA
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public Date getCreateData() {
        return createData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSINST_SYN_TEMP.CREATE_DATA
     *
     * @param createData the value for SUBSINST_SYN_TEMP.CREATE_DATA
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUBSINST_SYN_TEMP
     *
     * @mbg.generated Mon Jul 27 15:39:21 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", systemid=").append(systemid);
        sb.append(", reginid=").append(reginid);
        sb.append(", prodspeccode=").append(prodspeccode);
        sb.append(", useridtype=").append(useridtype);
        sb.append(", productno=").append(productno);
        sb.append(", vproductid=").append(vproductid);
        sb.append(", spid=").append(spid);
        sb.append(", productoffertype=").append(productoffertype);
        sb.append(", prodofferid=").append(prodofferid);
        sb.append(", status=").append(status);
        sb.append(", subscribetime=").append(subscribetime);
        sb.append(", effdate=").append(effdate);
        sb.append(", expdate=").append(expdate);
        sb.append(", channelplayerid=").append(channelplayerid);
        sb.append(", dealFlag=").append(dealFlag);
        sb.append(", outParam=").append(outParam);
        sb.append(", createData=").append(createData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}