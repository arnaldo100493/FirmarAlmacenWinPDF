// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSmartCopy.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfCopy, PRStream, PdfIndirectReference, PdfDictionary, 
//            PdfObject, PRIndirectReference, BadPdfFormatException, PdfReader, 
//            PdfName, PdfReaderInstance, PdfWriter, PdfImportedPage, 
//            ByteBuffer, PdfArray

public class PdfSmartCopy extends PdfCopy
{
    static class ByteStore
    {

        private void serObject(PdfObject obj, int level, ByteBuffer bb, HashMap serialized)
            throws IOException
        {
            if(level <= 0)
                return;
            if(obj == null)
            {
                bb.append("$Lnull");
                return;
            }
            PdfIndirectReference ref = null;
            ByteBuffer savedBb = null;
            if(obj.isIndirect())
            {
                ref = (PdfIndirectReference)obj;
                PdfCopy.RefKey key = new PdfCopy.RefKey(ref);
                if(serialized.containsKey(key))
                {
                    bb.append(((Integer)serialized.get(key)).intValue());
                    return;
                }
                savedBb = bb;
                bb = new ByteBuffer();
            }
            obj = PdfReader.getPdfObject(obj);
            if(obj.isStream())
            {
                bb.append("$B");
                serDic((PdfDictionary)obj, level - 1, bb, serialized);
                if(level > 0)
                {
                    md5.reset();
                    bb.append(md5.digest(PdfReader.getStreamBytesRaw((PRStream)obj)));
                }
            } else
            if(obj.isDictionary())
                serDic((PdfDictionary)obj, level - 1, bb, serialized);
            else
            if(obj.isArray())
                serArray((PdfArray)obj, level - 1, bb, serialized);
            else
            if(obj.isString())
                bb.append("$S").append(obj.toString());
            else
            if(obj.isName())
                bb.append("$N").append(obj.toString());
            else
                bb.append("$L").append(obj.toString());
            if(savedBb != null)
            {
                PdfCopy.RefKey key = new PdfCopy.RefKey(ref);
                if(!serialized.containsKey(key))
                    serialized.put(key, Integer.valueOf(calculateHash(bb.getBuffer())));
                savedBb.append(bb);
            }
        }

        private void serDic(PdfDictionary dic, int level, ByteBuffer bb, HashMap serialized)
            throws IOException
        {
            bb.append("$D");
            if(level <= 0)
                return;
            Object keys[] = dic.getKeys().toArray();
            Arrays.sort(keys);
            for(int k = 0; k < keys.length; k++)
            {
                serObject((PdfObject)keys[k], level, bb, serialized);
                serObject(dic.get((PdfName)keys[k]), level, bb, serialized);
            }

        }

        private void serArray(PdfArray array, int level, ByteBuffer bb, HashMap serialized)
            throws IOException
        {
            bb.append("$A");
            if(level <= 0)
                return;
            for(int k = 0; k < array.size(); k++)
                serObject(array.getPdfObject(k), level, bb, serialized);

        }

        private static int calculateHash(byte b[])
        {
            int hash = 0;
            int len = b.length;
            for(int k = 0; k < len; k++)
                hash = hash * 31 + (b[k] & 0xff);

            return hash;
        }

        public boolean equals(Object obj)
        {
            if(!(obj instanceof ByteStore))
                return false;
            if(hashCode() != obj.hashCode())
                return false;
            else
                return Arrays.equals(b, ((ByteStore)obj).b);
        }

        public int hashCode()
        {
            return hash;
        }

        private final byte b[];
        private final int hash;
        private MessageDigest md5;

        ByteStore(PRStream str, HashMap serialized)
            throws IOException
        {
            try
            {
                md5 = MessageDigest.getInstance("MD5");
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
            ByteBuffer bb = new ByteBuffer();
            int level = 100;
            serObject(str, level, bb, serialized);
            b = bb.toByteArray();
            hash = calculateHash(b);
            md5 = null;
        }

        ByteStore(PdfDictionary dict, HashMap serialized)
            throws IOException
        {
            try
            {
                md5 = MessageDigest.getInstance("MD5");
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
            ByteBuffer bb = new ByteBuffer();
            int level = 100;
            serObject(dict, level, bb, serialized);
            b = bb.toByteArray();
            hash = calculateHash(b);
            md5 = null;
        }
    }


    protected Counter getCounter()
    {
        return COUNTER;
    }

    public PdfSmartCopy(Document document, OutputStream os)
        throws DocumentException
    {
        super(document, os);
        streamMap = null;
        COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/PdfSmartCopy);
        streamMap = new HashMap();
    }

    protected PdfIndirectReference copyIndirect(PRIndirectReference in)
        throws IOException, BadPdfFormatException
    {
        PdfObject srcObj = PdfReader.getPdfObjectRelease(in);
        ByteStore streamKey = null;
        boolean validStream = false;
        if(srcObj.isStream())
        {
            streamKey = new ByteStore((PRStream)srcObj, serialized);
            validStream = true;
            PdfIndirectReference streamRef = (PdfIndirectReference)streamMap.get(streamKey);
            if(streamRef != null)
                return streamRef;
        } else
        if(srcObj.isDictionary())
        {
            streamKey = new ByteStore((PdfDictionary)srcObj, serialized);
            validStream = true;
            PdfIndirectReference streamRef = (PdfIndirectReference)streamMap.get(streamKey);
            if(streamRef != null)
                return streamRef;
        }
        PdfCopy.RefKey key = new PdfCopy.RefKey(in);
        PdfCopy.IndirectReferences iRef = (PdfCopy.IndirectReferences)indirects.get(key);
        PdfIndirectReference theRef;
        if(iRef != null)
        {
            theRef = iRef.getRef();
            if(iRef.getCopied())
                return theRef;
        } else
        {
            theRef = body.getPdfIndirectReference();
            iRef = new PdfCopy.IndirectReferences(theRef);
            indirects.put(key, iRef);
        }
        if(srcObj.isDictionary())
        {
            PdfObject type = PdfReader.getPdfObjectRelease(((PdfDictionary)srcObj).get(PdfName.TYPE));
            if(type != null && PdfName.PAGE.equals(type))
                return theRef;
        }
        iRef.setCopied();
        if(validStream)
            streamMap.put(streamKey, theRef);
        PdfObject obj = copyObject(srcObj);
        addToBody(obj, theRef);
        return theRef;
    }

    public void freeReader(PdfReader reader)
        throws IOException
    {
        serialized.clear();
        super.freeReader(reader);
    }

    public void addPage(PdfImportedPage iPage)
        throws IOException, BadPdfFormatException
    {
        if(currentPdfReaderInstance.getReader() != reader)
            serialized.clear();
        super.addPage(iPage);
    }

    private HashMap streamMap;
    private final HashMap serialized = new HashMap();
    protected Counter COUNTER;
}
