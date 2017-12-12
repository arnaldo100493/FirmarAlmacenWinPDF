// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocationTextExtractionStrategy.java

package co.com.pdf.text.pdf.parser;

import java.io.PrintStream;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Vector, LocationTextExtractionStrategy

public static class LocationTextExtractionStrategy$TextChunk
    implements Comparable
{

    public Vector getStartLocation()
    {
        return startLocation;
    }

    public Vector getEndLocation()
    {
        return endLocation;
    }

    public String getText()
    {
        return text;
    }

    public float getCharSpaceWidth()
    {
        return charSpaceWidth;
    }

    private void printDiagnostics()
    {
        System.out.println((new StringBuilder()).append("Text (@").append(startLocation).append(" -> ").append(endLocation).append("): ").append(text).toString());
        System.out.println((new StringBuilder()).append("orientationMagnitude: ").append(orientationMagnitude).toString());
        System.out.println((new StringBuilder()).append("distPerpendicular: ").append(distPerpendicular).toString());
        System.out.println((new StringBuilder()).append("distParallel: ").append(distParallelStart).toString());
    }

    public boolean sameLine(LocationTextExtractionStrategy$TextChunk as)
    {
        if(orientationMagnitude != as.orientationMagnitude)
            return false;
        return distPerpendicular == as.distPerpendicular;
    }

    public float distanceFromEndOf(LocationTextExtractionStrategy$TextChunk other)
    {
        float distance = distParallelStart - other.distParallelEnd;
        return distance;
    }

    public int compareTo(LocationTextExtractionStrategy$TextChunk rhs)
    {
        if(this == rhs)
            return 0;
        int rslt = compareInts(orientationMagnitude, rhs.orientationMagnitude);
        if(rslt != 0)
            return rslt;
        rslt = compareInts(distPerpendicular, rhs.distPerpendicular);
        if(rslt != 0)
            return rslt;
        else
            return Float.compare(distParallelStart, rhs.distParallelStart);
    }

    private static int compareInts(int int1, int int2)
    {
        return int1 != int2 ? int1 >= int2 ? 1 : -1 : 0;
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((LocationTextExtractionStrategy$TextChunk)x0);
    }

    private final String text;
    private final Vector startLocation;
    private final Vector endLocation;
    private final Vector orientationVector;
    private final int orientationMagnitude;
    private final int distPerpendicular;
    private final float distParallelStart;
    private final float distParallelEnd;
    private final float charSpaceWidth;



    public LocationTextExtractionStrategy$TextChunk(String string, Vector startLocation, Vector endLocation, float charSpaceWidth)
    {
        text = string;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.charSpaceWidth = charSpaceWidth;
        Vector oVector = endLocation.subtract(startLocation);
        if(oVector.length() == 0.0F)
            oVector = new Vector(1.0F, 0.0F, 0.0F);
        orientationVector = oVector.normalize();
        orientationMagnitude = (int)(Math.atan2(orientationVector.get(1), orientationVector.get(0)) * 1000D);
        Vector origin = new Vector(0.0F, 0.0F, 1.0F);
        distPerpendicular = (int)startLocation.subtract(origin).cross(orientationVector).get(2);
        distParallelStart = orientationVector.dot(startLocation);
        distParallelEnd = orientationVector.dot(endLocation);
    }
}
