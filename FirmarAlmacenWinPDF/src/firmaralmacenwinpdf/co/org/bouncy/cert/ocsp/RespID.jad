// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RespID.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.ResponderID;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.DigestCalculator;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            OCSPException

public class RespID
{

    public RespID(ResponderID id)
    {
        this.id = id;
    }

    public RespID(X500Name name)
    {
        id = new ResponderID(name);
    }

    public RespID(SubjectPublicKeyInfo subjectPublicKeyInfo, DigestCalculator digCalc)
        throws OCSPException
    {
        try
        {
            if(!digCalc.getAlgorithmIdentifier().equals(HASH_SHA1))
                throw new IllegalArgumentException("only SHA-1 can be used with RespID");
            OutputStream digOut = digCalc.getOutputStream();
            digOut.write(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            digOut.close();
            id = new ResponderID(new DEROctetString(digCalc.getDigest()));
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("problem creating ID: ").append(e).toString(), e);
        }
    }

    public ResponderID toASN1Object()
    {
        return id;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof RespID))
        {
            return false;
        } else
        {
            RespID obj = (RespID)o;
            return id.equals(obj.id);
        }
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    public static final AlgorithmIdentifier HASH_SHA1;
    ResponderID id;

    static 
    {
        HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    }
}
