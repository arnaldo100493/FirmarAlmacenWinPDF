// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocationTextExtractionStrategy.java

package co.com.pdf.text.pdf.parser;

import java.io.PrintStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            Matrix, TextExtractionStrategy, LineSegment, TextRenderInfo, 
//            ImageRenderInfo, Vector

public class LocationTextExtractionStrategy
    implements TextExtractionStrategy
{
    public static interface TextChunkFilter
    {

        public abstract boolean accept(TextChunk textchunk);
    }

    public static class TextChunk
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

        public boolean sameLine(TextChunk as)
        {
            if(orientationMagnitude != as.orientationMagnitude)
                return false;
            return distPerpendicular == as.distPerpendicular;
        }

        public float distanceFromEndOf(TextChunk other)
        {
            float distance = distParallelStart - other.distParallelEnd;
            return distance;
        }

        public int compareTo(TextChunk rhs)
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
            return compareTo((TextChunk)x0);
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



        public TextChunk(String string, Vector startLocation, Vector endLocation, float charSpaceWidth)
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


    public LocationTextExtractionStrategy()
    {
    }

    public void beginTextBlock()
    {
    }

    public void endTextBlock()
    {
    }

    private boolean startsWithSpace(String str)
    {
        if(str.length() == 0)
            return false;
        else
            return str.charAt(0) == ' ';
    }

    private boolean endsWithSpace(String str)
    {
        if(str.length() == 0)
            return false;
        else
            return str.charAt(str.length() - 1) == ' ';
    }

    private List filterTextChunks(List textChunks, TextChunkFilter filter)
    {
        if(filter == null)
            return textChunks;
        List filtered = new ArrayList();
        Iterator i$ = textChunks.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            TextChunk textChunk = (TextChunk)i$.next();
            if(filter.accept(textChunk))
                filtered.add(textChunk);
        } while(true);
        return filtered;
    }

    protected boolean isChunkAtWordBoundary(TextChunk chunk, TextChunk previousChunk)
    {
        float dist = chunk.distanceFromEndOf(previousChunk);
        return dist < -chunk.getCharSpaceWidth() || dist > chunk.getCharSpaceWidth() / 2.0F;
    }

    public String getResultantText(TextChunkFilter chunkFilter)
    {
        if(DUMP_STATE)
            dumpState();
        List filteredTextChunks = filterTextChunks(locationalResult, chunkFilter);
        Collections.sort(filteredTextChunks);
        StringBuffer sb = new StringBuffer();
        TextChunk lastChunk = null;
        TextChunk chunk;
        for(Iterator i$ = filteredTextChunks.iterator(); i$.hasNext(); lastChunk = chunk)
        {
            chunk = (TextChunk)i$.next();
            if(lastChunk == null)
            {
                sb.append(chunk.text);
                continue;
            }
            if(chunk.sameLine(lastChunk))
            {
                if(isChunkAtWordBoundary(chunk, lastChunk) && !startsWithSpace(chunk.text) && !endsWithSpace(lastChunk.text))
                    sb.append(' ');
                sb.append(chunk.text);
            } else
            {
                sb.append('\n');
                sb.append(chunk.text);
            }
        }

        return sb.toString();
    }

    public String getResultantText()
    {
        return getResultantText(null);
    }

    private void dumpState()
    {
        for(Iterator iterator = locationalResult.iterator(); iterator.hasNext(); System.out.println())
        {
            TextChunk location = (TextChunk)iterator.next();
            location.printDiagnostics();
        }

    }

    public void renderText(TextRenderInfo renderInfo)
    {
        LineSegment segment = renderInfo.getBaseline();
        if(renderInfo.getRise() != 0.0F)
        {
            Matrix riseOffsetTransform = new Matrix(0.0F, -renderInfo.getRise());
            segment = segment.transformBy(riseOffsetTransform);
        }
        TextChunk location = new TextChunk(renderInfo.getText(), segment.getStartPoint(), segment.getEndPoint(), renderInfo.getSingleSpaceWidth());
        locationalResult.add(location);
    }

    public void renderImage(ImageRenderInfo imagerenderinfo)
    {
    }

    static boolean DUMP_STATE = false;
    private final List locationalResult = new ArrayList();

}
