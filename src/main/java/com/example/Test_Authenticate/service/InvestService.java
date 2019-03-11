package com.example.Test_Authenticate.service;

import com.example.Test_Authenticate.DTO.*;
import com.example.Test_Authenticate.repository.InvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class InvestService {

    @Autowired
    InvestRepository repo;

    @Autowired
    FundService fundService;

    @Autowired
    MemberService memberService;

    @Autowired
    BalanceService balanceService;

    public FundInvest get(Long id) {
        return repo.findById(id).get();
    }

    public List<FundInvest> getAll(String uid) {
         Member a = memberService.get(uid);
        return a.getInvests();
    }

    public FundInvest create(Long fundItemId, Long investAmount, String uId) throws Exception{
        Member investor = memberService.get(uId);
        FundItem fundItem = fundService.get(fundItemId);
        FundInvest invest = new FundInvest();

        invest.setFundItem(fundItem);
        invest.setUid(uId);
        invest.setInvestamount(investAmount);
        invest.setIsValid("Y");
        investor.addInvests(invest);

        if(!investValidate(investor, fundItem, investAmount))
            throw new Exception();

        Balance blance = investor.getAccount().getBalance();
        FundInvest r = repo.save(invest);
        balanceService.createHistory(blance, BalanceService.Type.WITHDRAW, fundItem.getName(), investAmount);

        // 타이밍문제와 투자금액 오버플로 현상을 피할수있는 방법에 대해서는 작성하지 않음.
        return r;
    }

    private boolean investValidate(Member investor, FundItem item, Long amount) {
        if (checkBalance(investor, amount) && checkFundAmount(item, amount))
            return true;
        else
            return false;
    }

    private boolean checkBalance(Member investor, Long amount) {
        try {
            Account account = investor.getAccount();
            Balance balance = account.getBalance();
            if (balance.getAvailables() < amount
                    || balance.getOwned() < amount) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean checkFundAmount(FundItem item, Long amount) {
        Optional<List<FundInvest>> op = Optional.ofNullable(item.getInvestors());
        List<FundInvest> invests = op.filter(Objects::nonNull).get();
        Long total = invests.stream().mapToLong(i -> i.getInvestamount()).sum();

        if (total + amount >= item.getFundAmount()) {
            return false;
        }
        return true;
    }
}
