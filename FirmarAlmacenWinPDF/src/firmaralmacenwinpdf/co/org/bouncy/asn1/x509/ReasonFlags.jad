// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReasonFlags.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.DERBitString;

public class ReasonFlags extends DERBitString
{

    public ReasonFlags(int reasons)
    {
        super(getBytes(reasons), getPadBits(reasons));
    }

    public ReasonFlags(DERBitString reasons)
    {
        super(reasons.getBytes(), reasons.getPadBits());
    }

    /**
     * @deprecated Field UNUSED is deprecated
     */
    public static final int UNUSED = 128;
    /**
     * @deprecated Field KEY_COMPROMISE is deprecated
     */
    public static final int KEY_COMPROMISE = 64;
    /**
     * @deprecated Field CA_COMPROMISE is deprecated
     */
    public static final int CA_COMPROMISE = 32;
    /**
     * @deprecated Field AFFILIATION_CHANGED is deprecated
     */
    public static final int AFFILIATION_CHANGED = 16;
    /**
     * @deprecated Field SUPERSEDED is deprecated
     */
    public static final int SUPERSEDED = 8;
    /**
     * @deprecated Field CESSATION_OF_OPERATION is deprecated
     */
    public static final int CESSATION_OF_OPERATION = 4;
    /**
     * @deprecated Field CERTIFICATE_HOLD is deprecated
     */
    public static final int CERTIFICATE_HOLD = 2;
    /**
     * @deprecated Field PRIVILEGE_WITHDRAWN is deprecated
     */
    public static final int PRIVILEGE_WITHDRAWN = 1;
    /**
     * @deprecated Field AA_COMPROMISE is deprecated
     */
    public static final int AA_COMPROMISE = 32768;
    public static final int unused = 128;
    public static final int keyCompromise = 64;
    public static final int cACompromise = 32;
    public static final int affiliationChanged = 16;
    public static final int superseded = 8;
    public static final int cessationOfOperation = 4;
    public static final int certificateHold = 2;
    public static final int privilegeWithdrawn = 1;
    public static final int aACompromise = 32768;
}
