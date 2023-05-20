package pl.com.bit.auth.api;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.com.bit.auth.api.feign.AuthLdapApiFeignProxy;
import pl.com.bit.auth.ldap.dto.LdapStateDetails;
import pl.com.bit.common.exception.CommunicationProblemException;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class HTTPAuthInternalService implements AuthInternalService {
    private final AuthLdapApiFeignProxy ldapApiFeignProxy;

    @Override
    public LdapStateDetails check() {
        log.trace("Checking ldap state");
        return tryToApplyMethod(null, method -> ldapApiFeignProxy.check());
    }

    private <I, O> O tryToApplyMethod(@NotNull I input, @NotNull Function<I, O> method) {
        try {
            return method.apply(input);
        } catch (FeignException ex) {
            throw new CommunicationProblemException("Auth interaction faield", ex);
        }
    }
}
