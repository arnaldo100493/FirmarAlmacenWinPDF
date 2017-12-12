// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2ParameterSpec.java

package co.org.bouncy.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class McElieceCCA2ParameterSpec
    implements AlgorithmParameterSpec
{

    public McElieceCCA2ParameterSpec()
    {
        this("SHA256");
    }

    public McElieceCCA2ParameterSpec(String mdName)
    {
        this.mdName = mdName;
    }

    public String getMDName()
    {
        return mdName;
    }

    public static final String DEFAULT_MD = "SHA256";
    private String mdName;
}
