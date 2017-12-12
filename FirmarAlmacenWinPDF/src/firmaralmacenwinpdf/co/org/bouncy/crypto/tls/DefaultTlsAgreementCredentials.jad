// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultTlsAgreementCredentials.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.BasicAgreement;
import co.org.bouncy.crypto.agreement.DHBasicAgreement;
import co.org.bouncy.crypto.agreement.ECDHBasicAgreement;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsAgreementCredentials, Certificate

public class DefaultTlsAgreementCredentials
    implements TlsAgreementCredentials
{

    public DefaultTlsAgreementCredentials(Certificate certificate, AsymmetricKeyParameter privateKey)
    {
        if(certificate == null)
            throw new IllegalArgumentException("'certificate' cannot be null");
        if(certificate.isEmpty())
            throw new IllegalArgumentException("'certificate' cannot be empty");
        if(privateKey == null)
            throw new IllegalArgumentException("'privateKey' cannot be null");
        if(!privateKey.isPrivate())
            throw new IllegalArgumentException("'privateKey' must be private");
        if(privateKey instanceof DHPrivateKeyParameters)
        {
            basicAgreement = new DHBasicAgreement();
            truncateAgreement = true;
        } else
        if(privateKey instanceof ECPrivateKeyParameters)
        {
            basicAgreement = new ECDHBasicAgreement();
            truncateAgreement = false;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("'privateKey' type not supported: ").append(privateKey.getClass().getName()).toString());
        }
        this.certificate = certificate;
        this.privateKey = privateKey;
    }

    public Certificate getCertificate()
    {
        return certificate;
    }

    public byte[] generateAgreement(AsymmetricKeyParameter peerPublicKey)
    {
        basicAgreement.init(privateKey);
        BigInteger agreementValue = basicAgreement.calculateAgreement(peerPublicKey);
        if(truncateAgreement)
            return BigIntegers.asUnsignedByteArray(agreementValue);
        else
            return BigIntegers.asUnsignedByteArray(basicAgreement.getFieldSize(), agreementValue);
    }

    protected Certificate certificate;
    protected AsymmetricKeyParameter privateKey;
    protected BasicAgreement basicAgreement;
    protected boolean truncateAgreement;
}
