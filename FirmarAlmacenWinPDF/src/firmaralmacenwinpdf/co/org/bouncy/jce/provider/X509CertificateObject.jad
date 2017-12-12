// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertificateObject.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1OutputStream;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.ASN1String;
import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.DERIA5String;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.misc.MiscObjectIdentifiers;
import co.org.bouncy.asn1.misc.NetscapeCertType;
import co.org.bouncy.asn1.misc.NetscapeRevocationURL;
import co.org.bouncy.asn1.misc.VerisignCzagExtension;
import co.org.bouncy.asn1.util.ASN1Dump;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x500.style.RFC4519Style;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.BasicConstraints;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.KeyUsage;
import co.org.bouncy.asn1.x509.TBSCertificate;
import co.org.bouncy.asn1.x509.Time;
import co.org.bouncy.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Integers;
import co.org.bouncy.util.encoders.Hex;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.jce.provider:
//            RFC3280CertPathUtilities, BouncyCastleProvider, X509SignatureUtil

public class X509CertificateObject extends X509Certificate
    implements PKCS12BagAttributeCarrier
{

    public X509CertificateObject(co.org.bouncy.asn1.x509.Certificate c)
        throws CertificateParsingException
    {
        attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.c = c;
        try
        {
            byte bytes[] = getExtensionBytes("2.5.29.19");
            if(bytes != null)
                basicConstraints = BasicConstraints.getInstance(ASN1Primitive.fromByteArray(bytes));
        }
        catch(Exception e)
        {
            throw new CertificateParsingException((new StringBuilder()).append("cannot construct BasicConstraints: ").append(e).toString());
        }
        try
        {
            byte bytes[] = getExtensionBytes("2.5.29.15");
            if(bytes != null)
            {
                DERBitString bits = DERBitString.getInstance(ASN1Primitive.fromByteArray(bytes));
                bytes = bits.getBytes();
                int length = bytes.length * 8 - bits.getPadBits();
                keyUsage = new boolean[length >= 9 ? length : 9];
                for(int i = 0; i != length; i++)
                    keyUsage[i] = (bytes[i / 8] & 128 >>> i % 8) != 0;

            } else
            {
                keyUsage = null;
            }
        }
        catch(Exception e)
        {
            throw new CertificateParsingException((new StringBuilder()).append("cannot construct KeyUsage: ").append(e).toString());
        }
    }

    public void checkValidity()
        throws CertificateExpiredException, CertificateNotYetValidException
    {
        checkValidity(new Date());
    }

    public void checkValidity(Date date)
        throws CertificateExpiredException, CertificateNotYetValidException
    {
        if(date.getTime() > getNotAfter().getTime())
            throw new CertificateExpiredException((new StringBuilder()).append("certificate expired on ").append(c.getEndDate().getTime()).toString());
        if(date.getTime() < getNotBefore().getTime())
            throw new CertificateNotYetValidException((new StringBuilder()).append("certificate not valid till ").append(c.getStartDate().getTime()).toString());
        else
            return;
    }

    public int getVersion()
    {
        return c.getVersionNumber();
    }

    public BigInteger getSerialNumber()
    {
        return c.getSerialNumber().getValue();
    }

    public Principal getIssuerDN()
    {
        try
        {
            return new X509Principal(X500Name.getInstance(c.getIssuer().getEncoded()));
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public X500Principal getIssuerX500Principal()
    {
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream aOut = new ASN1OutputStream(bOut);
            aOut.writeObject(c.getIssuer());
            return new X500Principal(bOut.toByteArray());
        }
        catch(IOException e)
        {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Principal getSubjectDN()
    {
        return new X509Principal(X500Name.getInstance(c.getSubject().toASN1Primitive()));
    }

    public X500Principal getSubjectX500Principal()
    {
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream aOut = new ASN1OutputStream(bOut);
            aOut.writeObject(c.getSubject());
            return new X500Principal(bOut.toByteArray());
        }
        catch(IOException e)
        {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getNotBefore()
    {
        return c.getStartDate().getDate();
    }

    public Date getNotAfter()
    {
        return c.getEndDate().getDate();
    }

    public byte[] getTBSCertificate()
        throws CertificateEncodingException
    {
        try
        {
            return c.getTBSCertificate().getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public byte[] getSignature()
    {
        return c.getSignature().getBytes();
    }

    public String getSigAlgName()
    {
        Provider prov = Security.getProvider("BC");
        if(prov != null)
        {
            String algName = prov.getProperty((new StringBuilder()).append("Alg.Alias.Signature.").append(getSigAlgOID()).toString());
            if(algName != null)
                return algName;
        }
        Provider provs[] = Security.getProviders();
        for(int i = 0; i != provs.length; i++)
        {
            String algName = provs[i].getProperty((new StringBuilder()).append("Alg.Alias.Signature.").append(getSigAlgOID()).toString());
            if(algName != null)
                return algName;
        }

        return getSigAlgOID();
    }

    public String getSigAlgOID()
    {
        return c.getSignatureAlgorithm().getAlgorithm().getId();
    }

    public byte[] getSigAlgParams()
    {
        if(c.getSignatureAlgorithm().getParameters() != null)
            try
            {
                return c.getSignatureAlgorithm().getParameters().toASN1Primitive().getEncoded("DER");
            }
            catch(IOException e)
            {
                return null;
            }
        else
            return null;
    }

    public boolean[] getIssuerUniqueID()
    {
        DERBitString id = c.getTBSCertificate().getIssuerUniqueId();
        if(id != null)
        {
            byte bytes[] = id.getBytes();
            boolean boolId[] = new boolean[bytes.length * 8 - id.getPadBits()];
            for(int i = 0; i != boolId.length; i++)
                boolId[i] = (bytes[i / 8] & 128 >>> i % 8) != 0;

            return boolId;
        } else
        {
            return null;
        }
    }

    public boolean[] getSubjectUniqueID()
    {
        DERBitString id = c.getTBSCertificate().getSubjectUniqueId();
        if(id != null)
        {
            byte bytes[] = id.getBytes();
            boolean boolId[] = new boolean[bytes.length * 8 - id.getPadBits()];
            for(int i = 0; i != boolId.length; i++)
                boolId[i] = (bytes[i / 8] & 128 >>> i % 8) != 0;

            return boolId;
        } else
        {
            return null;
        }
    }

    public boolean[] getKeyUsage()
    {
        return keyUsage;
    }

    public List getExtendedKeyUsage()
        throws CertificateParsingException
    {
        byte bytes[] = getExtensionBytes("2.5.29.37");
        if(bytes != null)
            try
            {
                ASN1InputStream dIn = new ASN1InputStream(bytes);
                ASN1Sequence seq = (ASN1Sequence)dIn.readObject();
                List list = new ArrayList();
                for(int i = 0; i != seq.size(); i++)
                    list.add(((ASN1ObjectIdentifier)seq.getObjectAt(i)).getId());

                return Collections.unmodifiableList(list);
            }
            catch(Exception e)
            {
                throw new CertificateParsingException("error processing extended key usage extension");
            }
        else
            return null;
    }

    public int getBasicConstraints()
    {
        if(basicConstraints != null)
        {
            if(basicConstraints.isCA())
            {
                if(basicConstraints.getPathLenConstraint() == null)
                    return 0x7fffffff;
                else
                    return basicConstraints.getPathLenConstraint().intValue();
            } else
            {
                return -1;
            }
        } else
        {
            return -1;
        }
    }

    public Collection getSubjectAlternativeNames()
        throws CertificateParsingException
    {
        return getAlternativeNames(getExtensionBytes(Extension.subjectAlternativeName.getId()));
    }

    public Collection getIssuerAlternativeNames()
        throws CertificateParsingException
    {
        return getAlternativeNames(getExtensionBytes(Extension.issuerAlternativeName.getId()));
    }

    public Set getCriticalExtensionOIDs()
    {
        if(getVersion() == 3)
        {
            Set set = new HashSet();
            Extensions extensions = c.getTBSCertificate().getExtensions();
            if(extensions != null)
            {
                Enumeration e = extensions.oids();
                do
                {
                    if(!e.hasMoreElements())
                        break;
                    ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                    Extension ext = extensions.getExtension(oid);
                    if(ext.isCritical())
                        set.add(oid.getId());
                } while(true);
                return set;
            }
        }
        return null;
    }

    private byte[] getExtensionBytes(String oid)
    {
        Extensions exts = c.getTBSCertificate().getExtensions();
        if(exts != null)
        {
            Extension ext = exts.getExtension(new ASN1ObjectIdentifier(oid));
            if(ext != null)
                return ext.getExtnValue().getOctets();
        }
        return null;
    }

    public byte[] getExtensionValue(String oid)
    {
        Extensions exts = c.getTBSCertificate().getExtensions();
        if(exts != null)
        {
            Extension ext = exts.getExtension(new ASN1ObjectIdentifier(oid));
            if(ext != null)
                try
                {
                    return ext.getExtnValue().getEncoded();
                }
                catch(Exception e)
                {
                    throw new IllegalStateException((new StringBuilder()).append("error parsing ").append(e.toString()).toString());
                }
        }
        return null;
    }

    public Set getNonCriticalExtensionOIDs()
    {
        if(getVersion() == 3)
        {
            Set set = new HashSet();
            Extensions extensions = c.getTBSCertificate().getExtensions();
            if(extensions != null)
            {
                Enumeration e = extensions.oids();
                do
                {
                    if(!e.hasMoreElements())
                        break;
                    ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                    Extension ext = extensions.getExtension(oid);
                    if(!ext.isCritical())
                        set.add(oid.getId());
                } while(true);
                return set;
            }
        }
        return null;
    }

    public boolean hasUnsupportedCriticalExtension()
    {
label0:
        {
            if(getVersion() != 3)
                break label0;
            Extensions extensions = c.getTBSCertificate().getExtensions();
            if(extensions == null)
                break label0;
            Enumeration e = extensions.oids();
            Extension ext;
            do
            {
                ASN1ObjectIdentifier oid;
                String oidId;
                do
                {
                    if(!e.hasMoreElements())
                        break label0;
                    oid = (ASN1ObjectIdentifier)e.nextElement();
                    oidId = oid.getId();
                } while(oidId.equals(RFC3280CertPathUtilities.KEY_USAGE) || oidId.equals(RFC3280CertPathUtilities.CERTIFICATE_POLICIES) || oidId.equals(RFC3280CertPathUtilities.POLICY_MAPPINGS) || oidId.equals(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY) || oidId.equals(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS) || oidId.equals(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT) || oidId.equals(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR) || oidId.equals(RFC3280CertPathUtilities.POLICY_CONSTRAINTS) || oidId.equals(RFC3280CertPathUtilities.BASIC_CONSTRAINTS) || oidId.equals(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME) || oidId.equals(RFC3280CertPathUtilities.NAME_CONSTRAINTS));
                ext = extensions.getExtension(oid);
            } while(!ext.isCritical());
            return true;
        }
        return false;
    }

    public PublicKey getPublicKey()
    {
        try
        {
            return BouncyCastleProvider.getPublicKey(c.getSubjectPublicKeyInfo());
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public byte[] getEncoded()
        throws CertificateEncodingException
    {
        try
        {
            return c.getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof Certificate))
            return false;
        Certificate other = (Certificate)o;
        try
        {
            byte b1[] = getEncoded();
            byte b2[] = other.getEncoded();
            return Arrays.areEqual(b1, b2);
        }
        catch(CertificateEncodingException e)
        {
            return false;
        }
    }

    public synchronized int hashCode()
    {
        if(!hashValueSet)
        {
            hashValue = calculateHashCode();
            hashValueSet = true;
        }
        return hashValue;
    }

    private int calculateHashCode()
    {
        try
        {
            int hashCode = 0;
            byte certData[] = getEncoded();
            for(int i = 1; i < certData.length; i++)
                hashCode += certData[i] * i;

            return hashCode;
        }
        catch(CertificateEncodingException e)
        {
            return 0;
        }
    }

    public void setBagAttribute(ASN1ObjectIdentifier oid, ASN1Encodable attribute)
    {
        attrCarrier.setBagAttribute(oid, attribute);
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier oid)
    {
        return attrCarrier.getBagAttribute(oid);
    }

    public Enumeration getBagAttributeKeys()
    {
        return attrCarrier.getBagAttributeKeys();
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("  [0]         Version: ").append(getVersion()).append(nl);
        buf.append("         SerialNumber: ").append(getSerialNumber()).append(nl);
        buf.append("             IssuerDN: ").append(getIssuerDN()).append(nl);
        buf.append("           Start Date: ").append(getNotBefore()).append(nl);
        buf.append("           Final Date: ").append(getNotAfter()).append(nl);
        buf.append("            SubjectDN: ").append(getSubjectDN()).append(nl);
        buf.append("           Public Key: ").append(getPublicKey()).append(nl);
        buf.append("  Signature Algorithm: ").append(getSigAlgName()).append(nl);
        byte sig[] = getSignature();
        buf.append("            Signature: ").append(new String(Hex.encode(sig, 0, 20))).append(nl);
        for(int i = 20; i < sig.length; i += 20)
            if(i < sig.length - 20)
                buf.append("                       ").append(new String(Hex.encode(sig, i, 20))).append(nl);
            else
                buf.append("                       ").append(new String(Hex.encode(sig, i, sig.length - i))).append(nl);

        Extensions extensions = c.getTBSCertificate().getExtensions();
        if(extensions != null)
        {
            Enumeration e = extensions.oids();
            if(e.hasMoreElements())
                buf.append("       Extensions: \n");
            while(e.hasMoreElements()) 
            {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
                Extension ext = extensions.getExtension(oid);
                if(ext.getExtnValue() != null)
                {
                    byte octs[] = ext.getExtnValue().getOctets();
                    ASN1InputStream dIn = new ASN1InputStream(octs);
                    buf.append("                       critical(").append(ext.isCritical()).append(") ");
                    try
                    {
                        if(oid.equals(Extension.basicConstraints))
                            buf.append(BasicConstraints.getInstance(dIn.readObject())).append(nl);
                        else
                        if(oid.equals(Extension.keyUsage))
                            buf.append(KeyUsage.getInstance(dIn.readObject())).append(nl);
                        else
                        if(oid.equals(MiscObjectIdentifiers.netscapeCertType))
                            buf.append(new NetscapeCertType((DERBitString)dIn.readObject())).append(nl);
                        else
                        if(oid.equals(MiscObjectIdentifiers.netscapeRevocationURL))
                            buf.append(new NetscapeRevocationURL((DERIA5String)dIn.readObject())).append(nl);
                        else
                        if(oid.equals(MiscObjectIdentifiers.verisignCzagExtension))
                        {
                            buf.append(new VerisignCzagExtension((DERIA5String)dIn.readObject())).append(nl);
                        } else
                        {
                            buf.append(oid.getId());
                            buf.append(" value = ").append(ASN1Dump.dumpAsString(dIn.readObject())).append(nl);
                        }
                    }
                    catch(Exception ex)
                    {
                        buf.append(oid.getId());
                        buf.append(" value = ").append("*****").append(nl);
                    }
                } else
                {
                    buf.append(nl);
                }
            }
        }
        return buf.toString();
    }

    public final void verify(PublicKey key)
        throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
    {
        String sigName = X509SignatureUtil.getSignatureName(c.getSignatureAlgorithm());
        Signature signature;
        try
        {
            signature = Signature.getInstance(sigName, "BC");
        }
        catch(Exception e)
        {
            signature = Signature.getInstance(sigName);
        }
        checkSignature(key, signature);
    }

    public final void verify(PublicKey key, String sigProvider)
        throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException
    {
        String sigName = X509SignatureUtil.getSignatureName(c.getSignatureAlgorithm());
        Signature signature = Signature.getInstance(sigName, sigProvider);
        checkSignature(key, signature);
    }

    private void checkSignature(PublicKey key, Signature signature)
        throws CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        if(!isAlgIdEqual(c.getSignatureAlgorithm(), c.getTBSCertificate().getSignature()))
            throw new CertificateException("signature algorithm in TBS cert not same as outer cert");
        ASN1Encodable params = c.getSignatureAlgorithm().getParameters();
        X509SignatureUtil.setSignatureParameters(signature, params);
        signature.initVerify(key);
        signature.update(getTBSCertificate());
        if(!signature.verify(getSignature()))
            throw new SignatureException("certificate does not verify with supplied key");
        else
            return;
    }

    private boolean isAlgIdEqual(AlgorithmIdentifier id1, AlgorithmIdentifier id2)
    {
        if(!id1.getAlgorithm().equals(id2.getAlgorithm()))
            return false;
        if(id1.getParameters() == null)
            return id2.getParameters() == null || id2.getParameters().equals(DERNull.INSTANCE);
        if(id2.getParameters() == null)
            return id1.getParameters() == null || id1.getParameters().equals(DERNull.INSTANCE);
        else
            return id1.getParameters().equals(id2.getParameters());
    }

    private static Collection getAlternativeNames(byte extVal[])
        throws CertificateParsingException
    {
        if(extVal == null)
            return null;
        Collection temp;
        try
        {
            temp = new ArrayList();
            Enumeration it = ASN1Sequence.getInstance(extVal).getObjects();
            do
            {
                if(!it.hasMoreElements())
                    break;
                GeneralName genName = GeneralName.getInstance(it.nextElement());
                List list = new ArrayList();
                list.add(Integers.valueOf(genName.getTagNo()));
                switch(genName.getTagNo())
                {
                case 0: // '\0'
                case 3: // '\003'
                case 5: // '\005'
                    list.add(genName.getEncoded());
                    break;

                case 4: // '\004'
                    list.add(X500Name.getInstance(RFC4519Style.INSTANCE, genName.getName()).toString());
                    break;

                case 1: // '\001'
                case 2: // '\002'
                case 6: // '\006'
                    list.add(((ASN1String)genName.getName()).getString());
                    break;

                case 8: // '\b'
                    list.add(ASN1ObjectIdentifier.getInstance(genName.getName()).getId());
                    break;

                case 7: // '\007'
                    byte addrBytes[] = DEROctetString.getInstance(genName.getName()).getOctets();
                    String addr;
                    try
                    {
                        addr = InetAddress.getByAddress(addrBytes).getHostAddress();
                    }
                    catch(UnknownHostException e)
                    {
                        continue;
                    }
                    list.add(addr);
                    break;

                default:
                    throw new IOException((new StringBuilder()).append("Bad tag number: ").append(genName.getTagNo()).toString());
                }
                temp.add(Collections.unmodifiableList(list));
            } while(true);
            if(temp.size() == 0)
                return null;
        }
        catch(Exception e)
        {
            throw new CertificateParsingException(e.getMessage());
        }
        return Collections.unmodifiableCollection(temp);
    }

    private co.org.bouncy.asn1.x509.Certificate c;
    private BasicConstraints basicConstraints;
    private boolean keyUsage[];
    private boolean hashValueSet;
    private int hashValue;
    private PKCS12BagAttributeCarrier attrCarrier;
}
