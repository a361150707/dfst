package com.ennuova.common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import com.ennuova.entity.InmSuppliermaterialSearch;
import com.ennuova.util.UrlUtil;

public class AddBeansDemo {
  public static final String SOLR_URL = UrlUtil.getInstance().getSolrurl();

  public static void main(String[] args) {
    // 通过浏览器查看结果
    // 要保证bean中各属性的名称在conf/schema.xml中存在，如果查询，要保存被索引
    // http://172.168.63.233:8983/solr/collection1/select?q=description%3A%E6%94%B9%E9%9D%A9&wt=json&indent=true
   // delBeans();
  //  AddBeans();
  }

 

  public static void AddBeans(List<InmSuppliermaterialSearch> list) {

    long start = System.currentTimeMillis();
    Collection<InmSuppliermaterialSearch> docs = new ArrayList<InmSuppliermaterialSearch>();
//		DocumentObjectBinder binder = new DocumentObjectBinder();
    for (InmSuppliermaterialSearch search:list) {
//SolrInputDocument doc1 = binder.toSolrInputDocument(news);
       docs.add(search);
    }
    try {
      HttpSolrServer server = new HttpSolrServer(SOLR_URL);
      server.setRequestWriter(new BinaryRequestWriter());
      // 可以通过二种方式增加docs,其中server.add(docs.iterator())效率最高
      // 增加后通过执行commit函数commit (981ms)
      // server.addBeans(docs);
      // server.commit();

      // the most optimal way of updating all your docs
      // in one http request(481ms)
      server.addBeans(docs.iterator());
      server.optimize(); //time elasped 1176ms
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("time elapsed(ms):"
        + (System.currentTimeMillis() - start));
  }

  public static void delBeans() {
    long start = System.currentTimeMillis();
    try {
      HttpSolrServer server = new HttpSolrServer(SOLR_URL);
      server.deleteByQuery("*:*");
      server.commit();
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("time elapsed(ms):"
        + (System.currentTimeMillis() - start));
  }
  
  
}
