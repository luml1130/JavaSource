package com.luml.util.i18n;

/**
 * @author houjun@e6yun.com
 * @date 2023/9/27 10:08
 */
public class I {

    private static I18n i18n;

    public I(I18n i18n) {
        this.setIi(i18n);
    }

    public static String n(String code, Object... params) {
        return i18n.n(code, params);
    }

    private void setIi(I18n i18n) {
        I.i18n = i18n;
    }
}
