// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF256.java

package co.com.pdf.text.pdf.qrcode;


// Referenced classes of package co.com.pdf.text.pdf.qrcode:
//            GF256Poly

public final class GF256
{

    private GF256(int primitive)
    {
        int x = 1;
        for(int i = 0; i < 256; i++)
        {
            expTable[i] = x;
            x <<= 1;
            if(x >= 256)
                x ^= primitive;
        }

        for(int i = 0; i < 255; i++)
            logTable[expTable[i]] = i;

    }

    GF256Poly getZero()
    {
        return zero;
    }

    GF256Poly getOne()
    {
        return one;
    }

    GF256Poly buildMonomial(int degree, int coefficient)
    {
        if(degree < 0)
            throw new IllegalArgumentException();
        if(coefficient == 0)
        {
            return zero;
        } else
        {
            int coefficients[] = new int[degree + 1];
            coefficients[0] = coefficient;
            return new GF256Poly(this, coefficients);
        }
    }

    static int addOrSubtract(int a, int b)
    {
        return a ^ b;
    }

    int exp(int a)
    {
        return expTable[a];
    }

    int log(int a)
    {
        if(a == 0)
            throw new IllegalArgumentException();
        else
            return logTable[a];
    }

    int inverse(int a)
    {
        if(a == 0)
            throw new ArithmeticException();
        else
            return expTable[255 - logTable[a]];
    }

    int multiply(int a, int b)
    {
        if(a == 0 || b == 0)
            return 0;
        if(a == 1)
            return b;
        if(b == 1)
            return a;
        else
            return expTable[(logTable[a] + logTable[b]) % 255];
    }

    public static final GF256 QR_CODE_FIELD = new GF256(285);
    public static final GF256 DATA_MATRIX_FIELD = new GF256(301);
    private final int expTable[] = new int[256];
    private final int logTable[] = new int[256];
    private final GF256Poly zero = new GF256Poly(this, new int[] {
        0
    });
    private final GF256Poly one = new GF256Poly(this, new int[] {
        1
    });

}
