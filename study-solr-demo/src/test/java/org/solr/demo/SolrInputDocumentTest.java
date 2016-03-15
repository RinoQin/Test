/*
 * FileName: SolrInputDocumentTest.java
 * Author:   v_qinyuchen
 * Date:     2016年3月15日 上午10:00:56
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.solr.demo;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.junit.Test;
import org.solr.demo.entity.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author v_qinyuchen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SolrInputDocumentTest {

    public final void fail(Object o) {
        System.out.println(o);
    }

    public void createInputDoc() {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", System.currentTimeMillis());
        doc.addField("name", "SolrInputDocument");
        doc.addField("age", 22, 2.0f);
        doc.addField("like", new String[] { "music", "book", "soccer" });

        doc.put("address", new SolrInputField("guangzhou"));

        doc.setField("sex", "man");
        doc.setField("remark", "china pepole", 2.0f);

        fail(doc);
    }

    /**
     * 
     * 功能描述: <br>
     * 利用DocumentObjectBinder对象将SolrInputDocument和User对象互相转换
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void docAndBean4Binder() {
        SolrDocument doc = new SolrDocument();
        doc.addField("id", 456);
        doc.addField("name", "SolrInputDocument");
        doc.addField("like", new String[] { "music", "bool", "soccer" });
        doc.put("address", "guangzhou");
        doc.put("sex", "man");
        doc.setField("remark", "china pepole");

        DocumentObjectBinder binder = new DocumentObjectBinder();

        User user = new User();
        user.setId(222);
        user.setName("JavaUser");
        user.setLike(new String[] { "music", "book", "soccer" });
        user.setAddress("guangzhou");
        fail(doc);
        // User ->> SolrInputDocument
        fail(binder.toSolrInputDocument(user));
        // SolrDocument ->> User
        fail(binder.getBean(User.class, doc));

        SolrDocumentList list = new SolrDocumentList();
        list.add(doc);
        list.add(doc);

        // SolrDocumentList ->> List
        fail(binder.getBeans(User.class, list));
    }

    /**
     * 
     * 功能描述: <br>
     * SolrInputDocument的相关方法
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @Test
    public void docMethod() {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", System.currentTimeMillis());
        doc.addField("name", "SolrInputDocument");
        doc.addField("age", 23, 1.0f);
        doc.addField("age", 22, 2.0f);
        doc.addField("age", 24, 0f);

        fail("doc: " + doc);
        fail("doc.entrySet: " + doc.entrySet());
        fail("doc.get.age: " + doc.get("age"));

        // 排名
        doc.setDocumentBoost(2.0f);

        fail("doc.getDocumentBoost: " + doc.getDocumentBoost());
        fail("doc.getField.name: " + doc.getField("name"));
        fail("doc.getFieldNames: " + doc.getFieldNames());
        fail("doc.getFieldValues.age: " + doc.getFieldValues("age"));
        fail("doc.getFieldValue.age :" + doc.getFieldValue("age"));
        fail("doc.getFieldValues.id: " + doc.getFieldValues("id"));
        fail("doc.getFieldValue.id: " + doc.getFieldValue("id"));
        fail("doc.values: " + doc.values());
    }
}
