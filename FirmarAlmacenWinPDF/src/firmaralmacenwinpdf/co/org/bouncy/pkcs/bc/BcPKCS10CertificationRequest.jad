// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS10CertificationRequest.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.pkcs.CertificationRequest;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import co.org.bouncy.pkcs.PKCS10CertificationRequest;
import co.org.bouncy.pkcs.PKCSException;
import java.io.IOException;

public class BcPKCS10CertificationRequest extends PKCS10CertificationRequest
{

    public BcPKCS10CertificationRequest(CertificationRequest certificationRequest)
    {
        super(certificationRequest);
    }

    public BcPKCS10CertificationRequest(byte encoding[])
        throws IOException
    {
        super(encoding);
    }

    public BcPKCS10CertificationRequest(PKCS10CertificationRequest requestHolder)
    {
        super(requestHolder.toASN1Structure());
    }

    public AsymmetricKeyParameter getPublicKey()
        throws PKCSException
    {
        try
        {
            return PublicKeyFactory.createKey(getSubjectPublicKeyInfo());
        }
        catch(IOException e)
        {
            throw new PKCSException((new StringBuilder()).append("error extracting key encoding: ").append(e.getMessage()).toString(), e);
        }
    }
}
