// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPKIArchiveControlBuilder.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.cert.crmf.PKIArchiveControlBuilder;
import java.security.PrivateKey;
import javax.security.auth.x500.X500Principal;

public class JcaPKIArchiveControlBuilder extends PKIArchiveControlBuilder
{

    public JcaPKIArchiveControlBuilder(PrivateKey privateKey, X500Name name)
    {
        this(privateKey, new GeneralName(name));
    }

    public JcaPKIArchiveControlBuilder(PrivateKey privateKey, X500Principal name)
    {
        this(privateKey, X500Name.getInstance(name.getEncoded()));
    }

    public JcaPKIArchiveControlBuilder(PrivateKey privateKey, GeneralName generalName)
    {
        super(PrivateKeyInfo.getInstance(privateKey.getEncoded()), generalName);
    }
}
