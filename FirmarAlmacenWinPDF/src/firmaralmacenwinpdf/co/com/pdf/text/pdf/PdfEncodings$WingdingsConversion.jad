// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncodings.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            ExtraEncoding, PdfEncodings

private static class PdfEncodings$WingdingsConversion
    implements ExtraEncoding
{

    public byte[] charToByte(char char1, String encoding)
    {
        if(char1 == ' ')
            return (new byte[] {
                (byte)char1
            });
        if(char1 >= '\u2701' && char1 <= '\u27BE')
        {
            byte v = table[char1 - 9984];
            if(v != 0)
                return (new byte[] {
                    v
                });
        }
        return new byte[0];
    }

    public byte[] charToByte(String text, String encoding)
    {
        char cc[] = text.toCharArray();
        byte b[] = new byte[cc.length];
        int ptr = 0;
        int len = cc.length;
        for(int k = 0; k < len; k++)
        {
            char c = cc[k];
            if(c == ' ')
            {
                b[ptr++] = (byte)c;
                continue;
            }
            if(c < '\u2701' || c > '\u27BE')
                continue;
            byte v = table[c - 9984];
            if(v != 0)
                b[ptr++] = v;
        }

        if(ptr == len)
        {
            return b;
        } else
        {
            byte b2[] = new byte[ptr];
            System.arraycopy(b, 0, b2, 0, ptr);
            return b2;
        }
    }

    public String byteToChar(byte b[], String encoding)
    {
        return null;
    }

    private static final byte table[] = {
        0, 35, 34, 0, 0, 0, 41, 62, 81, 42, 
        0, 0, 65, 63, 0, 0, 0, 0, 0, -4, 
        0, 0, 0, -5, 0, 0, 0, 0, 0, 0, 
        86, 0, 88, 89, 0, 0, 0, 0, 0, 0, 
        0, 0, -75, 0, 0, 0, 0, 0, -74, 0, 
        0, 0, -83, -81, -84, 0, 0, 0, 0, 0, 
        0, 0, 0, 124, 123, 0, 0, 0, 84, 0, 
        0, 0, 0, 0, 0, 0, 0, -90, 0, 0, 
        0, 113, 114, 0, 0, 0, 117, 0, 0, 0, 
        0, 0, 0, 125, 126, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, -116, -115, 
        -114, -113, -112, -111, -110, -109, -108, -107, -127, -126, 
        -125, -124, -123, -122, -121, -120, -119, -118, -116, -115, 
        -114, -113, -112, -111, -110, -109, -108, -107, -24, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, -24, -40, 0, 0, -60, -58, 0, 0, -16, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, -36, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0
    };


    private PdfEncodings$WingdingsConversion()
    {
    }

    PdfEncodings$WingdingsConversion(PdfEncodings._cls1 x0)
    {
        this();
    }
}
