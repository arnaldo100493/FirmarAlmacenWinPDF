// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSAClient.java

package co.com.pdf.text.pdf.security;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public interface TSAClient
{

    public abstract int getTokenSizeEstimate();

    public abstract MessageDigest getMessageDigest()
        throws GeneralSecurityException;

    public abstract byte[] getTimeStampToken(byte abyte0[])
        throws Exception;
}
