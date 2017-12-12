// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextBundle.java

package co.org.bouncy.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package co.org.bouncy.i18n:
//            LocalizedMessage, MissingEntryException

public class TextBundle extends LocalizedMessage
{

    public TextBundle(String resource, String id)
        throws NullPointerException
    {
        super(resource, id);
    }

    public TextBundle(String resource, String id, String encoding)
        throws NullPointerException, UnsupportedEncodingException
    {
        super(resource, id, encoding);
    }

    public TextBundle(String resource, String id, Object arguments[])
        throws NullPointerException
    {
        super(resource, id, arguments);
    }

    public TextBundle(String resource, String id, String encoding, Object arguments[])
        throws NullPointerException, UnsupportedEncodingException
    {
        super(resource, id, encoding, arguments);
    }

    public String getText(Locale loc, TimeZone timezone)
        throws MissingEntryException
    {
        return getEntry("text", loc, timezone);
    }

    public String getText(Locale loc)
        throws MissingEntryException
    {
        return getEntry("text", loc, TimeZone.getDefault());
    }

    public static final String TEXT_ENTRY = "text";
}
