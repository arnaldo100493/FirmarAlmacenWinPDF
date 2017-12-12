// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsContext.java

package co.org.bouncy.crypto.tls;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            SecurityParameters, ProtocolVersion

public interface TlsContext
{

    public abstract SecureRandom getSecureRandom();

    public abstract SecurityParameters getSecurityParameters();

    public abstract boolean isServer();

    public abstract ProtocolVersion getClientVersion();

    public abstract ProtocolVersion getServerVersion();

    public abstract Object getUserObject();

    public abstract void setUserObject(Object obj);

    public abstract byte[] exportKeyingMaterial(String s, byte abyte0[], int i);
}
