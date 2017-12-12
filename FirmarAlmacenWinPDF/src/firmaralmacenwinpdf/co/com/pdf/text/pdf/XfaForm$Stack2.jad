// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;
import java.util.EmptyStackException;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm

public static class XfaForm$Stack2 extends ArrayList
{

    public Object peek()
    {
        if(size() == 0)
            throw new EmptyStackException();
        else
            return get(size() - 1);
    }

    public Object pop()
    {
        if(size() == 0)
        {
            throw new EmptyStackException();
        } else
        {
            Object ret = get(size() - 1);
            remove(size() - 1);
            return ret;
        }
    }

    public Object push(Object item)
    {
        add(item);
        return item;
    }

    public boolean empty()
    {
        return size() == 0;
    }

    private static final long serialVersionUID = 0x9897098898ffc494L;

    public XfaForm$Stack2()
    {
    }
}
