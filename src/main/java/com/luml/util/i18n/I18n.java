package com.luml.util.i18n;

import java.util.Objects;

/**
 * 国际化po层解耦接口
 * i18n关闭时，走默认实现
 * @author houjun@e6yun.com
 * @date 2023/9/27 10:06
 */
public interface I18n {
    default String n(String code, Object... params) {
        if (Objects.isNull(params) || params.length == 0) {
            return code;
        }
        return String.format(code, params);
    }
}
