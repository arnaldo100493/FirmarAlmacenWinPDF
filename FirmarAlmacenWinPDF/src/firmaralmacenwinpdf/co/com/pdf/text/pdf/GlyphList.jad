// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlyphList.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.io.StreamUtil;
import co.com.pdf.text.pdf.fonts.FontsResourceAnchor;
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfEncodings

public class GlyphList
{

    public GlyphList()
    {
    }

    public static int[] nameToUnicode(String name)
    {
        int v[] = (int[])names2unicode.get(name);
        if(v == null && name.length() == 7 && name.toLowerCase().startsWith("uni"))
            try
            {
                return (new int[] {
                    Integer.parseInt(name.substring(3), 16)
                });
            }
            catch(Exception ex) { }
        return v;
    }

    public static String unicodeToName(int num)
    {
        return (String)unicode2names.get(Integer.valueOf(num));
    }

    private static HashMap unicode2names;
    private static HashMap names2unicode;

    static 
    {
        InputStream is;
        unicode2names = new HashMap();
        names2unicode = new HashMap();
        is = null;
        is = StreamUtil.getResourceStream("co/com/pdf/text/pdf/fonts/glyphlist.txt", (new FontsResourceAnchor()).getClass().getClassLoader());
        if(is == null)
        {
            String msg = "glyphlist.txt not found as resource. (It must exist as resource in the package com.itextpdf.text.pdf.fonts)";
            throw new Exception(msg);
        }
        byte buf[] = new byte[1024];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        do
        {
            int size = is.read(buf);
            if(size < 0)
                break;
            out.write(buf, 0, size);
        } while(true);
        is.close();
        is = null;
        String s = PdfEncodings.convertToString(out.toByteArray(), null);
        StringTokenizer tk = new StringTokenizer(s, "\r\n");
        do
        {
            if(!tk.hasMoreTokens())
                break;
            String line = tk.nextToken();
            if(!line.startsWith("#"))
            {
                StringTokenizer t2 = new StringTokenizer(line, " ;\r\n\t\f");
                String name = null;
                String hex = null;
                if(t2.hasMoreTokens())
                {
                    name = t2.nextToken();
                    if(t2.hasMoreTokens())
                    {
                        hex = t2.nextToken();
                        Integer num = Integer.valueOf(hex, 16);
                        unicode2names.put(num, name);
                        names2unicode.put(name, new int[] {
                            num.intValue()
                        });
                    }
                }
            }
        } while(true);
        Exception e;
        if(is != null)
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_324;
        e;
        System.err.println((new StringBuilder()).append("glyphlist.txt loading error: ").append(e.getMessage()).toString());
        if(is != null)
            try
            {
                is.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_324;
        Exception exception;
        exception;
        if(is != null)
            try
            {
                is.close();
            }
            catch(Exception e) { }
        throw exception;
    }
}
