// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapCidByte.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.pdf.*;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            AbstractCMap

public class CMapCidByte extends AbstractCMap
{

    public CMapCidByte()
    {
        map = new HashMap();
    }

    void addChar(PdfString mark, PdfObject code)
    {
        if(!(code instanceof PdfNumber))
        {
            return;
        } else
        {
            byte ser[] = decodeStringToByte(mark);
            map.put(Integer.valueOf(((PdfNumber)code).intValue()), ser);
            return;
        }
    }

    public byte[] lookup(int cid)
    {
        byte ser[] = (byte[])map.get(Integer.valueOf(cid));
        if(ser == null)
            return EMPTY;
        else
            return ser;
    }

    private HashMap map;
    private final byte EMPTY[] = new byte[0];
}
