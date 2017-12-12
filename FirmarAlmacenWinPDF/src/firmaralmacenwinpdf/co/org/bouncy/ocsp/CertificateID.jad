// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateID.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.CertID;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jce.PrincipalUtil;
import co.org.bouncy.jce.X509Principal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

// Referenced classes of package co.org.bouncy.ocsp:
//            OCSPException, OCSPUtil

public class CertificateID
{

    public CertificateID(CertID id)
    {
        if(id == null)
        {
            throw new IllegalArgumentException("'id' cannot be null");
        } else
        {
            this.id = id;
            return;
        }
    }

    public CertificateID(String hashAlgorithm, X509Certificate issuerCert, BigInteger number, String provider)
        throws OCSPException
    {
        AlgorithmIdentifier hashAlg = new AlgorithmIdentifier(new DERObjectIdentifier(hashAlgorithm), DERNull.INSTANCE);
        id = createCertID(hashAlg, issuerCert, new ASN1Integer(number), provider);
    }

    public CertificateID(String hashAlgorithm, X509Certificate issuerCert, BigInteger number)
        throws OCSPException
    {
        this(hashAlgorithm, issuerCert, number, "BC");
    }

    public String getHashAlgOID()
    {
        return id.getHashAlgorithm().getObjectId().getId();
    }

    public byte[] getIssuerNameHash()
    {
        return id.getIssuerNameHash().getOctets();
    }

    public byte[] getIssuerKeyHash()
    {
        return id.getIssuerKeyHash().getOctets();
    }

    public BigInteger getSerialNumber()
    {
        return id.getSerialNumber().getValue();
    }

    public boolean matchesIssuer(X509Certificate issuerCert, String provider)
        throws OCSPException
    {
        return createCertID(id.getHashAlgorithm(), issuerCert, id.getSerialNumber(), provider).equals(id);
    }

    public CertID toASN1Object()
    {
        return id;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof CertificateID))
        {
            return false;
        } else
        {
            CertificateID obj = (CertificateID)o;
            return id.toASN1Primitive().equals(obj.id.toASN1Primitive());
        }
    }

    public int hashCode()
    {
        return id.toASN1Primitive().hashCode();
    }

    public static CertificateID deriveCertificateID(CertificateID original, BigInteger newSerialNumber)
    {
        return new CertificateID(new CertID(original.id.getHashAlgorithm(), original.id.getIssuerNameHash(), original.id.getIssuerKeyHash(), new ASN1Integer(newSerialNumber)));
    }

    private static CertID createCertID(AlgorithmIdentifier hashAlg, X509Certificate issuerCert, ASN1Integer serialNumber, String provider)
        throws OCSPException
    {
        try
        {
            MessageDigest digest = OCSPUtil.createDigestInstance(hashAlg.getAlgorithm().getId(), provider);
            X509Principal issuerName = PrincipalUtil.getSubjectX509Principal(issuerCert);
            digest.update(issuerName.getEncoded());
            ASN1OctetString issuerNameHash = new DEROctetString(digest.digest());
            PublicKey issuerKey = issuerCert.getPublicKey();
            ASN1InputStream aIn = new ASN1InputStream(issuerKey.getEncoded());
            SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(aIn.readObject());
            digest.update(info.getPublicKeyData().getBytes());
            ASN1OctetString issuerKeyHash = new DEROctetString(digest.digest());
            return new CertID(hashAlg, issuerNameHash, issuerKeyHash, serialNumber);
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("problem creating ID: ").append(e).toString(), e);
        }
    }

    public static final String HASH_SHA1 = "1.3.14.3.2.26";
    private final CertID id;
}
