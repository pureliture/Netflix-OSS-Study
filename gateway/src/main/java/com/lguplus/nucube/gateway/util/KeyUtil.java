package com.lguplus.nucube.gateway.util;

import org.springframework.core.io.ClassPathResource;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.stream.Collectors;

public class KeyUtil {
    public static RSAPublicKey loadPublicKey(String location) throws Exception {
        byte[] bytes = Files.readAllBytes(new ClassPathResource(location).getFile().toPath());
        String pem = new String(bytes).lines()
                .filter(l -> !l.startsWith("-----")).collect(Collectors.joining());
        byte[] decoded = java.util.Base64.getDecoder().decode(pem);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
    }
}
