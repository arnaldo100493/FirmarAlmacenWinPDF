// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttributeCertificateHolder.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.jce.PrincipalUtil;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Selector;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.x509_:
//            X509Util

/**
 * @deprecated Class AttributeCertificateHolder is deprecated
 */

public class AttributeCertificateHolder
    implements CertSelector, Selector
{

    AttributeCertificateHolder(ASN1Sequence seq)
    {
        holder = Holder.getInstance(seq);
    }

    public AttributeCertificateHolder(X509Principal issuerName, BigInteger serialNumber)
    {
        holder = new Holder(new IssuerSerial(GeneralNames.getInstance(new DERSequence(new GeneralName(issuerName))), new ASN1Integer(serialNumber)));
    }

    public AttributeCertificateHolder(X500Principal issuerName, BigInteger serialNumber)
    {
        this(X509Util.convertPrincipal(issuerName), serialNumber);
    }

    public AttributeCertificateHolder(X509Certificate cert)
        throws CertificateParsingException
    {
        X509Principal name;
        try
        {
            name = PrincipalUtil.getIssuerX509Principal(cert);
        }
        catch(Exception e)
        {
            throw new CertificateParsingException(e.getMessage());
        }
        holder = new Holder(new IssuerSerial(generateGeneralNames(name), new ASN1Integer(cert.getSerialNumber())));
    }

    public AttributeCertificateHolder(X509Principal principal)
    {
        holder = new Holder(generateGeneralNames(principal));
    }

    public AttributeCertificateHolder(X500Principal principal)
    {
        this(X509Util.convertPrincipal(principal));
    }

    public AttributeCertificateHolder(int digestedObjectType, String digestAlgorithm, String otherObjectTypeID, byte objectDigest[])
    {
        holder = new Holder(new ObjectDigestInfo(digestedObjectType, new ASN1ObjectIdentifier(otherObjectTypeID), new AlgorithmIdentifier(digestAlgorithm), Arrays.clone(objectDigest)));
    }

    public int getDigestedObjectType()
    {
        if(holder.getObjectDigestInfo() != null)
            return holder.getObjectDigestInfo().getDigestedObjectType().getValue().intValue();
        else
            return -1;
    }

    public String getDigestAlgorithm()
    {
        if(holder.getObjectDigestInfo() != null)
            return holder.getObjectDigestInfo().getDigestAlgorithm().getObjectId().getId();
        else
            return null;
    }

    public byte[] getObjectDigest()
    {
        if(holder.getObjectDigestInfo() != null)
            return holder.getObjectDigestInfo().getObjectDigest().getBytes();
        else
            return null;
    }

    public String getOtherObjectTypeID()
    {
        if(holder.getObjectDigestInfo() != null)
            holder.getObjectDigestInfo().getOtherObjectTypeID().getId();
        return null;
    }

    private GeneralNames generateGeneralNames(X509Principal principal)
    {
        return GeneralNames.getInstance(new DERSequence(new GeneralName(principal)));
    }

    private boolean matchesDN(X509Principal subject, GeneralNames targets)
    {
        GeneralName names[] = targets.getNames();
        for(int i = 0; i != names.length; i++)
        {
            GeneralName gn = names[i];
            if(gn.getTagNo() != 4)
                continue;
            try
            {
                if((new X509Principal(gn.getName().toASN1Primitive().getEncoded())).equals(subject))
                    return true;
            }
            catch(IOException e) { }
        }

        return false;
    }

    private Object[] getNames(GeneralName names[])
    {
        List l = new ArrayList(names.length);
        for(int i = 0; i != names.length; i++)
        {
            if(names[i].getTagNo() != 4)
                continue;
            try
            {
                l.add(new X500Principal(names[i].getName().toASN1Primitive().getEncoded()));
            }
            catch(IOException e)
            {
                throw new RuntimeException("badly formed Name object");
            }
        }

        return l.toArray(new Object[l.size()]);
    }

    private Principal[] getPrincipals(GeneralNames names)
    {
        Object p[] = getNames(names.getNames());
        List l = new ArrayList();
        for(int i = 0; i != p.length; i++)
            if(p[i] instanceof Principal)
                l.add(p[i]);

        return (Principal[])(Principal[])l.toArray(new Principal[l.size()]);
    }

    public Principal[] getEntityNames()
    {
        if(holder.getEntityName() != null)
            return getPrincipals(holder.getEntityName());
        else
            return null;
    }

    public Principal[] getIssuer()
    {
        if(holder.getBaseCertificateID() != null)
            return getPrincipals(holder.getBaseCertificateID().getIssuer());
        else
            return null;
    }

    public BigInteger getSerialNumber()
    {
        if(holder.getBaseCertificateID() != null)
            return holder.getBaseCertificateID().getSerial().getValue();
        else
            return null;
    }

    public Object clone()
    {
        return new AttributeCertificateHolder((ASN1Sequence)holder.toASN1Object());
    }

    public boolean match(Certificate cert)
    {
        if(!(cert instanceof X509Certificate))
            return false;
        X509Certificate x509Cert = (X509Certificate)cert;
        MessageDigest md;
        Exception e;
        try
        {
            if(holder.getBaseCertificateID() != null)
                return holder.getBaseCertificateID().getSerial().getValue().equals(x509Cert.getSerialNumber()) && matchesDN(PrincipalUtil.getIssuerX509Principal(x509Cert), holder.getBaseCertificateID().getIssuer());
        }
        catch(CertificateEncodingException e)
        {
            return false;
        }
        if(holder.getEntityName() != null && matchesDN(PrincipalUtil.getSubjectX509Principal(x509Cert), holder.getEntityName()))
            return true;
        if(holder.getObjectDigestInfo() == null)
            break MISSING_BLOCK_LABEL_210;
        md = null;
        try
        {
            md = MessageDigest.getInstance(getDigestAlgorithm(), "BC");
        }
        // Misplaced declaration of an exception variable
        catch(Exception e)
        {
            return false;
        }
        switch(getDigestedObjectType())
        {
        case 0: // '\0'
            md.update(cert.getPublicKey().getEncoded());
            break;

        case 1: // '\001'
            md.update(cert.getEncoded());
            break;
        }
        if(!Arrays.areEqual(md.digest(), getObjectDigest()))
            return false;
        return false;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof AttributeCertificateHolder))
        {
            return false;
        } else
        {
            AttributeCertificateHolder other = (AttributeCertificateHolder)obj;
            return holder.equals(other.holder);
        }
    }

    public int hashCode()
    {
        return holder.hashCode();
    }

    public boolean match(Object obj)
    {
        if(!(obj instanceof X509Certificate))
            return false;
        else
            return match((Certificate)obj);
    }

    final Holder holder;
}
