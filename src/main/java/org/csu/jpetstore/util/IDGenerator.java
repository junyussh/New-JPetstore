package org.csu.jpetstore.util;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class IDGenerator {
    public Integer getID() {
        String id = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 8);
        return Integer.valueOf(id);
    }
}
