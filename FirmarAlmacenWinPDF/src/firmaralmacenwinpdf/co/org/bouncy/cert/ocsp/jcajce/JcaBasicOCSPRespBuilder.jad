// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaBasicOCSPRespBuilder.java

package co.org.bouncy.cert.ocsp.jcajce;

import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.ocsp.BasicOCSPRespBuilder;
import co.org.bouncy.cert.ocsp.OCSPException;
import co.org.bouncy.operator.DigestCalculator;
import java.security.PublicKey;

public class JcaBasicOCSPRespBuilder extends BasicOCSPRespBuilder
{

    public JcaBasicOCSPRespBuilder(PublicKey key, DigestCalculator digCalc)
        throws OCSPException
    {
        super(SubjectPublicKeyInfo.getInstance(key.getEncoded()), digCalc);
    }
}
