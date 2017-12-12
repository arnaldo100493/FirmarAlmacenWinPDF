// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProofOfPossessionSigningKeyBuilder.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.crmf.*;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.operator.ContentSigner;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, PKMACValueGenerator, CRMFUtil

public class ProofOfPossessionSigningKeyBuilder
{

    public ProofOfPossessionSigningKeyBuilder(CertRequest certRequest)
    {
        this.certRequest = certRequest;
    }

    public ProofOfPossessionSigningKeyBuilder(SubjectPublicKeyInfo pubKeyInfo)
    {
        this.pubKeyInfo = pubKeyInfo;
    }

    public ProofOfPossessionSigningKeyBuilder setSender(GeneralName name)
    {
        this.name = name;
        return this;
    }

    public ProofOfPossessionSigningKeyBuilder setPublicKeyMac(PKMACValueGenerator generator, char password[])
        throws CRMFException
    {
        publicKeyMAC = generator.generate(password, pubKeyInfo);
        return this;
    }

    public POPOSigningKey build(ContentSigner signer)
    {
        if(name != null && publicKeyMAC != null)
            throw new IllegalStateException("name and publicKeyMAC cannot both be set.");
        POPOSigningKeyInput popo;
        if(certRequest != null)
        {
            popo = null;
            CRMFUtil.derEncodeToStream(certRequest, signer.getOutputStream());
        } else
        if(name != null)
        {
            popo = new POPOSigningKeyInput(name, pubKeyInfo);
            CRMFUtil.derEncodeToStream(popo, signer.getOutputStream());
        } else
        {
            popo = new POPOSigningKeyInput(publicKeyMAC, pubKeyInfo);
            CRMFUtil.derEncodeToStream(popo, signer.getOutputStream());
        }
        return new POPOSigningKey(popo, signer.getAlgorithmIdentifier(), new DERBitString(signer.getSignature()));
    }

    private CertRequest certRequest;
    private SubjectPublicKeyInfo pubKeyInfo;
    private GeneralName name;
    private PKMACValue publicKeyMAC;
}
