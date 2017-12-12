// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EACCertificateHolder.java

package co.org.bouncy.eac;

import co.org.bouncy.asn1.ASN1ParsingException;
import co.org.bouncy.asn1.eac.*;
import co.org.bouncy.eac.operator.EACSignatureVerifier;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.eac:
//            EACIOException, EACException

public class EACCertificateHolder
{

    private static CVCertificate parseBytes(byte certEncoding[])
        throws IOException
    {
        try
        {
            return CVCertificate.getInstance(certEncoding);
        }
        catch(ClassCastException e)
        {
            throw new EACIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new EACIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(ASN1ParsingException e)
        {
            if(e.getCause() instanceof IOException)
                throw (IOException)e.getCause();
            else
                throw new EACIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public EACCertificateHolder(byte certEncoding[])
        throws IOException
    {
        this(parseBytes(certEncoding));
    }

    public EACCertificateHolder(CVCertificate cvCertificate)
    {
        this.cvCertificate = cvCertificate;
    }

    public CVCertificate toASN1Structure()
    {
        return cvCertificate;
    }

    public PublicKeyDataObject getPublicKeyDataObject()
    {
        return cvCertificate.getBody().getPublicKey();
    }

    public boolean isSignatureValid(EACSignatureVerifier verifier)
        throws EACException
    {
        try
        {
            OutputStream vOut = verifier.getOutputStream();
            vOut.write(cvCertificate.getBody().getEncoded("DER"));
            vOut.close();
            return verifier.verify(cvCertificate.getSignature());
        }
        catch(Exception e)
        {
            throw new EACException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private CVCertificate cvCertificate;
}
