// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHKeyParameters.java

package co.org.bouncy.crypto.params;


// Referenced classes of package co.org.bouncy.crypto.params:
//            AsymmetricKeyParameter, DHParameters

public class DHKeyParameters extends AsymmetricKeyParameter
{

    protected DHKeyParameters(boolean isPrivate, DHParameters params)
    {
        super(isPrivate);
        this.params = params;
    }

    public DHParameters getParameters()
    {
        return params;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof DHKeyParameters))
            return false;
        DHKeyParameters dhKey = (DHKeyParameters)obj;
        if(params == null)
            return dhKey.getParameters() == null;
        else
            return params.equals(dhKey.getParameters());
    }

    public int hashCode()
    {
        int code = isPrivate() ? 0 : 1;
        if(params != null)
            code ^= params.hashCode();
        return code;
    }

    private DHParameters params;
}
