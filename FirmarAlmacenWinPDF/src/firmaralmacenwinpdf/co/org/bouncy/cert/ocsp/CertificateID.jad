// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateID.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.CertID;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.*;
import java.io.OutputStream;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            OCSPException

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

    public CertificateID(DigestCalculator digestCalculator, X509CertificateHolder issuerCert, BigInteger number)
        throws OCSPException
    {
        id = createCertID(digestCalculator, issuerCert, new ASN1Integer(number));
    }

    public ASN1ObjectIdentifier getHashAlgOID()
    {
        return id.getHashAlgorithm().getAlgorithm();
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

    public boolean matchesIssuer(X509CertificateHolder issuerCert, DigestCalculatorProvider digCalcProvider)
        throws OCSPException
    {
        try
        {
            return createCertID(digCalcProvider.get(id.getHashAlgorithm()), issuerCert, id.getSerialNumber()).equals(id);
        }
        catch(OperatorCreationException e)
        {
            throw new OCSPException((new StringBuilder()).append("unable to create digest calculator: ").append(e.getMessage()).toString(), e);
        }
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

    private static CertID createCertID(DigestCalculator digCalc, X509CertificateHolder issuerCert, ASN1Integer serialNumber)
        throws OCSPException
    {
        try
        {
            OutputStream dgOut = digCalc.getOutputStream();
            dgOut.write(issuerCert.toASN1Structure().getSubject().getEncoded("DER"));
            dgOut.close();
            ASN1OctetString issuerNameHash = new DEROctetString(digCalc.getDigest());
            SubjectPublicKeyInfo info = issuerCert.getSubjectPublicKeyInfo();
            dgOut = digCalc.getOutputStream();
            dgOut.write(info.getPublicKeyData().getBytes());
            dgOut.close();
            ASN1OctetString issuerKeyHash = new DEROctetString(digCalc.getDigest());
            return new CertID(digCalc.getAlgorithmIdentifier(), issuerNameHash, issuerKeyHash, serialNumber);
        }
        catch(Exception e)
        {
            throw new OCSPException((new StringBuilder()).append("problem creating ID: ").append(e).toString(), e);
        }
    }

    public static final AlgorithmIdentifier HASH_SHA1;
    private final CertID id;

    static 
    {
        HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    }
}
