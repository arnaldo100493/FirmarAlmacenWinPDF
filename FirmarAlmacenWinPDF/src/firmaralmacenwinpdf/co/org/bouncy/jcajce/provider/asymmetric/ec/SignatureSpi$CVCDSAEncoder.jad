// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.jcajce.provider.asymmetric.util.DSAEncoder;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            SignatureSpi

private static class SignatureSpi$CVCDSAEncoder
    implements DSAEncoder
{

    public byte[] encode(BigInteger r, BigInteger s)
        throws IOException
    {
        byte first[] = makeUnsigned(r);
        byte second[] = makeUnsigned(s);
        byte res[];
        if(first.length > second.length)
            res = new byte[first.length * 2];
        else
            res = new byte[second.length * 2];
        System.arraycopy(first, 0, res, res.length / 2 - first.length, first.length);
        System.arraycopy(second, 0, res, res.length - second.length, second.length);
        return res;
    }

    private byte[] makeUnsigned(BigInteger val)
    {
        byte res[] = val.toByteArray();
        if(res[0] == 0)
        {
            byte tmp[] = new byte[res.length - 1];
            System.arraycopy(res, 1, tmp, 0, tmp.length);
            return tmp;
        } else
        {
            return res;
        }
    }

    public BigInteger[] decode(byte encoding[])
        throws IOException
    {
        BigInteger sig[] = new BigInteger[2];
        byte first[] = new byte[encoding.length / 2];
        byte second[] = new byte[encoding.length / 2];
        System.arraycopy(encoding, 0, first, 0, first.length);
        System.arraycopy(encoding, first.length, second, 0, second.length);
        sig[0] = new BigInteger(1, first);
        sig[1] = new BigInteger(1, second);
        return sig;
    }

    private SignatureSpi$CVCDSAEncoder()
    {
    }

    SignatureSpi$CVCDSAEncoder(SignatureSpi._cls1 x0)
    {
        this();
    }
}
