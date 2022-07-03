package hyun6ik.shoppingmall.infrastructure.delivery.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.shoppingmall.domain.delivery.entity.QDelivery;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.domain.member.entity.QMember;
import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;
import hyun6ik.shoppingmall.interfaces.delivery.dto.QDeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hyun6ik.shoppingmall.domain.delivery.entity.QDelivery.*;
import static hyun6ik.shoppingmall.domain.member.entity.QMember.*;

@Repository
@RequiredArgsConstructor
public class DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<DeliveryDto> findDeliveryDtos(Long memberId) {
        return queryFactory
                .select(new QDeliveryDto(
                      delivery.id,
                      delivery.deliveryFee,
                      delivery.deliveryName
                ))
                .from(delivery)
                .innerJoin(delivery.member, member)
                .where(delivery.member.id.eq(memberId))
                .fetch();
    }
}
