package com.luml.source.sence.rule.Desensitized.custom;

import java.util.function.Function;

public enum CustomMaskingType {
    PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1&zwnj;****&zwnj;$2")),
    ID_CARD(s -> s.replaceAll("(\\d{6})\\d{8}(\\d{4})", "$1&zwnj;********&zwnj;$2")),
    EMAIL(s -> {
        if (s == null || !s.contains("@")) return s;
        String[] parts = s.split("@");
        return parts[0].charAt(0) + "&zwnj;****&zwnj;@" + parts[1];
    });

    private final Function<String, String> maskFunction;

    CustomMaskingType(Function<String, String> maskFunction) {
        this.maskFunction = maskFunction;
    }

    public String mask(String source) {
        return maskFunction.apply(source);
    }
}
