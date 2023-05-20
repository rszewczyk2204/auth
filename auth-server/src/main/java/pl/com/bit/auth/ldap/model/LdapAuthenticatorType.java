package pl.com.bit.auth.ldap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public enum LdapAuthenticatorType {

    TEMPLATE {
        public void authenticate(
                @NotNull LdapProperties properties,
                @NotNull AccountWithPasswordSnapshot account,
                @NotBlank String password) {
            LdapTemplate template = properties.buildTemplate();
            template.authenticate(properties.buildQuery(account), password);
        }
    },

    CONTEXT {
        public void authenticate(
                @NotNull LdapProperties properties,
                @NotNull AccountWithPasswordSnapshot account,
                @NotBlank String password) throws NamingException {
            ContextSource ctx = properties.buildContextSource();
            try {
                ctx.getContext(properties.buildUserDn(account).toString(), password)
                        .lookup(properties.buildUserDnWithoutBase(account));
            } catch (Exception ex) {
                properties.getCredentialsVerifier().verify(ctx, ex, properties, account, password);
            }
        }
    }
}
