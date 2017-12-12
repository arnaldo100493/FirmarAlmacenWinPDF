// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECKeyParameters.java

package co.org.bouncy.crypto.params;


// Referenced classes of package co.org.bouncy.crypto.params:
//            AsymmetricKeyParameter, ECDomainParameters

public class ECKeyParameters extends AsymmetricKeyParameter
{

    protected ECKeyParameters(boolean isPrivate, ECDomainParameters params)
    {
        super(isPrivate);
        this.params = params;
    }

    public ECDomainParameters getParameters()
    {
        return params;
    }

    ECDomainParameters params;
}
