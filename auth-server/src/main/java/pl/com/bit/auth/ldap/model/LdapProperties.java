package pl.com.bit.auth.ldap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LdapProperties {
    private String url;
    private String base;
    private String user;
    private String password;
    private LdapAuthenticatorType authenticator;
//    private EntryCredentialsVerifierType credentialsVerifier;
//    private LdapErrorParserType errorParser;
    private String identifierKey;
    private LdapAccountDn identifierValue;

    public ContextSource buildContextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(url);
        contextSource.setBase(base);
        contextSource.setUserDn(user);
        contextSource.setPassword(password);
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    public LdapTemplate buildLdapTemplate() {
        ContextSource ctx = buildContextSource();
        LdapTemplate ldapTemplate = new LdapTemplate(ctx);
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }

    public LdapQuery buildQuery(@NotNull AccountWithPasswordSnapshot account) {
        return LdapQueryBuilder.query()
                .where(identifierKey)
                .is(identifierValue.apply(account));
    }

    public Name buildUserDn(@NotNull AccountWithPasswordSnapshot account) {
        return LdapNameBuilder.newInstance()
                .add(identifierKey, identifierValue.apply(account))
                .build();
    }

    public Name buildUserDnWithoutBase(@NotNull AccountWithPasswordSnapshot account) {
        return LdapNameBuilder.newInstance()
                .add(identifierKey, identifierValue.apply(account))
                .build();
    }

    public void checkConnection() {
        LdapTemplate ldapTemplate = buildLdapTemplate();
        ldapTemplate.getContextSource().getContext(user, password);
    }
}
