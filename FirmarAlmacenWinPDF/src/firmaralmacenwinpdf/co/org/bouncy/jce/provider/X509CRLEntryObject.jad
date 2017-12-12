// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CRLEntryObject.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.util.ASN1Dump;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.*;
import javax.security.auth.x500.X500Principal;

public class X509CRLEntryObject extends X509CRLEntry
{

    public X509CRLEntryObject(co.org.bouncy.asn1.x509.TBSCertList.CRLEntry c)
    {
        this.c = c;
        certificateIssuer = null;
    }

    public X509CRLEntryObject(co.org.bouncy.asn1.x509.TBSCertList.CRLEntry c, boolean isIndirect, X500Name previousCertificateIssuer)
    {
        this.c = c;
        certificateIssuer = loadCertificateIssuer(isIndirect, previousCertificateIssuer);
    }

    public boolean hasUnsupportedCriticalExtension()
    {
        Set extns = getCriticalExtensionOIDs();
        return extns != null && !extns.isEmpty();
    }

    private X500Name loadCertificateIssuer(boolean isIndirect, X500Name previousCertificateIssuer)
    {
        Extension ext;
        if(!isIndirect)
            return null;
        ext = getExtension(Extension.certificateIssuer);
        if(ext == null)
            return previousCertificateIssuer;
        GeneralName names[];
        int i;
        names = GeneralNames.getInstance(ext.getParsedValue()).getNames();
        i = 0;
_L1:
        if(i >= names.length)
            break MISSING_BLOCK_LABEL_73;
        if(names[i].getTagNo() == 4)
            return X500Name.getInstance(names[i].getName());
        i++;
          goto _L1
        return null;
        Exception e;
        e;
        return null;
    }

    public X500Principal getCertificateIssuer()
    {
        if(certificateIssuer == null)
            return null;
        try
        {
            return new X500Principal(certificateIssuer.getEncoded());
        }
        catch(IOException e)
        {
            return null;
        }
    }

    private Set getExtensionOIDs(boolean critical)
    {
        Extensions extensions = c.getExtensions();
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
        } else
        {
            return null;
        }
    }

    public Set getCriticalExtensionOIDs()
    {
        return getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs()
    {
        return getExtensionOIDs(false);
    }

    private Extension getExtension(ASN1ObjectIdentifier oid)
    {
        Extensions exts = c.getExtensions();
        if(exts != null)
            return exts.getExtension(oid);
        else
            return null;
    }

    public byte[] getExtensionValue(String oid)
    {
        Extension ext = getExtension(new ASN1ObjectIdentifier(oid));
        if(ext != null)
            try
            {
                return ext.getExtnValue().getEncoded();
            }
            catch(Exception e)
            {
                throw new RuntimeException((new StringBuilder()).append("error encoding ").append(e.toString()).toString());
            }
        else
            return null;
    }

    public int hashCode()
    {
        if(!isHashValueSet)
        {
            hashValue = super.hashCode();
            isHashValueSet = true;
        }
        return hashValue;
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

    public BigInteger getSerialNumber()
    {
        return c.getUserCertificate().getValue();
    }

    public Date getRevocationDate()
    {
        return c.getRevocationDate().getDate();
    }

    public boolean hasExtensions()
    {
        return c.getExtensions() != null;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("      userCertificate: ").append(getSerialNumber()).append(nl);
        buf.append("       revocationDate: ").append(getRevocationDate()).append(nl);
        buf.append("       certificateIssuer: ").append(getCertificateIssuer()).append(nl);
        Extensions extensions = c.getExtensions();
        if(extensions != null)
        {
            Enumeration e = extensions.oids();
            if(e.hasMoreElements())
            {
                buf.append("   crlEntryExtensions:").append(nl);
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
                            if(oid.equals(X509Extension.reasonCode))
                                buf.append(CRLReason.getInstance(ASN1Enumerated.getInstance(dIn.readObject()))).append(nl);
                            else
                            if(oid.equals(X509Extension.certificateIssuer))
                            {
                                buf.append("Certificate issuer: ").append(GeneralNames.getInstance(dIn.readObject())).append(nl);
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
        }
        return buf.toString();
    }

    private co.org.bouncy.asn1.x509.TBSCertList.CRLEntry c;
    private X500Name certificateIssuer;
    private int hashValue;
    private boolean isHashValueSet;
}
