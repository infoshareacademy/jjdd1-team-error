package com.infoshareacademy.jjdd1.teamerror.logging;

/**
 * Created by Iga on 20.05.2017.
 */
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Google20Example {

    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    private Google20Example() {
    }

    public static void main(String... args) throws IOException, InterruptedException, ExecutionException {
        // Replace these with your client id and secret
        final String clientId = "447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com";
        final String clientSecret = "kypWEr8p2gMxtv1DZZG6g2mt";
        final String secretState = "secret" + new Random().nextInt(999_999);
        final OAuth20Service service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .scope("profile")
                .scope("email")
                .state(secretState)
                .callback("http://localhost:8080/login")
                .build(GoogleApi20.instance());
        final Scanner in = new Scanner(System.in, "UTF-8");

        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        additionalParams.put("prompt", "consent");
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        System.out.println("podaj code z url:");
        System.out.println(authorizationUrl);
        System.out.print(">>");
        final String code = in.nextLine();
        System.out.println("podaj: '" + secretState + "'.");
        System.out.print(">>");
        final String value = in.nextLine();
        if (!secretState.equals(value)) {
            System.out.println("Nie pasuje!");
        }

        OAuth2AccessToken accessToken = service.getAccessToken(code);
        accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
        while (true) {
            System.out.println("Podaj nazwe pola. Puste wylistuje wszystkie EXIT to wyjście");
            System.out.print(">>");
            final String query = in.nextLine();
            System.out.println();

            final String requestUrl;
            if ("exit" .equals(query)) {
                break;
            } else if (query == null || query.isEmpty()) {
                requestUrl = PROTECTED_RESOURCE_URL;
            } else {
                requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
            }

            final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl);
            service.signRequest(accessToken, request);
            final Response response = service.execute(request);
            System.out.println(response.getCode());
            System.out.println(response.getBody());
        }
    }
}
