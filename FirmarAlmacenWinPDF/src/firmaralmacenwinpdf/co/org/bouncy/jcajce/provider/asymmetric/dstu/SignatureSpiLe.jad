// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignatureSpiLe.java

package co.org.bouncy.jcajce.provider.asymmetric.dstu;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DEROctetString;
import java.io.IOException;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dstu:
//            SignatureSpi

public class SignatureSpiLe extends SignatureSpi
{

    public SignatureSpiLe()
    {
    }

    void reverseBytes(byte bytes[])
    {
        for(int i = 0; i < bytes.length / 2; i++)
        {
            byte tmp = bytes[i];
            bytes[i] = bytes[bytes.length - 1 - i];
            bytes[bytes.length - 1 - i] = tmp;
        }

    }

    protected byte[] engineSign()
        throws SignatureException
    {
        byte signature[] = ASN1OctetString.getInstance(super.engineSign()).getOctets();
        reverseBytes(signature);
        try
        {
            return (new DEROctetString(signature)).getEncoded();
        }
        catch(Exception e)
        {
            throw new SignatureException(e.toString());
        }
    }

    protected boolean engineVerify(byte sigBytes[])
        throws SignatureException
    {
        byte bytes[] = null;
        try
        {
            bytes = ((ASN1OctetString)ASN1OctetString.fromByteArray(sigBytes)).getOctets();
        }
        catch(IOException e)
        {
            throw new SignatureException("error decoding signature bytes.");
        }
        reverseBytes(bytes);
        try
        {
            return super.engineVerify((new DEROctetString(bytes)).getEncoded());
        }
        catch(SignatureException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new SignatureException(e.toString());
        }
    }
}
