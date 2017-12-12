// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncodings.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            ExtraEncoding, PdfEncodings

private static class PdfEncodings$SymbolTTConversion
    implements ExtraEncoding
{

    public byte[] charToByte(char char1, String encoding)
    {
        if((char1 & 0xff00) == 0 || (char1 & 0xff00) == 61440)
            return (new byte[] {
                (byte)char1
            });
        else
            return new byte[0];
    }

    public byte[] charToByte(String text, String encoding)
    {
        char ch[] = text.toCharArray();
        byte b[] = new byte[ch.length];
        int ptr = 0;
        int len = ch.length;
        for(int k = 0; k < len; k++)
        {
            char c = ch[k];
            if((c & 0xff00) == 0 || (c & 0xff00) == 61440)
                b[ptr++] = (byte)c;
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

    private PdfEncodings$SymbolTTConversion()
    {
    }

    PdfEncodings$SymbolTTConversion(PdfEncodings._cls1 x0)
    {
        this();
    }
}
