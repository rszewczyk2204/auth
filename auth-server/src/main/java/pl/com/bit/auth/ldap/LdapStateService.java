package pl.com.bit.auth.ldap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.com.bit.auth.ldap.config.LdapConfig;
import pl.com.bit.auth.ldap.dto.LdapConnectionDetails;
import pl.com.bit.auth.ldap.dto.LdapStateDetails;
import pl.com.bit.auth.ldap.model.LdapProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class LdapStateService {
    private final LdapConfig ldapConfig;

    public LdapStateDetails check() {
        return LdapStateDetails.of(
                ldapConfig.getSources().entrySet().stream()
                        .map(e -> checkConnection(e.getKey(), e.getValue()))
                        .collect(Collectors.toList()));
    }

    private static LdapConnectionDetails checkConnection(@NotBlank String source, @NotNull LdapProperties properties) {
        return LdapConnectionDetails.of(source, connectionStatus(properties));
    }

    private static String connectionStatus(@NotNull LdapProperties properties) {
        try {
            properties.checkConnection();
            return "OK";
        } catch (Exception ex) {
            log.error("Ldap connection failed", ex);
            return ex.getLocalizedMessage();
        }
    }
}
