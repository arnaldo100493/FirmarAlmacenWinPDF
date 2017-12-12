// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegionTextRenderFilter.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.awt.geom.Rectangle;
import co.com.pdf.awt.geom.Rectangle2D;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            RenderFilter, TextRenderInfo, LineSegment, Vector

public class RegionTextRenderFilter extends RenderFilter
{

    public RegionTextRenderFilter(Rectangle2D filterRect)
    {
        this.filterRect = filterRect;
    }

    public RegionTextRenderFilter(co.com.pdf.text.Rectangle filterRect)
    {
        this.filterRect = new Rectangle(filterRect);
    }

    public boolean allowText(TextRenderInfo renderInfo)
    {
        LineSegment segment = renderInfo.getBaseline();
        Vector startPoint = segment.getStartPoint();
        Vector endPoint = segment.getEndPoint();
        float x1 = startPoint.get(0);
        float y1 = startPoint.get(1);
        float x2 = endPoint.get(0);
        float y2 = endPoint.get(1);
        return filterRect.intersectsLine(x1, y1, x2, y2);
    }

    private final Rectangle2D filterRect;
}
