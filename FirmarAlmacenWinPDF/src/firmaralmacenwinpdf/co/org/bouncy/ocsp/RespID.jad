// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RespID.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.ResponderID;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import java.security.MessageDigest;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.ocsp:
//            OCSPException, OCSPUtil

public class RespID
{

    public RespID(ResponderID id)
    {
        this.id = id;
    }

    public RespID(X500Principal name)
    {
        id = new ResponderID(X500Name.getInstance(name.getEncoded()));
    }

    public RespID(PublicKey key)
        throws OCSPException
    {
        try
        {
            MessageDigest digest = OCSPUtil.createDigestInstance("SHA1", null);
            ASN1InputStream aIn = new ASN1InputStream(key.getEncoded());
            SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(aIn.readObject());
            digest.update(info.getPublicKeyData().getBytes());
            co.org.bouncy.asn1.ASN1OctetString keyHash = new DEROctetString(digest.digest());
            id = new ResponderID(keyHash);
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

    ResponderID id;
}
