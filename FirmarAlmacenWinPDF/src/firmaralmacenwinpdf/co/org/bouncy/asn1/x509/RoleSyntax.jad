// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RoleSyntax.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralName, GeneralNames

public class RoleSyntax extends ASN1Object
{

    public static RoleSyntax getInstance(Object obj)
    {
        if(obj instanceof RoleSyntax)
            return (RoleSyntax)obj;
        if(obj != null)
            return new RoleSyntax(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public RoleSyntax(GeneralNames roleAuthority, GeneralName roleName)
    {
        if(roleName == null || roleName.getTagNo() != 6 || ((ASN1String)roleName.getName()).getString().equals(""))
        {
            throw new IllegalArgumentException("the role name MUST be non empty and MUST use the URI option of GeneralName");
        } else
        {
            this.roleAuthority = roleAuthority;
            this.roleName = roleName;
            return;
        }
    }

    public RoleSyntax(GeneralName roleName)
    {
        this(null, roleName);
    }

    public RoleSyntax(String roleName)
    {
        this(new GeneralName(6, roleName != null ? roleName : ""));
    }

    private RoleSyntax(ASN1Sequence seq)
    {
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        for(int i = 0; i != seq.size(); i++)
        {
            ASN1TaggedObject taggedObject = ASN1TaggedObject.getInstance(seq.getObjectAt(i));
            switch(taggedObject.getTagNo())
            {
            case 0: // '\0'
                roleAuthority = GeneralNames.getInstance(taggedObject, false);
                break;

            case 1: // '\001'
                roleName = GeneralName.getInstance(taggedObject, true);
                break;

            default:
                throw new IllegalArgumentException("Unknown tag in RoleSyntax");
            }
        }

    }

    public GeneralNames getRoleAuthority()
    {
        return roleAuthority;
    }

    public GeneralName getRoleName()
    {
        return roleName;
    }

    public String getRoleNameAsString()
    {
        ASN1String str = (ASN1String)roleName.getName();
        return str.getString();
    }

    public String[] getRoleAuthorityAsString()
    {
        if(roleAuthority == null)
            return new String[0];
        GeneralName names[] = roleAuthority.getNames();
        String namesString[] = new String[names.length];
        for(int i = 0; i < names.length; i++)
        {
            ASN1Encodable value = names[i].getName();
            if(value instanceof ASN1String)
                namesString[i] = ((ASN1String)value).getString();
            else
                namesString[i] = value.toString();
        }

        return namesString;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(roleAuthority != null)
            v.add(new DERTaggedObject(false, 0, roleAuthority));
        v.add(new DERTaggedObject(true, 1, roleName));
        return new DERSequence(v);
    }

    public String toString()
    {
        StringBuffer buff = new StringBuffer((new StringBuilder()).append("Name: ").append(getRoleNameAsString()).append(" - Auth: ").toString());
        if(roleAuthority == null || roleAuthority.getNames().length == 0)
        {
            buff.append("N/A");
        } else
        {
            String names[] = getRoleAuthorityAsString();
            buff.append('[').append(names[0]);
            for(int i = 1; i < names.length; i++)
                buff.append(", ").append(names[i]);

            buff.append(']');
        }
        return buff.toString();
    }

    private GeneralNames roleAuthority;
    private GeneralName roleName;
}
