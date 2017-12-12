// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtendedPKIXParameters.java

package co.org.bouncy.x509_;

import co.org.bouncy.util.Selector;
import co.org.bouncy.util.Store;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.x509_:
//            PKIXAttrCertChecker, X509CertStoreSelector

public class ExtendedPKIXParameters extends PKIXParameters
{

    public ExtendedPKIXParameters(Set trustAnchors)
        throws InvalidAlgorithmParameterException
    {
        super(trustAnchors);
        validityModel = 0;
        useDeltas = false;
        stores = new ArrayList();
        additionalStores = new ArrayList();
        trustedACIssuers = new HashSet();
        necessaryACAttributes = new HashSet();
        prohibitedACAttributes = new HashSet();
        attrCertCheckers = new HashSet();
    }

    public static ExtendedPKIXParameters getInstance(PKIXParameters pkixParams)
    {
        ExtendedPKIXParameters params;
        try
        {
            params = new ExtendedPKIXParameters(pkixParams.getTrustAnchors());
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        params.setParams(pkixParams);
        return params;
    }

    protected void setParams(PKIXParameters params)
    {
        setDate(params.getDate());
        setCertPathCheckers(params.getCertPathCheckers());
        setCertStores(params.getCertStores());
        setAnyPolicyInhibited(params.isAnyPolicyInhibited());
        setExplicitPolicyRequired(params.isExplicitPolicyRequired());
        setPolicyMappingInhibited(params.isPolicyMappingInhibited());
        setRevocationEnabled(params.isRevocationEnabled());
        setInitialPolicies(params.getInitialPolicies());
        setPolicyQualifiersRejected(params.getPolicyQualifiersRejected());
        setSigProvider(params.getSigProvider());
        setTargetCertConstraints(params.getTargetCertConstraints());
        try
        {
            setTrustAnchors(params.getTrustAnchors());
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        if(params instanceof ExtendedPKIXParameters)
        {
            ExtendedPKIXParameters _params = (ExtendedPKIXParameters)params;
            validityModel = _params.validityModel;
            useDeltas = _params.useDeltas;
            additionalLocationsEnabled = _params.additionalLocationsEnabled;
            selector = _params.selector != null ? (Selector)_params.selector.clone() : null;
            stores = new ArrayList(_params.stores);
            additionalStores = new ArrayList(_params.additionalStores);
            trustedACIssuers = new HashSet(_params.trustedACIssuers);
            prohibitedACAttributes = new HashSet(_params.prohibitedACAttributes);
            necessaryACAttributes = new HashSet(_params.necessaryACAttributes);
            attrCertCheckers = new HashSet(_params.attrCertCheckers);
        }
    }

    public boolean isUseDeltasEnabled()
    {
        return useDeltas;
    }

    public void setUseDeltasEnabled(boolean useDeltas)
    {
        this.useDeltas = useDeltas;
    }

    public int getValidityModel()
    {
        return validityModel;
    }

    public void setCertStores(List stores)
    {
        if(stores != null)
        {
            for(Iterator it = stores.iterator(); it.hasNext(); addCertStore((CertStore)it.next()));
        }
    }

    public void setStores(List stores)
    {
        if(stores == null)
        {
            this.stores = new ArrayList();
        } else
        {
            for(Iterator i = stores.iterator(); i.hasNext();)
                if(!(i.next() instanceof Store))
                    throw new ClassCastException("All elements of list must be of type org.bouncy.util.Store.");

            this.stores = new ArrayList(stores);
        }
    }

    public void addStore(Store store)
    {
        if(store != null)
            stores.add(store);
    }

    public void addAdditionalStore(Store store)
    {
        if(store != null)
            additionalStores.add(store);
    }

    /**
     * @deprecated Method addAddionalStore is deprecated
     */

    public void addAddionalStore(Store store)
    {
        addAdditionalStore(store);
    }

    public List getAdditionalStores()
    {
        return Collections.unmodifiableList(additionalStores);
    }

    public List getStores()
    {
        return Collections.unmodifiableList(new ArrayList(stores));
    }

    public void setValidityModel(int validityModel)
    {
        this.validityModel = validityModel;
    }

    public Object clone()
    {
        ExtendedPKIXParameters params;
        try
        {
            params = new ExtendedPKIXParameters(getTrustAnchors());
        }
        catch(Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        params.setParams(this);
        return params;
    }

    public boolean isAdditionalLocationsEnabled()
    {
        return additionalLocationsEnabled;
    }

    public void setAdditionalLocationsEnabled(boolean enabled)
    {
        additionalLocationsEnabled = enabled;
    }

    public Selector getTargetConstraints()
    {
        if(selector != null)
            return (Selector)selector.clone();
        else
            return null;
    }

    public void setTargetConstraints(Selector selector)
    {
        if(selector != null)
            this.selector = (Selector)selector.clone();
        else
            this.selector = null;
    }

    public void setTargetCertConstraints(CertSelector selector)
    {
        super.setTargetCertConstraints(selector);
        if(selector != null)
            this.selector = X509CertStoreSelector.getInstance((X509CertSelector)selector);
        else
            this.selector = null;
    }

    public Set getTrustedACIssuers()
    {
        return Collections.unmodifiableSet(trustedACIssuers);
    }

    public void setTrustedACIssuers(Set trustedACIssuers)
    {
        if(trustedACIssuers == null)
        {
            this.trustedACIssuers.clear();
            return;
        }
        for(Iterator it = trustedACIssuers.iterator(); it.hasNext();)
            if(!(it.next() instanceof TrustAnchor))
                throw new ClassCastException((new StringBuilder()).append("All elements of set must be of type ").append(java/security/cert/TrustAnchor.getName()).append(".").toString());

        this.trustedACIssuers.clear();
        this.trustedACIssuers.addAll(trustedACIssuers);
    }

    public Set getNecessaryACAttributes()
    {
        return Collections.unmodifiableSet(necessaryACAttributes);
    }

    public void setNecessaryACAttributes(Set necessaryACAttributes)
    {
        if(necessaryACAttributes == null)
        {
            this.necessaryACAttributes.clear();
            return;
        }
        for(Iterator it = necessaryACAttributes.iterator(); it.hasNext();)
            if(!(it.next() instanceof String))
                throw new ClassCastException("All elements of set must be of type String.");

        this.necessaryACAttributes.clear();
        this.necessaryACAttributes.addAll(necessaryACAttributes);
    }

    public Set getProhibitedACAttributes()
    {
        return Collections.unmodifiableSet(prohibitedACAttributes);
    }

    public void setProhibitedACAttributes(Set prohibitedACAttributes)
    {
        if(prohibitedACAttributes == null)
        {
            this.prohibitedACAttributes.clear();
            return;
        }
        for(Iterator it = prohibitedACAttributes.iterator(); it.hasNext();)
            if(!(it.next() instanceof String))
                throw new ClassCastException("All elements of set must be of type String.");

        this.prohibitedACAttributes.clear();
        this.prohibitedACAttributes.addAll(prohibitedACAttributes);
    }

    public Set getAttrCertCheckers()
    {
        return Collections.unmodifiableSet(attrCertCheckers);
    }

    public void setAttrCertCheckers(Set attrCertCheckers)
    {
        if(attrCertCheckers == null)
        {
            this.attrCertCheckers.clear();
            return;
        }
        for(Iterator it = attrCertCheckers.iterator(); it.hasNext();)
            if(!(it.next() instanceof PKIXAttrCertChecker))
                throw new ClassCastException((new StringBuilder()).append("All elements of set must be of type ").append(co/org/bouncy/x509_/PKIXAttrCertChecker.getName()).append(".").toString());

        this.attrCertCheckers.clear();
        this.attrCertCheckers.addAll(attrCertCheckers);
    }

    private List stores;
    private Selector selector;
    private boolean additionalLocationsEnabled;
    private List additionalStores;
    private Set trustedACIssuers;
    private Set necessaryACAttributes;
    private Set prohibitedACAttributes;
    private Set attrCertCheckers;
    public static final int PKIX_VALIDITY_MODEL = 0;
    public static final int CHAIN_VALIDITY_MODEL = 1;
    private int validityModel;
    private boolean useDeltas;
}
