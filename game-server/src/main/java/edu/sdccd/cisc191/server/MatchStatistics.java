package edu.sdccd.cisc191.server;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tracks server-wide match statistics shared by many gRPC request threads.
 *
 * TODO 9: Uses AtomicInteger for lock-free, thread-safe counter increments.
*/

public class MatchStatistics {

    private final AtomicInteger joinedMatchCount = new AtomicInteger(0);
    private final AtomicInteger completedMatchCount = new AtomicInteger(0);

    /** TODO 9: Thread-safe join counter increment. */
    public void recordJoin() {
        joinedMatchCount.incrementAndGet();
    }

    /** TODO 9: Thread-safe completion counter increment. */
    public void recordCompletion() {
        completedMatchCount.incrementAndGet();
    }

    public int getJoinedMatchCount() {
        return joinedMatchCount.get();
    }

    public int getCompletedMatchCount() {
        return completedMatchCount.get();
    }

    /**
     * TODO 9: Return a readable status line.
     * Format: Server stats: 3 joined, 2 completed
     */
    public String buildStatusLine() {
        return "Server stats: " + joinedMatchCount.get() + " joined, " + completedMatchCount.get() + " completed";
    }
}