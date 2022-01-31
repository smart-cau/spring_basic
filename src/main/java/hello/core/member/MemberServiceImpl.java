package hello.core.member;

// interface에 대한 구현체가 하나면 구현체 되에 Impl이라는 접미사를 붙이는게 관례
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /** 추상*/
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
