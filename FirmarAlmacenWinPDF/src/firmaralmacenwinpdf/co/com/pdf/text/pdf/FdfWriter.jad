// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FdfWriter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import co.com.pdf.text.log.Counter;
import co.com.pdf.text.log.CounterFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, PdfString, PdfName, PdfDictionary, 
//            FdfReader, AcroFields, PdfReader, PdfAction, 
//            PdfWriter, PdfDocument, PdfArray, OutputStreamCounter, 
//            PdfIndirectObject

public class FdfWriter
{
    static class Wrt extends PdfWriter
    {

        void writeTo()
            throws IOException
        {
            PdfDictionary dic = new PdfDictionary();
            dic.put(PdfName.FIELDS, calculate(fdf.fields));
            if(fdf.file != null)
                dic.put(PdfName.F, new PdfString(fdf.file, "UnicodeBig"));
            PdfDictionary fd = new PdfDictionary();
            fd.put(PdfName.FDF, dic);
            PdfIndirectReference ref = addToBody(fd).getIndirectReference();
            os.write(getISOBytes("trailer\n"));
            PdfDictionary trailer = new PdfDictionary();
            trailer.put(PdfName.ROOT, ref);
            trailer.toPdf(null, os);
            os.write(getISOBytes("\n%%EOF\n"));
            os.close();
        }

        PdfArray calculate(HashMap map)
            throws IOException
        {
            PdfArray ar = new PdfArray();
            PdfDictionary dic;
            for(Iterator i$ = map.entrySet().iterator(); i$.hasNext(); ar.add(dic))
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                String key = (String)entry.getKey();
                Object v = entry.getValue();
                dic = new PdfDictionary();
                dic.put(PdfName.T, new PdfString(key, "UnicodeBig"));
                if(v instanceof HashMap)
                {
                    dic.put(PdfName.KIDS, calculate((HashMap)v));
                    continue;
                }
                if(v instanceof PdfAction)
                    dic.put(PdfName.A, (PdfAction)v);
                else
                    dic.put(PdfName.V, (PdfObject)v);
            }

            return ar;
        }

        private FdfWriter fdf;

        Wrt(OutputStream os, FdfWriter fdf)
            throws IOException
        {
            super(new PdfDocument(), os);
            this.fdf = fdf;
            this.os.write(FdfWriter.HEADER_FDF);
            body = new PdfWriter.PdfBody(this);
        }
    }


    public FdfWriter()
    {
        fields = new HashMap();
        COUNTER = CounterFactory.getCounter(co/com/pdf/text/pdf/FdfWriter);
    }

    public void writeTo(OutputStream os)
        throws IOException
    {
        Wrt wrt = new Wrt(os, this);
        wrt.writeTo();
    }

    boolean setField(String field, PdfObject value)
    {
        HashMap map = fields;
        StringTokenizer tk = new StringTokenizer(field, ".");
        if(!tk.hasMoreTokens())
            return false;
        String s;
        Object obj;
        do
        {
            s = tk.nextToken();
            obj = map.get(s);
            if(!tk.hasMoreTokens())
                break;
            if(obj == null)
            {
                obj = new HashMap();
                map.put(s, obj);
                map = (HashMap)obj;
            } else
            if(obj instanceof HashMap)
                map = (HashMap)obj;
            else
                return false;
        } while(true);
        if(!(obj instanceof HashMap))
        {
            map.put(s, value);
            return true;
        } else
        {
            return false;
        }
    }

    void iterateFields(HashMap values, HashMap map, String name)
    {
        for(Iterator i$ = map.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String s = (String)entry.getKey();
            Object obj = entry.getValue();
            if(obj instanceof HashMap)
                iterateFields(values, (HashMap)obj, (new StringBuilder()).append(name).append(".").append(s).toString());
            else
                values.put((new StringBuilder()).append(name).append(".").append(s).toString().substring(1), obj);
        }

    }

    public boolean removeField(String field)
    {
        HashMap map = fields;
        StringTokenizer tk = new StringTokenizer(field, ".");
        if(!tk.hasMoreTokens())
            return false;
        ArrayList hist = new ArrayList();
        Object obj;
        do
        {
            String s = tk.nextToken();
            obj = map.get(s);
            if(obj == null)
                return false;
            hist.add(map);
            hist.add(s);
            if(!tk.hasMoreTokens())
                break;
            if(obj instanceof HashMap)
                map = (HashMap)obj;
            else
                return false;
        } while(true);
        if(obj instanceof HashMap)
            return false;
        int k = hist.size() - 2;
        do
        {
            if(k < 0)
                break;
            map = (HashMap)hist.get(k);
            String s = (String)hist.get(k + 1);
            map.remove(s);
            if(!map.isEmpty())
                break;
            k -= 2;
        } while(true);
        return true;
    }

    public HashMap getFields()
    {
        HashMap values = new HashMap();
        iterateFields(values, fields, "");
        return values;
    }

    public String getField(String field)
    {
        HashMap map = fields;
        StringTokenizer tk = new StringTokenizer(field, ".");
        if(!tk.hasMoreTokens())
            return null;
        Object obj;
        do
        {
            String s = tk.nextToken();
            obj = map.get(s);
            if(obj == null)
                return null;
            if(!tk.hasMoreTokens())
                break;
            if(obj instanceof HashMap)
                map = (HashMap)obj;
            else
                return null;
        } while(true);
        if(obj instanceof HashMap)
            return null;
        if(((PdfObject)obj).isString())
            return ((PdfString)obj).toUnicodeString();
        else
            return PdfName.decodeName(obj.toString());
    }

    public boolean setFieldAsName(String field, String value)
    {
        return setField(field, new PdfName(value));
    }

    public boolean setFieldAsString(String field, String value)
    {
        return setField(field, new PdfString(value, "UnicodeBig"));
    }

    public boolean setFieldAsAction(String field, PdfAction action)
    {
        return setField(field, action);
    }

    public void setFields(FdfReader fdf)
    {
        HashMap map = fdf.getFields();
        Iterator i$ = map.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String key = (String)entry.getKey();
            PdfDictionary dic = (PdfDictionary)entry.getValue();
            PdfObject v = dic.get(PdfName.V);
            if(v != null)
                setField(key, v);
            v = dic.get(PdfName.A);
            if(v != null)
                setField(key, v);
        } while(true);
    }

    public void setFields(PdfReader pdf)
    {
        setFields(pdf.getAcroFields());
    }

    public void setFields(AcroFields af)
    {
        Iterator i$ = af.getFields().entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            String fn = (String)entry.getKey();
            AcroFields.Item item = (AcroFields.Item)entry.getValue();
            PdfDictionary dic = item.getMerged(0);
            PdfObject v = PdfReader.getPdfObjectRelease(dic.get(PdfName.V));
            if(v != null)
            {
                PdfObject ft = PdfReader.getPdfObjectRelease(dic.get(PdfName.FT));
                if(ft != null && !PdfName.SIG.equals(ft))
                    setField(fn, v);
            }
        } while(true);
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    protected Counter getCounter()
    {
        return COUNTER;
    }

    private static final byte HEADER_FDF[] = DocWriter.getISOBytes("%FDF-1.4\n%\342\343\317\323\n");
    HashMap fields;
    private String file;
    protected Counter COUNTER;



}
