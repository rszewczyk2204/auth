package pl.com.bit.auth.ldap.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import pl.com.bit.auth.ldap.model.LdapProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "ldap")
public class LdapConfig {
    private Map<String, LdapProperties> sources = new HashMap<>();

    @Bean
    public Map<String, LdapProperties> ldapConfigs() {
        return sources;
    }
}
