// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.sec.ECPrivateKey;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.util.io.pem.PemObject;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader

private class PEMReader$ECDSAKeyPairParser extends PEMReader.KeyPairParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        try
        {
            co.org.bouncy.asn1.ASN1Sequence seq = readKeyPair(obj);
            ECPrivateKey pKey = ECPrivateKey.getInstance(seq);
            AlgorithmIdentifier algId = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, pKey.getParameters());
            PrivateKeyInfo privInfo = new PrivateKeyInfo(algId, pKey);
            SubjectPublicKeyInfo pubInfo = new SubjectPublicKeyInfo(algId, pKey.getPublicKey().getBytes());
            PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privInfo.getEncoded());
            X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubInfo.getEncoded());
            KeyFactory fact = KeyFactory.getInstance("ECDSA", asymProvider);
            return new KeyPair(fact.generatePublic(pubSpec), fact.generatePrivate(privSpec));
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem creating EC private key: ").append(e.toString()).toString(), e);
        }
    }

    private String asymProvider;
    final PEMReader this$0;

    public PEMReader$ECDSAKeyPairParser(String symProvider, String asymProvider)
    {
        this$0 = PEMReader.this;
        super(PEMReader.this, symProvider);
        this.asymProvider = asymProvider;
    }
}
