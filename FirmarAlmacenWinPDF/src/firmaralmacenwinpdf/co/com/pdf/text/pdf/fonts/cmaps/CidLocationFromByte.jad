// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CidLocationFromByte.java

package co.com.pdf.text.pdf.fonts.cmaps;

import co.com.pdf.text.io.RandomAccessSourceFactory;
import co.com.pdf.text.pdf.PRTokeniser;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            CidLocation

public class CidLocationFromByte
    implements CidLocation
{

    public CidLocationFromByte(byte data[])
    {
        this.data = data;
    }

    public PRTokeniser getLocation(String location)
        throws IOException
    {
        return new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(data)));
    }

    private byte data[];
}
