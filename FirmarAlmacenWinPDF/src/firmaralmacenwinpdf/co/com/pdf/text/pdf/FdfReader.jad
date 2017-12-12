// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FdfReader.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfReader, PdfDictionary, PRIndirectReference, PRStream, 
//            PdfName, PdfString, PdfArray, PdfObject, 
//            PRTokeniser, PdfIndirectReference

public class FdfReader extends PdfReader
{

    public FdfReader(String filename)
        throws IOException
    {
        super(filename);
    }

    public FdfReader(byte pdfIn[])
        throws IOException
    {
        super(pdfIn);
    }

    public FdfReader(URL url)
        throws IOException
    {
        super(url);
    }

    public FdfReader(InputStream is)
        throws IOException
    {
        super(is);
    }

    protected Counter getCounter()
    {
        return COUNTER;
    }

    protected void readPdf()
        throws IOException
    {
        fields = new HashMap();
        tokens.checkFdfHeader();
        rebuildXref();
        readDocObj();
        try
        {
            tokens.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_54;
        Exception exception;
        exception;
        try
        {
            tokens.close();
        }
        catch(Exception e) { }
        throw exception;
        readFields();
        return;
    }

    protected void kidNode(PdfDictionary merged, String name)
    {
        PdfArray kids = merged.getAsArray(PdfName.KIDS);
        if(kids == null || kids.isEmpty())
        {
            if(name.length() > 0)
                name = name.substring(1);
            fields.put(name, merged);
        } else
        {
            merged.remove(PdfName.KIDS);
            for(int k = 0; k < kids.size(); k++)
            {
                PdfDictionary dic = new PdfDictionary();
                dic.merge(merged);
                PdfDictionary newDic = kids.getAsDict(k);
                PdfString t = newDic.getAsString(PdfName.T);
                String newName = name;
                if(t != null)
                    newName = (new StringBuilder()).append(newName).append(".").append(t.toUnicodeString()).toString();
                dic.merge(newDic);
                dic.remove(PdfName.T);
                kidNode(dic, newName);
            }

        }
    }

    protected void readFields()
    {
        catalog = trailer.getAsDict(PdfName.ROOT);
        PdfDictionary fdf = catalog.getAsDict(PdfName.FDF);
        if(fdf == null)
            return;
        PdfString fs = fdf.getAsString(PdfName.F);
        if(fs != null)
            fileSpec = fs.toUnicodeString();
        PdfArray fld = fdf.getAsArray(PdfName.FIELDS);
        if(fld == null)
        {
            return;
        } else
        {
            encoding = fdf.getAsName(PdfName.ENCODING);
            PdfDictionary merged = new PdfDictionary();
            merged.put(PdfName.KIDS, fld);
            kidNode(merged, "");
            return;
        }
    }

    public HashMap getFields()
    {
        return fields;
    }

    public PdfDictionary getField(String name)
    {
        return (PdfDictionary)fields.get(name);
    }

    public byte[] getAttachedFile(String name)
        throws IOException
    {
        PdfDictionary field = (PdfDictionary)fields.get(name);
        if(field != null)
        {
            PdfIndirectReference ir = (PRIndirectReference)field.get(PdfName.V);
            PdfDictionary filespec = (PdfDictionary)getPdfObject(ir.getNumber());
            PdfDictionary ef = filespec.getAsDict(PdfName.EF);
            ir = (PRIndirectReference)ef.get(PdfName.F);
            PRStream stream = (PRStream)getPdfObject(ir.getNumber());
            return getStreamBytes(stream);
        } else
        {
            return new byte[0];
        }
    }

    public String getFieldValue(String name)
    {
        PdfString vs;
        byte b[];
        PdfDictionary field = (PdfDictionary)fields.get(name);
        if(field == null)
            return null;
        PdfObject v = getPdfObject(field.get(PdfName.V));
        if(v == null)
            return null;
        if(v.isName())
            return PdfName.decodeName(((PdfName)v).toString());
        if(!v.isString())
            break MISSING_BLOCK_LABEL_260;
        vs = (PdfString)v;
        if(encoding == null || vs.getEncoding() != null)
            return vs.toUnicodeString();
        b = vs.getBytes();
        if(b.length >= 2 && b[0] == -2 && b[1] == -1)
            return vs.toUnicodeString();
        if(encoding.equals(PdfName.SHIFT_JIS))
            return new String(b, "SJIS");
        if(encoding.equals(PdfName.UHC))
            return new String(b, "MS949");
        if(encoding.equals(PdfName.GBK))
            return new String(b, "GBK");
        if(encoding.equals(PdfName.BIGFIVE))
            return new String(b, "Big5");
        try
        {
            if(encoding.equals(PdfName.UTF_8))
                return new String(b, "UTF8");
        }
        catch(Exception e) { }
        return vs.toUnicodeString();
        return null;
    }

    public String getFileSpec()
    {
        return fileSpec;
    }

    HashMap fields;
    String fileSpec;
    PdfName encoding;
    protected static Counter COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/FdfReader);

}
