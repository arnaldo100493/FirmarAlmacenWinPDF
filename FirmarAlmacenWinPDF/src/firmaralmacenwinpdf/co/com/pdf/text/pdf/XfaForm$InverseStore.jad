// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm

public static class XfaForm$InverseStore
{

    public String getDefaultName()
    {
        XfaForm$InverseStore store = this;
        do
        {
            Object obj = store.follow.get(0);
            if(obj instanceof String)
                return (String)obj;
            store = (XfaForm$InverseStore)obj;
        } while(true);
    }

    public boolean isSimilar(String name)
    {
        int idx = name.indexOf('[');
        name = name.substring(0, idx + 1);
        for(int k = 0; k < part.size(); k++)
            if(((String)part.get(k)).startsWith(name))
                return true;

        return false;
    }

    protected ArrayList part;
    protected ArrayList follow;

    public XfaForm$InverseStore()
    {
        part = new ArrayList();
        follow = new ArrayList();
    }
}
