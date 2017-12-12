// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VOMSAttribute.java

package co.org.bouncy.voms;


// Referenced classes of package co.org.bouncy.voms:
//            VOMSAttribute

public class VOMSAttribute$FQAN
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

    public VOMSAttribute$FQAN(String fqan)
    {
        this$0 = VOMSAttribute.this;
        super();
        this.fqan = fqan;
    }

    public VOMSAttribute$FQAN(String group, String role, String capability)
    {
        this$0 = VOMSAttribute.this;
        super();
        this.group = group;
        this.role = role;
        this.capability = capability;
    }
}
