// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JBIG2SegmentReader.java

package co.com.pdf.text.pdf.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.codec:
//            JBIG2SegmentReader

public static class JBIG2SegmentReader$JBIG2Page
{

    public byte[] getData(boolean for_embedding)
        throws IOException
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Iterator i$ = segs.keySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Integer sn = (Integer)i$.next();
            nt s = (nt)segs.get(sn);
            if(!for_embedding || s.type != 51 && s.type != 49)
            {
                if(for_embedding)
                {
                    byte headerData_emb[] = JBIG2SegmentReader.copyByteArray(s.headerData);
                    if(s.page_association_size)
                    {
                        headerData_emb[s.page_association_offset] = 0;
                        headerData_emb[s.page_association_offset + 1] = 0;
                        headerData_emb[s.page_association_offset + 2] = 0;
                        headerData_emb[s.page_association_offset + 3] = 1;
                    } else
                    {
                        headerData_emb[s.page_association_offset] = 1;
                    }
                    os.write(headerData_emb);
                } else
                {
                    os.write(s.headerData);
                }
                os.write(s.data);
            }
        } while(true);
        os.close();
        return os.toByteArray();
    }

    public void addSegment(nt s)
    {
        segs.put(Integer.valueOf(s.segmentNumber), s);
    }

    public final int page;
    private final JBIG2SegmentReader sr;
    private final SortedMap segs = new TreeMap();
    public int pageBitmapWidth;
    public int pageBitmapHeight;

    public JBIG2SegmentReader$JBIG2Page(int page, JBIG2SegmentReader sr)
    {
        pageBitmapWidth = -1;
        pageBitmapHeight = -1;
        this.page = page;
        this.sr = sr;
    }
}
