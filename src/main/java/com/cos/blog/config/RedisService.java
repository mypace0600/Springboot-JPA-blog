package com.cos.blog.config;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {
	private final Long clientAddressPostRequestWriteExpireDurationSec = 10L;
	private final RedisTemplate<String, Boolean> redisTemplate;

	public boolean isFirstIpRequest(String clientAddress, int postId, int userId){
		String key = generateKey(clientAddress,postId, userId);
		log.info("key :{}",key);
		log.info("redisTemplate :{}",redisTemplate.hasKey(key));
		if(redisTemplate.hasKey(key)){
			return false;
		}
		return true;
	}

	public void writeClientRequest(String clientAddress,int postId, int userId){
		String key = generateKey(clientAddress,postId,userId);
		redisTemplate.opsForValue().set(key,true);
		log.info("opsForValue :{}",redisTemplate.opsForValue().get(key));
		redisTemplate.expire(key,clientAddressPostRequestWriteExpireDurationSec, TimeUnit.MINUTES);
		log.info("expire :{}",redisTemplate.getExpire(key));
	}

	private String generateKey(String cliendAddress, int postId,int userId){
		return cliendAddress+"+"+postId+"+"+userId;
	}
}
