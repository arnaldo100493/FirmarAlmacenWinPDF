// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm

public static class XfaForm$AcroFieldsSearch extends XfaForm.Xml2Som
{

    public HashMap getAcroShort2LongName()
    {
        return acroShort2LongName;
    }

    public void setAcroShort2LongName(HashMap acroShort2LongName)
    {
        this.acroShort2LongName = acroShort2LongName;
    }

    private HashMap acroShort2LongName;

    public XfaForm$AcroFieldsSearch(Collection items)
    {
        inverseSearch = new HashMap();
        acroShort2LongName = new HashMap();
        String itemName;
        String itemShort;
        for(Iterator i$ = items.iterator(); i$.hasNext(); inverseSearchAdd(inverseSearch, splitParts(itemShort), itemName))
        {
            String string = (String)i$.next();
            itemName = string;
            itemShort = getShortName(itemName);
            acroShort2LongName.put(itemShort, itemName);
        }

    }
}
