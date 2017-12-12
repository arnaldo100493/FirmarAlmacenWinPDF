// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerInformationStore.java

package co.org.bouncy.cms;

import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            SignerInformation, SignerId

public class SignerInformationStore
{

    public SignerInformationStore(Collection signerInfos)
    {
        all = new ArrayList();
        table = new HashMap();
        SignerInformation signer;
        List list;
        for(Iterator it = signerInfos.iterator(); it.hasNext(); list.add(signer))
        {
            signer = (SignerInformation)it.next();
            SignerId sid = signer.getSID();
            list = (ArrayList)table.get(sid);
            if(list == null)
            {
                list = new ArrayList(1);
                table.put(sid, list);
            }
        }

        all = new ArrayList(signerInfos);
    }

    public SignerInformation get(SignerId selector)
    {
        Collection list = getSigners(selector);
        return list.size() != 0 ? (SignerInformation)list.iterator().next() : null;
    }

    public int size()
    {
        return all.size();
    }

    public Collection getSigners()
    {
        return new ArrayList(all);
    }

    public Collection getSigners(SignerId selector)
    {
        if(selector.getIssuer() != null && selector.getSubjectKeyIdentifier() != null)
        {
            List results = new ArrayList();
            Collection match1 = getSigners(new SignerId(selector.getIssuer(), selector.getSerialNumber()));
            if(match1 != null)
                results.addAll(match1);
            Collection match2 = getSigners(new SignerId(selector.getSubjectKeyIdentifier()));
            if(match2 != null)
                results.addAll(match2);
            return results;
        } else
        {
            List list = (ArrayList)table.get(selector);
            return list != null ? new ArrayList(list) : new ArrayList();
        }
    }

    private List all;
    private Map table;
}
