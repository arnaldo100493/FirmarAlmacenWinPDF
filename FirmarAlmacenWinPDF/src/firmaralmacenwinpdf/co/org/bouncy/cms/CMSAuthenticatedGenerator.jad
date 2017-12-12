// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthenticatedGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package co.org.bouncy.cms:
//            CMSEnvelopedGenerator, CMSAttributeTableGenerator

public class CMSAuthenticatedGenerator extends CMSEnvelopedGenerator
{

    public CMSAuthenticatedGenerator()
    {
    }

    public CMSAuthenticatedGenerator(SecureRandom rand)
    {
        super(rand);
    }

    public void setAuthenticatedAttributeGenerator(CMSAttributeTableGenerator authGen)
    {
        this.authGen = authGen;
    }

    public void setUnauthenticatedAttributeGenerator(CMSAttributeTableGenerator unauthGen)
    {
        this.unauthGen = unauthGen;
    }

    protected Map getBaseParameters(ASN1ObjectIdentifier contentType, AlgorithmIdentifier digAlgId, byte hash[])
    {
        Map param = new HashMap();
        param.put("contentType", contentType);
        param.put("digestAlgID", digAlgId);
        param.put("digest", hash.clone());
        return param;
    }

    protected CMSAttributeTableGenerator authGen;
    protected CMSAttributeTableGenerator unauthGen;
}
