// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageLocalization.java

package co.com.pdf.text.error_messages;

import co.com.pdf.text.io.StreamUtil;
import java.io.*;
import java.util.HashMap;

public final class MessageLocalization
{

    private MessageLocalization()
    {
    }

    public static String getMessage(String key)
    {
        HashMap cl = currentLanguage;
        String val;
        if(cl != null)
        {
            val = (String)cl.get(key);
            if(val != null)
                return val;
        }
        cl = defaultLanguage;
        val = (String)cl.get(key);
        if(val != null)
            return val;
        else
            return (new StringBuilder()).append("No message found for ").append(key).toString();
    }

    public static String getComposedMessage(String key, int p1)
    {
        return getComposedMessage(key, new Object[] {
            String.valueOf(p1), null, null, null
        });
    }

    public static transient String getComposedMessage(String key, Object param[])
    {
        String msg = getMessage(key);
        if(null != param)
        {
            int i = 1;
            Object arr$[] = param;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Object o = arr$[i$];
                if(null != o)
                    msg = msg.replace((new StringBuilder()).append("{").append(i).append("}").toString(), o.toString());
                i++;
            }

        }
        return msg;
    }

    public static boolean setLanguage(String language, String country)
        throws IOException
    {
        HashMap lang = getLanguageMessages(language, country);
        if(lang == null)
        {
            return false;
        } else
        {
            currentLanguage = lang;
            return true;
        }
    }

    public static void setMessages(Reader r)
        throws IOException
    {
        currentLanguage = readLanguageStream(r);
    }

    private static HashMap getLanguageMessages(String language, String country)
        throws IOException
    {
        InputStream is;
        if(language == null)
            throw new IllegalArgumentException("The language cannot be null.");
        is = null;
        HashMap hashmap;
        String file;
        if(country != null)
            file = (new StringBuilder()).append(language).append("_").append(country).append(".lng").toString();
        else
            file = (new StringBuilder()).append(language).append(".lng").toString();
        is = StreamUtil.getResourceStream((new StringBuilder()).append("com/itextpdf/text/l10n/error/").append(file).toString(), (new MessageLocalization()).getClass().getClassLoader());
        if(is == null)
            break MISSING_BLOCK_LABEL_135;
        hashmap = readLanguageStream(is);
        try
        {
            if(null != is)
                is.close();
        }
        catch(Exception exx) { }
        return hashmap;
        if(country != null)
            break MISSING_BLOCK_LABEL_159;
        hashmap = null;
        try
        {
            if(null != is)
                is.close();
        }
        catch(Exception exx) { }
        return hashmap;
        String file = (new StringBuilder()).append(language).append(".lng").toString();
        is = StreamUtil.getResourceStream((new StringBuilder()).append("com/itextpdf/text/l10n/error/").append(file).toString(), (new MessageLocalization()).getClass().getClassLoader());
        if(is == null)
            break MISSING_BLOCK_LABEL_242;
        hashmap = readLanguageStream(is);
        try
        {
            if(null != is)
                is.close();
        }
        catch(Exception exx) { }
        return hashmap;
        hashmap = null;
        try
        {
            if(null != is)
                is.close();
        }
        catch(Exception exx) { }
        return hashmap;
        Exception exception;
        exception;
        try
        {
            if(null != is)
                is.close();
        }
        catch(Exception exx) { }
        throw exception;
    }

    private static HashMap readLanguageStream(InputStream is)
        throws IOException
    {
        return readLanguageStream(((Reader) (new InputStreamReader(is, "UTF-8"))));
    }

    private static HashMap readLanguageStream(Reader r)
        throws IOException
    {
        HashMap lang = new HashMap();
        BufferedReader br = new BufferedReader(r);
        do
        {
            String line;
            if((line = br.readLine()) == null)
                break;
            int idxeq = line.indexOf('=');
            if(idxeq >= 0)
            {
                String key = line.substring(0, idxeq).trim();
                if(!key.startsWith("#"))
                    lang.put(key, line.substring(idxeq + 1));
            }
        } while(true);
        return lang;
    }

    private static HashMap defaultLanguage;
    private static HashMap currentLanguage;
    private static final String BASE_PATH = "com/itextpdf/text/l10n/error/";

    static 
    {
        defaultLanguage = new HashMap();
        try
        {
            defaultLanguage = getLanguageMessages("en", null);
        }
        catch(Exception ex) { }
        if(defaultLanguage == null)
            defaultLanguage = new HashMap();
    }
}
