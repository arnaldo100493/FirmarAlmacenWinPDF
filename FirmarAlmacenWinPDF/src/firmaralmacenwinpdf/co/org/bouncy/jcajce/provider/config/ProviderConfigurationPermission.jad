// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProviderConfigurationPermission.java

package co.org.bouncy.jcajce.provider.config;

import co.org.bouncy.util.Strings;
import java.security.BasicPermission;
import java.security.Permission;
import java.util.StringTokenizer;

public class ProviderConfigurationPermission extends BasicPermission
{

    public ProviderConfigurationPermission(String name)
    {
        super(name);
        actions = "all";
        permissionMask = 15;
    }

    public ProviderConfigurationPermission(String name, String actions)
    {
        super(name, actions);
        this.actions = actions;
        permissionMask = calculateMask(actions);
    }

    private int calculateMask(String actions)
    {
        StringTokenizer tok = new StringTokenizer(Strings.toLowerCase(actions), " ,");
        int mask = 0;
        do
        {
            if(!tok.hasMoreTokens())
                break;
            String s = tok.nextToken();
            if(s.equals("threadlocalecimplicitlyca"))
                mask |= 1;
            else
            if(s.equals("ecimplicitlyca"))
                mask |= 2;
            else
            if(s.equals("threadlocaldhdefaultparams"))
                mask |= 4;
            else
            if(s.equals("dhdefaultparams"))
                mask |= 8;
            else
            if(s.equals("all"))
                mask |= 0xf;
        } while(true);
        if(mask == 0)
            throw new IllegalArgumentException("unknown permissions passed to mask");
        else
            return mask;
    }

    public String getActions()
    {
        return actions;
    }

    public boolean implies(Permission permission)
    {
        if(!(permission instanceof ProviderConfigurationPermission))
            return false;
        if(!getName().equals(permission.getName()))
        {
            return false;
        } else
        {
            ProviderConfigurationPermission other = (ProviderConfigurationPermission)permission;
            return (permissionMask & other.permissionMask) == other.permissionMask;
        }
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(obj instanceof ProviderConfigurationPermission)
        {
            ProviderConfigurationPermission other = (ProviderConfigurationPermission)obj;
            return permissionMask == other.permissionMask && getName().equals(other.getName());
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return getName().hashCode() + permissionMask;
    }

    private static final int THREAD_LOCAL_EC_IMPLICITLY_CA = 1;
    private static final int EC_IMPLICITLY_CA = 2;
    private static final int THREAD_LOCAL_DH_DEFAULT_PARAMS = 4;
    private static final int DH_DEFAULT_PARAMS = 8;
    private static final int ALL = 15;
    private static final String THREAD_LOCAL_EC_IMPLICITLY_CA_STR = "threadlocalecimplicitlyca";
    private static final String EC_IMPLICITLY_CA_STR = "ecimplicitlyca";
    private static final String THREAD_LOCAL_DH_DEFAULT_PARAMS_STR = "threadlocaldhdefaultparams";
    private static final String DH_DEFAULT_PARAMS_STR = "dhdefaultparams";
    private static final String ALL_STR = "all";
    private final String actions;
    private final int permissionMask;
}
