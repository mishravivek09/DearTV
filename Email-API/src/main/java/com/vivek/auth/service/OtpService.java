package com.vivek.auth.service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
@Service
public class OtpService {
	private static final Integer expMin=5;
	private LoadingCache<String,Integer> otpCache;
	
	public OtpService() {
		super();
		otpCache=CacheBuilder.newBuilder()
				.expireAfterWrite(expMin, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>(){
					public Integer load(String key) {
						return 0;
					}
				});
		
	}
	
	public int generateOtp(String key) {
		Random r=new Random();
		int otp=100000+r.nextInt(900000);
		otpCache.put(key, otp);
		return otp;
	}
	
	public int getOtp(String key) {
		try {
			return otpCache.get(key);
		} catch (ExecutionException e) {
			return 0;
		}
	}
	public void clearOtp(String key) {
		otpCache.invalidate(key);
	}
}
