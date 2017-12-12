// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SQLFilter.java

package co.org.bouncy.i18n.filter;


// Referenced classes of package co.org.bouncy.i18n.filter:
//            Filter

public class SQLFilter
    implements Filter
{

    public SQLFilter()
    {
    }

    public String doFilter(String input)
    {
        StringBuffer buf = new StringBuffer(input);
        for(int i = 0; i < buf.length(); i++)
        {
            char ch = buf.charAt(i);
            switch(ch)
            {
            case 39: // '\''
                buf.replace(i, i + 1, "\\'");
                i++;
                break;

            case 34: // '"'
                buf.replace(i, i + 1, "\\\"");
                i++;
                break;

            case 61: // '='
                buf.replace(i, i + 1, "\\=");
                i++;
                break;

            case 45: // '-'
                buf.replace(i, i + 1, "\\-");
                i++;
                break;

            case 47: // '/'
                buf.replace(i, i + 1, "\\/");
                i++;
                break;

            case 92: // '\\'
                buf.replace(i, i + 1, "\\\\");
                i++;
                break;

            case 59: // ';'
                buf.replace(i, i + 1, "\\;");
                i++;
                break;

            case 13: // '\r'
                buf.replace(i, i + 1, "\\r");
                i++;
                break;

            case 10: // '\n'
                buf.replace(i, i + 1, "\\n");
                i++;
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
