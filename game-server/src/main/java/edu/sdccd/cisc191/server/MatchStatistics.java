package edu.sdccd.cisc191.server;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tracks server-wide match statistics shared by many gRPC request threads.
 */
public class MatchStatistics {

    // Thread-safe counters
    private final AtomicInteger joinedMatchCount = new AtomicInteger(0);
    private final AtomicInteger completedMatchCount = new AtomicInteger(0);

    /** Thread-safe join counter increment. */
    public void recordJoin() {
        joinedMatchCount.incrementAndGet();
    }

    /** Thread-safe completion counter increment. */
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
     * Return a readable, thread-safe statistics summary.
     *
     * Format:
     * Server stats: 3 joined, 2 completed
     */
    public String buildStatusLine() {
        return "Server stats: "
                + joinedMatchCount.get()
                + " joined, "
                + completedMatchCount.get()
                + " completed";
    }
}