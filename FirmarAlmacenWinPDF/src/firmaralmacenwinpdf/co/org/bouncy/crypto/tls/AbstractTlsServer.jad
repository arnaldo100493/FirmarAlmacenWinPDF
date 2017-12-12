// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractTlsServer.java

package co.org.bouncy.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsPeer, DefaultTlsCipherFactory, TlsFatalAlert, TlsNullCompression, 
//            NewSessionTicket, TlsServer, ProtocolVersion, TlsECCUtils, 
//            NamedCurve, TlsUtils, TlsProtocol, TlsCipherFactory, 
//            TlsServerContext, CertificateRequest, Certificate, TlsCompression

public abstract class AbstractTlsServer extends AbstractTlsPeer
    implements TlsServer
{

    public AbstractTlsServer()
    {
        this(((TlsCipherFactory) (new DefaultTlsCipherFactory())));
    }

    public AbstractTlsServer(TlsCipherFactory cipherFactory)
    {
        this.cipherFactory = cipherFactory;
    }

    protected abstract int[] getCipherSuites();

    protected short[] getCompressionMethods()
    {
        return (new short[] {
            0
        });
    }

    protected ProtocolVersion getMaximumVersion()
    {
        return ProtocolVersion.TLSv11;
    }

    protected ProtocolVersion getMinimumVersion()
    {
        return ProtocolVersion.TLSv10;
    }

    protected boolean supportsClientECCCapabilities(int namedCurves[], short ecPointFormats[])
    {
        if(namedCurves == null)
            return TlsECCUtils.hasAnySupportedNamedCurves();
        for(int i = 0; i < namedCurves.length; i++)
        {
            int namedCurve = namedCurves[i];
            if(!NamedCurve.refersToASpecificNamedCurve(namedCurve) || TlsECCUtils.isSupportedNamedCurve(namedCurve))
                return true;
        }

        return false;
    }

    public void init(TlsServerContext context)
    {
        this.context = context;
    }

    public void notifyClientVersion(ProtocolVersion clientVersion)
        throws IOException
    {
        this.clientVersion = clientVersion;
    }

    public void notifyOfferedCipherSuites(int offeredCipherSuites[])
        throws IOException
    {
        this.offeredCipherSuites = offeredCipherSuites;
        eccCipherSuitesOffered = TlsECCUtils.containsECCCipherSuites(this.offeredCipherSuites);
    }

    public void notifyOfferedCompressionMethods(short offeredCompressionMethods[])
        throws IOException
    {
        this.offeredCompressionMethods = offeredCompressionMethods;
    }

    public void notifySecureRenegotiation(boolean secureRenegotiation)
        throws IOException
    {
        if(!secureRenegotiation)
            throw new TlsFatalAlert((short)40);
        else
            return;
    }

    public void processClientExtensions(Hashtable clientExtensions)
        throws IOException
    {
        this.clientExtensions = clientExtensions;
        if(clientExtensions != null)
        {
            supportedSignatureAlgorithms = TlsUtils.getSignatureAlgorithmsExtension(clientExtensions);
            if(supportedSignatureAlgorithms != null && !TlsUtils.isSignatureAlgorithmsExtensionAllowed(clientVersion))
                throw new TlsFatalAlert((short)47);
            namedCurves = TlsECCUtils.getSupportedEllipticCurvesExtension(clientExtensions);
            clientECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(clientExtensions);
        }
        if(!eccCipherSuitesOffered && (namedCurves != null || clientECPointFormats != null))
            throw new TlsFatalAlert((short)47);
        else
            return;
    }

    public ProtocolVersion getServerVersion()
        throws IOException
    {
        if(getMinimumVersion().isEqualOrEarlierVersionOf(clientVersion))
        {
            ProtocolVersion maximumVersion = getMaximumVersion();
            if(clientVersion.isEqualOrEarlierVersionOf(maximumVersion))
                return serverVersion = clientVersion;
            if(clientVersion.isLaterVersionOf(maximumVersion))
                return serverVersion = maximumVersion;
        }
        throw new TlsFatalAlert((short)70);
    }

    public int getSelectedCipherSuite()
        throws IOException
    {
        boolean eccCipherSuitesEnabled = supportsClientECCCapabilities(namedCurves, clientECPointFormats);
        int cipherSuites[] = getCipherSuites();
        for(int i = 0; i < cipherSuites.length; i++)
        {
            int cipherSuite = cipherSuites[i];
            if(TlsProtocol.arrayContains(offeredCipherSuites, cipherSuite) && (eccCipherSuitesEnabled || !TlsECCUtils.isECCCipherSuite(cipherSuite)))
                return selectedCipherSuite = cipherSuite;
        }

        throw new TlsFatalAlert((short)40);
    }

    public short getSelectedCompressionMethod()
        throws IOException
    {
        short compressionMethods[] = getCompressionMethods();
        for(int i = 0; i < compressionMethods.length; i++)
            if(TlsProtocol.arrayContains(offeredCompressionMethods, compressionMethods[i]))
                return selectedCompressionMethod = compressionMethods[i];

        throw new TlsFatalAlert((short)40);
    }

    public Hashtable getServerExtensions()
        throws IOException
    {
        if(clientECPointFormats != null && TlsECCUtils.isECCCipherSuite(selectedCipherSuite))
        {
            serverECPointFormats = (new short[] {
                2, 1, 0
            });
            serverExtensions = new Hashtable();
            TlsECCUtils.addSupportedPointFormatsExtension(serverExtensions, serverECPointFormats);
            return serverExtensions;
        } else
        {
            return null;
        }
    }

    public Vector getServerSupplementalData()
        throws IOException
    {
        return null;
    }

    public CertificateRequest getCertificateRequest()
    {
        return null;
    }

    public void processClientSupplementalData(Vector clientSupplementalData)
        throws IOException
    {
        if(clientSupplementalData != null)
            throw new TlsFatalAlert((short)10);
        else
            return;
    }

    public void notifyClientCertificate(Certificate clientCertificate)
        throws IOException
    {
        throw new TlsFatalAlert((short)80);
    }

    public TlsCompression getCompression()
        throws IOException
    {
        switch(selectedCompressionMethod)
        {
        case 0: // '\0'
            return new TlsNullCompression();
        }
        throw new TlsFatalAlert((short)80);
    }

    public NewSessionTicket getNewSessionTicket()
        throws IOException
    {
        return new NewSessionTicket(0L, TlsUtils.EMPTY_BYTES);
    }

    public void notifyHandshakeComplete()
        throws IOException
    {
    }

    protected TlsCipherFactory cipherFactory;
    protected TlsServerContext context;
    protected ProtocolVersion clientVersion;
    protected int offeredCipherSuites[];
    protected short offeredCompressionMethods[];
    protected Hashtable clientExtensions;
    protected Vector supportedSignatureAlgorithms;
    protected boolean eccCipherSuitesOffered;
    protected int namedCurves[];
    protected short clientECPointFormats[];
    protected short serverECPointFormats[];
    protected ProtocolVersion serverVersion;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected Hashtable serverExtensions;
}
