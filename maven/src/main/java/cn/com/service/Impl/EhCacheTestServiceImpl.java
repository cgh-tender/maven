package cn.com.service.Impl;

import cn.com.service.EhCacheTestService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class EhCacheTestServiceImpl implements EhCacheTestService {
    @Cacheable(value="baseCache",key="#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
}