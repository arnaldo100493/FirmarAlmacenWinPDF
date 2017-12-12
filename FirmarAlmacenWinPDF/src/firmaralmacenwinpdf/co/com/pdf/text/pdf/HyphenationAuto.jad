// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HyphenationAuto.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.pdf.hyphenation.Hyphenation;
import co.com.pdf.text.pdf.hyphenation.Hyphenator;

// Referenced classes of package co.com.pdf.text.pdf:
//            HyphenationEvent, BaseFont

public class HyphenationAuto
    implements HyphenationEvent
{

    public HyphenationAuto(String lang, String country, int leftMin, int rightMin)
    {
        hyphenator = new Hyphenator(lang, country, leftMin, rightMin);
    }

    public String getHyphenSymbol()
    {
        return "-";
    }

    public String getHyphenatedWordPre(String word, BaseFont font, float fontSize, float remainingWidth)
    {
        post = word;
        String hyphen = getHyphenSymbol();
        float hyphenWidth = font.getWidthPoint(hyphen, fontSize);
        if(hyphenWidth > remainingWidth)
            return "";
        Hyphenation hyphenation = hyphenator.hyphenate(word);
        if(hyphenation == null)
            return "";
        int len = hyphenation.length();
        int k;
        for(k = 0; k < len && font.getWidthPoint(hyphenation.getPreHyphenText(k), fontSize) + hyphenWidth <= remainingWidth; k++);
        if(--k < 0)
        {
            return "";
        } else
        {
            post = hyphenation.getPostHyphenText(k);
            return (new StringBuilder()).append(hyphenation.getPreHyphenText(k)).append(hyphen).toString();
        }
    }

    public String getHyphenatedWordPost()
    {
        return post;
    }

    protected Hyphenator hyphenator;
    protected String post;
}
