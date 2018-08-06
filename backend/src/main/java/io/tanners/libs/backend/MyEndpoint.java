package io.tanners.libs.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import io.tanners.libs.jokester.Jokester;
import io.tanners.libs.jokester.model.JokeWrapper;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.libs.tanners.io",
                ownerName = "backend.libs.tanners.io",
                packagePath = ""
        )
)
public class MyEndpoint {
    @ApiMethod(name = "sendJoke")
    public JokeWrapper sendJoke() {
        return (new Jokester()).provideJokes();
    }
}


