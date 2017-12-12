// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReedSolomonEncoder.java

package co.com.pdf.text.pdf.qrcode;

import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf.qrcode:
//            GF256Poly, GF256

public final class ReedSolomonEncoder
{

    public ReedSolomonEncoder(GF256 field)
    {
        if(!GF256.QR_CODE_FIELD.equals(field))
        {
            throw new IllegalArgumentException("Only QR Code is supported at this time");
        } else
        {
            this.field = field;
            cachedGenerators = new ArrayList();
            cachedGenerators.add(new GF256Poly(field, new int[] {
                1
            }));
            return;
        }
    }

    private GF256Poly buildGenerator(int degree)
    {
        if(degree >= cachedGenerators.size())
        {
            GF256Poly lastGenerator = (GF256Poly)cachedGenerators.get(cachedGenerators.size() - 1);
            for(int d = cachedGenerators.size(); d <= degree; d++)
            {
                GF256Poly nextGenerator = lastGenerator.multiply(new GF256Poly(field, new int[] {
                    1, field.exp(d - 1)
                }));
                cachedGenerators.add(nextGenerator);
                lastGenerator = nextGenerator;
            }

        }
        return (GF256Poly)cachedGenerators.get(degree);
    }

    public void encode(int toEncode[], int ecBytes)
    {
        if(ecBytes == 0)
            throw new IllegalArgumentException("No error correction bytes");
        int dataBytes = toEncode.length - ecBytes;
        if(dataBytes <= 0)
            throw new IllegalArgumentException("No data bytes provided");
        GF256Poly generator = buildGenerator(ecBytes);
        int infoCoefficients[] = new int[dataBytes];
        System.arraycopy(toEncode, 0, infoCoefficients, 0, dataBytes);
        GF256Poly info = new GF256Poly(field, infoCoefficients);
        info = info.multiplyByMonomial(ecBytes, 1);
        GF256Poly remainder = info.divide(generator)[1];
        int coefficients[] = remainder.getCoefficients();
        int numZeroCoefficients = ecBytes - coefficients.length;
        for(int i = 0; i < numZeroCoefficients; i++)
            toEncode[dataBytes + i] = 0;

        System.arraycopy(coefficients, 0, toEncode, dataBytes + numZeroCoefficients, coefficients.length);
    }

    private final GF256 field;
    private final ArrayList cachedGenerators;
}
