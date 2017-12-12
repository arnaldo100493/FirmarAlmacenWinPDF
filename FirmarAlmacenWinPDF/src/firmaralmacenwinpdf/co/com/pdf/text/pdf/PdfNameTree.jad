// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfNameTree.java

package co.com.pdf.text.pdf;

import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfArray, PdfString, PdfObject, 
//            PdfIndirectReference, PdfWriter, PdfName, PdfIndirectObject, 
//            PdfReader, PdfEncodings

public class PdfNameTree
{

    public PdfNameTree()
    {
    }

    public static PdfDictionary writeTree(HashMap items, PdfWriter writer)
        throws IOException
    {
        if(items.isEmpty())
            return null;
        String names[] = new String[items.size()];
        names = (String[])items.keySet().toArray(names);
        Arrays.sort(names);
        if(names.length <= 64)
        {
            PdfDictionary dic = new PdfDictionary();
            PdfArray ar = new PdfArray();
            for(int k = 0; k < names.length; k++)
            {
                ar.add(new PdfString(names[k], null));
                ar.add((PdfObject)items.get(names[k]));
            }

            dic.put(PdfName.NAMES, ar);
            return dic;
        }
        int skip = 64;
        PdfIndirectReference kids[] = new PdfIndirectReference[((names.length + 64) - 1) / 64];
        for(int k = 0; k < kids.length; k++)
        {
            int offset = k * 64;
            int end = Math.min(offset + 64, names.length);
            PdfDictionary dic = new PdfDictionary();
            PdfArray arr = new PdfArray();
            arr.add(new PdfString(names[offset], null));
            arr.add(new PdfString(names[end - 1], null));
            dic.put(PdfName.LIMITS, arr);
            arr = new PdfArray();
            for(; offset < end; offset++)
            {
                arr.add(new PdfString(names[offset], null));
                arr.add((PdfObject)items.get(names[offset]));
            }

            dic.put(PdfName.NAMES, arr);
            kids[k] = writer.addToBody(dic).getIndirectReference();
        }

        int top = kids.length;
        do
        {
            if(top <= 64)
            {
                PdfArray arr = new PdfArray();
                for(int k = 0; k < top; k++)
                    arr.add(kids[k]);

                PdfDictionary dic = new PdfDictionary();
                dic.put(PdfName.KIDS, arr);
                return dic;
            }
            skip *= 64;
            int tt = ((names.length + skip) - 1) / skip;
            for(int k = 0; k < tt; k++)
            {
                int offset = k * 64;
                int end = Math.min(offset + 64, top);
                PdfDictionary dic = new PdfDictionary();
                PdfArray arr = new PdfArray();
                arr.add(new PdfString(names[k * skip], null));
                arr.add(new PdfString(names[Math.min((k + 1) * skip, names.length) - 1], null));
                dic.put(PdfName.LIMITS, arr);
                arr = new PdfArray();
                for(; offset < end; offset++)
                    arr.add(kids[offset]);

                dic.put(PdfName.KIDS, arr);
                kids[k] = writer.addToBody(dic).getIndirectReference();
            }

            top = tt;
        } while(true);
    }

    private static PdfString iterateItems(PdfDictionary dic, HashMap items, PdfString leftOverString)
    {
        PdfArray nn = (PdfArray)PdfReader.getPdfObjectRelease(dic.get(PdfName.NAMES));
        if(nn != null)
        {
            for(int k = 0; k < nn.size(); k++)
            {
                PdfString s;
                if(leftOverString == null)
                {
                    s = (PdfString)PdfReader.getPdfObjectRelease(nn.getPdfObject(k++));
                } else
                {
                    s = leftOverString;
                    leftOverString = null;
                }
                if(k < nn.size())
                    items.put(PdfEncodings.convertToString(s.getBytes(), null), nn.getPdfObject(k));
                else
                    return s;
            }

        } else
        if((nn = (PdfArray)PdfReader.getPdfObjectRelease(dic.get(PdfName.KIDS))) != null)
        {
            for(int k = 0; k < nn.size(); k++)
            {
                PdfDictionary kid = (PdfDictionary)PdfReader.getPdfObjectRelease(nn.getPdfObject(k));
                leftOverString = iterateItems(kid, items, leftOverString);
            }

        }
        return null;
    }

    public static HashMap readTree(PdfDictionary dic)
    {
        HashMap items = new HashMap();
        if(dic != null)
            iterateItems(dic, items, null);
        return items;
    }

    private static final int leafSize = 64;
}
