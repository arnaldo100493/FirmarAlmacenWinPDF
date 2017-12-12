// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrivateKeySignature.java

package co.com.pdf.text.pdf.security;

import java.security.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            ExternalSignature, DigestAlgorithms

public class PrivateKeySignature
    implements ExternalSignature
{

    public PrivateKeySignature(PrivateKey pk, String hashAlgorithm, String provider)
    {
        this.pk = pk;
        this.provider = provider;
        this.hashAlgorithm = DigestAlgorithms.getDigest(DigestAlgorithms.getAllowedDigests(hashAlgorithm));
        encryptionAlgorithm = pk.getAlgorithm();
        if(encryptionAlgorithm.startsWith("EC"))
            encryptionAlgorithm = "ECDSA";
    }

    public String getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public String getEncryptionAlgorithm()
    {
        return encryptionAlgorithm;
    }

    public byte[] sign(byte b[])
        throws GeneralSecurityException
    {
        String signMode = (new StringBuilder()).append(hashAlgorithm).append("with").append(encryptionAlgorithm).toString();
        Signature sig;
        if(provider == null)
            sig = Signature.getInstance(signMode);
        else
            sig = Signature.getInstance(signMode, provider);
        sig.initSign(pk);
        sig.update(b);
        return sig.sign();
    }

    private PrivateKey pk;
    private String hashAlgorithm;
    private String encryptionAlgorithm;
    private String provider;
}
