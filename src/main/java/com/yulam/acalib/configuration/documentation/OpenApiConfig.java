package com.yulam.acalib.configuration.documentation;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
  info = @Info(
            title = "AcaLib - Library of Academic Materials",
            description = "A library that provides access to academic materials like thesis, dissertations, journals and etc.",
            contact = @Contact(
              email = "info@acalib.com",
              url = "acalib.com",
              name = "Acalib"
            ),
            license = @License(
              name = "MIT License",
              url = "https://github.com/volunux/yulam-academic-thesis-disseration-api-service"
            )
        ),
        servers = @Server(url = "/")
)
public class OpenApiConfig {

}
