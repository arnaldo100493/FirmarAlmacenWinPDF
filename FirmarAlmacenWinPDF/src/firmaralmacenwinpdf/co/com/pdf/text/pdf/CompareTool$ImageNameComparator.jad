// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompareTool.java

package co.com.pdf.text.pdf;

import java.io.File;
import java.util.Comparator;

// Referenced classes of package co.com.pdf.text.pdf:
//            CompareTool

class CompareTool$ImageNameComparator
    implements Comparator
{

    public int compare(File f1, File f2)
    {
        String f1Name = f1.getAbsolutePath();
        String f2Name = f2.getAbsolutePath();
        return f1Name.compareTo(f2Name);
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((File)x0, (File)x1);
    }

    final CompareTool this$0;

    CompareTool$ImageNameComparator()
    {
        this$0 = CompareTool.this;
        super();
    }
}
