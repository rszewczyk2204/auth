package pl.com.bit.auth.ldap.model;

import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
@SuppressWarnings("unused")
public class LdapAccountDn implements Function<AccountWithPasswordSnapshot, String> {
    ID(a -> a.getId().toString()),
    LOGIN(AccountWithPasswordSnapshot::getLogin);
    private final Function<AccountWithPasswordSnapshot, String> function;

    @Override
    public String apply(AccountWithPasswordSnapshot account) {
        return function.apply(account);
    }
}
