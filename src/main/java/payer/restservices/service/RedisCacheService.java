package payer.restservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import payer.restservices.repository.RedisCacheRepository;
import payer.restservices.repository.RedisReadCacheRepository;

@Component
public class RedisCacheService {

	@Autowired
	RedisCacheRepository redisCacheRepository;
	
	@Autowired
	RedisReadCacheRepository redisReadCacheRepository;
	
	/*@Autowired
	QuestionsRepository questionsRepository;
	
	
	public TempQuestions getTempQuestionInfo() {
		
		if(redisReadCacheRepository.getTempQuestionsListCache("active")==null) {
			List<TempQuestions> questions=tempQuestionsRepository.findByIsActive(true);
			redisCacheRepository.setTempQuestionListCache(questions);
			if(questions!=null && questions.size()>0) {
				return questions.get(0);
			}else {
				return null;
			}
		}
		return null;
		
	}
	
    public Question getQuestionListInfo() {
		
		if(redisReadCacheRepository.getQuestionsListCache("active")==null) {
			List<Question> questions=questionsRepository.findByIsActive(true);
			redisCacheRepository.setQuestionListCache(questions);
			if(questions!=null && questions.size()>0) {
				return questions.get(0);
			}
		}
		return null;
		
	}*/
	
	
	/*@Cacheable(value = "EXAMSCACHE", key = "#active")
    public List<Question> getQueList(String active) {
    	
    	if(redisReadCacheRepository.getQuestionsListCache(active)==null) {
    		List<Question> questions=questionsRepository.findByIsActive(true);
    		questions=questions.stream().filter(a->a.getQuestion().length()>30).limit(180).collect(Collectors.toList());
    		redisCacheRepository.setQuestionListCache(questions);
    		if(questions!=null && questions.size()>0) {
    			return questions;
    		}
    	}
		return null;
    	
    }*/
	
}
