package pl.com.bit.auth.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import pl.com.bit.auth.ldap.api.LdapStateApi;
import pl.com.bit.common.feign.FeignConfiguration;

@FeignClient(
        name = "${feign.service.name.auth-ldap:api-auth-ldap}",
        configuration = {AuthFeighConfiguration.class, FeignConfiguration.class},
        contextId = "AuthLdapApiFeignProxy"
)
public interface AuthLdapApiFeignProxy extends LdapStateApi {
}
