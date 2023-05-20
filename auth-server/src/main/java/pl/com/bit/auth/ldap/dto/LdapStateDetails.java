package pl.com.bit.auth.ldap.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LdapStateDetails {

    private List<LdapConnectionDetails> sources;
}
