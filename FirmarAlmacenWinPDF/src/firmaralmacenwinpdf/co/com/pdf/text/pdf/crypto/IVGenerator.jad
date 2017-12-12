// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IVGenerator.java

package co.com.pdf.text.pdf.crypto;


// Referenced classes of package co.com.pdf.text.pdf.crypto:
//            ARCFOUREncryption

public final class IVGenerator
{

    private IVGenerator()
    {
    }

    public static byte[] getIV()
    {
        return getIV(16);
    }

    public static byte[] getIV(int len)
    {
        byte b[] = new byte[len];
        synchronized(arcfour)
        {
            arcfour.encryptARCFOUR(b);
        }
        return b;
    }

    private static ARCFOUREncryption arcfour;

    static 
    {
        arcfour = new ARCFOUREncryption();
        long time = System.currentTimeMillis();
        long mem = Runtime.getRuntime().freeMemory();
        String s = (new StringBuilder()).append(time).append("+").append(mem).toString();
        arcfour.prepareARCFOURKey(s.getBytes());
    }
}
