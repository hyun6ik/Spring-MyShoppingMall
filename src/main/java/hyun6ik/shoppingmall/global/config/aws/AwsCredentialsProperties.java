package hyun6ik.shoppingmall.global.config.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloud.aws.credentials")
public class AwsCredentialsProperties {

    private String accessKey;
    private String secretKey;
}
