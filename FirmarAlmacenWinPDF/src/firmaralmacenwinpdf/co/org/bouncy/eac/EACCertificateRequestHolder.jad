// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EACCertificateRequestHolder.java

package co.org.bouncy.eac;

import co.org.bouncy.asn1.ASN1ParsingException;
import co.org.bouncy.asn1.eac.*;
import co.org.bouncy.eac.operator.EACSignatureVerifier;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.eac:
//            EACIOException, EACException

public class EACCertificateRequestHolder
{

    private static CVCertificateRequest parseBytes(byte requestEncoding[])
        throws IOException
    {
        try
        {
            return CVCertificateRequest.getInstance(requestEncoding);
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

    public EACCertificateRequestHolder(byte certEncoding[])
        throws IOException
    {
        this(parseBytes(certEncoding));
    }

    public EACCertificateRequestHolder(CVCertificateRequest request)
    {
        this.request = request;
    }

    public CVCertificateRequest toASN1Structure()
    {
        return request;
    }

    public PublicKeyDataObject getPublicKeyDataObject()
    {
        return request.getPublicKey();
    }

    public boolean isInnerSignatureValid(EACSignatureVerifier verifier)
        throws EACException
    {
        try
        {
            OutputStream vOut = verifier.getOutputStream();
            vOut.write(request.getCertificateBody().getEncoded("DER"));
            vOut.close();
            return verifier.verify(request.getInnerSignature());
        }
        catch(Exception e)
        {
            throw new EACException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private CVCertificateRequest request;
}
