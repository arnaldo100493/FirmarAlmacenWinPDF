// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRMFUtil.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.ExtensionsGenerator;
import co.org.bouncy.cert.CertIOException;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFRuntimeException

class CRMFUtil
{

    CRMFUtil()
    {
    }

    static void derEncodeToStream(ASN1Encodable obj, OutputStream stream)
    {
        DEROutputStream dOut = new DEROutputStream(stream);
        try
        {
            dOut.writeObject(obj);
            dOut.close();
        }
        catch(IOException e)
        {
            throw new CRMFRuntimeException((new StringBuilder()).append("unable to DER encode object: ").append(e.getMessage()).toString(), e);
        }
    }

    static void addExtension(ExtensionsGenerator extGenerator, ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws CertIOException
    {
        try
        {
            extGenerator.addExtension(oid, isCritical, value);
        }
        catch(IOException e)
        {
            throw new CertIOException((new StringBuilder()).append("cannot encode extension: ").append(e.getMessage()).toString(), e);
        }
    }
}
