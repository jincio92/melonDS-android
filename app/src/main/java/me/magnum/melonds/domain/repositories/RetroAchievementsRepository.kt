package me.magnum.melonds.domain.repositories

import me.magnum.melonds.domain.model.retroachievements.RAUserAchievement
import me.magnum.rcheevosapi.model.RAAchievement

interface RetroAchievementsRepository {
    suspend fun isUserAuthenticated(): Boolean
    suspend fun login(username: String, password: String): Result<Unit>
    suspend fun getGameUserAchievements(gameHash: String): Result<List<RAUserAchievement>>
    suspend fun getGameRichPresencePatch(gameHash: String): String?
    suspend fun getAchievement(achievementId: Long): Result<RAAchievement?>
    suspend fun awardAchievement(achievement: RAAchievement)
    suspend fun submitPendingAchievements(): Result<Unit>
    suspend fun startSession(gameHash: String)
    suspend fun sendSessionHeartbeat(gameHash: String, richPresenceDescription: String?)
}