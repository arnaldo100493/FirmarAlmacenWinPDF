// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MissingEntryException.java

package co.org.bouncy.i18n;

import java.net.URLClassLoader;
import java.util.Locale;

public class MissingEntryException extends RuntimeException
{

    public MissingEntryException(String message, String resource, String key, Locale locale, ClassLoader loader)
    {
        super(message);
        this.resource = resource;
        this.key = key;
        this.locale = locale;
        this.loader = loader;
    }

    public MissingEntryException(String message, Throwable cause, String resource, String key, Locale locale, ClassLoader loader)
    {
        super(message, cause);
        this.resource = resource;
        this.key = key;
        this.locale = locale;
        this.loader = loader;
    }

    public String getKey()
    {
        return key;
    }

    public String getResource()
    {
        return resource;
    }

    public ClassLoader getClassLoader()
    {
        return loader;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public String getDebugMsg()
    {
        if(debugMsg != null) goto _L2; else goto _L1
_L1:
        debugMsg = (new StringBuilder()).append("Can not find entry ").append(key).append(" in resource file ").append(resource).append(" for the locale ").append(locale).append(".").toString();
        if(!(loader instanceof URLClassLoader)) goto _L2; else goto _L3
_L3:
        java.net.URL urls[] = ((URLClassLoader)loader).getURLs();
        new StringBuilder();
        this;
        JVM INSTR dup_x1 ;
        debugMsg;
        append();
        " The following entries in the classpath were searched: ";
        append();
        toString();
        debugMsg;
        int i = 0;
_L4:
        if(i == urls.length)
            break; /* Loop/switch isn't completed */
        new StringBuilder();
        this;
        JVM INSTR dup_x1 ;
        debugMsg;
        append();
        urls[i];
        append();
        " ";
        append();
        toString();
        debugMsg;
        i++;
        if(true) goto _L4; else goto _L2
_L2:
        return debugMsg;
    }

    protected final String resource;
    protected final String key;
    protected final ClassLoader loader;
    protected final Locale locale;
    private String debugMsg;
}
