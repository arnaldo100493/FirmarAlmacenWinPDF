// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcRSAKeyTransRecipientInfoGenerator.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.operator.bc.BcRSAAsymmetricKeyWrapper;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.cms.bc:
//            BcKeyTransRecipientInfoGenerator

public class BcRSAKeyTransRecipientInfoGenerator extends BcKeyTransRecipientInfoGenerator
{

    public BcRSAKeyTransRecipientInfoGenerator(byte subjectKeyIdentifier[], AlgorithmIdentifier encAlgId, AsymmetricKeyParameter publicKey)
    {
        super(subjectKeyIdentifier, new BcRSAAsymmetricKeyWrapper(encAlgId, publicKey));
    }

    public BcRSAKeyTransRecipientInfoGenerator(X509CertificateHolder recipientCert)
        throws IOException
    {
        super(recipientCert, new BcRSAAsymmetricKeyWrapper(recipientCert.getSubjectPublicKeyInfo().getAlgorithmId(), recipientCert.getSubjectPublicKeyInfo()));
    }
}
