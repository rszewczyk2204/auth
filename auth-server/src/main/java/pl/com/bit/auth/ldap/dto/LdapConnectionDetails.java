package pl.com.bit.auth.ldap.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class LdapConnectionDetails {

    @Nullable
    private String source;

    @Nullable
    private String status;
}
