// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDictionary.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfObject, PdfName, PdfArray, PdfStream, 
//            PdfString, PdfNumber, PdfBoolean, PdfIndirectReference, 
//            PdfWriter, PdfReader

public class PdfDictionary extends PdfObject
{

    public PdfDictionary()
    {
        super(6);
        dictionaryType = null;
        hashMap = new HashMap();
    }

    public PdfDictionary(PdfName type)
    {
        this();
        dictionaryType = type;
        put(PdfName.TYPE, dictionaryType);
    }

    public void toPdf(PdfWriter writer, OutputStream os)
        throws IOException
    {
        PdfWriter.checkPdfIsoConformance(writer, 11, this);
        os.write(60);
        os.write(60);
        int type = 0;
        PdfObject value;
        for(Iterator i$ = hashMap.entrySet().iterator(); i$.hasNext(); value.toPdf(writer, os))
        {
            java.util.Map.Entry e = (java.util.Map.Entry)i$.next();
            ((PdfName)e.getKey()).toPdf(writer, os);
            value = (PdfObject)e.getValue();
            type = value.type();
            if(type != 5 && type != 6 && type != 4 && type != 3)
                os.write(32);
        }

        os.write(62);
        os.write(62);
    }

    public String toString()
    {
        if(get(PdfName.TYPE) == null)
            return "Dictionary";
        else
            return (new StringBuilder()).append("Dictionary of type: ").append(get(PdfName.TYPE)).toString();
    }

    public void put(PdfName key, PdfObject object)
    {
        if(object == null || object.isNull())
            hashMap.remove(key);
        else
            hashMap.put(key, object);
    }

    public void putEx(PdfName key, PdfObject value)
    {
        if(value == null)
        {
            return;
        } else
        {
            put(key, value);
            return;
        }
    }

    public void putAll(PdfDictionary dic)
    {
        hashMap.putAll(dic.hashMap);
    }

    public void remove(PdfName key)
    {
        hashMap.remove(key);
    }

    public void clear()
    {
        hashMap.clear();
    }

    public PdfObject get(PdfName key)
    {
        return (PdfObject)hashMap.get(key);
    }

    public PdfObject getDirectObject(PdfName key)
    {
        return PdfReader.getPdfObject(get(key));
    }

    public Set getKeys()
    {
        return hashMap.keySet();
    }

    public int size()
    {
        return hashMap.size();
    }

    public boolean contains(PdfName key)
    {
        return hashMap.containsKey(key);
    }

    public boolean isFont()
    {
        return checkType(FONT);
    }

    public boolean isPage()
    {
        return checkType(PAGE);
    }

    public boolean isPages()
    {
        return checkType(PAGES);
    }

    public boolean isCatalog()
    {
        return checkType(CATALOG);
    }

    public boolean isOutlineTree()
    {
        return checkType(OUTLINES);
    }

    public boolean checkType(PdfName type)
    {
        if(type == null)
            return false;
        if(dictionaryType == null)
            dictionaryType = getAsName(PdfName.TYPE);
        return type.equals(dictionaryType);
    }

    public void merge(PdfDictionary other)
    {
        hashMap.putAll(other.hashMap);
    }

    public void mergeDifferent(PdfDictionary other)
    {
        Iterator i$ = other.hashMap.keySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfName key = (PdfName)i$.next();
            if(!hashMap.containsKey(key))
                hashMap.put(key, other.hashMap.get(key));
        } while(true);
    }

    public PdfDictionary getAsDict(PdfName key)
    {
        PdfDictionary dict = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isDictionary())
            dict = (PdfDictionary)orig;
        return dict;
    }

    public PdfArray getAsArray(PdfName key)
    {
        PdfArray array = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isArray())
            array = (PdfArray)orig;
        return array;
    }

    public PdfStream getAsStream(PdfName key)
    {
        PdfStream stream = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isStream())
            stream = (PdfStream)orig;
        return stream;
    }

    public PdfString getAsString(PdfName key)
    {
        PdfString string = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isString())
            string = (PdfString)orig;
        return string;
    }

    public PdfNumber getAsNumber(PdfName key)
    {
        PdfNumber number = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isNumber())
            number = (PdfNumber)orig;
        return number;
    }

    public PdfName getAsName(PdfName key)
    {
        PdfName name = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isName())
            name = (PdfName)orig;
        return name;
    }

    public PdfBoolean getAsBoolean(PdfName key)
    {
        PdfBoolean bool = null;
        PdfObject orig = getDirectObject(key);
        if(orig != null && orig.isBoolean())
            bool = (PdfBoolean)orig;
        return bool;
    }

    public PdfIndirectReference getAsIndirectObject(PdfName key)
    {
        PdfIndirectReference ref = null;
        PdfObject orig = get(key);
        if(orig != null && orig.isIndirect())
            ref = (PdfIndirectReference)orig;
        return ref;
    }

    public static final PdfName FONT;
    public static final PdfName OUTLINES;
    public static final PdfName PAGE;
    public static final PdfName PAGES;
    public static final PdfName CATALOG;
    private PdfName dictionaryType;
    protected HashMap hashMap;

    static 
    {
        FONT = PdfName.FONT;
        OUTLINES = PdfName.OUTLINES;
        PAGE = PdfName.PAGE;
        PAGES = PdfName.PAGES;
        CATALOG = PdfName.CATALOG;
    }
}
