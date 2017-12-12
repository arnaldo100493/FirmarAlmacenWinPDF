// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChainedProperties.java

package co.com.pdf.text.html.simpleparser;

import co.com.pdf.text.html.HtmlUtilities;
import java.util.*;

public class ChainedProperties
{
    private static final class TagAttributes
    {

        final String tag;
        final Map attrs;

        TagAttributes(String tag, Map attrs)
        {
            this.tag = tag;
            this.attrs = attrs;
        }
    }


    public ChainedProperties()
    {
        chain = new ArrayList();
    }

    public String getProperty(String key)
    {
        for(int k = chain.size() - 1; k >= 0; k--)
        {
            TagAttributes p = (TagAttributes)chain.get(k);
            Map attrs = p.attrs;
            String ret = (String)attrs.get(key);
            if(ret != null)
                return ret;
        }

        return null;
    }

    public boolean hasProperty(String key)
    {
        for(int k = chain.size() - 1; k >= 0; k--)
        {
            TagAttributes p = (TagAttributes)chain.get(k);
            Map attrs = p.attrs;
            if(attrs.containsKey(key))
                return true;
        }

        return false;
    }

    public void addToChain(String tag, Map props)
    {
        adjustFontSize(props);
        chain.add(new TagAttributes(tag, props));
    }

    public void removeChain(String tag)
    {
        for(int k = chain.size() - 1; k >= 0; k--)
            if(tag.equals(((TagAttributes)chain.get(k)).tag))
            {
                chain.remove(k);
                return;
            }

    }

    protected void adjustFontSize(Map attrs)
    {
        String value = (String)attrs.get("size");
        if(value == null)
            return;
        if(value.endsWith("pt"))
        {
            attrs.put("size", value.substring(0, value.length() - 2));
            return;
        } else
        {
            String old = getProperty("size");
            attrs.put("size", Integer.toString(HtmlUtilities.getIndexedFontSize(value, old)));
            return;
        }
    }

    public List chain;
}
