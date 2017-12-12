// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JBIG2SegmentReader.java

package co.com.pdf.text.pdf.codec;


// Referenced classes of package co.com.pdf.text.pdf.codec:
//            JBIG2SegmentReader

public static class JBIG2SegmentReader$JBIG2Segment
    implements Comparable
{

    public int compareTo(JBIG2SegmentReader$JBIG2Segment s)
    {
        return segmentNumber - s.segmentNumber;
    }

    public volatile int compareTo(Object x0)
    {
        return compareTo((JBIG2SegmentReader$JBIG2Segment)x0);
    }

    public final int segmentNumber;
    public long dataLength;
    public int page;
    public int referredToSegmentNumbers[];
    public boolean segmentRetentionFlags[];
    public int type;
    public boolean deferredNonRetain;
    public int countOfReferredToSegments;
    public byte data[];
    public byte headerData[];
    public boolean page_association_size;
    public int page_association_offset;

    public JBIG2SegmentReader$JBIG2Segment(int segment_number)
    {
        dataLength = -1L;
        page = -1;
        referredToSegmentNumbers = null;
        segmentRetentionFlags = null;
        type = -1;
        deferredNonRetain = false;
        countOfReferredToSegments = -1;
        data = null;
        headerData = null;
        page_association_size = false;
        page_association_offset = -1;
        segmentNumber = segment_number;
    }
}
