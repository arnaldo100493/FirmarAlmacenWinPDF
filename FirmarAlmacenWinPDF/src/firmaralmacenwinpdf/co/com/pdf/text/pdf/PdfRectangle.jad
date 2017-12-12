// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfRectangle.java

package co.com.pdf.text.pdf;

import co.com.pdf.awt.geom.AffineTransform;
import co.com.pdf.text.Rectangle;

// Referenced classes of package co.com.pdf.text.pdf:
//            NumberArray, PdfNumber, PdfObject

public class PdfRectangle extends NumberArray
{

    public PdfRectangle(float llx, float lly, float urx, float ury, int rotation)
    {
        super(new float[0]);
        this.llx = 0.0F;
        this.lly = 0.0F;
        this.urx = 0.0F;
        this.ury = 0.0F;
        if(rotation == 90 || rotation == 270)
        {
            this.llx = lly;
            this.lly = llx;
            this.urx = ury;
            this.ury = urx;
        } else
        {
            this.llx = llx;
            this.lly = lly;
            this.urx = urx;
            this.ury = ury;
        }
        super.add(new PdfNumber(this.llx));
        super.add(new PdfNumber(this.lly));
        super.add(new PdfNumber(this.urx));
        super.add(new PdfNumber(this.ury));
    }

    public PdfRectangle(float llx, float lly, float urx, float ury)
    {
        this(llx, lly, urx, ury, 0);
    }

    public PdfRectangle(float urx, float ury, int rotation)
    {
        this(0.0F, 0.0F, urx, ury, rotation);
    }

    public PdfRectangle(float urx, float ury)
    {
        this(0.0F, 0.0F, urx, ury, 0);
    }

    public PdfRectangle(Rectangle rectangle, int rotation)
    {
        this(rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop(), rotation);
    }

    public PdfRectangle(Rectangle rectangle)
    {
        this(rectangle.getLeft(), rectangle.getBottom(), rectangle.getRight(), rectangle.getTop(), 0);
    }

    public Rectangle getRectangle()
    {
        return new Rectangle(left(), bottom(), right(), top());
    }

    public boolean add(PdfObject object)
    {
        return false;
    }

    public boolean add(float values[])
    {
        return false;
    }

    public boolean add(int values[])
    {
        return false;
    }

    public void addFirst(PdfObject pdfobject)
    {
    }

    public float left()
    {
        return llx;
    }

    public float right()
    {
        return urx;
    }

    public float top()
    {
        return ury;
    }

    public float bottom()
    {
        return lly;
    }

    public float left(int margin)
    {
        return llx + (float)margin;
    }

    public float right(int margin)
    {
        return urx - (float)margin;
    }

    public float top(int margin)
    {
        return ury - (float)margin;
    }

    public float bottom(int margin)
    {
        return lly + (float)margin;
    }

    public float width()
    {
        return urx - llx;
    }

    public float height()
    {
        return ury - lly;
    }

    public PdfRectangle rotate()
    {
        return new PdfRectangle(lly, llx, ury, urx, 0);
    }

    public PdfRectangle transform(AffineTransform transform)
    {
        float pts[] = {
            llx, lly, urx, ury
        };
        transform.transform(pts, 0, pts, 0, 2);
        float dstPts[] = {
            pts[0], pts[1], pts[2], pts[3]
        };
        if(pts[0] > pts[2])
        {
            dstPts[0] = pts[2];
            dstPts[2] = pts[0];
        }
        if(pts[1] > pts[3])
        {
            dstPts[1] = pts[3];
            dstPts[3] = pts[1];
        }
        return new PdfRectangle(dstPts[0], dstPts[1], dstPts[2], dstPts[3]);
    }

    private float llx;
    private float lly;
    private float urx;
    private float ury;
}
