// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaJceExtHelper.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.JcaJceHelper;
import co.org.bouncy.operator.SymmetricKeyUnwrapper;
import co.org.bouncy.operator.jcajce.JceAsymmetricKeyUnwrapper;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

public interface JcaJceExtHelper
    extends JcaJceHelper
{

    public abstract JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmidentifier, PrivateKey privatekey);

    public abstract SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmidentifier, SecretKey secretkey);
}
