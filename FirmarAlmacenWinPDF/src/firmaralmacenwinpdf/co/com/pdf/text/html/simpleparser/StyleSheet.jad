// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StyleSheet.java

package co.com.pdf.text.html.simpleparser;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.html.HtmlUtilities;
import java.util.*;

// Referenced classes of package co.com.pdf.text.html.simpleparser:
//            ChainedProperties

public class StyleSheet
{

    public StyleSheet()
    {
        tagMap = new HashMap();
        classMap = new HashMap();
    }

    public void loadTagStyle(String tag, Map attrs)
    {
        tagMap.put(tag.toLowerCase(), attrs);
    }

    public void loadTagStyle(String tag, String key, String value)
    {
        tag = tag.toLowerCase();
        Map styles = (Map)tagMap.get(tag);
        if(styles == null)
        {
            styles = new HashMap();
            tagMap.put(tag, styles);
        }
        styles.put(key, value);
    }

    public void loadStyle(String className, HashMap attrs)
    {
        classMap.put(className.toLowerCase(), attrs);
    }

    public void loadStyle(String className, String key, String value)
    {
        className = className.toLowerCase();
        Map styles = (Map)classMap.get(className);
        if(styles == null)
        {
            styles = new HashMap();
            classMap.put(className, styles);
        }
        styles.put(key, value);
    }

    public void applyStyle(String tag, Map attrs)
    {
        Map map = (Map)tagMap.get(tag.toLowerCase());
        if(map != null)
        {
            Map temp = new HashMap(map);
            temp.putAll(attrs);
            attrs.putAll(temp);
        }
        String cm = (String)attrs.get("class");
        if(cm == null)
            return;
        map = (Map)classMap.get(cm.toLowerCase());
        if(map == null)
        {
            return;
        } else
        {
            attrs.remove("class");
            Map temp = new HashMap(map);
            temp.putAll(attrs);
            attrs.putAll(temp);
            return;
        }
    }

    public static void resolveStyleAttribute(Map h, ChainedProperties chain)
    {
        String style = (String)h.get("style");
        if(style == null)
            return;
        Properties prop = HtmlUtilities.parseAttributes(style);
        Iterator i$ = prop.keySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            String key = (String)element;
            if(key.equals("font-family"))
                h.put("face", prop.getProperty(key));
            else
            if(key.equals("font-size"))
            {
                float actualFontSize = HtmlUtilities.parseLength(chain.getProperty("size"), 12F);
                if(actualFontSize <= 0.0F)
                    actualFontSize = 12F;
                h.put("size", (new StringBuilder()).append(Float.toString(HtmlUtilities.parseLength(prop.getProperty(key), actualFontSize))).append("pt").toString());
            } else
            if(key.equals("font-style"))
            {
                String ss = prop.getProperty(key).trim().toLowerCase();
                if(ss.equals("italic") || ss.equals("oblique"))
                    h.put("i", null);
            } else
            if(key.equals("font-weight"))
            {
                String ss = prop.getProperty(key).trim().toLowerCase();
                if(ss.equals("bold") || ss.equals("700") || ss.equals("800") || ss.equals("900"))
                    h.put("b", null);
            } else
            if(key.equals("text-decoration"))
            {
                String ss = prop.getProperty(key).trim().toLowerCase();
                if(ss.equals("underline"))
                    h.put("u", null);
            } else
            if(key.equals("color"))
            {
                BaseColor c = HtmlUtilities.decodeColor(prop.getProperty(key));
                if(c != null)
                {
                    int hh = c.getRGB();
                    String hs = Integer.toHexString(hh);
                    hs = (new StringBuilder()).append("000000").append(hs).toString();
                    hs = (new StringBuilder()).append("#").append(hs.substring(hs.length() - 6)).toString();
                    h.put("color", hs);
                }
            } else
            if(key.equals("line-height"))
            {
                String ss = prop.getProperty(key).trim();
                float actualFontSize = HtmlUtilities.parseLength(chain.getProperty("size"), 12F);
                if(actualFontSize <= 0.0F)
                    actualFontSize = 12F;
                float v = HtmlUtilities.parseLength(prop.getProperty(key), actualFontSize);
                if(ss.endsWith("%"))
                {
                    h.put("leading", (new StringBuilder()).append("0,").append(v / 100F).toString());
                    return;
                }
                if("normal".equalsIgnoreCase(ss))
                {
                    h.put("leading", "0,1.5");
                    return;
                }
                h.put("leading", (new StringBuilder()).append(v).append(",0").toString());
            } else
            if(key.equals("text-align"))
            {
                String ss = prop.getProperty(key).trim().toLowerCase();
                h.put("align", ss);
            } else
            if(key.equals("padding-left"))
            {
                String ss = prop.getProperty(key).trim().toLowerCase();
                h.put("indent", Float.toString(HtmlUtilities.parseLength(ss)));
            }
        } while(true);
    }

    protected Map tagMap;
    protected Map classMap;
}
