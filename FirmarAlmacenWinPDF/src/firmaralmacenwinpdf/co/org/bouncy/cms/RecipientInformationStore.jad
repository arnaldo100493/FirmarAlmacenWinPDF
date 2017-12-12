// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientInformationStore.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x500.X500Name;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInformation, KeyTransRecipientId, RecipientId

public class RecipientInformationStore
{

    public RecipientInformationStore(Collection recipientInfos)
    {
        RecipientInformation recipientInformation;
        List list;
        for(Iterator it = recipientInfos.iterator(); it.hasNext(); list.add(recipientInformation))
        {
            recipientInformation = (RecipientInformation)it.next();
            RecipientId rid = recipientInformation.getRID();
            list = (ArrayList)table.get(rid);
            if(list == null)
            {
                list = new ArrayList(1);
                table.put(rid, list);
            }
        }

        all = new ArrayList(recipientInfos);
    }

    public RecipientInformation get(RecipientId selector)
    {
        Collection list = getRecipients(selector);
        return list.size() != 0 ? (RecipientInformation)list.iterator().next() : null;
    }

    public int size()
    {
        return all.size();
    }

    public Collection getRecipients()
    {
        return new ArrayList(all);
    }

    public Collection getRecipients(RecipientId selector)
    {
        if(selector instanceof KeyTransRecipientId)
        {
            KeyTransRecipientId keyTrans = (KeyTransRecipientId)selector;
            X500Name issuer = keyTrans.getIssuer();
            byte subjectKeyId[] = keyTrans.getSubjectKeyIdentifier();
            if(issuer != null && subjectKeyId != null)
            {
                List results = new ArrayList();
                Collection match1 = getRecipients(((RecipientId) (new KeyTransRecipientId(issuer, keyTrans.getSerialNumber()))));
                if(match1 != null)
                    results.addAll(match1);
                Collection match2 = getRecipients(((RecipientId) (new KeyTransRecipientId(subjectKeyId))));
                if(match2 != null)
                    results.addAll(match2);
                return results;
            }
        }
        List list = (ArrayList)table.get(selector);
        return list != null ? new ArrayList(list) : new ArrayList();
    }

    private final List all;
    private final Map table = new HashMap();
}
