package hello.core.order;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    @MainDiscountPolicy
    private final DiscountPolicy discountPolicy;

    /** @RequiredArgsConstructor가 아래 코드를 만들어줌 */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("1. Constructor");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("2. setMemberRepository");
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("3. setDiscountPolicy");
//        this.discountPolicy = discountPolicy;
//    }

    /** 구현체인 FixDiscountPolicy와 RateDiscountPolicy에 의존하고 있음! -> DIP 원칙 위반!!!!
     * interface인 DiscountPlicy에 의존은 하지만, 위와 같은 문제가 있다    * */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
