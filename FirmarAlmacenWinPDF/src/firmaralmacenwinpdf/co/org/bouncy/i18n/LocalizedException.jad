// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocalizedException.java

package co.org.bouncy.i18n;

import java.util.Locale;

// Referenced classes of package co.org.bouncy.i18n:
//            ErrorBundle

public class LocalizedException extends Exception
{

    public LocalizedException(ErrorBundle message)
    {
        super(message.getText(Locale.getDefault()));
        this.message = message;
    }

    public LocalizedException(ErrorBundle message, Throwable throwable)
    {
        super(message.getText(Locale.getDefault()));
        this.message = message;
        cause = throwable;
    }

    public ErrorBundle getErrorMessage()
    {
        return message;
    }

    public Throwable getCause()
    {
        return cause;
    }

    protected ErrorBundle message;
    private Throwable cause;
}
