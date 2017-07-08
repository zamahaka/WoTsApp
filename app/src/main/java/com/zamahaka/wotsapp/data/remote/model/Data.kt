package com.zamahaka.wotsapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.zamahaka.wotsapp.data.remote.*

/**
 * Created by Ura on 08.07.2017.
 */

data class Meta(@SerializedName("count") val count: Int)

data class Error(@SerializedName("field") val field: String,
                 @SerializedName("message") val message: String,
                 @SerializedName("code") val code: Int,
                 @SerializedName("value") val value: Any)

data class SearchUser(@SerializedName("account_id") val accountId: Int,
                      @SerializedName("nickname") val nickName: String)

data class TankopediaInfo(@SerializedName("game_version") val gameVersion: Double,
                          @SerializedName("languages") val languages: Map<CountryCode, Language>,
                          @SerializedName("tanks_updated_at") val tanksUpdatedAt: Long,
                          @SerializedName("vehicle_crew_roles") val vehicleCrewRoles: Map<CrewRole, String>,
                          @SerializedName("vehicle_nations") val vehicleNations: Map<Nation, String>,
                          @SerializedName("vehicle_types") val vehicleTypes: Map<VehicleType, String>,
                          @SerializedName("achievement_sections") val achievementSections: Map<AchievementSection, AchievementSectionData>)

data class AchievementSectionData(@SerializedName("name") val name: String,
                                  @SerializedName("order") val order: Int)