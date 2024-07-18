package za.co.mkhungo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Noxolo.Mkhungo
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Noxolo Mkhungo",
                        email = "noxolo@mkhungo.co.za",
                        url = "www.noxolo.mkhungo.co,za"
                ),
                description = "OpenApi documentation for JSON Node Tree Model (Hierarchical) ",
                title = "JSON Node Tree Model (Hierarchical)",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        })
public class OpenApiConfig {
}
