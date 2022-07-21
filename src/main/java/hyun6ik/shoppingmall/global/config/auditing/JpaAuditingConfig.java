package hyun6ik.shoppingmall.global.config.auditing;

import hyun6ik.shoppingmall.infrastructure.delivery.repository.DeliveryRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemImageRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import hyun6ik.shoppingmall.infrastructure.order.repository.OrderRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackageClasses = {DeliveryRepository.class, MemberRepository.class, ItemRepository.class,
        ItemImageRepository.class, OrderRepository.class})
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfig {

}
