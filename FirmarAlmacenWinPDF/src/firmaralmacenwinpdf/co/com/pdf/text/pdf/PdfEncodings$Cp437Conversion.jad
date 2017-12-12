// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncodings.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable, ExtraEncoding, PdfEncodings

private static class PdfEncodings$Cp437Conversion
    implements ExtraEncoding
{

    public byte[] charToByte(String text, String encoding)
    {
        char cc[] = text.toCharArray();
        byte b[] = new byte[cc.length];
        int ptr = 0;
        int len = cc.length;
        for(int k = 0; k < len; k++)
        {
            char c = cc[k];
            if(c < '\200')
            {
                b[ptr++] = (byte)c;
                continue;
            }
            byte v = (byte)c2b.get(c);
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

    public byte[] charToByte(char char1, String encoding)
    {
        if(char1 < '\200')
            return (new byte[] {
                (byte)char1
            });
        byte v = (byte)c2b.get(char1);
        if(v != 0)
            return (new byte[] {
                v
            });
        else
            return new byte[0];
    }

    public String byteToChar(byte b[], String encoding)
    {
        int len = b.length;
        char cc[] = new char[len];
        int ptr = 0;
        for(int k = 0; k < len; k++)
        {
            int c = b[k] & 0xff;
            if(c < 32)
                continue;
            if(c < 128)
            {
                cc[ptr++] = (char)c;
            } else
            {
                char v = table[c - 128];
                cc[ptr++] = v;
            }
        }

        return new String(cc, 0, ptr);
    }

    private static IntHashtable c2b;
    private static final char table[] = {
        '\307', '\374', '\351', '\342', '\344', '\340', '\345', '\347', '\352', '\353', 
        '\350', '\357', '\356', '\354', '\304', '\305', '\311', '\346', '\306', '\364', 
        '\366', '\362', '\373', '\371', '\377', '\326', '\334', '\242', '\243', '\245', 
        '\u20A7', '\u0192', '\341', '\355', '\363', '\372', '\361', '\321', '\252', '\272', 
        '\277', '\u2310', '\254', '\275', '\274', '\241', '\253', '\273', '\u2591', '\u2592', 
        '\u2593', '\u2502', '\u2524', '\u2561', '\u2562', '\u2556', '\u2555', '\u2563', '\u2551', '\u2557', 
        '\u255D', '\u255C', '\u255B', '\u2510', '\u2514', '\u2534', '\u252C', '\u251C', '\u2500', '\u253C', 
        '\u255E', '\u255F', '\u255A', '\u2554', '\u2569', '\u2566', '\u2560', '\u2550', '\u256C', '\u2567', 
        '\u2568', '\u2564', '\u2565', '\u2559', '\u2558', '\u2552', '\u2553', '\u256B', '\u256A', '\u2518', 
        '\u250C', '\u2588', '\u2584', '\u258C', '\u2590', '\u2580', '\u03B1', '\337', '\u0393', '\u03C0', 
        '\u03A3', '\u03C3', '\265', '\u03C4', '\u03A6', '\u0398', '\u03A9', '\u03B4', '\u221E', '\u03C6', 
        '\u03B5', '\u2229', '\u2261', '\261', '\u2265', '\u2264', '\u2320', '\u2321', '\367', '\u2248', 
        '\260', '\u2219', '\267', '\u221A', '\u207F', '\262', '\u25A0', '\240'
    };

    static 
    {
        c2b = new IntHashtable();
        for(int k = 0; k < table.length; k++)
            c2b.put(table[k], k + 128);

    }

    private PdfEncodings$Cp437Conversion()
    {
    }

    PdfEncodings$Cp437Conversion(PdfEncodings._cls1 x0)
    {
        this();
    }
}
