package com.interview.points.provider;


import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LockProviderImp implements LockProvider{

    private final RedissonClient redissonClient;

    @Override
    public RLock getLock(String parameterToLock, Integer userId) {
        return redissonClient.getLock(parameterToLock + userId);
    }
}
