package com.country.countryside.config.id;

import java.io.Serializable;
import java.util.UUID;

public class UUIDGenerator implements IdGenerator{

    @Override
    public Serializable generator() {
        // 自行修改此处生成id方案
        return UUID.randomUUID().toString();
    }
}
