// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm

public static class XfaForm$Xml2SomDatasets extends XfaForm.Xml2Som
{

    public Node insertNode(Node n, String shortName)
    {
        XfaForm.Stack2 stack = splitParts(shortName);
        Document doc = n.getOwnerDocument();
        Node n2 = null;
        for(n = n.getFirstChild(); n.getNodeType() != 1; n = n.getNextSibling());
        for(int k = 0; k < stack.size(); k++)
        {
            String part = (String)stack.get(k);
            int idx = part.lastIndexOf('[');
            String name = part.substring(0, idx);
            idx = Integer.parseInt(part.substring(idx + 1, part.length() - 1));
            int found = -1;
            for(n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
            {
                if(n2.getNodeType() != 1)
                    continue;
                String s = escapeSom(n2.getLocalName());
                if(s.equals(name) && ++found == idx)
                    break;
            }

            for(; found < idx; found++)
            {
                n2 = doc.createElementNS(null, name);
                n2 = n.appendChild(n2);
                Node attr = doc.createAttributeNS("http://www.xfa.org/schema/xfa-data/1.0/", "dataNode");
                attr.setNodeValue("dataGroup");
                n2.getAttributes().setNamedItemNS(attr);
            }

            n = n2;
        }

        inverseSearchAdd(inverseSearch, stack, shortName);
        name2Node.put(shortName, n2);
        order.add(shortName);
        return n2;
    }

    private static boolean hasChildren(Node n)
    {
        Node dataNodeN = n.getAttributes().getNamedItemNS("http://www.xfa.org/schema/xfa-data/1.0/", "dataNode");
        if(dataNodeN != null)
        {
            String dataNode = dataNodeN.getNodeValue();
            if("dataGroup".equals(dataNode))
                return true;
            if("dataValue".equals(dataNode))
                return false;
        }
        if(!n.hasChildNodes())
            return false;
        for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
            if(n2.getNodeType() == 1)
                return true;

        return false;
    }

    private void processDatasetsInternal(Node n)
    {
        if(n != null)
        {
            HashMap ss = new HashMap();
            for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
            {
                if(n2.getNodeType() != 1)
                    continue;
                String s = escapeSom(n2.getLocalName());
                Integer i = (Integer)ss.get(s);
                if(i == null)
                    i = Integer.valueOf(0);
                else
                    i = Integer.valueOf(i.intValue() + 1);
                ss.put(s, i);
                if(hasChildren(n2))
                {
                    stack.push((new StringBuilder()).append(s).append("[").append(i.toString()).append("]").toString());
                    processDatasetsInternal(n2);
                    stack.pop();
                } else
                {
                    stack.push((new StringBuilder()).append(s).append("[").append(i.toString()).append("]").toString());
                    String unstack = printStack();
                    order.add(unstack);
                    inverseSearchAdd(unstack);
                    name2Node.put(unstack, n2);
                    stack.pop();
                }
            }

        }
    }

    public XfaForm$Xml2SomDatasets(Node n)
    {
        order = new ArrayList();
        name2Node = new HashMap();
        stack = new XfaForm.Stack2();
        anform = 0;
        inverseSearch = new HashMap();
        processDatasetsInternal(n);
    }
}
