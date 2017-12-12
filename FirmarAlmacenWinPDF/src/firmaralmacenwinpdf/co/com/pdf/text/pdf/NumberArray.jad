// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NumberArray.java

package co.com.pdf.text.pdf;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfArray, PdfNumber

public class NumberArray extends PdfArray
{

    public transient NumberArray(float numbers[])
    {
        float arr$[] = numbers;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            float f = arr$[i$];
            add(new PdfNumber(f));
        }

    }

    public NumberArray(List numbers)
    {
        PdfNumber n;
        for(Iterator i$ = numbers.iterator(); i$.hasNext(); add(n))
            n = (PdfNumber)i$.next();

    }
}
