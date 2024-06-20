package com.interview.points.provider;

import org.redisson.api.RLock;

/**
 * Service interface for providing distributed locks.
 */
public interface LockProvider {

    /**
     * Retrieves a distributed lock based on a parameter and user ID.
     *
     * @param parameterToLock the parameter to lock.
     * @param userId the ID of the user requesting the lock.
     * @return an RLock representing the distributed lock.
     */
    RLock getLock(String parameterToLock, Integer userId);
}
