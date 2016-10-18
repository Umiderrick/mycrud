package com.guangguanger.MyCRUD.web.dao.nosqldao;

import com.guangguanger.MyCRUD.web.model.User;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umiderrick on 2016/7/18.
 */
@Repository
public class MongoDao {

    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

    MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

    MongoCollection<Document> collection = mongoDatabase.getCollection("user");


    /******连接mongo并创建集合******/
    public  void init(){
        try{
            mongoDatabase.createCollection("user");
            System.out.println("集合创建成功");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public boolean insert(User user){
        /**
         * 1. 创建文档 org.bson.Document 参数为key-value的格式
         * 2. 创建文档集合List<Document>
         * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
         * */
        Document document = new Document("id", user.getId()).
                append("name", user.getUsername()).
                append("password", user.getPassword()).
                append("create_time",user.getCreateTime()).
                append("Estimated_time",user.getState());
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文档插入成功");
        return true;
    }
    public List getall(){

        //检索所有文档
        /**
         * 1. 获取迭代器FindIterable<Document>
         * 2. 获取游标MongoCursor<Document>
         * 3. 通过游标遍历检索出的文档集合
         * */
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List list = new ArrayList<>();
        while(mongoCursor.hasNext()){
            list.add(mongoCursor.next());
        }
        return list;
    }
    public String get(int id){
        Document queryObject=new Document().append("id",id);
        FindIterable<Document> findIterable =collection.find(queryObject);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            return  mongoCursor.next().toString();
        }
        return  null;
    }
    public boolean delete(int id ){
        //删除符合条件的第一个文档
        collection.deleteOne(Filters.eq("id", id));
        return true;
    }


}
