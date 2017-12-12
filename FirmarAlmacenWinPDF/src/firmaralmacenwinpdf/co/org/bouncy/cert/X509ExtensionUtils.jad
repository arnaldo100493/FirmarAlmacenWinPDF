// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509ExtensionUtils.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.DigestCalculator;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert:
//            CertRuntimeException, X509CertificateHolder

public class X509ExtensionUtils
{

    public X509ExtensionUtils(DigestCalculator calculator)
    {
        this.calculator = calculator;
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(X509CertificateHolder certHolder)
    {
        GeneralName genName;
        if(certHolder.getVersionNumber() != 3)
        {
            genName = new GeneralName(certHolder.getIssuer());
            SubjectPublicKeyInfo info = certHolder.getSubjectPublicKeyInfo();
            return new AuthorityKeyIdentifier(calculateIdentifier(info), new GeneralNames(genName), certHolder.getSerialNumber());
        }
        genName = new GeneralName(certHolder.getIssuer());
        Extension ext = certHolder.getExtension(Extension.subjectKeyIdentifier);
        if(ext != null)
        {
            ASN1OctetString str = ASN1OctetString.getInstance(ext.getParsedValue());
            return new AuthorityKeyIdentifier(str.getOctets(), new GeneralNames(genName), certHolder.getSerialNumber());
        } else
        {
            SubjectPublicKeyInfo info = certHolder.getSubjectPublicKeyInfo();
            return new AuthorityKeyIdentifier(calculateIdentifier(info), new GeneralNames(genName), certHolder.getSerialNumber());
        }
    }

    public AuthorityKeyIdentifier createAuthorityKeyIdentifier(SubjectPublicKeyInfo publicKeyInfo)
    {
        return new AuthorityKeyIdentifier(calculateIdentifier(publicKeyInfo));
    }

    public SubjectKeyIdentifier createSubjectKeyIdentifier(SubjectPublicKeyInfo publicKeyInfo)
    {
        return new SubjectKeyIdentifier(calculateIdentifier(publicKeyInfo));
    }

    public SubjectKeyIdentifier createTruncatedSubjectKeyIdentifier(SubjectPublicKeyInfo publicKeyInfo)
    {
        byte digest[] = calculateIdentifier(publicKeyInfo);
        byte id[] = new byte[8];
        System.arraycopy(digest, digest.length - 8, id, 0, id.length);
        id[0] &= 0xf;
        id[0] |= 0x40;
        return new SubjectKeyIdentifier(id);
    }

    private byte[] calculateIdentifier(SubjectPublicKeyInfo publicKeyInfo)
    {
        byte bytes[] = publicKeyInfo.getPublicKeyData().getBytes();
        OutputStream cOut = calculator.getOutputStream();
        try
        {
            cOut.write(bytes);
            cOut.close();
        }
        catch(IOException e)
        {
            throw new CertRuntimeException((new StringBuilder()).append("unable to calculate identifier: ").append(e.getMessage()).toString(), e);
        }
        return calculator.getDigest();
    }

    private DigestCalculator calculator;
}
