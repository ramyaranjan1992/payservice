package payer.restservices.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RedisReadCacheRepository {

	
	public static final String TEMPQUESTIONCACHE = "TEMPQUESTIONCACHE";
	public static final String QUESTIONSLISTCACHE = "QUESTIONSLISTCACHE";


	private HashOperations hashOperations;

	@Qualifier("redisReadTemplate")
	@Autowired
	private RedisTemplate redisTemplate;

	public RedisReadCacheRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	


	public Object getTempQuestionCache(Object tempQuestionID) {

		return (Object) hashOperations.get(TEMPQUESTIONCACHE, tempQuestionID);
	}
	
	
	public List<Object> getTempQuestionsListCache(Object tempQueId) {

		return (List<Object>) hashOperations.get(TEMPQUESTIONCACHE, tempQueId);
	}
	
	public List<Object> getQuestionsListCache(Object tempQueId) {
		
		return (List<Object>) hashOperations.get(QUESTIONSLISTCACHE, tempQueId);
	}

	

}