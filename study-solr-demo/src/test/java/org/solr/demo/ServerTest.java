package org.solr.demo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ServerTest {

    private SolrServer server;
    private CommonsHttpSolrServer httpServer;

    private static final String DEFAULT_URL = "http://localhost:8080/solr/";

    @Before
    public void init() {
        try {
            server = new CommonsHttpSolrServer(DEFAULT_URL);
            httpServer = new CommonsHttpSolrServer(DEFAULT_URL);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @After
    public void destory() {
        server = null;
        httpServer = null;
        System.runFinalization();
        System.gc();
    }

    public final void fail(Object o) {
        System.out.println(o);
    }

    /**
     * 测试是否创建server对象成功
     */
    public void server() {
        fail(server);
        fail(httpServer);
        query("solr");
    }

    /**
     * 根据query参数查询索引
     */
    public void query(String query) {
        SolrParams params = new SolrQuery(query);
        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            assert (list.size() > 0);
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    public void addDoc() {
        // 创建doc文档
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 1);
        doc.addField("name", "Solr Input Document");
        try {
            UpdateResponse response = server.add(doc);
            fail(server.commit());// commit后才能保持到索引库
            fail(response);
            fail("query time: " + response.getQTime());
            fail("Elapsed Time: " + response.getElapsedTime());
            fail("status: " + response.getStatus());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        query("name:solr");
    }

    @Test
    public void addDocs() {
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 2);
        doc.addField("name", "Solr Input Documnet 1");
        doc.addField("manu", "This is SolrInputDocuments 1 content");

        docs.add(doc);

        doc = new SolrInputDocument();
        doc.addField("id", 3);
        doc.addField("name", "Solr Input Deocument 2");
        doc.addField("manu", "This is SolrInputDocument is 3 content");

        docs.add(doc);

        try {
            UpdateResponse response = server.add(docs);
            fail(server.commit());
            fail(response);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        query("solr");
    }
}
