// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcX509ExtensionUtils.java

package co.org.bouncy.cert.bc;

import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509ExtensionUtils;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.util.SubjectPublicKeyInfoFactory;
import co.org.bouncy.operator.DigestCalculator;
import java.io.*;

public class BcX509ExtensionUtils extends X509ExtensionUtils
{
    private static class SHA1DigestCalculator
        implements DigestCalculator
    {

        public AlgorithmIdentifier getAlgorithmIdentifier()
        {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }

        public OutputStream getOutputStream()
        {
            return bOut;
        }

        public byte[] getDigest()
        {
            byte bytes[] = bOut.toByteArray();
            bOut.reset();
            Digest sha1 = new SHA1Digest();
            sha1.update(bytes, 0, bytes.length);
            byte digest[] = new byte[sha1.getDigestSize()];
            sha1.doFinal(digest, 0);
            return digest;
        }

        private ByteArrayOutputStream bOut;

        private SHA1DigestCalculator()
        {
            bOut = new ByteArrayOutputStream();
        }

    }


    public BcX509ExtensionUtils()
    {
        super(new SHA1DigestCalculator());
    }

    public BcX509ExtensionUtils(DigestCalculator calculator)
    {
        super(calculator);
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(AsymmetricKeyParameter publicKey)
        throws IOException
    {
        return super.createAuthorityKeyIdentifier(SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey));
    }

    public SubjectKeyIdentifier createSubjectKeyIdentifier(AsymmetricKeyParameter publicKey)
        throws IOException
    {
        return super.createSubjectKeyIdentifier(SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey));
    }
}
