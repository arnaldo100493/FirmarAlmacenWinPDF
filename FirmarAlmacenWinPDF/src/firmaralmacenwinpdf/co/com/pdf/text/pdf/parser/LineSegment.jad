// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LineSegment.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.awt.geom.Rectangle2D;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Vector, Matrix

public class LineSegment
{

    public LineSegment(Vector startPoint, Vector endPoint)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Vector getStartPoint()
    {
        return startPoint;
    }

    public Vector getEndPoint()
    {
        return endPoint;
    }

    public float getLength()
    {
        return endPoint.subtract(startPoint).length();
    }

    public co.com.pdf.awt.geom.Rectangle2D.Float getBoundingRectange()
    {
        float x1 = getStartPoint().get(0);
        float y1 = getStartPoint().get(1);
        float x2 = getEndPoint().get(0);
        float y2 = getEndPoint().get(1);
        return new co.com.pdf.awt.geom.Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    public LineSegment transformBy(Matrix m)
    {
        Vector newStart = startPoint.cross(m);
        Vector newEnd = endPoint.cross(m);
        return new LineSegment(newStart, newEnd);
    }

    private final Vector startPoint;
    private final Vector endPoint;
}
