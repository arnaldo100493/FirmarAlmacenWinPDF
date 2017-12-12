// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PageResources.java

package co.com.pdf.text.pdf;

import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfName, PdfResources, PdfObject, 
//            PdfReader, PdfIndirectReference

class PageResources
{

    PageResources()
    {
        fontDictionary = new PdfDictionary();
        xObjectDictionary = new PdfDictionary();
        colorDictionary = new PdfDictionary();
        patternDictionary = new PdfDictionary();
        shadingDictionary = new PdfDictionary();
        extGStateDictionary = new PdfDictionary();
        propertyDictionary = new PdfDictionary();
    }

    void setOriginalResources(PdfDictionary resources, int newNamePtr[])
    {
        if(newNamePtr != null)
            namePtr = newNamePtr;
        forbiddenNames = new HashSet();
        usedNames = new HashMap();
        if(resources == null)
            return;
        originalResources = new PdfDictionary();
        originalResources.merge(resources);
        Iterator i$ = resources.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfName key = (PdfName)element;
            PdfObject sub = PdfReader.getPdfObject(resources.get(key));
            if(sub != null && sub.isDictionary())
            {
                PdfDictionary dic = (PdfDictionary)sub;
                PdfName element2;
                for(Iterator i$ = dic.getKeys().iterator(); i$.hasNext(); forbiddenNames.add(element2))
                    element2 = (PdfName)i$.next();

                PdfDictionary dic2 = new PdfDictionary();
                dic2.merge(dic);
                originalResources.put(key, dic2);
            }
        } while(true);
    }

    PdfName translateName(PdfName name)
    {
        PdfName translated = name;
        if(forbiddenNames != null)
        {
            translated = (PdfName)usedNames.get(name);
            if(translated == null)
            {
                do
                    translated = new PdfName((new StringBuilder()).append("Xi").append(namePtr[0]++).toString());
                while(forbiddenNames.contains(translated));
                usedNames.put(name, translated);
            }
        }
        return translated;
    }

    PdfName addFont(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        fontDictionary.put(name, reference);
        return name;
    }

    PdfName addXObject(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        xObjectDictionary.put(name, reference);
        return name;
    }

    PdfName addColor(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        colorDictionary.put(name, reference);
        return name;
    }

    void addDefaultColor(PdfName name, PdfObject obj)
    {
        if(obj == null || obj.isNull())
            colorDictionary.remove(name);
        else
            colorDictionary.put(name, obj);
    }

    void addDefaultColor(PdfDictionary dic)
    {
        colorDictionary.merge(dic);
    }

    void addDefaultColorDiff(PdfDictionary dic)
    {
        colorDictionary.mergeDifferent(dic);
    }

    PdfName addShading(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        shadingDictionary.put(name, reference);
        return name;
    }

    PdfName addPattern(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        patternDictionary.put(name, reference);
        return name;
    }

    PdfName addExtGState(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        extGStateDictionary.put(name, reference);
        return name;
    }

    PdfName addProperty(PdfName name, PdfIndirectReference reference)
    {
        name = translateName(name);
        propertyDictionary.put(name, reference);
        return name;
    }

    PdfDictionary getResources()
    {
        PdfResources resources = new PdfResources();
        if(originalResources != null)
            resources.putAll(originalResources);
        resources.add(PdfName.FONT, fontDictionary);
        resources.add(PdfName.XOBJECT, xObjectDictionary);
        resources.add(PdfName.COLORSPACE, colorDictionary);
        resources.add(PdfName.PATTERN, patternDictionary);
        resources.add(PdfName.SHADING, shadingDictionary);
        resources.add(PdfName.EXTGSTATE, extGStateDictionary);
        resources.add(PdfName.PROPERTIES, propertyDictionary);
        return resources;
    }

    boolean hasResources()
    {
        return fontDictionary.size() > 0 || xObjectDictionary.size() > 0 || colorDictionary.size() > 0 || patternDictionary.size() > 0 || shadingDictionary.size() > 0 || extGStateDictionary.size() > 0 || propertyDictionary.size() > 0;
    }

    protected PdfDictionary fontDictionary;
    protected PdfDictionary xObjectDictionary;
    protected PdfDictionary colorDictionary;
    protected PdfDictionary patternDictionary;
    protected PdfDictionary shadingDictionary;
    protected PdfDictionary extGStateDictionary;
    protected PdfDictionary propertyDictionary;
    protected HashSet forbiddenNames;
    protected PdfDictionary originalResources;
    protected int namePtr[] = {
        0
    };
    protected HashMap usedNames;
}
