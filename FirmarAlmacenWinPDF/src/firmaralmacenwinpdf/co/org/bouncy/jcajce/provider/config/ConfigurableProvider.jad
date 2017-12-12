// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConfigurableProvider.java

package co.org.bouncy.jcajce.provider.config;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;

public interface ConfigurableProvider
{

    public abstract void setParameter(String s, Object obj);

    public abstract void addAlgorithm(String s, String s1);

    public abstract boolean hasAlgorithm(String s, String s1);

    public abstract void addKeyInfoConverter(ASN1ObjectIdentifier asn1objectidentifier, AsymmetricKeyInfoConverter asymmetrickeyinfoconverter);

    public static final String THREAD_LOCAL_EC_IMPLICITLY_CA = "threadLocalEcImplicitlyCa";
    public static final String EC_IMPLICITLY_CA = "ecImplicitlyCa";
    public static final String THREAD_LOCAL_DH_DEFAULT_PARAMS = "threadLocalDhDefaultParams";
    public static final String DH_DEFAULT_PARAMS = "DhDefaultParams";
}
