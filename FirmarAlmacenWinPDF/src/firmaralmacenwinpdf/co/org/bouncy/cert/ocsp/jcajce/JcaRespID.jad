// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaRespID.java

package co.org.bouncy.cert.ocsp.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.ocsp.OCSPException;
import co.org.bouncy.cert.ocsp.RespID;
import co.org.bouncy.operator.DigestCalculator;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;

public class JcaRespID extends RespID
{

    public JcaRespID(X500Principal name)
    {
        super(X500Name.getInstance(name.getEncoded()));
    }

    public JcaRespID(PublicKey pubKey, DigestCalculator digCalc)
        throws OCSPException
    {
        super(SubjectPublicKeyInfo.getInstance(pubKey.getEncoded()), digCalc);
    }
}
