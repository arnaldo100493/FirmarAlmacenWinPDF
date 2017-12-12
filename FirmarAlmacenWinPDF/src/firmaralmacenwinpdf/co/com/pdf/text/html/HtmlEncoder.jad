// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HtmlEncoder.java

package co.com.pdf.text.html;

import co.com.pdf.text.BaseColor;
import java.util.HashSet;
import java.util.Set;

public final class HtmlEncoder
{

    private HtmlEncoder()
    {
    }

    public static String encode(String string)
    {
        int n = string.length();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < n; i++)
        {
            char character = string.charAt(i);
            if(character < '\u0100')
                buffer.append(HTML_CODE[character]);
            else
                buffer.append("&#").append(character).append(';');
        }

        return buffer.toString();
    }

    public static String encode(BaseColor color)
    {
        StringBuffer buffer = new StringBuffer("#");
        if(color.getRed() < 16)
            buffer.append('0');
        buffer.append(Integer.toString(color.getRed(), 16));
        if(color.getGreen() < 16)
            buffer.append('0');
        buffer.append(Integer.toString(color.getGreen(), 16));
        if(color.getBlue() < 16)
            buffer.append('0');
        buffer.append(Integer.toString(color.getBlue(), 16));
        return buffer.toString();
    }

    public static String getAlignment(int alignment)
    {
        switch(alignment)
        {
        case 0: // '\0'
            return "left";

        case 1: // '\001'
            return "center";

        case 2: // '\002'
            return "right";

        case 3: // '\003'
        case 8: // '\b'
            return "justify";

        case 4: // '\004'
            return "top";

        case 5: // '\005'
            return "middle";

        case 6: // '\006'
            return "bottom";

        case 7: // '\007'
            return "baseline";
        }
        return "";
    }

    public static boolean isNewLineTag(String tag)
    {
        return NEWLINETAGS.contains(tag);
    }

    private static final String HTML_CODE[];
    private static final Set NEWLINETAGS;

    static 
    {
        HTML_CODE = new String[256];
        for(int i = 0; i < 10; i++)
            HTML_CODE[i] = (new StringBuilder()).append("&#00").append(i).append(";").toString();

        for(int i = 10; i < 32; i++)
            HTML_CODE[i] = (new StringBuilder()).append("&#0").append(i).append(";").toString();

        for(int i = 32; i < 128; i++)
            HTML_CODE[i] = String.valueOf((char)i);

        HTML_CODE[9] = "\t";
        HTML_CODE[10] = "<br />\n";
        HTML_CODE[34] = "&quot;";
        HTML_CODE[38] = "&amp;";
        HTML_CODE[60] = "&lt;";
        HTML_CODE[62] = "&gt;";
        for(int i = 128; i < 256; i++)
            HTML_CODE[i] = (new StringBuilder()).append("&#").append(i).append(";").toString();

        NEWLINETAGS = new HashSet();
        NEWLINETAGS.add("p");
        NEWLINETAGS.add("blockquote");
        NEWLINETAGS.add("br");
    }
}
