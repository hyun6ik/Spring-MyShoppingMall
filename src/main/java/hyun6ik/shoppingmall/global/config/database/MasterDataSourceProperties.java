package hyun6ik.shoppingmall.global.config.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Getter
@Setter
public class MasterDataSourceProperties {

    private final Map<String, Slave> slave = new HashMap<>();

    private String url;
    private String username;
    private String password;


    @Getter
    @Setter
    public static class Slave {

        private String name;
        private String url;
        private String username;
        private String password;
    }
}
