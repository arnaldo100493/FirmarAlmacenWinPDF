// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkedContentRenderFilter.java

package co.com.pdf.text.pdf.parser;


// Referenced classes of package co.com.pdf.text.pdf.parser:
//            RenderFilter, TextRenderInfo

public class MarkedContentRenderFilter extends RenderFilter
{

    public MarkedContentRenderFilter(int mcid)
    {
        this.mcid = mcid;
    }

    public boolean allowText(TextRenderInfo renderInfo)
    {
        return renderInfo.hasMcid(mcid);
    }

    private int mcid;
}
