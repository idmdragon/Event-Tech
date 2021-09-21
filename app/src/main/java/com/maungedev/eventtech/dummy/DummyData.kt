package com.maungedev.eventtech.dummy

import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.R
import com.maungedev.eventtech.ui.main.ui.conference.ConferenceCategory

object DummyData {
    fun generateDummyEvent(): List<EventIT> {
        val listData = arrayListOf<EventIT>()

        listData.add(
            EventIT(
                "",
                "Hology 1.0 App Inovation",
                "Conference",
                "Mobile Apps",
                "",
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null
            )
        )

        listData.add(
            EventIT(
                "",
                "Hology 2.0 App Inovation",
                "Conference",
                "Mobile Apps",
                "",
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null
            )
        )
        listData.add(
            EventIT(
                "",
                "Hology 3.0 App Inovation",
                "Conference",
                "Mobile Apps",
                "",
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null
            )
        )
        listData.add(
            EventIT(
                "",
                "Hology 4.0 App Inovation",
                "Conference",
                "Mobile Apps",
                "",
                "20 April 2000",
                "20:00",
                "Meet",
                "",
                "Lorem ipsum",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8LuyKp6QSNwhSnZKMmrFPaJ3z_X_Y6A3tkw&usqp=CAU",
                2,
                2,
                null
            )
        )

        return listData
    }

    fun generateConferenceCategory(): List<ConferenceCategory> {
        val listData = arrayListOf<ConferenceCategory>()

        listData.add(
            ConferenceCategory(
                0,
                "Semua Kategori",
                R.drawable.ic_all_category
            )
        )
        listData.add(
            ConferenceCategory(
                1,
                "UI/UX",
                R.drawable.ic_ui_ux
            )
        )
        listData.add(
            ConferenceCategory(
                2,
                "Securit",
                R.drawable.ic_security
            )
        )
        listData.add(
            ConferenceCategory(
                3,
                "Frontend",
                R.drawable.ic_frontend
            )
        )
        listData.add(
            ConferenceCategory(
                4,
                "Semua Kategori",
                R.drawable.ic_all_category
            )
        )
        listData.add(
            ConferenceCategory(
                5,
                "Cloud Computing",
                R.drawable.ic_cloud_computing
            )
        )
        listData.add(
            ConferenceCategory(
                6,
                "Mobile Apps",
                R.drawable.ic_mobile_apps
            )
        )
        listData.add(
            ConferenceCategory(
                7,
                "Backend",
                R.drawable.ic_backend
            )
        )
        listData.add(
            ConferenceCategory(
                8,
                "IoT",
                R.drawable.ic_iot
            )
        )
        listData.add(
            ConferenceCategory(
                9,
                "Career",
                R.drawable.ic_career
            )
        )

        return listData

    }
}