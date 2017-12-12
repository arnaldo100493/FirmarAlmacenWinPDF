// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAttributeTableGenerationException.java

package co.org.bouncy.cms;


// Referenced classes of package co.org.bouncy.cms:
//            CMSRuntimeException

public class CMSAttributeTableGenerationException extends CMSRuntimeException
{

    public CMSAttributeTableGenerationException(String name)
    {
        super(name);
    }

    public CMSAttributeTableGenerationException(String name, Exception e)
    {
        super(name);
        this.e = e;
    }

    public Exception getUnderlyingException()
    {
        return e;
    }

    public Throwable getCause()
    {
        return e;
    }

    Exception e;
}
