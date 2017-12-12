// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BouncyCastleProviderConfiguration.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jcajce.provider.asymmetric.util.EC5Util;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jcajce.provider.config.ProviderConfigurationPermission;
import co.org.bouncy.jce.spec.ECParameterSpec;
import java.math.BigInteger;
import java.security.Permission;
import javax.crypto.spec.DHParameterSpec;

class BouncyCastleProviderConfiguration
    implements ProviderConfiguration
{

    BouncyCastleProviderConfiguration()
    {
        ecThreadSpec = new ThreadLocal();
        dhThreadSpec = new ThreadLocal();
    }

    void setParameter(String parameterName, Object parameter)
    {
        SecurityManager securityManager = System.getSecurityManager();
        if(parameterName.equals("threadLocalEcImplicitlyCa"))
        {
            if(securityManager != null)
                securityManager.checkPermission(BC_EC_LOCAL_PERMISSION);
            ECParameterSpec curveSpec;
            if((parameter instanceof ECParameterSpec) || parameter == null)
                curveSpec = (ECParameterSpec)parameter;
            else
                curveSpec = EC5Util.convertSpec((java.security.spec.ECParameterSpec)parameter, false);
            if(curveSpec == null)
                ecThreadSpec.remove();
            else
                ecThreadSpec.set(curveSpec);
        } else
        if(parameterName.equals("ecImplicitlyCa"))
        {
            if(securityManager != null)
                securityManager.checkPermission(BC_EC_PERMISSION);
            if((parameter instanceof ECParameterSpec) || parameter == null)
                ecImplicitCaParams = (ECParameterSpec)parameter;
            else
                ecImplicitCaParams = EC5Util.convertSpec((java.security.spec.ECParameterSpec)parameter, false);
        } else
        if(parameterName.equals("threadLocalDhDefaultParams"))
        {
            if(securityManager != null)
                securityManager.checkPermission(BC_DH_LOCAL_PERMISSION);
            Object dhSpec;
            if((parameter instanceof DHParameterSpec) || (parameter instanceof DHParameterSpec[]) || parameter == null)
                dhSpec = parameter;
            else
                throw new IllegalArgumentException("not a valid DHParameterSpec");
            if(dhSpec == null)
                dhThreadSpec.remove();
            else
                dhThreadSpec.set(dhSpec);
        } else
        if(parameterName.equals("DhDefaultParams"))
        {
            if(securityManager != null)
                securityManager.checkPermission(BC_DH_PERMISSION);
            if((parameter instanceof DHParameterSpec) || (parameter instanceof DHParameterSpec[]) || parameter == null)
                dhDefaultParams = parameter;
            else
                throw new IllegalArgumentException("not a valid DHParameterSpec or DHParameterSpec[]");
        }
    }

    public ECParameterSpec getEcImplicitlyCa()
    {
        ECParameterSpec spec = (ECParameterSpec)ecThreadSpec.get();
        if(spec != null)
            return spec;
        else
            return ecImplicitCaParams;
    }

    public DHParameterSpec getDHDefaultParameters(int keySize)
    {
        Object params = dhThreadSpec.get();
        if(params == null)
            params = dhDefaultParams;
        if(params instanceof DHParameterSpec)
        {
            DHParameterSpec spec = (DHParameterSpec)params;
            if(spec.getP().bitLength() == keySize)
                return spec;
        } else
        if(params instanceof DHParameterSpec[])
        {
            DHParameterSpec specs[] = (DHParameterSpec[])(DHParameterSpec[])params;
            for(int i = 0; i != specs.length; i++)
                if(specs[i].getP().bitLength() == keySize)
                    return specs[i];

        }
        return null;
    }

    private static Permission BC_EC_LOCAL_PERMISSION = new ProviderConfigurationPermission("BC", "threadLocalEcImplicitlyCa");
    private static Permission BC_EC_PERMISSION = new ProviderConfigurationPermission("BC", "ecImplicitlyCa");
    private static Permission BC_DH_LOCAL_PERMISSION = new ProviderConfigurationPermission("BC", "threadLocalDhDefaultParams");
    private static Permission BC_DH_PERMISSION = new ProviderConfigurationPermission("BC", "DhDefaultParams");
    private ThreadLocal ecThreadSpec;
    private ThreadLocal dhThreadSpec;
    private volatile ECParameterSpec ecImplicitCaParams;
    private volatile Object dhDefaultParams;

}
