// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompareTool.java

package co.com.pdf.text.pdf;

import java.io.File;
import java.io.FileFilter;

// Referenced classes of package co.com.pdf.text.pdf:
//            CompareTool

class CompareTool$PngFileFilter
    implements FileFilter
{

    public boolean accept(File pathname)
    {
        String ap = pathname.getAbsolutePath();
        boolean b1 = ap.endsWith(".png");
        boolean b2 = ap.contains("cmp_");
        return b1 && !b2 && ap.contains(CompareTool.access$000(CompareTool.this));
    }

    final CompareTool this$0;

    CompareTool$PngFileFilter()
    {
        this$0 = CompareTool.this;
        super();
    }
}
