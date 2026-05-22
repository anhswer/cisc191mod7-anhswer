package edu.sdccd.cisc191.model;

import java.util.concurrent.atomic.AtomicInteger;

public class MatchViewModel {

    private String matchId;
    private final Player player = new Player("Player");
    private final Player opponent = new Player("Opponent");
    private boolean matchOver;
    private String winnerName = "";

    // TODO 7: Make this shared counter thread-safe.
    // Use either an AtomicInteger field or synchronized methods so background tasks cannot lose updates.
    private final AtomicInteger completedMatchCount = new AtomicInteger(0);

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return opponent;
    }

    public boolean isMatchOver() {
        return matchOver;
    }

    public void setMatchOver(boolean matchOver) {
        this.matchOver = matchOver;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = (winnerName == null) ? "" : winnerName;
    }

    public int getCompletedMatchCount() {
        return completedMatchCount.get();
    }

    public void resetCompletedMatchCount() {
        completedMatchCount.set(0);
    }

    /**
     * TODO 7: Complete this method using thread-safe programming.
     *
     * Requirements:
     * - Increase completed match count exactly once per call
     * - Store winner name safely
     * - Mark match as over
     */
    public synchronized void recordCompletedMatchThreadSafely(String winnerName) {
        completedMatchCount.incrementAndGet();
        setWinnerName(winnerName);
        matchOver = true;
    }

    public boolean hasJoinedMatch() {
        return matchId != null && !matchId.isBlank();
    }

    public boolean canPlayMatch() {
        return hasJoinedMatch() && !matchOver;
    }

    /**
     * TODO 2: Complete this MVC helper.
     *
     * Expected:
     * Match match-001: Ada vs Bot (Hard, ranked)
     *
     * Rules:
     * - "No match" if matchId is null/blank
     * - Use player/opponent names
     * - "Normal" if difficulty blank
     * - ranked vs casual
     */
    public String buildMatchSummary(String difficulty, boolean ranked) {

        if (matchId == null || matchId.isBlank()) {
            return "No match";
        }

        String resolvedDifficulty =
                (difficulty == null || difficulty.isBlank()) ? "Normal" : difficulty.trim();

        String rankedLabel = ranked ? "ranked" : "casual";

        return "Match " + matchId + ": "
                + player.getName() + " vs " + opponent.getName()
                + " (" + resolvedDifficulty + ", " + rankedLabel + ")";
    }

    public void resetLocalState() {
        matchId = null;
        player.setName("Player");
        opponent.setName("Opponent");
        matchOver = false;
        winnerName = "";
        completedMatchCount.set(0);
    }
}