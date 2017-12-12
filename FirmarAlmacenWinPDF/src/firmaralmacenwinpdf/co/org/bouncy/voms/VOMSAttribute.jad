// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VOMSAttribute.java

package co.org.bouncy.voms;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DERIA5String;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.x509_.X509Attribute;
import co.org.bouncy.x509_.X509AttributeCertificate;
import java.util.ArrayList;
import java.util.List;

public class VOMSAttribute
{
    public class FQAN
    {

        public String getFQAN()
        {
            if(fqan != null)
            {
                return fqan;
            } else
            {
                fqan = (new StringBuilder()).append(group).append("/Role=").append(role == null ? "" : role).append(capability == null ? "" : (new StringBuilder()).append("/Capability=").append(capability).toString()).toString();
                return fqan;
            }
        }

        protected void split()
        {
            int len = fqan.length();
            int i = fqan.indexOf("/Role=");
            if(i < 0)
            {
                return;
            } else
            {
                group = fqan.substring(0, i);
                int j = fqan.indexOf("/Capability=", i + 6);
                String s = j >= 0 ? fqan.substring(i + 6, j) : fqan.substring(i + 6);
                role = s.length() != 0 ? s : null;
                s = j >= 0 ? fqan.substring(j + 12) : null;
                capability = s != null && s.length() != 0 ? s : null;
                return;
            }
        }

        public String getGroup()
        {
            if(group == null && fqan != null)
                split();
            return group;
        }

        public String getRole()
        {
            if(group == null && fqan != null)
                split();
            return role;
        }

        public String getCapability()
        {
            if(group == null && fqan != null)
                split();
            return capability;
        }

        public String toString()
        {
            return getFQAN();
        }

        String fqan;
        String group;
        String role;
        String capability;
        final VOMSAttribute this$0;

        public FQAN(String fqan)
        {
            this$0 = VOMSAttribute.this;
            super();
            this.fqan = fqan;
        }

        public FQAN(String group, String role, String capability)
        {
            this$0 = VOMSAttribute.this;
            super();
            this.group = group;
            this.role = role;
            this.capability = capability;
        }
    }


    public VOMSAttribute(X509AttributeCertificate ac)
    {
        myStringList = new ArrayList();
        myFQANs = new ArrayList();
        if(ac == null)
            throw new IllegalArgumentException("VOMSAttribute: AttributeCertificate is NULL");
        myAC = ac;
        X509Attribute l[] = ac.getAttributes("1.3.6.1.4.1.8005.100.100.4");
        if(l == null)
            return;
        try
        {
            for(int i = 0; i != l.length; i++)
            {
                IetfAttrSyntax attr = IetfAttrSyntax.getInstance(l[i].getValues()[0]);
                String url = ((DERIA5String)attr.getPolicyAuthority().getNames()[0].getName()).getString();
                int idx = url.indexOf("://");
                if(idx < 0 || idx == url.length() - 1)
                    throw new IllegalArgumentException((new StringBuilder()).append("Bad encoding of VOMS policyAuthority : [").append(url).append("]").toString());
                myVo = url.substring(0, idx);
                myHostPort = url.substring(idx + 3);
                if(attr.getValueType() != 1)
                    throw new IllegalArgumentException((new StringBuilder()).append("VOMS attribute values are not encoded as octet strings, policyAuthority = ").append(url).toString());
                ASN1OctetString values[] = (ASN1OctetString[])(ASN1OctetString[])attr.getValues();
                for(int j = 0; j != values.length; j++)
                {
                    String fqan = new String(values[j].getOctets());
                    FQAN f = new FQAN(fqan);
                    if(!myStringList.contains(fqan) && fqan.startsWith((new StringBuilder()).append("/").append(myVo).append("/").toString()))
                    {
                        myStringList.add(fqan);
                        myFQANs.add(f);
                    }
                }

            }

        }
        catch(IllegalArgumentException ie)
        {
            throw ie;
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Badly encoded VOMS extension in AC issued by ").append(ac.getIssuer()).toString());
        }
    }

    public X509AttributeCertificate getAC()
    {
        return myAC;
    }

    public List getFullyQualifiedAttributes()
    {
        return myStringList;
    }

    public List getListOfFQAN()
    {
        return myFQANs;
    }

    public String getHostPort()
    {
        return myHostPort;
    }

    public String getVO()
    {
        return myVo;
    }

    public String toString()
    {
        return (new StringBuilder()).append("VO      :").append(myVo).append("\n").append("HostPort:").append(myHostPort).append("\n").append("FQANs   :").append(myFQANs).toString();
    }

    public static final String VOMS_ATTR_OID = "1.3.6.1.4.1.8005.100.100.4";
    private X509AttributeCertificate myAC;
    private String myHostPort;
    private String myVo;
    private List myStringList;
    private List myFQANs;
}
