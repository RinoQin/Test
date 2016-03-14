package org.solr.demo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.solr.demo.entity.Index;

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

    /**
     * 
     * 功能描述: <br>
     * 查询所有索引信息
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void queryAll() {
        ModifiableSolrParams params = new ModifiableSolrParams();
        // 查询关键词，“*:*”代表所有属性、所有值，即所有index
        params.set("q", "*:*");
        // 分页，start=0就是从0开始，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了
        params.set("start", 0);
        params.set("rows", Integer.MAX_VALUE);

        // 排序，如果按照id排序，那么将score desc改成id desc(or asc)
        params.set("sort", "score desc");

        // 返回信息*为全部，这里是全部加上score，如果不加下面就不能使用score
        params.set("fl", "*,score");

        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加JavaEntity Bean
     */
    public void addBean() {
        // Index需要添加相关的注解，便于告诉solr哪些属性参与到index中
        Index index = new Index();
        index.setId("4");
        index.setName("add bean index");
        index.setManu("index bean manu");
        index.setCat(new String[] { "a1", "b2" });

        try {
            UpdateResponse response = server.addBean(index);
            fail(server.commit());
            fail(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        queryAll();
    }

    /**
     * 添加Entity Bean集合到索引库
     */
    public void addBeans() {
        Index index = new Index();
        index.setId("6");
        index.setName("add beans index1");
        index.setManu("index beans manu 1");
        index.setCat(new String[] { "a", "b" });

        List<Index> indexs = new ArrayList<Index>();
        indexs.add(index);

        index = new Index();
        index.setId("5");
        index.setName("index beans index2");
        index.setManu("index beans manu 2");
        index.setCat(new String[] { "aaa", "bbb" });
        indexs.add(index);
        try {
            UpdateResponse response = server.addBeans(indexs);
            fail(server.commit());
            fail(response);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queryAll();
    }

    /**
     * 删除索引操作
     */
    public void remove() {
        try {
            // 删除id为1的索引
            server.deleteById("1");
            server.commit();
            query("id:1");

            // 根据id集合，删除多个索引
            List<String> ids = new ArrayList<String>();
            ids.add("2");
            ids.add("3");
            server.deleteById(ids);
            server.commit(true, true);
            query("id:2 id:3");

            // 删除查询到的索引信息
            server.deleteByQuery("id:4 id:6");
            server.commit(true, true);
            queryAll();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: <br>
     * 其他server相关方法测试
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void otherMethord() {
        fail(server.getBinder());
        try {
            // 合并索引文件，可以优化索引、提高性能，但需要一定的时间
            fail(server.optimize());
            // ping服务器是否连接成功
            fail(server.ping());

            Index index = new Index();
            index.setId("299");
            index.setName("add bean index299");
            index.setManu("index bean manu299");
            index.setCat(new String[] { "a299", "b299" });

            UpdateResponse response = server.addBean(index);
            fail("response: " + response);
            queryAll();
            // 回滚掉之前的操作，rollback addBean operation
            fail("rollback: " + server.rollback());
            // 提交操作
            fail("commit: " + server.commit());
            queryAll();
        } catch (SolrServerException e) {
            System.out.println("SolrServerException ....");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException ....");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("OtherException ....");
            e.printStackTrace();
        }
    }

    public void test() {
        List<Index> indexs = new ArrayList<Index>();
        Index index = new Index();
        index.setId("id1");
        index.setName("apple");
        index.setManu("inc");
        index.setPopularity(6);
        indexs.add(index);
        index = new Index();
        index.setId("id2");
        index.setName("server");
        index.setManu("apache");
        index.setPrice(50);
        indexs.add(index);
        index = new Index();
        index.setId("id3");
        index.setName("solr");
        index.setManu("dell");
        index.setPrice(200);
        index.setPopularity(5);
        indexs.add(index);

        try {
            UpdateResponse response = server.addBeans(indexs);
            fail(server.commit());
            fail(response);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queryAll();
    }

    /**
     * query基本用法测试
     */
    public void queryCase() {
        // AND 并且
        // SolrQuery params = new SolrQuery("name:apple AND manu:inc");

        SolrQuery params = new SolrQuery();
        // params.setQuery("name:apple AND manu:inc");
        // OR 或者
        // params.setQuery("name:apple OR manu:dell");
        // 查询name包含solr apple
        // params.setQuery("name:solr,apple");
        // manu不包含inc
        // params.setQuery("name:solr,apple NOT manu:inc");
        // 50 <= price <= 200
        // params.setQuery("price:[50 TO 200]");
        // 5 <= popularity <=6
        // params.setQuery("popularity:[5 TO 6]");
        // 50 <= price <= 200 AND NOT 5 <= popularity <=6
        // params.setQuery("price:[50 TO 200] - popularity:[5 TO 6]");
        // 50 <= price <= 200 5 <= popularity <=6 这个我没法形容了
        // params.setQuery("price:[50 TO 200] + popularity:[5 TO 6]");
        // 50 <= price <= 200 AND 5 <= popularity <= 6
        // params.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");
        // 50 <= price <= 200 OR 5 <= popularity <= 6
        // params.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");
        try {
            QueryResponse response = server.query(params);
            // List<Index> indexs = response.getBeans(Index.class);
            // for (Index index : indexs) {
            // fail(index);
            // }
            SolrDocumentList list = response.getResults();
            fail("query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    public void querySetParams() {
        SolrQuery params = new SolrQuery("id:5");
        // 开启高亮组件
        params.setHighlight(true);
        // 高亮字段
        params.addHighlightField("name");
        // 标记，高亮关键字前缀
        params.setHighlightSimplePre("<font color='red'>");
        // 标记，高亮关键字后缀
        params.setHighlightSimplePost("</font>");

        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryFacet() {
        SolrQuery params = new SolrQuery("*:*");
        // 结果分方面数，默认为1
        params.setHighlightSnippets(1);
        // 每个分方面的最大长度，默认为100
        params.setHighlightFragsize(1000);

        // 分方面信息
        params.setFacet(true).setFacetMinCount(1).setFacetLimit(5).addFacetField("name").addFacetField("inStock");

        try {
            QueryResponse response = server.query(params);
            // 输出分方面信息
            List<FacetField> facets = response.getFacetFields();
            fail(facets);
            for (FacetField facet : facets) {
                fail(facet);
                List<Count> facetCounts = facet.getValues();
                for (Count count : facetCounts) {
                    System.out.println(count.getName() + ": " + count.getCount());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
