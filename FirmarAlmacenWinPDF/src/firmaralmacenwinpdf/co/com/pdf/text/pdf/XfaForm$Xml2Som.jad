// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm

public static class XfaForm$Xml2Som
{

    public static String escapeSom(String s)
    {
        if(s == null)
            return "";
        int idx = s.indexOf('.');
        if(idx < 0)
            return s;
        StringBuffer sb = new StringBuffer();
        int last = 0;
        for(; idx >= 0; idx = s.indexOf('.', idx + 1))
        {
            sb.append(s.substring(last, idx));
            sb.append('\\');
            last = idx;
        }

        sb.append(s.substring(last));
        return sb.toString();
    }

    public static String unescapeSom(String s)
    {
        int idx = s.indexOf('\\');
        if(idx < 0)
            return s;
        StringBuffer sb = new StringBuffer();
        int last = 0;
        for(; idx >= 0; idx = s.indexOf('\\', idx + 1))
        {
            sb.append(s.substring(last, idx));
            last = idx + 1;
        }

        sb.append(s.substring(last));
        return sb.toString();
    }

    protected String printStack()
    {
        if(stack.empty())
            return "";
        StringBuffer s = new StringBuffer();
        for(int k = 0; k < stack.size(); k++)
            s.append('.').append((String)stack.get(k));

        return s.substring(1);
    }

    public static String getShortName(String s)
    {
        int idx = s.indexOf(".#subform[");
        if(idx < 0)
            return s;
        int last = 0;
        StringBuffer sb = new StringBuffer();
        for(; idx >= 0; idx = s.indexOf(".#subform[", last))
        {
            sb.append(s.substring(last, idx));
            idx = s.indexOf("]", idx + 10);
            if(idx < 0)
                return sb.toString();
            last = idx + 1;
        }

        sb.append(s.substring(last));
        return sb.toString();
    }

    public void inverseSearchAdd(String unstack)
    {
        inverseSearchAdd(inverseSearch, stack, unstack);
    }

    public static void inverseSearchAdd(HashMap inverseSearch, XfaForm.Stack2 stack, String unstack)
    {
        String last = (String)stack.peek();
        tore store = (tore)inverseSearch.get(last);
        if(store == null)
        {
            store = new tore();
            inverseSearch.put(last, store);
        }
        for(int k = stack.size() - 2; k >= 0; k--)
        {
            last = (String)stack.get(k);
            int idx = store.part.indexOf(last);
            tore store2;
            if(idx < 0)
            {
                store.part.add(last);
                store2 = new tore();
                store.follow.add(store2);
            } else
            {
                store2 = (tore)store.follow.get(idx);
            }
            store = store2;
        }

        store.part.add("");
        store.follow.add(unstack);
    }

    public String inverseSearchGlobal(ArrayList parts)
    {
        if(parts.isEmpty())
            return null;
        tore store = (tore)inverseSearch.get(parts.get(parts.size() - 1));
        if(store == null)
            return null;
        for(int k = parts.size() - 2; k >= 0; k--)
        {
            String part = (String)parts.get(k);
            int idx = store.part.indexOf(part);
            if(idx < 0)
                if(store.isSimilar(part))
                    return null;
                else
                    return store.getDefaultName();
            store = (tore)store.follow.get(idx);
        }

        return store.getDefaultName();
    }

    public static XfaForm.Stack2 splitParts(String name)
    {
        for(; name.startsWith("."); name = name.substring(1));
        XfaForm.Stack2 parts = new XfaForm.Stack2();
        int last = 0;
        int pos = 0;
        String part;
        do
        {
            pos = last;
            do
            {
                pos = name.indexOf('.', pos);
                if(pos < 0 || name.charAt(pos - 1) != '\\')
                    break;
                pos++;
            } while(true);
            if(pos < 0)
                break;
            part = name.substring(last, pos);
            if(!part.endsWith("]"))
                part = (new StringBuilder()).append(part).append("[0]").toString();
            parts.add(part);
            last = pos + 1;
        } while(true);
        part = name.substring(last);
        if(!part.endsWith("]"))
            part = (new StringBuilder()).append(part).append("[0]").toString();
        parts.add(part);
        return parts;
    }

    public ArrayList getOrder()
    {
        return order;
    }

    public void setOrder(ArrayList order)
    {
        this.order = order;
    }

    public HashMap getName2Node()
    {
        return name2Node;
    }

    public void setName2Node(HashMap name2Node)
    {
        this.name2Node = name2Node;
    }

    public HashMap getInverseSearch()
    {
        return inverseSearch;
    }

    public void setInverseSearch(HashMap inverseSearch)
    {
        this.inverseSearch = inverseSearch;
    }

    protected ArrayList order;
    protected HashMap name2Node;
    protected HashMap inverseSearch;
    protected XfaForm.Stack2 stack;
    protected int anform;

    public XfaForm$Xml2Som()
    {
    }
}
