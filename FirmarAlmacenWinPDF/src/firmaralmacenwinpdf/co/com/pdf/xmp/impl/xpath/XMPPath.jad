// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPPath.java

package co.com.pdf.xmp.impl.xpath;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.com.pdf.xmp.impl.xpath:
//            XMPPathSegment

public class XMPPath
{

    public XMPPath()
    {
        segments = new ArrayList(5);
    }

    public void add(XMPPathSegment segment)
    {
        segments.add(segment);
    }

    public XMPPathSegment getSegment(int index)
    {
        return (XMPPathSegment)segments.get(index);
    }

    public int size()
    {
        return segments.size();
    }

    public String toString()
    {
        StringBuffer result = new StringBuffer();
        for(int index = 1; index < size(); index++)
        {
            result.append(getSegment(index));
            if(index >= size() - 1)
                continue;
            int kind = getSegment(index + 1).getKind();
            if(kind == 1 || kind == 2)
                result.append('/');
        }

        return result.toString();
    }

    public static final int STRUCT_FIELD_STEP = 1;
    public static final int QUALIFIER_STEP = 2;
    public static final int ARRAY_INDEX_STEP = 3;
    public static final int ARRAY_LAST_STEP = 4;
    public static final int QUAL_SELECTOR_STEP = 5;
    public static final int FIELD_SELECTOR_STEP = 6;
    public static final int SCHEMA_NODE = 0x80000000;
    public static final int STEP_SCHEMA = 0;
    public static final int STEP_ROOT_PROP = 1;
    private List segments;
}
