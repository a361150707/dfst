package com.ennuova.common;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.ennuova.entity.InmSuppliermaterialSearch;
import com.ennuova.util.UrlUtil;

public class QueryBeanDemo {
	public static final String SOLR_URL =  UrlUtil.getInstance().getSolrurl();

	public static List<InmSuppliermaterialSearch> seach(String name,int length) {
		
		try {
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);
		server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
		server.setConnectionTimeout(5000); // 5 seconds to establish TCP
		// server.setRequestWriter(new BinaryRequestWriter());

		SolrQuery query = new SolrQuery();
		query.setQuery("*"+name+"*");
		query.setStart(0);
		query.setRows(length);
		query.setFacet(true);
		// query.addFacetField("name");

		QueryResponse response;

		response = server.query(query);
	
		// 搜索得到的结果数
		System.out.println("Find:" + response.getResults().getNumFound());
		// 输出结果
		int iRow = 1;

		// response.getBeans存在BUG,将DocumentObjectBinder引用的Field应该为
		// org.apache.solr.client.solrj.beans.Field
		SolrDocumentList list = response.getResults();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<InmSuppliermaterialSearch> beanList = binder.getBeans(InmSuppliermaterialSearch.class, list);
		/*for (InmSuppliermaterialSearch news : beanList) {
			System.out.println("----------------------");
			System.out.println("id: " + news.getId());
			System.out.println("name: " + news.getName());
			System.out.println("author: " + news.getAuthor());
		}

		for (SolrDocument doc : response.getResults()) {
			System.out.println("----------" + iRow + "------------");
			System.out.println("id: " + doc.getFieldValue("id").toString());
			System.out.println("name: " + doc.getFieldValue("name").toString());
			System.out.println("author: "
					+ doc.getFieldValue("author").toString());
			System.out.println("author_s: "
					+ doc.getFieldValue("author_s").toString());
			iRow++;
		}

		System.out.println("=====================");
		for (FacetField ff : response.getFacetFields()) {
			System.out.println(ff.getName() + "," + ff.getValueCount() + ","
					+ ff.getValues());
		}*/

		return beanList;
		
		} catch (SolrServerException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}

}