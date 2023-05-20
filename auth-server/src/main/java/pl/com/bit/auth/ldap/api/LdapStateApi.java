package pl.com.bit.auth.ldap.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.com.bit.auth.ldap.dto.LdapStateDetails;

@Api(
        value = "LdapState",
        description = "the LDAP state API"
)
public interface LdapStateApi {

    @ApiOperation(
            value = "Check ldap connection state.",
            nickname = "check",
            response = LdapStateDetails.class,
            authorizations = {@Authorization("bearerAuth")},
            tags = {"vehicles"}
    )
    @ApiResponses({@ApiResponse(
            code = 201,
            message = "OK",
            response = LdapStateDetails.class
    ), @ApiResponse(
            code = 400,
            message = "Bad request - the request cannot be handled by the server due to an irregularity perceived as a user's error (e.g. incorrect query syntax).",
            response = Object.class
    ), @ApiResponse(
            code = 403,
            message = "Forbidden - access denied",
            response = Object.class
    ), @ApiResponse(
            code = 404,
            message = "Not found",
            response = Object.class
    )})
    @RequestMapping(
            value = {"/ldap/state"},
            consumes = {"application/json"},
            method = {RequestMethod.GET}
    )
    LdapStateDetails check();
}
