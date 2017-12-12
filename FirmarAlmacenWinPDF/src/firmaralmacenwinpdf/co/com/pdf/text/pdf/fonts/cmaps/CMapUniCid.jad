// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapUniCid.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.Utilities;
import co.com.pdf.text.pdf.*;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            AbstractCMap, CMapToUnicode

public class CMapUniCid extends AbstractCMap
{

    public CMapUniCid()
    {
        map = new IntHashtable(0x10001);
    }

    void addChar(PdfString mark, PdfObject code)
    {
        if(!(code instanceof PdfNumber))
            return;
        String s = decodeStringToUnicode(mark);
        int codepoint;
        if(Utilities.isSurrogatePair(s, 0))
            codepoint = Utilities.convertToUtf32(s, 0);
        else
            codepoint = s.charAt(0);
        map.put(codepoint, ((PdfNumber)code).intValue());
    }

    public int lookup(int character)
    {
        return map.get(character);
    }

    public CMapToUnicode exportToUnicode()
    {
        CMapToUnicode uni = new CMapToUnicode();
        int keys[] = map.toOrderedKeys();
        int arr$[] = keys;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            int key = arr$[i$];
            uni.addChar(map.get(key), Utilities.convertFromUtf32(key));
        }

        return uni;
    }

    private IntHashtable map;
}
