package payer.restservices.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RedisCacheRepository {

	
	public static final String TEMPQUESTIONCACHE = "TEMPQUESTIONCACHE";
	
	public static final String TEMPQUESTIONLISTCACHE = "TEMPQUESTIONLISTCACHE";
	
	public static final String QUESTIONSLISTCACHE = "QUESTIONSLISTCACHE";

	

	private HashOperations hashOperations;

	@Qualifier("redisWriteTemplate")
	@Autowired
	private RedisTemplate redisTemplate;
	
	public RedisCacheRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	

	/*public void setTempQuestionCache(Object tempQueVal,TempQuestions item) {
		hashOperations.put(TEMPQUESTIONCACHE, tempQueVal, item);
	}

	
	
	
	public void setTempQuestionListCache(List<TempQuestions> tempQueList) {

		for (TempQuestions tempQuestion : tempQueList) {
			hashOperations.put(TEMPQUESTIONLISTCACHE, tempQuestion.get_id(), tempQuestion);
		}
	}
	public void setQuestionListCache(List<Question> queList) {
		
		for (Question question : queList) {
			hashOperations.put(QUESTIONSLISTCACHE, question.get_id(), question);
		}
	}
*/
	
	

}