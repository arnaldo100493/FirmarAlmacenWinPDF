// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertPathValidatorUtilities.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.ASN1OutputStream;
import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DEREnumerated;
import co.org.bouncy.asn1.DERGeneralizedTime;
import co.org.bouncy.asn1.DERIA5String;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.DERSequence;
import co.org.bouncy.asn1.isismtt.ISISMTTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.CRLDistPoint;
import co.org.bouncy.asn1.x509.DistributionPoint;
import co.org.bouncy.asn1.x509.DistributionPointName;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.GeneralNames;
import co.org.bouncy.asn1.x509.PolicyInformation;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jce.X509LDAPCertStoreParameters;
import co.org.bouncy.jce.exception.ExtCertPathValidatorException;
import co.org.bouncy.util.Integers;
import co.org.bouncy.util.StoreException;
import co.org.bouncy.x509_.AttributeCertificateIssuer;
import co.org.bouncy.x509_.ExtendedPKIXBuilderParameters;
import co.org.bouncy.x509_.ExtendedPKIXParameters;
import co.org.bouncy.x509_.X509AttributeCertStoreSelector;
import co.org.bouncy.x509_.X509AttributeCertificate;
import co.org.bouncy.x509_.X509CRLStoreSelector;
import co.org.bouncy.x509_.X509CertStoreSelector;
import co.org.bouncy.x509_.X509Store;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyQualifierInfo;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.jce.provider:
//            AnnotatedException, PKIXPolicyNode, PKIXCRLUtil, CertStatus, 
//            X509CRLObject, RFC3280CertPathUtilities

public class CertPathValidatorUtilities
{

    public CertPathValidatorUtilities()
    {
    }

    protected static TrustAnchor findTrustAnchor(X509Certificate cert, Set trustAnchors)
        throws AnnotatedException
    {
        return findTrustAnchor(cert, trustAnchors, null);
    }

    protected static TrustAnchor findTrustAnchor(X509Certificate cert, Set trustAnchors, String sigProvider)
        throws AnnotatedException
    {
        TrustAnchor trust = null;
        PublicKey trustPublicKey = null;
        Exception invalidKeyEx = null;
        X509CertSelector certSelectX509 = new X509CertSelector();
        X500Principal certIssuer = getEncodedIssuerPrincipal(cert);
        try
        {
            certSelectX509.setSubject(certIssuer.getEncoded());
        }
        catch(IOException ex)
        {
            throw new AnnotatedException("Cannot set subject search criteria for trust anchor.", ex);
        }
        Iterator iter = trustAnchors.iterator();
        do
        {
            if(!iter.hasNext() || trust != null)
                break;
            trust = (TrustAnchor)iter.next();
            if(trust.getTrustedCert() != null)
            {
                if(certSelectX509.match(trust.getTrustedCert()))
                    trustPublicKey = trust.getTrustedCert().getPublicKey();
                else
                    trust = null;
            } else
            if(trust.getCAName() != null && trust.getCAPublicKey() != null)
                try
                {
                    X500Principal caName = new X500Principal(trust.getCAName());
                    if(certIssuer.equals(caName))
                        trustPublicKey = trust.getCAPublicKey();
                    else
                        trust = null;
                }
                catch(IllegalArgumentException ex)
                {
                    trust = null;
                }
            else
                trust = null;
            if(trustPublicKey != null)
                try
                {
                    verifyX509Certificate(cert, trustPublicKey, sigProvider);
                }
                catch(Exception ex)
                {
                    invalidKeyEx = ex;
                    trust = null;
                    trustPublicKey = null;
                }
        } while(true);
        if(trust == null && invalidKeyEx != null)
            throw new AnnotatedException("TrustAnchor found but certificate validation failed.", invalidKeyEx);
        else
            return trust;
    }

    protected static void addAdditionalStoresFromAltNames(X509Certificate cert, ExtendedPKIXParameters pkixParams)
        throws CertificateParsingException
    {
        if(cert.getIssuerAlternativeNames() != null)
        {
            Iterator it = cert.getIssuerAlternativeNames().iterator();
            do
            {
                if(!it.hasNext())
                    break;
                List list = (List)it.next();
                if(list.get(0).equals(Integers.valueOf(6)))
                {
                    String temp = (String)list.get(1);
                    addAdditionalStoreFromLocation(temp, pkixParams);
                }
            } while(true);
        }
    }

    protected static X500Principal getEncodedIssuerPrincipal(Object cert)
    {
        if(cert instanceof X509Certificate)
            return ((X509Certificate)cert).getIssuerX500Principal();
        else
            return (X500Principal)((X509AttributeCertificate)cert).getIssuer().getPrincipals()[0];
    }

    protected static Date getValidDate(PKIXParameters paramsPKIX)
    {
        Date validDate = paramsPKIX.getDate();
        if(validDate == null)
            validDate = new Date();
        return validDate;
    }

    protected static X500Principal getSubjectPrincipal(X509Certificate cert)
    {
        return cert.getSubjectX500Principal();
    }

    protected static boolean isSelfIssued(X509Certificate cert)
    {
        return cert.getSubjectDN().equals(cert.getIssuerDN());
    }

    protected static ASN1Primitive getExtensionValue(X509Extension ext, String oid)
        throws AnnotatedException
    {
        byte bytes[] = ext.getExtensionValue(oid);
        if(bytes == null)
            return null;
        else
            return getObject(oid, bytes);
    }

    private static ASN1Primitive getObject(String oid, byte ext[])
        throws AnnotatedException
    {
        try
        {
            ASN1InputStream aIn = new ASN1InputStream(ext);
            ASN1OctetString octs = (ASN1OctetString)aIn.readObject();
            aIn = new ASN1InputStream(octs.getOctets());
            return aIn.readObject();
        }
        catch(Exception e)
        {
            throw new AnnotatedException((new StringBuilder()).append("exception processing extension ").append(oid).toString(), e);
        }
    }

    protected static X500Principal getIssuerPrincipal(X509CRL crl)
    {
        return crl.getIssuerX500Principal();
    }

    protected static AlgorithmIdentifier getAlgorithmIdentifier(PublicKey key)
        throws CertPathValidatorException
    {
        try
        {
            ASN1InputStream aIn = new ASN1InputStream(key.getEncoded());
            SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(aIn.readObject());
            return info.getAlgorithmId();
        }
        catch(Exception e)
        {
            throw new ExtCertPathValidatorException("Subject public key cannot be decoded.", e);
        }
    }

    protected static final Set getQualifierSet(ASN1Sequence qualifiers)
        throws CertPathValidatorException
    {
        Set pq = new HashSet();
        if(qualifiers == null)
            return pq;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        for(Enumeration e = qualifiers.getObjects(); e.hasMoreElements(); bOut.reset())
            try
            {
                aOut.writeObject((ASN1Encodable)e.nextElement());
                pq.add(new PolicyQualifierInfo(bOut.toByteArray()));
            }
            catch(IOException ex)
            {
                throw new ExtCertPathValidatorException("Policy qualifier info cannot be decoded.", ex);
            }

        return pq;
    }

    protected static PKIXPolicyNode removePolicyNode(PKIXPolicyNode validPolicyTree, List policyNodes[], PKIXPolicyNode _node)
    {
        PKIXPolicyNode _parent = (PKIXPolicyNode)_node.getParent();
        if(validPolicyTree == null)
            return null;
        if(_parent == null)
        {
            for(int j = 0; j < policyNodes.length; j++)
                policyNodes[j] = new ArrayList();

            return null;
        } else
        {
            _parent.removeChild(_node);
            removePolicyNodeRecurse(policyNodes, _node);
            return validPolicyTree;
        }
    }

    private static void removePolicyNodeRecurse(List policyNodes[], PKIXPolicyNode _node)
    {
        policyNodes[_node.getDepth()].remove(_node);
        if(_node.hasChildren())
        {
            PKIXPolicyNode _child;
            for(Iterator _iter = _node.getChildren(); _iter.hasNext(); removePolicyNodeRecurse(policyNodes, _child))
                _child = (PKIXPolicyNode)_iter.next();

        }
    }

    protected static boolean processCertD1i(int index, List policyNodes[], DERObjectIdentifier pOid, Set pq)
    {
        List policyNodeVec = policyNodes[index - 1];
        for(int j = 0; j < policyNodeVec.size(); j++)
        {
            PKIXPolicyNode node = (PKIXPolicyNode)policyNodeVec.get(j);
            Set expectedPolicies = node.getExpectedPolicies();
            if(expectedPolicies.contains(pOid.getId()))
            {
                Set childExpectedPolicies = new HashSet();
                childExpectedPolicies.add(pOid.getId());
                PKIXPolicyNode child = new PKIXPolicyNode(new ArrayList(), index, childExpectedPolicies, node, pq, pOid.getId(), false);
                node.addChild(child);
                policyNodes[index].add(child);
                return true;
            }
        }

        return false;
    }

    protected static void processCertD1ii(int index, List policyNodes[], DERObjectIdentifier _poid, Set _pq)
    {
        List policyNodeVec = policyNodes[index - 1];
        for(int j = 0; j < policyNodeVec.size(); j++)
        {
            PKIXPolicyNode _node = (PKIXPolicyNode)policyNodeVec.get(j);
            if("2.5.29.32.0".equals(_node.getValidPolicy()))
            {
                Set _childExpectedPolicies = new HashSet();
                _childExpectedPolicies.add(_poid.getId());
                PKIXPolicyNode _child = new PKIXPolicyNode(new ArrayList(), index, _childExpectedPolicies, _node, _pq, _poid.getId(), false);
                _node.addChild(_child);
                policyNodes[index].add(_child);
                return;
            }
        }

    }

    protected static void prepareNextCertB1(int i, List policyNodes[], String id_p, Map m_idp, X509Certificate cert)
        throws AnnotatedException, CertPathValidatorException
    {
label0:
        {
            boolean idp_found = false;
            Iterator nodes_i = policyNodes[i].iterator();
            PKIXPolicyNode node;
            do
            {
                if(!nodes_i.hasNext())
                    break;
                node = (PKIXPolicyNode)nodes_i.next();
                if(!node.getValidPolicy().equals(id_p))
                    continue;
                idp_found = true;
                node.expectedPolicies = (Set)m_idp.get(id_p);
                break;
            } while(true);
            if(idp_found)
                break label0;
            nodes_i = policyNodes[i].iterator();
            do
            {
                if(!nodes_i.hasNext())
                    break label0;
                node = (PKIXPolicyNode)nodes_i.next();
            } while(!"2.5.29.32.0".equals(node.getValidPolicy()));
            Set pq = null;
            ASN1Sequence policies = null;
            try
            {
                policies = DERSequence.getInstance(getExtensionValue(cert, CERTIFICATE_POLICIES));
            }
            catch(Exception e)
            {
                throw new AnnotatedException("Certificate policies cannot be decoded.", e);
            }
            Enumeration e = policies.getObjects();
            do
            {
                if(!e.hasMoreElements())
                    break;
                PolicyInformation pinfo = null;
                try
                {
                    pinfo = PolicyInformation.getInstance(e.nextElement());
                }
                catch(Exception ex)
                {
                    throw new AnnotatedException("Policy information cannot be decoded.", ex);
                }
                if(!"2.5.29.32.0".equals(pinfo.getPolicyIdentifier().getId()))
                    continue;
                try
                {
                    pq = getQualifierSet(pinfo.getPolicyQualifiers());
                }
                catch(CertPathValidatorException ex)
                {
                    throw new ExtCertPathValidatorException("Policy qualifier info set could not be built.", ex);
                }
                break;
            } while(true);
            boolean ci = false;
            if(cert.getCriticalExtensionOIDs() != null)
                ci = cert.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES);
            PKIXPolicyNode p_node = (PKIXPolicyNode)node.getParent();
            if("2.5.29.32.0".equals(p_node.getValidPolicy()))
            {
                PKIXPolicyNode c_node = new PKIXPolicyNode(new ArrayList(), i, (Set)m_idp.get(id_p), p_node, pq, id_p, ci);
                p_node.addChild(c_node);
                policyNodes[i].add(c_node);
            }
        }
    }

    protected static PKIXPolicyNode prepareNextCertB2(int i, List policyNodes[], String id_p, PKIXPolicyNode validPolicyTree)
    {
        Iterator nodes_i = policyNodes[i].iterator();
        do
        {
            if(!nodes_i.hasNext())
                break;
            PKIXPolicyNode node = (PKIXPolicyNode)nodes_i.next();
            if(node.getValidPolicy().equals(id_p))
            {
                PKIXPolicyNode p_node = (PKIXPolicyNode)node.getParent();
                p_node.removeChild(node);
                nodes_i.remove();
                int k = i - 1;
                while(k >= 0) 
                {
                    List nodes = policyNodes[k];
                    for(int l = 0; l < nodes.size(); l++)
                    {
                        PKIXPolicyNode node2 = (PKIXPolicyNode)nodes.get(l);
                        if(node2.hasChildren())
                            continue;
                        validPolicyTree = removePolicyNode(validPolicyTree, policyNodes, node2);
                        if(validPolicyTree == null)
                            break;
                    }

                    k--;
                }
            }
        } while(true);
        return validPolicyTree;
    }

    protected static boolean isAnyPolicy(Set policySet)
    {
        return policySet == null || policySet.contains("2.5.29.32.0") || policySet.isEmpty();
    }

    protected static void addAdditionalStoreFromLocation(String location, ExtendedPKIXParameters pkixParams)
    {
        if(pkixParams.isAdditionalLocationsEnabled())
            try
            {
                if(location.startsWith("ldap://"))
                {
                    location = location.substring(7);
                    String base = null;
                    String url = null;
                    if(location.indexOf("/") != -1)
                    {
                        base = location.substring(location.indexOf("/"));
                        url = (new StringBuilder()).append("ldap://").append(location.substring(0, location.indexOf("/"))).toString();
                    } else
                    {
                        url = (new StringBuilder()).append("ldap://").append(location).toString();
                    }
                    X509LDAPCertStoreParameters params = (new co.org.bouncy.jce.X509LDAPCertStoreParameters.Builder(url, base)).build();
                    pkixParams.addAdditionalStore(X509Store.getInstance("CERTIFICATE/LDAP", params, "BC"));
                    pkixParams.addAdditionalStore(X509Store.getInstance("CRL/LDAP", params, "BC"));
                    pkixParams.addAdditionalStore(X509Store.getInstance("ATTRIBUTECERTIFICATE/LDAP", params, "BC"));
                    pkixParams.addAdditionalStore(X509Store.getInstance("CERTIFICATEPAIR/LDAP", params, "BC"));
                }
            }
            catch(Exception e)
            {
                throw new RuntimeException("Exception adding X.509 stores.");
            }
    }

    protected static Collection findCertificates(X509CertStoreSelector certSelect, List certStores)
        throws AnnotatedException
    {
        Set certs = new HashSet();
        for(Iterator iter = certStores.iterator(); iter.hasNext();)
        {
            Object obj = iter.next();
            if(obj instanceof X509Store)
            {
                X509Store certStore = (X509Store)obj;
                try
                {
                    certs.addAll(certStore.getMatches(certSelect));
                }
                catch(StoreException e)
                {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            } else
            {
                CertStore certStore = (CertStore)obj;
                try
                {
                    certs.addAll(certStore.getCertificates(certSelect));
                }
                catch(CertStoreException e)
                {
                    throw new AnnotatedException("Problem while picking certificates from certificate store.", e);
                }
            }
        }

        return certs;
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector certSelect, List certStores)
        throws AnnotatedException
    {
        Set certs = new HashSet();
        Iterator iter = certStores.iterator();
        do
        {
            if(!iter.hasNext())
                break;
            Object obj = iter.next();
            if(obj instanceof X509Store)
            {
                X509Store certStore = (X509Store)obj;
                try
                {
                    certs.addAll(certStore.getMatches(certSelect));
                }
                catch(StoreException e)
                {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
        } while(true);
        return certs;
    }

    protected static void addAdditionalStoresFromCRLDistributionPoint(CRLDistPoint crldp, ExtendedPKIXParameters pkixParams)
        throws AnnotatedException
    {
        if(crldp != null)
        {
            DistributionPoint dps[] = null;
            try
            {
                dps = crldp.getDistributionPoints();
            }
            catch(Exception e)
            {
                throw new AnnotatedException("Distribution points could not be read.", e);
            }
            for(int i = 0; i < dps.length; i++)
            {
                DistributionPointName dpn = dps[i].getDistributionPoint();
                if(dpn == null || dpn.getType() != 0)
                    continue;
                GeneralName genNames[] = GeneralNames.getInstance(dpn.getName()).getNames();
                for(int j = 0; j < genNames.length; j++)
                    if(genNames[j].getTagNo() == 6)
                    {
                        String location = DERIA5String.getInstance(genNames[j].getName()).getString();
                        addAdditionalStoreFromLocation(location, pkixParams);
                    }

            }

        }
    }

    protected static void getCRLIssuersFromDistributionPoint(DistributionPoint dp, Collection issuerPrincipals, X509CRLSelector selector, ExtendedPKIXParameters pkixParams)
        throws AnnotatedException
    {
        List issuers = new ArrayList();
        if(dp.getCRLIssuer() != null)
        {
            GeneralName genNames[] = dp.getCRLIssuer().getNames();
            for(int j = 0; j < genNames.length; j++)
                if(genNames[j].getTagNo() == 4)
                    try
                    {
                        issuers.add(new X500Principal(genNames[j].getName().toASN1Primitive().getEncoded()));
                    }
                    catch(IOException e)
                    {
                        throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", e);
                    }

        } else
        {
            if(dp.getDistributionPoint() == null)
                throw new AnnotatedException("CRL issuer is omitted from distribution point but no distributionPoint field present.");
            for(Iterator it = issuerPrincipals.iterator(); it.hasNext(); issuers.add((X500Principal)it.next()));
        }
        for(Iterator it = issuers.iterator(); it.hasNext();)
            try
            {
                selector.addIssuerName(((X500Principal)it.next()).getEncoded());
            }
            catch(IOException ex)
            {
                throw new AnnotatedException("Cannot decode CRL issuer information.", ex);
            }

    }

    private static BigInteger getSerialNumber(Object cert)
    {
        if(cert instanceof X509Certificate)
            return ((X509Certificate)cert).getSerialNumber();
        else
            return ((X509AttributeCertificate)cert).getSerialNumber();
    }

    protected static void getCertStatus(Date validDate, X509CRL crl, Object cert, CertStatus certStatus)
        throws AnnotatedException
    {
        X509CRLEntry crl_entry = null;
        boolean isIndirect;
        try
        {
            isIndirect = X509CRLObject.isIndirectCRL(crl);
        }
        catch(CRLException exception)
        {
            throw new AnnotatedException("Failed check for indirect CRL.", exception);
        }
        if(isIndirect)
        {
            crl_entry = crl.getRevokedCertificate(getSerialNumber(cert));
            if(crl_entry == null)
                return;
            X500Principal certIssuer = crl_entry.getCertificateIssuer();
            if(certIssuer == null)
                certIssuer = getIssuerPrincipal(crl);
            if(!getEncodedIssuerPrincipal(cert).equals(certIssuer))
                return;
        } else
        {
            if(!getEncodedIssuerPrincipal(cert).equals(getIssuerPrincipal(crl)))
                return;
            crl_entry = crl.getRevokedCertificate(getSerialNumber(cert));
            if(crl_entry == null)
                return;
        }
        DEREnumerated reasonCode = null;
        if(crl_entry.hasExtensions())
            try
            {
                reasonCode = DEREnumerated.getInstance(getExtensionValue(crl_entry, co.org.bouncy.asn1.x509.X509Extension.reasonCode.getId()));
            }
            catch(Exception e)
            {
                throw new AnnotatedException("Reason code CRL entry extension could not be decoded.", e);
            }
        if(validDate.getTime() >= crl_entry.getRevocationDate().getTime() || reasonCode == null || reasonCode.getValue().intValue() == 0 || reasonCode.getValue().intValue() == 1 || reasonCode.getValue().intValue() == 2 || reasonCode.getValue().intValue() == 8)
        {
            if(reasonCode != null)
                certStatus.setCertStatus(reasonCode.getValue().intValue());
            else
                certStatus.setCertStatus(0);
            certStatus.setRevocationDate(crl_entry.getRevocationDate());
        }
    }

    protected static Set getDeltaCRLs(Date currentDate, ExtendedPKIXParameters paramsPKIX, X509CRL completeCRL)
        throws AnnotatedException
    {
        X509CRLStoreSelector deltaSelect = new X509CRLStoreSelector();
        try
        {
            deltaSelect.addIssuerName(getIssuerPrincipal(completeCRL).getEncoded());
        }
        catch(IOException e)
        {
            throw new AnnotatedException("Cannot extract issuer from CRL.", e);
        }
        BigInteger completeCRLNumber = null;
        try
        {
            ASN1Primitive derObject = getExtensionValue(completeCRL, CRL_NUMBER);
            if(derObject != null)
                completeCRLNumber = ASN1Integer.getInstance(derObject).getPositiveValue();
        }
        catch(Exception e)
        {
            throw new AnnotatedException("CRL number extension could not be extracted from CRL.", e);
        }
        byte idp[] = null;
        try
        {
            idp = completeCRL.getExtensionValue(ISSUING_DISTRIBUTION_POINT);
        }
        catch(Exception e)
        {
            throw new AnnotatedException("Issuing distribution point extension value could not be read.", e);
        }
        deltaSelect.setMinCRLNumber(completeCRLNumber != null ? completeCRLNumber.add(BigInteger.valueOf(1L)) : null);
        deltaSelect.setIssuingDistributionPoint(idp);
        deltaSelect.setIssuingDistributionPointEnabled(true);
        deltaSelect.setMaxBaseCRLNumber(completeCRLNumber);
        Set temp = CRL_UTIL.findCRLs(deltaSelect, paramsPKIX, currentDate);
        Set result = new HashSet();
        Iterator it = temp.iterator();
        do
        {
            if(!it.hasNext())
                break;
            X509CRL crl = (X509CRL)it.next();
            if(isDeltaCRL(crl))
                result.add(crl);
        } while(true);
        return result;
    }

    private static boolean isDeltaCRL(X509CRL crl)
    {
        Set critical = crl.getCriticalExtensionOIDs();
        if(critical == null)
            return false;
        else
            return critical.contains(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
    }

    protected static Set getCompleteCRLs(DistributionPoint dp, Object cert, Date currentDate, ExtendedPKIXParameters paramsPKIX)
        throws AnnotatedException
    {
        X509CRLStoreSelector crlselect = new X509CRLStoreSelector();
        try
        {
            Set issuers = new HashSet();
            if(cert instanceof X509AttributeCertificate)
                issuers.add(((X509AttributeCertificate)cert).getIssuer().getPrincipals()[0]);
            else
                issuers.add(getEncodedIssuerPrincipal(cert));
            getCRLIssuersFromDistributionPoint(dp, issuers, crlselect, paramsPKIX);
        }
        catch(AnnotatedException e)
        {
            throw new AnnotatedException("Could not get issuer information from distribution point.", e);
        }
        if(cert instanceof X509Certificate)
            crlselect.setCertificateChecking((X509Certificate)cert);
        else
        if(cert instanceof X509AttributeCertificate)
            crlselect.setAttrCertificateChecking((X509AttributeCertificate)cert);
        crlselect.setCompleteCRLEnabled(true);
        Set crls = CRL_UTIL.findCRLs(crlselect, paramsPKIX, currentDate);
        if(crls.isEmpty())
        {
            if(cert instanceof X509AttributeCertificate)
            {
                X509AttributeCertificate aCert = (X509AttributeCertificate)cert;
                throw new AnnotatedException((new StringBuilder()).append("No CRLs found for issuer \"").append(aCert.getIssuer().getPrincipals()[0]).append("\"").toString());
            } else
            {
                X509Certificate xCert = (X509Certificate)cert;
                throw new AnnotatedException((new StringBuilder()).append("No CRLs found for issuer \"").append(xCert.getIssuerX500Principal()).append("\"").toString());
            }
        } else
        {
            return crls;
        }
    }

    protected static Date getValidCertDateFromValidityModel(ExtendedPKIXParameters paramsPKIX, CertPath certPath, int index)
        throws AnnotatedException
    {
        if(paramsPKIX.getValidityModel() == 1)
        {
            if(index <= 0)
                return getValidDate(paramsPKIX);
            if(index - 1 == 0)
            {
                DERGeneralizedTime dateOfCertgen = null;
                try
                {
                    byte extBytes[] = ((X509Certificate)certPath.getCertificates().get(index - 1)).getExtensionValue(ISISMTTObjectIdentifiers.id_isismtt_at_dateOfCertGen.getId());
                    if(extBytes != null)
                        dateOfCertgen = DERGeneralizedTime.getInstance(ASN1Primitive.fromByteArray(extBytes));
                }
                catch(IOException e)
                {
                    throw new AnnotatedException("Date of cert gen extension could not be read.");
                }
                catch(IllegalArgumentException e)
                {
                    throw new AnnotatedException("Date of cert gen extension could not be read.");
                }
                if(dateOfCertgen != null)
                    try
                    {
                        return dateOfCertgen.getDate();
                    }
                    catch(ParseException e)
                    {
                        throw new AnnotatedException("Date from date of cert gen extension could not be parsed.", e);
                    }
                else
                    return ((X509Certificate)certPath.getCertificates().get(index - 1)).getNotBefore();
            } else
            {
                return ((X509Certificate)certPath.getCertificates().get(index - 1)).getNotBefore();
            }
        } else
        {
            return getValidDate(paramsPKIX);
        }
    }

    protected static PublicKey getNextWorkingKey(List certs, int index)
        throws CertPathValidatorException
    {
        Certificate cert = (Certificate)certs.get(index);
        PublicKey pubKey = cert.getPublicKey();
        if(!(pubKey instanceof DSAPublicKey))
            return pubKey;
        DSAPublicKey dsaPubKey = (DSAPublicKey)pubKey;
        if(dsaPubKey.getParams() != null)
            return dsaPubKey;
        for(int i = index + 1; i < certs.size(); i++)
        {
            X509Certificate parentCert = (X509Certificate)certs.get(i);
            pubKey = parentCert.getPublicKey();
            if(!(pubKey instanceof DSAPublicKey))
                throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
            DSAPublicKey prevDSAPubKey = (DSAPublicKey)pubKey;
            if(prevDSAPubKey.getParams() != null)
            {
                DSAParams dsaParams = prevDSAPubKey.getParams();
                DSAPublicKeySpec dsaPubKeySpec = new DSAPublicKeySpec(dsaPubKey.getY(), dsaParams.getP(), dsaParams.getQ(), dsaParams.getG());
                try
                {
                    KeyFactory keyFactory = KeyFactory.getInstance("DSA", "BC");
                    return keyFactory.generatePublic(dsaPubKeySpec);
                }
                catch(Exception exception)
                {
                    throw new RuntimeException(exception.getMessage());
                }
            }
        }

        throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
    }

    protected static Collection findIssuerCerts(X509Certificate cert, ExtendedPKIXBuilderParameters pkixParams)
        throws AnnotatedException
    {
        X509CertStoreSelector certSelect = new X509CertStoreSelector();
        Set certs = new HashSet();
        try
        {
            certSelect.setSubject(cert.getIssuerX500Principal().getEncoded());
        }
        catch(IOException ex)
        {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate could not be set.", ex);
        }
        Iterator iter;
        try
        {
            List matches = new ArrayList();
            matches.addAll(findCertificates(certSelect, pkixParams.getCertStores()));
            matches.addAll(findCertificates(certSelect, pkixParams.getStores()));
            matches.addAll(findCertificates(certSelect, pkixParams.getAdditionalStores()));
            iter = matches.iterator();
        }
        catch(AnnotatedException e)
        {
            throw new AnnotatedException("Issuer certificate cannot be searched.", e);
        }
        X509Certificate issuer = null;
        for(; iter.hasNext(); certs.add(issuer))
            issuer = (X509Certificate)iter.next();

        return certs;
    }

    protected static void verifyX509Certificate(X509Certificate cert, PublicKey publicKey, String sigProvider)
        throws GeneralSecurityException
    {
        if(sigProvider == null)
            cert.verify(publicKey);
        else
            cert.verify(publicKey, sigProvider);
    }

    protected static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    protected static final String CERTIFICATE_POLICIES;
    protected static final String BASIC_CONSTRAINTS;
    protected static final String POLICY_MAPPINGS;
    protected static final String SUBJECT_ALTERNATIVE_NAME;
    protected static final String NAME_CONSTRAINTS;
    protected static final String KEY_USAGE;
    protected static final String INHIBIT_ANY_POLICY;
    protected static final String ISSUING_DISTRIBUTION_POINT;
    protected static final String DELTA_CRL_INDICATOR;
    protected static final String POLICY_CONSTRAINTS;
    protected static final String FRESHEST_CRL;
    protected static final String CRL_DISTRIBUTION_POINTS;
    protected static final String AUTHORITY_KEY_IDENTIFIER;
    protected static final String ANY_POLICY = "2.5.29.32.0";
    protected static final String CRL_NUMBER;
    protected static final int KEY_CERT_SIGN = 5;
    protected static final int CRL_SIGN = 6;
    protected static final String crlReasons[] = {
        "unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", 
        "aACompromise"
    };

    static 
    {
        CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
        BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
        POLICY_MAPPINGS = Extension.policyMappings.getId();
        SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
        NAME_CONSTRAINTS = Extension.nameConstraints.getId();
        KEY_USAGE = Extension.keyUsage.getId();
        INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
        ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
        DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
        POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
        FRESHEST_CRL = Extension.freshestCRL.getId();
        CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
        AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
        CRL_NUMBER = Extension.cRLNumber.getId();
    }
}
