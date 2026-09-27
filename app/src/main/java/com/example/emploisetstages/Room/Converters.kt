package com.odc.emploisetstage.Room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(it)
        }
    }

    @TypeConverter
    fun fromStatut(statut: StatutCandidature): String {
        return statut.name
    }

    @TypeConverter
    fun toStatut(value: String): StatutCandidature {
        return StatutCandidature.valueOf(value)
    }
}