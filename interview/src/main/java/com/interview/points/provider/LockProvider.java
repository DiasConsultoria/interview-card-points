package com.interview.points.provider;

import org.redisson.api.RLock;

public interface LockProvider {

    RLock getLock(String parameterToLock, Integer userId);
}
