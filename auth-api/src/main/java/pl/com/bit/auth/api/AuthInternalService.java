package pl.com.bit.auth.api;

import pl.com.bit.auth.ldap.dto.LdapStateDetails;

public interface AuthInternalService {

    LdapStateDetails check();
}
