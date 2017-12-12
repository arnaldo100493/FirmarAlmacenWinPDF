// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfTextArray.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfNumber

public class PdfTextArray
{

    public PdfTextArray(String str)
    {
        arrayList = new ArrayList();
        add(str);
    }

    public PdfTextArray()
    {
        arrayList = new ArrayList();
    }

    public void add(PdfNumber number)
    {
        add((float)number.doubleValue());
    }

    public void add(float number)
    {
        if(number != 0.0F)
        {
            if(lastNum != null)
            {
                lastNum = new Float(number + lastNum.floatValue());
                if(lastNum.floatValue() != 0.0F)
                    replaceLast(lastNum);
                else
                    arrayList.remove(arrayList.size() - 1);
            } else
            {
                lastNum = new Float(number);
                arrayList.add(lastNum);
            }
            lastStr = null;
        }
    }

    public void add(String str)
    {
        if(str.length() > 0)
        {
            if(lastStr != null)
            {
                lastStr = (new StringBuilder()).append(lastStr).append(str).toString();
                replaceLast(lastStr);
            } else
            {
                lastStr = str;
                arrayList.add(lastStr);
            }
            lastNum = null;
        }
    }

    ArrayList getArrayList()
    {
        return arrayList;
    }

    private void replaceLast(Object obj)
    {
        arrayList.set(arrayList.size() - 1, obj);
    }

    ArrayList arrayList;
    private String lastStr;
    private Float lastNum;
}
