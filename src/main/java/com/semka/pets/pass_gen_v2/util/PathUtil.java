package com.semka.pets.pass_gen_v2.util;

import java.net.URISyntaxException;
import java.nio.file.Path;

public class PathUtil {
    public static String getPathExecuted(Class<?> clazz) {
        try {
            Path path = Path.of(
                    clazz.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).getParent();
            return path.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
