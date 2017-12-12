// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorCorrectionLevel.java

package co.com.pdf.text.pdf.qrcode;


public final class ErrorCorrectionLevel
{

    private ErrorCorrectionLevel(int ordinal, int bits, String name)
    {
        this.ordinal = ordinal;
        this.bits = bits;
        this.name = name;
    }

    public int ordinal()
    {
        return ordinal;
    }

    public int getBits()
    {
        return bits;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return name;
    }

    public static ErrorCorrectionLevel forBits(int bits)
    {
        if(bits < 0 || bits >= FOR_BITS.length)
            throw new IllegalArgumentException();
        else
            return FOR_BITS[bits];
    }

    public static final ErrorCorrectionLevel L;
    public static final ErrorCorrectionLevel M;
    public static final ErrorCorrectionLevel Q;
    public static final ErrorCorrectionLevel H;
    private static final ErrorCorrectionLevel FOR_BITS[];
    private final int ordinal;
    private final int bits;
    private final String name;

    static 
    {
        L = new ErrorCorrectionLevel(0, 1, "L");
        M = new ErrorCorrectionLevel(1, 0, "M");
        Q = new ErrorCorrectionLevel(2, 3, "Q");
        H = new ErrorCorrectionLevel(3, 2, "H");
        FOR_BITS = (new ErrorCorrectionLevel[] {
            M, L, H, Q
        });
    }
}
