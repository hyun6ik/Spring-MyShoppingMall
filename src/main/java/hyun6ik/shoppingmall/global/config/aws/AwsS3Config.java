package hyun6ik.shoppingmall.global.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableConfigurationProperties(AwsCredentialsProperties.class)
@Configuration
@RequiredArgsConstructor
public class AwsS3Config {

    private final AwsCredentialsProperties awsCredentialsProperties;

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsCredentialsProperties.getAccessKey(), awsCredentialsProperties.getSecretKey());
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion("ap-northeast-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
