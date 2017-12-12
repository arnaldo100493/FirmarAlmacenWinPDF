// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcKeyTransRecipientInfoGenerator.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.cms.IssuerAndSerialNumber;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.KeyTransRecipientInfoGenerator;
import co.org.bouncy.operator.bc.BcAsymmetricKeyWrapper;

public abstract class BcKeyTransRecipientInfoGenerator extends KeyTransRecipientInfoGenerator
{

    public BcKeyTransRecipientInfoGenerator(X509CertificateHolder recipientCert, BcAsymmetricKeyWrapper wrapper)
    {
        super(new IssuerAndSerialNumber(recipientCert.toASN1Structure()), wrapper);
    }

    public BcKeyTransRecipientInfoGenerator(byte subjectKeyIdentifier[], BcAsymmetricKeyWrapper wrapper)
    {
        super(subjectKeyIdentifier, wrapper);
    }
}
