package cn.coding.com.mysqlmongotransaction.config;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;


@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {


    @Override
    protected String getDatabaseName() {
        return "demo-transaction";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "demo-transaction");
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/demo-transaction");
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

//    @Bean
//    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context, BeanFactory beanFactory){
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
//        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);
//        try {
//            converter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //don't save column class to mongo collection
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        return converter;
//    }

    @Bean(name = "MONGO_TRANSACTION_MANAGER")
    MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory dbFactory){
        TransactionOptions transactionOptions =
                TransactionOptions.builder().readConcern(ReadConcern.LOCAL).writeConcern(WriteConcern.W1).build();
        return new MongoTransactionManager(dbFactory, transactionOptions);
    }
}
