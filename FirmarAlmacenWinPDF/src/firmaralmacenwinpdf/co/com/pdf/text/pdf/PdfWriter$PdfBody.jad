// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfWriter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            ByteBuffer, PdfStream, PdfNumber, PdfIndirectReference, 
//            PdfIndirectObject, PdfArray, PdfObject, PdfWriter, 
//            OutputStreamCounter, PdfName

public static class PdfWriter$PdfBody
{
    public static class PdfCrossReference
        implements Comparable
    {

        public int getRefnum()
        {
            return refnum;
        }

        public void toPdf(OutputStream os)
            throws IOException
        {
            StringBuffer off = (new StringBuffer("0000000000")).append(offset);
            off.delete(0, off.length() - 10);
            StringBuffer gen = (new StringBuffer("00000")).append(generation);
            gen.delete(0, gen.length() - 5);
            off.append(' ').append(gen).append(generation != 65535 ? " n \n" : " f \n");
            os.write(DocWriter.getISOBytes(off.toString()));
        }

        public void toPdf(int midSize, OutputStream os)
            throws IOException
        {
            os.write((byte)type);
            while(--midSize >= 0) 
                os.write((byte)(int)(offset >>> 8 * midSize & 255L));
            os.write((byte)(generation >>> 8 & 0xff));
            os.write((byte)(generation & 0xff));
        }

        public int compareTo(PdfCrossReference other)
        {
            return refnum >= other.refnum ? ((int) (refnum != other.refnum ? 1 : 0)) : -1;
        }

        public boolean equals(Object obj)
        {
            if(obj instanceof PdfCrossReference)
            {
                PdfCrossReference other = (PdfCrossReference)obj;
                return refnum == other.refnum;
            } else
            {
                return false;
            }
        }

        public int hashCode()
        {
            return refnum;
        }

        public volatile int compareTo(Object x0)
        {
            return compareTo((PdfCrossReference)x0);
        }

        private final int type;
        private final long offset;
        private final int refnum;
        private final int generation;

        public PdfCrossReference(int refnum, long offset, int generation)
        {
            type = 0;
            this.offset = offset;
            this.refnum = refnum;
            this.generation = generation;
        }

        public PdfCrossReference(int refnum, long offset)
        {
            type = 1;
            this.offset = offset;
            this.refnum = refnum;
            generation = 0;
        }

        public PdfCrossReference(int type, int refnum, long offset, int generation)
        {
            this.type = type;
            this.offset = offset;
            this.refnum = refnum;
            this.generation = generation;
        }
    }


    void setRefnum(int refnum)
    {
        this.refnum = refnum;
    }

    protected PdfCrossReference addToObjStm(PdfObject obj, int nObj)
        throws IOException
    {
        if(numObj >= 200)
            flushObjStm();
        if(index == null)
        {
            index = new ByteBuffer();
            streamObjects = new ByteBuffer();
            currentObjNum = getIndirectReferenceNumber();
            numObj = 0;
        }
        int p = streamObjects.size();
        int idx = numObj++;
        PdfEncryption enc = writer.crypto;
        writer.crypto = null;
        obj.toPdf(writer, streamObjects);
        writer.crypto = enc;
        streamObjects.append(' ');
        index.append(nObj).append(' ').append(p).append(' ');
        return new PdfCrossReference(2, nObj, currentObjNum, idx);
    }

    public void flushObjStm()
        throws IOException
    {
        if(numObj == 0)
        {
            return;
        } else
        {
            int first = index.size();
            index.append(streamObjects);
            PdfStream stream = new PdfStream(index.toByteArray());
            stream.flateCompress(writer.getCompressionLevel());
            stream.put(PdfName.TYPE, PdfName.OBJSTM);
            stream.put(PdfName.N, new PdfNumber(numObj));
            stream.put(PdfName.FIRST, new PdfNumber(first));
            add(stream, currentObjNum);
            index = null;
            streamObjects = null;
            numObj = 0;
            return;
        }
    }

    PdfIndirectObject add(PdfObject object)
        throws IOException
    {
        return add(object, getIndirectReferenceNumber());
    }

    PdfIndirectObject add(PdfObject object, boolean inObjStm)
        throws IOException
    {
        return add(object, getIndirectReferenceNumber(), 0, inObjStm);
    }

    public PdfIndirectReference getPdfIndirectReference()
    {
        return new PdfIndirectReference(0, getIndirectReferenceNumber());
    }

    protected int getIndirectReferenceNumber()
    {
        int n = refnum++;
        xrefs.add(new PdfCrossReference(n, 0L, 65535));
        return n;
    }

    PdfIndirectObject add(PdfObject object, PdfIndirectReference ref)
        throws IOException
    {
        return add(object, ref, true);
    }

    PdfIndirectObject add(PdfObject object, PdfIndirectReference ref, boolean inObjStm)
        throws IOException
    {
        return add(object, ref.getNumber(), ref.getGeneration(), inObjStm);
    }

    PdfIndirectObject add(PdfObject object, int refNumber)
        throws IOException
    {
        return add(object, refNumber, 0, true);
    }

    protected PdfIndirectObject add(PdfObject object, int refNumber, int generation, boolean inObjStm)
        throws IOException
    {
        if(inObjStm && object.canBeInObjStm() && writer.isFullCompression())
        {
            PdfCrossReference pxref = addToObjStm(object, refNumber);
            PdfIndirectObject indirect = new PdfIndirectObject(refNumber, object, writer);
            if(!xrefs.add(pxref))
            {
                xrefs.remove(pxref);
                xrefs.add(pxref);
            }
            return indirect;
        }
        PdfIndirectObject indirect;
        if(writer.isFullCompression())
        {
            indirect = new PdfIndirectObject(refNumber, object, writer);
            write(indirect, refNumber);
        } else
        {
            indirect = new PdfIndirectObject(refNumber, generation, object, writer);
            write(indirect, refNumber, generation);
        }
        return indirect;
    }

    protected void write(PdfIndirectObject indirect, int refNumber)
        throws IOException
    {
        PdfCrossReference pxref = new PdfCrossReference(refNumber, position);
        if(!xrefs.add(pxref))
        {
            xrefs.remove(pxref);
            xrefs.add(pxref);
        }
        indirect.writeTo(writer.getOs());
        position = writer.getOs().getCounter();
    }

    protected void write(PdfIndirectObject indirect, int refNumber, int generation)
        throws IOException
    {
        PdfCrossReference pxref = new PdfCrossReference(refNumber, position, generation);
        if(!xrefs.add(pxref))
        {
            xrefs.remove(pxref);
            xrefs.add(pxref);
        }
        indirect.writeTo(writer.getOs());
        position = writer.getOs().getCounter();
    }

    public long offset()
    {
        return position;
    }

    public int size()
    {
        return Math.max(((PdfCrossReference)xrefs.last()).getRefnum() + 1, refnum);
    }

    public void writeCrossReferenceTable(OutputStream os, PdfIndirectReference root, PdfIndirectReference info, PdfIndirectReference encryption, PdfObject fileID, long prevxref)
        throws IOException
    {
        int refNumber = 0;
        if(writer.isFullCompression())
        {
            flushObjStm();
            refNumber = getIndirectReferenceNumber();
            xrefs.add(new PdfCrossReference(refNumber, position));
        }
        PdfCrossReference entry = (PdfCrossReference)xrefs.first();
        int first = entry.getRefnum();
        int len = 0;
        ArrayList sections = new ArrayList();
        for(Iterator i$ = xrefs.iterator(); i$.hasNext();)
        {
            PdfCrossReference pdfCrossReference = (PdfCrossReference)i$.next();
            entry = pdfCrossReference;
            if(first + len == entry.getRefnum())
            {
                len++;
            } else
            {
                sections.add(Integer.valueOf(first));
                sections.add(Integer.valueOf(len));
                first = entry.getRefnum();
                len = 1;
            }
        }

        sections.add(Integer.valueOf(first));
        sections.add(Integer.valueOf(len));
        if(writer.isFullCompression())
        {
            int mid = 5;
            for(long mask = 0xff00000000L; mid > 1 && (mask & position) == 0L; mid--)
                mask >>>= 8;

            ByteBuffer buf = new ByteBuffer();
            for(Iterator i$ = xrefs.iterator(); i$.hasNext(); entry.toPdf(mid, buf))
            {
                Object element = i$.next();
                entry = (PdfCrossReference)element;
            }

            PdfStream xr = new PdfStream(buf.toByteArray());
            buf = null;
            xr.flateCompress(writer.getCompressionLevel());
            xr.put(PdfName.SIZE, new PdfNumber(size()));
            xr.put(PdfName.ROOT, root);
            if(info != null)
                xr.put(PdfName.INFO, info);
            if(encryption != null)
                xr.put(PdfName.ENCRYPT, encryption);
            if(fileID != null)
                xr.put(PdfName.ID, fileID);
            xr.put(PdfName.W, new PdfArray(new int[] {
                1, mid, 2
            }));
            xr.put(PdfName.TYPE, PdfName.XREF);
            PdfArray idx = new PdfArray();
            for(int k = 0; k < sections.size(); k++)
                idx.add(new PdfNumber(((Integer)sections.get(k)).intValue()));

            xr.put(PdfName.INDEX, idx);
            if(prevxref > 0L)
                xr.put(PdfName.PREV, new PdfNumber(prevxref));
            PdfEncryption enc = writer.crypto;
            writer.crypto = null;
            PdfIndirectObject indirect = new PdfIndirectObject(refNumber, xr, writer);
            indirect.writeTo(writer.getOs());
            writer.crypto = enc;
        } else
        {
            os.write(DocWriter.getISOBytes("xref\n"));
            Iterator i = xrefs.iterator();
            for(int k = 0; k < sections.size(); k += 2)
            {
                first = ((Integer)sections.get(k)).intValue();
                len = ((Integer)sections.get(k + 1)).intValue();
                os.write(DocWriter.getISOBytes(String.valueOf(first)));
                os.write(DocWriter.getISOBytes(" "));
                os.write(DocWriter.getISOBytes(String.valueOf(len)));
                os.write(10);
                while(len-- > 0) 
                {
                    entry = (PdfCrossReference)i.next();
                    entry.toPdf(os);
                }
            }

        }
    }

    private static final int OBJSINSTREAM = 200;
    protected final TreeSet xrefs = new TreeSet();
    protected int refnum;
    protected long position;
    protected final PdfWriter writer;
    protected ByteBuffer index;
    protected ByteBuffer streamObjects;
    protected int currentObjNum;
    protected int numObj;

    protected PdfWriter$PdfBody(PdfWriter writer)
    {
        numObj = 0;
        xrefs.add(new PdfCrossReference(0, 0L, 65535));
        position = writer.getOs().getCounter();
        refnum = 1;
        this.writer = writer;
    }
}
