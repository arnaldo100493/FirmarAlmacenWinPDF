// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTMLFilter.java

package co.org.bouncy.i18n.filter;


// Referenced classes of package co.org.bouncy.i18n.filter:
//            Filter

public class HTMLFilter
    implements Filter
{

    public HTMLFilter()
    {
    }

    public String doFilter(String input)
    {
        StringBuffer buf = new StringBuffer(input);
        for(int i = 0; i < buf.length(); i += 4)
        {
            char ch = buf.charAt(i);
            switch(ch)
            {
            case 60: // '<'
                buf.replace(i, i + 1, "&#60");
                break;

            case 62: // '>'
                buf.replace(i, i + 1, "&#62");
                break;

            case 40: // '('
                buf.replace(i, i + 1, "&#40");
                break;

            case 41: // ')'
                buf.replace(i, i + 1, "&#41");
                break;

            case 35: // '#'
                buf.replace(i, i + 1, "&#35");
                break;

            case 38: // '&'
                buf.replace(i, i + 1, "&#38");
                break;

            case 34: // '"'
                buf.replace(i, i + 1, "&#34");
                break;

            case 39: // '\''
                buf.replace(i, i + 1, "&#39");
                break;

            case 37: // '%'
                buf.replace(i, i + 1, "&#37");
                break;

            case 59: // ';'
                buf.replace(i, i + 1, "&#59");
                break;

            case 43: // '+'
                buf.replace(i, i + 1, "&#43");
                break;

            case 45: // '-'
                buf.replace(i, i + 1, "&#45");
                break;

            case 36: // '$'
            case 42: // '*'
            case 44: // ','
            case 46: // '.'
            case 47: // '/'
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
            case 58: // ':'
            case 61: // '='
            default:
                i -= 3;
                break;
            }
        }

        return buf.toString();
    }

    public String doFilterUrl(String input)
    {
        return doFilter(input);
    }
}
