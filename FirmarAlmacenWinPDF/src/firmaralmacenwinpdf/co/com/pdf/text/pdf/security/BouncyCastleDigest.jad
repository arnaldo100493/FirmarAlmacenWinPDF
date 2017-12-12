// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BouncyCastleDigest.java

package co.com.pdf.text.pdf.security;

import co.org.bouncy.jcajce.provider.digest.*;
import java.security.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            ExternalDigest, DigestAlgorithms

public class BouncyCastleDigest
    implements ExternalDigest
{

    public BouncyCastleDigest()
    {
    }

    public MessageDigest getMessageDigest(String hashAlgorithm)
        throws GeneralSecurityException
    {
        String oid = DigestAlgorithms.getAllowedDigests(hashAlgorithm);
        if(oid == null)
            throw new NoSuchAlgorithmException(hashAlgorithm);
        if(oid.equals("1.2.840.113549.2.2"))
            return new co.org.bouncy.jcajce.provider.digest.MD2.Digest();
        if(oid.equals("1.2.840.113549.2.5"))
            return new co.org.bouncy.jcajce.provider.digest.MD5.Digest();
        if(oid.equals("1.3.14.3.2.26"))
            return new co.org.bouncy.jcajce.provider.digest.SHA1.Digest();
        if(oid.equals("2.16.840.1.101.3.4.2.4"))
            return new co.org.bouncy.jcajce.provider.digest.SHA224.Digest();
        if(oid.equals("2.16.840.1.101.3.4.2.1"))
            return new co.org.bouncy.jcajce.provider.digest.SHA256.Digest();
        if(oid.equals("2.16.840.1.101.3.4.2.2"))
            return new co.org.bouncy.jcajce.provider.digest.SHA384.Digest();
        if(oid.equals("2.16.840.1.101.3.4.2.3"))
            return new co.org.bouncy.jcajce.provider.digest.SHA512.Digest();
        if(oid.equals("1.3.36.3.2.2"))
            return new co.org.bouncy.jcajce.provider.digest.RIPEMD128.Digest();
        if(oid.equals("1.3.36.3.2.1"))
            return new co.org.bouncy.jcajce.provider.digest.RIPEMD160.Digest();
        if(oid.equals("1.3.36.3.2.3"))
            return new co.org.bouncy.jcajce.provider.digest.RIPEMD256.Digest();
        if(oid.equals("1.2.643.2.2.9"))
            return new co.org.bouncy.jcajce.provider.digest.GOST3411.Digest();
        else
            throw new NoSuchAlgorithmException(hashAlgorithm);
    }
}
