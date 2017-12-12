// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CRLObject.java

package co.org.bouncy.jcajce.provider.asymmetric.x509;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.util.ASN1Dump;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.CRLDistPoint;
import co.org.bouncy.asn1.x509.CRLNumber;
import co.org.bouncy.asn1.x509.CertificateList;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.GeneralNames;
import co.org.bouncy.asn1.x509.IssuingDistributionPoint;
import co.org.bouncy.asn1.x509.TBSCertList;
import co.org.bouncy.asn1.x509.Time;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.jce.provider.RFC3280CertPathUtilities;
import co.org.bouncy.util.encoders.Hex;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.x509:
//            ExtCRLException, X509CRLEntryObject, X509SignatureUtil

class X509CRLObject extends X509CRL
{

    static boolean isIndirectCRL(X509CRL crl)
        throws CRLException
    {
        try
        {
            byte idp[] = crl.getExtensionValue(Extension.issuingDistributionPoint.getId());
            return idp != null && IssuingDistributionPoint.getInstance(ASN1OctetString.getInstance(idp).getOctets()).isIndirectCRL();
        }
        catch(Exception e)
        {
            throw new ExtCRLException("Exception reading IssuingDistributionPoint", e);
        }
    }

    public X509CRLObject(CertificateList c)
        throws CRLException
    {
        this.c = c;
        try
        {
            sigAlgName = X509SignatureUtil.getSignatureName(c.getSignatureAlgorithm());
            if(c.getSignatureAlgorithm().getParameters() != null)
                sigAlgParams = c.getSignatureAlgorithm().getParameters().toASN1Primitive().getEncoded("DER");
            else
                sigAlgParams = null;
            isIndirect = isIndirectCRL(this);
        }
        catch(Exception e)
        {
            throw new CRLException((new StringBuilder()).append("CRL contents invalid: ").append(e).toString());
        }
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        if(extns == null)
        {
            return false;
        } else
        {
            extns.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
            extns.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
            return !extns.isEmpty();
        }
    }

    private Set getExtensionOIDs(boolean critical)
    {
        if(getVersion() == 2)
        {
            Extensions extensions = c.getTBSCertList().getExtensions();
            if(extensions != null)
            {
                Set set = new HashSet();
                Enumeration e = extensions.oids();
                do
                {
                    if(!e.hasMoreElements())
                        break;
                    ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                    Extension ext = extensions.getExtension(oid);
                    if(critical == ext.isCritical())
                        set.add(oid.getId());
                } while(true);
                return set;
            }
        }
        return null;
    }

    public Set getCriticalExtensionOIDs()
    {
        return getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return getExtensionOIDs(false);
    }

    public byte[] getExtensionValue(String oid)
    {
        Extensions exts = c.getTBSCertList().getExtensions();
        if(exts != null)
        {
            Extension ext = exts.getExtension(new ASN1ObjectIdentifier(oid));
            if(ext != null)
                try
                {
                    return ext.getExtnValue().getEncoded();
                }
                catch(Exception e)
                {
                    throw new IllegalStateException((new StringBuilder()).append("error parsing ").append(e.toString()).toString());
                }
        }
        return null;
    }

    public byte[] getEncoded()
        throws CRLException
    {
        try
        {
            return c.getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new CRLException(e.toString());
        }
    }

    public void verify(PublicKey key)
        throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
    {
        verify(key, "BC");
    }

    public void verify(PublicKey key, String sigProvider)
        throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
    {
        if(!c.getSignatureAlgorithm().equals(c.getTBSCertList().getSignature()))
            throw new CRLException("Signature algorithm on CertificateList does not match TBSCertList.");
        Signature sig;
        if(sigProvider != null)
            sig = Signature.getInstance(getSigAlgName(), sigProvider);
        else
            sig = Signature.getInstance(getSigAlgName());
        sig.initVerify(key);
        sig.update(getTBSCertList());
        if(!sig.verify(getSignature()))
            throw new SignatureException("CRL does not verify with supplied public key.");
        else
            return;
    }

    public int getVersion()
    {
        return c.getVersionNumber();
    }

    public Principal getIssuerDN()
    {
        return new X509Principal(X500Name.getInstance(c.getIssuer().toASN1Primitive()));
    }

    public X500Principal getIssuerX500Principal()
    {
        try
        {
            return new X500Principal(c.getIssuer().getEncoded());
        }
        catch(IOException e)
        {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getThisUpdate()
    {
        return c.getThisUpdate().getDate();
    }

    public Date getNextUpdate()
    {
        if(c.getNextUpdate() != null)
            return c.getNextUpdate().getDate();
        else
            return null;
    }

    private Set loadCRLEntries()
    {
        Set entrySet = new HashSet();
        Enumeration certs = c.getRevokedCertificateEnumeration();
        X500Name previousCertificateIssuer = null;
        do
        {
            if(!certs.hasMoreElements())
                break;
            co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entry = (co.org.bouncy.asn1.x509.TBSCertList.CRLEntry)certs.nextElement();
            X509CRLEntryObject crlEntry = new X509CRLEntryObject(entry, isIndirect, previousCertificateIssuer);
            entrySet.add(crlEntry);
            if(isIndirect && entry.hasExtensions())
            {
                Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
                if(currentCaName != null)
                    previousCertificateIssuer = X500Name.getInstance(GeneralNames.getInstance(currentCaName.getParsedValue()).getNames()[0].getName());
            }
        } while(true);
        return entrySet;
    }

    public X509CRLEntry getRevokedCertificate(BigInteger serialNumber)
    {
        Enumeration certs = c.getRevokedCertificateEnumeration();
        X500Name previousCertificateIssuer = null;
        do
        {
            if(!certs.hasMoreElements())
                break;
            co.org.bouncy.asn1.x509.TBSCertList.CRLEntry entry = (co.org.bouncy.asn1.x509.TBSCertList.CRLEntry)certs.nextElement();
            if(serialNumber.equals(entry.getUserCertificate().getValue()))
                return new X509CRLEntryObject(entry, isIndirect, previousCertificateIssuer);
            if(isIndirect && entry.hasExtensions())
            {
                Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
                if(currentCaName != null)
                    previousCertificateIssuer = X500Name.getInstance(GeneralNames.getInstance(currentCaName.getParsedValue()).getNames()[0].getName());
            }
        } while(true);
        return null;
    }

    public Set getRevokedCertificates()
    {
        Set entrySet = loadCRLEntries();
        if(!entrySet.isEmpty())
            return Collections.unmodifiableSet(entrySet);
        else
            return null;
    }

    public byte[] getTBSCertList()
        throws CRLException
    {
        try
        {
            return c.getTBSCertList().getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new CRLException(e.toString());
        }
    }

    public byte[] getSignature()
    {
        return c.getSignature().getBytes();
    }

    public String getSigAlgName()
    {
        return sigAlgName;
    }

    public String getSigAlgOID()
    {
        return c.getSignatureAlgorithm().getAlgorithm().getId();
    }

    public byte[] getSigAlgParams()
    {
        if(sigAlgParams != null)
        {
            byte tmp[] = new byte[sigAlgParams.length];
            System.arraycopy(sigAlgParams, 0, tmp, 0, tmp.length);
            return tmp;
        } else
        {
            return null;
        }
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("              Version: ").append(getVersion()).append(nl);
        buf.append("             IssuerDN: ").append(getIssuerDN()).append(nl);
        buf.append("          This update: ").append(getThisUpdate()).append(nl);
        buf.append("          Next update: ").append(getNextUpdate()).append(nl);
        buf.append("  Signature Algorithm: ").append(getSigAlgName()).append(nl);
        byte sig[] = getSignature();
        buf.append("            Signature: ").append(new String(Hex.encode(sig, 0, 20))).append(nl);
        for(int i = 20; i < sig.length; i += 20)
            if(i < sig.length - 20)
                buf.append("                       ").append(new String(Hex.encode(sig, i, 20))).append(nl);
            else
                buf.append("                       ").append(new String(Hex.encode(sig, i, sig.length - i))).append(nl);

        Extensions extensions = c.getTBSCertList().getExtensions();
        if(extensions != null)
        {
            Enumeration e = extensions.oids();
            if(e.hasMoreElements())
                buf.append("           Extensions: ").append(nl);
            while(e.hasMoreElements()) 
            {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                Extension ext = extensions.getExtension(oid);
                if(ext.getExtnValue() != null)
                {
                    byte octs[] = ext.getExtnValue().getOctets();
                    ASN1InputStream dIn = new ASN1InputStream(octs);
                    buf.append("                       critical(").append(ext.isCritical()).append(") ");
                    try
                    {
                        if(oid.equals(Extension.cRLNumber))
                            buf.append(new CRLNumber(ASN1Integer.getInstance(dIn.readObject()).getPositiveValue())).append(nl);
                        else
                        if(oid.equals(Extension.deltaCRLIndicator))
                            buf.append((new StringBuilder()).append("Base CRL: ").append(new CRLNumber(ASN1Integer.getInstance(dIn.readObject()).getPositiveValue())).toString()).append(nl);
                        else
                        if(oid.equals(Extension.issuingDistributionPoint))
                            buf.append(IssuingDistributionPoint.getInstance(dIn.readObject())).append(nl);
                        else
                        if(oid.equals(Extension.cRLDistributionPoints))
                            buf.append(CRLDistPoint.getInstance(dIn.readObject())).append(nl);
                        else
                        if(oid.equals(Extension.freshestCRL))
                        {
                            buf.append(CRLDistPoint.getInstance(dIn.readObject())).append(nl);
                        } else
                        {
                            buf.append(oid.getId());
                            buf.append(" value = ").append(ASN1Dump.dumpAsString(dIn.readObject())).append(nl);
                        }
                    }
                    catch(Exception ex)
                    {
                        buf.append(oid.getId());
                        buf.append(" value = ").append("*****").append(nl);
                    }
                } else
                {
                    buf.append(nl);
                }
            }
        }
        Set set = getRevokedCertificates();
        if(set != null)
        {
            for(Iterator it = set.iterator(); it.hasNext(); buf.append(nl))
                buf.append(it.next());

        }
        return buf.toString();
    }

    public boolean isRevoked(Certificate cert)
    {
        if(!cert.getType().equals("X.509"))
            throw new RuntimeException("X.509 CRL used with non X.509 Cert");
        co.org.bouncy.asn1.x509.TBSCertList.CRLEntry certs[] = c.getRevokedCertificates();
        X500Name caName = c.getIssuer();
        if(certs != null)
        {
            BigInteger serial = ((X509Certificate)cert).getSerialNumber();
            for(int i = 0; i < certs.length; i++)
            {
                if(isIndirect && certs[i].hasExtensions())
                {
                    Extension currentCaName = certs[i].getExtensions().getExtension(Extension.certificateIssuer);
                    if(currentCaName != null)
                        caName = X500Name.getInstance(GeneralNames.getInstance(currentCaName.getParsedValue()).getNames()[0].getName());
                }
                if(certs[i].getUserCertificate().getValue().equals(serial))
                {
                    X500Name issuer;
                    if(cert instanceof X509Certificate)
                        issuer = X500Name.getInstance(((X509Certificate)cert).getIssuerX500Principal().getEncoded());
                    else
                        try
                        {
                            issuer = co.org.bouncy.asn1.x509.Certificate.getInstance(cert.getEncoded()).getIssuer();
                        }
                        catch(CertificateEncodingException e)
                        {
                            throw new RuntimeException("Cannot process certificate");
                        }
                    return caName.equals(issuer);
                }
            }

        }
        return false;
    }

    private CertificateList c;
    private String sigAlgName;
    private byte sigAlgParams[];
    private boolean isIndirect;
}
