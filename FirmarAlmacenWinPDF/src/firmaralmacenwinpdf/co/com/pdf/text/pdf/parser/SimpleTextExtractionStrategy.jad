// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleTextExtractionStrategy.java

package co.com.pdf.text.pdf.parser;


// Referenced classes of package co.com.pdf.text.pdf.parser:
//            TextExtractionStrategy, TextRenderInfo, LineSegment, Vector, 
//            ImageRenderInfo

public class SimpleTextExtractionStrategy
    implements TextExtractionStrategy
{

    public SimpleTextExtractionStrategy()
    {
    }

    public void beginTextBlock()
    {
    }

    public void endTextBlock()
    {
    }

    public String getResultantText()
    {
        return result.toString();
    }

    protected final void appendTextChunk(CharSequence text)
    {
        result.append(text);
    }

    public void renderText(TextRenderInfo renderInfo)
    {
        boolean firstRender = result.length() == 0;
        boolean hardReturn = false;
        LineSegment segment = renderInfo.getBaseline();
        Vector start = segment.getStartPoint();
        Vector end = segment.getEndPoint();
        if(!firstRender)
        {
            Vector x0 = start;
            Vector x1 = lastStart;
            Vector x2 = lastEnd;
            float dist = x2.subtract(x1).cross(x1.subtract(x0)).lengthSquared() / x2.subtract(x1).lengthSquared();
            float sameLineThreshold = 1.0F;
            if(dist > sameLineThreshold)
                hardReturn = true;
        }
        if(hardReturn)
            appendTextChunk("\n");
        else
        if(!firstRender && result.charAt(result.length() - 1) != ' ' && renderInfo.getText().length() > 0 && renderInfo.getText().charAt(0) != ' ')
        {
            float spacing = lastEnd.subtract(start).length();
            if(spacing > renderInfo.getSingleSpaceWidth() / 2.0F)
                appendTextChunk(" ");
        }
        appendTextChunk(renderInfo.getText());
        lastStart = start;
        lastEnd = end;
    }

    public void renderImage(ImageRenderInfo imagerenderinfo)
    {
    }

    private Vector lastStart;
    private Vector lastEnd;
    private final StringBuffer result = new StringBuffer();
}
