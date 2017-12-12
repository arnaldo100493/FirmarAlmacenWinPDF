// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTMLNewLineHandler.java

package co.com.pdf.text.xml.simpleparser.handler;

import co.com.pdf.text.xml.simpleparser.NewLineHandler;
import java.util.HashSet;
import java.util.Set;

public class HTMLNewLineHandler
    implements NewLineHandler
{

    public HTMLNewLineHandler()
    {
        newLineTags.add("p");
        newLineTags.add("blockquote");
        newLineTags.add("br");
    }

    public boolean isNewLineTag(String tag)
    {
        return newLineTags.contains(tag);
    }

    private final Set newLineTags = new HashSet();
}
