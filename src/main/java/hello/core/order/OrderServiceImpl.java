package hello.core.order;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    /** 구현체인 FixDiscountPolicy와 RateDiscountPolicy에 의존하고 있음! -> DIP 원칙 위반!!!!
     * interface인 DiscountPlicy에 의존은 하지만, 위와 같은 문제가 있다    * */
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy1) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy1;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
