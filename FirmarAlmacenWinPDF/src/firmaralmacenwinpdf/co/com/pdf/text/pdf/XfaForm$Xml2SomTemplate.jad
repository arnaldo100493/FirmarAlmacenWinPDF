// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaForm.java

package co.com.pdf.text.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm

public static class XfaForm$Xml2SomTemplate extends XfaForm.Xml2Som
{

    public String getFieldType(String s)
    {
        Node n = (Node)name2Node.get(s);
        if(n == null)
            return null;
        if("exclGroup".equals(n.getLocalName()))
            return "exclGroup";
        Node ui;
        for(ui = n.getFirstChild(); ui != null && (ui.getNodeType() != 1 || !"ui".equals(ui.getLocalName())); ui = ui.getNextSibling());
        if(ui == null)
            return null;
        for(Node type = ui.getFirstChild(); type != null; type = type.getNextSibling())
            if(type.getNodeType() == 1 && (!"extras".equals(type.getLocalName()) || !"picture".equals(type.getLocalName())))
                return type.getLocalName();

        return null;
    }

    private void processTemplate(Node n, HashMap ff)
    {
        if(ff == null)
            ff = new HashMap();
        HashMap ss = new HashMap();
        for(Node n2 = n.getFirstChild(); n2 != null; n2 = n2.getNextSibling())
        {
            if(n2.getNodeType() != 1)
                continue;
            String s = n2.getLocalName();
            if("subform".equals(s))
            {
                Node name = n2.getAttributes().getNamedItem("name");
                String nn = "#subform";
                boolean annon = true;
                if(name != null)
                {
                    nn = escapeSom(name.getNodeValue());
                    annon = false;
                }
                Integer i;
                if(annon)
                {
                    i = Integer.valueOf(anform);
                    anform++;
                } else
                {
                    i = (Integer)ss.get(nn);
                    if(i == null)
                        i = Integer.valueOf(0);
                    else
                        i = Integer.valueOf(i.intValue() + 1);
                    ss.put(nn, i);
                }
                stack.push((new StringBuilder()).append(nn).append("[").append(i.toString()).append("]").toString());
                templateLevel++;
                if(annon)
                    processTemplate(n2, ff);
                else
                    processTemplate(n2, null);
                templateLevel--;
                stack.pop();
                continue;
            }
            if("field".equals(s) || "exclGroup".equals(s))
            {
                Node name = n2.getAttributes().getNamedItem("name");
                if(name == null)
                    continue;
                String nn = escapeSom(name.getNodeValue());
                Integer i = (Integer)ff.get(nn);
                if(i == null)
                    i = Integer.valueOf(0);
                else
                    i = Integer.valueOf(i.intValue() + 1);
                ff.put(nn, i);
                stack.push((new StringBuilder()).append(nn).append("[").append(i.toString()).append("]").toString());
                String unstack = printStack();
                order.add(unstack);
                inverseSearchAdd(unstack);
                name2Node.put(unstack, n2);
                stack.pop();
                continue;
            }
            if(dynamicForm || templateLevel <= 0 || !"occur".equals(s))
                continue;
            int initial = 1;
            int min = 1;
            int max = 1;
            Node a = n2.getAttributes().getNamedItem("initial");
            if(a != null)
                try
                {
                    initial = Integer.parseInt(a.getNodeValue().trim());
                }
                catch(Exception e) { }
            a = n2.getAttributes().getNamedItem("min");
            if(a != null)
                try
                {
                    min = Integer.parseInt(a.getNodeValue().trim());
                }
                catch(Exception e) { }
            a = n2.getAttributes().getNamedItem("max");
            if(a != null)
                try
                {
                    max = Integer.parseInt(a.getNodeValue().trim());
                }
                catch(Exception e) { }
            if(initial != min || min != max)
                dynamicForm = true;
        }

    }

    public boolean isDynamicForm()
    {
        return dynamicForm;
    }

    public void setDynamicForm(boolean dynamicForm)
    {
        this.dynamicForm = dynamicForm;
    }

    private boolean dynamicForm;
    private int templateLevel;

    public XfaForm$Xml2SomTemplate(Node n)
    {
        order = new ArrayList();
        name2Node = new HashMap();
        stack = new XfaForm.Stack2();
        anform = 0;
        templateLevel = 0;
        inverseSearch = new HashMap();
        processTemplate(n, null);
    }
}
