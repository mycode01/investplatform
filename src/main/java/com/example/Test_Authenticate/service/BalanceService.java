package com.example.Test_Authenticate.service;

import com.example.Test_Authenticate.DTO.Balance;
import com.example.Test_Authenticate.DTO.BalanceHistory;
import com.example.Test_Authenticate.DTO.Member;
import com.example.Test_Authenticate.repository.BalanceHistoryRepository;
import com.example.Test_Authenticate.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    BalanceHistoryRepository historyRepository;

    @Autowired
    MemberService memberService;

    public Balance getBalance(String uid) throws Exception{
        Member member = memberService.get(uid);
//        if(member.getAccount() == null){
//            throw new Exception();
//        } //어차피 에러날것이므로
        return member.getAccount().getBalance();
    }

    public List<BalanceHistory> getHistory(String uid) throws Exception{
        Member member = memberService.get(uid);
        return member.getAccount().getBalance().getBalanceHistories();
    }

    //실제 존재하지 않는 테스트용 입금처리
    @Transactional
    public Balance deposit(String uId){
        Member member = memberService.get(uId);
        Balance balance = member.getAccount().getBalance();
        return createHistory(balance, Type.DEPOSIT, "", 5000000L).getBalance();
    }

    //jpa로 작성하다보니 생각처럼 모양이 나오지 못했음
    public BalanceHistory createHistory(Balance balance, Type type, String description, Long amount){
        Long total = balance.getBalanceHistories().stream().filter(i->i.getType() == Type.DEPOSIT.name())
                .mapToLong(BalanceHistory::getAmount).sum();
        total -= balance.getBalanceHistories().stream().filter(i->i.getType() == Type.WITHDRAW.name())
                .mapToLong(BalanceHistory::getAmount).sum();
//        Map<String, Long> tm =  tc.collect(Collectors.groupingBy(BalanceHistory::getType
//                , Collectors.summingLong(BalanceHistory::getAmount)));
//        Long total = tm.get(Type.DEPOSIT) - tm.get(Type.WITHDRAW);


        if(type == Type.DEPOSIT) {
            balance.setAvailables(total + amount);
            balance.setOwned(total + amount); // 실제로는 용도가 있어 나누었지만 테스트용으로 작성하느라 Type을 나누지 못하여
        } else if(type == Type.WITHDRAW) {
            balance.setAvailables(total - amount);
            balance.setOwned(total - amount);
        }

        BalanceHistory hist = new BalanceHistory();

        hist.setType(type.name());
        hist.setDescription(description);
        hist.setAmount(amount);

        hist.setBalance(balance);

        return historyRepository.save(hist);
    }




    public enum Type {
        OTHERS, DEPOSIT, WITHDRAW
    }
}
