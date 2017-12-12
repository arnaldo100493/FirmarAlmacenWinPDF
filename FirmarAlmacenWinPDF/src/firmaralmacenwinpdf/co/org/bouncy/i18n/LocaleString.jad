// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocaleString.java

package co.org.bouncy.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

// Referenced classes of package co.org.bouncy.i18n:
//            LocalizedMessage

public class LocaleString extends LocalizedMessage
{

    public LocaleString(String resource, String id)
    {
        super(resource, id);
    }

    public LocaleString(String resource, String id, String encoding)
        throws NullPointerException, UnsupportedEncodingException
    {
        super(resource, id, encoding);
    }

    public String getLocaleString(Locale locale)
    {
        return getEntry(null, locale, null);
    }
}
