package com.cristhianbonilla.foundations.tracker

enum class TrackerSectionFamily : MyTrackerSectionFamily {
    END_REGISTER_USER {
        override fun type() = "EndRegisterUser"
    },
    MAGAZINE {
        override fun type() = "magazine"
    }
}
enum class TrackerSectionCategory : MyTrackerSectionCategory {
    AUTHENTICATION {
        override fun type() = "authentication"
    },
    MAGAZINE {
        override fun type() = "services"
    }
}

enum class TrackerSectionName : MyTrackerSectionName {
    MAGAZINE_READ {
        override fun type() = "magazine read"
    },
    REGISTER {
        override fun type() = "user registratin"
    },

    LOGIN {
        override fun type() = "user login"
    },
}