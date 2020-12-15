package com.ambevtech.prova.utils;

import com.ambevtech.prova.util.FunctionUtil;
import io.restassured.response.Response;
import org.springframework.util.Assert;

public class Validators {

    public static String getErrorByResponse(Response response) {

        String mensagem = response.getBody().asString();

        return "HTTP: " + response.getStatusCode() + System.lineSeparator() +
                "Body: " + System.lineSeparator() + mensagem;
    }

    public static void validaMensagemBody(Response response, String mensagem, String cenario) {

        String body = response.getBody().asString();
        Assert.isTrue(!FunctionUtil.isEmpty(body) && body.contains(mensagem), "Falha no cenário: " + cenario + " mensagem: " + mensagem + " não encontrada." +
                System.lineSeparator() + "HTTP: " + response.getStatusCode() +
                System.lineSeparator() + "Body: " + body);
    }

    public static void validaStatus(String cenario, Response response, String status) {
        Assert.isTrue(response.statusCode() == Integer.parseInt(status),
                    "Falha no cenário " + cenario + System.lineSeparator() +
                            ", o teste retornou: " + Validators.getErrorByResponse(response));
    }
}
