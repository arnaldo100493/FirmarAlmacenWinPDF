// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BOMInputStream.java

package org.apache.commons.io.input;

import java.util.Comparator;
import org.apache.commons.io.ByteOrderMark;

// Referenced classes of package org.apache.commons.io.input:
//            BOMInputStream

static class BOMInputStream$1
    implements Comparator
{

    public int compare(ByteOrderMark bom1, ByteOrderMark bom2)
    {
        int len1 = bom1.length();
        int len2 = bom2.length();
        if(len1 > len2)
            return -1;
        return len2 <= len1 ? 0 : 1;
    }

    public volatile int compare(Object x0, Object x1)
    {
        return compare((ByteOrderMark)x0, (ByteOrderMark)x1);
    }

    BOMInputStream$1()
    {
    }
}
