// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509AttributeCertStoreSelector.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.util.Selector;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.*;

// Referenced classes of package co.org.bouncy.x509_:
//            X509AttributeCertificate, AttributeCertificateHolder, AttributeCertificateIssuer

/**
 * @deprecated Class X509AttributeCertStoreSelector is deprecated
 */

public class X509AttributeCertStoreSelector
    implements Selector
{

    public X509AttributeCertStoreSelector()
    {
        targetNames = new HashSet();
        targetGroups = new HashSet();
    }

    public boolean match(Object obj)
    {
        if(!(obj instanceof X509AttributeCertificate))
            return false;
        X509AttributeCertificate attrCert = (X509AttributeCertificate)obj;
        if(attributeCert != null && !attributeCert.equals(attrCert))
            return false;
        if(serialNumber != null && !attrCert.getSerialNumber().equals(serialNumber))
            return false;
        if(holder != null && !attrCert.getHolder().equals(holder))
            return false;
        if(issuer != null && !attrCert.getIssuer().equals(issuer))
            return false;
        if(attributeCertificateValid != null)
            try
            {
                attrCert.checkValidity(attributeCertificateValid);
            }
            catch(CertificateExpiredException e)
            {
                return false;
            }
            catch(CertificateNotYetValidException e)
            {
                return false;
            }
        if(!targetNames.isEmpty() || !targetGroups.isEmpty())
        {
            byte targetInfoExt[] = attrCert.getExtensionValue(X509Extensions.TargetInformation.getId());
            if(targetInfoExt != null)
            {
                TargetInformation targetinfo;
                try
                {
                    targetinfo = TargetInformation.getInstance((new ASN1InputStream(((DEROctetString)DEROctetString.fromByteArray(targetInfoExt)).getOctets())).readObject());
                }
                catch(IOException e)
                {
                    return false;
                }
                catch(IllegalArgumentException e)
                {
                    return false;
                }
                Targets targetss[] = targetinfo.getTargetsObjects();
                if(!targetNames.isEmpty())
                {
                    boolean found = false;
label0:
                    for(int i = 0; i < targetss.length; i++)
                    {
                        Targets t = targetss[i];
                        Target targets[] = t.getTargets();
                        int j = 0;
                        do
                        {
                            if(j >= targets.length)
                                continue label0;
                            if(targetNames.contains(GeneralName.getInstance(targets[j].getTargetName())))
                            {
                                found = true;
                                continue label0;
                            }
                            j++;
                        } while(true);
                    }

                    if(!found)
                        return false;
                }
                if(!targetGroups.isEmpty())
                {
                    boolean found = false;
label1:
                    for(int i = 0; i < targetss.length; i++)
                    {
                        Targets t = targetss[i];
                        Target targets[] = t.getTargets();
                        int j = 0;
                        do
                        {
                            if(j >= targets.length)
                                continue label1;
                            if(targetGroups.contains(GeneralName.getInstance(targets[j].getTargetGroup())))
                            {
                                found = true;
                                continue label1;
                            }
                            j++;
                        } while(true);
                    }

                    if(!found)
                        return false;
                }
            }
        }
        return true;
    }

    public Object clone()
    {
        X509AttributeCertStoreSelector sel = new X509AttributeCertStoreSelector();
        sel.attributeCert = attributeCert;
        sel.attributeCertificateValid = getAttributeCertificateValid();
        sel.holder = holder;
        sel.issuer = issuer;
        sel.serialNumber = serialNumber;
        sel.targetGroups = getTargetGroups();
        sel.targetNames = getTargetNames();
        return sel;
    }

    public X509AttributeCertificate getAttributeCert()
    {
        return attributeCert;
    }

    public void setAttributeCert(X509AttributeCertificate attributeCert)
    {
        this.attributeCert = attributeCert;
    }

    public Date getAttributeCertificateValid()
    {
        if(attributeCertificateValid != null)
            return new Date(attributeCertificateValid.getTime());
        else
            return null;
    }

    public void setAttributeCertificateValid(Date attributeCertificateValid)
    {
        if(attributeCertificateValid != null)
            this.attributeCertificateValid = new Date(attributeCertificateValid.getTime());
        else
            this.attributeCertificateValid = null;
    }

    public AttributeCertificateHolder getHolder()
    {
        return holder;
    }

    public void setHolder(AttributeCertificateHolder holder)
    {
        this.holder = holder;
    }

    public AttributeCertificateIssuer getIssuer()
    {
        return issuer;
    }

    public void setIssuer(AttributeCertificateIssuer issuer)
    {
        this.issuer = issuer;
    }

    public BigInteger getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void addTargetName(GeneralName name)
    {
        targetNames.add(name);
    }

    public void addTargetName(byte name[])
        throws IOException
    {
        addTargetName(GeneralName.getInstance(ASN1Primitive.fromByteArray(name)));
    }

    public void setTargetNames(Collection names)
        throws IOException
    {
        targetNames = extractGeneralNames(names);
    }

    public Collection getTargetNames()
    {
        return Collections.unmodifiableCollection(targetNames);
    }

    public void addTargetGroup(GeneralName group)
    {
        targetGroups.add(group);
    }

    public void addTargetGroup(byte name[])
        throws IOException
    {
        addTargetGroup(GeneralName.getInstance(ASN1Primitive.fromByteArray(name)));
    }

    public void setTargetGroups(Collection names)
        throws IOException
    {
        targetGroups = extractGeneralNames(names);
    }

    public Collection getTargetGroups()
    {
        return Collections.unmodifiableCollection(targetGroups);
    }

    private Set extractGeneralNames(Collection names)
        throws IOException
    {
        if(names == null || names.isEmpty())
            return new HashSet();
        Set temp = new HashSet();
        for(Iterator it = names.iterator(); it.hasNext();)
        {
            Object o = it.next();
            if(o instanceof GeneralName)
                temp.add(o);
            else
                temp.add(GeneralName.getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])o)));
        }

        return temp;
    }

    private AttributeCertificateHolder holder;
    private AttributeCertificateIssuer issuer;
    private BigInteger serialNumber;
    private Date attributeCertificateValid;
    private X509AttributeCertificate attributeCert;
    private Collection targetNames;
    private Collection targetGroups;
}
