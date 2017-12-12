// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Language.java

package co.com.pdf.text.pdf.fonts.otf;

import java.util.Arrays;
import java.util.List;

public final class Language extends Enum
{

    public static Language[] values()
    {
        return (Language[])$VALUES.clone();
    }

    public static Language valueOf(String name)
    {
        return (Language)Enum.valueOf(co/com/pdf/text/pdf/fonts/otf/Language, name);
    }

    private transient Language(String s, int i, String codes[])
    {
        super(s, i);
        this.codes = Arrays.asList(codes);
    }

    public boolean isSupported(String languageCode)
    {
        return codes.contains(languageCode);
    }

    public static final Language BENGALI;
    private final List codes;
    private static final Language $VALUES[];

    static 
    {
        BENGALI = new Language("BENGALI", 0, new String[] {
            "beng", "bng2"
        });
        $VALUES = (new Language[] {
            BENGALI
        });
    }
}
