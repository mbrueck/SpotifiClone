package com.example.musicplayeritunessample.data.model

import Results
import com.example.musicplayeritunessample.R

class AppRepository {

    fun loadArtist(): MutableList<Results>{

        return mutableListOf(
            Results(artistName = "Kool Savas", trackName = "Beste Tag meines Lebens", previewUrl = "https//", artwork = R.drawable._2_kool_savas_beste_tag_meines_lebens, trackTimeMillis = 0, kind = ""),
            Results(artistName = "Sido", trackName = "Ich und miene Maske", previewUrl = "https//", artwork = R.drawable._5_sido_maske, trackTimeMillis = 0, kind = ""),
            Results(artistName = "haiyti", trackName = "Akku", previewUrl = "https//", artwork = R.drawable._0_haiyti_toxic_ep, trackTimeMillis = 0, kind = ""),
            Results(artistName = "Materia", trackName = "Lila Wolken", previewUrl = "https//", artwork = R.drawable._0_marteria_zum_glueck_in_die_zukunft, trackTimeMillis = 0, kind = ""),
            Results(artistName = "KIZ", trackName = "Hurensohn", previewUrl = "https//", artwork = R.drawable._9_k_i_z_hahnenkampf, trackTimeMillis = 0, kind = ""),
            Results(artistName = "Audio88 & Yassin", trackName = "Täter oder Opfer", previewUrl = "https//", artwork = R.drawable.audio_88_yassin_normaler_samt, trackTimeMillis = 0, kind = ""),

            )
    }


}