package com.maungedev.eventtech.dummy

import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.R

object DummyData {
    fun generateDummyEvent(): List<EventIT> {
        val listData = arrayListOf<EventIT>()

        listData.add(
            EventIT(
                "",
                "Hology 1.0 App Inovation",
                "Conference",
                "Mobile Apps",
                0,
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null,
                "HMIF"
            )
        )

        listData.add(
            EventIT(
                "",
                "Hology 2.0 App Inovation",
                "Conference",
                "Mobile Apps",
                10000,
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null,
                "UB"
            )
        )
        listData.add(
            EventIT(
                "",
                "Hology 3.0 App Inovation",
                "Conference",
                "Mobile Apps",
                5000,
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null,
                "UB"
            )
        )
        listData.add(
            EventIT(
                "",
                "Hology 4.0 App Inovation",
                "Conference",
                "Mobile Apps",
                2000,
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null,
                "TEKKOM UB"
            )
        )
        listData.add(
            EventIT(
                "",
                "Hology 5.0 App Inovation",
                "Conference",
                "Mobile Apps",
                0,
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null,
                "Dicoding ID"
            )
        )

        return listData
    }
/*
    fun generateConferenceCategory(): List<EventConferenceCategory> {
        val listData = arrayListOf<EventConferenceCategory>()

        listData.add(
            EventConferenceCategory(
                0,
                "Semua Kategori",
                R.drawable.ic_all_category
            )
        )
        listData.add(
            EventConferenceCategory(
                1,
                "UI/UX",
                R.drawable.ic_ui_ux
            )
        )
        listData.add(
            EventConferenceCategory(
                2,
                "Securit",
                R.drawable.ic_security
            )
        )
        listData.add(
            EventConferenceCategory(
                3,
                "Frontend",
                R.drawable.ic_frontend
            )
        )
        listData.add(
            EventConferenceCategory(
                4,
                "Cloud Computing",
                R.drawable.ic_cloud_computing
            )
        )
        listData.add(
            EventConferenceCategory(
                5,
                "Mobile Apps",
                R.drawable.ic_mobile_apps
            )
        )
        listData.add(
            EventConferenceCategory(
                6,
                "Backend",
                R.drawable.ic_backend
            )
        )
        listData.add(
            EventConferenceCategory(
                7,
                "IoT",
                R.drawable.ic_iot
            )
        )
        listData.add(
            EventConferenceCategory(
                8,
                "Career",
                R.drawable.ic_career
            )
        )

        return listData

    }

    fun generateCompetitionCategory(): List<EventCompetitionCategory> {
        val listData = arrayListOf<EventCompetitionCategory>()
        listData.add(
            EventCompetitionCategory(
                1,
                "Semua"
            )
        )

        listData.add(
            EventCompetitionCategory(
                2,
                "UI/UX"
            )
        )

        listData.add(
            EventCompetitionCategory(
                3,
                "App Inovation"
            )
        )
        listData.add(
            EventCompetitionCategory(
                4,
                "Capture The Flag"
            )
        )
        listData.add(
            EventCompetitionCategory(
                5,
                "Hackaton"
            )
        )
        listData.add(
            EventCompetitionCategory(
                6,
                "Game Jam"
            )
        )
        listData.add(
            EventCompetitionCategory(
                7,
                "Business Plan"
            )
        )

        return listData
    }*/
}