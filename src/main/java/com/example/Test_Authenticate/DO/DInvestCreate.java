package com.example.Test_Authenticate.DO;

import com.example.Test_Authenticate.DTO.FundInvest;
import com.example.Test_Authenticate.DTO.FundItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.Date;

public class DInvestCreate extends DFundInvest { //투자시 금액, 투자할 아이템번호, 유저정보만 있으면됨.
    // 유저정보는 security에서.
    @Null
    public Long id;

    @Min(value = 0)
    public Long fundItemId;

    @Null
    public String fundName;

    @Null
    public String uid;

    @Min(value = 0)
    @Max(value = 10000000000L)
    public Long investamount;

    @Null
    public String isValid;

    @Null
    public Date regdate;

    @Null
    public Date updatedate;

    public DInvestCreate() { }

    private static final DInvestCreate dFund = new DInvestCreate();

    public static DInvestCreate asResponse(FundInvest invest) {
        FundItem item = invest.getFundItem();
        dFund.id = invest.getIvid();
        dFund.fundItemId = item.getFundItemId();
        dFund.fundName = item.getName();
        dFund.uid = invest.getUid();
        dFund.investamount = invest.getInvestamount();
        dFund.isValid = invest.getIsValid();
        dFund.regdate = invest.getRegdate();
        dFund.updatedate = invest.getUpdatedate();

        return dFund;
    }
}
