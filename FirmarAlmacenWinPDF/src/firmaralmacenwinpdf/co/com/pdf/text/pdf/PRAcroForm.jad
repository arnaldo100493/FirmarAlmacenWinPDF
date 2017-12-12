// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PRAcroForm.java

package co.com.pdf.text.pdf;

import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PRIndirectReference, PdfString, 
//            PdfName, PdfReader, PdfObject

public class PRAcroForm extends PdfDictionary
{
    public static class FieldInformation
    {

        public String getWidgetName()
        {
            PdfObject name = info.get(PdfName.NM);
            if(name != null)
                return name.toString();
            else
                return null;
        }

        public String getName()
        {
            return fieldName;
        }

        public PdfDictionary getInfo()
        {
            return info;
        }

        public PRIndirectReference getRef()
        {
            return ref;
        }

        String fieldName;
        PdfDictionary info;
        PRIndirectReference ref;

        FieldInformation(String fieldName, PdfDictionary info, PRIndirectReference ref)
        {
            this.fieldName = fieldName;
            this.info = info;
            this.ref = ref;
        }
    }


    public PRAcroForm(PdfReader reader)
    {
        this.reader = reader;
        fields = new ArrayList();
        fieldByName = new HashMap();
        stack = new ArrayList();
    }

    public int size()
    {
        return fields.size();
    }

    public ArrayList getFields()
    {
        return fields;
    }

    public FieldInformation getField(String name)
    {
        return (FieldInformation)fieldByName.get(name);
    }

    public PRIndirectReference getRefByName(String name)
    {
        FieldInformation fi = (FieldInformation)fieldByName.get(name);
        if(fi == null)
            return null;
        else
            return fi.getRef();
    }

    public void readAcroForm(PdfDictionary root)
    {
        if(root == null)
            return;
        hashMap = root.hashMap;
        pushAttrib(root);
        PdfArray fieldlist = (PdfArray)PdfReader.getPdfObjectRelease(root.get(PdfName.FIELDS));
        if(fieldlist != null)
            iterateFields(fieldlist, null, null);
    }

    protected void iterateFields(PdfArray fieldlist, PRIndirectReference fieldDict, String parentPath)
    {
        Iterator it = fieldlist.listIterator();
        do
        {
            if(!it.hasNext())
                break;
            PRIndirectReference ref = (PRIndirectReference)it.next();
            PdfDictionary dict = (PdfDictionary)PdfReader.getPdfObjectRelease(ref);
            PRIndirectReference myFieldDict = fieldDict;
            String fullPath = parentPath;
            PdfString tField = (PdfString)dict.get(PdfName.T);
            boolean isFieldDict = tField != null;
            if(isFieldDict)
            {
                myFieldDict = ref;
                if(parentPath == null)
                    fullPath = tField.toString();
                else
                    fullPath = (new StringBuilder()).append(parentPath).append('.').append(tField.toString()).toString();
            }
            PdfArray kids = (PdfArray)dict.get(PdfName.KIDS);
            if(kids != null)
            {
                pushAttrib(dict);
                iterateFields(kids, myFieldDict, fullPath);
                stack.remove(stack.size() - 1);
            } else
            if(myFieldDict != null)
            {
                PdfDictionary mergedDict = (PdfDictionary)stack.get(stack.size() - 1);
                if(isFieldDict)
                    mergedDict = mergeAttrib(mergedDict, dict);
                mergedDict.put(PdfName.T, new PdfString(fullPath));
                FieldInformation fi = new FieldInformation(fullPath, mergedDict, myFieldDict);
                fields.add(fi);
                fieldByName.put(fullPath, fi);
            }
        } while(true);
    }

    protected PdfDictionary mergeAttrib(PdfDictionary parent, PdfDictionary child)
    {
        PdfDictionary targ = new PdfDictionary();
        if(parent != null)
            targ.putAll(parent);
        Iterator i$ = child.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfName key = (PdfName)element;
            if(key.equals(PdfName.DR) || key.equals(PdfName.DA) || key.equals(PdfName.Q) || key.equals(PdfName.FF) || key.equals(PdfName.DV) || key.equals(PdfName.V) || key.equals(PdfName.FT) || key.equals(PdfName.NM) || key.equals(PdfName.F))
                targ.put(key, child.get(key));
        } while(true);
        return targ;
    }

    protected void pushAttrib(PdfDictionary dict)
    {
        PdfDictionary dic = null;
        if(!stack.isEmpty())
            dic = (PdfDictionary)stack.get(stack.size() - 1);
        dic = mergeAttrib(dic, dict);
        stack.add(dic);
    }

    ArrayList fields;
    ArrayList stack;
    HashMap fieldByName;
    PdfReader reader;
}
