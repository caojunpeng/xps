package com.cao.xps.service.plug.luncence;

import com.cao.xps.service.user.entity.User;
import com.cao.xps.service.user.mapper.UserMapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class LuncenceManage {

    @Resource
    private UserMapper userMapper;

    /**
     * 创建索引
     * @throws Exception
     */
    public void createIndex() throws Exception{
        // 1. 获取数据
        List<User> users = userMapper.selectList(null);
        // 2. 创建文档对象
        List<Document> documents = new ArrayList<Document>();
        if(!users.isEmpty()){
            for (User u : users) {
                Document document = new Document();
                document.add(new TextField("userId", u.getUserId() + "", Field.Store.YES));
                document.add(new TextField("userName", u.getUserName() + "", Field.Store.YES));
                document.add(new TextField("userPwd", u.getUserPwd() + "", Field.Store.YES));
                documents.add(document);
            }
        }
        // 3. 创建分析器对象(Analyzer), 用于分词
        Analyzer analyzer = new StandardAnalyzer();
        // 4. 创建索引配置对象(IndexWriterConfig), 用于配置Lucene
        // 参数一:当前使用的Lucene版本, 参数二:分析器
        IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
// 5. 创建索引库目录位置对象(Directory), 指定索引库的存储位置
        File path = new File("C:\\Users\\peng\\Desktop\\test");
        Directory directory = FSDirectory.open(path);
        // 6. 创建索引写入对象(IndexWriter), 将文档对象写入索引
        IndexWriter indexWriter = new IndexWriter(directory, indexConfig);
        // 7. 使用IndexWriter对象创建索引
        for (Document doc : documents) {
            // addDocement(doc): 将文档对象写入索引库
            indexWriter.addDocument(doc);
        }
        // 8. 释放资源
        indexWriter.close();
    }

    /**
     * 全文检索
     * @throws Exception
     */
    public void searchIndex() throws Exception {
        // 1. 创建分析器对象(Analyzer), 用于分词
        Analyzer analyzer = new StandardAnalyzer();

        QueryParser queryParser = new QueryParser("userName", analyzer);
        // 2.2 使用查询解析器对象, 实例化Query对象
        Query query = queryParser.parse("userName:admin");

        // 3. 创建索引库目录位置对象(Directory), 指定索引库位置
        Directory directory = FSDirectory.open(new File("C:\\Users\\peng\\Desktop\\test"));
        // 4. 创建索引读取对象(IndexReader), 用于读取索引
        IndexReader indexReader = DirectoryReader.open(directory);
        // 5. 创建索引搜索对象(IndexSearcher), 用于执行索引
        IndexSearcher searcher = new IndexSearcher(indexReader);

        // 6. 使用IndexSearcher对象执行搜索, 返回搜索结果集TopDocs
        // 参数一:使用的查询对象, 参数二:指定要返回的搜索结果排序后的前n个
        TopDocs topDocs = searcher.search(query, 10);

        // 7. 处理结果集
        // 7.1 打印实际查询到的结果数量
        System.out.println("实际查询到的结果数量: " + topDocs.totalHits);
        // 7.2 获取搜索的结果数组
        // ScoreDoc中有文档的id及其评分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            System.out.println("= = = = = = = = = = = = = = = = = = =");
            // 获取文档的id和评分
            int docId = scoreDoc.doc;
            float score = scoreDoc.score;
            System.out.println("文档id= " + docId + " , 评分= " + score);
            Document doc = searcher.doc(docId);
            System.out.println("用户Id: " + doc.get("userId"));
            System.out.println("用户名称: " + doc.get("userName"));
            System.out.println("用户密码: " + doc.get("userPwd"));
        }
        // 8. 关闭资源
        indexReader.close();
    }

}
