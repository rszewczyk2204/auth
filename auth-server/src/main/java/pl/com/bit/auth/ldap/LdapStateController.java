package pl.com.bit.auth.ldap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bit.auth.ldap.api.LdapStateApi;
import pl.com.bit.auth.ldap.dto.LdapStateDetails;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LdapStateController implements LdapStateApi {
    private final LdapStateService ldapStateService;

    @Override
    public LdapStateDetails check() {
        log.trace("Checking ldap connection state");
        LdapStateDetails state = ldapStateService.check();
        log.trace("Ldap connection state: {}", state);
        return state;
    }
}
