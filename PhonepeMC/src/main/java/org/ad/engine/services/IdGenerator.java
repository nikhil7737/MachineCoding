package org.ad.engine.services;

import java.util.UUID;

public class IdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
