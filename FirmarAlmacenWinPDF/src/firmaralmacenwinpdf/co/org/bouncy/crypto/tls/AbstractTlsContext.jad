// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTlsContext.java

package co.org.bouncy.crypto.tls;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsContext, SecurityParameters, TlsUtils, ProtocolVersion

abstract class AbstractTlsContext
    implements TlsContext
{

    AbstractTlsContext(SecureRandom secureRandom, SecurityParameters securityParameters)
    {
        clientVersion = null;
        serverVersion = null;
        userObject = null;
        this.secureRandom = secureRandom;
        this.securityParameters = securityParameters;
    }

    public SecureRandom getSecureRandom()
    {
        return secureRandom;
    }

    public SecurityParameters getSecurityParameters()
    {
        return securityParameters;
    }

    public ProtocolVersion getClientVersion()
    {
        return clientVersion;
    }

    public void setClientVersion(ProtocolVersion clientVersion)
    {
        this.clientVersion = clientVersion;
    }

    public ProtocolVersion getServerVersion()
    {
        return serverVersion;
    }

    public void setServerVersion(ProtocolVersion serverVersion)
    {
        this.serverVersion = serverVersion;
    }

    public Object getUserObject()
    {
        return userObject;
    }

    public void setUserObject(Object userObject)
    {
        this.userObject = userObject;
    }

    public byte[] exportKeyingMaterial(String asciiLabel, byte context_value[], int length)
    {
        SecurityParameters sp = getSecurityParameters();
        byte cr[] = sp.getClientRandom();
        byte sr[] = sp.getServerRandom();
        int seedLength = cr.length + sr.length;
        if(context_value != null)
            seedLength += 2 + context_value.length;
        byte seed[] = new byte[seedLength];
        int seedPos = 0;
        System.arraycopy(cr, 0, seed, seedPos, cr.length);
        seedPos += cr.length;
        System.arraycopy(sr, 0, seed, seedPos, sr.length);
        seedPos += sr.length;
        if(context_value != null)
        {
            TlsUtils.writeUint16(context_value.length, seed, seedPos);
            seedPos += 2;
            System.arraycopy(context_value, 0, seed, seedPos, context_value.length);
            seedPos += context_value.length;
        }
        if(seedPos != seedLength)
            throw new IllegalStateException("error in calculation of seed for export");
        else
            return TlsUtils.PRF(this, sp.getMasterSecret(), asciiLabel, seed, length);
    }

    private SecureRandom secureRandom;
    private SecurityParameters securityParameters;
    private ProtocolVersion clientVersion;
    private ProtocolVersion serverVersion;
    private Object userObject;
}
