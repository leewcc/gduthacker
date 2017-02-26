package com.hackerspace.service;

import java.util.ArrayList;
import java.util.List;

import com.hackerspace.dao.NewsDao;
import com.hackerspace.model.News;
import com.hackerspace.model.PageElem;

public class NewsService {

	public boolean saveNews(News news) {
		NewsDao nd=new NewsDao();
		return nd.saveNews(news);
	}
	
	public boolean findNews(byte status,byte tag,PageElem<News> pe) {
		NewsDao nd=new NewsDao();
		return nd.findNews(status, tag, pe);
	}

	public boolean deletePublishedNews(int id) {
		NewsDao nd=new NewsDao();
		return nd.deletePublishedNews(id);
		
	}

	public boolean findDrafts(byte status,PageElem<News> pageElem) {
		NewsDao nd=new NewsDao();
		return nd.findDrafts(status, pageElem);
	}

	public boolean takeToTop(int id,byte isTop) {
		NewsDao nd=new NewsDao();
		return nd.takeToTop(id,isTop);
	}

	public boolean updateNews(News news) {
		NewsDao nd=new NewsDao();
		return nd.updateNews(news);
	}

	public News getOneArticle(int id) {
		NewsDao nd=new NewsDao();
		
		return nd.getOneArticle(id);
	}

	public ArrayList<News> findTopNews() {
		NewsDao nd=new NewsDao();
		return nd.findTopNews();
	}
}
