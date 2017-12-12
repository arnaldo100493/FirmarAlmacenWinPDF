// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIFailureInfo.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.DERBitString;

public class PKIFailureInfo extends DERBitString
{

    public PKIFailureInfo(int info)
    {
        super(getBytes(info), getPadBits(info));
    }

    public PKIFailureInfo(DERBitString info)
    {
        super(info.getBytes(), info.getPadBits());
    }

    public String toString()
    {
        return (new StringBuilder()).append("PKIFailureInfo: 0x").append(Integer.toHexString(intValue())).toString();
    }

    public static final int badAlg = 128;
    public static final int badMessageCheck = 64;
    public static final int badRequest = 32;
    public static final int badTime = 16;
    public static final int badCertId = 8;
    public static final int badDataFormat = 4;
    public static final int wrongAuthority = 2;
    public static final int incorrectData = 1;
    public static final int missingTimeStamp = 32768;
    public static final int badPOP = 16384;
    public static final int certRevoked = 8192;
    public static final int certConfirmed = 4096;
    public static final int wrongIntegrity = 2048;
    public static final int badRecipientNonce = 1024;
    public static final int timeNotAvailable = 512;
    public static final int unacceptedPolicy = 256;
    public static final int unacceptedExtension = 0x800000;
    public static final int addInfoNotAvailable = 0x400000;
    public static final int badSenderNonce = 0x200000;
    public static final int badCertTemplate = 0x100000;
    public static final int signerNotTrusted = 0x80000;
    public static final int transactionIdInUse = 0x40000;
    public static final int unsupportedVersion = 0x20000;
    public static final int notAuthorized = 0x10000;
    public static final int systemUnavail = 0x80000000;
    public static final int systemFailure = 0x40000000;
    public static final int duplicateCertReq = 0x20000000;
    /**
     * @deprecated Field BAD_ALG is deprecated
     */
    public static final int BAD_ALG = 128;
    /**
     * @deprecated Field BAD_MESSAGE_CHECK is deprecated
     */
    public static final int BAD_MESSAGE_CHECK = 64;
    /**
     * @deprecated Field BAD_REQUEST is deprecated
     */
    public static final int BAD_REQUEST = 32;
    /**
     * @deprecated Field BAD_TIME is deprecated
     */
    public static final int BAD_TIME = 16;
    /**
     * @deprecated Field BAD_CERT_ID is deprecated
     */
    public static final int BAD_CERT_ID = 8;
    /**
     * @deprecated Field BAD_DATA_FORMAT is deprecated
     */
    public static final int BAD_DATA_FORMAT = 4;
    /**
     * @deprecated Field WRONG_AUTHORITY is deprecated
     */
    public static final int WRONG_AUTHORITY = 2;
    /**
     * @deprecated Field INCORRECT_DATA is deprecated
     */
    public static final int INCORRECT_DATA = 1;
    /**
     * @deprecated Field MISSING_TIME_STAMP is deprecated
     */
    public static final int MISSING_TIME_STAMP = 32768;
    /**
     * @deprecated Field BAD_POP is deprecated
     */
    public static final int BAD_POP = 16384;
    /**
     * @deprecated Field TIME_NOT_AVAILABLE is deprecated
     */
    public static final int TIME_NOT_AVAILABLE = 512;
    /**
     * @deprecated Field UNACCEPTED_POLICY is deprecated
     */
    public static final int UNACCEPTED_POLICY = 256;
    /**
     * @deprecated Field UNACCEPTED_EXTENSION is deprecated
     */
    public static final int UNACCEPTED_EXTENSION = 0x800000;
    /**
     * @deprecated Field ADD_INFO_NOT_AVAILABLE is deprecated
     */
    public static final int ADD_INFO_NOT_AVAILABLE = 0x400000;
    /**
     * @deprecated Field SYSTEM_FAILURE is deprecated
     */
    public static final int SYSTEM_FAILURE = 0x40000000;
}
