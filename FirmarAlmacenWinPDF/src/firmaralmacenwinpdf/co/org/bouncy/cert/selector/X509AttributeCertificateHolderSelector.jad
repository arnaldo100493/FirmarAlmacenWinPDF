// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509AttributeCertificateHolderSelector.java

package co.org.bouncy.cert.selector;

import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.*;
import co.org.bouncy.util.Selector;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

public class X509AttributeCertificateHolderSelector
    implements Selector
{

    X509AttributeCertificateHolderSelector(AttributeCertificateHolder holder, AttributeCertificateIssuer issuer, BigInteger serialNumber, Date attributeCertificateValid, X509AttributeCertificateHolder attributeCert, Collection targetNames, Collection targetGroups)
    {
        this.holder = holder;
        this.issuer = issuer;
        this.serialNumber = serialNumber;
        this.attributeCertificateValid = attributeCertificateValid;
        this.attributeCert = attributeCert;
        this.targetNames = targetNames;
        this.targetGroups = targetGroups;
    }

    public boolean match(Object obj)
    {
        if(!(obj instanceof X509AttributeCertificateHolder))
            return false;
        X509AttributeCertificateHolder attrCert = (X509AttributeCertificateHolder)obj;
        if(attributeCert != null && !attributeCert.equals(attrCert))
            return false;
        if(serialNumber != null && !attrCert.getSerialNumber().equals(serialNumber))
            return false;
        if(holder != null && !attrCert.getHolder().equals(holder))
            return false;
        if(issuer != null && !attrCert.getIssuer().equals(issuer))
            return false;
        if(attributeCertificateValid != null && !attrCert.isValidOn(attributeCertificateValid))
            return false;
        if(!targetNames.isEmpty() || !targetGroups.isEmpty())
        {
            Extension targetInfoExt = attrCert.getExtension(Extension.targetInformation);
            if(targetInfoExt != null)
            {
                TargetInformation targetinfo;
                try
                {
                    targetinfo = TargetInformation.getInstance(targetInfoExt.getParsedValue());
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
        X509AttributeCertificateHolderSelector sel = new X509AttributeCertificateHolderSelector(holder, issuer, serialNumber, attributeCertificateValid, attributeCert, targetNames, targetGroups);
        return sel;
    }

    public X509AttributeCertificateHolder getAttributeCert()
    {
        return attributeCert;
    }

    public Date getAttributeCertificateValid()
    {
        if(attributeCertificateValid != null)
            return new Date(attributeCertificateValid.getTime());
        else
            return null;
    }

    public AttributeCertificateHolder getHolder()
    {
        return holder;
    }

    public AttributeCertificateIssuer getIssuer()
    {
        return issuer;
    }

    public BigInteger getSerialNumber()
    {
        return serialNumber;
    }

    public Collection getTargetNames()
    {
        return targetNames;
    }

    public Collection getTargetGroups()
    {
        return targetGroups;
    }

    private final AttributeCertificateHolder holder;
    private final AttributeCertificateIssuer issuer;
    private final BigInteger serialNumber;
    private final Date attributeCertificateValid;
    private final X509AttributeCertificateHolder attributeCert;
    private final Collection targetNames;
    private final Collection targetGroups;
}
