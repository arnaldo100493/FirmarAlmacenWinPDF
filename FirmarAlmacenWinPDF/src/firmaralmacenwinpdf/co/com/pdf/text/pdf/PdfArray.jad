// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfArray.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, PdfNumber, PdfDictionary, PdfStream, 
//            PdfString, PdfName, PdfBoolean, PdfIndirectReference, 
//            PdfWriter, PdfNull, PdfReader

public class PdfArray extends PdfObject
    implements Iterable
{

    public PdfArray()
    {
        super(5);
        arrayList = new ArrayList();
    }

    public PdfArray(PdfObject object)
    {
        super(5);
        arrayList = new ArrayList();
        arrayList.add(object);
    }

    public PdfArray(float values[])
    {
        super(5);
        arrayList = new ArrayList();
        add(values);
    }

    public PdfArray(int values[])
    {
        super(5);
        arrayList = new ArrayList();
        add(values);
    }

    public PdfArray(List l)
    {
        this();
        PdfObject element;
        for(Iterator i$ = l.iterator(); i$.hasNext(); add(element))
            element = (PdfObject)i$.next();

    }

    public PdfArray(PdfArray array)
    {
        super(5);
        arrayList = new ArrayList(array.arrayList);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 11, this);
        os.write(91);
        Iterator i = arrayList.iterator();
        int type = 0;
        if(i.hasNext())
        {
            PdfObject object = (PdfObject)i.next();
            if(object == null)
                object = PdfNull.PDFNULL;
            object.toPdf(writer, os);
        }
        PdfObject object;
        for(; i.hasNext(); object.toPdf(writer, os))
        {
            object = (PdfObject)i.next();
            if(object == null)
                object = PdfNull.PDFNULL;
            type = object.type();
            if(type != 5 && type != 6 && type != 4 && type != 3)
                os.write(32);
        }

        os.write(93);
    }

    public String toString()
    {
        return arrayList.toString();
    }

    public PdfObject set(int idx, PdfObject obj)
    {
        return (PdfObject)arrayList.set(idx, obj);
    }

    public PdfObject remove(int idx)
    {
        return (PdfObject)arrayList.remove(idx);
    }

    /**
     * @deprecated Method getArrayList is deprecated
     */

    public ArrayList getArrayList()
    {
        return arrayList;
    }

    public int size()
    {
        return arrayList.size();
    }

    public boolean isEmpty()
    {
        return arrayList.isEmpty();
    }

    public boolean add(PdfObject object)
    {
        return arrayList.add(object);
    }

    public boolean add(float values[])
    {
        for(int k = 0; k < values.length; k++)
            arrayList.add(new PdfNumber(values[k]));

        return true;
    }

    public boolean add(int values[])
    {
        for(int k = 0; k < values.length; k++)
            arrayList.add(new PdfNumber(values[k]));

        return true;
    }

    public void add(int index, PdfObject element)
    {
        arrayList.add(index, element);
    }

    public void addFirst(PdfObject object)
    {
        arrayList.add(0, object);
    }

    public boolean contains(PdfObject object)
    {
        return arrayList.contains(object);
    }

    public ListIterator listIterator()
    {
        return arrayList.listIterator();
    }

    public PdfObject getPdfObject(int idx)
    {
        return (PdfObject)arrayList.get(idx);
    }

    public PdfObject getDirectObject(int idx)
    {
        return PdfReader.getPdfObject(getPdfObject(idx));
    }

    public PdfDictionary getAsDict(int idx)
    {
        PdfDictionary dict = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isDictionary())
            dict = (PdfDictionary)orig;
        return dict;
    }

    public PdfArray getAsArray(int idx)
    {
        PdfArray array = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isArray())
            array = (PdfArray)orig;
        return array;
    }

    public PdfStream getAsStream(int idx)
    {
        PdfStream stream = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isStream())
            stream = (PdfStream)orig;
        return stream;
    }

    public PdfString getAsString(int idx)
    {
        PdfString string = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isString())
            string = (PdfString)orig;
        return string;
    }

    public PdfNumber getAsNumber(int idx)
    {
        PdfNumber number = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isNumber())
            number = (PdfNumber)orig;
        return number;
    }

    public PdfName getAsName(int idx)
    {
        PdfName name = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isName())
            name = (PdfName)orig;
        return name;
    }

    public PdfBoolean getAsBoolean(int idx)
    {
        PdfBoolean bool = null;
        PdfObject orig = getDirectObject(idx);
        if(orig != null && orig.isBoolean())
            bool = (PdfBoolean)orig;
        return bool;
    }

    public PdfIndirectReference getAsIndirectObject(int idx)
    {
        PdfIndirectReference ref = null;
        PdfObject orig = getPdfObject(idx);
        if(orig instanceof PdfIndirectReference)
            ref = (PdfIndirectReference)orig;
        return ref;
    }

    public Iterator iterator()
    {
        return arrayList.iterator();
    }

    public long[] asLongArray()
    {
        long rslt[] = new long[size()];
        for(int k = 0; k < rslt.length; k++)
            rslt[k] = getAsNumber(k).longValue();

        return rslt;
    }

    protected ArrayList arrayList;
}
