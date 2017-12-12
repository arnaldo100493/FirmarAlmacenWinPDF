// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509v2AttributeCertificateBuilder.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.operator.ContentSigner;
import java.math.BigInteger;
import java.util.Date;

// Referenced classes of package co.org.bouncy.cert:
//            CertIOException, AttributeCertificateHolder, AttributeCertificateIssuer, CertUtils, 
//            X509AttributeCertificateHolder

public class X509v2AttributeCertificateBuilder
{

    public X509v2AttributeCertificateBuilder(AttributeCertificateHolder holder, AttributeCertificateIssuer issuer, BigInteger serialNumber, Date notBefore, Date notAfter)
    {
        acInfoGen = new V2AttributeCertificateInfoGenerator();
        extGenerator = new ExtensionsGenerator();
        acInfoGen.setHolder(holder.holder);
        acInfoGen.setIssuer(AttCertIssuer.getInstance(issuer.form));
        acInfoGen.setSerialNumber(new ASN1Integer(serialNumber));
        acInfoGen.setStartDate(new ASN1GeneralizedTime(notBefore));
        acInfoGen.setEndDate(new ASN1GeneralizedTime(notAfter));
    }

    public X509v2AttributeCertificateBuilder addAttribute(ASN1ObjectIdentifier attrType, ASN1Encodable attrValue)
    {
        acInfoGen.addAttribute(new Attribute(attrType, new DERSet(attrValue)));
        return this;
    }

    public X509v2AttributeCertificateBuilder addAttribute(ASN1ObjectIdentifier attrType, ASN1Encodable attrValues[])
    {
        acInfoGen.addAttribute(new Attribute(attrType, new DERSet(attrValues)));
        return this;
    }

    public void setIssuerUniqueId(boolean iui[])
    {
        acInfoGen.setIssuerUniqueID(CertUtils.booleanToBitString(iui));
    }

    public X509v2AttributeCertificateBuilder addExtension(ASN1ObjectIdentifier oid, boolean isCritical, ASN1Encodable value)
        throws CertIOException
    {
        CertUtils.addExtension(extGenerator, oid, isCritical, value);
        return this;
    }

    public X509AttributeCertificateHolder build(ContentSigner signer)
    {
        acInfoGen.setSignature(signer.getAlgorithmIdentifier());
        if(!extGenerator.isEmpty())
            acInfoGen.setExtensions(extGenerator.generate());
        return CertUtils.generateFullAttrCert(signer, acInfoGen.generateAttributeCertificateInfo());
    }

    private V2AttributeCertificateInfoGenerator acInfoGen;
    private ExtensionsGenerator extGenerator;
}
