// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilteredRenderListener.java

package co.com.pdf.text.pdf.parser;


// Referenced classes of package co.com.pdf.text.pdf.parser:
//            RenderListener, RenderFilter, TextRenderInfo, ImageRenderInfo

public class FilteredRenderListener
    implements RenderListener
{

    public transient FilteredRenderListener(RenderListener delegate, RenderFilter filters[])
    {
        _flddelegate = delegate;
        this.filters = filters;
    }

    public void renderText(TextRenderInfo renderInfo)
    {
        RenderFilter arr$[] = filters;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            RenderFilter filter = arr$[i$];
            if(!filter.allowText(renderInfo))
                return;
        }

        _flddelegate.renderText(renderInfo);
    }

    public void beginTextBlock()
    {
        _flddelegate.beginTextBlock();
    }

    public void endTextBlock()
    {
        _flddelegate.endTextBlock();
    }

    public void renderImage(ImageRenderInfo renderInfo)
    {
        RenderFilter arr$[] = filters;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            RenderFilter filter = arr$[i$];
            if(!filter.allowImage(renderInfo))
                return;
        }

        _flddelegate.renderImage(renderInfo);
    }

    private final RenderListener _flddelegate;
    private final RenderFilter filters[];
}
