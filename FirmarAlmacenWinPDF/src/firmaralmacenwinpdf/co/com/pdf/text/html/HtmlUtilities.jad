// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HtmlUtilities.java

package co.com.pdf.text.html;

import co.com.pdf.text.BaseColor;
import java.util.*;

// Referenced classes of package co.com.pdf.text.html:
//            WebColors

public class HtmlUtilities
{

    public HtmlUtilities()
    {
    }

    public static float parseLength(String string)
    {
        return parseLength(string, 12F);
    }

    public static float parseLength(String string, float actualFontSize)
    {
        if(string == null)
            return 0.0F;
        Float fl = (Float)sizes.get(string.toLowerCase());
        if(fl != null)
            return fl.floatValue();
        int pos = 0;
        int length = string.length();
        boolean ok = true;
        do
        {
            if(!ok || pos >= length)
                break;
            switch(string.charAt(pos))
            {
            case 43: // '+'
            case 45: // '-'
            case 46: // '.'
            case 48: // '0'
            case 49: // '1'
            case 50: // '2'
            case 51: // '3'
            case 52: // '4'
            case 53: // '5'
            case 54: // '6'
            case 55: // '7'
            case 56: // '8'
            case 57: // '9'
                pos++;
                break;

            case 44: // ','
            case 47: // '/'
            default:
                ok = false;
                break;
            }
        } while(true);
        if(pos == 0)
            return 0.0F;
        if(pos == length)
            return Float.parseFloat((new StringBuilder()).append(string).append("f").toString());
        float f = Float.parseFloat((new StringBuilder()).append(string.substring(0, pos)).append("f").toString());
        string = string.substring(pos);
        if(string.startsWith("in"))
            return f * 72F;
        if(string.startsWith("cm"))
            return (f / 2.54F) * 72F;
        if(string.startsWith("mm"))
            return (f / 25.4F) * 72F;
        if(string.startsWith("pc"))
            return f * 12F;
        if(string.startsWith("em"))
            return f * actualFontSize;
        if(string.startsWith("ex"))
            return (f * actualFontSize) / 2.0F;
        else
            return f;
    }

    public static BaseColor decodeColor(String s)
    {
        if(s == null)
            return null;
        s = s.toLowerCase().trim();
        try
        {
            return WebColors.getRGBColor(s);
        }
        catch(IllegalArgumentException iae)
        {
            return null;
        }
    }

    public static Properties parseAttributes(String string)
    {
        Properties result = new Properties();
        if(string == null)
            return result;
        StringTokenizer keyValuePairs = new StringTokenizer(string, ";");
        do
        {
            if(!keyValuePairs.hasMoreTokens())
                break;
            StringTokenizer keyValuePair = new StringTokenizer(keyValuePairs.nextToken(), ":");
            if(keyValuePair.hasMoreTokens())
            {
                String key = keyValuePair.nextToken().trim();
                if(keyValuePair.hasMoreTokens())
                {
                    String value = keyValuePair.nextToken().trim();
                    if(value.startsWith("\""))
                        value = value.substring(1);
                    if(value.endsWith("\""))
                        value = value.substring(0, value.length() - 1);
                    result.setProperty(key.toLowerCase(), value);
                }
            }
        } while(true);
        return result;
    }

    public static String removeComment(String string, String startComment, String endComment)
    {
        StringBuffer result = new StringBuffer();
        int pos = 0;
        int end = endComment.length();
        for(int start = string.indexOf(startComment, pos); start > -1; start = string.indexOf(startComment, pos))
        {
            result.append(string.substring(pos, start));
            pos = string.indexOf(endComment, start) + end;
        }

        result.append(string.substring(pos));
        return result.toString();
    }

    public static String eliminateWhiteSpace(String content)
    {
        StringBuffer buf = new StringBuffer();
        int len = content.length();
        boolean newline = false;
        for(int i = 0; i < len; i++)
        {
            char character;
            switch(character = content.charAt(i))
            {
            case 9: // '\t'
            case 13: // '\r'
                break;

            case 32: // ' '
                if(!newline)
                    buf.append(character);
                break;

            case 10: // '\n'
                if(i > 0)
                {
                    newline = true;
                    buf.append(' ');
                }
                break;

            default:
                newline = false;
                buf.append(character);
                break;
            }
        }

        return buf.toString();
    }

    public static int getIndexedFontSize(String value, String previous)
    {
        int sIndex = 0;
        if(value.startsWith("+") || value.startsWith("-"))
        {
            if(previous == null)
                previous = "12";
            int c = (int)Float.parseFloat(previous);
            int k = FONTSIZES.length - 1;
            do
            {
                if(k < 0)
                    break;
                if(c >= FONTSIZES[k])
                {
                    sIndex = k;
                    break;
                }
                k--;
            } while(true);
            int diff = Integer.parseInt(value.startsWith("+") ? value.substring(1) : value);
            sIndex += diff;
        } else
        {
            try
            {
                sIndex = Integer.parseInt(value) - 1;
            }
            catch(NumberFormatException nfe)
            {
                sIndex = 0;
            }
        }
        if(sIndex < 0)
            sIndex = 0;
        else
        if(sIndex >= FONTSIZES.length)
            sIndex = FONTSIZES.length - 1;
        return FONTSIZES[sIndex];
    }

    public static int alignmentValue(String alignment)
    {
        if(alignment == null)
            return -1;
        if("center".equalsIgnoreCase(alignment))
            return 1;
        if("left".equalsIgnoreCase(alignment))
            return 0;
        if("right".equalsIgnoreCase(alignment))
            return 2;
        if("justify".equalsIgnoreCase(alignment))
            return 3;
        if("JustifyAll".equalsIgnoreCase(alignment))
            return 8;
        if("top".equalsIgnoreCase(alignment))
            return 4;
        if("middle".equalsIgnoreCase(alignment))
            return 5;
        if("bottom".equalsIgnoreCase(alignment))
            return 6;
        return !"baseline".equalsIgnoreCase(alignment) ? -1 : 7;
    }

    public static final float DEFAULT_FONT_SIZE = 12F;
    private static HashMap sizes;
    public static final int FONTSIZES[] = {
        8, 10, 12, 14, 18, 24, 36
    };

    static 
    {
        sizes = new HashMap();
        sizes.put("xx-small", new Float(4F));
        sizes.put("x-small", new Float(6F));
        sizes.put("small", new Float(8F));
        sizes.put("medium", new Float(10F));
        sizes.put("large", new Float(13F));
        sizes.put("x-large", new Float(18F));
        sizes.put("xx-large", new Float(26F));
    }
}
