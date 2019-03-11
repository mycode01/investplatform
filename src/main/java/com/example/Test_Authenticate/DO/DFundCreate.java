package com.example.Test_Authenticate.DO;


import com.example.Test_Authenticate.DTO.FundInfo;
import com.example.Test_Authenticate.DTO.FundItem;

import javax.validation.constraints.*;
import java.util.Optional;

public class DFundCreate extends DFundItem { // 타 시스템과 주고받을 데이터객체(펀드생성)
    // request response 따로 객체를 만들어도 괜찮지만 귀찮음

    @Null
    public Long fundItemId;

    @NotEmpty
    public String name;

    @Min(value = 0)
    @Max(value = 10000000000L)
    public Long fundAmount;

    @NotEmpty
    public String fundType;

    public String description;

    @NotEmpty
    @Pattern(regexp = "[A-E]{1}")
    public String grade;

    public FundItem toFundItem() { // builder패턴 사용시 jpa와 기본 생성자 문제가 있어서
        FundItem item = new FundItem();

        item.setName(this.name);
        item.setFundAmount(this.fundAmount);
        item.setFundType(this.fundType);

        FundInfo info = new FundInfo();
        info.setDescription(this.description);
        info.setGrade(this.grade);

        item.setFundInfo(info);
        return item;
    }

    public DFundCreate(){}

    private static final DFundCreate dFund = new DFundCreate();

    public static DFundCreate asResponse(FundItem item) {
        dFund.fundItemId = item.getFundItemId();
        dFund.name = item.getName();
        dFund.fundAmount = item.getFundAmount();
        dFund.fundType = item.getFundType();
        Optional<FundInfo> info = Optional.ofNullable(item.getFundInfo());
        dFund.description = info.map(i -> i.getDescription()).orElse("");
        dFund.grade = info.map(i -> i.getGrade()).orElse("");
        return dFund;
    }
}
