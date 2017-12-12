// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JBIG2SegmentReader.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class JBIG2SegmentReader
{
    public static class JBIG2Page
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
                JBIG2Segment s = (JBIG2Segment)segs.get(sn);
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

        public void addSegment(JBIG2Segment s)
        {
            segs.put(Integer.valueOf(s.segmentNumber), s);
        }

        public final int page;
        private final JBIG2SegmentReader sr;
        private final SortedMap segs = new TreeMap();
        public int pageBitmapWidth;
        public int pageBitmapHeight;

        public JBIG2Page(int page, JBIG2SegmentReader sr)
        {
            pageBitmapWidth = -1;
            pageBitmapHeight = -1;
            this.page = page;
            this.sr = sr;
        }
    }

    public static class JBIG2Segment
        implements Comparable
    {

        public int compareTo(JBIG2Segment s)
        {
            return segmentNumber - s.segmentNumber;
        }

        public volatile int compareTo(Object x0)
        {
            return compareTo((JBIG2Segment)x0);
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

        public JBIG2Segment(int segment_number)
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


    public JBIG2SegmentReader(RandomAccessFileOrArray ra)
        throws IOException
    {
        number_of_pages = -1;
        read = false;
        this.ra = ra;
    }

    public static byte[] copyByteArray(byte b[])
    {
        byte bc[] = new byte[b.length];
        System.arraycopy(b, 0, bc, 0, b.length);
        return bc;
    }

    public void read()
        throws IOException
    {
        if(read)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("already.attempted.a.read.on.this.jbig2.file", new Object[0]));
        read = true;
        readFileHeader();
        if(sequential)
        {
            do
            {
                JBIG2Segment tmp = readHeader();
                readSegment(tmp);
                segments.put(Integer.valueOf(tmp.segmentNumber), tmp);
            } while(ra.getFilePointer() < ra.length());
        } else
        {
            JBIG2Segment tmp;
            do
            {
                tmp = readHeader();
                segments.put(Integer.valueOf(tmp.segmentNumber), tmp);
            } while(tmp.type != 51);
            for(Iterator segs = segments.keySet().iterator(); segs.hasNext(); readSegment((JBIG2Segment)segments.get(segs.next())));
        }
    }

    void readSegment(JBIG2Segment s)
        throws IOException
    {
        int ptr = (int)ra.getFilePointer();
        if(s.dataLength == 0xffffffffL)
            return;
        byte data[] = new byte[(int)s.dataLength];
        ra.read(data);
        s.data = data;
        if(s.type == 48)
        {
            int last = (int)ra.getFilePointer();
            ra.seek(ptr);
            int page_bitmap_width = ra.readInt();
            int page_bitmap_height = ra.readInt();
            ra.seek(last);
            JBIG2Page p = (JBIG2Page)pages.get(Integer.valueOf(s.page));
            if(p == null)
                throw new IllegalStateException(MessageLocalization.getComposedMessage("referring.to.widht.height.of.page.we.havent.seen.yet.1", s.page));
            p.pageBitmapWidth = page_bitmap_width;
            p.pageBitmapHeight = page_bitmap_height;
        }
    }

    JBIG2Segment readHeader()
        throws IOException
    {
        int ptr = (int)ra.getFilePointer();
        int segment_number = ra.readInt();
        JBIG2Segment s = new JBIG2Segment(segment_number);
        int segment_header_flags = ra.read();
        boolean deferred_non_retain = (segment_header_flags & 0x80) == 128;
        s.deferredNonRetain = deferred_non_retain;
        boolean page_association_size = (segment_header_flags & 0x40) == 64;
        int segment_type = segment_header_flags & 0x3f;
        s.type = segment_type;
        int referred_to_byte0 = ra.read();
        int count_of_referred_to_segments = (referred_to_byte0 & 0xe0) >> 5;
        int referred_to_segment_numbers[] = null;
        boolean segment_retention_flags[] = null;
        if(count_of_referred_to_segments == 7)
        {
            ra.seek(ra.getFilePointer() - 1L);
            count_of_referred_to_segments = ra.readInt() & 0x1fffffff;
            segment_retention_flags = new boolean[count_of_referred_to_segments + 1];
            int i = 0;
            int referred_to_current_byte = 0;
            do
            {
                int j = i % 8;
                if(j == 0)
                    referred_to_current_byte = ra.read();
                segment_retention_flags[i] = (1 << j & referred_to_current_byte) >> j == 1;
            } while(++i <= count_of_referred_to_segments);
        } else
        if(count_of_referred_to_segments <= 4)
        {
            segment_retention_flags = new boolean[count_of_referred_to_segments + 1];
            referred_to_byte0 &= 0x1f;
            for(int i = 0; i <= count_of_referred_to_segments; i++)
                segment_retention_flags[i] = (1 << i & referred_to_byte0) >> i == 1;

        } else
        if(count_of_referred_to_segments == 5 || count_of_referred_to_segments == 6)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("count.of.referred.to.segments.had.bad.value.in.header.for.segment.1.starting.at.2", new Object[] {
                String.valueOf(segment_number), String.valueOf(ptr)
            }));
        s.segmentRetentionFlags = segment_retention_flags;
        s.countOfReferredToSegments = count_of_referred_to_segments;
        referred_to_segment_numbers = new int[count_of_referred_to_segments + 1];
        for(int i = 1; i <= count_of_referred_to_segments; i++)
        {
            if(segment_number <= 256)
            {
                referred_to_segment_numbers[i] = ra.read();
                continue;
            }
            if(segment_number <= 0x10000)
                referred_to_segment_numbers[i] = ra.readUnsignedShort();
            else
                referred_to_segment_numbers[i] = (int)ra.readUnsignedInt();
        }

        s.referredToSegmentNumbers = referred_to_segment_numbers;
        int page_association_offset = (int)ra.getFilePointer() - ptr;
        int segment_page_association;
        if(page_association_size)
            segment_page_association = ra.readInt();
        else
            segment_page_association = ra.read();
        if(segment_page_association < 0)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("page.1.invalid.for.segment.2.starting.at.3", new Object[] {
                String.valueOf(segment_page_association), String.valueOf(segment_number), String.valueOf(ptr)
            }));
        s.page = segment_page_association;
        s.page_association_size = page_association_size;
        s.page_association_offset = page_association_offset;
        if(segment_page_association > 0 && !pages.containsKey(Integer.valueOf(segment_page_association)))
            pages.put(Integer.valueOf(segment_page_association), new JBIG2Page(segment_page_association, this));
        if(segment_page_association > 0)
            ((JBIG2Page)pages.get(Integer.valueOf(segment_page_association))).addSegment(s);
        else
            globals.add(s);
        long segment_data_length = ra.readUnsignedInt();
        s.dataLength = segment_data_length;
        int end_ptr = (int)ra.getFilePointer();
        ra.seek(ptr);
        byte header_data[] = new byte[end_ptr - ptr];
        ra.read(header_data);
        s.headerData = header_data;
        return s;
    }

    void readFileHeader()
        throws IOException
    {
        ra.seek(0L);
        byte idstring[] = new byte[8];
        ra.read(idstring);
        byte refidstring[] = {
            -105, 74, 66, 50, 13, 10, 26, 10
        };
        for(int i = 0; i < idstring.length; i++)
            if(idstring[i] != refidstring[i])
                throw new IllegalStateException(MessageLocalization.getComposedMessage("file.header.idstring.not.good.at.byte.1", i));

        int fileheaderflags = ra.read();
        sequential = (fileheaderflags & 1) == 1;
        number_of_pages_known = (fileheaderflags & 2) == 0;
        if((fileheaderflags & 0xfc) != 0)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("file.header.flags.bits.2.7.not.0", new Object[0]));
        if(number_of_pages_known)
            number_of_pages = ra.readInt();
    }

    public int numberOfPages()
    {
        return pages.size();
    }

    public int getPageHeight(int i)
    {
        return ((JBIG2Page)pages.get(Integer.valueOf(i))).pageBitmapHeight;
    }

    public int getPageWidth(int i)
    {
        return ((JBIG2Page)pages.get(Integer.valueOf(i))).pageBitmapWidth;
    }

    public JBIG2Page getPage(int page)
    {
        return (JBIG2Page)pages.get(Integer.valueOf(page));
    }

    public byte[] getGlobal(boolean for_embedding)
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try
        {
            Iterator i$ = globals.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Object element = i$.next();
                JBIG2Segment s = (JBIG2Segment)element;
                if(!for_embedding || s.type != 51 && s.type != 49)
                {
                    os.write(s.headerData);
                    os.write(s.data);
                }
            } while(true);
            os.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        if(os.size() <= 0)
            return null;
        else
            return os.toByteArray();
    }

    public String toString()
    {
        if(read)
            return (new StringBuilder()).append("Jbig2SegmentReader: number of pages: ").append(numberOfPages()).toString();
        else
            return "Jbig2SegmentReader in indeterminate state.";
    }

    public static final int SYMBOL_DICTIONARY = 0;
    public static final int INTERMEDIATE_TEXT_REGION = 4;
    public static final int IMMEDIATE_TEXT_REGION = 6;
    public static final int IMMEDIATE_LOSSLESS_TEXT_REGION = 7;
    public static final int PATTERN_DICTIONARY = 16;
    public static final int INTERMEDIATE_HALFTONE_REGION = 20;
    public static final int IMMEDIATE_HALFTONE_REGION = 22;
    public static final int IMMEDIATE_LOSSLESS_HALFTONE_REGION = 23;
    public static final int INTERMEDIATE_GENERIC_REGION = 36;
    public static final int IMMEDIATE_GENERIC_REGION = 38;
    public static final int IMMEDIATE_LOSSLESS_GENERIC_REGION = 39;
    public static final int INTERMEDIATE_GENERIC_REFINEMENT_REGION = 40;
    public static final int IMMEDIATE_GENERIC_REFINEMENT_REGION = 42;
    public static final int IMMEDIATE_LOSSLESS_GENERIC_REFINEMENT_REGION = 43;
    public static final int PAGE_INFORMATION = 48;
    public static final int END_OF_PAGE = 49;
    public static final int END_OF_STRIPE = 50;
    public static final int END_OF_FILE = 51;
    public static final int PROFILES = 52;
    public static final int TABLES = 53;
    public static final int EXTENSION = 62;
    private final SortedMap segments = new TreeMap();
    private final SortedMap pages = new TreeMap();
    private final SortedSet globals = new TreeSet();
    private RandomAccessFileOrArray ra;
    private boolean sequential;
    private boolean number_of_pages_known;
    private int number_of_pages;
    private boolean read;
}
