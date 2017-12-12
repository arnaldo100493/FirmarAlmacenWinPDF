// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509ExtensionUtils.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509ExtensionUtils;
import co.org.bouncy.operator.DigestCalculator;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            JcaX509CertificateHolder

public class JcaX509ExtensionUtils extends X509ExtensionUtils
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
            byte bytes[] = digest.digest(bOut.toByteArray());
            bOut.reset();
            return bytes;
        }

        private ByteArrayOutputStream bOut;
        private MessageDigest digest;

        public SHA1DigestCalculator(MessageDigest digest)
        {
            bOut = new ByteArrayOutputStream();
            this.digest = digest;
        }
    }


    public JcaX509ExtensionUtils()
        throws NoSuchAlgorithmException
    {
        super(new SHA1DigestCalculator(MessageDigest.getInstance("SHA1")));
    }

    public JcaX509ExtensionUtils(DigestCalculator calculator)
    {
        super(calculator);
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(X509Certificate cert)
        throws CertificateEncodingException
    {
        return super.createAuthorityKeyIdentifier(new JcaX509CertificateHolder(cert));
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(PublicKey pubKey)
    {
        return super.createAuthorityKeyIdentifier(SubjectPublicKeyInfo.getInstance(pubKey.getEncoded()));
    }

    public SubjectKeyIdentifier createSubjectKeyIdentifier(PublicKey publicKey)
    {
        return super.createSubjectKeyIdentifier(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
    }

    public SubjectKeyIdentifier createTruncatedSubjectKeyIdentifier(PublicKey publicKey)
    {
        return super.createSubjectKeyIdentifier(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
    }

    public static ASN1Primitive parseExtensionValue(byte encExtValue[])
        throws IOException
    {
        return ASN1Primitive.fromByteArray(ASN1OctetString.getInstance(encExtValue).getOctets());
    }
}
