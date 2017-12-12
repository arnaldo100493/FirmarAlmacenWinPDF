// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FilteredTextRenderListener.java

package co.com.pdf.text.pdf.parser;


// Referenced classes of package co.com.pdf.text.pdf.parser:
//            FilteredRenderListener, TextExtractionStrategy, RenderFilter

public class FilteredTextRenderListener extends FilteredRenderListener
    implements TextExtractionStrategy
{

    public transient FilteredTextRenderListener(TextExtractionStrategy delegate, RenderFilter filters[])
    {
        super(delegate, filters);
        _flddelegate = delegate;
    }

    public String getResultantText()
    {
        return _flddelegate.getResultantText();
    }

    private final TextExtractionStrategy _flddelegate;
}
